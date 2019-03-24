import React, {ChangeEvent, PureComponent} from 'react';
import uuidv4 from 'uuid/v4';

type Message = {
    id: string,
    body: string
};

export type Props = {
    roomId: string
};

export type State = {
    newMessageText: string,
    messages: Message[]
};

const handleChange = Symbol();
const handleSubmit = Symbol();

class Room extends PureComponent<Props, State> {

    state = {
        newMessageText: '',
        messages: []
    };

    [handleChange] = (ev: ChangeEvent<HTMLInputElement>) => {
        this.setState({newMessageText: ev.target.value});
    };

    [handleSubmit] = () => {
        const messages: Message[] = this.state.messages;
        const newMessage: Message = {id: uuidv4(), body: this.state.newMessageText};
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
                    {this.state.messages.map((message: Message) => <div key={message.id}>{message.body}</div>) }
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

export default Room;
