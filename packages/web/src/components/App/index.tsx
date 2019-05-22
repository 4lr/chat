import React from 'react';
import {Provider} from 'react-redux';
import configureStore from '../../store/configureStore';
import Router from '../Router';

const {store} = configureStore();

const App = () => {
    return <Provider store={store}><Router/></Provider>;
};

export default App;
