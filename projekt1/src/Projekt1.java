import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Projekt1 extends JFrame implements ActionListener {
    JButton[] buttons;
    JFrame f;

    Projekt1()
    {
        int w=300,h=350,h6=h/6;

        f= new JFrame();
        JLabel l = new JLabel("Choose part of project to show", SwingConstants.CENTER);
        l.setBounds(0,0,w,h6);
        l.setVerticalAlignment(SwingConstants.CENTER);
        f.add(l);

        buttons = new JButton[4];
        buttons[0]=new JButton("Picture (lab1)");
        buttons[1]=new JButton("Game (lab2)");
        buttons[2]=new JButton("Bezier curves (lab3)");
        buttons[3]=new JButton("Bezier planes (lab4)");
        for(int i=0;i<buttons.length;i++){
            buttons[i].setBounds(0,h6+h6*i,w,h6);
            buttons[i].addActionListener(this);
            f.add(buttons[i]);
        }


        f.setSize(w,h);
        f.setLayout(null);
        f.setVisible(true);

        // linijka ważna!!!!
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        //RUN PICTURE
        if(e.getSource()==buttons[0]){
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    com.zetcode.Picture ex = new com.zetcode.Picture();
                    ex.setVisible(true);
                }
            });
        }
        //RUN GAME
        else if(e.getSource()==buttons[1]) {
            new Game();
        }
        //RUN BEZIER CURVES
        else if(e.getSource()==buttons[2]) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Bezier ex = new Bezier();
                    ex.setVisible(true);
                }
            });
        }
        //RUN BEZIER PLANES
        else if(e.getSource()==buttons[3]) {
            JOptionPane.showMessageDialog(f,"I'm sorry, I haven't done that :(");
        }
    }


    public static void main(String[] args) {
        Projekt1 p1 = new Projekt1();
    }
}
