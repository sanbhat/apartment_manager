import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';


import { signUpStyle } from './signup';
import { APP_BASE_URL } from '../../config/config';
import { clone, isEmpty } from 'lodash';



class Profile extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email : '',
            password : '',
            name : '',
            countryCode : 0,
            phone: 0,
            pictureUrl: '',
            errorMessage : '',
            message : ''
        };
        this.preview = this.preview.bind(this);
        this.updateUserData = this.updateUserData.bind(this);
    }

    componentWillMount() {
        console.log(this.props.match.params.user);
        this.fetchUserData(this.props.match.params.user);
    }

    render() {
        console.log('render - edit profile');
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <strong>Edit Profile</strong>
                            </div>
                            <div className="panel-body">
                                <form className="form-horizontal" onSubmit={this.updateUserData}>
                                    <div className="form-group">
                                        <label htmlFor="emailLabel" className="col-sm-3 control-label">
                                            Email</label>
                                        <div className="col-sm-9">
                                            <input type="email" className="form-control" id="emailLabel" readOnly
                                                placeholder="Email" required="true" value={this.state.email} onChange={(event) => this.setState({ email: event.target.value })} />
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="fullNameLabel" className="col-sm-3 control-label">
                                            Full Name</label>
                                        <div className="col-sm-9">
                                            <input type="text" className="form-control" id="fullNameLabel"
                                                placeholder="Full Name" required="true" value={this.state.name} onChange={(event) => this.setState({ name: event.target.value })} />
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="codeLabel" className="col-sm-3 control-label">
                                            Country Code</label>
                                        <div className="col-sm-9">
                                            <input type="number" className="form-control" id="codeLabel" size="3" min="1" max="999"
                                                placeholder="For example - +91" required="true" value={this.state.countryCode} onChange={(event) => this.setState({ countryCode: event.target.value })} />
                                        </div>
                                        <label htmlFor="phoneLabel" className="col-sm-3 control-label">
                                            Phone</label>
                                        <div className="col-sm-9">
                                            <input type="text" pattern="\d*" className="form-control" id="phoneLabel" size="10" minLength="10" maxLength="10"
                                                placeholder="Phone" required="true" value={this.state.phone} onChange={(event) => this.setState({ phone: event.target.value })} />
                                        </div>
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="profilePicLabel" className="col-sm-3 control-label">
                                            Profile picture</label>
                                        <div className="col-sm-7">
                                            <input type="text" className="form-control" placeholder="Picture URL" id="profilePicLabel" 
                                                value={this.state.pictureUrl ? this.state.pictureUrl : '' } onChange={(event) => this.setState({ pictureUrl: event.target.value })} />
                                        </div>
                                        <div className="col-sm-2">
                                            <button type="button" className="btn btn-success btn-sm" onClick={(event) => this.preview(event.target.value)}>
                                                Preview</button>
                                        </div>
                                    </div>

                                    <div className="form-group last">
                                        <div className="col-sm-offset-3 col-sm-9">
                                            <button type="submit" className="btn btn-success btn-sm">
                                                Update Profile</button>
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
                        </div>
                    </div>
                    <div className="col-xs-6 col-md-3">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <strong>Profile picture Preview</strong>
                            </div>
                            <div className="panel-body">
                                <a href="#" className="thumbnail">
                                    <img id="profilePicPreview" src="//:0" style={signUpStyle} alt="..." />
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    preview(url) {
        document.getElementById('profilePicPreview').src = this.state.pictureUrl;
    }

    fetchUserData(userEmailId) {
        axios.get(APP_BASE_URL + 'users/email?email=' + userEmailId)
        .then(res => {
            let data = res.data;
            this.setState({
                name : data.name,
                email : data.email,
                phone: data.phone,
                countryCode: data.countryCode,
                pictureUrl: data.pictureUrl
            })
            if(!isEmpty(this.state.pictureUrl)) {
                this.preview(this.state.pictureUrl)
            }
        })
        .catch(err => {
            console.error(err);
        })
    }

    updateUserData(event) {
        event.preventDefault();
        let data = clone(this.state);
        axios.put(APP_BASE_URL + 'users/email', data)
        .then(res => {
            if(!isEmpty(res.data.errorMessage)) {
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

export default Profile;
