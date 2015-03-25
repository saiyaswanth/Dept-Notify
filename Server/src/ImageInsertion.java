

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageInsertion
 */
@WebServlet("/ImageInsertion")
public class ImageInsertion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageInsertion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Connection connection=null;
		Statement st=null;
		try
		{
			PrintWriter out = response.getWriter();
			connection=DataBaseConnectionClass.getConnection();
			PreparedStatement pst = connection.prepareStatement("insert into imgtable values(?,?)");
			pst.setString(1	, "saale");
			FileInputStream f =new FileInputStream("d:\\s.jpg");
			pst.setBinaryStream(2,f,f.available());  
			int i = pst.executeUpdate();
		
			out.print(i);
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
