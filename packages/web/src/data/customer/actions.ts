import { action } from 'typesafe-actions';
import { AxiosError } from 'axios';
import { IAuthData, CUSTOMER_ACTION_TYPES } from './types';

export const onLogin = (authData: IAuthData) => action(CUSTOMER_ACTION_TYPES.LOGIN, authData);
export const onLoginSuccess = (data: any) => action(CUSTOMER_ACTION_TYPES.LOGIN_SUCCESS, data);
export const onLoginError = (error: AxiosError) => action(CUSTOMER_ACTION_TYPES.LOGIN_ERROR, error);
