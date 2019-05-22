import {action} from 'typesafe-actions';
import {AxiosError} from 'axios';
import {CHAT_ACTION_TYPES} from './types';
import {MessageTO} from '../../api/__generated__';

export const onJoin = (roomId: string) => action(CHAT_ACTION_TYPES.JOIN, roomId);
export const onJoinSuccess = (messages: MessageTO[]) => action(CHAT_ACTION_TYPES.JOIN_SUCCESS, messages);
export const onJoinError = (error: AxiosError) => action(CHAT_ACTION_TYPES.JOIN_ERROR, undefined, undefined, error);
