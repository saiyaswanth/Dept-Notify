

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class PunishmentReturn
 */
@WebServlet("/PunishmentReturn")
public class PunishmentReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PunishmentReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
	
		String rollno = request.getParameter("RollNo").toString();
	String password = request.getParameter("Pswrd").toString();
//		String rollno="sai";
//		String password = "sai";
		Connection connection=null;
		//Statement st=null;
		ResultSet rs=null;
		String retriveQuery="select * from dataentry where Rollno = ? and password = ?";
		System.out.println("42/LogoutReturn");
		try{
			connection=DataBaseConnectionClass.getConnection();
	//		st=connection.createStatement();
			PreparedStatement pst = connection.prepareStatement(retriveQuery);
			System.out.print(rollno);
			System.out.print(password);
			
			pst.setString(1	, rollno);
			pst.setString(2, password);
			rs=pst.executeQuery();
			PrintWriter writer=response.getWriter();
			//JSONObject object=new JSONObject();
			//JSONArray array=new JSONArray();
			System.out.println("52/LogoutReturn");
			
			JSONObject object=new JSONObject();
			JSONArray array=new JSONArray();
			
			if(rs.next())
			{ 
				System.out.println("56/LogoutReturn");
				String punishment = rs.getString(6);
				object.put("punishment", punishment);
				array.put(object);
				writer.print(array.toString());
				
			}
			else
			{
				System.out.println("61/LogoutReturn");
				writer.print(0);
			}
			}catch(Exception e)
			{
				System.out.println("66/LogoutReturn");
				System.out.println(e);
			}
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
