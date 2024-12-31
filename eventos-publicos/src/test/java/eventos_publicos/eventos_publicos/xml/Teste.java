package eventos_publicos.eventos_publicos.xml;

import java.sql.Connection;

import org.junit.Test;

import conexao.SingleConnection;
import dao.UserDaoEventos;
import model.UserModel;

public class Teste {

	@Test
	public void testeInserindo() {
		
		UserDaoEventos userDaoEventos = new UserDaoEventos();
		UserModel userModel = new UserModel();
		
		userModel.setNome("Laila");
		userModel.setData("25/08/2019");
		
		userDaoEventos.cadastro(userModel);
		
	}
	
}
