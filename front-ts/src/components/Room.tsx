import React, {ChangeEvent, PureComponent} from 'react';
import uuidv4 from 'uuid/v4';
import MessageTo from "../api/MessageTo";

export interface Props {
    roomId: string
}

export interface State {
    newMessageText: string,
    messages: MessageTo[]
}

const handleChange = Symbol();
const handleSubmit = Symbol();

export default class Room extends PureComponent<Props, State> {

    state = {
        newMessageText: '',
        messages: []
    };

    [handleChange] = (ev: ChangeEvent<HTMLInputElement>) => {
        this.setState({newMessageText: ev.target.value});
    };

    [handleSubmit] = () => {
        const messages: MessageTo[] = this.state.messages;
        const newMessage: MessageTo = {id: uuidv4(), body: this.state.newMessageText};
        messages.push(newMessage);
        this.setState({newMessageText: '', messages: messages});
    };

    render() {
        return (
            <div>
                <div>
                    Room {this.props.roomId}
                </div>
                <div>
                    {this.state.messages.map((message: MessageTo) => <div key={message.id}>{message.body}</div>)}
                </div>
                <div>
                    <input
                        onChange={this[handleChange]}
                        value={this.state.newMessageText}
                    />
                    <button onClick={this[handleSubmit]}>Send</button>
                </div>
            </div>
        );
    }
}
