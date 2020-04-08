package sistema;

import javax.swing.JFrame; // Fornece recursos básicos de janela
import javax.swing.JPanel; // Fornece caixa de diálogos
import javax.swing.SwingConstants; // Constantes comuns utilizadas com swing
import javax.swing.JLabel; // Exibe texto e imagens

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon; // Carrega imagens
import javax.swing.JButton;

import sistema.telas.*;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;
// I am not responsible for any damage caused by installing the software on your hardware..
/**
 * Esse Software foi desenvolvido por <b>DevFML</b>.
 * @author Eduardo Sayans
 * 
 * Versão do Software: V1.8
 * @version 1.8
 * 
 * 
 * </br>Para entrar em contato com o desenvolvedor pode-se usar o e-mail: 
 * devfastml@outlook.com
 * Especificar no assunto de e-mail o motivo do contato.
 * 
 * 
 * @since ProjetoV1.8
 */
public class Sistema{

	/* Definimos uma variável do tipo JPanel, que armazenará a tela atual do nosso sistema. 
	Será do tipo estática, pois vamos utilizá-la em vários momentos. */
	public static JPanel JPtela;
	/* Definimos uma variável do tipo JFrame, que será a janela da nossa aplicação. 
	Será do tipo estática, pois vamos utilizá-la em vários momentos */
	public static JFrame JFframe;


	public static void main(String[] args){
		criarComponentes();

		String strIconWay = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\system03.png";
		Image iconSystem = Toolkit.getDefaultToolkit().getImage(strIconWay);
		JFframe.setIconImage(iconSystem);

	}
	/** 
	 *Criação dos componentes de tela
	 */
	private static void criarComponentes(){

		/* Instanciamos o objeto JFrame e definimos algumas configurações */
		JFframe = new JFrame("Sistema");
		JFframe.setSize(800, 600);
		JFframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFframe.setLocationRelativeTo(null);

		Conexao conexao = new Conexao();
		conexao.conectar();
		CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
		criarBancoDeDados.createTableFuncionarios();
		criarBancoDeDados.createTableCargos();
		conexao.desconectar();

		// Abrir 01
		/** Método para criação do menu*/
		Navegador.login();
	}
}