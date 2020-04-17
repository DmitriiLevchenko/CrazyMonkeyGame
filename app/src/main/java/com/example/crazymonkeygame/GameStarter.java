package com.example.crazymonkeygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import gamelogik.BodyParts;
interface IMove
{
    public void Animlefthand(BodyParts bodyParts);
}
public class GameStarter extends AppCompatActivity implements IMove {
    private ImageView head,body,lefthand,righthand,leftleg,rightleg;
    public static int quantitymoves = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_starter);
        InizalizateComponents();


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
    }
    private void InizalizateComponents()
    {
        head = findViewById(R.id.head);
        body = findViewById(R.id.body);
        lefthand = findViewById(R.id.lefthand);
        righthand = findViewById(R.id.righthand);
        leftleg = findViewById(R.id.leftleg);
        rightleg = findViewById(R.id.rightleg);

        head.setOnClickListener(getOnclickListener());
        body.setOnClickListener(getOnclickListener());
        lefthand.setOnClickListener(getOnclickListener());
        righthand.setOnClickListener(getOnclickListener());
        leftleg.setOnClickListener(getOnclickListener());
        rightleg.setOnClickListener(getOnclickListener());
    }
    public View.OnClickListener getOnclickListener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.lefthand:
                        Animlefthand();break;
                    case R.id.righthand:
                        Animrighthand();break;
                    case R.id.leftleg:
                        Animleftleg();break;
                    case R.id.rightleg:
                        Animrightleg();break;

                }
            }
        };
        return  onClickListener;
    }
    public Animation.AnimationListener getAnimationListener(final ImageView imageView, final Animation second)
    {
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
    public void Animlefthand()
    {
        final Animation lefthandanimup = AnimationUtils.loadAnimation(this, R.anim.lefthandup);
        final Animation lefthandanimdown = AnimationUtils.loadAnimation(this, R.anim.lefthanddown);
        lefthandanimup.setAnimationListener(getAnimationListener(lefthand,lefthandanimdown));
        lefthand.startAnimation(lefthandanimup);
    }
    public void Animrighthand()
    {
        final Animation righthanddown = AnimationUtils.loadAnimation(this, R.anim.righthanddown);
        final Animation righthandup = AnimationUtils.loadAnimation(this, R.anim.righthandup);
        righthandup.setAnimationListener(getAnimationListener(righthand,righthanddown));
        righthand.startAnimation(righthandup);
    }
    public void Animleftleg()
    {
        final Animation leftlegup = AnimationUtils.loadAnimation(this, R.anim.leftlegup);
        final Animation leftlegdown = AnimationUtils.loadAnimation(this, R.anim.leftlegdown);
        leftlegup.setAnimationListener(getAnimationListener(leftleg,leftlegdown));
        leftleg.startAnimation(leftlegup);
    }
    public void Animrightleg()
    {
        final Animation rightlegup = AnimationUtils.loadAnimation(this, R.anim.rightlegup);
        final Animation rightlegdown = AnimationUtils.loadAnimation(this, R.anim.rightlegdown);
        rightlegup.setAnimationListener(getAnimationListener(rightleg,rightlegdown));
        rightleg.startAnimation(rightlegup);
    }
    public void HeadAnim()
    {
        final Animation headleft = AnimationUtils.loadAnimation(this, R.anim.headleft);
        final Animation headright = AnimationUtils.loadAnimation(this, R.anim.headright);
        headleft.setAnimationListener(getAnimationListener(head,headright));
        headright.setAnimationListener(getAnimationListener(head,headleft));
        head.startAnimation(headleft);
    }

    @Override
    public void Animlefthand(BodyParts bodyParts) {
        switch (bodyParts)
        {
            case LEFTLEG:
                Animleftleg();break;
            case RIGHTLEG:
                Animrightleg();break;
            case LEFTHAND:
                Animleftleg();break;
            case RIGHTHAND:
                Animrightleg();break;
        }
    }
}
