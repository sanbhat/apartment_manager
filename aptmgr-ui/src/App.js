import React, { Component } from 'react';
import {Route} from 'react-router-dom';

import Header from './components/headerComponent/header';
import AptMgrRoutes from './components/routes/routes';


class App extends Component {
  render() {
    return (

      <div className="App">

        <Header />
        <AptMgrRoutes />
      </div>
    );
  }
}

export default App;
