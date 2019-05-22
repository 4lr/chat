import {combineEpics} from 'redux-observable';
import chatEpics from './chat/epics';
import userEpics from './user/epics';

export const rootEpic = combineEpics(chatEpics, userEpics);

export default rootEpic;
