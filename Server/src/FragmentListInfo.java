

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
 * Servlet implementation class FragmentListInfo
 */
@WebServlet("/FragmentListInfo")
public class FragmentListInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FragmentListInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		Connection connection=null;
		String tname = request.getParameter("tname").toString();
		//String tname = "data";
		System.out.println(tname+"now in 42 nd line");
		//Statement st=null;
		ResultSet rs=null;
		String retriveQuery = " select * from "+tname+"";
		try{
		connection=DataBaseConnectionClass.getConnection();
		//st=connection.createStatement();
		//rs=st.executeQuery(retriveQuery);
		PreparedStatement pst = connection.prepareStatement(retriveQuery);
		//pst.setString(1	, tname);
		rs=pst.executeQuery();
		PrintWriter writer=response.getWriter();
		
		JSONArray array=new JSONArray();
		while(rs.next())
		{ 
		String Name=rs.getString(1);
		System.out.println(Name);
		JSONObject object=new JSONObject();
		object.put("Name",Name);
		array.put(object);
		}
		writer.print(array.toString());
		}catch(Exception e)
		{
			System.out.print(e);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
