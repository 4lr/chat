import React, {PureComponent} from 'react';
import {authController} from "../api/AuthController";

const user = {
    username: 'John',
    password: 'pass',
};

export default class Login extends PureComponent {

    state = user;

    constructor(props) {
        super(props);
        this.handleChangeLogin = this.handleChangeLogin.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleSignin = this.handleSignin.bind(this);
        this.handleSignup = this.handleSignup.bind(this);
    }

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

    handleChangeLogin(ev) {
        this.setState({username: ev.target.value});
    };

    handleChangePassword(ev) {
        this.setState({password: ev.target.value});
    };

    async handleSignin() {
        const jwtAuthResponse = await authController.signin({username: this.state.username, password: this.state.password});
        this.props.setToken(jwtAuthResponse);
        this.setState(user);
    };

    async handleSignup() {
        const jwtAuthResponse = await authController.signin({username: this.state.username, password: this.state.password});
        this.props.setToken(jwtAuthResponse);
        this.setState(user);
    };
}
