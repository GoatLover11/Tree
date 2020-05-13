package utils;

import java.awt.*;
import java.util.ArrayList;

public class Tree {

    public int Fit;
    ArrayList <Block> Blocks;       //type 0 = semmi, 1 = törzs, 2 = lomb

    public Tree (){
        Blocks = new ArrayList<>();
        Blocks.add(new Block(1, 0, 0));
        Fit = 0;
    }


    //hozáadd egy random helyre egy random elemet
    public void addRanBlock(){
        Block b = new Block(Blocks.get((int)(Math.random() * Blocks.size())));
        Blocks.add(new Block(
                (int)(Math.random() * 3),
                b.X + ((int)(Math.random() * 3) -1),
                b.Y + ((int)(Math.random() * 3) -1)
                )
        );

        //ha ugyan ott van kettö kitörli
        for(Block b1 : Blocks){
            for(Block b2 : Blocks){
                if (b1.X == b2.X && b1.Y == b2.Y){
                    Blocks.remove(b1);
                }
            }
        }

    }

    public void soutTree(){
        for (Block b:Blocks) {
            b.soutBlock();
            System.out.println();
        }
    }

    //minden block közelébe kell lenie törzsnek
    public void check (){
        if (Blocks.size() > 1){
            for (Block b : Blocks) {
                int x = b.X;
                int y = b.Y;
                boolean life = false;

                if (x != 0 && y != 0){
                    for (int i = x-1; i <= x+1; i++) {
                        for (int j = y-1; j <= x+1; j++) {
                            if ((x != j && y != i) && getTxy(i, j) == 1){
                                life = true;
                            }
                        }
                    }
                }
                if (!life){
                    Blocks.remove(b);
                }
            }}
    }


    //ha block 2 és nincs fölötte semmi fit ++
    public void checkFit(){
        this.Fit = 0;
        for(Block b : Blocks){
            if (getTxy(b.X, b.Y + 1) == 0 && b.Type == 2 && b.Life){
                this.Fit++;
            }
        }
    }

    //x y kordinátán lévö blockot viszaadja
    private Block getXY (int x, int y){
        Block b = null;
        for(Block b1 : Blocks){
            if (b1.X == x && b1.Y == y){
                b = new Block(b1);
            }
        }
        return b;
    }

    //x y kordinátán lévö blockot tipusát viszaadja
    private int getTxy (int x, int y){
        int t = 0;
        for(Block b1 : Blocks){
            if (b1.X == x && b1.Y == y){
                t = b1.Type;
            }
        }
        return t;
    }

}
