package fishgame1;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author Brian
 */
public class HighScore{
    boolean pressed,visible;
    BufferedImage background;
    int x,y;
    int fontSize=45;
    int spacing=55;
    public JLabel score1,score2,score3,score4,score5;
    SavedData save;
    URL imageURL;
    public HighScore(){
        imageURL=getClass().getResource("menuHighScore.png");
        background=loadImage(imageURL);
        x=FishGame.width/2-background.getWidth()/3;
        y=FishGame.height/2-5*background.getHeight()/12;
        score1=new JLabel("Hi");
        score1.setFont(new Font("Baveuse",Font.BOLD,fontSize));
        score1.setForeground(Color.blue);
        score1.setBounds(x,y,500,350);
        score1.setVisible(false);
        score2=new JLabel("Hi");
        score2.setFont(new Font("Baveuse",Font.BOLD,fontSize));
        score2.setForeground(Color.blue);
        score2.setBounds(x,y+spacing,500,350);
        score2.setVisible(false);
        score3=new JLabel("Hi");
        score3.setFont(new Font("Baveuse",Font.BOLD,fontSize));
        score3.setForeground(Color.blue);
        score3.setBounds(x,y+2*spacing,500,350);
        score3.setVisible(false);
        score4=new JLabel("Hi");
        score4.setFont(new Font("Baveuse",Font.BOLD,fontSize));
        score4.setForeground(Color.blue);
        score4.setBounds(x,y+3*spacing,500,350);
        score4.setVisible(false);
        score5=new JLabel("Hi");
        score5.setFont(new Font("Baveuse",Font.BOLD,fontSize));
        score5.setForeground(Color.blue);
        score5.setBounds(x,y+4*spacing,500,350);
        score5.setVisible(false);
    }
    public void setVisibility(boolean VISIBLE){
        visible=VISIBLE;
        if(!visible){
            score1.setVisible(false);
            score2.setVisible(false);
            score3.setVisible(false);
            score4.setVisible(false);
            score5.setVisible(false);
        }
    }
    public void draw(Graphics g){
        if(visible){
            this.loadGame("SaveFile.dat");
            score1.setText(save.name[4]+"    "+save.highScore[4]);
            score1.setVisible(true);
            score2.setText(save.name[3]+"    "+save.highScore[3]);
            score2.setVisible(true);
            score3.setText(save.name[2]+"    "+save.highScore[2]);
            score3.setVisible(true);
            score4.setText(save.name[1]+"    "+save.highScore[1]);
            score4.setVisible(true);
            score5.setText(save.name[0]+"    "+save.highScore[0]);
            score5.setVisible(true);
            g.drawImage(background,(FishGame.width-background.getWidth())/2,(FishGame.height-background.getHeight())/2,null);
        }else{
            score1.setVisible(false);
            score2.setVisible(false);
            score3.setVisible(false);
            score4.setVisible(false);
            score5.setVisible(false);
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
    public void loadGame(String FILE_NAME){
        try{
            ObjectInputStream istream=new ObjectInputStream(new FileInputStream(FILE_NAME));
            save=(SavedData)istream.readObject();
            istream.close();
        }
        catch(Exception e){
            System.out.println("Load Failed");
        }
    }
}

