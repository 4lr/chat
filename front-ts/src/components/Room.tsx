import React, {ChangeEvent, PureComponent} from 'react';
import {Message, messageStore} from "../stores/MessageStore";

export interface Props {
    roomId: string
}

export interface State {
    newMessageText: string,
    messages: Message[]
}

export default class Room extends PureComponent<Props, State> {

    state = {
        newMessageText: '',
        messages: []
    };

    protected subscriptionId: string = '';

    componentDidMount() {
        this.subscriptionId = messageStore.subscribe(this.props.roomId, (messages) => {
            this.setState({messages: messages});
        });
    }

    componentWillUnmount() {
        messageStore.unSubscribe(this.subscriptionId);
    }

    protected handleChange(ev: ChangeEvent<HTMLInputElement>) {
        this.setState({newMessageText: ev.target.value});
    };

    protected handleSubmit() {
        messageStore.postMessage({roomId: this.props.roomId, body: this.state.newMessageText});
        this.setState({newMessageText: ''});
    };

    render() {
        return (
            <div>
                <div>
                    Room {this.props.roomId}
                </div>
                <div>
                    {this.state.messages.map((message: Message) => <div key={message.id}>{message.body}</div>)}
                </div>
                <div>
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
