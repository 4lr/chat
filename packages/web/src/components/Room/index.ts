import {connect} from 'react-redux';
import {IRootState} from '../../models';
import {IDispatchToProps, IStateToProps, TOwnProps} from './index.interfaces';
import Room from './index.component';
import {onJoin, onSend, selectMessages} from '../../data/chat';

const mapStateToProps = (store: IRootState): IStateToProps => ({
    messages: selectMessages(store),
});

const mapDispatchToProps = {
    onJoin,
    onSend,
};

export default connect<IStateToProps, IDispatchToProps, TOwnProps, IRootState>(mapStateToProps, mapDispatchToProps)(Room);
