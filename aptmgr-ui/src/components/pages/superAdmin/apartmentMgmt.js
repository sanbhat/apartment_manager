import React,{ Component } from 'react';
import { Link } from 'react-router-dom';

import axios from 'axios';
import { isEmpty } from 'lodash';

import { APP_BASE_URL } from '../../../config/config';
import ApartmentTableRow from './apartmentRow';




class ApartmentMgmt extends Component {

    constructor(props) {
        super(props);
        this.state = {
            message : '',
            apartments : []
        }
    }

    componentWillMount() {
        this.fetchApartmentData();
    }

    render() {
        let warningDiv =  (
            <div className="alert alert-warning" role="alert">
                <strong>Oh Snap!</strong> {this.state.warningMessage}
            </div>
         );
        return (
            <div>
                { this.state.warningMessage ? warningDiv : ''}
               
                <div className="panel panel-default">
                    <div className="panel-heading">Manage Apartments</div>
                    <div className="panel-body">
                        <Link to={"/register-apt"} className="btn btn-primary"><span className="glyphicon glyphicon-plus"></span> Register New Apartment</Link>
                    </div>
                </div>
                <table className="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Apartment Name</th>
                                <th>Primary email</th>
                                <th>Address</th>
                                <th>Pin Code</th>
                                <th>City</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.apartments.map((apt, i) =>
                                <ApartmentTableRow apartment={apt} index={i + 1} key={apt.id} />)}
                        </tbody>
                    </table>
            </div>
        );
    }

    fetchApartmentData() {
        axios.get(APP_BASE_URL + 'apartment/all')
        .then(res => {
            this.setState( {
                apartments : res.data,
                warningMessage : isEmpty(res.data) ? 'No apartments have registered so far' : ''
            })
            console.log(res.data);
        })
        .catch(err => {
            console.error(err);
        })
    }

}

export default ApartmentMgmt;