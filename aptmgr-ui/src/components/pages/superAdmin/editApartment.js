import React, {Component} from 'react';
import axios from 'axios';
import _ from 'lodash';

import { APP_BASE_URL } from '../../../config/config';
import ApartmentAdminMgmt from './apartmentAdminMgmt';

class EditApartment extends Component {

    constructor(props) {
        super(props);
        this.state = {
            savedApartment : {},
            apartment: {
                id: '',
                aptName: '',
                apartmentRoleMappings: []
            },
            saveButtonDisabled : true,
            discardButtonDisabled : true,
            errorMessage: '',
            message: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.updateApartmentData = this.updateApartmentData.bind(this);
    }

    componentWillMount() {
        this.fetchApartmentData(this.props.match.params.aptId);
    }

    render() {
        let errorDiv =  (
            <div className="alert alert-danger" role="alert">
                <strong>Oh Snap!</strong> {this.state.errorMessage}
            </div>
         );
         let successDiv =  (
            <div className="alert alert-success" role="alert">
                <strong>Yay!</strong> {this.state.message}
            </div>
         );
        return (
            <div>
                { this.state.errorMessage ? errorDiv : ''}
                { this.state.message ? successDiv : ''}

                <div className="row">
                    <div className="col-sm-8">
                        <h3>{this.state.apartment.aptName} <small>configuration</small></h3>
                    </div>
                    <div className="col-sm-2">
                        <button className="btn btn-success btn-md" disabled={this.state.saveButtonDisabled} onClick={this.updateApartmentData}><span className="glyphicon glyphicon-ok"></span> Save Changes</button>
                    </div>
                    <div className="col-sm-2">
                        <button className="btn btn-danger btn-md" disabled={this.state.discardButtonDisabled}><span className="glyphicon glyphicon-remove"></span> Discard</button>
                    </div>
                </div>
                
                <div id="configTab">
                    <ul className="nav nav-pills">
                        <li className="active">
                            <a href="#admin" data-toggle="tab">Configure Admins</a>
                        </li>
                        <li><a href="#roleMgmt" data-toggle="tab">Assign Roles</a>
                        </li>
                        <li><a href="#unitMgmt" data-toggle="tab">Units Management</a>
                        </li>
                    </ul>

                    <div className="tab-content clearfix">
                        <div className="tab-pane active" id="admin">
                            <br></br>
                            <ApartmentAdminMgmt apartmentModel={this.state.apartment} handleChangeMethod={this.handleChange}/>
                        </div>
                        <div className="tab-pane" id="roleMgmt">
                            <br></br>
                            <div className="panel panel-default">
                                <div className="panel-heading">Configure Apartment Roles</div>
                            </div>
                        </div>
                        <div className="tab-pane" id="unitMgmt">
                            <br></br>
                            <div className="panel panel-default">
                                <div className="panel-heading">Manage Units</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    handleChange(newValue) {
       if(!_.isEqual(this.state.apartment, newValue)) {
            this.setState({
                errorMessage : '',
                message : '',
                saveButtonDisabled : false,
                discardButtonDisabled : false,
                apartment : newValue
            })
       }
    }

    fetchApartmentData(id) {
        axios.get(APP_BASE_URL + 'apartment/get/'+id)
        .then(res => {
            this.setState({
                message : '',
                errorMessage : '',
                savedApartment : res.data,
                apartment : res.data
            })
        })
        .catch(err => {
            this.setState({
                errorMessage : err.message
            });
        })
    }

    updateApartmentData() {
        let data = this.state.apartment;
        let id = this.state.apartment.id;
        axios.put(APP_BASE_URL + 'apartment/update', data)
        .then(res => {
            this.setState({
                errorMessage : res.data.errorMessage,
                message : res.data.message,
                saveButtonDisabled : res.data.errorMessage ? false : true,
                discardButtonDisabled : res.data.errorMessage ? false : true
            })
        })
        .catch(err => {
            this.setState({
                errorMessage : err.message
            });
            console.error(err);
        })
    }

}

export default EditApartment;