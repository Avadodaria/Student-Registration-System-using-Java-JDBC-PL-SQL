# Implement-Student-Registration-System-using-PL-SQL-and-JDBC

CS532 DataBase Systems
Fall 2016
Final Project README FILE

Submission Date: Monday, December 05, 2016
Team Authors: Akshay Shah, Vishvas Patel, Kushal Shinde
e-mail: ashah38@binghamton.edu,
		vpatel19@binghamton.edu,
		kshinde1@binghamton.edu

PURPOSE: 
Develop database application using JDBC connectivity to Java and executing stored procedures and triggers.

FILES:
This project inlcudes the files listed below with directory structure:
There are total of 4 java files

DB_Final_Project/src/dbfp/student_db/Driver.java				: Driver to start the execution 
DB_Final_Project/src/dbfp/student_db/ProcessQuery.java			: Calls to different Stored procedures and execution 
DB_Final_Project/src/dbfp/utilities/ConnectionUtil.java			: Utility to connect to data base 
DB_Final_Project/src/dbfp/utilities/Display_Util.java			: Utility to print formatted output to standard output 

The java code also needs ojdbc7.jar. It is included in this zip file.

PL/SQL FILES:
newdeclaration.sql
new1.sql
sequence.sql
trigger.sql

project also needs script file for loading the data to database. It is NOT included in this zip file.

TO COMPILE & RUN:
This zip file contains entire eclipse project. 
To compile please import folder named DB_Final_Project to eclipse workspace.
Now, compile & run using run button in eclipse.
Please make sure that Oracle12c Standard Edition Database is installed and set up with above mentioned database before running this project on eclipse.
Also please mention your username & password of Orace DB in ConnectionUtil.java file.
