import {StateObservable} from 'redux-observable';
import {IStateUser} from '../data/user/types';
import {IStateChat} from '../data/chat/types';

export interface IRootState {
    chat: IStateChat,
    user: IStateUser,
}

export type TStateObservableRootState = StateObservable<IRootState>;
