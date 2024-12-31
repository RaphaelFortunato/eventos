package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.SingleConnection;
import model.UserModel;

public class UserDaoEventos {
	
	private Connection connection;
	
	public UserDaoEventos() {
		connection = SingleConnection.getConexao();
	}
	
	public void cadastro(UserModel userModel) {
		
		
		try {
			String sql = "INSERT INTO public.inscricao	( nome, data) VALUES ( ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userModel.getNome());
			preparedStatement.setString(2, userModel.getData());
			preparedStatement.execute();
			
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	
		
	}

}
