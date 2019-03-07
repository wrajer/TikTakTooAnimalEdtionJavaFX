import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game extends JFrame {


    int player = 1;


    public Game() {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fox and Bear");
        setSize(300, 400);
        setLocation(300, 300);
        setLayout(new GridLayout(4, 3)); // jest kilka typów to jeden z najprostszych, ustawia guziki po kolei
        // pack(); //zbija nam w jak najmniejszy rozmiar

        int[] tablicawynikow = new int[9];

        Image foxIconImage = new ImageIcon(this.getClass().getResource("images/fox60.png")).getImage(); // import icony foxa
        Image bearIconImage = new ImageIcon(this.getClass().getResource("images/bear60.png")).getImage();
        Image startIconImage = new ImageIcon(this.getClass().getResource("images/newgame60.png")).getImage();
        Image exitIconImage = new ImageIcon(this.getClass().getResource("images/exit60.png")).getImage();

        for (int i = 0; i < 9; i++) {
            JButton Button = new JButton();
            // Button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            String nameString = Integer.toString(i);
            Button.setName(nameString);
            add(Button);
            Button.setBackground(Color.white);
            // dodawanie akcji po kliknieciu mysza
            Button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    // System.out.println("działa naciśniecie");

                    // akcje po nacinieciu
                    JButton button = (JButton) e.getSource(); // rzutownie, ze zmiennej e dostajemy zrodlo co zostlo
                    // nacisniete

                    tablicawynikow[Integer.parseInt(Button.getName())] = player; // wpisywanie nr playera do tablicy


                    if (player == 1) {
                        // Button.setBackground(Color.green);

                        Button.setIcon(new ImageIcon(bearIconImage));
                        Button.setBackground(new Color(153, 102, 0)); //colory R,G,B,A
                        winCheck(tablicawynikow);
                        //printTab(tablicawynikow); // usn
                        player++;
                    } else {

                        // Button.setText(Integer.toString(player)); // wwyswietlanie nazwy playera
                        Button.setIcon(new ImageIcon(foxIconImage));
                        Button.setBackground(new Color(255, 204, 102));
                        winCheck(tablicawynikow);
                        //printTab(tablicawynikow); // usun
                        player--;

                    }
                    button.removeActionListener(this);
                    // Button.setEnabled(false); // to jet wylaczenie guzika ale robi się szary

                }
            });
        }

        // guziki do sterowania
        JButton ButtonStart = new JButton();

        add(ButtonStart);
        ButtonStart.setBackground(Color.LIGHT_GRAY);
        ButtonStart.setIcon(new ImageIcon(startIconImage));
        ButtonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JButton button = (JButton) e.getSource(); // rzutownie, ze zmiennej e dostajemy zrodlo co zostlo
                // nacisniete
                // System.out.println(e);

                new Game(); // pytanie czy mozna w latwiejszy sposób zresetować stan

            }
        });

        // pusty guzik
        JButton ButtonEmpty = new JButton();
        add(ButtonEmpty);
        ButtonEmpty.setEnabled(false);

        // Exit button
        JButton ButtonExit = new JButton();
        add(ButtonExit);
        ButtonExit.setBackground(Color.LIGHT_GRAY);
        ButtonExit.setIcon(new ImageIcon(exitIconImage));
        ButtonExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

        setVisible(true);

    }

    public void printTab(int[] tab) { // metoda do sprawdzenie poprawności wypisywanych wyników
        for (int x : tab) {
            System.out.print(x + " ");

        }
        System.out.println("");
    }

    public void winCheck(int[] Tab) {

        if ((Tab[0] == Tab[1] && Tab[0] == Tab[2] && Tab[0] != 0)
                || (Tab[3] == Tab[4] && Tab[4] == Tab[5] && Tab[3] != 0)
                || (Tab[6] == Tab[7] && Tab[7] == Tab[8] && Tab[6] != 0)
                || (Tab[0] == Tab[3] && Tab[3] == Tab[6] && Tab[0] != 0)
                || (Tab[1] == Tab[4] && Tab[4] == Tab[7] && Tab[1] != 0)
                || (Tab[2] == Tab[5] && Tab[5] == Tab[8] && Tab[2] != 0)
                || (Tab[0] == Tab[4] && Tab[4] == Tab[8] && Tab[0] != 0)
                || (Tab[2] == Tab[4] && Tab[4] == Tab[6] && Tab[2] != 0)

        ) {
            winPanel();
        }

    }

    public void winPanel() {
        String message;


        if (player == 1) {
            message = "Bear Won";
        } else {
            message = "Fox won !";
        }

        JOptionPane.showMessageDialog(new JFrame(), message, "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
        // dać opóznienie i opcje nowej rozgrywki
        new Game();

    }

}