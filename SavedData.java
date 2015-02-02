package fishgame1;
import java.io.*;
/**
 *
 * @author Brian
 */
public class SavedData implements Serializable{
    public boolean[] goals;
    public int[] highScore;
    public String[] name;
    public int score,depth,scoreHolder;
    public String nameHolder;

    public SavedData(){
        highScore=new int[5];
        name=new String[10];
        goals=new boolean[20];
//        goals[1]=true;
//        goals[2]=true;
    }
    public boolean addHighScore(int SCORE,String NAME){
        if(SCORE>=highScore[0]){
            highScore[0]=SCORE;
            name[0]=NAME;
            for(int i=1;i<highScore.length;i++){
                if(highScore[i-1]>highScore[i]){
                    scoreHolder=highScore[i];
                    highScore[i]=highScore[i-1];
                    highScore[i-1]=scoreHolder;
                    nameHolder=name[i];
                    name[i]=name[i-1];
                    name[i-1]=nameHolder;
                }
            }
            return true;
        }
        else
            return false;
    }
    public void saveName(String NAME){
        name[0]=NAME;
    }
    public void saveDepth(int DEPTH){
        depth=DEPTH;
    }
    public void saveScore(int SCORE){
        score=SCORE;
    }
    public void saveGoal(int GOAL,boolean COMPLETED){
        goals[GOAL]=COMPLETED;
    }
    public String loadName(){
        return name[0];
    }
    public int loadDepth(){
        return depth;
    }
    public int loadScore(){
        return score;
    }
    public boolean loadGoal(int GOAL){
        return goals[GOAL];
    }
}
