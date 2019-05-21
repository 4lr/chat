import { Selector } from '../../utils';

import { createSelector } from 'reselect';

import { IStateCustomer } from './types';

const selectCustomer = Selector.getRootBranch('customer');

export const selectToken = createSelector(
  selectCustomer,
  (state: IStateCustomer) => state.token,
);
