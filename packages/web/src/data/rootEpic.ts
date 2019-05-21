import { combineEpics } from 'redux-observable';

import customerEpics from './customer/epics';

export const rootEpic = combineEpics(customerEpics);

export default rootEpic;
