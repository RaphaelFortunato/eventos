package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;

import conexao.SingleConnection;
import model.BeanLocalEvento;
import model.LocalEvento;
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
	
	//atualizar
	public void atualizar(UserModel userModel){
	
		String sql = "update inscricao set nome = ?, data = ? where id =" + userModel.getId();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userModel.getNome());
			statement.setString(2, userModel.getData());
			
			statement.execute();
			connection.commit();
		
		}catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		
	}
	
	
	public void deletando(Long id) {
		
		String sql = "delete from inscricao where id = " + id;
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
	
	//salvar local evento - tabela filha
	public void salvarEvento(LocalEvento localEvento) {
		
		try {
			
			String sql = "INSERT INTO localevento(localeve, tipo, inscricao) VALUES ( ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, localEvento.getLocalevento());
			preparedStatement.setString(2, localEvento.getTipo());
			preparedStatement.setLong(3, localEvento.getInscricao());
			
			preparedStatement.execute();
			connection.commit();
			
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	//consultando inner join
	public List<BeanLocalEvento> listaLocalEventos (Long idEvento){
		
		List<BeanLocalEvento> beanLocalEventos = new ArrayList<BeanLocalEvento>();
		
		String sql = "select localeve, tipo, nome from localevento as loc";
		sql += " inner join inscricao as insc ";
		sql += " on loc.inscricao = insc.id ";
		sql += " where insc.id = " + idEvento;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BeanLocalEvento beanLocalEvento = new BeanLocalEvento();
				beanLocalEvento.setLocal(resultSet.getString("localeve"));
				beanLocalEvento.setTipo(resultSet.getString("tipo"));
				beanLocalEvento.setNome(resultSet.getString("nome"));
				
				beanLocalEventos.add(beanLocalEvento);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return beanLocalEventos;
		
	}
	
	//deletando em cascata
	public void deletandoEvento(Long idEvento) {
		
		String sqlLocEve = "delete from localevento where inscricao = " + idEvento;
		String sqlInscricao = "delete from inscricao where id = " + idEvento;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlLocEve);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlInscricao);
			statement.executeUpdate();
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
