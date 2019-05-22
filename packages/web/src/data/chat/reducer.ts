import {CHAT_ACTION_TYPES, IStateChat, TChatActionType} from './types';
import {PURGE, TPurgeActionType} from '../purge';

const initialStateChat: IStateChat = {
    messages: [],
    lastError: null,
};

export default function chatReducer(
    state: IStateChat = initialStateChat,
    action: TChatActionType | TPurgeActionType,
): IStateChat {
    switch (action.type) {
        case CHAT_ACTION_TYPES.JOIN:
            return state;
        case CHAT_ACTION_TYPES.JOIN_SUCCESS:
            return {...state, messages: action.payload};
        case CHAT_ACTION_TYPES.JOIN_ERROR:
            return {...state, lastError: action.error};
        case PURGE.STORE:
            return {...initialStateChat};
        default:
            return state;
    }
}
