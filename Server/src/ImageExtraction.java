import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class ImageExtraction 
{
	@SuppressWarnings("resource")
	public static void main(String args[])
	{
		Connection con = null;
		Statement st = null;
		con = DataBaseConnectionClass.getConnection();
		try{
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from imgtable");
		if(rs.next())
		{
			Blob b = rs.getBlob(2);
			byte barr[]=b.getBytes(1,(int)b.length());
			FileOutputStream fout=new FileOutputStream("d:\\asdf.jpg");  
			fout.write(barr); 
		}
		}catch(Exception e)
		{
			System.out.print(e);
		}
	}
}
