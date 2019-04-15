import uuidv4 from 'uuid/v4';
import {messageController} from "../api/MessageController";

const userId = '00000000-0000-0000-C000-000000000777';
const timeout = 2000;

class MessageStore {

    subscribers = new Map();

    //store = new Map();

    constructor() {
        this.fetchMessages = this.fetchMessages.bind(this);
        setTimeout(this.fetchMessages, timeout);
    }

    postMessage(newMessage) {
        const postMessageTo = {
            id: uuidv4(),
            userId: userId,
            roomId: newMessage.roomId,
            body: newMessage.body,
        };
        /*const messages = this.store.has(postMessageTo.roomId) ? this.store.get(postMessageTo.roomId) : [];
        messages.push({
            ...postMessageTo,
            timestamp: new Date()
        });
        this.store.set(postMessageTo.roomId, messages);*/

        try {
            messageController.postMessage(postMessageTo);
        } catch (e) {
            console.error(e);
        }
    };

    subscribe(roomId, callback) {
        const subscriptionId = uuidv4();
        const roomSubscribers = this.subscribers.has(roomId) ? this.subscribers.get(roomId) : new Map();
        roomSubscribers.set(subscriptionId, callback);
        this.subscribers.set(roomId, roomSubscribers);
        return subscriptionId;
    };

    unSubscribe(subscriptionId) {
        this.subscribers.forEach(roomSubscribers => roomSubscribers.delete(subscriptionId));
    };

    fetchMessages() {
        this.subscribers.forEach(async (roomSubscribers, roomId) => {

            //const messages = this.store.get(roomId);

            try {
                const messageTos = await messageController.getMessages(roomId);
                const messages = messageTos.map((messageTo) => {
                    return {
                        ...messageTo,
                        timestamp: new Date(messageTo.timestamp)
                    };
                });
                roomSubscribers.forEach(callback => callback(Array.from(messages ? messages : [])));
            } catch (e) {
                console.error(e);
            }
        });
        setTimeout(this.fetchMessages, timeout);
    };
}

export const messageStore = new MessageStore();
