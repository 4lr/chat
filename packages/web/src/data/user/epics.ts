import {from, Observable, of} from 'rxjs';
import {USER_ACTION_TYPES, TActionOnLogin, TActionOnSignUp} from './types';
import {combineEpics, ofType} from 'redux-observable';
import {AxiosError, AxiosResponse} from 'axios';
import {onLoginError, onLoginSuccess, onSignUpError, onSignUpSuccess} from './actions';
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
                    const userId = JSON.parse(atob(response.data.accessToken)).userId;
                    return onLoginSuccess({userId: userId});
                }),
                catchError((error: AxiosError) => of(onLoginError(error))),
            );
        }),
    );
};

const onSignUpEpic = (action$: Observable<TActionOnSignUp>) => {
    return action$.pipe(
        ofType(USER_ACTION_TYPES.SIGNUP),
        mergeMap(({payload}: TActionOnSignUp) => {
            return from(authController.signupUsingPOST(payload)).pipe(
                map((response: AxiosResponse<AuthTokenResponse>) => {
                    axios.defaults.headers.common = {Authorization: response.data.accessToken};
                    const userId = JSON.parse(atob(response.data.accessToken)).userId;
                    return onSignUpSuccess({userId: userId});
                }),
                catchError((error: AxiosError) => of(onSignUpError(error))),
            );
        }),
    );
};

export default combineEpics(onLoginEpic, onSignUpEpic);
