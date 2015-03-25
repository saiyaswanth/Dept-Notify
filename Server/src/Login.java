

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rollno = request.getParameter("RollNo").toString();
		String password = request.getParameter("Pswrd").toString();
		Connection connection=null;
		//Statement st=null;
		ResultSet rs=null;
		
		System.out.println(rollno);
		System.out.print(password);
		
		
		
		String retriveQuery="select * from DataEntry where Rollno = ? and password = ?";
		try{
			connection=DataBaseConnectionClass.getConnection();
	//		st=connection.createStatement();
			PreparedStatement pst = connection.prepareStatement(retriveQuery);
			pst.setString(1	, rollno);
			pst.setString(2, password);
			rs=pst.executeQuery();
			PrintWriter writer=response.getWriter();
			//JSONObject object=new JSONObject();
			//JSONArray array=new JSONArray();
			if(rs.next())
			{ 
				System.out.print("in the if loop");
				writer.print(1);
			}
			else
			{
				System.out.print("in the else loop");
				writer.print(0);
			}
			}catch(Exception e)
			{
				System.out.println(e);
			}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
