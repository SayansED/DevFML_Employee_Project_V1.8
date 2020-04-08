package JListExemplo;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListFrame extends JFrame {

	private final JList<String> colorJList; // Lista para exibir cores
	private static final String[] colorNames = {"Black", "Blue", "Cyan", "Dark Gray", "Gray",
			"Green", "Light Gray"};
	private static final Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
			Color.GRAY, Color.GREEN, Color.LIGHT_GRAY};

	// Construtor ListFrame adciona JScrollPane que contém JList ao JFrame
	public ListFrame() {
		super("List Test");
		setLayout(new FlowLayout());

		colorJList = new JList<String>(colorNames); // Lista de colorNames
		colorJList.setVisibleRowCount(5); // Exibe 5 linhas de uma vez

		// Não permite mútiplas seleções
		colorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Adciona um JScrollPane que contém JList ao frame
		add(new JScrollPane(colorJList));

		colorJList.addListSelectionListener(new ListSelectionListener() {
					// Trata eventos de seleção de lista
					@Override
					public void valueChanged(ListSelectionEvent e) {
						getContentPane().setBackground(
								colors[colorJList.getSelectedIndex()]);
					}
				}
				);
	}
}
