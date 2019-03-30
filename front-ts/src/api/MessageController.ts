import axios from 'axios';
import * as querystring from "querystring";

const path = '/api/message';

class MessageController {

    public async postMessage(postMessageTo: PostMessageTo) : Promise<MessageTo> {
        const result = await axios.post<MessageTo>(path, postMessageTo);
        return result.data;
    };

    public async getMessages(roomId: string) : Promise<MessageTo[]> {
        const result = await axios.get<MessageTo[]>(path + '?' + querystring.stringify({roomId: roomId}));
        return result.data;
    };
}

export const messageController = new MessageController();

export interface MessageTo {
    id: string;
    userId: string;
    roomId: string;
    body: string;
    timestamp: string;
}

export interface PostMessageTo {
    id: string,
    userId: string,
    roomId: string,
    body: string
}
