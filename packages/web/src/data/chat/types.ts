import {ActionType} from 'typesafe-actions';
import {AxiosError} from 'axios';
import * as chatActions from './actions';
import {MessageTO} from '../../api/__generated__';

export enum CHAT_ACTION_TYPES {
    JOIN = '@@chat/join',
    UNJOIN = '@@chat/unjoin',
    JOIN_SUCCESS = '@@chat/join_success',
    JOIN_ERROR = '@@chat/join_error',
    SEND = '@@chat/send',
    SEND_SUCCESS = '@@chat/send_success',
    SEND_ERROR = '@@chat/send_error',
}

export interface IStateChat {
    joined: boolean;
    messages: Map<string, MessageTO>;
    lastError: AxiosError | null;
}

export type TChatActions = typeof chatActions;
export type TChatActionType = ActionType<TChatActions>;

export type TActionOnJoin = ActionType<typeof chatActions.onJoin>;
export type TActionOnSend = ActionType<typeof chatActions.onSend>;
