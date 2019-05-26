import {Selector} from '../../utils';
import {createSelector} from 'reselect';
import {IStateChat} from './types';

const selectChat = Selector.getRootBranch('chat');

export const selectMessages = createSelector(
    // @ts-ignore
    selectChat,
    (state: IStateChat) => Array.from(state.messages.values()).sort((a, b) =>  a.timestamp.getTime() - b.timestamp.getTime()),
);
