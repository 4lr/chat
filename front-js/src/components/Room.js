import React, {PureComponent} from 'react';
import {messageStore} from "../stores/MessageStore";


export default class Room extends PureComponent {

    state = {
        newMessageText: '',
        messages: []
    };

    subscriptionId = '';

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.subscriptionId = messageStore.subscribe(this.props.roomId, (messages) => {
            this.setState({messages: messages});
        });
    }

    componentWillUnmount() {
        messageStore.unSubscribe(this.subscriptionId);
    }

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
                        onChange={this.handleChange}
                        value={this.state.newMessageText}
                    />
                    <button onClick={this.handleSubmit}>Send</button>
                </div>
            </div>
        );
    }

    handleChange = (ev) => {
        this.setState({newMessageText: ev.target.value});
    };

    handleSubmit = () => {
        messageStore.postMessage({roomId: this.props.roomId, body: this.state.newMessageText});
        this.setState({newMessageText: ''});
    };
}
