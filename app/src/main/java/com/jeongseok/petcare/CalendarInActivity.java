package com.jeongseok.petcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

import petrov.kristiyan.colorpicker.ColorPicker;

public class CalendarInActivity extends AppCompatActivity {
    private CardView poo_img;
    private int[] healthScore ={1,1,1,1,1};
    private int idx;
    private ImageView poo_down;
    private ImageView eye_down;
    private ImageView skin_down;
    private ImageView mouth_down;
    private ImageView ears_down;
    private Button save_btn;
    private TextView eye_textView;
    private TextView skin_textView;
    private TextView mouth_textView;
    private TextView ears_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_in);

        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.deleteMyTipTable();
        mDbHelper.close();

        poo_img =(CardView)findViewById(R.id.poo_img);
        eye_down = (ImageView) findViewById(R.id.eye_down);
        skin_down = (ImageView) findViewById(R.id.skin_down);
        mouth_down = (ImageView) findViewById(R.id.mouth_down);
        ears_down = (ImageView) findViewById(R.id.ears_down);
        poo_down=(ImageView)findViewById(R.id.poo_down);
        save_btn = (Button) findViewById(R.id.calendarSave_btn);

        setSaveButton(save_btn);
        mouthState(mouth_down);
        skinState(skin_down);
        eyeState(eye_down);
        earsState(ears_down);
        poo_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PooState();
                setPooScore(idx);
            }
        });
        setPooScore(idx);

    }
    private void setDBTip(String str, int idx) {
        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.insertMyTipTable(str,idx);
        mDbHelper.close();
    }
    public void PooState() {
        final ColorPicker colorPicker = new ColorPicker(this);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#997000");
        colors.add("#A6A6A6");
        colors.add("#000000");
        colors.add("#BA2B2B");
        colors.add("#8FBD24");
        colors.add("#F2CB61");
        colors.add("#FF8224");
        colors.add("#FFFFFF");
        colors.add("#660058");
        colors.add("#C05AB2");
        colorPicker.setColors(colors)
                .setTitle("변 색깔과 비슷한 것을 골라주세요!!")
                .setRoundColorButton(true)
                .setColumns(5)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        poo_img.setBackgroundColor(color);
                        idx=position;
                     if(color==8||color==9) {
                         setDBTip("poo", position + 10);
                     }else{
                         setDBTip("poo", position + 1);
                     }
                    }
                    @Override
                    public void onCancel() {

                    }
                }).show();
    }


    private int setPooScore(int idx){
        int score=100;
        switch (idx){
            case 1: case 5:
                score+=20;
                break;
            case 6:
                score+=40;
                break;
            case 2: case 3: case 8: case 9:
                score+=80;
                break;
        }
        return score;
    }

    private void eyeState(ImageView eye_down){
        eye_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                eye_textView = (TextView)findViewById(R.id.eye_textview);
                getMenuInflater().inflate(R.menu.eye_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.eye_menu0:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",0);
                                healthScore[1]+=100;
                                break;
                            case R.id.eye_menu1:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",1);
                                healthScore[1]+=100;
                                break;
                            case R.id.eye_menu2:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",2);
                                healthScore[1]+=20;
                                break;
                            case R.id.eye_menu3:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",3);
                                healthScore[1]+=20;
                                break;
                            case R.id.eye_menu4:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",4);
                                healthScore[1]+=100;
                                break;
                            case R.id.eye_menu5:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",5);
                                healthScore[1]+=40;
                                break;
                            case R.id.eye_menu6:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",6);
                                healthScore[1]+=100;
                                break;
                            case R.id.eye_menu7:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",7);
                                healthScore[1]+=80;
                                break;
                            case R.id.eye_menu8:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",8);
                                healthScore[1]+=20;
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
    private void mouthState(ImageView mouth_down){
        mouth_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                mouth_textView = (TextView)findViewById(R.id.mouth_textview);
                getMenuInflater().inflate(R.menu.mouth_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.mouth_menu0:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",0);
                                healthScore[2]+=100;
                                break;
                            case R.id.mouth_menu1:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",1);
                                healthScore[2]+=20;
                                break;
                            case R.id.mouth_menu2:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",2);
                                healthScore[2]+=40;
                                break;
                            case R.id.mouth_menu3:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",3);
                                healthScore[2]+=80;
                                break;
                            case R.id.mouth_menu4:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",4);
                                healthScore[2]+=80;
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }


    private void skinState(ImageView skin_down){
        skin_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                skin_textView = (TextView)findViewById(R.id.skin_textview);
                getMenuInflater().inflate(R.menu.skin_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.skin_menu0:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",0);
                                healthScore[3]+=100;
                                break;
                            case R.id.skin_menu1:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",1);
                                healthScore[3]+=20;
                                break;
                            case R.id.skin_menu2:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",2);
                                healthScore[3]+=100;
                                break;
                            case R.id.skin_menu3:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",3);
                                healthScore[3]+=40;
                                break;
                            case R.id.skin_menu4:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",4);
                                healthScore[3]+=20;
                                break;
                            case R.id.skin_menu5:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",5);
                                healthScore[3]+=80;
                                break;
                            case R.id.skin_menu6:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",6);
                                healthScore[3]+=20;
                                break;
                            case R.id.skin_menu7:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",7);
                                healthScore[3]+=40;
                                break;
                            case R.id.skin_menu8:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",8);
                                healthScore[3]+=80;
                                break;
                            case R.id.skin_menu9:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",9);
                                healthScore[3]+=20;
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }



    private void earsState(ImageView ears_down){
        ears_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                ears_textView = (TextView)findViewById(R.id.ears_textView);
                getMenuInflater().inflate(R.menu.ears_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.ears_menu0:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",0);
                                healthScore[4]+=100;
                                break;
                            case R.id.ears_menu1:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",1);
                                healthScore[4]+=20;
                                break;
                            case R.id.ears_menu2:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",2);
                                healthScore[4]+=20;
                                break;
                            case R.id.ears_menu3:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",3);
                                healthScore[4]+=80;
                                break;
                            case R.id.ears_menu4:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",4);
                                healthScore[4]+=80;
                                break;
                            case R.id.ears_menu5:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",5);
                                healthScore[4]+=40;
                                break;
                            case R.id.ears_menu6:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",6);
                                healthScore[4]+=80;
                                break;
                            case R.id.ears_menu7:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",7);
                                healthScore[4]+=40;
                                break;
                            case R.id.ears_menu8:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",8);
                                healthScore[4]+=20;
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void setSaveButton(Button save_btn){
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum=0;
                for(int i=0;i<healthScore.length; i++){
                    sum+=healthScore[i];
                }
                Intent next_intent = new Intent(getApplicationContext(),TipActivity.class);
                next_intent.putExtra("healthScore",sum/healthScore.length);
                startActivity(next_intent);

            }
        });
    }
}