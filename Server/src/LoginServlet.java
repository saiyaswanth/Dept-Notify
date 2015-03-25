

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Connection connection=null;
		Statement st=null;
		ResultSet rs=null;
		String retriveQuery="select * from employee";
		try{
		connection=DataBaseConnectionClass.getConnection();
		st=connection.createStatement();
		rs=st.executeQuery(retriveQuery);
		PrintWriter writer=response.getWriter();
		JSONObject object=new JSONObject();
		JSONArray array=new JSONArray();
		while(rs.next())
		{ 
		String Name=rs.getString(1);
		String id=rs.getString(2);
		object.put("Name", Name);
		object.put("Id", id);
		array.put(object);
		}
		writer.print(array.toString());
		
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
