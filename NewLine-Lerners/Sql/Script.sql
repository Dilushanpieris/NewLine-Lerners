DROP DATABASE IF EXISTS newlineDb;
CREATE DATABASE IF NOT EXISTS newlineDb;
SHOW DATABASES;
USE newlineDb;

#----------------------------------------------

DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
    St_ID VARCHAR(6),
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
    T_ID VARCHAR(6),
    Name VARCHAR(20),
    Address VARCHAR(30),
    Age INT(3),
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
SHOW TABLES;
DESCRIBE Exam;
#-------------------------------------------------
DROP TABLE IF EXISTS ExamWrittenStudent;
CREATE TABLE IF NOT EXISTS ExamWrittenStudent(
        Stx_ID VARCHAR(6),
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
    Ins_ID VARCHAR(6),
    Name VARCHAR(20),
    Address VARCHAR(30),
    Telephone VARCHAR(15),
    CONSTRAINT PRIMARY KEY(Ins_ID)
);
SHOW TABLES;
DESCRIBE Instructor;
#-----------------------------------------------------
DROP TABLE IF EXISTS Vehicle;
CREATE TABLE IF NOT EXISTS Vehicle(
    Veh_ID VARCHAR(6),
    Veh_Type VARCHAR(10),
    NamePlate VARCHAR(10),
    CONSTRAINT PRIMARY KEY(Veh_ID)
);
SHOW TABLES;
DESCRIBE Vehicle;
#---------1 to Many Tables----------------------
DROP TABLE IF EXISTS PaymentDetails;
CREATE TABLE IF NOT EXISTS PaymentDetails(
    Pay_ID VARCHAR(6),
    Pay_Date DATE,
    Pay_amount DOUBLE,
    St_ID VARCHAR(6),
    CONSTRAINT PRIMARY KEY(Pay_ID),
    CONSTRAINT FOREIGN KEY(St_ID) REFERENCES Student(St_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE PaymentDetails;

#----associate Tables--------
DROP TABLE IF EXISTS ExamDetail;
CREATE TABLE IF NOT EXISTS ExamDetail(
     Ex_ID VARCHAR(6),
     St_ID VARCHAR(6),
     Exam_Date DATE,
     Marks INT(3),
     CONSTRAINT PRIMARY KEY (Ex_ID, St_ID),
     CONSTRAINT FOREIGN KEY (Ex_ID) REFERENCES Exam(Ex_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (St_ID) REFERENCES Student(St_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE ExamDetail;

DROP TABLE IF EXISTS TeacherDetail;
CREATE TABLE IF NOT EXISTS TeacherDetail(
     T_ID VARCHAR(6),
     St_ID VARCHAR(6),
     Session_Date DATE,
     Day_Count INT,
     CONSTRAINT PRIMARY KEY (T_ID, St_ID),
     CONSTRAINT FOREIGN KEY (T_ID) REFERENCES Teacher(T_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (St_ID) REFERENCES Student(St_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE TeacherDetail;
#----AKA driver Table..
DROP TABLE IF EXISTS TrainingDetail;
CREATE TABLE IF NOT EXISTS TrainingDetail(
     Veh_ID VARCHAR(6),
     Stx_ID VARCHAR(6),
     Ins_ID VARCHAR(6),
     Session_Date DATE,
     Day_Count INT,
     CONSTRAINT PRIMARY KEY (Veh_ID, Stx_ID,Ins_ID),
     CONSTRAINT FOREIGN KEY (Veh_ID) REFERENCES Vehicle(Veh_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (Stx_ID) REFERENCES ExamWrittenStudent(Stx_ID) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT FOREIGN KEY (Ins_ID) REFERENCES Instructor(Ins_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE TrainingDetail;


DROP TABLE IF EXISTS InstructorDetails;
CREATE TABLE IF NOT EXISTS InstructorDetails(
     Ins_ID VARCHAR(6),
     Stx_ID VARCHAR(6),
     Assign_Date DATE,
     CONSTRAINT PRIMARY KEY (Ins_ID, Stx_ID),
     CONSTRAINT FOREIGN KEY (Ins_ID) REFERENCES Instructor(Ins_ID) ON DELETE CASCADE ON UPDATE CASCADE ,
     CONSTRAINT FOREIGN KEY (Stx_ID) REFERENCES ExamWrittenStudent(Stx_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE InstructorDetails;