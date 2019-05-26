import {USER_ACTION_TYPES, IStateUser, TUserActionType} from './types';
import {PURGE, TPurgeActionType} from '../purge';

const initialStateUser: IStateUser = {
    userId: null,
    lastError: null,
};

export default function userReducer(
    state: IStateUser = initialStateUser,
    action: TUserActionType | TPurgeActionType,
): IStateUser {
    switch (action.type) {
        case USER_ACTION_TYPES.LOGIN:
            return state;
        case USER_ACTION_TYPES.LOGIN_SUCCESS:
            return {...state, userId: action.payload.userId};
        case USER_ACTION_TYPES.LOGIN_ERROR:
            return {...state, lastError: action.error};
        case PURGE.STORE:
            return {...initialStateUser};
        default:
            return state;
    }
}
