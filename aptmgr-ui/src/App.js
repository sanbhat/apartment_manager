import React, { Component } from 'react';
import { BrowserRouter } from 'react-router-dom';

import Header from './components/headerComponent/header';
import AptMgrRoutes from './components/routes/routes';


class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">

          <Header />
          <AptMgrRoutes />
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
