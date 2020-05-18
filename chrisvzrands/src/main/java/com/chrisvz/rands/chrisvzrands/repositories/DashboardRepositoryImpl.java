package com.chrisvz.rands.chrisvzrands.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.chrisvz.rands.chrisvzrands.vo.Message;
import com.chrisvz.rands.chrisvzrands.vo.User;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List getUserList(int i) {
		// TODO Auto-generated method stub

		String getAllUsers= "select list_id,name FROM user_chat_list where user_id="+i+"";
		return jdbcTemplate.query( getAllUsers, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int row) throws SQLException {
				User client = new User();
				client.setUserID(rs.getLong("list_id"));
				client.setUserName(rs.getString("name"));
				return client;
			}
		});
	}

	@Override
	public List getUserMeassageList(int frommsg, int tomsg) {
		// TODO Auto-generated method stub
		String getAllUsers= "select  id,text,from_msg,to_msg FROM demoforreact.message where from_msg=? and to_msg=? or from_msg=? and to_msg=?;";
		return jdbcTemplate.query( getAllUsers,new Object[] {frommsg,tomsg,tomsg,frommsg}, new RowMapper<Message>() {
			public Message mapRow(ResultSet rs, int row) throws SQLException {
				Message client = new Message();
				client.setId(rs.getLong("id"));
				client.setText(rs.getString("text"));
				client.setFrom(rs.getInt("from_msg"));
				client.setTo(rs.getInt("to_msg"));
				if(client.getFrom()==frommsg) {
					client.setUserMsg(true);
				}
				
				return client;
			}
		});
	}

	@Override
	public int sendMsg(Message m) {
		// TODO Auto-generated method stub
		final String addClient = "INSERT INTO message(from_msg,to_msg,text,created_datetime)"
				+ "VALUES(?,?,?,now())";

		long clientID = 0;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = (PreparedStatement) con.prepareStatement(addClient,
						Statement.RETURN_GENERATED_KEYS);

				statement.setInt(1, m.getFrom());
				statement.setInt(2, m.getTo());
				statement.setString(3, m.getText());
				return statement;
			}
		});

		
		
		
		return 0;
	}

	@Override
	public int sendMsg(int userID, int listID,String name) {
		// TODO Auto-generated method stub
		
		long v=chekId(listID);
		if(v>0) {
			final String addClient = "INSERT INTO user_chat_list(user_id,list_id,name)"
					+ "VALUES(?,?,?)";

			long clientID = 0;
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement statement = (PreparedStatement) con.prepareStatement(addClient,
							Statement.RETURN_GENERATED_KEYS);

					statement.setInt(1, userID);
					statement.setInt(2, listID);
					statement.setString(3, name);
					return statement;
				}
			});

		}

		return 0;
	}

	private Long chekId(int listID) {
		// TODO Auto-generated method stub
		String sql = "SELECT  count(id)  FROM user WHERE id=?";

		return jdbcTemplate.queryForObject(sql, new Object[] { listID }, Long.class);

	}

}
