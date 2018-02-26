import React from 'react';
import AptMgrConstants from  '../config/config';

class ApiService extends React.Component {


    static makePostCall(uri, requestBody) {
       return fetch(AptMgrConstants.baseUrl + uri, {
            method: 'POST',
            headers: {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(requestBody)
        });
    }

}

export default ApiService;