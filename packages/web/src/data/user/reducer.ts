import {USER_ACTION_TYPES, IStateUser, TUserActionType} from './types';
import {PURGE, TPurgeActionType} from '../purge';

const initialStateUser: IStateUser = {
    userId: '',
    lastError: null,
};

export default function userReducer(
    state: IStateUser = initialStateUser,
    action: TUserActionType | TPurgeActionType,
): IStateUser {
    switch (action.type) {
        case USER_ACTION_TYPES.SIGNIN:
            return state;
        case USER_ACTION_TYPES.SIGNIN_SUCCESS:
            return {...state, userId: action.payload.userId};
        case USER_ACTION_TYPES.SIGNIN_ERROR:
            return {...state, lastError: action.error};
        case USER_ACTION_TYPES.SIGNUP:
            return state;
        case USER_ACTION_TYPES.SIGNUP_SUCCESS:
            return {...state, userId: action.payload.userId};
        case USER_ACTION_TYPES.SIGNUP_ERROR:
            return {...state, lastError: action.error};
        case PURGE.STORE:
            return {...initialStateUser};
        default:
            return state;
    }
}
