import uuidv4 from 'uuid/v4';

const userId = '00000000-0000-0000-C000-000000000777';

class MessageStore {

    subscribers = new Map();
    store = new Map();
    interval = setInterval(this.fetchMessages.bind(this), 1000);

    postMessage(newMessage) {
        const postMessageTo = {
            id: uuidv4(),
            userId: userId,
            roomId: newMessage.roomId,
            body: newMessage.body,
        };
        const messages = this.store.has(postMessageTo.roomId) ? this.store.get(postMessageTo.roomId) : [];
        messages.push({
            ...postMessageTo,
            timestamp: new Date()
        });
        this.store.set(postMessageTo.roomId, messages);
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


        this.subscribers.forEach((roomSubscribers, roomId) => {
            const messages = this.store.get(roomId);
            roomSubscribers.forEach(callback => callback(Array.from(messages ? messages : [])));
        });
    };
}

export const messageStore = new MessageStore();
