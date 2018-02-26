import React, { Component } from 'react';
import { Link } from 'react-router-dom';


class Header extends Component {
  render() {
    return (
      <header>

      <nav class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">Apartment Manager</a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="#">Notifications</a></li>
	            <li><a href="#">Settings</a></li>
	            <li><a href="#">Profile</a></li>
	            <li><a href="#">Help</a></li>
	          </ul>
	          <form class="navbar-form navbar-right">
	            <input type="text" class="form-control" placeholder="Search..."/>
	          </form>
	        </div>
	      </div>
    	</nav>

			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-3 col-md-2 sidebar">
						<ul class="nav nav-sidebar">
						
							<li><Link to="/home" activeClass={"active"}>Home<span class="sr-only">(current)</span></Link></li>
							<li><Link to="/residents" activeClass={"active"}>Residents Management</Link></li>
							<li><Link to="/incidents" activeClass={"active"}>Incident Management</Link></li>
						
						</ul>
					</div>
				</div>
			</div>
				

      </header>
    );
  }
}

export default Header;
