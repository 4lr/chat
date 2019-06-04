import React, {ChangeEvent, PureComponent} from 'react';
import {TProps} from './index.interfaces';

interface State {
    username: string,
    password: string,
}

export default class Login extends PureComponent<TProps, State> {

    state = {
        username: 'John',
        password: 'pass',
    };

    render() {
        return (
            <div>
                <input
                    placeholder={'username'}
                    onChange={this.handleChangeLogin}
                    value={this.state.username}
                />
                <input
                    placeholder={'password'}
                    onChange={this.handleChangePassword}
                    value={this.state.password}
                />
                <button onClick={this.handleSignin}>Sign in</button>
                <button onClick={this.handleSignup}>Sign up</button>
            </div>
        );
    }

    private readonly handleChangeLogin = (ev: ChangeEvent<HTMLInputElement>) => {
        this.setState({username: ev.target.value});
    };

    private readonly handleChangePassword = (ev: ChangeEvent<HTMLInputElement>) => {
        this.setState({password: ev.target.value});
    };

    private readonly handleSignin = () => {
        this.props.onLogin(this.state);
        this.setState({username: '', password: ''});
    };

    private readonly handleSignup = () => {
        this.props.onSignUp(this.state);
        this.setState({username: '', password: ''});
    };
}
