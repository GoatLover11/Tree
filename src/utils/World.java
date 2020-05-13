package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class World extends JFrame{

    final Dimension Dim = new Dimension(800, 600);
    private int Size = 20;
    private GenClinic GC;
    private BufferedImage Img = new BufferedImage(Dim.width, Dim.height, 1);
    private int Generation = 0;

    public World () throws Exception {
        GC = new GenClinic(10);
        setDefault();
    }

    public void start() throws Exception{
        Block [][] Map = new Block[Dim.height / Size][Dim.width / Size];

        while (true){

            Thread.sleep(100);
            Map = new Block[Dim.height / Size][Dim.width / Size];

            int i = 0;
            for(Tree t : GC.Trees){
                int j = 0;
                for (Block b : t.Blocks){

                    int x = (i * 3) + 3 + b.X ;
                    int y = Dim.height / Size - 5 + b.Y;

                    //System.out.println("i: " + i);
                    //System.out.println("x, y : " + x + " " + y);


                    if (x >= 0 && x < Dim.width / Size && y >= 0 && y < Dim.height / Size){
                        if (Map[y][x] == null){
                            Map[y][x] = new Block(b);
                        }
                    }
                    j++;
                }
                i++;
            }


            drawMap(Map);

            Thread.sleep(100);

            GC.soutGens();
            GC.addRan();
            Generation++;
            draw(Img.getGraphics());
            System.out.println("Generation: " + Generation);
            System.out.println("MaxFit: " + GC.maxFit().Fit);

        }
    }

    private void soutMap(Block [][] Map){
        System.out.println();
        for (int j = 0; j < Map.length; j++) {
            for (int k = 0; k < Map.length; k++) {
                if (Map[j][k] != null){
                    System.out.print(Map[j][k].Type);
                }else {
                    System.out.print("n");
                }
            }
            System.out.println();
        }
    }

    //mátrix ábrázolás (Map)
    private void drawMap( Block [][] Map){
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[i].length; j++) {
                if (Map[i][j] != null){
                    drawPoint(Map[i][j], i , j);
                }
            }
        }
    }

    //mátrix pontok ábrázolása
    private void drawPoint(Block b, int i, int j){
        Graphics g = Img.getGraphics();
        int x = i * Size;
        int y =Dim.height - (j * Size);

        if (b.Type == 0){
            g.setColor(Color.RED);
            g.fillRect(x, y, Size, Size);
        }
        if (b.Type == 1){
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, Size, Size);
        }
        if (b.Type == 2){
            g.setColor(Color.GREEN);
            g.fillRect(x, y, Size, Size);
        }

        repaint();

    }

    //alap beálitások
    private void setDefault(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Tree GA");
        this.add(new JLabel(new ImageIcon(Img)), BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setPreferredSize(Dim);
        draw(Img.createGraphics());
    }

    //alapszinezés
    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Img.getWidth(), Img.getHeight());
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 550, Img.getWidth(), Img.getHeight());

        g.setColor(Color.BLUE);
        g.drawString("Max fit: " + GC.maxFit().Fit, 280, 20);
        g.drawString("Max hossz: " + GC.maxFit().Blocks.size(), 350, 20);
        g.drawString("Generation: " + Generation, 440, 20);
    }
}
