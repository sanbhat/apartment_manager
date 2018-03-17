import React from 'react';
import ReactDOM from 'react-dom';
import jwtDecode from 'jwt-decode';

import App from './App';
import store from './store/store';
import registerServiceWorker from './registerServiceWorker';
import {setAuthenticationToken, setDefaultRequestHeaders} from './utils/utils';
import {APP_TOKEN_KEY} from './config/config';
import {setCurrentUser} from './actions/authActions';

setDefaultRequestHeaders();
setAuthenticationToken(localStorage.getItem(APP_TOKEN_KEY));
store.dispatch(setCurrentUser(
    localStorage.getItem(APP_TOKEN_KEY) ? jwtDecode(localStorage.getItem(APP_TOKEN_KEY)) : {}));

ReactDOM.render(
( <App />), document.getElementById('root'));
registerServiceWorker();
