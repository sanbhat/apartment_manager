import React from 'react';
import axios from 'axios';

import {APP_BASE_URL} from '../config/config';

class ApiService extends React.Component {

    static login(requestBody) {
        return axios.post(
            APP_BASE_URL + 'api/auth/login',
            JSON.stringify(requestBody),
            {headers : {
                    'Content-Type': 'application/json',
                    'Accept' : 'application/json'
            }}
        );
    }

    static setAuthenticationToken(token) {
        if (token) {
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
        } else {
            delete axios.defaults.headers.common['Authorization'];
        }
    }

}

export default ApiService;