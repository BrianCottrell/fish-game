package fishgame1;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
/**
 * 
 *
 * @author Brian
 */
public class FishPanel extends JPanel implements ActionListener,KeyListener,MouseListener{
    boolean start,select,selected,chosen,unlock,end,saved;
    int collision,depth,score,fishType,numberUnlocked;
    int time_count,score_count,light_timer;
    int time_interval=30;
    int selections=4;
    int select_x=FishGame.width/4;
    int select_y=FishGame.height/3;
    int select_margin=FishGame.width/7;
    int[] collision_data;
    public static int width=FishGame.width;
    public static int height=FishGame.height;
    Aquarium aquarium;
    ArrayList<Fish>fishArray;
    Cursor blankCursor;
    FishButton buttonSet;
    HighScore highScore;
    Image blank;
    JLabel score_label,depth_label,your_score,new_fish;
    JTextField nameInput;
    Point hotSpot;
    SavedData save;
    SmartFish fish;
    String name;
    Timer timer;
    Toolkit toolkit;

    public FishPanel(){
        super();
        toolkit=Toolkit.getDefaultToolkit();
        blank=toolkit.getImage("transparent.png");
        hotSpot=new Point(0,0);
        blankCursor=toolkit.createCustomCursor(blank,hotSpot,"transparent");
        this.setBackground(Color.black);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        save=new SavedData();
        aquarium=new Aquarium();
        aquarium.setVisibility(false);
        buttonSet=new FishButton(0,FishGame.height/2);
        buttonSet.setSequence(true,1);
        highScore=new HighScore();
        highScore.setVisibility(false);
        this.add(highScore.score1);
        this.add(highScore.score2);
        this.add(highScore.score3);
        this.add(highScore.score4);
        this.add(highScore.score5);
        depth_label=new JLabel("DEPTH: "+Integer.toString(depth));
        depth_label.setFont(new Font("Baveuse",Font.BOLD,25));
        depth_label.setForeground(Color.blue);
        depth_label.setBounds(50,50,350,100);
        depth_label.setVisible(false);
        score_label=new JLabel("SCORE: "+Integer.toString(score));
        score_label.setFont(new Font("Baveuse",Font.BOLD,25));
        score_label.setForeground(Color.blue);
        score_label.setBounds(50,50,350,150);
        score_label.setVisible(false);
        your_score=new JLabel();
        your_score.setFont(new Font("Baveuse",Font.BOLD,50));
        your_score.setForeground(Color.blue);
        your_score.setBounds(FishGame.width/2-10,FishGame.height/2-100,300,100);
        your_score.setVisible(false);
        new_fish=new JLabel("A new fish was added to your aquarium!");
        new_fish.setFont(new Font("Baveuse",Font.BOLD,20));
        new_fish.setForeground(Color.blue);
        new_fish.setBounds(FishGame.width/3,FishGame.height/2+100,700,100);
        new_fish.setVisible(false);
        this.add(depth_label);
        this.add(score_label);
        this.add(your_score);
        this.add(new_fish);
        nameInput=new JTextField(20);
        nameInput.setBackground(Color.lightGray);
        nameInput.setFont(new Font("Baveuse",Font.BOLD,40));
        nameInput.setBounds(FishGame.width/2-150,FishGame.height/2,300,100);
        nameInput.setEditable(true);
        nameInput.addKeyListener(this);
        nameInput.setVisible(false);
        this.add(nameInput);
//        playSound("bubbling2.wav");
        fishType=1;
        start=true;
        select=true;
        selected=true;
        saved=true;
        unlock=true;
        end=true;
    }
    private void startGame(){
        this.setBackground(Color.black);
        fish=new SmartFish(fishType);
        depth=1500;
        score=0;
        score_count=0;
        time_count=0;
        light_timer=0;
        timer=new Timer(1000/time_interval,this);
        timer.start();
        fishArray=new ArrayList<Fish>();
        playSound("bubbling2.wav");
        score_label.setVisible(false);
        this.loadGame("SaveFile.dat");
        setCursor(blankCursor);
        depth=save.loadDepth();
        score=save.loadScore();
    }
    public void saveGame(String FILE_NAME){
        try{
            ObjectOutputStream ostream=new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            ostream.writeObject(save);
            ostream.close();
        }
        catch(Exception e){
            System.out.println("Save Failed");
        }
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
    public void finishGame(){
        fishArray.clear();
        if(depth<=0){
            end=false;
            saved=false;
            this.endGame();
        }
    }
    public void endGame(){
        timer.stop();
        fish=null;
        fishArray.clear();
        depth_label.setVisible(false);
        score_label.setVisible(false);
        your_score.setText(Integer.toString(score));
        your_score.setVisible(true);
        this.setBackground(Color.black);
        this.addMouseListener(this);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        start=true;
        if(score>=100&&save.goals[10]!=true){
            save.saveGoal(10,true);
            new_fish.setVisible(true);
        }
        if(score>=500&&save.goals[11]!=true){
            save.saveGoal(11,true);
            new_fish.setVisible(true);
        }
        if(score>=300&&save.goals[12]!=true){
            save.saveGoal(12,true);
            new_fish.setVisible(true);
        }
        if(score>=200&&save.goals[1]!=true){
            save.saveGoal(1,true);
            unlock=false;
        }
        if(score>=400&&save.goals[2]!=true){
            save.saveGoal(2,true);
            unlock=false;
        }
        if(score>save.highScore[0]){
            nameInput.setVisible(true);
            buttonSet.setSequence(false,5);
            this.repaint();
        }else{
            buttonSet.setSequence(false,6);
        }
        this.saveGame("SaveFile.dat");
    }
    public void unlock(){
        if(unlock)
            this.restart();
        else{
            your_score.setVisible(false);
            buttonSet.setSequence(false,0);
            buttonSet.setUnlock(true,1);
            this.repaint();
        }
    }
    public void restart(){
        your_score.setVisible(false);
        this.setBackground(Color.black);
        this.repaint();
        this.setFocusable(true);
        buttonSet.setUnlock(false,0);
        buttonSet.setSequence(true,1);
        this.saveGame("SaveFile.dat");
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        score_count--;
        time_count++;
        light_timer--;
        if(depth%100==0)
            depth_label.setVisible(true);
        if(depth%10==5)
            depth_label.setVisible(false);
        if(depth<=30)
            finishGame();
        if(light_timer>500)
            light_timer=500;
        if(light_timer<1)
            this.setBackground(Color.black);
        if(light_timer<0)
            light_timer=0;
        if(score_count>0)
            score_label.setVisible(true);
        else{
            score_count=0;
            score_label.setVisible(false);
        }
        if(time_count>30){
            depth--;
            time_count=0;
            if(depth>1000)
                fishArray.add(new Fish(randomNumber(1,100)));
            if(depth<=1000&&depth>500)
                fishArray.add(new Fish(randomNumber(101,200)));
            if(depth<=500)
                fishArray.add(new Fish(randomNumber(201,300)));
            save.saveDepth(depth);
            save.saveScore(score);
            this.saveGame("SaveFile.dat");
        }
        for(int i=0;i<fishArray.size();i++){
            fishArray.get(i).move();
            if((fishArray.get(i).fish_x+fishArray.get(i).fish_w>fish.fish_x+fish.fish_w/2&&fishArray.get(i).fish_x<fish.fish_x+fish.fish_w/2
                    &&fishArray.get(i).fish_y+fishArray.get(i).fish_h>fish.fish_y+fish.fish_h/2&&fishArray.get(i).fish_y<fish.fish_y+fish.fish_h/2)){
                collision_data=fishArray.get(i).collide();
                if(collision_data[0]>=fish.size){
                    end=false;
                    saved=false;
                    this.endGame();
                }
                else{
                    fishArray.get(i).fish_y+=FishGame.height;
                }
                score+=collision_data[2];
                light_timer+=collision_data[3];
                if(collision_data[2]>0)
                    score_count+=20;
                if(collision_data[4]==0)
                    this.setBackground(Color.black);
                if(collision_data[4]==1)
                    this.setBackground(Color.blue);
                if(collision_data[4]==2)
                    this.setBackground(Color.yellow);
                if(collision_data[4]==3)
                    this.setBackground(Color.white);
                if(collision_data[4]==4)
                    this.setBackground(Color.green);
                if(collision_data[4]==5)
                    this.setBackground(Color.red);
            }
            if(!fishArray.isEmpty()){
                if(fishArray.get(i).fish_y>super.getHeight()
                        ||fishArray.get(i).fish_x>super.getWidth()+100
                        ||fishArray.get(i).fish_x<-fishArray.get(i).fish_w){
                    fishArray.remove(i);
                }
            }
        }
        if(fish!=null)
            fish.move();
        this.repaint();
    }
    @Override
    public void keyPressed(KeyEvent ke){
        if(fish!=null)
            fish.keyPressed(ke);
    }
    @Override
    public void keyReleased(KeyEvent ke){
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
            if(!end){
                name=nameInput.getText();
                nameInput.setVisible(false);
                new_fish.setVisible(false);
                if(score>save.highScore[0]&&!saved)
                    save.addHighScore(score,name);
                saved=true;
                score=0;
                end=true;
                this.unlock();
            }
        }
        if(ke.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
    @Override
    public void keyTyped(KeyEvent ke){
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
        buttonSet.mousePressed(e);
        aquarium.mousePressed(e);
        this.repaint();
        if(!selected)
            select=false;
        if(buttonSet.unlock){
            unlock=true;
            this.restart();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e){
        buttonSet.mouseReleased(e);
        aquarium.mouseReleased(e);
        if(buttonSet.end){
            saved=true;
            score=0;
            end=true;
            this.unlock();
        }
        if(!select){
            chosen=false;
            if(e.getX()>select_x&&e.getX()<select_x+125&&e.getY()>select_y+25&&e.getY()<select_y+100){
                fishType=1;
                chosen=true;
            }
            else if(e.getX()>select_x+select_margin&&e.getX()<select_x+select_margin+100&&e.getY()>select_y&&e.getY()<select_y+150){
                if(save.goals[1]){
                    fishType=2;
                    chosen=true;
                }
            }
            else if(e.getX()>select_x+2*select_margin&&e.getX()<select_x+2*select_margin+200&&e.getY()>select_y+50&&e.getY()<select_y+150){
                if(save.goals[2]){
                    fishType=3;
                    chosen=true;
                }
            }
            if(chosen){
                chosen=false;
                buttonSet.setSequence(false,0);
                selected=true;
                select=true;
                start=false;
                this.saveGame("SaveFile.dat");
                this.startGame();
            }
        }
        if(buttonSet.getPressedButton()==0){
            buttonSet.pressedButton=-1;
            buttonSet.loadGoals(save.goals);
            buttonSet.setSequence(false,7);
            highScore.setVisibility(false);
            save.saveDepth(1500);
            save.saveScore(0);
            selected=false;
        }
        if(buttonSet.getPressedButton()==1){
            buttonSet.pressedButton=-1;
            buttonSet.setSequence(false,0);
            highScore.setVisibility(false);
            save.saveDepth(1500);
            save.saveScore(0);
            this.saveGame("SaveFile.dat");
            this.startGame();
            start=false;
        }
        if(buttonSet.getPressedButton()==2){
            System.exit(0);
        }
        if(buttonSet.getPressedButton()==3){
            buttonSet.pressedButton=-1;
            highScore.setVisibility(false);
            if(buttonSet.aquarium){
                buttonSet.setSequence(true,1);
                aquarium.setVisibility(false);
            }else{
                buttonSet.setSequence(true,3);
                aquarium.loadGoals(save.goals);
                aquarium.setVisibility(true);
            }
        }
        if(buttonSet.getPressedButton()==4){
            buttonSet.pressedButton=-1;
            highScore.setVisibility(false);
            aquarium.setVisibility(false);
            if(buttonSet.about){
                buttonSet.setSequence(true,1);
            }else{
                buttonSet.setSequence(true,4);
            }
        }
        if(buttonSet.getPressedButton()==5){
            buttonSet.pressedButton=-1;
            aquarium.setVisibility(false);
            if(buttonSet.highScore){
                buttonSet.setSequence(true,1);
                highScore.setVisibility(false);
            }else{
                buttonSet.setSequence(true,2);
                highScore.setVisibility(true);
            }
        }
        this.repaint();
    }
    @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       if(start){
           aquarium.draw(g);
           highScore.draw(g);
           buttonSet.draw(g);
       }
       if(fishArray!=null){
           for(int i=0;i<fishArray.size();i++){
               fishArray.get(i).draw(g);
           }
       }
       if(fish!=null)
           fish.draw(g,light_timer);
       if(fishArray!=null){
           for(int i=0;i<fishArray.size();i++){
               fishArray.get(i).drawLight(g);
           }
       }
       depth_label.setText("DEPTH: "+Integer.toString(depth));
       score_label.setText("SCORE: "+Integer.toString(score));
    }
    public static int randomNumber(int low,int high){
        return low+(int)(Math.random()*(high-low+1));
    }
    public static synchronized void playSound(final String AUDIO_URL){
        new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip=AudioSystem.getClip();
                    AudioInputStream inputStream=AudioSystem.getAudioInputStream(FishGame.class.getResourceAsStream(AUDIO_URL));
                    clip.open(inputStream);
                    clip.start();
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
