package sqlite;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import sqlite.Conexao;

public class CriarBancoDeDados {

	
	// Adapatação
	//private Conexao conexao = new Conexao();

	private final Conexao conexao;

    //Construtor da classe
  	//Pq está usando uma variavel do tipo "final"
    public CriarBancoDeDados(Conexao pConexaoSQLite) {
        this.conexao = pConexaoSQLite;
    }
    
	//Metodo
	public void createTableFuncionarios() {
		//Codigo para crias tabela
		String sql = "CREATE TABLE IF NOT EXISTS T_FUNCIONARIOS"
				+ "("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome text NOT NULL, "
				+ "sobrenome text NOT NULL, "
				+ "dataNascimento text NOT NULL, "
				+ "email text NOT NULL, "
				+ "cargo text NOT NULL, "
				+ "salario text NOT NULL"
				+ ");";
		// Executando o sql criar tabela
		boolean conectou = false;

		try {
			//conectou = this.conexaoSQLite.conectar(); // Chama o metodo de outra classe, para conectar-se do BD
			conectou = this.conexao.conectar();
			Statement stmt = this.conexao.criarStatement(); // Estancia o Statement para usa-lo
			stmt.execute(sql);
		} 
		catch (SQLException e) { //Erro na tabela
			JOptionPane.showMessageDialog(null, "ERRO NA CRIAÇÃO DA TEBELA FUNCIONÁRIO", "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			if(conectou){
				this.conexao.desconectar(); // Chama o metodo de outra classe, para desconectar-se do BD
			}
		}

	}

	public void createTableCargos() {
		String sql = "CREATE TABLE IF NOT EXISTS T_CARGOS"
				+ "("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome text NOT NULL"
				+ ");";
		boolean conectou = false;
		try {
			conectou = this.conexao.conectar();
			Statement stmt = this.conexao.criarStatement();
			stmt.execute(sql);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO NA CRIAÇÃO DA TEBELA CARGOS", "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			if(conectou) {
				this.conexao.desconectar();
			}
		}
	}

}
