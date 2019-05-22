import {Selector} from '../../utils';
import {createSelector} from 'reselect';
import {IStateChat} from './types';

const selectChat = Selector.getRootBranch('chat');

export const selectMessages = createSelector(
    // @ts-ignore
    selectChat,
    (state: IStateChat) => state.messages,
);
