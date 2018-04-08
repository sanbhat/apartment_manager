import React, {Component} from 'react';
import Select from 'react-select';
import axios from 'axios';
import { isEmpty } from 'lodash';
import { APP_BASE_URL } from '../../../config/config';

import 'react-select/dist/react-select.css';

const suggestBoxStyle = {
    position : 'relative',
    paddingRight : '15px',
    paddingLeft : '15px'
}

class RegisterApartment extends Component {

    constructor(args) {
        super(args);
        this.state = {
            backspaceRemoves : true,
            multi : false,
            required : true,
            aptName : '',
            primaryEmail : '',
            address : '',
            pinCode : '',
            city : '',
            message : '',
            errorMessage : ''
        }
        this.registerApt = this.registerApt.bind(this);
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <strong>Register a New Apartment</strong>
                            </div>
                            <div className="panel-body">
                                <form className="form-horizontal" onSubmit={this.registerApt}>
                                    <div className="form-group">
                                        <label htmlFor="apartmentNameLabel" className="col-sm-3 control-label">
                                            Apartment Name</label>
                                        <div className="col-sm-9">
                                            <input type="text" className="form-control" id="apartmentNameLabel" 
                                            placeholder="Apartment Name" autoComplete="off" required="true" onChange= { (event) => this.setState({ aptName : event.target.value})}/>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="primaryEmailLabel" className="col-sm-3 control-label">
                                            Primary Email</label>
                                            <Select.Async id="primaryEmailLabel" 
                                                          type="email"
                                                          required={this.state.required}
                                                          autoComplete="off"
                                                          multi={this.state.multi} 
                                                          placeholder="Find users..."
                                                          searchPromptText="Type at least 3 characters to start searching"
                                                          menuContainerStyle = {suggestBoxStyle}
                                                          valueKey="email"
                                                          labelKey="displayValue"
                                                          className="col-sm-9" 
                                                          matchPos="any" matchProp="any"
                                                          value={this.state.primaryEmail} onChange={ (value) => this.setState( { primaryEmail : value })}
                                                          loadOptions={this.fetchUserAsync.bind(this)} 
                                                          backspaceRemoves={this.state.backspaceRemoves} /> 
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="addressLabel" className="col-sm-3 control-label">
                                            Address</label>
                                        <div className="col-sm-9">
                                            <textarea  className="form-control" id="addressLabel" 
                                            placeholder="Address" required="true" onChange = { (event) => this.setState({ address: event.target.value})}/>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="pinCodeLabel" className="col-sm-3 control-label">
                                            Pin Code</label>
                                        <div className="col-sm-9">
                                            <input type="text" pattern="\d*" size="6" minLength="6" maxLength="6" className="form-control" id="pinCodeLabel"
                                            placeholder="example - 560001" required="true" onChange = { (event) => this.setState({ pinCode: event.target.value})}/>
                                        </div>
                                        <label htmlFor="cityLabel" className="col-sm-3 control-label">
                                            City</label>
                                        <div className="col-sm-9">
                                            <input type="text" className="form-control" id="cityLabel" 
                                                placeholder="City" required="true" onChange = { (event) => this.setState({ city: event.target.value})}/>
                                        </div>
                                    </div>

                                    <div className="form-group last">
                                        <div className="col-sm-offset-3 col-sm-9">
                                            <button type="submit" className="btn btn-success btn-sm col-sm-3">
                                                Register</button>
                                                <button type="reset" className="btn btn-default btn-sm">
                                                Reset</button>
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
                </div>
            </div>      
        )
    }

    fetchUserAsync(input) {
        if (isEmpty(input) || input.length <= 2) {
            return Promise.resolve({
                options: []
            })
        } else {
            return axios.get(APP_BASE_URL + 'users/search?query=' + input)
                .then(res => { return { options: res.data.payload } })
                .catch(err => { });
        }
    }

    registerApt(event) {
        event.preventDefault();
        if (this.state.primaryEmail.email) {
            let form = event.target;
            let data = {
                aptName: this.state.aptName,
                primaryEmail: this.state.primaryEmail.email,
                address: this.state.address,
                pinCode: this.state.pinCode,
                city: this.state.city
            }
            axios.post(APP_BASE_URL + 'apartment/save', data)
                .then(res => {
                    if (!isEmpty(res.data.errorMessage)) {
                        this.setState({
                            errorMessage: res.data.errorMessage,
                            message: ''
                        });
                    } else {
                        this.setState({
                            errorMessage: '',
                            primaryEmail : '',
                            message: res.data.message
                        });
                        form.reset();
                    }
                })
                .catch(err => {
                    this.setState({
                        errorMessage: err.message,
                        message: ''
                    });
                })
        } else {
            this.setState({
                errorMessage : 'Please select a valid Primary contact for the apartment'
            })
        }
    }
}

export default RegisterApartment;