import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Color;

public class Slide extends JFrame{

	public static String bg="1";
	public static JPanel pan = new JPanel();
	public static String selection ="b";

	public JMenuBar barreMenu = new JMenuBar();
	public JMenu fich = new JMenu("Fichier");
	public JMenuItem save = new JMenuItem("Sauvegarder");
	public JMenuItem charger = new JMenuItem("Charger");

	public JCheckBox isSolid = new JCheckBox("Solide");
	public JCheckBox isBreakable = new JCheckBox("Cassable");

	public JComboBox blocs = new JComboBox<String>(LectureFichier.listerDoss("textures/"));
	public JComboBox plan = new JComboBox<String>(new String[]{"1","2"});

	public Bouton nuit = new Bouton("zzz");
	public Bouton jouer = new Bouton("Jouer");

	public Slide(){
		
		this.setTitle("Menu construction");
		try{
			this.setIconImage(ImageIO.read(new File("textures/bases/icone.png")));
		}catch (Exception e){
			System.exit(1);
		}
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan.setPreferredSize(new Dimension(250,720));
		this.setContentPane(pan);
		this.pack();

		blocs.setPreferredSize(new Dimension(240, 26));
		ItemAction ita = new ItemAction();
    	blocs.addActionListener(ita);
		plan.addActionListener(ita);
		

		InteractionMenu itm = new InteractionMenu();
		save.addActionListener(itm);
		charger.addActionListener(itm);

		fich.add(save);
		fich.add(charger);
		barreMenu.add(fich);

		this.setJMenuBar(barreMenu);

		pan.add(new JLabel("Plan :"));
		pan.add(plan);
		pan.add(nuit);
		pan.add(blocs);


		isSolid.setPreferredSize(new Dimension(235,15));
		isBreakable.setPreferredSize(new Dimension(235,15));
		isSolid.setBackground(new Color(150,150,150));
		isBreakable.setBackground(new Color(150,150,150));
		isSolid.setSelected(true);
		pan.add(isSolid);
		pan.add(isBreakable);
		//pan.add(jouer);

		
		this.setLocation(730,0);
		this.setVisible(true);
	}
	//action listener les sliders
	class ItemAction implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
			Object source = e.getSource(); // R??cup??ration de la source de l'evt
			if(source.equals(blocs)){
				Panneau.joueur.changerImage((String)blocs.getSelectedItem());
				Ecran.pan.repaint();
			}else if (source.equals(plan)){
				bg=(String)plan.getSelectedItem();
				Ecran.pan.repaint();
			}
			
    	}               
  	}

	class InteractionMenu implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			JMenuItem source = (JMenuItem)event.getSource();
			switch(source.getText()){
				case "Sauvegarder":
					JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
					String nom = jop.showInputDialog(null, "Sous quel nom sauvegarder?", "Sauvegarder", JOptionPane.QUESTION_MESSAGE);
					Saver save = new Saver();
					if(nom!=null){
						save.sauvegarder(Panneau.hey,nom);
						jop2.showMessageDialog(null, "Fichier sauvegarde sous : " + nom, "Fini!", JOptionPane.INFORMATION_MESSAGE);
					}else{
						jop2.showMessageDialog(null, "Annule.","Erreur", JOptionPane.WARNING_MESSAGE);
					}
					break;
				case "Charger":

					JOptionPane jop3 = new JOptionPane(), jop4 = new JOptionPane();
					String nomC = jop3.showInputDialog(null, "Quel niveau charger?", "Charger", JOptionPane.QUESTION_MESSAGE);
					try{
						if(nomC!=null){
							LectureFichier.ChargerNiveau(nomC);
							Ecran.pan.repaint();
							jop4.showMessageDialog(null, "Fichier charge (" + nomC+")", "Fini!", JOptionPane.INFORMATION_MESSAGE);
						}else{
							jop4.showMessageDialog(null, "Annule.","Erreur", JOptionPane.WARNING_MESSAGE);
						}
					}catch (Exception e){
						e.printStackTrace();
						jop4.showMessageDialog(null, "Ce niveau n'existe pas :\""+nomC+"\"","Erreur", JOptionPane.WARNING_MESSAGE);
					}
					break;

			}
		}
	}

}
