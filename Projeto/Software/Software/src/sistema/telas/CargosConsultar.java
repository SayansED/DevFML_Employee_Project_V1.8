package sistema.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sistema.Navegador;
import sistema.Sistema;
import sistema.entidades.Cargo;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CargosConsultar extends JPanel {

	Cargo cargoAtual;
	JLabel lblTitle, lblCargo;
	JTextField txtCampoCargo;
	JButton btnPesquisar, btnEditar, btnExcluir;
	ImageIcon imgIconPesquisar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\search03.png");
	ImageIcon imgIconDeletar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgIconEditar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");

	public CargosConsultar() {
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	private void criarComponentes() {
		setLayout(null);

		lblTitle = new JLabel("Consulta de Cargos", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));
		lblCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		txtCampoCargo = new JTextField();
		btnPesquisar = new JButton("Pesquisar Cargo", imgIconPesquisar);
		btnEditar = new JButton("Editar Cargo", imgIconEditar);
		btnExcluir = new JButton("Excluir Cargo", imgIconDeletar);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);

		lblTitle.setBounds(20, 20, 660, 40);
		lblCargo.setBounds(150, 120, 400, 20);
		txtCampoCargo.setBounds(150, 140, 400, 40);
		btnPesquisar.setBounds(560, 140, 200, 40);
		btnEditar.setBounds(180, 210, 180, 40);
		btnExcluir.setBounds(440, 210, 180, 40);

		add(lblTitle);
		add(lblCargo);
		add(txtCampoCargo);
		add(btnPesquisar);
		add(btnEditar);
		add(btnExcluir);

		setVisible(true);
	}

	private void criarEventos() {

		// Pesquisar
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listPesquisarCargos(txtCampoCargo.getText());
			}
		});

		// Editar
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ptxtCampoDigitado = txtCampoCargo.getText();
				if (!txtCampoCargo.getText().isEmpty())
					sqlAtualizarCargo(ptxtCampoDigitado);
				else {
					JOptionPane.showMessageDialog(null, "Preencha o campo", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Excluir
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validando campo e nome
				if (txtCampoCargo.getText().isEmpty() || txtCampoCargo.getText().length() <= 3) {
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				sqlDeletarCargo();
			}
		});
	}

	private void listPesquisarCargos(String ptxtCampoCargo) {
		Navegador.CargosPesquisa(ptxtCampoCargo);
	}

	private void sqlDeletarCargo() {
		int confirmacao = JOptionPane.showConfirmDialog(null,
				"Deseja realmente excluir o Cargo " + txtCampoCargo.getText() + "?", "Excluir",
				JOptionPane.YES_NO_OPTION);
		if (confirmacao == JOptionPane.YES_OPTION) {
			// conexão
			Conexao conexao = new Conexao();
			// instrucao SQL
			Statement statement = null;
			// resultados
			ResultSet resultados = null;

			String nomeCargo = txtCampoCargo.getText();

			try {
				// Conectando - Driver
				conexao.conectar();
				// Instrução SQL
				statement = conexao.criarStatement();
				String sqlDelete = "DELETE FROM T_CARGOS WHERE nome=?;";
				PreparedStatement preparedStatement = conexao.criarPreparedStatement(sqlDelete);
				preparedStatement.setString(1, nomeCargo);
				int resultado = preparedStatement.executeUpdate();
				if (resultado == 1) {
					String message = String.format("Cargo: %s\nDeletado com sucesso", nomeCargo);
					JOptionPane.showMessageDialog(null, message, "Excluir", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cargo não encontrado");
					return;
				}
				conexao.desconectar();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Cargo.");
				Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
			}
			return;
		}
	}

	private void sqlAtualizarCargo(String ptxtCampoCargo) {
		// conexão
		Conexao conexao = new Conexao();
		// instrucao SQL
		Statement statement;
		PreparedStatement preparedStatement;
		// resultados
		int resultado;

		try {
			conexao.conectar();
			String alterando = JOptionPane.showInputDialog("Digite o novo nome para ser inserido");
			String sqlUpdate = "UPDATE T_CARGOS SET nome = ? WHERE nome = ?;"; // Codigo de atualização
			preparedStatement = conexao.criarPreparedStatement(sqlUpdate);
			preparedStatement.setString(1, alterando);
			preparedStatement.setString(2, ptxtCampoCargo);
			resultado = preparedStatement.executeUpdate(); // Executando o UPDATE
			if (resultado == 0) {
				JOptionPane.showMessageDialog(null, "Cargo não encontrado: " + ptxtCampoCargo, "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				;
				return;
			}
			JOptionPane.showMessageDialog(null, "Cargo alterado com sucesso", "Mensagem",
					JOptionPane.INFORMATION_MESSAGE);
			Navegador.inicio();
			conexao.desconectar();

		} catch (SQLException ew) {
			ew.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}
}
