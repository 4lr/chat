import React, {ChangeEvent, PureComponent} from 'react';
import {AuthControllerApi, AuthTokenResponse} from "../api";
import {basePath} from "../config";

export interface Props {
    setToken: (authTokenResponse: AuthTokenResponse) => void;
}

export interface State {
    username: string,
    password: string,
}

const user = {
    username: 'John',
    password: 'pass',
};

const authController = new AuthControllerApi({basePath: basePath});

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
        const authTokenResponse = (await authController.signinUsingPOST({
            username: this.state.username,
            password: this.state.password
        })).data;
        this.props.setToken(authTokenResponse);
        this.setState(user);
    };

    protected async handleSignup() {
        const authTokenResponse = (await authController.signupUsingPOST({
            username: this.state.username,
            password: this.state.password
        })).data;
        this.props.setToken(authTokenResponse);
        this.setState(user);
    };
}
