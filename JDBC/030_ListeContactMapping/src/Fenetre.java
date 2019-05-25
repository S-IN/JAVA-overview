// ==========================================================================
// Projet MAPPING : Vue (classe Fenetre)
// ==========================================================================

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import metierMapping.*;
import utilitairesMG.divers.*;

public class Fenetre extends JFrame implements ListSelectionListener,
                                               MouseListener,
                                               ActionListener
{

// ---------------------------------------------------------------------
// PROPRIETES
// ---------------------------------------------------------------------
    private JPanel panneauFond;

// ---------------------------------------------------------------------
// Zone centrale avec ses composants
// ---------------------------------------------------------------------
    private JPanel panneauCentre;

    private JPanel panneauContacts;
    private JLabel enteteContacts;
    private JList jListContacts;
    private DefaultListModel modeleListeContacts;
    private JScrollPane defileurListeContacts;

    private JPopupMenu menuPopup;
    private JMenuItem itemRafraichir;

    private JPanel panneauVersements;
    private JLabel enteteVersements;
    private JTextArea zoneVersements;
    private JScrollPane defileurVersements;

// ---------------------------------------------------------------------
// Zone de message (sud)
// ---------------------------------------------------------------------
    private JPanel panneauSud;
    private JLabel message;

// ---------------------------------------------------------------------
// Vecteur des contacts de la fenetre
// ---------------------------------------------------------------------
    private Vector<Contact> listeContacts;

// ---------------------------------------------------------------------
// CONSTRUCTEUR
// ---------------------------------------------------------------------
    public Fenetre(String s, Vector<Contact> listeContacts)
    {
        super(s);
        this.listeContacts = listeContacts;
        prepareFenetre();
    }

    public final void prepareFenetre()
    {
        addWindowListener(new EcouteWindowClosing());

// ---------------------------------------------------------------------
// Aspect graphique
// ---------------------------------------------------------------------
        setPreferredSize(new Dimension(800, 300));

        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());

// ---------------------------------------------------------------------
// Creation du panneau du centre :
// ---------------------------------------------------------------------
        panneauCentre = new JPanel();
        panneauCentre.setLayout(new GridLayout(1, 2));

// ---------------------------------------------------------------------
// Creation du panneau de la liste des contacts :
// ---------------------------------------------------------------------
        panneauContacts = new JPanel();
        panneauContacts.setLayout(new BorderLayout());

        enteteContacts = new JLabel(" CONTACTS");
        enteteContacts.setFont(new Font("Calibri", Font.BOLD, 24));
        panneauContacts.add(enteteContacts, BorderLayout.NORTH);

// ---------------------------------------------------------------------
// JList des contacts et son modele :
// ---------------------------------------------------------------------
        jListContacts = new JList();
        jListContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListContacts.setFont(new Font("Courier new", Font.PLAIN, 12));

// ---------------------------------------------------------------------
// Ecouteurs : Changement de selection dans la JList
//             Evenement souris pour le menu contextuel
// ---------------------------------------------------------------------
        jListContacts.addListSelectionListener(this);
        jListContacts.addMouseListener(this);

// ---------------------------------------------------------------------
// Modele de la JList
// ---------------------------------------------------------------------
        modeleListeContacts = new DefaultListModel();
        Contact contact;

        for (int i = 0; i < listeContacts.size(); i++)
        {
            contact = listeContacts.elementAt(i);
            modeleListeContacts.addElement(chaineContact(contact));
        }
        jListContacts.setModel(modeleListeContacts);

// ---------------------------------------------------------------------
// Menu Popup de la JList
// ---------------------------------------------------------------------
        menuPopup = new JPopupMenu();
        itemRafraichir =
            new JMenuItem("Rafraichir", new ImageIcon("bal21.gif"));
        itemRafraichir.addActionListener(this);
        menuPopup.add(itemRafraichir);

// ---------------------------------------------------------------------
// Defileur (JScrollPane) de la liste des contacts
// ---------------------------------------------------------------------
        defileurListeContacts = new JScrollPane(jListContacts);
        panneauContacts.add(defileurListeContacts);

// ---------------------------------------------------------------------
// Mise en place du panneau des contacts dans le panneau du centre :
// ---------------------------------------------------------------------
        panneauCentre.add(panneauContacts);

// ---------------------------------------------------------------------
// Creation du panneau de la liste des versements :
// ---------------------------------------------------------------------
        panneauVersements = new JPanel();
        panneauVersements.setLayout(new BorderLayout());
        enteteVersements = new JLabel(" VERSEMENTS");
        enteteVersements.setFont(new Font("Calibri", Font.BOLD, 24));
        panneauVersements.add(enteteVersements, BorderLayout.NORTH);

// ---------------------------------------------------------------------
// Les versements sont affiches dans une zone de texte non editable
// ---------------------------------------------------------------------
        zoneVersements = new JTextArea();
        zoneVersements.setEditable(false);
        zoneVersements.setFont(new Font("Courier new", Font.PLAIN, 12));

// ---------------------------------------------------------------------
// Defileur (JScrollPane) de la liste des versements
// ---------------------------------------------------------------------
        defileurVersements = new JScrollPane(zoneVersements);
        panneauVersements.add(defileurVersements);

// ---------------------------------------------------------------------
// Mise en place du panneau des versements dans le panneau du centre :
// ---------------------------------------------------------------------
        panneauCentre.add(panneauVersements);

// ---------------------------------------------------------------------
// Creation du panneau de message (sud) :
// ---------------------------------------------------------------------
        panneauSud = new JPanel();

        message = new JLabel(" ");
        message.setFont(new Font("Calibri", Font.BOLD, 16));
        message.setForeground(Color.red);

        panneauSud.add(message);

// ---------------------------------------------------------------------
// Remplissage du panneau de fond :
// ---------------------------------------------------------------------
        panneauFond.add(panneauCentre);
        panneauFond.add(panneauSud, BorderLayout.SOUTH);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }

