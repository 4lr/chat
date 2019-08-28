import {ActionType} from 'typesafe-actions';
import {AxiosError} from 'axios';
import * as userActions from './actions';

export enum USER_ACTION_TYPES {
    SIGNIN = '@@user/signin',
    SIGNIN_SUCCESS = '@@user/signin_success',
    SIGNIN_ERROR = '@@user/signin_error',
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

export type TActionOnSignIn = ActionType<typeof userActions.onSignIn>;
export type TActionOnSignUp = ActionType<typeof userActions.onSignUp>;
