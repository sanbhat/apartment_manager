import React, { Component } from 'react';
import { Route, Redirect } from 'react-router-dom';

import Home from '../components/pages/home';
import Incidents from '../components/pages/incidents';
import Residents from '../components/pages/residents';
import Login from '../components/pages/login';
import {APP_TOKEN_KEY} from '../config/config';

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      isAuthenticated() ? (
        <Component {...props} />
      ) : (
        <Redirect
          to={{
            pathname: "/login",
            state: { from: props.location }
          }}
        />
      )
    }
  />
);

const isAuthenticated = () => {
    let token = localStorage.getItem(APP_TOKEN_KEY);
    return token !== null;
}

class AptMgrRoutes extends Component {

    render() {
        return (
            <div className="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <Route path="/login" component={Login} />
                <PrivateRoute path="/home" component={Home} />
                <PrivateRoute path="/incidents" component={Incidents} />
                <PrivateRoute path="/residents" component={Residents} />
            </div>
        );
    }
}

export default AptMgrRoutes;