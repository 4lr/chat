import {combineReducers} from 'redux';
import userReducer from './user';
import {IRootState} from '../models';
import chatReducer from './chat';

const rootReducer = combineReducers<IRootState>({
    chat: chatReducer,
    user: userReducer,
});

export default rootReducer;
