import { SET_CURRENT_USER } from '../actions/actionTypes';
import { isEmpty } from '../utils/utils';

const initialState = {
    isAuthenticated : false,
    user: {}
}

const auth = (state = initialState, action = {}) => {
    switch(action.type) {
        case SET_CURRENT_USER:
            console.log('SET_CURRENT_USER reducer', action.user, !isEmpty(action.user));
            return Object.assign({}, state, {
                isAuthenticated: !isEmpty(action.user),
                user: action.user
            });
        default:
            return state;
    }
}

export default auth;