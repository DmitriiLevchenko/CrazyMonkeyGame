package com.example.crazymonkeygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button exit,start,rules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InizalizateComponents();



    }
    private void InizalizateComponents()
    {
        exit = findViewById(R.id.exit);
        start = findViewById(R.id.start);
        rules = findViewById(R.id.rules);

        exit.setOnClickListener(getOnclickListener());
        start.setOnClickListener(getOnclickListener());
        rules.setOnClickListener(getOnclickListener());
    }
    public View.OnClickListener getOnclickListener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.exit:
                        CreateExitIntent();break;
                    case R.id.start:
                        CreateStartIntent();break;
                    case R.id.rules:
                        CreateRulesIntent();break;

                }
            }
        };
        return  onClickListener;
    }
    public void CreateExitIntent()
    {
       this.finish();
    }
    public void CreateStartIntent()
    {
        Intent intent = new Intent(MainActivity.this,GameStarter.class);
        startActivity(intent);
    }
    public void CreateRulesIntent()
    {
        Intent intent = new Intent(MainActivity.this,Rules.class);
        startActivity(intent);
    }
}
