import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Header from "./components/Header";
import Hello from "./components/Hello";
import Login from "./components/Login";
import Room from "./components/Room";
import axios from 'axios';

export default class App extends Component {

    state = {
        isAuthenticated: false
    };

    constructor(props) {
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

    Auth() {
        return this.state.isAuthenticated ?
            <Switch>
                <Route exact path='/' component={Hello}/>
                <Route path={`/:roomId`}
                       component={
                           (props) =>
                               <Room roomId={props.match.params.roomId}/>
                       }
                />
            </Switch> :
            <Login setToken={(jwtAuthResponse) => {
                axios.defaults.headers.common = {Authorization: `${jwtAuthResponse.tokenType} ${jwtAuthResponse.accessToken}`};
                this.setState({isAuthenticated: true});
            }}/>;
    }

}
