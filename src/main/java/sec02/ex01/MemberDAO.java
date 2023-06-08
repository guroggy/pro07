package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext; //이름 찾기위한 작업 위해 사용하는 클래스
import javax.naming.NamingException;
import javax.sql.DataSource; // 실질적인 어떤 데이터인지를 연결하는 객체

public class MemberDAO {
	// 필
//	private static final String driver = "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";   // jdbc:사용할 JDBC 드라이버(oracle, mysql):드라이버타입:@서버이름(ip):포트번호:DB서비스아이디(SID)
//	private static final String user = "scott";
//	private static final String pwd = "12341234";

	private Connection con;
	private DataSource dataFactory;
	private PreparedStatement pstmt;

	public MemberDAO() {
		// JNDI방식의 연결로 MemberDAO 객체를 초기화

		try {
			Context ctx = new InitialContext(); // 컨텍스트 작업을 위한 객체
			Context envContext = (Context) ctx.lookup("java:/comp/env"); // 오라클인지 mysql인지의 환경을 찾기위한 컨텍스트
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			System.out.println("톰캣의 context.xml에 정의되어 있는 이름부분에서 정확하지 않은 에러");
//			e.printStackTrace();
		}

	}

	// 메
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member ";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	// 커넥션 DB이므로 미리 연결객체를 만들어 놨기 때문에 필요없음
//	private void connDB() {  
//	}

}
