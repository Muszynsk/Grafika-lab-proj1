package com.zetcode;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.GeneralPath;
import java.awt.GradientPaint;



class Surface extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        int w = getWidth();
        int h = getHeight();
        int strokeWidth = (int)(0.01*w);

        final double pointsPrism[][] = {
                { w/2, 0.135*h }, { w*0.3, 0.63*h }, { w*0.7, 0.63*h }
        };
        final double pLight[][] = {
                { (int)(w*0.41), h/3 }, { (int)(w-(w*0.42+2*strokeWidth)), h/3-2*strokeWidth },
                { (int)(w-w*0.42+3*strokeWidth), h/3+3*strokeWidth }
        };

        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 0, w, h);

        BasicStroke bs1 = new BasicStroke(strokeWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);

        //white line on the left
        g2d.setPaint(new Color(255, 255, 255));
        g2d.drawLine(0, h/2, (int)(w*0.42), h/3);
        //rainbow on the right
        g2d.setPaint(new Color(255, 0, 0)); //red
        g2d.drawLine(w, h/2-2*strokeWidth, (int)(w-(w*0.42+2*strokeWidth)), h/3-2*strokeWidth);
        g2d.setPaint(new Color(255, 125, 0)); //orange
        g2d.drawLine(w, h/2-strokeWidth, (int)(w-(w*0.42+strokeWidth)), h/3-strokeWidth);
        g2d.setPaint(new Color(255, 255, 0)); //yellow
        g2d.drawLine(w, h/2, (int)(w-w*0.42), h/3);
        g2d.setPaint(new Color(0, 255, 0)); //green
        g2d.drawLine(w, h/2+strokeWidth, (int)(w-w*0.42+strokeWidth), h/3+strokeWidth);
        g2d.setPaint(new Color(0, 0, 255)); //blue
        g2d.drawLine(w, h/2+2*strokeWidth, (int)(w-w*0.42+2*strokeWidth), h/3+2*strokeWidth);
        g2d.setPaint(new Color(255, 0, 255)); //violet
        g2d.drawLine(w, h/2+3*strokeWidth, (int)(w-w*0.42+3*strokeWidth), h/3+3*strokeWidth);

        //setting gradient paint
        GradientPaint gp1 = new GradientPaint((int)(w*0.42), h/3,
                Color.white, (int)(w-w*0.42), h/3, Color.black, false);
        g2d.setPaint(gp1);

        //triangle (light)
        GeneralPath triLight = new GeneralPath();

        triLight.moveTo(pLight[0][0], pLight[0][1]);

        for (int k = 1; k < pLight.length; k++)
            triLight.lineTo(pLight[k][0], pLight[k][1]);

        triLight.closePath();
        g2d.fill(triLight);

        //triangle (prism)
        g2d.setPaint(new Color(255, 255, 255));
        GeneralPath triangle = new GeneralPath();

        triangle.moveTo(pointsPrism[0][0], pointsPrism[0][1]);

        for (int k = 1; k < pointsPrism.length; k++)
            triangle.lineTo(pointsPrism[k][0], pointsPrism[k][1]);

        triangle.closePath();
        g2d.draw(triangle);

        g2d.dispose();

        //https://zetcode.com/gfx/java2d/shapesandfills/ -> general paths

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Picture extends JFrame {

    public Picture() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Basic shapes");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Picture ex = new Picture();
                ex.setVisible(true);
            }
        });
    }
}