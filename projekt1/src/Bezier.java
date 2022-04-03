import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;


class Surface extends JPanel{

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.black);

        int w = getWidth();
        int h = getHeight();

        //Bezier 3

        int[][] p = new int[26][2]; //outside J
        p[0]=new int[]{w/8,h/8};
        p[1]=new int[]{w/3,h/8};
        p[2]=new int[]{w/3,5*h/16};
        p[3]=new int[]{3*w/8,5*h/16};
        p[4]=new int[]{3*w/8,3*h/8};
        p[5]=new int[]{w/3,3*h/8};
        p[6]=new int[]{w/3,11*h/16};
        p[7]=new int[]{w/8,9*h/16};
        p[8]=new int[]{7*w/24,5*h/16};
        p[9]=new int[]{w/4,3*h/16};
        p[10]=new int[]{w/8,3*h/16};

        p[11]=new int[]{7*w/24,3*h/8}; //inside J
        p[12]=new int[]{7*w/24,5*h/8};
        p[13]=new int[]{w/6,9*h/16};

        p[14]=new int[]{13*w/24,h/8}; //M
        p[15]=new int[]{5*w/8,h/8};
        p[16]=new int[]{17*w/24,3*h/8};
        p[17]=new int[]{19*w/24,h/8};
        p[18]=new int[]{7*w/8,h/8};
        p[19]=new int[]{11*w/12,11*h/16};
        p[20]=new int[]{10*w/12,11*h/16};
        p[21]=new int[]{19*w/24,7*h/16};
        p[22]=new int[]{17*w/24,5*h/8};
        p[23]=new int[]{5*w/8,7*h/16};
        p[24]=new int[]{7*w/12,11*h/16};
        p[25]=new int[]{w/2,11*h/16};


        int[][][] bezierPoints = new int[26][4][4]; //4 points with 2 coordinates ([][n][0] is X, [][n][1] is y

        bezierPoints[0]=new int[][]{p[0],{p[0][0]+w/24,p[0][1]+h/16},{p[1][0],p[1][1]-h/16},p[1]}; //outside J
        bezierPoints[1]=new int[][]{p[1],p[1],{p[2][0]-w/24,p[2][1]},p[2]};
        bezierPoints[2]=new int[][]{p[2],p[2],{p[3][0],p[3][1]-h/16},p[3]};
        bezierPoints[3]=new int[][]{p[3],{p[3][0]-w/48,p[3][1]+h/16},{p[4][0]+w/24,p[4][1]+h/16},p[4]};
        bezierPoints[4]=new int[][]{p[4],p[4],{p[5][0],p[5][1]-h/16},p[5]};
        bezierPoints[5]=new int[][]{p[5],p[5],{p[6][0]+w/24,p[6][1]-h/16},p[6]};
        bezierPoints[6]=new int[][]{p[6],{p[6][0]-w/24,p[6][1]+h/16},{p[7][0]+w/12,p[7][1]+h/8},p[7]};
        bezierPoints[7]=new int[][]{p[7],{p[7][0]-w/24,p[7][1]-h/16},{p[8][0]+w/24,p[8][1]+h/8},p[8]};
        bezierPoints[8]=new int[][]{p[8],p[8],p[9],p[9]};
        bezierPoints[9]=new int[][]{p[9],{p[9][0]-w/24,p[9][1]-h/16},p[10],p[10]};
        bezierPoints[10]=new int[][]{p[10],{p[10][0]-w/24,p[10][1]+h/16},{p[0][0]-w/24,p[0][1]-h/8},p[0]};

        bezierPoints[11]=new int[][]{p[11],{p[11][0]+w/24,p[11][1]},{p[12][0]+w/24,p[12][1]},p[12]}; //inside J
        bezierPoints[12]=new int[][]{p[12],{p[12][0]-w/12,p[12][1]+h/32},p[13],p[13]};
        bezierPoints[13]=new int[][]{p[13],{p[13][0]-w/24,p[13][1]-h/16},{p[11][0]+w/24,p[11][1]+h/16},p[11]};

        bezierPoints[14]=new int[][]{p[14],{p[14][0]+w/48,p[14][1]-h/16},{p[15][0]-w/48,p[15][1]-h/16},p[15]}; //M
        bezierPoints[15]=new int[][]{p[15],p[15],{p[16][0]-w/48,p[16][1]},p[16]};
        bezierPoints[16]=new int[][]{p[16],{p[16][0]+w/48,p[16][1]},p[17],p[17]};
        bezierPoints[17]=new int[][]{p[17],{p[17][0]+w/48,p[17][1]-h/16},{p[18][0]-w/48,p[18][1]-h/16},p[18]};
        bezierPoints[18]=new int[][]{p[18],p[18],{p[19][0]+w/24,p[19][1]-h/16},p[19]};
        bezierPoints[19]=new int[][]{p[19],{p[19][0]-w/48,p[19][1]+h/16},{p[20][0]+w/48,p[20][1]+h/16},p[20]};
        bezierPoints[20]=new int[][]{p[20],{p[20][0]-w/48,p[20][1]-h/8},{p[21][0]+w/48,p[21][1]},p[21]};
        bezierPoints[21]=new int[][]{p[21],p[21],{p[16][0]+w/48,p[16][1]},p[22]};
        bezierPoints[22]=new int[][]{p[22],{p[16][0]-w/48,p[16][1]},p[23],p[23]};
        bezierPoints[23]=new int[][]{p[23],{p[23][0]-w/48,p[23][1]},{p[24][0]+w/48,p[24][1]-h/8},p[24]};
        bezierPoints[24]=new int[][]{p[24],{p[24][0]-w/48,p[24][1]+h/16},{p[25][0]+w/48,p[25][1]+h/16},p[25]};
        bezierPoints[25]=new int[][]{p[25],{p[25][0]-w/24,p[25][1]-h/16},p[14],p[14]};


        for(int i=0;i<bezierPoints.length;i++) {
            drawCurve(g2d, bezierPoints[i]);
        }
    }

    private void drawCurve(Graphics g2d,int[][] bezierPoints){
        int drawX;
        int drawY;
        for (double t=0; t<=1; t+=0.001){
            double oneMinT=1-t;
            drawX=(int)(Math.pow(oneMinT,3)*bezierPoints[0][0]+3*Math.pow(oneMinT,2)*t*bezierPoints[1][0]+
                    3*oneMinT*t*t*bezierPoints[2][0]+t*t*t*bezierPoints[3][0]);
            drawY=(int)(Math.pow(oneMinT,3)*bezierPoints[0][1]+3*Math.pow(oneMinT,2)*t*bezierPoints[1][1]+
                    3*oneMinT*t*t*bezierPoints[2][1]+t*t*t*bezierPoints[3][1]);
            g2d.drawLine(drawX, drawY, drawX, drawY);
        }


    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Bezier extends JFrame {

    public Bezier() {

        initUI();
    }

    private void initUI() {

        final Surface surface = new Surface();
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

            }
        });

        setTitle("Bezier");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//                Bezier ex = new Bezier();
//                ex.setVisible(true);
//            }
//        });
//    }
}
