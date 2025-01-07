package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//consulta toda tabela
	public List<UserModel> lista() throws Exception{
		
		List<UserModel> listar = new ArrayList<UserModel>();
		
		String sql = "select * from inscricao";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		while (resultado.next()) {
			
			UserModel userModel = new UserModel();
			userModel.setId(resultado.getLong("id"));
			userModel.setNome(resultado.getString("nome"));
			userModel.setData(resultado.getString("data"));
			
			listar.add(userModel);
		}
		
		return listar;
	}
	
	
	//busca por id
	public UserModel buscaId(Long id) throws Exception{
		
		
		UserModel userModel = new UserModel();
		
		String sql = "select * from inscricao where id = " + id;
		PreparedStatement prStatement = connection.prepareStatement(sql);
		
		ResultSet resultado = prStatement.executeQuery();
		
		while(resultado.next()) {
			
			userModel.setId(resultado.getLong("id"));
			userModel.setNome(resultado.getString("nome"));
			userModel.setData(resultado.getString("data"));
			
		}
		
		return userModel;
		
		
	}
	

}
