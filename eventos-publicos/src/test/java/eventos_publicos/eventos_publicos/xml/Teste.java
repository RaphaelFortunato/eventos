package eventos_publicos.eventos_publicos.xml;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import conexao.SingleConnection;
import dao.UserDaoEventos;
import model.UserModel;

public class Teste {

	@Test
	public void testeInserindo() {
		
		UserDaoEventos userDaoEventos = new UserDaoEventos();
		UserModel userModel = new UserModel();
		
		userModel.setNome("Fabio");
		userModel.setData("15/10/2010");
		
		userDaoEventos.cadastro(userModel);
		
	}
	
	@Test
	public void consultar() {
		
		UserDaoEventos userDaoEventos = new UserDaoEventos();
		
		try {
			List<UserModel> lista = new UserDaoEventos().lista();
			
			System.out.println("\r\nNomes");
			
			for (UserModel userModel : lista) {
				
				System.out.println(userModel.getNome());
				System.out.println("-------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
	}
	
	@Test
	public void consultarId() {
		
		
		UserDaoEventos daoEventos = new UserDaoEventos();
		
		try {
			UserModel model = daoEventos.buscaId(2L);
			System.out.println(model.getNome());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void atualiza() {
		
		try {
			
			UserDaoEventos userDaoEventos = new UserDaoEventos();
			UserModel consultaNoBanco = userDaoEventos.buscaId(2L);
			
			consultaNoBanco.setNome("Luana");
			consultaNoBanco.setData("10/10/2010");
			
			userDaoEventos.atualizar(consultaNoBanco);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void deletar() {
		
		try {
			
			UserDaoEventos eventos = new UserDaoEventos();
			eventos.deletando(2L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
