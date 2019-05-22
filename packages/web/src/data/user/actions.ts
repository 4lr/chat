import {action} from 'typesafe-actions';
import {AxiosError} from 'axios';
import {USER_ACTION_TYPES} from './types';
import {AuthTokenResponse, SignInRequest} from '../../api/__generated__';

export const onLogin = (authData: SignInRequest) => action(USER_ACTION_TYPES.LOGIN, authData);
export const onLoginSuccess = (authTokenResponse: AuthTokenResponse) => action(USER_ACTION_TYPES.LOGIN_SUCCESS, authTokenResponse);
export const onLoginError = (error: AxiosError) => action(USER_ACTION_TYPES.LOGIN_ERROR, undefined, undefined, error);
