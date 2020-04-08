package sistema.telas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import sistema.Navegador;
import sistema.Sistema;

public class Login extends JPanel {

    JLabel lblUsuario, lblSenha;
    JTextField txtUsuario;
    JPasswordField ptxtSenha;
    JButton btnEntrar, btnSair;
    ImageIcon imgLogin = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\login02.png");
    ImageIcon imgExit = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\exit01.png");
    ImageIcon imgUser = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\user01.png");
    ImageIcon imgBanner = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\banner01.png");
    

    public Login(){
        criarComponentes();
        criarEventos();
    }

    private void criarComponentes() {

        // Nenhum tipo de layout
        setLayout(null);

        /* Os componentes estão sendo inicializados com textos e alinhamentos. O botão apenas com seu texto*/
        JLabel labelTitulo = new JLabel("Seja Bem vindo ao Sistema", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 18));
        JLabel lblImgUser = new JLabel(imgUser);
        JLabel lblImgBanner = new JLabel(imgBanner);
        lblUsuario = new JLabel("Usuário", JLabel.LEFT);
        txtUsuario = new JFormattedTextField();
        lblSenha = new JLabel("Senha", JLabel.LEFT);
        ptxtSenha = new JPasswordField();
        btnEntrar = new JButton("Login", imgLogin);
        btnEntrar.setToolTipText("Realizar Login");
        btnSair = new JButton("Sair", imgExit);
        btnSair.setToolTipText("Sair do Sistema");

        // Posicionamentos e o tamanho dos componentes na tela
        lblImgUser.setBounds(312, 150, 625, 300);
        lblImgBanner.setBounds(100, 15, 600, 100);
        labelTitulo.setBounds(40, 150, 660, 40);
        lblUsuario.setBounds(250, 220, 200, 20);
        txtUsuario.setBounds(250, 240, 230, 40);
        lblSenha.setBounds(250, 280, 200, 20);
        ptxtSenha.setBounds(250, 300, 230, 40);
        btnEntrar.setBounds(250, 350, 200, 40);
        btnSair.setBounds(250, 400, 200, 40);

        // Adcionando componentes à tela
        add(lblImgBanner);
        add(lblImgUser);
        add(labelTitulo);
        add(lblUsuario);
        add(txtUsuario);
        add(lblSenha);
        add(ptxtSenha);
        add(btnEntrar);
        add(btnSair);

        // Visibilidade do Frame
        setVisible(true);
        
    }

    private void criarEventos(){
    	try {
    		// Login
            btnEntrar.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    // Validando as credenciais
                    if(txtUsuario.getText().equals("admin") && new String(ptxtSenha.getPassword()).equals("admin")) {
                        // Abrir 02
                        Navegador.inicio();
                    }
                    else if(txtUsuario.getText().isEmpty() || ptxtSenha.getText().isEmpty()) {
                    	JOptionPane.showMessageDialog(null, "Preencha todos os campo", "Mensagem", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Login ou Senha incorretos", "Mensagem", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            ptxtSenha.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO Auto-generated method stub
    				if(e.getSource() == ptxtSenha) {
    					if(txtUsuario.getText().equals("admin") && new String(ptxtSenha.getPassword()).equals("admin")) {
    						Navegador.inicio();
    					}
    					else if(txtUsuario.getText().isEmpty() || ptxtSenha.getText().isEmpty()) {
    	                	JOptionPane.showMessageDialog(null, "Preencha todos os campo", "Mensagem", JOptionPane.WARNING_MESSAGE);
    	                }
    	                else {
    	                    JOptionPane.showMessageDialog(null, "Login ou Senha incorretos", "Mensagem", JOptionPane.ERROR_MESSAGE);
    	                }
    				}
    			}
    		});
            
            // Exit
            btnSair.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		System.exit(0);
            	}
            });
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar.");
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
			System.exit(0);
		}
    }
}