import axios, { AxiosInstance } from 'axios';
import { isObject, isFunction, isString, get, set } from 'lodash';
import { TStateObservableRootState } from '../../../models';
import { API_URL } from '../../../config';

const accept = 'Accept';

const _restApi: AxiosInstance = axios.create({
  baseURL: API_URL,
  headers: {
    [accept]: '*/*',
    'Content-Type': 'application/json',
  },
});

export const restApi = (
  state$?: TStateObservableRootState,
  callback?: (apiObject: typeof _restApi) => void,
): AxiosInstance => {
  if (isObject(state$)) {
    const sid: string | undefined = get(state$, 'value.customer.sid');

    if (isString(sid)) {
      set<AxiosInstance>(_restApi, 'defaults.headers.common.Authorization', `Bearer ${sid}`);
    }
  }

  if (isFunction(callback)) {
    callback(_restApi);
  }

  return _restApi;
};
