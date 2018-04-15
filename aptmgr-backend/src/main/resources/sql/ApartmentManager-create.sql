-- SaaS structure for registering apartments

-- apartment structure
create table apartments (
	id serial primary key,
	apt_name varchar(250),
	apt_primary_email varchar(250),
	apt_address text,
	apt_city varchar(200),
	apt_pincode varchar(20)
);

-- user structure
create table users (
	id serial primary key,
	email varchar(150),
	name varchar(250),
	password varchar(100),
	country_code smallint,
	phone varchar(10),
	picture_url varchar(300),
	role varchar(30), 
	-- SUPER_ADMIN (SaaS admin), APT_ADMIN (Admin of a particular Apt), MC (Memember of Mangement committe of an Apt), 
	-- MEMBER ( Member of an Apt), USER (A simply regiested user, not part of any apt)
	unique(email)
);

create table meta_apartment_roles (
	id serial primary key,
	apt_role_name varchar(100) unique,
	description varchar(300),
	core boolean
);

insert into meta_apartment_roles (apt_role_name, description, core) values
('President',  'Takes care of overall functioning of the apartment', true),
('Vice President',  'Takes care of overall functioning of the apartment, in the absence of president. Will also help the president', true),
('Secretary',  'Responsible for day to day activities, functioning and communications', true),
('Joint Secretary',  'Responsible for day to day activities, functioning and communications, in the absence of Secretary. Will also help the Secretary', true),
('Treasurer',  'Manages finance of the apartment society', true),
('Joint Treasurer',  'Manages finance of the apartment society, in the absence of Treasurer. Will also help the Treasurer', true),
('Committee member',  'Member of one of or more committees ', false),
('Owner',  'Member of apartment association, who owns one or more units in the society.', false),
('Tenant',  'A temporary resident of the apartment society.', false); 

create table meta_apartment_functions (
	id serial primary key,
	function_key varchar(100),
	function_name varchar(150),
	description varchar(300)
);

insert into meta_apartment_functions (function_key, function_name, description) values
('Manage_Members', 'Manage Members', 'Ability to change the role of members'),
('Manage_Finance', 'Manage Finance', 'Ability to add expense, manage maintenance payments'),
('Manage_Issues', 'Manage Issues', 'View and Manage the issues');

create table apartment_user_data (
	id serial primary key,
	apt_id int,
	apt_role_id int,
	user_id int,
	status varchar(50),
	unique(apt_id, user_id, apt_role_id),
	foreign key (user_id) references users(id),
	foreign key (apt_id) references apartments(id),
	foreign key (apt_role_id) references meta_apartment_roles(id)
);

create table apartment_admin_functions (
	id serial primary key,
	apt_user_data_id int,
	function_id int,
	unique(apt_user_data_id, function_id),
	foreign key (apt_user_data_id) references apartment_user_data(id),
	foreign key (function_id) references meta_apartment_functions(id)
);

create table apartment_units (
	id serial primary key,
	apt_id int,
	block varchar(30),
	wing varchar(30),
	flat_number varchar(30),
	unique(apt_id, block, wing, flat_number),
	foreign key (apt_id) references apartments(id)
);

create table apartment_occupancy (
	id serial primary key,
	apt_unit_id int,
	user_id int,
	unique(apt_unit_id, user_id),
	foreign key (apt_unit_id) references apartment_units(id),
	foreign key (user_id) references users(id)
);



