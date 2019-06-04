import {onLogin, onSignUp} from '../../data/user';

export type TOwnProps = null;

export type IStateToProps = null;

export interface IDispatchToProps {
    onLogin: typeof onLogin;
    onSignUp: typeof onSignUp;
}

export type TProps = TOwnProps & IStateToProps & IDispatchToProps;
