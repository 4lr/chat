import {createLogger} from 'redux-logger';

const logger = createLogger({
    actionTransformer: (action: any) => ({...action, type: String(action.type)}),
    collapsed: false,
    level: 'info',
    logger: console,
    // @ts-ignore
    predicate: () => process.env.NODE_ENV !== 'production',
});

export default logger;
