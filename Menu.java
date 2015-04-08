import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends JFrame implements ActionListener {

    JMenuBar menuBar = new JMenuBar();
    JMenu menuPlik = new JMenu("Plik");
    JMenuItem mOtworz = new JMenuItem("Otwórz", 'O'); //'O' - mnemonik
    JMenuItem mZapisz = new JMenuItem("Zapisz");
    JMenuItem mWyjście = new JMenuItem("Wyjście");

    JMenu menuStyl = new JMenu("Styl");
    JMenuItem mNimbus = new JMenuItem("Nimbus");
    JMenuItem mMetal = new JMenuItem("Metal");
    JMenuItem mWindows = new JMenuItem("Windows");

    JMenu fontSize = new JMenu("Rozmiar czcionki");
    JMenuItem m10 = new JMenuItem("10");
    JMenuItem m12 = new JMenuItem("12");
    JMenuItem m14 = new JMenuItem("14");
    JMenuItem m16 = new JMenuItem("16");
    JMenuItem m20 = new JMenuItem("20");
    JMenuItem m24 = new JMenuItem("24");
    JMenuItem m32 = new JMenuItem("32");

    JMenu menuPomoc = new JMenu("Pomoc");
    JMenuItem mOprogramie = new JMenuItem("O programie");


    JTextArea tPole = new JTextArea();
    JScrollPane scroll = new JScrollPane(tPole);
    JButton bZliczaj = new JButton("Zlicz");
    JLabel lZliczaj = new JLabel("Zlicz wystąpienia wyrazów");

    JPopupMenu popup = new JPopupMenu();
    JMenuItem mpCopy = new JMenuItem("Kopiuj");
    JMenuItem mpPaste = new JMenuItem("Wklej");

    String wybranyTekst;

    JButton bWybierzKolor = new JButton("Wybierz kolor");
    JButton bDodajUsera = new JButton("Dodaj usera");
    JButton bWczytaj = new JButton("Wczytaj");

    private PanelHasla panelHasla;
    Runnable wateczek = new watek(tPole);

    Menu() {
        setSize(510, 560);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setTitle("Zaliczenie PO 2015");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(menuBar);
        menuBar.add(menuPlik);
        menuPlik.add(mOtworz);
        mOtworz.addActionListener(this);
        menuPlik.add(mZapisz);
        mZapisz.addActionListener(this);
        menuPlik.addSeparator();
        menuPlik.add(mWyjście);
        mWyjście.addActionListener(this);
        mWyjście.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        menuBar.add(menuStyl);
        menuStyl.add(mMetal);
        mMetal.addActionListener(this);
        menuStyl.add(mNimbus);
        mNimbus.addActionListener(this);
        menuStyl.add(mWindows);
        mWindows.addActionListener(this);

        menuBar.add(fontSize);
        fontSize.add(m10);
        m10.addActionListener(this);
        fontSize.add(m12);
        m12.addActionListener(this);
        fontSize.add(m14);
        m14.addActionListener(this);
        fontSize.add(m16);
        m16.addActionListener(this);
        fontSize.add(m20);
        m20.addActionListener(this);
        fontSize.add(m24);
        m24.addActionListener(this);
        fontSize.add(m32);
        m32.addActionListener(this);

        menuBar.add(menuPomoc);
        menuPomoc.add(mOprogramie);
        mOprogramie.addActionListener(this);

        add(scroll);
        scroll.setBounds(50, 50, 400, 400);
        lZliczaj.setBounds(50, 460, 180, 20);
        add(lZliczaj);
        bZliczaj.setBounds(210, 460, 60, 20);
        add(bZliczaj);
        bZliczaj.addActionListener(this);

        bWybierzKolor.setBounds(50, 20, 150, 20);
        add(bWybierzKolor);
        bWybierzKolor.addActionListener(this);

        popup.add(mpCopy);
        mpCopy.addActionListener(this);
        popup.add(mpPaste);
        mpPaste.addActionListener(this);

        tPole.setComponentPopupMenu(popup);

        add(bDodajUsera);
        bDodajUsera.setBounds(260, 460, 110, 20);
        bDodajUsera.addActionListener(this);
        add(bWczytaj);
        bWczytaj.addActionListener(this);
        bWczytaj.setBounds(370, 460, 80, 20);

    }

    public static void main(String[] args) {

        new Menu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mWyjście) {
            int wybor = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjśc?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
            if (wybor == 0) {
                dispose();
            }
        }
        else if (e.getSource() == mOprogramie) {
            JOptionPane.showMessageDialog(this, "Autor: Damian Bachórz\nGrupa I3", "O programie", JOptionPane.INFORMATION_MESSAGE);
        }else if (e.getSource() == mOtworz) {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File plik = fc.getSelectedFile();
                try {
                    Scanner skaner = new Scanner(plik);
                    while (skaner.hasNext()) {
                        tPole.append(skaner.next() + "\n");
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == mZapisz) { //zapis do pliku
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File plik = fc.getSelectedFile();
                try {
                    PrintWriter pw = new PrintWriter(plik);
                    Scanner skaner = new Scanner(tPole.getText());
                    while (skaner.hasNext()) {
                        pw.println(skaner.next() + "\n");
                    }
                    pw.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == bZliczaj) {
            //wątki
            Thread thread = new Thread(wateczek);
            thread.start();
        }
        else if(e.getSource() == mpCopy)
            wybranyTekst = tPole.getSelectedText();
        else if(e.getSource() == mpPaste)
            tPole.insert(wybranyTekst, tPole.getCaretPosition());
        else if(e.getSource() == mMetal){
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
        else if(e.getSource() == mNimbus){
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
        else if(e.getSource() == mWindows){
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
        else if(e.getSource() == m10){
            tPole.setFont(new Font("SansSerif", Font.PLAIN, 10));
        }
        else if(e.getSource() == m12){
            tPole.setFont(new Font("SansSerif", Font.PLAIN, 12));
        }
        else if(e.getSource() == m14){
            tPole.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
        else if(e.getSource() == m16){
            tPole.setFont(new Font("SansSerif", Font.PLAIN, 16));
        }
        else if(e.getSource() == m20){
            tPole.setFont(new Font("SansSerif", Font.PLAIN, 20));
        }
        else if(e.getSource() == m24){
            tPole.setFont(new Font("SansSerif", Font.PLAIN, 24));
        }
        else if(e.getSource() == m32){
            tPole.setFont(new Font("SansSerif", Font.PLAIN, 32));
        }
        else if(e.getSource() == bWybierzKolor){
            Color wybranyKolor = JColorChooser.showDialog(null, "Wybór koloru", Color.black);
            tPole.setForeground(wybranyKolor);
        }
        else if(e.getSource() == bDodajUsera){
            if(panelHasla == null){
                panelHasla = new PanelHasla(this);
            }
            panelHasla.setVisible(true);
        }
        else if(e.getSource() == bWczytaj){

            try {
                ObjectInputStream is;
                is = new ObjectInputStream(new FileInputStream("users.txt"));
                User osoba = (User)is.readObject();
                System.out.println("User " + osoba.nick + " zalogowany");
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }


        }


    }
}
