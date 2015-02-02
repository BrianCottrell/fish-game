package fishgame1;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Brian
 */
public class Aquarium implements MouseListener{
    boolean pressed,visible;
    boolean[]goals;
    BufferedImage background;
    BufferedImage lanternFish,jellyFish1,anglerFish;
    int background_x,background_y,button_x,button_y;
    int time_interval=3;
    JLabel[]score;
    URL imageURL,fishURL;
    public Aquarium(){
        imageURL=getClass().getResource("aquarium.png");
        background=loadImage(imageURL);
        background_x=(FishGame.width-background.getWidth())/2;
        background_y=(FishGame.height-background.getHeight())/2;
        goals=new boolean[20];
        fishURL=getClass().getResource("fishAngler1a.png");
        anglerFish=loadImage(fishURL);
        fishURL=getClass().getResource("fishLantern1.png");
        lanternFish=loadImage(fishURL);
        fishURL=getClass().getResource("fishJelly2a.png");
        jellyFish1=loadImage(fishURL);
    }
    public void setVisibility(boolean VISIBLE){
        visible=VISIBLE;
    }
    public void loadGoals(boolean[]GOALS){
        goals=GOALS;
    }
    @Override
    public void mouseClicked(MouseEvent e){
    }
    public void mouseDragged(MouseEvent e){
    }
    @Override
    public void mouseEntered(MouseEvent e){
    }
    @Override
    public void mouseExited(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
    }
    @Override
    public void mousePressed(MouseEvent e){
    }
    @Override
    public void mouseReleased(MouseEvent e){
    }
    public void draw(Graphics g){
        if(visible){
            g.drawImage(background,background_x,background_y,null);
            if(goals[10])
                g.drawImage(anglerFish,FishGame.width/2,FishGame.height/3,null);
            if(goals[11])
                g.drawImage(lanternFish,FishGame.width/2,FishGame.height/10,null);
            if(goals[12])
                g.drawImage(jellyFish1,FishGame.width/3,FishGame.height/4,null);
        }
    }
    public static BufferedImage loadImage(java.net.URL ref){
        BufferedImage image=null;
        try{
            image=ImageIO.read(ref);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }
}