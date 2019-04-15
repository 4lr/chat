import React, {ChangeEvent, PureComponent} from 'react';
import {authController, JwtAuthResponse} from "../api/AuthController";

export interface Props {
    setToken: (jwtAuthResponse: JwtAuthResponse) => void;
}

export interface State {
    username: string,
    password: string,
}

const user = {
    username: 'John',
    password: 'pass',
};

export default class Login extends PureComponent<Props, State> {

    state = user;

    constructor(props: Props) {
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

    protected handleChangeLogin(ev: ChangeEvent<HTMLInputElement>) {
        this.setState({username: ev.target.value});
    };

    protected handleChangePassword(ev: ChangeEvent<HTMLInputElement>) {
        this.setState({password: ev.target.value});
    };

    protected async handleSignin() {
        const jwtAuthResponse = await authController.signin({username: this.state.username, password: this.state.password});
        this.props.setToken(jwtAuthResponse);
        this.setState(user);
    };

    protected async handleSignup() {
        const jwtAuthResponse = await authController.signin({username: this.state.username, password: this.state.password});
        this.props.setToken(jwtAuthResponse);
        this.setState(user);
    };
}
