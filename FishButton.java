package fishgame1;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
/**
 *
 * @author Brian
 */
public class FishButton implements MouseListener{
    BufferedImage background,fish,backButton,backPressed,backFocused,scoreScreen,newScoreScreen,selectScreen,unlockScreen,aboutScreen;
    BufferedImage[] buttonImage,pressedImage,focusedImage,selectFish;
    boolean pressed,focused,home,visible,highScore,about,aquarium,end,newScore,select,unlock;
    boolean[]goals;
    int pressedButton,unlockFish;
    int selections=4;
    int select_x=FishGame.width/4;
    int select_y=FishGame.height/3;
    int select_margin=FishGame.width/7;
    int margin=25;
    int bx,by;
    int[] x,y;
    URL buttonURL,pressedURL,focusedURL,backgroundURL,fishURL;

    public FishButton(int X,int Y){
        highScore=false;
        about=false;
        aquarium=false;
        goals=new boolean[20];
        buttonImage=new BufferedImage[6];
        pressedImage=new BufferedImage[6];
        focusedImage=new BufferedImage[6];
        selectFish=new BufferedImage[selections];
        backgroundURL=getClass().getResource("background.png");
        background=loadImage(backgroundURL);
        backgroundURL=getClass().getResource("endScore.png");
        scoreScreen=loadImage(backgroundURL);
        backgroundURL=getClass().getResource("newScore.png");
        newScoreScreen=loadImage(backgroundURL);
        backgroundURL=getClass().getResource("menuAbout.png");
        aboutScreen=loadImage(backgroundURL);
        backgroundURL=getClass().getResource("menuSelect.png");
        selectScreen=loadImage(backgroundURL);
        backgroundURL=getClass().getResource("menuUnlock.png");
        unlockScreen=loadImage(backgroundURL);
        fishURL=getClass().getResource("fishAngler1a.png");
        fish=loadImage(fishURL);
        bx=(FishGame.width-background.getWidth())/2;
        by=(FishGame.height-background.getHeight())/2;
        buttonURL=getClass().getResource("buttonNew1.png");
        buttonImage[0]=loadImage(buttonURL);
        pressedURL=getClass().getResource("buttonNew2.png");
        pressedImage[0]=loadImage(pressedURL);
        focusedURL=getClass().getResource("buttonNew2.png");
        focusedImage[0]=loadImage(focusedURL);
        buttonURL=getClass().getResource("buttonContinue1.png");
        buttonImage[1]=loadImage(buttonURL);
        pressedURL=getClass().getResource("buttonContinue2.png");
        pressedImage[1]=loadImage(pressedURL);
        focusedURL=getClass().getResource("buttonContinue2.png");
        focusedImage[1]=loadImage(focusedURL);
        buttonURL=getClass().getResource("buttonQuit1.png");
        buttonImage[2]=loadImage(buttonURL);
        pressedURL=getClass().getResource("buttonQuit2.png");
        pressedImage[2]=loadImage(pressedURL);
        focusedURL=getClass().getResource("buttonQuit2.png");
        focusedImage[2]=loadImage(focusedURL);
        buttonURL=getClass().getResource("buttonAquarium1.png");
        buttonImage[3]=loadImage(buttonURL);
        pressedURL=getClass().getResource("buttonAquarium2.png");
        pressedImage[3]=loadImage(pressedURL);
        focusedURL=getClass().getResource("buttonAquarium2.png");
        focusedImage[3]=loadImage(focusedURL);
        buttonURL=getClass().getResource("buttonAbout1.png");
        buttonImage[4]=loadImage(buttonURL);
        pressedURL=getClass().getResource("buttonAbout2.png");
        pressedImage[4]=loadImage(pressedURL);
        focusedURL=getClass().getResource("buttonAbout2.png");
        focusedImage[4]=loadImage(focusedURL);
        buttonURL=getClass().getResource("buttonScore1.png");
        buttonImage[5]=loadImage(buttonURL);
        pressedURL=getClass().getResource("buttonScore2.png");
        pressedImage[5]=loadImage(pressedURL);
        focusedURL=getClass().getResource("buttonScore2.png");
        focusedImage[5]=loadImage(focusedURL);
        buttonURL=getClass().getResource("buttonBack1.png");
        backButton=loadImage(buttonURL);
        pressedURL=getClass().getResource("buttonBack2.png");
        backPressed=loadImage(pressedURL);
        focusedURL=getClass().getResource("buttonBack2.png");
        backFocused=loadImage(focusedURL);
        fishURL=getClass().getResource("fishLantern1.png");
        selectFish[0]=loadImage(fishURL);
        fishURL=getClass().getResource("fishJelly3a.png");
        selectFish[1]=loadImage(fishURL);
        fishURL=getClass().getResource("fishViperA.png");
        selectFish[2]=loadImage(fishURL);
        fishURL=getClass().getResource("fishAngler.png");
        selectFish[3]=loadImage(fishURL);
        pressedButton=-1;
        visible=true;
        home=true;
        unlock=false;
        x=new int[6];
        y=new int[6];
        x[0]=(FishGame.width-buttonImage[0].getWidth()-buttonImage[1].getWidth()-buttonImage[2].getWidth())/2-margin;
        x[1]=x[0]+buttonImage[0].getWidth()+margin;
        x[2]=x[1]+buttonImage[1].getWidth()+margin;
        x[3]=x[0];
        x[4]=x[1];
        x[5]=x[2];
        y[0]=by+background.getHeight()*5/8;
        y[1]=y[0];
        y[2]=y[1];
        y[3]=y[0]+buttonImage[0].getHeight()+margin;
        y[4]=y[3];
        y[5]=y[4];
    }
    public int getPressedButton(){
        if(visible)
            return pressedButton;
        else
            return -1;
    }
    public void setSequence(boolean BUTTONS,int BACKGROUND){
        visible=BUTTONS;
        home=false;
        highScore=false;
        about=false;
        aquarium=false;
        end=false;
        newScore=false;
        select=false;
        if(BACKGROUND==1)
            home=true;
        if(BACKGROUND==2)
            highScore=true;
        if(BACKGROUND==3)
            aquarium=true;
        if(BACKGROUND==4)
            about=true;
        if(BACKGROUND==5)
            newScore=true;
        if(BACKGROUND==6)
            end=true;
        if(BACKGROUND==7)
            select=true;
    }
    public void setUnlock(boolean UNLOCK,int GOAL){
        unlock=UNLOCK;
        unlockFish=GOAL;
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
        if(visible){
            for(int i=0;i<buttonImage.length;i++){
                if(e.getX()>x[i]&&e.getX()<x[i]+buttonImage[i].getWidth()&&e.getY()>y[i]&&e.getY()<y[i]+buttonImage[i].getHeight())
                    pressedButton=i;
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e){
        if(visible){
            for(int i=0;i<buttonImage.length;i++){
                if(i==pressedButton)
                    if(e.getX()>x[i]&&e.getX()<x[i]+buttonImage[i].getWidth()&&e.getY()>y[i]&&e.getY()<y[i]+buttonImage[i].getHeight())
                        pressed=true;
                    else
                        pressedButton=-1;
            }
        }
    }
    public void draw(Graphics g){
        if(end)
            g.drawImage(scoreScreen,bx,by,null);
        if(newScore)
            g.drawImage(newScoreScreen,bx,by,null);
        if(unlock&&!newScore){
            g.drawImage(unlockScreen,bx,by,null);
            g.drawImage(selectFish[unlockFish],FishGame.width/2-selectFish[unlockFish].getWidth()/2,5*FishGame.height/12,null);
        }
        if(about)
            g.drawImage(aboutScreen,bx,by,null);
        if(visible){
            if(home){
                g.drawImage(background,bx,by,null);
                g.drawImage(fish,bx+3*background.getWidth()/5,by+background.getHeight()/5,null);
            }
            for(int i=0;i<buttonImage.length;i++){
                if(pressedButton==i)
                    g.drawImage(pressedImage[i],x[i],y[i],null);
                else if(focused)
                    g.drawImage(focusedImage[i],x[i],y[i],null);
                else
                    g.drawImage(buttonImage[i],x[i],y[i],null);
            }
            if(highScore)
                if(pressedButton==5)
                    g.drawImage(backPressed,x[5],y[5],null);
                else
                    g.drawImage(backButton,x[5],y[5],null);
            if(about)
                if(pressedButton==4)
                    g.drawImage(backPressed,x[4],y[4],null);
                else
                    g.drawImage(backButton,x[4],y[4],null);
            if(aquarium)
                if(pressedButton==4)
                    g.drawImage(backPressed,x[3],y[3],null);
                else
                    g.drawImage(backButton,x[3],y[3],null);
        }
        if(select){
            g.drawImage(selectScreen,bx,by,null);
            g.drawImage(selectFish[0],select_x,select_y,null);
            if(goals[1])
                g.drawImage(selectFish[1],select_x+select_margin,select_y,null);
            if(goals[2])
                g.drawImage(selectFish[2],select_x+2*select_margin,select_y,null);
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
