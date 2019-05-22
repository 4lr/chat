import React, {Component} from 'react';
import {BrowserRouter, Route, RouteComponentProps, Switch} from 'react-router-dom';
import {TProps} from './index.interfaces';
import Room from '../Room';
import Hello from '../Hello';
import Header from '../Header';
import Login from '../Login';

class UserSwitch extends Component<any> {

    render() {
        return (
            <Switch>
                <Route exact path='/' component={Hello}/>
                <Route path={'/:roomId'}
                       component={
                           (props: RouteComponentProps<{ roomId: string }>) =>
                               <Room roomId={props.match.params.roomId}/>
                       }
                />
            </Switch>
        );
    }
}

export default class Router extends Component<TProps> {

    render() {
        return (
            <BrowserRouter>
                <Header/>
                {this.props.isAuthenticated ? <UserSwitch/> : <Login/>}
            </BrowserRouter>
        );
    }
}
