import { combineReducers } from 'redux';
import customerReducer from './customer';
import { IRootState } from '../models';

const rootReducer = combineReducers<IRootState>({
  customer: customerReducer,
});

export default rootReducer;
