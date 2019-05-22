import {connect} from 'react-redux';
import {IRootState} from '../../models';
import {IDispatchToProps, IStateToProps, TOwnProps} from './index.interfaces';
import Room from './index.component';
import {onJoin, selectMessages} from '../../data/chat';

const mapStateToProps = (store: IRootState): IStateToProps => ({
    messages: selectMessages(store),
});

const mapDispatchToProps = {
    onJoin,
};

export default connect<IStateToProps, IDispatchToProps, TOwnProps, IRootState>(mapStateToProps, mapDispatchToProps)(Room);
