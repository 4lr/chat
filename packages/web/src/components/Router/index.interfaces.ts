export type TOwnProps = null;

export interface IStateToProps {
    isAuthenticated: boolean;
}

export type IDispatchToProps = null;

export type TProps = TOwnProps & IStateToProps & IDispatchToProps;
