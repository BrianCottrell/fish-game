package fishgame1;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
/**
 *
 * @author Brian
 */
public class Fish {
    Boolean collision;
    BufferedImage[] fish;
    BufferedImage light;
    int fish_x,fish_y,fish_h,fish_w,fish_sx1,fish_sy1,fish_sx2,fish_sy2,fish_cx,fish_cy;
    int fish_speed,fish_type,scale,animation,frames;
    int fish_x_movement,fish_y_movement;
    int direction,collision_type;
    int lightOffset_x,lightOffset_y;
    int[]collision_data=new int[5];
    java.net.URL fishURL,lightURL;

    public Fish(int TYPE){
        fish_type=TYPE;
        animation=0;
        if(fish_type>0&&fish_type<=21||fish_type>100&&fish_type<=110||fish_type>200&&fish_type<=210){
            frames=3;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishAngler1a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishAngler1b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishAngler1c.png");
            fish[2]=loadImage(fishURL);
            lightURL=getClass().getResource("lightYellow1.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(1,2);
            loadArray(9,0,0,0,9);
            lightOffset_x=-fish[0].getWidth()/7;
            lightOffset_y=fish[0].getHeight()/25;
            scale=1;
        }
        if(fish_type>110&&fish_type<=123||fish_type==22||fish_type==23||fish_type>210&&fish_type<=220){
            frames=3;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishAngler2a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishAngler2b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishAngler2c.png");
            fish[2]=loadImage(fishURL);
            lightURL=getClass().getResource("lightRed1.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(2,3);
            loadArray(9,0,0,0,9);
            lightOffset_x=-fish[0].getWidth()/7;
            lightOffset_y=fish[0].getHeight()/25;
            scale=1;
        }
        if(fish_type>220&&fish_type<=230||fish_type==24||fish_type==25||fish_type==124||fish_type==125){
            frames=3;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishAngler3a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishAngler3b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishAngler3c.png");
            fish[2]=loadImage(fishURL);
            lightURL=getClass().getResource("lightBlue2.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(3,4);
            loadArray(9,0,0,0,9);
            lightOffset_x=-fish[0].getWidth()/7;
            lightOffset_y=fish[0].getHeight()/25;
            scale=1;
        }
        if(fish_type>25&&fish_type<=40||fish_type>125&&fish_type<=140||fish_type>230&&fish_type<=240){
            frames=3;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishViper1a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishViper1b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishViper1c.png");
            fish[2]=loadImage(fishURL);
            lightURL=getClass().getResource("lightYellow2.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(2,3);
            loadArray(7,0,0,0,9);
            lightOffset_x=fish[0].getWidth()*13/30;
            lightOffset_y=-fish[0].getHeight()/5;
            scale=1;
        }
        if(fish_type>140&&fish_type<=145||fish_type>240&&fish_type<=250){
            frames=3;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishViper1a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishViper1b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishViper1c.png");
            fish[2]=loadImage(fishURL);
            lightURL=getClass().getResource("lightRed2.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(2,3);
            loadArray(7,0,0,0,9);
            lightOffset_x=fish[0].getWidth()*13/30;
            lightOffset_y=-fish[0].getHeight()/5;
            scale=1;
        }
        if(fish_type>40&&fish_type<=45||fish_type>145&&fish_type<=150||fish_type>250&&fish_type<=255){
            frames=3;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishDragon1a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishDragon1b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishDragon1c.png");
            fish[2]=loadImage(fishURL);
            lightURL=getClass().getResource("lightGreen1.png");
            light=loadImage(lightURL);
            fish_speed=1;
            loadArray(8,0,0,0,9);
            lightOffset_x=fish[0].getWidth()/3;
            lightOffset_y=fish[0].getHeight()*11/20;
            scale=1;
        }
        if(fish_type>45&&fish_type<=65||fish_type>150&&fish_type<=165||fish_type>255&&fish_type<=265){
            frames=4;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishJelly1a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly1b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly1c.png");
            fish[2]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly1d.png");
            fish[3]=loadImage(fishURL);
            lightURL=getClass().getResource("lightYellow1.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(1,4);
            loadArray(0,1,5,125,2);
            lightOffset_x=-fish[0].getWidth()/3;
            lightOffset_y=-fish[0].getHeight()/3;
            scale=1;
        }
        if(fish_type>65&&fish_type<=80||fish_type>165&&fish_type<=180||fish_type>265&&fish_type<=280){
            frames=4;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishJelly2a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly2b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly2c.png");
            fish[2]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly2d.png");
            fish[3]=loadImage(fishURL);
            lightURL=getClass().getResource("lightRed1.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(1,5);
            loadArray(1,1,5,150,2);
            lightOffset_x=fish[0].getWidth()/7;
            lightOffset_y=fish[0].getHeight()/5;
            scale=1;
        }
        if(fish_type>80&&fish_type<=95||fish_type>180&&fish_type<=195||fish_type>280&&fish_type<=295){
            frames=4;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishJelly3a.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly3b.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly3c.png");
            fish[2]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly3d.png");
            fish[3]=loadImage(fishURL);
            lightURL=getClass().getResource("lightBlue2.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(1,1);
            loadArray(2,1,10,175,3);
            lightOffset_x=fish[0].getWidth()/7;
            lightOffset_y=0;
            scale=1;
        }
        if(fish_type>95&&fish_type<=100||fish_type>195&&fish_type<=200||fish_type>295&&fish_type<=300){
            frames=1;
            fish=new BufferedImage[frames];
            fishURL=getClass().getResource("fishJelly4a.png");
            fish[0]=loadImage(fishURL);
            lightURL=getClass().getResource("transparent.png");
            light=loadImage(lightURL);
            fish_speed=randomNumber(1,4);
            loadArray(0,1,25,200,3);
            lightOffset_x=fish[0].getWidth()/7;
            lightOffset_y=0;
            scale=1;
        }
        fish_w=fish[0].getWidth();
        fish_h=fish[0].getHeight();
        fish_y=randomNumber(-500,FishGame.height-fish_h);
        fish_x_movement=0;
        fish_y_movement=0;
        direction=randomNumber(0,1);
        collision=true;
        if(direction==0){
            fish_x=-fish[0].getWidth();
            fish_sx1=fish_w;
            fish_sy1=0;
            fish_sx2=0;
            fish_sy2=fish_h;
            lightOffset_x=fish_w-light.getWidth()-lightOffset_x;
        }
        if(direction==1){
            fish_x=FishPanel.width;
            fish_sx1=0;
            fish_sy1=0;
            fish_sx2=fish_w;
            fish_sy2=fish_h;
        }
    }
    public void move(){
        fish_y+=3;
        if(direction==0){
            fish_x+=fish_speed+randomNumber(0,fish_x_movement);
        }
        if(direction==1){
            fish_x-=fish_speed+randomNumber(0,fish_x_movement);
        }
        fish_cx=fish_x+fish_w/(2*scale);
        fish_cy=fish_y+fish_h/(2*scale);
    }
    public int[]collide(){
        if(collision){
            collision=false;
            return collision_data;
        }
        else{
            int[]empty=new int[5];
            return empty;
        }
    }
    private int[] loadArray(int END,int EAT,int SCORE,int LIGHT,int COLOR){
        collision_data=new int[5];
        collision_data[0]=END;
        collision_data[1]=EAT;
        collision_data[2]=SCORE;
        collision_data[3]=LIGHT;
        collision_data[4]=COLOR;
        return collision_data;
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
    public void draw(Graphics g){
        animation++;
        if(animation>=frames*5)
            animation=0;
        for(int i=0;i<frames;i++){
            if(animation>=5*i&&animation<5*(i+1))
                g.drawImage(fish[i],fish_x,fish_y,fish_x+fish_w/scale,fish_y+fish_h/scale,fish_sx1,fish_sy1,fish_sx2,fish_sy2,null);
        }
    }
    public void drawLight(Graphics g){
        g.drawImage(light,fish_x+lightOffset_x,fish_y+lightOffset_y,null);
    }
    public static int randomNumber(int low,int high){
        return low+(int)(Math.random()*(high-low+1));
    }
}
