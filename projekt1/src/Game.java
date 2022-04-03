import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Game implements ActionListener
{
   JButton b1, tab[][];
   JFrame f;
    Game()
    {
        f= new JFrame();

        tab = new JButton[4][4];
        int num = 1;
        for(int i=0;i<tab.length;i++)
        {
            for(int j=0;j<tab[0].length;j++)
            {
                if(num<16)
                    tab[i][j]=new JButton(""+num);
                else if(num==16)
                    tab[i][j]=new JButton("");
                tab[i][j].setBounds(10+(j*50),10+(i*50),50,50);
                tab[i][j].addActionListener(this);
                f.add(tab[i][j]);
                num++;
            }
        }
        b1=new JButton("Start");
        b1.setBounds(10,220,200,50);
        b1.addActionListener(this);
        f.add(b1);
        f.setSize(235,320);
        f.setLayout(null);
        f.setVisible(true);

        // linijka ważna!!!!
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    void shuffle()
    {
//        System.out.println("shuffle here!");
        for(int i=0;i<100;i++)
        {
            int[] blankCoords = findBlank(tab);
            int a = blankCoords[0];
            int b = blankCoords[1];
            int xMod=0, yMod=0;
            while (xMod==yMod || xMod==-yMod)
            {
                xMod=getRandomNumber(0,3)-1;
                yMod=getRandomNumber(0,3)-1;
            }

//            System.out.println(xMod+" "+yMod);
            if(!(a+xMod<0 || a+xMod>=tab.length ||b+yMod<0 || b+yMod>=tab.length))
                swapTiles(tab,a,b,a+xMod,b+yMod);
        }
    }
    void swapTiles(JButton[][]tab, int blankX, int blankY, int currX, int currY)
    {
//        System.out.println("swapTiles here!");
        String txt = tab[currX][currY].getText();
        tab[currX][currY].setText("");
        tab[blankX][blankY].setText(txt);
    }

    int[] findBlank(JButton[][] tab)
    {
        int[] result = {-1,-1};
        for(int i=0;i<tab.length;i++)
        {
            for(int j=0;j<tab[0].length;j++)
            {
                if(tab[i][j].getText().equals(""))
                    result = new int[] {i,j};
            }
        }

        return result;
    }
    int[] checkBlank(JButton[][] tab, int x, int y)
    {
        int[] result = {-1,-1};
        for(int i=-1;i<=1;i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (i==j || -i==j)
                    continue;
                try
                {
                    if(tab[x + j][y + i].getText().equals(""))
                        result = new int[] {x + j,y + i};
                }
                catch (Exception e)
                {
//                    System.out.println("Out of array size");
//                    MUTED ON PURPOSE
                }
            }
        }

        return result;
    }

    void checkWin(JButton[][] tab) //popup not done!
    {
        int num=1;
        for(int i=0;i<tab.length;i++)
        {
            for(int j=0;j<tab[0].length;j++)
            {
                try
                {
                    if (Integer.parseInt(tab[i][j].getText()) != num)
                        return;
                }
                catch (Exception e)
                {
//                    System.out.println("Checked empty tile");
//                    MUTED ON PURPOSE
                }
                finally
                {num++;}
            }
        }
//        System.out.println("tu ma być popup o wygranej");
        JOptionPane.showMessageDialog(f,"Congratulations! You have Won!");
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            shuffle();
        }
        else
        {
            int []blankCoords;
            for(int i=0;i<tab.length;i++)
            {
                for(int j=0;j<tab[0].length;j++)
                {
                    if(e.getSource()==tab[i][j])
                    {
                        if(!Arrays.equals(blankCoords=checkBlank(tab, i, j), new int[]{-1, -1}))
                        {
                            swapTiles(tab,blankCoords[0],blankCoords[1],i,j);
//                            System.out.println(Arrays.toString(clickedCoords));
                            checkWin(tab);
                        }
                    }
                }
            }
        }
    }

//    public static void main(String[] args)
//        {
//            new Game();
//        }
}