import React, { Component } from 'react';
import { BrowserRouter } from 'react-router-dom';

import { Provider } from 'react-redux';

import Header from './components/headerComponent/header';
import AptMgrRoutes from './routes/routes';
import store from './store/store';


class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <BrowserRouter>
          <div className="App">
            <Header />
            <AptMgrRoutes />
          </div>
        </BrowserRouter>
      </Provider>
    );
  }
}

export default App;
