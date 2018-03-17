import React, { Component } from 'react';
import {Redirect} from 'react-router';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import { setCurrentUser } from '../../actions/authActions';
import { APP_TOKEN_KEY } from '../../config/config';

class Header extends Component {

	constructor(props) {
		super(props);
	}
	
	render() {

			const {isAuthenticated, user} = this.props;

			const userLinks = (
				<ul className="nav navbar-nav navbar-right">
					<li><a href="#">Notifications</a></li>
					<li><a href="#">Profile - {user.login_name}</a></li>
					<li><a onClick={ (event) =>  this.logout(event)}>Logout</a></li>
				</ul>
			);

			const guestLinks = (
				<ul className="nav navbar-nav navbar-right">
					<li><Link to="/login">Login</Link></li>	
				</ul>
			);

			return (
					<header>

						<nav className="navbar navbar-inverse navbar-fixed-top">
							<div className="container-fluid">
								<div className="navbar-header">
									<button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
										<span className="sr-only">Toggle navigation</span>
										<span className="icon-bar"></span>
										<span className="icon-bar"></span>
										<span className="icon-bar"></span>
									</button>
									<a className="navbar-brand" href="#">Apartment Manager</a>
								</div>
								<div id="navbar" className="navbar-collapse collapse">
									{isAuthenticated ? userLinks : guestLinks}
									<form className="navbar-form navbar-right">
										<input type="text" className="form-control" placeholder="Search..."/>
									</form>
								</div>
							</div>
						</nav>

						<div className="container-fluid">
							<div className="row">
								<div className="col-sm-3 col-md-2 sidebar">
									<ul className="nav nav-sidebar">
									
										<li><Link to="/home" activeclassname={"active"}>Home<span className="sr-only">(current)</span></Link></li>
										<li><Link to="/residents" activeclassname={"active"}>Residents Management</Link></li>
										<li><Link to="/incidents" activeclassname={"active"}>Incident Management</Link></li>
									
									</ul>
								</div>
							</div>
						</div>
						
						<Redirect to='/login' />

			</header>
			);
	}

	logout(event) {
		localStorage.removeItem(APP_TOKEN_KEY);
		this.props.setCurrentUser({});
	}
}




const mapDispatchToProps = (dispatch) => {
    return {
      setCurrentUser: (user) => dispatch(setCurrentUser(user))
    };
}

const mapStateToProps = (state) => {
	return {
		isAuthenticated : state.auth.isAuthenticated,
		user : state.auth.user
	}
};

Header = connect(mapStateToProps, mapDispatchToProps)(Header);
export default Header;