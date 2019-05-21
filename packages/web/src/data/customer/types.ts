import { ActionType } from 'typesafe-actions';
import { AxiosError } from 'axios';
import * as customerActions from './actions';

export enum CUSTOMER_ACTION_TYPES {
  LOGIN = '@@customer/login',
  LOGIN_SUCCESS = '@@customer/login_success',
  LOGIN_ERROR = '@@customer/login_error',
}

export interface IAuthData {
  login: string;
  password: string;
}

export interface IStateCustomer {
  token: string | null;
  loginError: AxiosError | null;
}

export type TCustomerActions = typeof customerActions;
export type TCustomerActionType = ActionType<TCustomerActions>;

export type TActionOnLogin = ActionType<typeof customerActions.onLogin>;
