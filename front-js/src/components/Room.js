import React, {PureComponent} from 'react';
import {messageStore} from "../stores/MessageStore";


export default class Room extends PureComponent {

    state = {
        newMessageText: '',
        messages: []
    };

    subscriptionId = '';

    componentDidMount() {
        this.subscriptionId = messageStore.subscribe(this.props.roomId, (messages) => {
            this.setState({messages: messages});
        });
    }

    componentWillUnmount() {
        messageStore.unSubscribe(this.subscriptionId);
    }


    handleChange = (ev) => {
        this.setState({newMessageText: ev.target.value});
    };

    handleSubmit = () => {
        messageStore.postMessage({roomId: this.props.roomId, body: this.state.newMessageText});
        this.setState({newMessageText: ''});
    };

    render() {
        return (
            <div>
                <div>
                    Room {this.props.roomId}
                </div>
                <div style={{marginBottom:"30px"}}>
                    {this.state.messages.map((message) => <div key={message.id}>{message.body}</div>) }
                </div>
                <div style={{position:"fixed",bottom:"0px"}}>
                    <input
                        onChange={this.handleChange.bind(this)}
                        value={this.state.newMessageText}
                    />
                    <button onClick={this.handleSubmit.bind(this)}>Send</button>
                </div>
            </div>
        );
    }
}
