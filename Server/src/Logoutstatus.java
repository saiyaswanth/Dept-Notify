

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

/**
 * Servlet implementation class Logoutstatus
 */
@WebServlet("/Logoutstatus")
public class Logoutstatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logoutstatus() {
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
		String logot = request.getParameter("logot").toString();
		Connection connection=null;
		//Statement st=null;
		//ResultSet rs=null;
		String updateQuery="update DataEntry set logout = ? where Rollno = ? and password = ?";
		
		try{
			connection=DataBaseConnectionClass.getConnection();
			//st=connection.createStatement();
			PreparedStatement pst = connection.prepareStatement(updateQuery);
			pst.setString(1, logot);
			pst.setString(2	, rollno);
			pst.setString(3, password);
			
			PrintWriter writer=response.getWriter();
			//JSONObject object=new JSONObject();
			//JSONArray array=new JSONArray();
			if(pst.executeUpdate()!=0)
			{ 
				writer.print(1);
			}
			else
			{
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
	}

}
