import React, { Component } from 'react';

import {LoginStyles as styles} from '../../styles/login-styles';
import ApiService from  '../services/api-service';

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import Checkbox from 'material-ui/Checkbox';


class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            loginId: '',
            password: '',
            rememberMe: false
        }
        this.updateValues = this.updateValues.bind(this);
        this.login = this.login.bind(this);
        this.reset = this.reset.bind(this);
    }

    render() {
        return (
            <MuiThemeProvider>
                <div>
                    <h3>Apartment Manager - Login</h3>
                    <TextField
                        id="loginTextField"
                        hintText="enter email"
                        floatingLabelText="Login Id"
                        value={this.state.loginId}
                        onChange={this.updateValues}
                    />
                    <br></br>
                    <TextField
                        id="passwordTextField"
                        hintText="Password"
                        floatingLabelText="Password"
                        type="password"
                        value={this.state.password}
                        onChange={this.updateValues}
                    />
                    <br></br>
                    <Checkbox
                        id="rememberMeCheckbox"
                        label="Remember me"
                        checked={this.state.rememberMe}
                        onCheck={this.updateValues}
                        style={styles.checkbox}
                    />
                    
                    <RaisedButton label="Login" primary={true} style={styles.raisedButton} onClick={this.login} />
                    <RaisedButton label="Reset" secondary={true} style={styles.raisedButton} onClick={this.reset} />
                    <br />
                    <FlatButton label="Forgot Password" primary={true}  style={styles.flatButton}/>
                </div>
            </MuiThemeProvider>
        );
    }

    login(event) {
        console.log('Login button clicked! LoginId', this.state);
        ApiService.makePostCall('/login', this.state)
        .then(res => res.json())
        .then(item => console.log(item));
    }

    reset(event) {
       this.setState({
            loginId: '',
            password: '',
            rememberMe: false
        });
    }

    updateValues(event) {
        switch (event.target.id) {
            case 'loginTextField':
                this.setState({
                    loginId: event.target.value
                });
                break;
            case 'passwordTextField':
                this.setState({
                    password: event.target.value
                });
                break;
            case 'rememberMeCheckbox':
                let newChecked = !this.state.rememberMe;
                this.setState({
                    rememberMe: newChecked
                });
                break;
        }
    }
}

export default Login;
