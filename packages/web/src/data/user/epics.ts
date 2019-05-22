import {from, Observable, of} from 'rxjs';
import {USER_ACTION_TYPES, TActionOnLogin} from './types';
import {combineEpics, ofType} from 'redux-observable';
import {AxiosError, AxiosResponse} from 'axios';
import {onLoginError, onLoginSuccess} from './actions';
import {catchError, map, mergeMap} from 'rxjs/operators';
import {AuthControllerApi, AuthTokenResponse} from '../../api/__generated__';
import axios from 'axios';

const authController = new AuthControllerApi();

const onLoginEpic = (action$: Observable<TActionOnLogin>) => {
    return action$.pipe(
        ofType(USER_ACTION_TYPES.LOGIN),
        mergeMap(({payload}: TActionOnLogin) => {
            return from(authController.signinUsingPOST(payload)).pipe(
                map((response: AxiosResponse<AuthTokenResponse>) => {
                    axios.defaults.headers.common = {Authorization: response.data.accessToken};
                    return onLoginSuccess(response.data);
                }),
                catchError((error: AxiosError) => of(onLoginError(error))),
            );
        }),
    );
};

export default combineEpics(onLoginEpic);
