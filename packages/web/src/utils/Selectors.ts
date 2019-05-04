import { createSelector } from 'reselect';
import { IRootState } from '../models';

const checkState = (state: IRootState): IRootState => state;

export class Selector {
  public static getRootBranch(branchName: keyof IRootState) {
    return createSelector(
      checkState,
      (state: IRootState) => state[branchName],
    );
  }
}
