package utils;

//type 0 = semmi, 1 = törzs, 2 = lomb

public class Block {
    public final int Type;
    public final int X;
    public final int Y;
    public boolean Life;

    public Block(int t, int x, int y){
        this.Type = t;
        this.X = x;
        this.Y = y;
        Life = true;
    }

    public Block(Block b){
        this.Type = b.Type;
        this.X = b.X;
        this.Y = b.Y;
        Life = b.Life;
    }

    public void soutBlock(){
        System.out.println(Type + " " + X + ":" + Y);
    }

    public void death (){
        Life = false;
    }

    public void heal (){
        Life = true;
    }

}
