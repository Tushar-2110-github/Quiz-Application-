package com.velocity.quiz;

import java.sql.Connection;
import java.sql.SQLException;

public interface Quiz_Interface {
	public abstract Connection getConnectionDetails() ;
	public abstract  void getcandidatedata() throws SQLException;
	public void login() throws SQLException;
	public void quiz() throws SQLException;
	public void ResultByUP() throws SQLException;
	public  void getStudentList() throws SQLException;
	public  void ResultByID() throws SQLException;
}
