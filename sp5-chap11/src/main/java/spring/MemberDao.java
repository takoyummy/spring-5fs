package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
		 "select * from MEMBER where EMAIL = ?",
		 new RowMapper<Member>() {
			 public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				Member member = new Member(
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("name"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
			 }
		 },email); // 여기서 세번째 인자는 ?안에 들어갈 값 
		//query 메서드는 쿼리를 실행한 결과가 존재하지 않으면 길이가 0인 List를 리턴함. 
		return results.isEmpty() ? null: results.get(0);				
	}
		 
		

	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL,PASSWORD,NAME,REGDATE)" + "values(?,?,?,?)", new String[]{"ID"});
			
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		},keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	
	public void update(Member member) {
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD=? where EMAIL=?", 
				member.getName(),member.getPassword(),member.getEmail());
	}
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query("select * from MEMBER", new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Member member = new Member(
						rs.getString("EMAIL"),
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
				}
			});
		return results;		
	}
	
	public int count() {
		//queryForObject는 쿼리 실행결과가 한 행일 경우 사용하는 메서드이며,
		//두번째 파라미터는 칼럼을 읽어올 때 사용할 타입을 정한다. 
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class);
	
		return count;
	}
				
	}

