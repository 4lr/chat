import axios from 'axios';
import * as querystring from "querystring";

const path = '/api/message';

class MessageController {

    async postMessage(newMessageTo) {
        const result = await axios.post(path, newMessageTo);
        return result.data;
    };

    async getMessagesByRoomId(roomId) {
        const result = await axios.get(path + '?' + querystring.stringify({roomId: roomId}));
        return result.data;
    };
}

export const messageController = new MessageController();
