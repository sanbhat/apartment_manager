import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class ApartmentTableRow extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td>{this.props.index}</td> 
                <td>{this.props.apartment.aptName}</td>
                <td>{this.props.apartment.primaryEmail}</td>
                <td>{this.props.apartment.address}</td>
                <td>{this.props.apartment.pinCode}</td>
                <td>{this.props.apartment.city}</td>
                <td> 
                    <div className="btn-group" aria-label="...">
                        <Link title="Configure Apartment" to={"/edit-apartment/" + this.props.apartment.id} className="btn btn-primary"><span className="glyphicon glyphicon-cog"></span></Link>
                        <Link title="Delete Apartment" to="/delete-aprtment" className="btn btn-danger"><span className="glyphicon glyphicon-trash"></span></Link>
                        <Link title="Contact Apartment Admins" to="/contact-aprtment" className="btn btn-warning"><span className="glyphicon glyphicon-envelope"></span></Link>
                    </div>
                </td>
            </tr>
        )
    }
}

export default ApartmentTableRow;