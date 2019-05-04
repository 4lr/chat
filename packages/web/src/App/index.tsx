import React from 'react';
import { Provider } from 'react-redux';
import configureStore from '../store/configureStore';

const { store } = configureStore();

const App = () => {
  return <Provider store={store} />;
};

export default App;
