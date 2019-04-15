import axios from 'axios';
import * as querystring from "querystring";

const path = '/api/message';

class MessageController {

    async postMessage(postMessageTo) {
        const result = await axios.post(path, postMessageTo);
        return result.data;
    };

    async getMessages(roomId) {
        const result = await axios.get(path + '?' + querystring.stringify({roomId: roomId}));
        return result.data;
    };
}

export const messageController = new MessageController();
