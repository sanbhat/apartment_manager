import React, {Component} from 'react';
import Select from 'react-select';
import axios from 'axios';
import { isEmpty, cloneDeep } from 'lodash';
import { APP_BASE_URL } from '../../../config/config';

import 'react-select/dist/react-select.css';

const colStyle = {
    wordWrap: 'break-word',
    minWidth: '60px',
    maxWidth: '60px'
}

const suggestBoxStyle = {
    paddingRight : '15px',
    paddingLeft : '15px'
}

class EditApartmentRoleMappingRow extends Component {

    constructor(props) {
        super(props);
        this.state = {
            multi : false,
            backspaceRemoves : true,
            required : false,
            apartmentRoleMapping : {
                role : {},
                user : {
                    displayName : ''
                }
            }
        }
        this.setNewUser = this.setNewUser.bind(this);
    }

    //to load the page for the first time
    componentDidMount() {
        if(this.props.roleMappingModel) {
            this.setState({
                apartmentRoleMapping : this.props.roleMappingModel
            })
        }
    }

    //To handle the props state change
    componentWillReceiveProps(nextProps) {
        if(nextProps !== this.props) {
            this.setState({
                apartmentRoleMapping : nextProps.roleMappingModel
            })
        }
    }

    render() {
        return (
            <tr>
                <td style={colStyle}><strong>{this.state.apartmentRoleMapping.role.roleName}</strong></td>
                <td style={colStyle}>{this.state.apartmentRoleMapping.role.description}</td>
                <td style={colStyle}>
                    <Select.Async 
                        type="email"
                        required={this.state.required}
                        autoComplete="off"
                        multi={this.state.multi}
                        placeholder="Find users..."
                        searchPromptText="Type at least 3 characters to start searching"
                        menuContainerStyle={suggestBoxStyle}
                        valueKey="email"
                        labelKey="displayName"
                        matchPos="any" matchProp="any"
                        value={this.state.apartmentRoleMapping.user ? this.state.apartmentRoleMapping.user : {}} 
                        onChange={(value) => this.setNewUser(value)}
                        loadOptions={this.fetchUserAsync.bind(this)}
                        backspaceRemoves={this.state.backspaceRemoves} /> 
                </td>
            </tr>
        )
    }

    setNewUser(value) {
        let newValue = cloneDeep(this.state.apartmentRoleMapping);
        newValue.user = value
        this.props.handleChangeMethod("APT_ROLE_MAPPING", newValue);
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
}
export default EditApartmentRoleMappingRow;