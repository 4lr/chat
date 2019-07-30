import {ActionType} from 'typesafe-actions';
import {AxiosError} from 'axios';
import * as userActions from './actions';

export enum USER_ACTION_TYPES {
    LOGIN = '@@user/login',
    LOGIN_SUCCESS = '@@user/login_success',
    LOGIN_ERROR = '@@user/login_error',
    SIGNUP = '@@user/signup',
    SIGNUP_SUCCESS = '@@user/signup_success',
    SIGNUP_ERROR = '@@user/signup_error',
}

export interface IStateUser {
    userId: string;
    lastError: AxiosError | null;
}

export type TUserActions = typeof userActions;
export type TUserActionType = ActionType<TUserActions>;

export type TActionOnLogin = ActionType<typeof userActions.onLogin>;
export type TActionOnSignUp = ActionType<typeof userActions.onSignUp>;