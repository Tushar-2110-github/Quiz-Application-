package com.velocity.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuizIMPL extends RuntimeException implements Quiz_Interface {
	private static String message;

	public QuizIMPL(String message) {
		super(message);
	}

	static int TotalScore = 0;
	static QuizIMPL registrationpage = new QuizIMPL(message);

	public Connection getConnectionDetails() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "Tushar@2110");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void getcandidatedata() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement qs = null;
		try {

			con = registrationpage.getConnectionDetails();
			ps = con.prepareStatement(
					"insert into quiz_registration(firstName,lastname,username,password,city,emailid,Mobilenumber)values(?,?,?,?,?,?,?)");
			qs = con.prepareStatement("insert into credential(username,password)values(?,?)");
			System.out.println("***************** WELCOME TO THE QUIZ REGISTRATION ***************");
			System.out.println("");
			System.out.println("Enter the First Name");
			String firstName = scanner.next();
			ps.setString(1, firstName);
			System.out.println("Enter the Last Name");
			String lastname = scanner.next();
			ps.setString(2, lastname);
			System.out.println("Enter the Username");
			String username = scanner.next();
			ps.setString(3, username);
			qs.setString(1, username);
			System.out.println("Enter the Password");
			String password = scanner.next();
			ps.setString(4, password);
			qs.setString(2, password);
			System.out.println("Enter the City");
			String city = scanner.next();
			ps.setString(5, city);
			System.out.println("Enter the Email id");
			String emailid = scanner.next();
			ps.setString(6, emailid);
			System.out.println("Enter the Mobile Number");
			String Mobilenumber = scanner.next();
			ps.setString(7, Mobilenumber);
			int i = ps.executeUpdate();
			int j = qs.executeUpdate();
			System.out.println("Candidate Sucessfully Registered...!");
			System.out.println("");
			System.out.println("**Candidate Details**");
			System.out.println("First Name    : " + firstName);
			System.out.println("Last Name     : " + lastname);
			System.out.println("Username      : " + username);
			System.out.println("Password      : " + password);
			System.out.println("City          : " + city);
			System.out.println("Email ID      : " + emailid);
			System.out.println("Mobile Number : " + Mobilenumber);
			System.out.println("");
			System.out.println("Thank you !");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
			qs.close();
		}
	}

	public void login() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		Connection cons = null;
		PreparedStatement psl = null;
		ResultSet rs = null;
		PreparedStatement ps11 = null;
		try {
			cons = registrationpage.getConnectionDetails();
			psl = cons.prepareStatement("SELECT * FROM credential where username=? AND password=?");
			System.out.println("********* WELCOME CONTESTANT TO THE LOGIN PAGE **********");
			System.out.println("");
			System.out.println("Enter the Registered Userame");
			String Username = scanner.next();
			System.out.println("Enter the Registered Password");
			String Password = scanner.next();
			psl.setString(1, Username);
			psl.setString(2, Password);
			rs = psl.executeQuery();
			if (rs.next()) {
				System.out.println("You are sucessfully logged in");
				System.out.println("");
				quiz();
				ps11 = cons.prepareStatement("Update quiz_registration set score=? where username=? and password=?");
				ps11.setInt(1, TotalScore);
				ps11.setString(2, Username);
				ps11.setString(3, Password);
				int a = ps11.executeUpdate();
				System.out.println("Thank your score is sucessfully submitted  !");
			} else {
				throw new QuizIMPL("Invalid User !! You need to Register First !!!");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			cons.close();
			psl.close();
			rs.close();

		}
	}

	public void quiz() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		Connection cons = null;
		PreparedStatement psl = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		PreparedStatement ps6 = null;
		PreparedStatement ps7 = null;
		PreparedStatement ps8 = null;
		PreparedStatement ps9 = null;

		try {
			cons = registrationpage.getConnectionDetails();
			psl = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("******************** Welcome to the quiz !!! *******************");
			System.out.println("");
			System.out.println("*** Choose the correct option ***");
			System.out.println("");
			System.out.println("1. Who invented Java Programming?");
			System.out.println("a) Guido van Rossum\r\n" + "b) James Gosling\r\n" + "c) Dennis Ritchie\r\n"
					+ "d) Bjarne Stroustrup");
			System.out.print("Enter your answer :");
			String answer1 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer1);
			psl.setString(1, "1");
			psl.setString(2, answer1);
			rs = psl.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps1 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("2. Which statement is true about Java?");
			System.out.println("a) Java is a sequence-dependent programming language\r\n"
					+ "b) Java is a code dependent programming language\r\n"
					+ "c) Java is a platform-dependent programming language\r\n"
					+ "d) Java is a platform-independent programming language");
			System.out.print("Enter your answer :");
			String answer2 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer2);
			ps1.setString(1, "2");
			ps1.setString(2, answer2);
			rs = ps1.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps2 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("3. Which one of the following is not a Java feature?");
			System.out.println("a) Object-oriented\r\n" + "b) Use of pointers\r\n" + "c) Portable\r\n"
					+ "d) Dynamic and Extensible");
			System.out.print("Enter your answer :");
			String answer3 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer3);
			ps2.setString(1, "3");
			ps2.setString(2, answer3);
			rs = ps2.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps3 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("4. Which of these cannot be used for a variable name in Java?");
			System.out.println("a) identifier & keyword\r\n" + "b) identifier\r\n" + "c) keyword\r\n"
					+ "d) none of the mentioned");
			System.out.print("Enter your answer :");
			String answer4 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer4);
			ps3.setString(1, "4");
			ps3.setString(2, answer4);
			rs = ps3.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" *********************************************************");
			System.out.println("");

			ps4 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("5. What is the extension of java code files?");
			System.out.println("a) .js\r\n" + "b) .txt\r\n" + "c) .class\r\n" + "d) .java");
			System.out.print("Enter your answer :");
			String answer5 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer5);
			ps4.setString(1, "5");
			ps4.setString(2, answer5);
			rs = ps4.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps5 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("6. Which of the following is not an OOPS concept in Java?");
			System.out
					.println("a) Polymorphism\r\n" + "b) Inheritance\r\n" + "c) Compilation\r\n" + "d) Encapsulation");
			System.out.print("Enter your answer :");
			String answer6 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer6);
			ps5.setString(1, "6");
			ps5.setString(2, answer6);
			rs = ps5.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps6 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("7. Which of the following is a type of polymorphism in Java Programming?");
			System.out.println("a) Multiple polymorphism\r\n" + "b) Compile time polymorphism\r\n"
					+ "c) Multilevel polymorphism\r\n" + "d) Execution time polymorphism");
			System.out.print("Enter your answer :");
			String answer7 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer7);
			ps6.setString(1, "7");
			ps6.setString(2, answer7);
			rs = ps6.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps7 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("8. What is the extension of compiled java classes?");
			System.out.println("a) .txt\r\n" + "b) .js\r\n" + "c) .class\r\n" + "d) .java");
			System.out.print("Enter your answer :");
			String answer8 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer8);
			ps7.setString(1, "8");
			ps7.setString(2, answer8);
			rs = ps7.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps8 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("9. Which of these keywords is used to define interfaces in Java?");
			System.out.println("a) intf\r\n" + "b) Intf\r\n" + "c) interface\r\n" + "d) Interface");
			System.out.print("Enter your answer :");
			String answer9 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer9);
			ps8.setString(1, "9");
			ps8.setString(2, answer9);
			rs = ps8.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			System.out.println("");

			ps9 = cons.prepareStatement("SELECT * FROM quiz_que where Que_no=? AND answer=?");
			System.out.println("10. Which of the following is a superclass of every class in Java?");
			System.out.println("a) ArrayList\r\n" + "b) Abstract class\r\n" + "c) Object class\r\n" + "d) String");
			System.out.print("Enter your answer :");
			String answer10 = scanner.next().toLowerCase();
			System.out.println("Your option is : " + answer10);
			ps9.setString(1, "10");
			ps9.setString(2, answer10);
			rs = ps9.executeQuery();
			System.out.println("");
			if (rs.next()) {
				System.out.println("Correct Answer ! You Scored One Mark");
				count++;
			} else {
				System.out.println("Opps ! Incorrect Answer !!");
			}
			System.out.println(" ********************************************************* ");
			TotalScore = count;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			cons.close();
			psl.close();
			rs.close();
			ps1.close();
			ps2.close();
			ps3.close();
			ps4.close();
			ps5.close();
			ps6.close();
			ps7.close();
			ps8.close();
			ps9.close();
		}

	}

	public void ResultByUP() throws SQLException {
		Connection conn = null;
		Scanner scanner = null;
		PreparedStatement ps101 = null;
		ResultSet st = null;
		try {
			conn = registrationpage.getConnectionDetails();
			scanner = new Scanner(System.in);
			ps101 = conn.prepareStatement("select * from quiz_registration where username=? and password=?");
			System.out.println("Enter your Registered Username");
			String UserName = scanner.next();
			System.out.println("Enter your Registered Password");
			String PassWord = scanner.next();
			ps101.setString(1, UserName);
			ps101.setString(2, PassWord);
			st = ps101.executeQuery();

			while (st.next()) {
				System.out.println("Your Score is >>>" + st.getInt(9));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			conn.close();
			scanner.close();
			ps101.close();
			st.close();

		}
	}

	public void getStudentList() throws SQLException {
		Connection conn = null;
		PreparedStatement pss = null;
		ResultSet rs = null;
		try {
			conn = registrationpage.getConnectionDetails();
			pss = conn.prepareStatement("select * from quiz_registration order by score");
			rs = pss.executeQuery();
			while (rs.next()) {
				System.out.println("First Name >> " + rs.getString(2));
				System.out.println("Last Name >> " + rs.getString(3));
				System.out.println("Score >> " + rs.getInt(9));
				System.out.println("________________________");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conn.close();
			pss.close();
			rs.close();
		}
	}

	public void ResultByID() throws SQLException {
		Connection conn = null;
		Scanner scanner = null;
		PreparedStatement ps13 = null;
		ResultSet st = null;
		try {
			conn = registrationpage.getConnectionDetails();
			scanner = new Scanner(System.in);
			ps13 = conn.prepareStatement("select * from quiz_registration where emailid=? ");
			System.out.println("Enter you Email ID");
			String Emailid = scanner.next();

			ps13.setString(1, Emailid);

			st = ps13.executeQuery();

			while (st.next()) {
				System.out.println("Your Score is >>>" + st.getInt(9));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			conn.close();
			scanner.close();
			ps13.close();
			st.close();
		}
	}

	static {
		System.out.println("*********Welcome to Quiz based application********** \r\n" + "\r\n"
				+ "1. Student Registration\r\n" + "2. Student Login and Quiz \r\n"
				+ "3. Display Quiz result by using Username and Password\r\n" + "4. Display all students score \r\n"
				+ "5. Display Quiz result by using Email ID\r\n" + "\r\nEnter 0 to exit");
	}

	public static void main(String[] args) throws SQLException {
		QuizIMPL registrationPage = new QuizIMPL(message);
		Scanner scanner = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter your choice >>> ");
		int a = scanner.nextInt();
		int number = a;
		switch (number) {
		case 1:
			registrationPage.getcandidatedata();
			break;
		case 2:
			registrationPage.login();
			break;
		case 3:
			registrationPage.ResultByUP();
			break;
		case 4:
			registrationPage.getStudentList();
			break;
		case 5:
			registrationPage.ResultByID();
			;
			break;
		default:
			System.out.println("Exit");

		}
	}

}
