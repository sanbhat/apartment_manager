import { SET_CURRENT_USER } from './actionTypes';

export const setCurrentUser = (user) => {
    return {
        type: SET_CURRENT_USER,
        user
    }
}
