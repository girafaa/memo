package memo.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class memberDao {
	Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "memo";
		String password = "memo";
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
	void register(memberDto dto) throws Exception{
		Connection con = getConnection();
		String sql = "insert into member values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getPw());
		ps.setString(3, dto.getPhone());
		ps.execute();
	}
	
}
