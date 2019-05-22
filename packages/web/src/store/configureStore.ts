import {applyMiddleware, createStore} from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';

import rootEpic from '../data/rootEpic';
import rootReducer from '../data/rootReducer';

import logger from './middleware/logger';
import {createEpicMiddleware} from 'redux-observable';

const epicMiddleware = createEpicMiddleware();

export default function configureStore() {
    const store = createStore(rootReducer as any, composeWithDevTools(applyMiddleware(logger, epicMiddleware)));

    epicMiddleware.run(rootEpic as any);

    return {store};
}
