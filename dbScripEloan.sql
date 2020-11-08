CREATE DATABASE eloan;

USE eloan;


CREATE TABLE user_details(
user_id VARCHAR(20) PRIMARY KEY,
password VARCHAR(20) NOT NULL,
name VARCHAR(30) not null,
phone_no VARCHAR(10) not null,
email_id VARCHAR(50) not null,
role VARCHAR(20) NOT NULL CHECK (role IN('admin','user'))
);

INSERT INTO user_details(user_id,password,name,phone_no,email_id,role) 
values("admin","admin","administrator","1234567890","admin@iiht.com","admin");


CREATE TABLE loan_info(
user_id VARCHAR(20),
appl_no VARCHAR(20) PRIMARY KEY,
loan_desc VARCHAR(100) NOT NULL,
amount_requested DOUBLE NOT NULL,
appl_date DATE NOT NULL,
business_strc VARCHAR(20) NOT NULL CHECK (business_strc IN('individual','organisation')),
billing_ind VARCHAR(20) NOT NULL CHECK (billing_ind IN('salaried','non-salaried')),
tax_ind  VARCHAR(20) NOT NULL CHECK (tax_ind IN('tax-payer','non-tax-payer')),
address VARCHAR(200) NOT NULL,
mobile VARCHAR(10) NOT NULL,
email VARCHAR(50) NOT NULL,
loan_status VARCHAR(20) NOT NULL CHECK (loan_status IN('pending','processing','approved','rejected')),
CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES user_details(user_id)
);

CREATE TABLE approved_loans(
app_no VARCHAR(20) PRIMARY KEY,
sanctioned_amount DOUBLE,
rate DOUBLE,
term INT,
payment_start_dte DATE,
loan_closure_dte DATE,
emi DOUBLE,
term_payment_amount DOUBLE,
comments VARCHAR(200),
CONSTRAINT FK_app_no FOREIGN KEY (app_no) REFERENCES loan_info(appl_no)
);

I
