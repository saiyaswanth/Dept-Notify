

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImgExtration
 */
@WebServlet("/ImgExtration")
public class ImgExtration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgExtration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//PrintWriter out = response.getWriter();
		Connection con = null;
		Statement st = null;
		con = DataBaseConnectionClass.getConnection();
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("image/jpg");
		try{
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from imgtable");
		if(rs.next())
		{
			Blob b = rs.getBlob(2);
			byte barr[]=b.getBytes(1,(int)b.length());
			//FileOutputStream fout=new FileOutputStream("d:\\asdf.jpg");  
			//fout.write(barr); 
			out.write(barr,0,(int)b.length());
		}
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
