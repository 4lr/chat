import {connect} from 'react-redux';
import {IRootState} from '../../models';
import {selectIsAuthenticated} from '../../data/user';
import Router from './index.component';
import {IDispatchToProps, IStateToProps, TOwnProps} from './index.interfaces';

const mapStateToProps = (store: IRootState): IStateToProps => ({
    isAuthenticated: selectIsAuthenticated(store),
});

const mapDispatchToProps = null;

export default connect<IStateToProps, IDispatchToProps, TOwnProps, IRootState>(mapStateToProps, mapDispatchToProps)(Router);
