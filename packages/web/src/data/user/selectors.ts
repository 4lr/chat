import {Selector} from '../../utils';
import {createSelector} from 'reselect';
import {IStateUser} from './types';

const selectUser = Selector.getRootBranch('user');

export const selectUserId = createSelector(
    // @ts-ignore
    selectUser,
    (state: IStateUser) => state.userId,
);

export const selectIsAuthenticated = createSelector(
    // @ts-ignore
    selectUser,
    (state: IStateUser) => !!state.userId,
);
