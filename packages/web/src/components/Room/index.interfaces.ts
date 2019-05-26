import {onJoin, onSend} from '../../data/chat';
import {MessageTO} from '../../api/__generated__';

export interface TOwnProps {
    roomId: string
}

export type IStateToProps = {
    messages: MessageTO[],
};

export interface IDispatchToProps {
    onJoin: typeof onJoin
    onSend: typeof onSend
}

export type TProps = TOwnProps & IStateToProps & IDispatchToProps;
