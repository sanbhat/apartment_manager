import React, { Component } from 'react';
import { connect } from 'react-redux';
import jwtDecode from 'jwt-decode';

import {APP_TOKEN_KEY} from '../../config/config';
import {LoginStyles as styles} from '../../styles/loginStyles';
import ApiService from  '../../services/apiService';
import setCurrentUser from '../../actions/authActions';

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import TextField from 'material-ui/TextField'
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import Checkbox from 'material-ui/Checkbox';


class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            rememberMe: false
        }
        this.login = this.login.bind(this);
        this.reset = this.reset.bind(this);
    }

    render() {
        return (
            <MuiThemeProvider>
                <div>
                    <h3>Apartment Manager - Login</h3>
                    <TextField
                        id="email"
                        hintText="enter email"
                        floatingLabelText="Login Id"
                        value={this.state.email}
                        onChange={event => {this.setState({ email : event.target.value})}}
                    />
                    <br></br>
                    <TextField
                        id="password"
                        hintText="Password"
                        floatingLabelText="Password"
                        type="password"
                        value={this.state.password}
                        onChange={event => {this.setState({ password : event.target.value})}}
                    />
                    <br></br>
                    <Checkbox
                        id="rememberMe"
                        label="Remember me"
                        checked={this.state.rememberMe}
                        onCheck={event => {this.setState({ rememberMe : event.target.value})}}
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

    login = (event) => {
        event.preventDefault();
        ApiService.login(this.state)
        .then(res => {
            const token = res.data.token;
            localStorage.setItem(APP_TOKEN_KEY, token);
            if (token) {
                this.props.setCurrentUser(jwtDecode(token));
            } else {
                this.props.setCurrentUser({});
            }
            ApiService.setAuthenticationToken(token);
        })
    }

    reset = (event) => {
       this.setState({
            email : '',
            password: '',
            rememberMe: false
        });
    }
}

    
const mapDispatchToProps = (dispatch) => {
    return {
      setCurrentUser: (user) => dispatch(setCurrentUser(user))
    };
  }
  

export default connect(null, mapDispatchToProps)(Login);
