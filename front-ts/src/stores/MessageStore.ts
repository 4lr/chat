import uuidv4 from 'uuid/v4';

interface MessageTo {
    id: string;
    userId: string;
    roomId: string;
    body: string;
    timestamp: string;
}

interface PostMessageTo {
    id: string,
    userId: string,
    roomId: string,
    body: string
}

const userId = '00000000-0000-0000-C000-000000000777';

class MessageStore {

    protected subscribers: Map<string, Map<string, (messages: Message[]) => void>> = new Map();
    protected store: Map<string, Message[]> = new Map();
    protected interval = setInterval(this.fetchMessages.bind(this), 1000);

    public postMessage(newMessage: { roomId: string; body: string; }) {
        const postMessageTo: PostMessageTo = {
            id: uuidv4(),
            userId: userId,
            roomId: newMessage.roomId,
            body: newMessage.body,
        };
        const messages = this.store.has(postMessageTo.roomId) ? this.store.get(postMessageTo.roomId) : [];
        // @ts-ignore
        messages.push({
            ...postMessageTo,
            timestamp: new Date()
        });
        // @ts-ignore
        this.store.set(postMessageTo.roomId, messages);
    };

    public subscribe(roomId: string, callback: (messages: Message[]) => void): string {
        const subscriptionId = uuidv4();
        const roomSubscribers = this.subscribers.has(roomId) ? this.subscribers.get(roomId) : new Map();
        // @ts-ignore
        roomSubscribers.set(subscriptionId, callback);
        // @ts-ignore
        this.subscribers.set(roomId, roomSubscribers);
        return subscriptionId;
    };

    public unSubscribe(subscriptionId: string) {
        this.subscribers.forEach(roomSubscribers => roomSubscribers.delete(subscriptionId));
    };

    protected fetchMessages() {
        this.subscribers.forEach((roomSubscribers, roomId) => {
            const messages = this.store.get(roomId);
            roomSubscribers.forEach(callback => callback(Array.from(messages ? messages : [])));
        });
    };
}

export const messageStore = new MessageStore();

export interface Message {
    id: string;
    userId: string;
    roomId: string;
    body: string;
    timestamp: Date;
}
