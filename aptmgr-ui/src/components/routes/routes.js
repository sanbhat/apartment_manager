import React, { Component } from 'react';
import { Route, Redirect } from 'react-router-dom';

import Home from '../pages/home';
import Incidents from '../pages/incidents';
import Residents from '../pages/residents';
import Login from '../pages/login';

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
    return true;
}

class AptMgrRoutes extends Component {

    render() {
        return (
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <Route path="/login" component={Login} />
                <PrivateRoute path="/home" component={Home} />
                <PrivateRoute path="/incidents" component={Incidents} />
                <PrivateRoute path="/residents" component={Residents} />
            </div>
        );
    }
}

export default AptMgrRoutes;