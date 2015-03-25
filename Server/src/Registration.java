

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PrintWriter out = response.getWriter();
		 try{
			 connection=DataBaseConnectionClass.getConnection();
				String Name = request.getParameter("Name").toString();
				String rollno = request.getParameter("RollNo").toString();
				String phnum = request.getParameter("PhNum").toString();
				String pswrd = request.getParameter("Pswrd").toString();
			//	System.out.println(phnum);
			//	System.out.println(getCurrentTimeStamp());
			//	System.out.println("hey connected pina");
				PreparedStatement pst = connection.prepareStatement("insert into DataEntry values(?,?,?,?,?,?)");
				pst.setString(1,Name);
				pst.setString(2,rollno);
				pst.setString(3,phnum);
				pst.setString(4,pswrd);
				pst.setInt(5, 0);
				pst.setString(6,"nothing");
				out.print(pst.executeUpdate());
				
				}
				catch(Exception e)
			{
				e.printStackTrace();
			}finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	
	
	
	
	
	
	
	
	
	}

	protected static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
