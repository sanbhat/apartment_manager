import React, { Component } from 'react';
import {Redirect} from 'react-router';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import jwtDecode from 'jwt-decode';
import axios from 'axios';

import {APP_TOKEN_KEY, APP_BASE_URL} from '../../config/config';
import {setCurrentUser} from '../../actions/authActions';
import {setAuthenticationToken} from '../../utils/utils';



class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            rememberMe: false,
            errorMessage: ''
        }
        this.login = this.login.bind(this);
    }

    render() {

        const isAuthenticated = this.props.isAuthenticated;
        console.log('login - isAuth', isAuthenticated);

        if(isAuthenticated) {
            return <Redirect to='/home' />
        }

        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <strong>Login</strong>
                            </div>
                            <div className="panel-body">
                                <form className="form-horizontal" role="form">
                                    <div className="form-group">
                                        <label htmlFor="emailLabel" className="col-sm-3 control-label">
                                            Email</label>
                                        <div className="col-sm-9">
                                            <input type="email" className="form-control" id="emailLabel" 
                                            placeholder="Email" required="required" onChange= { (event) => this.setState({ email : event.target.value})}/>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="passwordLabel" className="col-sm-3 control-label">
                                            Password</label>
                                        <div className="col-sm-9">
                                            <input type="password" className="form-control" id="passwordLabel" 
                                            placeholder="Password" required="true" onChange = { (event) => this.setState({ password: event.target.value})}/>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <div className="col-sm-offset-3 col-sm-9">
                                            <div className="checkbox">
                                                <label>
                                                    <input type="checkbox" onChange={ (event) => this.setState({ rememberMe : event.target.value === "on" ? true : false})}/>
                                                    Remember me
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="form-group last">
                                        <div className="col-sm-offset-3 col-sm-9">
                                            <button type="submit" className="btn btn-success btn-sm" onClick={ (event) => this.login(event)}>
                                                Sign in</button>
                                                <button type="reset" className="btn btn-default btn-sm">
                                                Reset</button>
                                        </div>
                                    </div>
                                    { this.state.errorMessage !== '' && 
                                         <div class="alert alert-danger" role="alert">{this.state.errorMessage}</div>
                                    }
                                   
                                </form>
                            </div>
                            <div className="panel-footer">
                                Not Registered? <Link to="/signup">Register here</Link>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>

        );
    }

    login = (event) => {
        this.state.errorMessage = '';
        let data = this.state;
        axios.post(APP_BASE_URL + 'api/auth/login', data)
        .then(res => {
            const token = res.data.token;
            localStorage.setItem(APP_TOKEN_KEY, token);
            if (token) {
                this.props.setCurrentUser(jwtDecode(token));
            } else {
                this.props.setCurrentUser({});
            }
            setAuthenticationToken(token);
        })
        .catch(err => {
            if(err.response.data.message) {
                this.setState({errorMessage : err.response.data.message});
            } else {
                this.setState({errorMessage : err.message});
            }
        })
    }
}

const mapStateToProps = (state) => {
	return {
		isAuthenticated : state.auth.isAuthenticated
	}
};

    
const mapDispatchToProps = (dispatch) => {
    return {
      setCurrentUser: (user) => dispatch(setCurrentUser(user))
    };
}
  

export default connect(mapStateToProps, mapDispatchToProps)(Login);
