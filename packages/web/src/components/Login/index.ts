import {connect} from 'react-redux';
import {IRootState} from '../../models';
import {onLogin, onSignUp} from '../../data/user';
import {IDispatchToProps, IStateToProps, TOwnProps} from './index.interfaces';
import Login from './index.component';

const mapStateToProps = (): IStateToProps => null;

const mapDispatchToProps = {
    onLogin,
    onSignUp,
};

export default connect<IStateToProps, IDispatchToProps, TOwnProps, IRootState>(null, mapDispatchToProps)(Login);
