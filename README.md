# CarSharingProject
## Overview
Car Sharing project is a part of Java Backend Developer track provided by Hyperskill that allows users to rent cars from different companies. It provides functionalities for managers to manage companies and cars, as well as for customers to rent and return cars. 
Application is working with H2 database
Connection to the database is done with the help of JDBC api.
### Note:
I wrote my own code in Car Sharing/task/src/carsharing, but the test code from Hyperskill was also uploaded to GitHub because I pushed everything from IntelliJ.

## TABLES

### Company
- ID with the type INT, PRIMARY KEY and AUTO_INCREMENT.
- name with the type VARCHAR, UNIQUE and NOT NULL.

### Car
- ID with the type INT, PRIMARY KEY and AUTO_INCREMENT.
- name with the type VARCHAR, UNIQUE and NOT NULL.
- company_ID with the type INT, NOT NULL, FOREIGN KEY referring to the ID column of the table COMPANY.

### Customer 
- ID with the type INT, PRIMARY KEY and AUTO_INCREMENT.
- name with the type VARCHAR, UNIQUE and NOT NULL.
- rented_car_ID with the type INT, FOREIGN KEY referring to the ID column of the CAR table, and this column can be NULL in case the customer didn't rent a car.
- rentedBefore boolean


## Features
### Manager Features:
Log in as a manager
Create and manage companies
Add cars to companies
View the list of companies and cars

### Customer Features:

Create a customer profile
Rent a car from a selected company
Return a rented car
View rented car details

## Example
'''
1. Log in as a manager
2. Log in as a customer
3. Create a customer
0\. Exit
> 2

The customer list is empty!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0\. Exit
> 3

Enter the customer name:
> First customer
The customer was added!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0\. Exit
> 3

Enter the customer name:
> Second customer
The customer was added!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0\. Exit
> 2

Customer list:
1. First customer
2. Second customer
0\. Back
> 1

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 3

You didn't rent a car!

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 2

You didn't rent a car!

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 0

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0\. Exit
> 01. Log in as a manager
2. Log in as a customer
3. Create a customer
0\. Exit
> 2

The customer list:
1. First customer
2. Second customer
0\. Back
> 1

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 1

Choose a company:
1. Car To Go
2. Drive Now
0\. Back
> 1

Choose a car:
1. Hyundai Venue
2. Maruti Suzuki Dzire
0\. Back
> 1

You rented 'Hyundai Venue'

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 3

Your rented car:
Hyundai Venue
Company:
Car To Go

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 1

You've already rented a car!

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 2

You've returned a rented car!

1. Rent a car
2. Return a rented car
3. My rented car
0\. Back
> 0

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0\. Exit
> 0
