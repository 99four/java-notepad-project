import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

class PanelHasla extends JDialog implements ActionListener {

    private JLabel lUser = new JLabel("Użytkownik", JLabel.RIGHT);
    private JLabel lHaslo = new JLabel("Hasło", JLabel.RIGHT);
    private JTextField tUser = new JTextField();
    private JPasswordField pHaslo = new JPasswordField();
    private JButton bOk = new JButton("OK");
    private JButton bCancel = new JButton("Anuluj");


    public PanelHasla(JFrame owner){
        super(owner, "Wprowadzanie hasła", false);
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);

        lUser.setBounds(0, 0, 100, 20);
        add(lUser);

        tUser.setBounds(150, 0, 100, 20);
        add(tUser);

        lHaslo.setBounds(0, 50, 100, 20);
        add(lHaslo);

        pHaslo.setBounds(150, 50, 100, 20);
        add(pHaslo);

        bOk.setBounds(0, 100, 100, 20);
        add(bOk);
        bOk.addActionListener(this);

        bCancel.setBounds(150, 100, 100, 20);
        add(bCancel);
        bCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bOk){

            ObjectOutputStream os;
            try {
                User osoba = new User(tUser.getText(), "whateva");
                os = new ObjectOutputStream(new FileOutputStream("users.txt"));
                os.writeObject(osoba);
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(PanelHasla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setVisible(false);
    }

}