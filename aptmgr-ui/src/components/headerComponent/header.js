import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';

import { setCurrentUser } from '../../actions/authActions';
import { APP_TOKEN_KEY, APP_BASE_URL } from '../../config/config';
import defaultProfilePic from '../../images/profile_default.jpg';

class Header extends Component {

	constructor(props) {
		super(props);
		this.state = {
			email : '',
			name : '',
			pictureUrl : '',
			defaultPictureUrl : defaultProfilePic
		};
	}

	/**
	 * Called when the component is mounted for the first time
	 */
	componentDidMount() {
		this.fetchUserData(this.props.user.login_name);
	}

	/**
	 * Called when, redux state change triggers props update
	 */
	componentWillReceiveProps(nextProps) {
		this.fetchUserData(nextProps.user.login_name);
	}

	
	render() {
			const {isAuthenticated, user} = this.props;

			const isSuperAdmin = user.user_role === "SUPER_ADMIN";

			const userNavBarLinks = (
				<ul className="nav navbar-nav navbar-right">
					<li><img src={this.state.pictureUrl ? this.state.pictureUrl : this.state.defaultPictureUrl} height="45" width="45" className="profile-image img-circle" /></li>
					<li className="dropdown">
						<a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">{this.state.name ? this.state.name : user.login_name}<span className="caret"></span></a>
						<ul className="dropdown-menu">
							<li><a href="#">Notifications <span className="badge">3</span></a></li>
							<li><Link to={"/profile/"+ user.login_name}>Edit Profile</Link></li>
							<li role="separator" className="divider"></li>
							<li><a onClick={ (event) =>  this.logout(event)}>Logout</a></li>
						</ul>
					</li>
				</ul>
			);

			const guestNavBarLinks = (
				<ul className="nav navbar-nav navbar-right">
					<li><Link to={"/login"}>Login</Link></li>	
				</ul>
			);

			const userLeftMenu = (
				<React.Fragment>
					<li><Link to="/home" activeclassname={"active"}><span className="glyphicon glyphicon-home"></span> Home<span className="sr-only">(current)</span></Link></li>
					<li><Link to="/residents" activeclassname={"active"}><span className="glyphicon glyphicon-check"></span> Residents Management</Link></li>
					<li><Link to="/incidents" activeclassname={"active"}><span className="glyphicon glyphicon-info-sign"></span> Incident Management</Link></li>
				</React.Fragment>
			)

			const superAdminLeftMenu = (
				<React.Fragment>
					{userLeftMenu}
					<li><Link to="/apt-management" activeclassname={"active"}><span className="glyphicon glyphicon-tasks"></span> Apartment Management</Link></li>
					<li><Link to="/saas-admin" activeclassname={"active"}><span className="glyphicon glyphicon-user"></span> Admin</Link></li>
				</React.Fragment>
			)

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
									<a className="navbar-brand" href="#">Apartment Manager <span className="glyphicon glyphicon-home"></span></a>
								</div>
								<div id="navbar" className="navbar-collapse collapse">
									{isAuthenticated ? userNavBarLinks : guestNavBarLinks}
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
										{ isSuperAdmin ? superAdminLeftMenu : userLeftMenu }
									</ul>
								</div>
							</div>
						</div>

			</header>
			);
	}

	logout(event) {
		localStorage.removeItem(APP_TOKEN_KEY);
		this.props.setCurrentUser({});
		window.location.reload();
	}

	fetchUserData(userEmailId) {
		axios.get(APP_BASE_URL + 'users/email?email='+userEmailId)
		.then(res => {
			this.setState({
				name : res.data.name,
				pictureUrl : res.data.pictureUrl
			});
		})
		.catch(err => {
			console.log(err);
		})
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