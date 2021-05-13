import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
public class Slide extends JFrame{

	public static String bg="1";
	public static JPanel pan = new JPanel();
	public JComboBox combo = new JComboBox<String>(LectureFichier.listerDoss("textures/"));
	public JComboBox plan = new JComboBox<String>(new String[]{"1","2"});
	public Bouton save = new Bouton("Sauvegarder");
	public Bouton charger = new Bouton("Charger");
	public Bouton nuit = new Bouton("zzz");
	//public Bouton jouer = new Bouton("Jouer");

	public Slide(){
		
		this.setTitle("Menu");
		this.setSize(250, 110);
		try{
			this.setIconImage(ImageIO.read(new File("textures/bases/icone.png")));
		}catch (Exception e){
			System.exit(1);
		}
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);

		combo.setPreferredSize(new Dimension(100, 26));
		ItemAction ita = new ItemAction();
    	combo.addActionListener(ita);
		plan.addActionListener(ita);
		pan.add(combo);
		pan.add(save);
		pan.add(charger);
		pan.add(new JLabel("Plan :"));
		pan.add(plan);
		pan.add(nuit);
		//pan.add(jouer);

		this.setVisible(true);
	}
	class ItemAction implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
			Object source = e.getSource(); // Récupération de la source de l'evt
			if(source.equals(combo)){
				Panneau.joueur.changerImage((String)combo.getSelectedItem());
				Ecran.pan.repaint();
			}else{
				bg=(String)plan.getSelectedItem();
				Ecran.pan.repaint();
			}
			
    	}               
  	}


}
