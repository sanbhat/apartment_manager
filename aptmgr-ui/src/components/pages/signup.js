import React, {Component} from 'react';
import { Link } from 'react-router-dom';

import axios from 'axios';
import {APP_BASE_URL} from '../../config/config';


class Signup extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email : '',
            password : '',
            name : '',
            countryCode : 0,
            phone: 0,
            errorMessage : '',
            message : ''
        };
        this.signup = this.signup.bind(this);
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <strong>Sign up ( Its free! )</strong>
                            </div>
                            <div className="panel-body">
                                <form className="form-horizontal">
                                    <div className="form-group">
                                        <label htmlFor="emailLabel" className="col-sm-3 control-label">
                                            Email</label>
                                        <div className="col-sm-9">
                                            <input type="email" className="form-control" id="emailLabel" 
                                            placeholder="Email" required="true" onChange= { (event) => this.setState({ email : event.target.value})}/>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="fullNameLabel" className="col-sm-3 control-label">
                                            Full Name</label>
                                        <div className="col-sm-9">
                                            <input type="text" className="form-control" id="fullNameLabel" 
                                            placeholder="Full Name" required="true" onChange= { (event) => this.setState({ name : event.target.value})}/>
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
                                        <label htmlFor="codeLabel" className="col-sm-3 control-label">
                                            Country Code</label>
                                        <div className="col-sm-9">
                                            <input type="number" className="form-control" id="codeLabel" size="3" minLength="1"  maxLength="3"
                                            placeholder="For example - +91" required="true" onChange = { (event) => this.setState({ countryCode: event.target.value})}/>
                                        </div>
                                        <label htmlFor="phoneLabel" className="col-sm-3 control-label">
                                            Phone</label>
                                        <div className="col-sm-9">
                                            <input type="number" className="form-control" id="phoneLabel" size="10" minLength="10" maxLength="10"
                                            placeholder="Phone" required="true" onChange = { (event) => this.setState({ phone: event.target.value})}/>
                                        </div>
                                    </div>

                                    { this.state.errorMessage !== '' && 
                                         <div className="alert alert-danger" role="alert">{this.state.errorMessage}</div>
                                    }
                                    { this.state.message !== '' && 
                                         <div className="alert alert-success" role="alert">{this.state.message}</div>
                                    }

                                    <div className="form-group last">
                                        <div className="col-sm-offset-3 col-sm-9">
                                            <button type="submit" className="btn btn-success btn-sm" onClick={ (event) => this.signup(event)}>
                                                Sign Up</button>
                                                <button type="reset" className="btn btn-default btn-sm">
                                                Reset</button>
                                        </div>
                                    </div>
                                   
                                </form>
                            </div>
                            <div className="panel-footer">
                                Already registered? <Link to="/login">Sign in</Link>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>            

        )
    }

    signup(event) {
        let data = this.state;
        event.preventDefault();
        axios.post(APP_BASE_URL + 'api/auth/signup', data)
        .then(res => {
            if(res.data.errorMessage !== '') {
                this.setState({ 
                    errorMessage : res.data.errorMessage,
                    message : ''
                });
            } else {
                this.setState( {
                    errorMessage : '',
                    message : res.data.message
                });
            }
        })
        .catch(err => {
            this.setState({ 
                errorMessage : err.message,
                message : ''
            });
        })
    }
}

export default Signup;