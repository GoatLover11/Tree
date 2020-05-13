import utils.GenClinic;
import utils.Tree;
import utils.World;

public class Main {
    public static void main(String[] args) {
        try {
            World world = new World();
            world.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
