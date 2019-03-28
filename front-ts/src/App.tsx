import React, {Component} from 'react';
import {BrowserRouter as Router, Route, RouteComponentProps} from "react-router-dom";
import Header from "./components/Header";
import Hello from "./components/Hello";
import Room from "./components/Room";


export default class App extends Component {
    render() {
        return (
            <Router>
                <Header/>
                <Route exact path='/' component={Hello}/>
                <Route path={`/:roomId`}
                       component={
                           (props: RouteComponentProps<{ roomId: string }>) =>
                               <Room roomId={props.match.params.roomId}/>
                       }
                />
            </Router>
        );
    }
}
