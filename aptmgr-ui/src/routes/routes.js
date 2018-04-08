import React, { Component } from 'react';
import { Route, Redirect } from 'react-router-dom';

import Profile from '../components/pages/profile';
import Home from '../components/pages/home';
import Incidents from '../components/pages/incidents';
import Residents from '../components/pages/residents';
import Login from '../components/pages/login';
import Signup from '../components/pages/signup';

//Super Admin pages
import ApartmentMgmt from '../components/pages/superAdmin/apartmentMgmt';
import RegisterApartment from '../components/pages/superAdmin/registerApartment';
import EditApartment from '../components/pages/superAdmin/editApartment';

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
                <Route path={"/login"} component={Login} />
                <Route path={"/signup"} component={Signup} />
                <PrivateRoute path={"/profile/:user"} component={Profile} />
                <PrivateRoute path={"/home"} component={Home} />
                <PrivateRoute path={"/incidents"} component={Incidents} />
                <PrivateRoute path={"/residents"} component={Residents} />
                <PrivateRoute path={"/register-apt"} component={RegisterApartment} />
					      <PrivateRoute path={"/apt-management"} component={ApartmentMgmt} />
                <PrivateRoute path={"/edit-apartment/:aptId"} component={EditApartment} />
					      <PrivateRoute path={"/saas-admin"} component={Home} />
            </div>
        );
    }
}

export default AptMgrRoutes;