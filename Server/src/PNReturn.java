

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
 * Servlet implementation class PNReturn
 */
@WebServlet("/PNReturn")
public class PNReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PNReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		
		Connection connection=null;
		//Statement st=null;
		ResultSet rs=null;
		String retriveQuery="select * from DataEntry where Rollno = ? and password = ?";
		System.out.println("42/PNreturn");
		try{
			connection=DataBaseConnectionClass.getConnection();
	//		st=connection.createStatement();
			PreparedStatement pst = connection.prepareStatement(retriveQuery);
			String rollno = request.getParameter("RollNo").toString();
		String password = request.getParameter("Pswrd").toString();
		
		System.out.print(rollno);
			System.out.print(password);
			
			pst.setString(1	, rollno);
			pst.setString(2, password);
			rs=pst.executeQuery();
			PrintWriter writer=response.getWriter();
			JSONObject object=new JSONObject();
			JSONArray array=new JSONArray();
			System.out.println("52/PNreturn");
			
			if(rs.next())
			{ 
				
			//	writer.print(rs.getString(3));
				String number = rs.getString(3);
				String myname = rs.getString(1);
				object.put("myname",myname);
				array.put(object);
				object.put("number", number);
				array.put(object);
				writer.print(array.toString());
				
			}
			
			else
			{
				System.out.println("61/PNreturn");
				writer.print(0);
			}
			}catch(Exception e)
			{
				System.out.println("66/PNreturn");
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
