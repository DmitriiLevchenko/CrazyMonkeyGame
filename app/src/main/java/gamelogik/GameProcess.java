package gamelogik;

import android.util.Log;

import com.example.crazymonkeygame.GameStarter;
import com.example.crazymonkeygame.IMove;
import com.example.crazymonkeygame.MainActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;

import static java.lang.Thread.sleep;

public class GameProcess {
    public static ArrayList<BodyParts> arrayList = new ArrayList<>();
    private IMove iMove;
    public GameProcess(IMove iMove) {
     this.iMove = iMove;
    }


    public void Showsequence() {
                for (int i = 0; i < GameStarter.quantitymoves; i++) {
                    int pick = new Random().nextInt(BodyParts.values().length);
                    switch (BodyParts.values()[pick]) {
                        case LEFTLEG:
                            GameProcess.arrayList.add(BodyParts.values()[pick]);iMove.ChoosetypeofAnim(BodyParts.values()[pick]);
                            Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                            break;
                        case RIGHTLEG:
                            GameProcess.arrayList.add(BodyParts.values()[pick]);iMove.ChoosetypeofAnim(BodyParts.values()[pick]);
                            Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                            break;
                        case RIGHTHAND:
                            GameProcess.arrayList.add(BodyParts.values()[pick]);iMove.ChoosetypeofAnim(BodyParts.values()[pick]);
                            Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                            break;
                        case LEFTHAND:
                            GameProcess.arrayList.add(BodyParts.values()[pick]);iMove.ChoosetypeofAnim(BodyParts.values()[pick]);
                            Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                            break;
                    }
                }
    }
    public void Checksequenceuser()
    {

    }
}
