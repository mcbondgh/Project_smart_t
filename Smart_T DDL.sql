USE smart_toll;

CREATE TABLE roles(
roleId INT AUTO_INCREMENT PRIMARY KEY,
role_name VARCHAR(100) NOT NULL UNIQUE,
date_added DATETIME DEFAULT NOW()
);

CREATE TABLE employees(
empId INT AUTO_INCREMENT PRIMARY KEY,
firstName VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
mobileNumber VARCHAR(20) NOT NULL,
gender VARCHAR(10) NOT NULL,
department VARCHAR(50) NOT NULL,
date_added DATETIME DEFAULT NOW()
);

CREATE TABLE users(
userID INT AUTO_INCREMENT PRIMARY KEY,
empId INT NOT NULL,
roleId INT NOT NULL,
username VARCHAR(50),
password VARCHAR(100),
date_added DATETIME DEFAULT NOW()
);

CREATE TABLE vehicle_categories(
	vehicleId INT auto_increment PRIMARY KEY,
    category_name VARCHAR(100),
    category_class CHARACTER,
    description VARCHAR(255) DEFAULT "No description provided.",
    date_added DATETIME DEFAULT NOW());
    
CREATE TABLE vehicle_types(
id INT AUTO_INCREMENT PRIMARY KEY,
vehicle_name VARCHAR(100),
vehicle_class CHARACTER,
vehicle_weight VARCHAR(20),
max_weight INT,
min_weight INT,
toll_rate DECIMAL(5,2),
date_added DATETIME DEFAULT NOW()
);

CREATE TABLE customer_registration(
customerId INT AUTO_INCREMENT PRIMARY KEY,
customer_name VARCHAR(100),
customer_contact VARCHAR(50),
customer_email VARCHAR(50),
customer_address VARCHAR(50),
customer_idType VARCHAR(50),
customer_idNumber VARCHAR(50),
comments TEXT,
customer_vehicle_name VARCHAR(50),
vechicle_class CHARACTER,
vehicle_weight VARCHAR(50),
registration_number VARCHAR(50),
toll_rate DECIMAL(5,2),
qr_code_token VARCHAR(255),
date_added DATETIME DEFAULT NOW(),
valid_date DATE
);

CREATE TABLE toll_logs(
	log_id INT AUTO_INCREMENT PRIMARY KEY,
    qr_token VARCHAR(100),
    amount DECIMAL(5,2),
    payment_method VARCHAR(10),
    payment_status BOOLEAN DEFAULT FALSE,
    date_created DATETIME default now()
);


