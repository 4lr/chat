import {onLogin} from '../../data/user';

export type TOwnProps = null;

export type IStateToProps = null;

export interface IDispatchToProps {
    onLogin: typeof onLogin
}

export type TProps = TOwnProps & IStateToProps & IDispatchToProps;
