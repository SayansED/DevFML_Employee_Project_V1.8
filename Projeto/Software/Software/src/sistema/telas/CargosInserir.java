package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import Controller.ControllerCargo;
import Controller.ControllerFuncionario;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;

public class CargosInserir extends JPanel {

	JLabel lblTitulo, lblNomeCargo, lblIdCargo;
	JTextField txtNomeCargo, txtIdCargo;
	JButton btnGravar;
	ImageIcon imgSalvar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\save01.png");

	public CargosInserir() {
		criarComponentes();
		criarEventos();
	}

	private void criarComponentes() {

		setLayout(null);

		lblTitulo = new JLabel("Cadastro de Cargo", JLabel.CENTER);
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.PLAIN, 20));
		lblNomeCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		txtNomeCargo = new JTextField();
		lblIdCargo = new JLabel("Id do cargo", JLabel.LEFT);
		txtIdCargo = new JTextField();
		btnGravar = new JButton("Adcionar", imgSalvar);
		txtIdCargo.setEnabled(false);
		txtIdCargo.setText("ID gerado automaticamente pelo banco de dados");

		lblTitulo.setBounds(20, 20, 660, 40);
		lblNomeCargo.setBounds(150, 120, 400, 20);
		txtNomeCargo.setBounds(150, 140, 400, 40);
		lblIdCargo.setBounds(150, 180, 400, 20);
		txtIdCargo.setBounds(150, 200, 400, 40);
		btnGravar.setBounds(250, 380, 150, 40);

		//campoIdCargo.enable(false);;

		add(lblTitulo);
		add(lblNomeCargo);
		add(txtNomeCargo);
		add(txtIdCargo);
		add(lblIdCargo);
		add(btnGravar);

		setVisible(true);
	}

	public void criarEventos() {
		btnGravar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Cargo novoCargo = new Cargo();
				novoCargo.setCargoNome(txtNomeCargo.getText());
				// Validando campo
				if (txtNomeCargo.getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Preencha todos os campo", "Validação", JOptionPane.WARNING_MESSAGE);
				sqlInserirCargo(novoCargo);
			}
		});
	}

	private void sqlInserirCargo(Cargo novoCargo) {

		Conexao conexao = new Conexao();
		PreparedStatement preparedStatement = null;
		CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);

		// Validando nome
		if(txtNomeCargo.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor inserir o nome completo");
			return;
		}
		
		try {
			String nomeCargo = txtNomeCargo.getText();
			novoCargo.setCargoNome(nomeCargo);
			
			// Modelo DAO
			ControllerCargo controllerCargo = new ControllerCargo();
			controllerCargo.inserirCargoController(novoCargo);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar.");
			Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
