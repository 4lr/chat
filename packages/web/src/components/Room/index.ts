import {connect} from 'react-redux';
import {IRootState} from '../../models';
import {IDispatchToProps, IStateToProps, TOwnProps} from './index.interfaces';
import Room from './index.component';
import {onJoin, onSend, onUnjoin, selectMessages} from '../../data/chat';
import {selectUserId} from '../../data/user';

const mapStateToProps = (store: IRootState): IStateToProps => ({
    messages: selectMessages(store),
    userId: selectUserId(store),
});

const mapDispatchToProps = {
    onJoin,
    onUnjoin,
    onSend,
};

export default connect<IStateToProps, IDispatchToProps, TOwnProps, IRootState>(mapStateToProps, mapDispatchToProps)(Room);
