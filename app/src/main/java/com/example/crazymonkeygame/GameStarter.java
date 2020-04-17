package com.example.crazymonkeygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import gamelogik.BodyParts;
import gamelogik.GameProcess;
import gamelogik.WriteText;

import static java.lang.Thread.sleep;

public class GameStarter extends AppCompatActivity implements IMove {
    private ImageView head, body, lefthand, righthand, leftleg, rightleg;
    public static int quantitymoves = 3;
    public static int number = 0;
    public Handler handler;
    public Timer timer;
    private TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HeadAnim();
                    }
                });
            }
        });
        thread.start();
        setContentView(R.layout.activity_game_starter);
        InizalizateComponents();
        GameProcess.arrayList.clear();
        printText(WriteText.START);
        nextstep();
    }

    private void InizalizateComponents() {
        head = findViewById(R.id.head);
        body = findViewById(R.id.body);
        lefthand = findViewById(R.id.lefthand);
        righthand = findViewById(R.id.righthand);
        leftleg = findViewById(R.id.leftleg);
        rightleg = findViewById(R.id.rightleg);
        textView = findViewById(R.id.textView);

        head.setOnClickListener(getOnclickListener());
        body.setOnClickListener(getOnclickListener());
        lefthand.setOnClickListener(getOnclickListener());
        righthand.setOnClickListener(getOnclickListener());
        leftleg.setOnClickListener(getOnclickListener());
        rightleg.setOnClickListener(getOnclickListener());
    }

    public View.OnClickListener getOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.lefthand:
                        checker(BodyParts.LEFTHAND);
                        Animlefthand();
                        break;
                    case R.id.righthand:
                        checker(BodyParts.RIGHTHAND);
                        Animrighthand();
                        break;
                    case R.id.leftleg:
                        checker(BodyParts.LEFTLEG);
                        Animleftleg();
                        break;
                    case R.id.rightleg:
                        checker(BodyParts.RIGHTLEG);
                        Animrightleg();
                        break;

                }
            }
        };
        return onClickListener;
    }

    public Animation.AnimationListener getAnimationListener(final ImageView imageView, final Animation second) {
        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(second);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        return animationListener;
    }

    public void Animlefthand() {
        final Animation lefthandanimup = AnimationUtils.loadAnimation(this, R.anim.lefthandup);
        final Animation lefthandanimdown = AnimationUtils.loadAnimation(this, R.anim.lefthanddown);
        lefthandanimup.setAnimationListener(getAnimationListener(lefthand, lefthandanimdown));
        lefthand.startAnimation(lefthandanimup);
    }

    public void Animrighthand() {
        final Animation righthanddown = AnimationUtils.loadAnimation(this, R.anim.righthanddown);
        final Animation righthandup = AnimationUtils.loadAnimation(this, R.anim.righthandup);
        righthandup.setAnimationListener(getAnimationListener(righthand, righthanddown));
        righthand.startAnimation(righthandup);
    }

    public void Animleftleg() {
        final Animation leftlegup = AnimationUtils.loadAnimation(this, R.anim.leftlegup);
        final Animation leftlegdown = AnimationUtils.loadAnimation(this, R.anim.leftlegdown);
        leftlegup.setAnimationListener(getAnimationListener(leftleg, leftlegdown));
        leftleg.startAnimation(leftlegup);
    }

    public void Animrightleg() {
        final Animation rightlegup = AnimationUtils.loadAnimation(this, R.anim.rightlegup);
        final Animation rightlegdown = AnimationUtils.loadAnimation(this, R.anim.rightlegdown);
        rightlegup.setAnimationListener(getAnimationListener(rightleg, rightlegdown));
        rightleg.startAnimation(rightlegup);
    }

    public void HeadAnim() {
        final Animation headleft = AnimationUtils.loadAnimation(this, R.anim.headleft);
        final Animation headright = AnimationUtils.loadAnimation(this, R.anim.headright);
        headleft.setAnimationListener(getAnimationListener(head, headright));
        headright.setAnimationListener(getAnimationListener(head, headleft));
        head.startAnimation(headleft);
    }

    @Override
    public void ChoosetypeofAnim(BodyParts bodyParts)// выбор анимации
    {
        switch (bodyParts) {
            case LEFTLEG:
                Animleftleg();
                break;
            case RIGHTLEG:
                Animrightleg();
                break;
            case LEFTHAND:
                Animlefthand();
                break;
            case RIGHTHAND:
                Animrighthand();
                break;
        }
    }


    public void checker(BodyParts bodyParts)//проверка правильно
    // ли нажимаються конечности и проверка на количество нажатий после вызов след этапа
    {
        if(GameStarter.number < GameStarter.quantitymoves)
        {
            if(!bodyParts.equals(GameProcess.arrayList.get(GameStarter.number)))
            {
                Log.d(MainActivity.GAMELOG,"finish" + bodyParts.toString() + "!=" + GameProcess.arrayList.get(GameStarter.number).toString());
                printText(WriteText.FINISH);
                //this.finish();
            }
            else
            {
                Log.d(MainActivity.GAMELOG, bodyParts.toString() + "===" + GameProcess.arrayList.get(GameStarter.number).toString());
            }
            GameStarter.number++;
            if(GameStarter.number >= GameStarter.quantitymoves) {
                Log.d(MainActivity.GAMELOG,"next");
                GameProcess.arrayList.clear();
                GameStarter.number = 0;
                GameStarter.quantitymoves++;
                printText(WriteText.NEXTSTEP);
                nextstep();
            }

        }
    }


    public void nextstep()//поочередная демострация движений которые надо повторять + запись в масив для дальнейшего сравнения в checker
    {
        for(int i = 0;i< GameStarter.quantitymoves;i++)
        {
            int pick = new Random().nextInt(BodyParts.values().length);
            switch (BodyParts.values()[pick]) {
                case LEFTLEG:
                    GameProcess.arrayList.add(BodyParts.values()[pick]);
                    ChoosetypeofAnim(BodyParts.values()[pick]);
                    Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                    break;
                case RIGHTLEG:
                    GameProcess.arrayList.add(BodyParts.values()[pick]);
                    ChoosetypeofAnim(BodyParts.values()[pick]);
                    Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                    break;
                case RIGHTHAND:
                    GameProcess.arrayList.add(BodyParts.values()[pick]);
                    ChoosetypeofAnim(BodyParts.values()[pick]);
                    Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                    break;
                case LEFTHAND:
                    GameProcess.arrayList.add(BodyParts.values()[pick]);
                    ChoosetypeofAnim(BodyParts.values()[pick]);
                    Log.d(MainActivity.GAMELOG,BodyParts.values()[pick].toString());
                    break;
            }
        }
        Log.d(MainActivity.GAMELOG,"Arraylist");
        for(int i = 0;i < GameProcess.arrayList.size();i++)
        {
            Log.d(MainActivity.GAMELOG,GameProcess.arrayList.get(i).toString());
        }

    }
    public void printText(WriteText writeText)//выводит сообщения в textView
    {
      switch (writeText)
      {
          case MOVES:break;
          case START:
              textView.setText("Look closely at the movements, and then repeat!");break;
          case FINISH:
              textView.setText("Wrong!");break;
          case NEXTSTEP:
              textView.setText("Step" + (GameStarter.quantitymoves-2));break;
      }
    }
}


