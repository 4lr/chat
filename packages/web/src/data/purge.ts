import {action, ActionType} from 'typesafe-actions';

export enum PURGE {
    STORE = '@@purge/store',
}

export const purgeStore = () => action(PURGE.STORE);
export type TPurgeActionType = ActionType<typeof purgeStore>;
