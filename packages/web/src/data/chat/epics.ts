import {from, Observable, of} from 'rxjs';
import {CHAT_ACTION_TYPES, TActionOnJoin, TActionOnSend} from './types';
import {combineEpics, ofType} from 'redux-observable';
import {AxiosError, AxiosResponse} from 'axios';
import {onJoinError, onJoinSuccess, onSendError, onSendSuccess} from './actions';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {MessageControllerApi, MessageTO} from '../../api/__generated__';

const messageControllerApi = new MessageControllerApi();

const onJoinEpic = (action$: Observable<TActionOnJoin>) => {
    return action$.pipe(
        ofType(CHAT_ACTION_TYPES.JOIN),
        mergeMap(({payload}: TActionOnJoin) => {
            return from(messageControllerApi.getMessagesByRoomIdUsingGET(payload)).pipe(
                map((response: AxiosResponse<MessageTO[]>) => {
                    return onJoinSuccess(response.data);
                }),
                catchError((error: AxiosError) => of(onJoinError(error))),
            );
        }),
    );
};

const onSendEpic = (action$: Observable<TActionOnSend>) => {
    return action$.pipe(
        ofType(CHAT_ACTION_TYPES.SEND),
        mergeMap(({payload}: TActionOnSend) => {
            return from(messageControllerApi.postMessageUsingPOST(payload)).pipe(
                map((response: AxiosResponse<MessageTO>) => {
                    return onSendSuccess(response.data);
                }),
                catchError((error: AxiosError) => of(onSendError(error))),
            );
        }),
    );
};

export default combineEpics(onJoinEpic, onSendEpic);
