import {Selector} from '../../utils';
import {createSelector} from 'reselect';
import {IStateUser} from './types';

const selectUser = Selector.getRootBranch('user');

export const selectUserId = createSelector(
    selectUser,
    (state: IStateUser) => state.userId,
);

export const selectIsAuthenticated = createSelector(
    selectUser,
    (state: IStateUser) => !!state.userId,
);
