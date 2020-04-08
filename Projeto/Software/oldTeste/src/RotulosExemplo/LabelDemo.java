package RotulosExemplo;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LabelDemo {

	public static void main(String[] args) {
		JLabel northLabel = new JLabel("North");
		
		ImageIcon labelIcon = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\usuario02.png");
		
		JLabel centerLabel = new JLabel(labelIcon);
		
		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(northLabel, BorderLayout.NORTH);
		application.add(centerLabel, BorderLayout.CENTER);
		
		application.setSize(800, 600);
		application.setVisible(true);
	}

}
