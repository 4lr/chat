import {Selector} from '../../utils';
import {createSelector} from 'reselect';
import {IStateUser} from './types';

const selectUser = Selector.getRootBranch('user');

export const selectToken = createSelector(
    // @ts-ignore
    selectUser,
    (state: IStateUser) => state.accessToken,
);

export const selectIsAuthenticated = createSelector(
    // @ts-ignore
    selectUser,
    (state: IStateUser) => !!state.accessToken,
);
