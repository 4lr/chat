import {defer, from, Observable, of} from 'rxjs';
import {CHAT_ACTION_TYPES, TActionOnJoin, TActionOnSend} from './types';
import {combineEpics, ofType} from 'redux-observable';
import {AxiosError, AxiosResponse} from 'axios';
import {onJoinError, onJoinSuccess, onSendError, onSendSuccess} from './actions';
import {catchError, delay, map, mergeMap, repeatWhen, takeWhile} from 'rxjs/operators';
import {MessageControllerApi, MessageTO} from '../../api/__generated__';
import uuidv4 from 'uuid';
import {TStateObservableRootState} from '../../models';

const messageControllerApi = new MessageControllerApi();
const API_DELAY = 1000;

const normaliser = (message: MessageTO): void => {
    message.timestamp = new Date(message.timestamp);
};

const collector = (messages: Map<string, MessageTO>, message: MessageTO): Map<string, MessageTO> => {
    messages.set(message.id, message);
    return messages;
};

const onJoinEpic = (action$: Observable<TActionOnJoin>, state$: TStateObservableRootState) => {
    return action$.pipe(
        ofType(CHAT_ACTION_TYPES.JOIN),
        mergeMap(({payload}: TActionOnJoin) => {
            return defer(() => from(messageControllerApi.getMessagesByRoomIdUsingGET(payload))).pipe(
                repeatWhen((complete: Observable<any>) => complete.pipe(delay(API_DELAY), takeWhile(() => {
                    return state$.value.chat.joined;
                }))),
                map((response: AxiosResponse<MessageTO[]>) => {
                    response.data.forEach(normaliser);
                    return onJoinSuccess(new Map<string, MessageTO>([
                        ...state$.value.chat.messages,
                        ...response.data.reduce(collector, new Map<string, MessageTO>())
                    ]));
                }),
                catchError((error: AxiosError) => {
                    return of(onJoinError(error))
                }),
            );
        }),
    );
};

const onSendEpic = (action$: Observable<TActionOnSend>, state$: TStateObservableRootState) => {
    return action$.pipe(
        ofType(CHAT_ACTION_TYPES.SEND),
        mergeMap(({payload}: TActionOnSend) => {
            payload.id = uuidv4();
            return from(messageControllerApi.postMessageUsingPOST(payload)).pipe(
                map((response: AxiosResponse<MessageTO>) => {
                    const message = response.data;
                    normaliser(message);
                    return onSendSuccess(new Map<string, MessageTO>([
                        ...state$.value.chat.messages,
                        ...[message].reduce(collector, new Map<string, MessageTO>())
                    ]));
                }),
                catchError((error: AxiosError) => of(onSendError(error))),
            );
        }),
    );
};

export default combineEpics(onJoinEpic, onSendEpic);
