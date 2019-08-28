import {onSignIn, onSignUp} from '../../data/user';

export type TOwnProps = null;

export type IStateToProps = {};

export interface IDispatchToProps {
    onSignIn: typeof onSignIn;
    onSignUp: typeof onSignUp;
}

export type TProps = TOwnProps & IStateToProps & IDispatchToProps;
