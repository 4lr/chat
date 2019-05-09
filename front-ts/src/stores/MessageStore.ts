import uuidv4 from 'uuid/v4';
import {MessageControllerApi, NewMessageTO} from "../api";
import {basePath} from "../config";

const userId = '00000000-0000-0000-C000-000000000777';
const timeout = 2000;

const messageController = new MessageControllerApi({basePath: basePath});

class MessageStore {

    protected subscribers: Map<string, Map<string, (messages: Message[]) => void>> = new Map();

    //protected store: Map<string, Message[]> = new Map();

    constructor() {
        this.fetchMessages = this.fetchMessages.bind(this);
        setTimeout(this.fetchMessages, timeout);
    }

    public postMessage(newMessage: { roomId: string; body: string; }) {
        const newMessageTO: NewMessageTO = {
            id: uuidv4(),
            userId: userId,
            roomId: newMessage.roomId,
            body: newMessage.body,
        };

        /*const messages = this.store.has(newMessageTO.roomId) ? this.store.get(newMessageTO.roomId) : [];
        // @ts-ignore
        messages.push({
            ...newMessageTO,
            timestamp: new Date()
        });
        // @ts-ignore
        this.store.set(newMessageTO.roomId, messages);*/

        try {
            messageController.postMessageUsingPOST(newMessageTO);
        } catch (e) {
            console.error(e);
        }
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
        this.subscribers.forEach(async (roomSubscribers, roomId) => {

            //const messages = this.store.get(roomId);

            try {
                const messageTOs = (await messageController.getMessagesByRoomIdUsingGET(roomId)).data;
                const messages = messageTOs.map((messageTO) => {
                    return {
                        ...messageTO,
                        timestamp: new Date(messageTO.timestamp)
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

export interface Message {
    id: string;
    userId: string;
    roomId: string;
    body: string;
    timestamp: Date;
}
