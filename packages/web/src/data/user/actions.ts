import {action} from 'typesafe-actions';
import {AxiosError} from 'axios';
import {USER_ACTION_TYPES} from './types';
import {SignInRequest, SignUpRequest} from '../../api/__generated__';

//TODO rename Login to SignIn
export const onLogin = (authData: SignInRequest) => action(USER_ACTION_TYPES.LOGIN, authData);
export const onLoginSuccess = (authTokenResponse: {userId: string}) => action(USER_ACTION_TYPES.LOGIN_SUCCESS, authTokenResponse);
export const onLoginError = (error: AxiosError) => action(USER_ACTION_TYPES.LOGIN_ERROR, undefined, undefined, error);

export const onSignUp = (authData: SignUpRequest) => action(USER_ACTION_TYPES.SIGNUP, authData);
export const onSignUpSuccess = (authTokenResponse: {userId: string}) => action(USER_ACTION_TYPES.SIGNUP_SUCCESS, authTokenResponse);
export const onSignUpError = (error: AxiosError) => action(USER_ACTION_TYPES.SIGNUP_ERROR, undefined, undefined, error);
