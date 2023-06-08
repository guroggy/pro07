package sec01.ex01;

import java.sql.Date;


public class MemberVO {
	//필드(field) - 객체의 데이터가 저장되는 곳
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	//생성자 - 객체의 생성시 초기화 역할 담당, 생성자 만드는 법   클래스명() {}, 일종의 메서드, 리턴 타입이 없다.
	
	//기본 생성자
	public MemberVO() {
		System.out.println("Member 생성자 호출됨");
	}
	
	//메서드 - 객체의 동작에 해당하는 실행 블록
	
	//Getter(다른 클래스에서 id 값을 가져가게 하는 역할)
	public String getId(){
		return id;
	}
	
	//Setter(다른 클래스에서 id 값을 설정하는 역할)
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
}
