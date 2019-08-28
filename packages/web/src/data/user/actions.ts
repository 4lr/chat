import {action} from 'typesafe-actions';
import {AxiosError} from 'axios';
import {USER_ACTION_TYPES} from './types';
import {SignInRequest, SignUpRequest} from '../../api/__generated__';

export const onSignIn = (authData: SignInRequest) => action(USER_ACTION_TYPES.SIGNIN, authData);
export const onSignInSuccess = (authTokenResponse: {userId: string}) => action(USER_ACTION_TYPES.SIGNIN_SUCCESS, authTokenResponse);
export const onSignInError = (error: AxiosError) => action(USER_ACTION_TYPES.SIGNIN_ERROR, undefined, undefined, error);

export const onSignUp = (authData: SignUpRequest) => action(USER_ACTION_TYPES.SIGNUP, authData);
export const onSignUpSuccess = (authTokenResponse: {userId: string}) => action(USER_ACTION_TYPES.SIGNUP_SUCCESS, authTokenResponse);
export const onSignUpError = (error: AxiosError) => action(USER_ACTION_TYPES.SIGNUP_ERROR, undefined, undefined, error);
