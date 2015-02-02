package fishgame1;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.ImageIO;
/**
 *
 * @author Brian
 */
public class SmartFish implements KeyListener{
    boolean reverse;
    BufferedImage[]fish,fish_up,fish_mid,fish_side;
    BufferedImage[]shadow;
    int fish_type,speed,size;
    int x,y,fish_x,fish_y,fish_h,fish_w;
    int fish_sx1,fish_sy1,fish_sx2,fish_sy2;
    int shadow_x,shadow_y,shadow_w,shadow_h;
    int current_frame,delay_count;
    int draw_count,frames,delay;
    java.net.URL fishURL,shadowURL;

    public SmartFish(int TYPE){
        fish_type=TYPE;
        if(fish_type==1){
            frames=12;
            fish=new BufferedImage[frames];
            fish_up=new BufferedImage[frames];
            fish_mid=new BufferedImage[frames];
            fish_side=new BufferedImage[frames];
            fishURL=getClass().getResource("fishLantern1.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern2.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern3.png");
            fish[2]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern4.png");
            fish[3]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern5.png");
            fish[4]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern6.png");
            fish[5]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern7.png");
            fish[6]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern8.png");
            fish[7]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern9.png");
            fish[8]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern10.png");
            fish[9]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern11.png");
            fish[10]=loadImage(fishURL);
            fishURL=getClass().getResource("fishLantern12.png");
            fish[11]=loadImage(fishURL);
            AffineTransform transform=new AffineTransform();
            transform.rotate(Math.PI/2,fish[0].getWidth()/2+1,fish[0].getHeight()/2+1);
            AffineTransformOp op=new AffineTransformOp(transform,AffineTransformOp.TYPE_BILINEAR);
            for(int i=0;i<frames;i++){
                fish_up[i]=op.filter(fish[i],null);
            }
            shadow=new BufferedImage[5];
            shadowURL=getClass().getResource("shadow200.png");
            shadow[0]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow300.png");
            shadow[1]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow400.png");
            shadow[2]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow500.png");
            shadow[3]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow600.png");
            shadow[4]=loadImage(shadowURL);
            speed=50;
            delay=2;
            size=5;
        }
        if(fish_type==2){
            frames=4;
            fish=new BufferedImage[frames];
            fish_up=new BufferedImage[frames];
            fish_mid=new BufferedImage[frames];
            fish_side=new BufferedImage[frames];
            fishURL=getClass().getResource("fishJelly3a.png");
            fish_up[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly3b.png");
            fish_up[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly3c.png");
            fish_up[2]=loadImage(fishURL);
            fishURL=getClass().getResource("fishJelly3d.png");
            fish_up[3]=loadImage(fishURL);
            shadow=new BufferedImage[5];
            shadowURL=getClass().getResource("shadow400.png");
            shadow[0]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow500.png");
            shadow[1]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow600.png");
            shadow[2]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow700.png");
            shadow[3]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow700.png");
            shadow[4]=loadImage(shadowURL);
            speed=30;
            delay=5;
            size=1;
        }
        if(fish_type==3){
            frames=3;
            fish=new BufferedImage[frames];
            fish_up=new BufferedImage[frames];
            fish_mid=new BufferedImage[frames];
            fish_side=new BufferedImage[frames];
            fishURL=getClass().getResource("fishViperA.png");
            fish[0]=loadImage(fishURL);
            fishURL=getClass().getResource("fishViperB.png");
            fish[1]=loadImage(fishURL);
            fishURL=getClass().getResource("fishViperC.png");
            fish[2]=loadImage(fishURL);
            AffineTransform transform=new AffineTransform();
            transform.rotate(Math.PI/2,fish[0].getWidth()/2+1,fish[0].getHeight()/2+1);
            AffineTransformOp op=new AffineTransformOp(transform,AffineTransformOp.TYPE_BILINEAR);
            for(int i=0;i<frames;i++){
                fish_up[i]=op.filter(fish[i],null);
            }
            shadow=new BufferedImage[5];
            shadowURL=getClass().getResource("shadow200.png");
            shadow[0]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow200.png");
            shadow[1]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow300.png");
            shadow[2]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow300.png");
            shadow[3]=loadImage(shadowURL);
            shadowURL=getClass().getResource("shadow400.png");
            shadow[4]=loadImage(shadowURL);
            speed=60;
            delay=5;
            size=7;
        }
        fish_w=fish_up[0].getWidth();
        fish_h=fish_up[0].getHeight();
        x=FishPanel.width/2;
        y=FishPanel.height/4;
        fish_x=(FishPanel.width-fish_w)/2;
        fish_y=FishPanel.height-fish_h;
        fish_sx1=fish_w;
        fish_sy1=0;
        fish_sx2=0;
        fish_sy2=fish_h;
        shadow_w=shadow[0].getWidth();
        shadow_h=shadow[0].getHeight();
        current_frame=0;
        delay_count=0;
        reverse=false;
        AffineTransform transform=new AffineTransform();
        transform.rotate(-Math.PI/8,fish_w/2,fish_h/2);
        AffineTransformOp op=new AffineTransformOp(transform,AffineTransformOp.TYPE_BILINEAR);
        for(int i=0;i<frames;i++){
            fish_mid[i]=op.filter(fish_up[i],null);
        }
        for(int i=0;i<frames;i++){
            if(fish_type==2)
                fish_side[i]=fish_mid[i];
            else
                fish_side[i]=op.filter(fish_mid[i],null);
        }
    }
    public void draw(Graphics g,int BRIGHTNESS){
        shadow_x=fish_x-(shadow_w-fish_w)/2;
        shadow_y=fish_y-(shadow_h-fish_h)/2;
        if(BRIGHTNESS<=100)
            g.drawImage(shadow[0],shadow_x,shadow_y,null);
        if(BRIGHTNESS>100&&BRIGHTNESS<=200)
            g.drawImage(shadow[1],shadow_x,shadow_y,null);
        if(BRIGHTNESS>200&&BRIGHTNESS<=300)
            g.drawImage(shadow[2],shadow_x,shadow_y,null);
        if(BRIGHTNESS>300&&BRIGHTNESS<=400)
            g.drawImage(shadow[3],shadow_x,shadow_y,null);
        if(BRIGHTNESS>400)
            g.drawImage(shadow[4],shadow_x,shadow_y,null);
        g.setColor(Color.black);
        g.fillRect(0,0,FishGame.width,shadow_y);
        g.fillRect(0,shadow_y,shadow_x,FishGame.height-shadow_y);
        g.fillRect(0,shadow_y+shadow_h,FishGame.width,FishGame.height-shadow_y-shadow_h);
        g.fillRect(shadow_x+shadow_w,shadow_y,FishGame.width-shadow_x-shadow_w,shadow_h);
        if(draw_count==0||draw_count==1||draw_count==2||draw_count==11)
            g.drawImage(fish_mid[current_frame],fish_x,fish_y,fish_x+fish_w,fish_y+fish_h,fish_sx1,fish_sy1,fish_sx2,fish_sy2,null);
        else if(draw_count>0)
            g.drawImage(fish_side[current_frame],fish_x,fish_y,fish_x+fish_w,fish_y+fish_h,fish_sx1,fish_sy1,fish_sx2,fish_sy2,null);
        else
            g.drawImage(fish_up[current_frame],fish_x,fish_y,fish_x+fish_w,fish_y+fish_h,fish_sx1,fish_sy1,fish_sx2,fish_sy2,null);
        if(delay_count==0){
            if(reverse)
                current_frame--;
            else
                current_frame++;
            if(current_frame<0){
                current_frame=0;
                reverse=false;
            }
            if(current_frame>frames-1){
                if(fish_type==2)
                    current_frame=0;
                else{
                    current_frame=frames-1;
                    reverse=true;
                }
            }
            delay_count=delay;
        }
        if(fish_y+fish_h/2>=y)
            delay_count--;
        draw_count--;
    }
    public void move(){
        if(fish_x+fish_w/2<x)
            fish_x+=10;
        if(fish_x+fish_w/2>x)
            fish_x-=10;
        if(fish_y+fish_h/2<y)
            fish_y+=3;
        if(fish_y+fish_h/2>y)
            fish_y-=10;
        y+=3;
        fish_y+=3;
        if(fish_y>FishPanel.height-fish_h){
            fish_y=FishPanel.height-fish_h;
            y=FishPanel.height-fish_h;
        }
        if(fish_y<-2*fish_h){
            fish_y=-2*fish_h;
            y=-fish_h;
        }
        if(fish_x>FishPanel.width+fish_w){
            fish_x=FishPanel.width+fish_w;
            x=FishPanel.width+fish_w;
        }
        if(fish_x<-2*fish_w){
            fish_x=-2*fish_w;
            x=-2*fish_w;
        }
        if(x>fish_x+fish_w/2+50)
            x=fish_x+fish_w/2+50;
        if(x<fish_x+fish_w/2-50)
            x=fish_x+fish_w/2-50;
        if(y>fish_y+fish_h/2+50)
            y=fish_y+fish_h/2+50;
        if(y<fish_y+fish_h/2-50)
            y=fish_y+fish_h/2-50;
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
    @Override
    public void keyPressed(KeyEvent ke){
        y-=20;
        if(ke.getKeyCode()==KeyEvent.VK_UP){
            y-=speed;
        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN){
            y+=speed-15;
        }
        if(ke.getKeyCode()==KeyEvent.VK_LEFT){
            draw_count=10;
            x-=speed;
            fish_sx1=0;
            fish_sy1=0;
            fish_sx2=fish_w;
            fish_sy2=fish_h;
        }
        if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
            draw_count=10;
            x+=speed;
            fish_sx1=fish_w;
            fish_sy1=0;
            fish_sx2=0;
            fish_sy2=fish_h;
        }
    }
    public void keyReleased(KeyEvent ke){
    }
    public void keyTyped(KeyEvent ke){
    }
}
