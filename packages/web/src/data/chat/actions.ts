import {action} from 'typesafe-actions';
import {AxiosError} from 'axios';
import {CHAT_ACTION_TYPES} from './types';
import {MessageTO, NewMessageTO} from '../../api/__generated__';

export const onJoin = (roomId: string) => action(CHAT_ACTION_TYPES.JOIN, roomId);
export const onJoinSuccess = (messages: MessageTO[]) => action(CHAT_ACTION_TYPES.JOIN_SUCCESS, messages);
export const onJoinError = (error: AxiosError) => action(CHAT_ACTION_TYPES.JOIN_ERROR, undefined, undefined, error);

export const onSend = (newMessage: NewMessageTO) => action(CHAT_ACTION_TYPES.SEND, newMessage);
export const onSendSuccess = (message: MessageTO) => action(CHAT_ACTION_TYPES.SEND_SUCCESS, message);
export const onSendError = (error: AxiosError) => action(CHAT_ACTION_TYPES.SEND_ERROR, undefined, undefined, error);
