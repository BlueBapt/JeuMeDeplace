import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.*;

public class Slide extends JFrame{

	public static String bg="1";
	public static JPanel pan = new JPanel();
	public static String selection ="b";

	public JMenuBar barreMenu = new JMenuBar();
	public JMenu fich = new JMenu("Fichier");
	public JMenuItem save = new JMenuItem("Sauvegarder");
	public JMenuItem charger = new JMenuItem("Charger");
	public JMenu poser = new JMenu("Blocs");
	public JMenuItem custom = new JMenuItem("Custom");
	public JMenuItem bases = new JMenuItem("Bases");

	public JComboBox blocs = new JComboBox<String>(LectureFichier.listerDoss("textures/"));
	public JComboBox spe = new JComboBox<String>(LectureFichier.listerDoss("textures/bases/objets/"));
	public JComboBox plan = new JComboBox<String>(new String[]{"1","2"});

	public Bouton nuit = new Bouton("zzz");
	public Bouton jouer = new Bouton("Jouer");

	public Slide(){
		
		this.setTitle("Blocs de base");
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
		spe.setPreferredSize(new Dimension(240, 26));
		ItemAction ita = new ItemAction();
    	blocs.addActionListener(ita);
		plan.addActionListener(ita);
		spe.addActionListener(ita);
		

		InteractionMenu itm = new InteractionMenu();
		save.addActionListener(itm);
		charger.addActionListener(itm);
		custom.addActionListener(itm);
		bases.addActionListener(itm);

		fich.add(save);
		fich.add(charger);
		poser.add(custom);
		poser.add(bases);
		barreMenu.add(fich);
		barreMenu.add(poser);

		this.setJMenuBar(barreMenu);

		pan.add(new JLabel("Plan :"));
		pan.add(plan);
		pan.add(nuit);
		pan.add(spe);
		//pan.add(jouer);

		
		this.setLocation(730,0);
		this.setVisible(true);
	}
	//action listener les sliders
	class ItemAction implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
			Object source = e.getSource(); // Récupération de la source de l'evt
			if(source.equals(blocs)){
				Panneau.joueur.changerImage((String)blocs.getSelectedItem());
				Ecran.pan.repaint();
			}else if (source.equals(plan)){
				bg=(String)plan.getSelectedItem();
				Ecran.pan.repaint();
			}else if(source.equals(spe)){
				Panneau.joueur.changerImage((String)spe.getSelectedItem());
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
					jop4.showMessageDialog(null, "Ce niveau n'existe pas","Erreur", JOptionPane.WARNING_MESSAGE);
					}
					break;
				case "Bases":
					if(!Slide.selection.equals("b")){
						main.sli.getContentPane().remove(blocs);
						main.sli.add(spe);
						main.sli.setTitle("Blocs de base");
						Slide.selection="b";
						Panneau.joueur.changerImage((String)spe.getSelectedItem());
						main.sli.repaint();
						main.fen.repaint();
					}
					break;

				case "Custom":
					if(!Slide.selection.equals("c")){
						main.sli.getContentPane().remove(spe);
						main.sli.add(blocs);
						main.sli.setTitle("Blocs customs");
						Slide.selection="c";
						Panneau.joueur.changerImage((String)blocs.getSelectedItem());
						main.sli.repaint();
						main.fen.repaint();
					}
					
					break;

			}
		}
	}

}
