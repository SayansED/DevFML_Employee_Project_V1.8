package sistema.telas;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sistema.Navegador;

public class Inicio extends JPanel {
     
    JLabel lblTitle, lblDev, lblEmail, lblAuthor, lblVersion;
    ImageIcon imgBanner = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\banner01.png");
    ImageIcon imgBannerCentral = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\banner02.png");

    public Inicio(){
        criarComponentes();
        criarEventos();
        // Abrir 03
        Navegador.habilitaMenu();
    }

    private void criarComponentes(){
        setLayout(null);
       
        lblTitle = new JLabel("", JLabel.CENTER);
        lblAuthor = new JLabel("Autor: Eduardo Sayans", JLabel.LEFT);
        lblDev = new JLabel("Desenvolvido por DevFML", JLabel.LEFT);
        lblEmail = new JLabel("devfastml@outlook.com", JLabel.LEFT);
        lblVersion = new JLabel("Versão: 1.8", JLabel.LEFT);
        JLabel lblImgBanner = new JLabel(imgBanner);
        JLabel lblImgBannerCentral = new JLabel(imgBannerCentral);
        lblImgBanner.setToolTipText("Company Employees");
        lblImgBannerCentral.setToolTipText("Company Employees");
        
        lblTitle.setBounds(20, 100, 660, 40);
        lblDev.setBounds(20, 440, 660, 40);
        lblAuthor.setBounds(20, 460, 660, 40);
        lblEmail.setBounds(20, 480, 660, 40);
        lblVersion.setBounds(20, 500, 660, 40);
        lblImgBanner.setBounds(100, 15, 600, 100);
        // Adicionando de outra forma 
        lblImgBannerCentral.setSize(300, 250);
        lblImgBannerCentral.setLocation(250, 150);;

        add(lblTitle);
        add(lblDev);
        add(lblAuthor);
        add(lblEmail);
        add(lblVersion);
        add(lblImgBanner);
        add(lblImgBannerCentral);

        setVisible(true);
    }

    private void criarEventos() {
        
    }
}