package sistema.telas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sqlite.Conexao;

public class FuncionariosPesquisa extends JFrame {

	DefaultListModel<Funcionario> listFuncionariosModel = new DefaultListModel();
	JList<Funcionario> listFuncionarios;
	String txtCampoFuncionario;
	JLabel lblTitle;
	JButton btnVoltar, btnEditar, btnExcluir;
	ImageIcon imgIconDeletar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgIconEditar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");
	ImageIcon imgIconBack = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\back02.png");
	Container container;

	public FuncionariosPesquisa(String ptxtFuncionario) {
		this.txtCampoFuncionario = ptxtFuncionario;
		criarComponentes();
		criarEventos();
	}

	private void criarComponentes() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		container = getContentPane();

		lblTitle = new JLabel("Consulta de Funcion�rio", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));
		btnVoltar = new JButton("Voltar", imgIconBack);
		btnEditar = new JButton("Editar", imgIconEditar);
		btnExcluir = new JButton("Excluir", imgIconDeletar);
		btnEditar.setEnabled(false);

		add(lblTitle);

		listFuncionariosModel = new DefaultListModel();
		listFuncionarios = new JList();
		listFuncionarios.setModel(listFuncionariosModel);
		listPesquisarFuncionarios();

		add(btnVoltar);
		add(btnEditar);
		add(btnExcluir);
	}

	private void criarEventos() {
		// Editar
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				String funcionarioSelecionado = "";
				// Recebo um objeto
				Funcionario listFuncionarioSelecionado = (Funcionario) listFuncionarios.getSelectedValue();
				if (listFuncionarioSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					funcionarioSelecionado = listFuncionarioSelecionado.getFuncionarioNome();
					// Depois de selecionar, passa o cargo para o metodo
					// Navegador navegar = new Navegador();
					// navegar.funcionariosEditar(funcionarioSelecionado);

					Funcionario fun = new Funcionario();
					fun.setFuncionarioNome(funcionarioSelecionado);
					System.out.println(fun.getFuncionarioNome());

				}
				voltaFrame();
				 */
			}
		});

		// Excluir
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String funcionarioSelecionado;
				// Recebo um objeto
				Funcionario listFuncionarioSelecionado = (Funcionario) listFuncionarios.getSelectedValue();
				if (listFuncionarioSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					funcionarioSelecionado = listFuncionarioSelecionado.getFuncionarioNome();
					// Depois de selecionar, passa o cargo para o metodo
					sqlDeletarFuncionario(funcionarioSelecionado);
				}
				voltaFrame();
			}
		});

		// Voltar
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltaFrame();
			}
		});
	}

	private void voltaFrame() {
		Navegador.funcionariosConsultar();
		Navegador.funcionariosList.setVisible(false);
	}

	private void listPesquisarFuncionarios() {
		// Conexao BD
		Conexao conexao = new Conexao();
		// Statement
		Statement statement = null;
		// resultados
		ResultSet resultSet = null;
		try {
			conexao.conectar();
			String sqlSelect = "SELECT * FROM T_FUNCIONARIOS WHERE nome LIKE '%" + txtCampoFuncionario + "%';";
			statement = conexao.criarStatement();
			resultSet = statement.executeQuery(sqlSelect);
			listFuncionariosModel.clear();
			while (resultSet.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setFuncionarioId(resultSet.getInt("id"));
				funcionario.setFuncionarioNome(resultSet.getString("nome"));
				funcionario.setFuncionarioSobrenome(resultSet.getString("sobrenome"));
				listFuncionariosModel.addElement(funcionario);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar funcionarios");
			Logger.getLogger(CargosConsultar.class.getName()).log(Level.SEVERE, null, ex);
		}
		listFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		listFuncionarios.setVisibleRowCount(10);
		add(new JScrollPane(listFuncionarios));
	}

	private void sqlDeletarFuncionario(String pFuncionarioSelecionado) {
		int confirmacao = JOptionPane.showConfirmDialog(null,
				"Deseja realmente excluir o Funcion�rio: " + pFuncionarioSelecionado + "?", "Excluir",
				JOptionPane.YES_NO_OPTION);
		if (confirmacao == JOptionPane.YES_OPTION) {
			// conex�o
			Conexao conexao = new Conexao();
			// instrucao SQL
			Statement statement = null;
			// resultados
			ResultSet resultados = null;
			try {
				// Conectando - Driver
				conexao.conectar();
				// Instru��o SQL
				statement = conexao.criarStatement();
				String sqlDelete = "DELETE FROM T_FUNCIONARIOS WHERE nome=?;";
				PreparedStatement preparedStatement = conexao.criarPreparedStatement(sqlDelete);
				preparedStatement.setString(1, pFuncionarioSelecionado);
				int resultado = preparedStatement.executeUpdate();
				if (resultado == 1) {
					String message = String.format("Funcion�rio: %s\nDeletado com sucesso", pFuncionarioSelecionado);
					JOptionPane.showMessageDialog(null, message, "Excluir", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado");
					return;
				}
				conexao.desconectar();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Funcion�rio.");
				Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
			}
			return;
		}
	}
}
