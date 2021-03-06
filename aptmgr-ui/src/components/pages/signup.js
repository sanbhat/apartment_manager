import React, {Component} from 'react';
import { Link } from 'react-router-dom';
import { clone, isEmpty } from 'lodash';

import axios from 'axios';
import {APP_BASE_URL} from '../../config/config';
import profilePicDefault from '../../images/profile_default.jpg';


export const signUpStyle = {
    height: "180px", 
    width: "100%", 
    display: "block"
}

class Signup extends Component {


    constructor(props) {
        super(props);
        this.state = {
            email : '',
            password : '',
            name : '',
            countryCode : 0,
            phone: 0,
            pictureUrl : profilePicDefault,
            errorMessage : '',
            message : ''
        };
        this.signup = this.signup.bind(this);
        this.preview = this.preview.bind(this);
    }

    componentDidMount() {
        this.preview();
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
                                <form className="form-horizontal" onSubmit={this.signup}>
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
                                            <input type="number" className="form-control" id="codeLabel" size="3" min="1" max="999"
                                            placeholder="For example - +91" required="true" onChange = { (event) => this.setState({ countryCode: event.target.value})}/>
                                        </div>
                                        <label htmlFor="phoneLabel" className="col-sm-3 control-label">
                                            Phone</label>
                                        <div className="col-sm-9">
                                            <input type="text" pattern="\d*" className="form-control" id="phoneLabel" size="10" minLength="10" maxLength="10"
                                                placeholder="Phone" required="true" onChange = { (event) => this.setState({ phone: event.target.value})}/>
                                        </div>
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="profilePicLabel" className="col-sm-3 control-label">
                                            Profile picture</label>
                                        <div className="col-sm-7">
                                            <input type="text" className="form-control" placeholder="Picture URL" id="profilePicLabel" onChange= { (event) => this.setState({ pictureUrl : event.target.value})}/>
                                        </div>
                                        <div className="col-sm-2">
                                        <button type="button" className="btn btn-primary btn-sm" onClick={this.preview}>
                                                Preview</button>
                                        </div>
                                    </div>

                                    <div className="form-group last">
                                        <div className="col-sm-offset-3">
                                            <div className="col-sm-3">
                                                <button type="submit" id="signUpButton" className="btn btn-success btn-md">
                                                Sign Up</button>
                                            </div>
                                            <div className="col-sm-3">
                                                <button type="reset" id="resetButton" className="btn btn-default btn-md">
                                                Reset</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                {this.state.errorMessage !== '' &&
                                    <div className="alert alert-danger" role="alert">{this.state.errorMessage}</div>
                                }
                                {this.state.message !== '' &&
                                    <div className="alert alert-success" role="alert">{this.state.message}</div>
                                }
                            </div>
                            <div className="panel-footer">
                                Already registered? <Link to={"/login"}>Sign in</Link>
                            </div>
                            
                        </div>
                    </div>
                    <div className="col-xs-6 col-md-3">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <strong>Profile picture Preview</strong>
                            </div>
                            <div className="panel-body">
                                <a href="#" className="thumbnail">
                                    <img id="profilePicPreview" src="//:0"  style={signUpStyle} alt="..." />
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            

        )
    }

    signup(event) {
        let data = clone(this.state);
        let form = event.target;
        event.preventDefault();
        this.toggleButtons(false);

        if(data.pictureUrl === profilePicDefault) {
            data.pictureUrl = '';
        }

        axios.post(APP_BASE_URL + 'auth/signup', data)
        .then(res => {
            if(!isEmpty(res.data.errorMessage)) {
                this.setState({ 
                    errorMessage : res.data.errorMessage,
                    message : ''
                });
            } else {
                this.setState( {
                    errorMessage : '',
                    pictureUrl: '',
                    message : res.data.message
                });
                form.reset();
                this.preview();
            }
            this.toggleButtons(true);
        })
        .catch(err => {
            this.setState({ 
                errorMessage : err.message,
                message : ''
            });
            this.toggleButtons(true);
        })
    }

    toggleButtons(enable) {
        document.getElementById("signUpButton").enable = enable;
        document.getElementById("resetButton").enable = enable;
    }

    preview() {
        let pic = this.state.pictureUrl ? this.state.pictureUrl : profilePicDefault
        document.getElementById('profilePicPreview').src = pic;
    }

}

export default Signup;