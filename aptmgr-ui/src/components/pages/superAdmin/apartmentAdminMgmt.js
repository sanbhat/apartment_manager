import React,{Component} from 'react';
import EditApartmentRoleMappingRow from './editApartmentRoleMappingRow';
import { cloneDeep } from 'lodash';

class ApartmentAdminMgmt extends Component {

    constructor(props) {
        super(props);
        this.state = {
            apartment : {
                apartmentRoleMappings : []
            }
        }
        this.handleChange = this.handleChange.bind(this);
    }

    /**
     * Method to get updated props and set it to the statet
     * @param {} nextProps 
     */
    componentWillReceiveProps(nextProps) {
        if(nextProps !== this.props) {
            this.setState(
                {
                    apartment : nextProps.apartmentModel
                }
            )
        }
    }

    render() {
        return (
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
                        {this.state.apartment.apartmentRoleMappings.map((roleMapping, i) =>
                            <EditApartmentRoleMappingRow roleMappingModel={roleMapping} 
                                    handleChangeMethod={this.handleChange}
                                    index={i + 1} key={roleMapping.role.roleId} />)}
                    </tbody>
                </table>
            </div>
        )
    }

    handleChange(type, newValue) {
        if (type === "APT_ROLE_MAPPING") {
            let newApartmentValue = cloneDeep(this.state.apartment);
            newApartmentValue.apartmentRoleMappings.map((roleMapping) => {
                if (roleMapping.role.roleId === newValue.role.roleId) {
                    roleMapping.user = newValue.user;
                }
            });
            this.props.handleChangeMethod(newApartmentValue);
        }
    }
}

export default ApartmentAdminMgmt;