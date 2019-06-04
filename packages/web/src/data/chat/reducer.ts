import {CHAT_ACTION_TYPES, IStateChat, TChatActionType} from './types';
import {PURGE, TPurgeActionType} from '../purge';
import {MessageTO} from '../../api/__generated__';

const initialStateChat: IStateChat = {
    messages: new Map<string, MessageTO>(),
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
        case CHAT_ACTION_TYPES.SEND:
            return state;
        case CHAT_ACTION_TYPES.SEND_SUCCESS:
            return {...state, messages: action.payload};
        case CHAT_ACTION_TYPES.SEND_ERROR:
            return {...state, lastError: action.error};
        case PURGE.STORE:
            return {...initialStateChat};
        default:
            return state;
    }
}
