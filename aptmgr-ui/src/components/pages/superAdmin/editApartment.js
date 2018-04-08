import React, {Component} from 'react';
import axios from 'axios';

import { APP_BASE_URL } from '../../../config/config';
import EditApartmentRoleMappingRow from './editApartmentRoleMappingRow';

class EditApartment extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id : '',
            name : '',
            apartmentRoleMappings : [],
            errorMessage : ''
        }
    }

    componentWillMount() {
        this.fetchApartmentData(this.props.match.params.aptId);
    }

    render() {
        console.log(this.state.apartmentRoleMappings);
        let errorDiv =  (
            <div className="alert alert-error" role="alert">
                <strong>Oh Snap!</strong> {this.state.errorMessage}
            </div>
         );
        return (
            <div>
                 { this.errorMessage ? errorDiv : ''}
                <div className="page-header">
                    <h3> {this.state.name} <small>configuration</small></h3>
                </div>
                <div className="panel panel-default">
                    <div className="panel-heading">Configure Apartment Roles</div>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Role</th>
                                <th>Description</th>
                                <th>User</th>
                            </tr>
                        </thead>
                        <tbody>
                             { this.state.apartmentRoleMappings.map((roleMapping, i) => 
                                    <EditApartmentRoleMappingRow data={roleMapping} index={i+1} key={roleMapping.role.roleId} />)}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }

    fetchApartmentData(id) {
        axios.get(APP_BASE_URL + 'apartment/get/'+id)
        .then(res => {
            this.setState({
                name : res.data.name,
                id : res.data.id,
                apartmentRoleMappings : res.data.apartmentRoleMappings
            })
        })
        .catch(err => {
            this.setState({
                errorMessage : err.message
            });
        })
    }

}

export default EditApartment;