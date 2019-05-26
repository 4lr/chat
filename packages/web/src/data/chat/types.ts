import {ActionType} from 'typesafe-actions';
import {AxiosError} from 'axios';
import * as chatActions from './actions';
import {MessageTO} from '../../api/__generated__';

export enum CHAT_ACTION_TYPES {
    JOIN = '@@chat/join',
    JOIN_SUCCESS = '@@chat/join_success',
    JOIN_ERROR = '@@chat/join_error',
}

export interface IStateChat {
    messages: Map<string, MessageTO>;
    lastError: AxiosError | null;
}

export type TChatActions = typeof chatActions;
export type TChatActionType = ActionType<TChatActions>;

export type TActionOnJoin = ActionType<typeof chatActions.onJoin>;
