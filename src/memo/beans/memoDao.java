package memo.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class memoDao {
	private Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "memo";
		String password = "memo";
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
	
	public List<memoDto> getList() throws Exception{
		Connection con = getConnection();
		String sql = "select * from memo order by no";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<memoDto> list = new ArrayList<>();
		while(rs.next()) {
			memoDto dto = new memoDto();
			dto.setNo(rs.getInt(1));
			dto.setId(rs.getString(2));
			dto.setDay(rs.getString(3));
			dto.setLastupdate(rs.getString(4));
			dto.setDetail(rs.getString(5));
			list.add(dto);
		}
		return list;
	}
	
	public void write(memoDto dto) throws Exception {
		Connection con = getConnection();
		String sql = "insert into memo values(memo_seq.nextval,?,sysdate,sysdate,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getDetail());
		ps.execute();
		con.close();
	}
	
	public boolean update(memoDto dto) throws Exception{
		Connection con = getConnection();
		String sql = "update memo set detail=?, lastupdate=sysdate where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getDetail());
		ps.setInt(2, dto.getNo());
		int result = ps.executeUpdate();
		return result>0;
	}
	
	boolean delete(memoDto dto) throws Exception{
		Connection con = getConnection();
		String sql = "delete memo where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, dto.getNo());
		int result = ps.executeUpdate();
		return result>0;
	}
}
