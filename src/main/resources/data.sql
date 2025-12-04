create database oneToStore;

USE oneToStore;

CREATE TABLE usr_tbl (
  user_id INT auto_increment NOT NULL,
  first_name varchar(255) not null,
  middle_name varchar(255) default null,
  last_name varchar(255) default null,
  dob DATE not null,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (user_id)
);

CREATE TABLE role_tbl (
  role_id INT auto_increment NOT NULL,
  role_name varchar(255) not null,
  role_description varchar(255) default null,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (role_id)
);

CREATE TABLE user_roles_tbl (
  mapping_id INT auto_increment NOT NULL,
  user_id int not null,
  role_id int not null,
  PRIMARY KEY (mapping_id),
  constraint fk_user_id foreign key(user_id) references usr_tbl(user_id),
  constraint fk_role_id foreign key(role_id) references role_tbl(role_id)
);

CREATE TABLE exp_item_tbl (
  exp_item_id INT auto_increment NOT NULL,
  item_name varchar(255) not null,
  item_code varchar(10) not null,
  item_desc varchar(255) default null,
  parent_id int(10) DEFAULT NULL,
  depth INT NOT NULL,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null default current_timestamp on update current_timestamp,
  FOREIGN KEY (parent_id) REFERENCES exp_item_tbl (exp_item_id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (exp_item_id)
);

CREATE TABLE user_exp_tbl (
  user_item_id INT auto_increment NOT NULL,
  user_id int not null,
  exp_item_id int not null,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (user_item_id),
  constraint fk_exp_item_user_id foreign key(user_id) references usr_tbl(user_id),
constraint fk_exp_item_id foreign key(exp_item_id) references exp_item_tbl(exp_item_id)
);

CREATE TABLE exp_details (
  exp_id INT auto_increment NOT NULL,
  user_id int not null,
  user_item_id int not null,
  exp_date DATE not null,
  exp_description varchar(255) default null,
  exp_amount decimal(10,5) not null,
  due_date DATE default null,
  submission_date DATE not null,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null  default current_timestamp on update current_timestamp,
  PRIMARY KEY (exp_id),
  constraint fk_exp_user_id foreign key(user_id) references usr_tbl(user_id),
constraint fk_exp_user_item_id foreign key(user_item_id) references user_exp_tbl(user_item_id)
);

CREATE TABLE exp_doc (
  exp_doc_id INT auto_increment NOT NULL,
  exp_id int not null,
  exp_doc_desc int default null,
  exp_doc_name DATE not null,
  exp_doc_path varchar(255) not null,
  active tinyINT default 1,
  PRIMARY KEY (exp_doc_id),
  constraint fk_exp_id foreign key(exp_id) references exp_details(exp_id)
);


CREATE TABLE rec_item_tbl (
  rec_item_id INT auto_increment NOT NULL,
  rec_item_name varchar(255) not null,
  rec_item_code varchar(10) not null,
  rec_item_desc varchar(255) default null,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (rec_item_id)
);

CREATE TABLE rec_rel_tbl (
  rec_map_id INT auto_increment NOT NULL,
  ancestor_id INT NOT NULL,
  descendant_id INT NOT NULL,
  depth INT NOT NULL,
  PRIMARY KEY (rec_map_id),
  constraint fk_rec_ancestor_id foreign key(ancestor_id) references rec_item_tbl(rec_item_id),
  constraint fk_rec_descendant_id foreign key(descendant_id) references rec_item_tbl(rec_item_id)
);

CREATE TABLE user_rec_tbl (
  user_item_id INT auto_increment NOT NULL,
  user_id int not null,
  rec_item_id int not null,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (user_item_id),
  constraint fk_rec_item_user_id foreign key(user_id) references usr_tbl(user_id),
constraint fk_rec_item_id foreign key(rec_item_id) references rec_item_tbl(rec_item_id)
);

CREATE TABLE rec_details (
  rec_id INT auto_increment NOT NULL,
  user_id int not null,
  user_item_id int not null,
  rec_description varchar(255) default null,
 rec_generation_date DATE not null,
  active tinyINT default 1,
  created_on timestamp not null default current_timestamp,
  updated_on timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (rec_id),
   constraint fk_rec_user_id foreign key(user_id) references usr_tbl(user_id),
constraint fk_rec_user_item_id foreign key(user_item_id) references user_rec_tbl(user_item_id)
);

CREATE TABLE rec_doc (
  rec_doc_id INT auto_increment NOT NULL,
  rec_id int not null,
  rec_doc_desc int default null,
  rec_doc_name DATE not null,
  rec_doc_path varchar(255) default null,
  active tinyINT default 1,
  PRIMARY KEY (rec_doc_id),
  constraint fk_rec_id foreign key(rec_id) references rec_details(rec_id)
);


-- Insert data

insert into onetostore.usr_tbl (first_name, last_name, dob) values ('Pooja', 'Agarwal', '1993-02-01');
insert into onetostore.usr_tbl (first_name, last_name, dob) values ('Neha', 'Agarwal', '1994-07-11');
insert into onetostore.usr_tbl (first_name, last_name, dob) values ('Siyaram', 'Agarwal', '1966-07-01');
insert into onetostore.usr_tbl (first_name, last_name, dob) values ('Peeyush', 'Agarwal', '1970-03-19');
insert into onetostore.usr_tbl (first_name, last_name, dob) values ('Mamta', 'Agarwal', '2003-05-02');

insert into onetostore.role_tbl (role_name, role_description) values ('Admin', 'Administrator Privileges');
insert into onetostore.role_tbl (role_name, role_description) values ('User', 'User Privileges');

insert into onetostore.user_roles_tbl (user_id, role_id) values (1, 1);
insert into onetostore.user_roles_tbl (user_id, role_id) values (2, 2);
insert into onetostore.user_roles_tbl (user_id, role_id) values (3, 2);
insert into onetostore.user_roles_tbl (user_id, role_id) values (4, 2);
insert into onetostore.user_roles_tbl (user_id, role_id) values (5, 2);


-- onetostore.exp_item_tbl

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Housing','ITEM1',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Rent','ITEM1-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Property Tax','ITEM1-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Household Repair','ITEM1-3',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Transportation','ITEM2',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Car EMI','ITEM2-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Petrol/CNG/Diesel','ITEM2-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Car Warranty/Insurance','ITEM2-3',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Car Service','ITEM2-4',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Parking Fees','ITEM2-5',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Pollution Check','ITEM2-6',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Food','ITEM3',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Groceries','ITEM3-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Restaurant','ITEM3-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Vegetables','ITEM3-3',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Utilities','ITEM4',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Electricity','ITEM4-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Water','ITEM4-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Garbage','ITEM4-3',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Phones','ITEM4-4',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Cable','ITEM4-5',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Internet','ITEM4-6',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('IT and other utilities','ITEM4-7',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Clothing','ITEM5',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Footwear','ITEM5-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Adult Clothing','ITEM5-2',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Insurance','ITEM6',1);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Personal','ITEM7',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Gym Memebership','ITEM7-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Haircut','ITEM7-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Salon Service','ITEM7-3',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Cosmetic','ITEM7-4',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Subscriptions','ITEM7-5',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Debt','ITEM8',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Home Loan','ITEM8-1',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Retirement','ITEM9',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Financial Planning','ITEM9-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Investing','ITEM9-2',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Eductation','ITEM10',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('School/College Fees','ITEM10-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('School Transport','ITEM10-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Books','ITEM10-3',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Savings','ITEM11',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Emergency Fund','ITEM11-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Big Celebrations (Marriage, Function)','ITEM11-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Other Savings','ITEM11-3',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Gift/Donations','ITEM12',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Birthdays','ITEM12-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Anniversary','ITEM12-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Wedding','ITEM12-3',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Special occasion','ITEM12-4',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Charities','ITEM12-5',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Entertainment','ITEM13',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Alcohol and/or bars','ITEM13-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Games','ITEM13-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Movies','ITEM13-3',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Concerts','ITEM13-4',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Vacations','ITEM13-5',2);

insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Medical/Healthcare','ITEM14',1);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Primary care','ITEM14-1',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Dental care','ITEM14-2',2);
insert into onetostore.exp_item_tbl (item_name,item_code,item_desc, depth) values ('Specialty care (Diabetes,Thyroid etc.)','ITEM14-3','Specialty care (Diabetes,Thyroid etc.)',2);
insert into onetostore.exp_item_tbl (item_name,item_code, depth) values ('Medical devices','ITEM14-4',2);






