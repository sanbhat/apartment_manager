import React, { Component } from 'react';
import {Route} from 'react-router-dom';

import Home from '../pages/home';
import Incidents from '../pages/incidents';
import Residents from '../pages/residents';

class AptMgrRoutes extends Component {

    render() {
        return (
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <Route exact path="/" component={Home} />
                <Route path="/incidents" component={Incidents} />
                <Route path="/residents" component={Residents} />
            </div>
        );
    }
}

export default AptMgrRoutes;