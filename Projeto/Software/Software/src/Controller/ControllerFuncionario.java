package Controller;

import DAO.DAO;
import sistema.entidades.Funcionario;

public class ControllerFuncionario {
	
	DAO dao = new DAO();
	public boolean inserirUsuarioController(Funcionario pFuncionario){
		return this.dao.inserirFuncionario(pFuncionario);
	}
}
