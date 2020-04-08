package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Controller.ControllerFuncionario;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;

public class FuncionariosInserir extends JPanel {

	JLabel lblTitulo, llblNome, lblSobrenome, lblDataNascimento, lblEmail, lblCargo, lblSalario, lblId;
	JTextField txtNome, txtSobrenome, txtEmail, txtCargo, txtId;
	JFormattedTextField ftxtDataNascimento, ftxtSalario;
	//JComboBox<Cargo> comboboxCargo;
	JButton btnGravar;
	MaskFormatter mkfSalario;
	ImageIcon imgSalvar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\save01.png");

	public FuncionariosInserir(){
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	private void criarComponentes() {
		setLayout(null);

		lblTitulo = new JLabel("Cadastro de Funcionario", JLabel.CENTER);
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.PLAIN, 20));
		lblId = new JLabel("Id:", JLabel.LEFT);
		txtId = new JTextField();
		llblNome = new JLabel("Nome:", JLabel.LEFT);
		txtNome = new JTextField();     
		lblSobrenome = new JLabel("Sobrenome:", JLabel.LEFT);
		txtSobrenome = new JTextField();     
		lblDataNascimento = new JLabel("Data de Nascimento:", JLabel.LEFT);
		ftxtDataNascimento = new JFormattedTextField();
		try {
			MaskFormatter dateMask= new MaskFormatter("##/##/####");
			dateMask.install(ftxtDataNascimento);
		} catch (ParseException ex) {
			Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}
		lblEmail = new JLabel("E-mail:", JLabel.LEFT);
		txtEmail = new JTextField();     
		lblCargo = new JLabel("Cargo:", JLabel.LEFT);
		//comboboxCargo = new JComboBox();
		txtCargo = new JTextField();
		lblSalario = new JLabel("Salário:", JLabel.LEFT);
		DecimalFormat formatter = new DecimalFormat("0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		ftxtSalario = new JFormattedTextField(formatter);
		//campoSalario.setValue(0);
		btnGravar = new JButton("Adicionar", imgSalvar);
		txtId.setEnabled(false);
		txtId.setText("ID gerado automaticamente pelo banco de dados.");

		lblTitulo.setBounds(20, 20, 660, 40);
		lblId.setBounds(150, 100, 400, 20);
		txtId.setBounds(150, 120, 400, 40);

		llblNome.setBounds(150, 160, 400, 20);
		txtNome.setBounds(150, 180, 400, 40);
		lblSobrenome.setBounds(150, 220, 400, 20);
		txtSobrenome.setBounds(150, 240, 400, 40);
		lblDataNascimento.setBounds(150, 280, 400, 20);
		ftxtDataNascimento.setBounds(150, 300, 400, 40);
		lblEmail.setBounds(150, 340, 400, 20);
		txtEmail.setBounds(150, 360, 400, 40);
		lblCargo.setBounds(150, 400, 400, 20);
		//comboboxCargo.setBounds(150, 340, 400, 40);
		txtCargo.setBounds(150, 420, 400, 40);
		lblSalario.setBounds(150, 460, 400, 20);
		ftxtSalario.setBounds(150, 480, 400, 40);
		btnGravar.setBounds(560, 480, 150, 40); 

		add(lblTitulo);
		add(lblId);
		add(txtId);
		add(llblNome);
		add(txtNome);
		add(lblSobrenome);
		add(txtSobrenome);
		add(lblDataNascimento);
		add(ftxtDataNascimento);
		add(lblEmail);
		add(txtEmail);
		add(lblCargo);
		//add(comboboxCargo);
		add(txtCargo);
		add(lblSalario);
		add(ftxtSalario);
		add(btnGravar);

		sqlCarregarCargos();

		setVisible(true);
	}

	private void criarEventos() {
		btnGravar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Funcionario novoFuncionario = new Funcionario();
				novoFuncionario.setFuncionarioNome(txtNome.getText());
				novoFuncionario.setFuncionarioSobrenome(txtSobrenome.getText());
				novoFuncionario.setFuncionarioDataNascimento(ftxtDataNascimento.getText());
				novoFuncionario.setFuncionarioEmail(txtEmail.getText());
				novoFuncionario.setFuncionarioCargo(txtCargo.getText());
				novoFuncionario.setFuncionarioSalario(ftxtSalario.getText().replace(",", "."));
				sqlInserirFuncionario(novoFuncionario);   
			}
		});
	}

	private void sqlCarregarCargos() {  
		//Navegador.inicio();
	}

	private void sqlInserirFuncionario(Funcionario novoFuncionario) {

		if(txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtEmail.getText().isEmpty() || 
				txtCargo.getText().isEmpty() || ftxtDataNascimento.getText().isEmpty() || 
				ftxtSalario.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campo", "Validação", JOptionPane.WARNING_MESSAGE);
			return;
		}

		/* Não será mais preciso usar, porque o id será gerado automaticamente
		// Validando Id
		if(campoId.getText().length() == 0 && campoId.getText().length() > 2) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha o id corretamente.");
			return;
		}
		*/

		// validando nome
		if(txtNome.getText().length() <= 3){
			JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
			return;
		}

		// validando sobrenome
		if(txtSobrenome.getText().length() <= 3){
			JOptionPane.showMessageDialog(null, "Por favor, preencha o sobrenome corretamente.");
			return;
		}

		// validando Salario
		/*
        if(Double.parseDouble(campoSalario.getText().replace(",", ".")) <= 100){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o salário corretamente.");
            return;
        }
		 */

		// Validando Salario
		if (ftxtSalario.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha o salário corretamente.");
			return;
		}

		// validando email
		/*
        Boolean emailValidado = false;
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(campoEmail.getText());
        emailValidado = m.matches();

        if(!emailValidado){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o email corretamente.");
            return;
        }
		 */
		
		// conexão
		Conexao conexao = new Conexao();
		CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
		// instrucao SQL
		PreparedStatement preparedStatement = null;
		// resultados
		ResultSet resultado = null;

		try {
			String nomeFuncionario = txtNome.getText();
			novoFuncionario.setFuncionarioNome(nomeFuncionario);
			String sobrenomeFuncionario = txtSobrenome.getText();
			novoFuncionario.setFuncionarioSobrenome(sobrenomeFuncionario);
			String dataNascimentoFuncionario = ftxtDataNascimento.getText();
			novoFuncionario.setFuncionarioDataNascimento(dataNascimentoFuncionario);
			String emailFuncionario = txtEmail.getText();
			novoFuncionario.setFuncionarioEmail(emailFuncionario);
			String cargoFuncionario = txtCargo.getText();
			novoFuncionario.setFuncionarioCargo(emailFuncionario);
			String salarioFuncionario = ftxtSalario.getText();
			novoFuncionario.setFuncionarioSalario(salarioFuncionario);
			
			// Modelo DAO
			ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
			controllerFuncionario.inserirUsuarioController(novoFuncionario);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar.");
			Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
