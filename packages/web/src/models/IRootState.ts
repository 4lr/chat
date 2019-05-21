import { StateObservable } from 'redux-observable';
import { IStateCustomer } from '../data/customer/types';

export interface IRootState {
  customer: IStateCustomer;
}

export type TStateObservableRootState = StateObservable<IRootState>;
