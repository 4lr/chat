import {connect} from 'react-redux';
import {IRootState} from '../../models';
import {onSignIn, onSignUp} from '../../data/user';
import {IDispatchToProps, IStateToProps, TOwnProps} from './index.interfaces';
import Login from './index.component';

const mapStateToProps = (): IStateToProps => ({});

const mapDispatchToProps = {
    onSignIn,
    onSignUp,
};

export default connect<IStateToProps, IDispatchToProps, TOwnProps, IRootState>(mapStateToProps, mapDispatchToProps)(Login);
