package com.app.fish.catchreport;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;

public class AddFishActivity extends AppCompatActivity {

    private static final String FISH_LAKES_DB = "FishAndLakes.db";
    private ArrayList<Fish> fishArrayList;
    private int cur;
    private Button addButton;
    private Button prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fishArrayList = new ArrayList<Fish>();
        this.cur = 0;
        this.fishArrayList.add(this.cur, new Fish());
        AddFishFragment fishFragment = AddFishFragment.newInstance(this.fishArrayList.get(this.cur));
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(R.id.fragHolder, fishFragment, "FISH_FRAGMENT");
        trans.commit();

        this.prevButton = (Button) findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur--;
                Fish f = null;
                if(cur>0){
                    f = fishArrayList.get(cur);
                }

                switchFish(f);
                checkButtons();
            }
        });


        this.addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fish f;
                cur++;
                if(cur == fishArrayList.size())
                {
                    f = new Fish();
                    fishArrayList.add(cur,f);
                }
                else
                {
                    f = fishArrayList.get(cur);
                }

                switchFish(f);
                checkButtons();
            }
        });

        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void switchFish(Fish f){
        AddFishFragment fishFragment = AddFishFragment.newInstance(this.fishArrayList.get(this.cur));
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.replace(R.id.fragHolder, fishFragment, "FISH_FRAGMENT");
        trans.commit();
    }


    private void checkButtons(){
        if(this.cur > 0){
            this.prevButton.setVisibility(View.VISIBLE);
        }
        else
        {
            this.prevButton.setVisibility(View.INVISIBLE);
        }
        if(this.cur < this.fishArrayList.size() - 1)
        {
            this.addButton.setText("Next");
        }
        else
        {
            this.addButton.setText("Add Fish");
        }
    }


}
