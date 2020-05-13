package utils;

import java.util.ArrayList;

public class GenClinic {

    public ArrayList<Tree> Trees;

    public GenClinic(int db){
        Trees = new ArrayList<>();
        for (int i = 0; i < db; i++) {
            Trees.add(new Tree());
        }
    }

    public void soutGens(){
        System.out.println();
        for(Tree t :Trees){
            t.soutTree();
            System.out.println();
        }
    }

    public void addRan(){
        for(Tree t : Trees){
            t.addRanBlock();
            t.check();
        }

    }

    public Tree maxFit(){
        Tree max = new Tree();
        for(Tree t : Trees){
            t.checkFit();
            if (t.Fit > max.Fit){
                max = t;
            }
        }
        return max;
    }
}