// ---------------------------------------------------------------------
// METHODES
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// Creation d'une chaine correspondant a un contact pour affichage dans
// la JList
// ---------------------------------------------------------------------
    public String chaineContact(Contact contact)
    {
        String chaineContact = "";

        chaineContact =
            Chaine.placeSousChaine(chaineContact,
                "" + contact.getNumero(), 5, 'g');
        if(contact.getNom() != null)
        {
            chaineContact =
            Chaine.placeSousChaine(chaineContact,
                contact.getNom(), 8, 'd');
        }

        Secteur s = contact.getSecteur();
        if (s != null)
        {
            chaineContact =
                Chaine.placeSousChaine(chaineContact,
                    "(" + s.getLibelle() + ")", 35, 'd');
        }

        return chaineContact;
    }

// ---------------------------------------------------------------------
// Affichage d'un contact et de ses versements.
// ---------------------------------------------------------------------
    public void afficheContact(Contact contact)
    {
        int i = jListContacts.getSelectedIndex();

        modeleListeContacts.setElementAt(chaineContact(contact), i);
        afficheVersements(contact);
    }

// ---------------------------------------------------------------------
// Affichage des versements d'un contact.
// Constitution de la chaine a partir des donnees de chaque versement,
// et affichage dans la JTextArea (zoneVersements).
// ---------------------------------------------------------------------
    public void afficheVersements(Contact contact)
    {
        Versement versement;
        Vector<Versement> listeVersements;

        zoneVersements.setText("");
        String chaineVersements = "";

        listeVersements = contact.getListeVersements();

        if (listeVersements != null)
        {
            for (int i = 0; i < listeVersements.size(); i++)
            {
                versement = listeVersements.elementAt(i);
                chaineVersements = "";

                chaineVersements =
                    Chaine.placeSousChaine(chaineVersements,
                        "" + versement.getNumero(), 5, 'g');

                if (versement.getDate() != null)
                {
                    DateFr dateFr = new DateFr();

                    dateFr.setTime(versement.getDate());
                    String sDate = dateFr.toString();
                    chaineVersements =
                        Chaine.placeSousChaine(chaineVersements, sDate, 10, 'd');
                }

                if (versement.getMontant() != null)
                {
                    String sMontant =
                        Conversion.doubleChaineStandard(
                            versement.getMontant().doubleValue(), 2, 1);

                    chaineVersements =
                        Chaine.placeSousChaine(chaineVersements, sMontant, 35, 'g');
                }

                zoneVersements.append(chaineVersements + "\n");
            }
        }
    }

// ---------------------------------------------------------------------
// Affichage d'un message d'erreur.
// ---------------------------------------------------------------------
    public void afficheMessage(String s)
    {
        message.setText(s);
    }

// ---------------------------------------------------------------------
// METHODES DE GESTION DES EVENEMENTS
// ---------------------------------------------------------------------
// Modification de statut d'une ligne de la liste des contacts.
// Un element change de valeur (selectionne ou pas).
// ---------------------------------------------------------------------
// Le test sur getValueIsAdjusting() sert a eviter deux appels du code
// dans le cas ou on choisit une ligne avec la souris.
// ---------------------------------------------------------------------
    public void valueChanged(ListSelectionEvent e)
    {
        if (!e.getValueIsAdjusting())
        {
            Contact contact =
                listeContacts.elementAt(jListContacts.getSelectedIndex());
            afficheVersements(contact);
            afficheMessage(" ");
        }
    }

// ---------------------------------------------------------------------
// Evenements souris sur le menu contextuel.
// ---------------------------------------------------------------------
// Remarque : le code utile est duplique dans mousePressed et
// mouseReleased parce que les declencheurs (trigger) ne fonctionnent
// pas de la meme façon selon le systeme.
// ---------------------------------------------------------------------
    public void mouseClicked(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        mouseReleased(e);
    }

    public void mouseReleased(MouseEvent e)
    {
        int numeroItem;
        if (e.isPopupTrigger())
        {
            menuPopup.show(e.getComponent(), e.getX(), e.getY());
            numeroItem = jListContacts.locationToIndex(e.getPoint());
            jListContacts.setSelectedIndex(numeroItem);
        }
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

// ---------------------------------------------------------------------
// Action sur le menu contextuel : un seul choix possible (rafraichir).
// ---------------------------------------------------------------------
    public void actionPerformed(ActionEvent e)
    {
        int numeroItem = jListContacts.getSelectedIndex();
        Controleur.rafraichir(listeContacts.elementAt(numeroItem));
    }

// --------------------------------------------------------------------------
// Ecouteur de l'evenement fermeture de la fenetre
// --------------------------------------------------------------------------
    private class EcouteWindowClosing extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            Controleur.arreter();
        }
    }
}