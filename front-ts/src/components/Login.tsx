import React, {ChangeEvent, PureComponent} from 'react';

export interface Props {
}

export interface State {
    login: string,
    password: string,
}

export default class Login extends PureComponent<Props, State> {

    state = {
        login: '',
        password: '',
    };

    constructor(props: Props) {
        super(props);
        this.handleChangeLogin = this.handleChangeLogin.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    render() {
        return (
            <div>
                <input
                    placeholder={'login'}
                    onChange={this.handleChangeLogin}
                    value={this.state.login}
                />
                <input
                    placeholder={'password'}
                    onChange={this.handleChangePassword}
                    value={this.state.password}
                />
                <button onClick={this.handleSubmit}>Send</button>
            </div>
        );
    }

    protected handleChangeLogin(ev: ChangeEvent<HTMLInputElement>) {
        this.setState({login: ev.target.value});
    };

    protected handleChangePassword(ev: ChangeEvent<HTMLInputElement>) {
        this.setState({password: ev.target.value});
    };

    protected handleSubmit() {
    };
}
