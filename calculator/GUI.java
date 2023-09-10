package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class GUI extends JFrame {
    JPanel p1 = new JPanel();
    private JCheckBox checkBox1, checkBox2;
    private JLabel label1, label2, label3, vegDatum;
    private JTextField textField, pluszInfo;
    private JButton button1, button2;
    private String text, text2, eredmeny;
    FelmondasiIdo felmondas = new FelmondasiIdo();
    FellebbezesIdo fellebbezes = new FellebbezesIdo();

    public GUI() {

        setTitle("Határidő számítása");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p1.setLayout(new GridBagLayout());

        checkBox1 = new JCheckBox("Fellebbezés");
        checkBox2 = new JCheckBox("Felmondási idő");
        label1 = new JLabel("Válassza ki a kívánt határidőt!");
        label2 = new JLabel("Kezdőnap:");
        label3 = new JLabel("Egyéb:");
        pluszInfo = new JTextField(20);
        vegDatum = new JLabel("A határidő vége");
        textField = new JTextField(20);
        button1 = new JButton("OK");
        button2 = new JButton("OK");

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 3, 2, 3);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 15;
        c.ipady = 5;
        p1.add(label1, c);

        c.gridy = 1;
        c.gridx = 0;
        p1.add(checkBox1, c);

        c.gridx = 2;
        p1.add(checkBox2, c);

        c.gridx = 1;
        c.gridy = 2;
        p1.add(label2, c);

        c.gridx = 1;
        c.gridy = 3;
        p1.add(textField, c);

        c.gridx = 1;
        c.gridy = 4;
        p1.add(button1, c);

        c.gridx = 1;
        c.gridy = 5;
        p1.add(label3, c);

        c.gridx = 1;
        c.gridy = 6;
        p1.add(pluszInfo, c);

        c.gridx = 1;
        c.gridy = 7;
        p1.add(button2, c);

        c.gridx = 1;
        c.gridy = 8;
        p1.add(vegDatum, c);

        add(p1, "North");

        // Add an item listener to each checkbox
        checkBox1.addItemListener(new CheckboxListener());
        checkBox2.addItemListener(new CheckboxListener());

        // Display the frame
        setVisible(true);
        setResizable(true);

    }

    public void felmondasido() {
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();
                if (text != null) {
                    String keltezes = text;
                    LocalDate datum = LocalDate.parse(keltezes);
                    felmondas.fellebbezesVege(datum);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();
                text2 = pluszInfo.getText();
                if (text != null) {
                    String vege = text;
                    String keltezes = text2;
                    LocalDate datum = LocalDate.parse(vege);
                    LocalDate kezdet = LocalDate.parse(keltezes);
                    felmondas.hosszabbFelmondas(kezdet, datum);
                    eredmeny = felmondas.kiiras();
                    vegDatum.setText(eredmeny);
                }
            }
        });

    }

    public void fellebbezesido() {
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();
                if (text != null) {
                    String keltezes = text;
                    LocalDate datum = LocalDate.parse(keltezes);
                    fellebbezes.fellebbezesVege(datum);
                    fellebbezes.getdatum(datum);
                    fellebbezes.munkaSzunet();
                    fellebbezes.itelkezesiSzunet(datum);
                    fellebbezes.hetVege();
                    eredmeny = fellebbezes.kiir();
                    vegDatum.setText(eredmeny);
                }
            }
        });

    }

    // CheckboxListener is an inner class that implement the ItemListener interface
    // It has an itemStateChanged method that is called whenever the state of a
    // checkbox changes
    private class CheckboxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            String text = "Válassza ki a kívánt határidőt";
            String szoveg1 = "";
            String szoveg2 = "";
            if (checkBox1.isSelected()) {
                checkBox2.setEnabled(false); // Disable the other checkbox
                label3.setEnabled(false);
                pluszInfo.setEnabled(false);
                button2.setEnabled(false);
                szoveg1 = szoveg1.concat("Írja be a határozat keltét!");
                fellebbezesido();
            } else if (checkBox2.isSelected()) {
                checkBox1.setEnabled(false);
                szoveg1 = szoveg1.concat("Írja be a felmondási idő kezdetét!");
                szoveg2 = szoveg2.concat("Írja be a munkaviszony kezdetét!");
                felmondasido();
            }
            label2.setText(szoveg1);
            label3.setText(szoveg2);
        }
    }

}
