import axios from 'axios';


export const isEmpty = (obj) => {
    return Object.keys(obj).length === 0 && obj.constructor === Object;
}

export const setDefaultRequestHeaders = () => {
    axios.defaults.headers.common['Content-Type'] = 'application/json';
    axios.defaults.headers.common['Accept'] = 'application/json';
}

export const setAuthenticationToken = (token) => {
    if (token) {
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    } else {
        delete axios.defaults.headers.common['Authorization'];
    }
}