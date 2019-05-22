export type TOwnProps = null;

export interface IStateToProps {
    isAuthenticated: boolean,
    token: string | null
}

export type IDispatchToProps = null;

export type TProps = TOwnProps & IStateToProps & IDispatchToProps;
