import {onJoin} from '../../data/chat';
import {MessageTO} from '../../api/__generated__';

export interface TOwnProps {
    roomId: string
}

export type IStateToProps = {
    messages: MessageTO[],
};

export interface IDispatchToProps {
    onJoin: typeof onJoin
}

export type TProps = TOwnProps & IStateToProps & IDispatchToProps;
