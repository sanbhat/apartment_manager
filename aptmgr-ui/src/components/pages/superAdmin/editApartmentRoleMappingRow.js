import React, {Component} from 'react';
import Select from 'react-select';
import axios from 'axios';
import { isEmpty } from 'lodash';
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
            roleUser : ''
        }
    }

    render() {
        return (
            <tr>
                <td style={colStyle}><strong>{this.props.data.role.roleName}</strong></td>
                <td style={colStyle}>{this.props.data.role.description}</td>
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
                        labelKey="displayValue"
                        matchPos="any" matchProp="any"
                        value={this.state.roleUser} onChange={(value) => this.setState({ roleUser: value })}
                        loadOptions={this.fetchUserAsync.bind(this)}
                        backspaceRemoves={this.state.backspaceRemoves} /> 
                </td>
            </tr>
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
}
export default EditApartmentRoleMappingRow;