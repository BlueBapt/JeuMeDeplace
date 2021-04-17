import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.File;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JComboBox;
import javax.swing.JPanel;
public class Slide extends JFrame{

	public static JPanel pan = new JPanel();
	public JComboBox combo = new JComboBox<String>(LectureFichier.listerDoss("textures/"));
	public Bouton save = new Bouton("Sauvegarder");
	public Bouton charger = new Bouton("Charger");
	//public Bouton jouer = new Bouton("Jouer");

	public Slide(){
		
		this.setTitle("Textures");
		this.setVisible(true);
		this.setSize(250, 110);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);

		combo.setPreferredSize(new Dimension(100, 26));
    	combo.addActionListener(new ItemAction());
		pan.add(combo);
		pan.add(save);
		pan.add(charger);
		//pan.add(jouer);
	}
	class ItemAction implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
			Panneau.joueur.changerImage((String)combo.getSelectedItem());
			Ecran.pan.repaint();
    	}               
  	}


}
