package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	// 필
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String user = "scott";
	private static final String pwd = "12341234";

	ResultSet rs;
//	Statement stmt;
	Connection conn;
	PreparedStatement pstmt;
	// 생

	// 메
	public List<MemberVO> listMember() {
		List<MemberVO> list = new ArrayList<MemberVO>();

		connDB();

		String query = "select * from t_member ";
		System.out.println(query);

		try {
//			ResultSet rs =stmt.executeQuery(query);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
//			rs.next()
			while (rs.next()) {
//				String id = rs.getString("id");
				String id = rs.getString(1);
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				System.out.println(id + pwd + name + email + joinDate);

				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);

				list.add(vo);
				System.out.println("=======================");
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("sql 문장을 돌리는데 문제가 생김");
//			e.printStackTrace();
		}

		return list;
	}

	private void connDB() {
		try {
			Class.forName(driver); // Returns the Class object associated with the class orinterface with the given
									// string name.
			System.out.println("oracle 드라이버 로딩 성공");
			conn = DriverManager.getConnection(url, user, pwd); // Attempts to establish a connection to the given
																// database URL.
			System.out.println("Connection 생성 성공");
//			pstmt = conn.createStatement(); // Creates a Statement object for sendingSQL statements to the database.
			System.out.println("Statement 생성 성공");
		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 생김");

		}
	}

}
