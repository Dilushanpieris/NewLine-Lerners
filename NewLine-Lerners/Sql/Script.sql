DROP DATABASE IF EXISTS newlineDb;
CREATE DATABASE IF NOT EXISTS newlineDb;
SHOW DATABASES;
USE newlineDb;

#----------------------------------------------

DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
    St_ID VARCHAR(8),
    Name VARCHAR(20),
    Age INT(3),
    Vehicle_Type VARCHAR(15),
    Email VARCHAR(30),
    Address VARCHAR(30),
    Telephone VARCHAR(15),
    CONSTRAINT PRIMARY KEY(St_ID)
);
SHOW TABLES ;
DESCRIBE Student;

#-----------------------------------------------
DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(
    T_ID VARCHAR(8),
    Name VARCHAR(20),
    Address VARCHAR(30),
    Telephone VARCHAR(15),
    CONSTRAINT PRIMARY KEY(T_ID)
);
SHOW TABLES;
DESCRIBE Teacher;
#------------------------------------------------
DROP TABLE IF EXISTS Exam;
CREATE TABLE IF NOT EXISTS Exam(
    Ex_ID VARCHAR(6),
    PaperType VARCHAR(10),
    CONSTRAINT PRIMARY KEY(Ex_ID)
);
INSERT INTO Exam VALUES("Ex001","Common");
INSERT INTO Exam VALUES("Ex002","Common");
INSERT INTO Exam VALUES("Ex003","General");
INSERT INTO Exam VALUES("Ex004","Final");
SHOW TABLES;
DESCRIBE Exam;
#-------------------------------------------------
DROP TABLE IF EXISTS ExamWrittenStudent;
CREATE TABLE IF NOT EXISTS ExamWrittenStudent(
        Stx_ID VARCHAR(8),
        Name VARCHAR(20),
        Age INT(3),
        Vehicle_Type VARCHAR(15),
        Email VARCHAR(30),
        Address VARCHAR(30),
        Telephone VARCHAR(15),
        CONSTRAINT PRIMARY KEY(Stx_ID)
);
SHOW TABLES;
DESCRIBE ExamWrittenStudent;
#---------------------------------------------------
DROP TABLE IF EXISTS Instructor;
CREATE TABLE IF NOT EXISTS Instructor(
    Ins_ID VARCHAR(8),
    Name VARCHAR(20),
    Address VARCHAR(30),
    Telephone VARCHAR(15),
    CONSTRAINT PRIMARY KEY(Ins_ID)
);
SHOW TABLES;
DESCRIBE Instructor;
#-----------------------------------------------------
DROP TABLE IF EXISTS Withdrawal_Data;
CREATE TABLE IF NOT EXISTS Withdrawal_Data(
    With_ID VARCHAR(8),
    With_Date DATE,
    With_Amount DOUBLE,
    With_Balance DOUBLE,
    CONSTRAINT PRIMARY KEY(With_ID)
);
SHOW TABLES;
DESCRIBE Withdrawal_Data;

#---------1 to Many Tables----------------------
DROP TABLE IF EXISTS PaymentDetails;
CREATE TABLE IF NOT EXISTS PaymentDetails(
    Pay_ID VARCHAR(8),
    Pay_Date DATE,
    Pay_amount DOUBLE,
    St_ID VARCHAR(8) DEFAULT NULL,
    Stx_ID VARCHAR(8) DEFAULT NULL,
    CONSTRAINT PRIMARY KEY(Pay_ID)
);
#--Modeified Line---
SHOW TABLES;
DESCRIBE PaymentDetails;

#----associate Tables--------
DROP TABLE IF EXISTS ExamDetail;
CREATE TABLE IF NOT EXISTS ExamDetail(
     Ex_ID VARCHAR(6),
     St_ID VARCHAR(8),
     Exam_Date DATE,
     Marks INT(3),
     CONSTRAINT PRIMARY KEY (Ex_ID, St_ID),
     CONSTRAINT FOREIGN KEY (Ex_ID) REFERENCES Exam(Ex_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (St_ID) REFERENCES Student(St_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE ExamDetail;
#------------------------------------------
DROP TABLE IF EXISTS LearningDetails;
CREATE TABLE IF NOT EXISTS LearningDetails(
     T_ID VARCHAR(8),
     St_ID VARCHAR(8),
     Session_Date DATE,
     Day_Count INT,
     CONSTRAINT PRIMARY KEY (T_ID, St_ID,Session_Date),
     CONSTRAINT FOREIGN KEY (T_ID) REFERENCES Teacher(T_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (St_ID) REFERENCES Student(St_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE LearningDetails;
#----AKA driver Table..
DROP TABLE IF EXISTS TrainingDetail;
CREATE TABLE IF NOT EXISTS TrainingDetail(
     Stx_ID VARCHAR(8),
     Ins_ID VARCHAR(8),
     Session_Date DATE,
     Day_Count INT,
     CONSTRAINT PRIMARY KEY (Stx_ID,Ins_ID),
     CONSTRAINT FOREIGN KEY (Stx_ID) REFERENCES ExamWrittenStudent(Stx_ID) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT FOREIGN KEY (Ins_ID) REFERENCES Instructor(Ins_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE TrainingDetail;

#-----------------------------------------
DROP TABLE IF EXISTS InstructorDetails;
CREATE TABLE IF NOT EXISTS InstructorDetails(
     Ins_ID VARCHAR(8),
     Stx_ID VARCHAR(8),
     Assign_Date DATE,
     CONSTRAINT PRIMARY KEY (Ins_ID, Stx_ID),
     CONSTRAINT FOREIGN KEY (Ins_ID) REFERENCES Instructor(Ins_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (Stx_ID) REFERENCES ExamWrittenStudent(Stx_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE InstructorDetails;
#---------------------------------------
DROP TABLE IF EXISTS TeacherDetail;
CREATE TABLE IF NOT EXISTS TeacherDetail(
     T_ID VARCHAR(8),
     St_ID VARCHAR(8),
     Assign_Date DATE,
     CONSTRAINT PRIMARY KEY (T_ID, St_ID),
     CONSTRAINT FOREIGN KEY (T_ID) REFERENCES Teacher(T_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (St_ID) REFERENCES Student(St_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE TeacherDetail;