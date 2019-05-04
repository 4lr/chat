import { Observable, from, of } from 'rxjs';
import { TActionOnLogin, CUSTOMER_ACTION_TYPES } from './types';
import { TStateObservableRootState } from '../../models';
import { ofType, combineEpics } from 'redux-observable';
import { restApi } from '../../store/middleware/api';
import { AxiosResponse, AxiosError } from 'axios';
import { onLoginSuccess, onLoginError } from './actions';
import { get } from 'lodash';
import { mergeMap, map, catchError } from 'rxjs/operators';

const onLoginEpic = (action$: Observable<TActionOnLogin>, state$: TStateObservableRootState) => {
  return action$.pipe(
    ofType(CUSTOMER_ACTION_TYPES.LOGIN),
    mergeMap(({ payload }: TActionOnLogin) => {
      return from(
        restApi(state$)({
          data: payload,
          method: 'post',
          url: 'login',
        }),
      ).pipe(
        map((response: AxiosResponse) => onLoginSuccess(get(response, 'data'))),
        catchError((error: AxiosError) => of(onLoginError(error))),
      );
    }),
  );
};

export default combineEpics(onLoginEpic);
