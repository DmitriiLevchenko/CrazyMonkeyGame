package gamelogik;

import com.example.crazymonkeygame.GameStarter;

import java.util.ArrayList;
import java.util.Random;

public class GameProcess {
    public ArrayList<BodyParts> arrayList = new ArrayList<>();

    GameProcess() {

    }

    public void Showsequence() {
        for (int i = 0; i < GameStarter.quantitymoves; i++) {
            int pick = new Random().nextInt(BodyParts.values().length);
            switch (BodyParts.values()[pick]) {
                case LEFTLEG:
                    arrayList.add(BodyParts.values()[pick]);
            }
        }
    }
}
