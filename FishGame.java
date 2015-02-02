package fishgame1;
import javax.swing.*;
/**
 *
 * @author Brian
 */
public class FishGame extends JFrame{
    public static int width,height;
    public FishPanel fish;
    public FishGame(String title){
        super(title);
        width=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        height=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(width,height);
        fish=new FishPanel();
        this.add(fish);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        FishGame game=new FishGame("Fish Game");
    }

}
