import React, {Component} from 'react';
import {BrowserRouter as Router, Route, RouteComponentProps, Switch} from "react-router-dom";
import Header from "./components/Header";
import Hello from "./components/Hello";
import Login from "./components/Login";
import Room from "./components/Room";

export interface Props {
}

export interface State {
    isAuthenticated: boolean;
}

export default class App extends Component<Props, State> {

    state = {
        isAuthenticated: false,
    };

    constructor(props: Props) {
        super(props);
        this.Auth = this.Auth.bind(this);
    }

    render() {
        return (
            <Router>
                <Header/>
                <this.Auth/>
            </Router>
        );
    }

    protected Auth() {
        if (this.state.isAuthenticated) {
            return (
                <Switch>
                    <Route exact path='/' component={Hello}/>
                    <Route path={`/:roomId`}
                           component={
                               (props: RouteComponentProps<{ roomId: string }>) =>
                                   <Room roomId={props.match.params.roomId}/>
                           }
                    />
                </Switch>
            );
        }
        return <Login/>;
    }
}
