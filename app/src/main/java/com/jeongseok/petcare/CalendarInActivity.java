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
        //입관련
        mouthState(mouth_down);
        //피부관련
        skinState(skin_down);
        //eye관련
        eyeState(eye_down);
        //ears관련
        earsState(ears_down);
        //poo 관련
        poo_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();

            }
        });


    }
    private void setDBTip(String str, int idx) {
        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.insertMyTipTable(str,idx);
        mDbHelper.close();
    }
    public void openColorPicker() {
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
                .setDefaultColorButton(R.color.pink)
                .setTitle("변 색깔과 비슷한 것을 골라주세요!!")
                .setColumns(5)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        poo_img.setBackgroundColor(color);
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

    private void setSaveButton(Button save_btn){
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(getApplicationContext(),TipActivity.class);
                startActivity(next_intent);

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
                                break;
                            case R.id.mouth_menu1:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",1);
                                break;
                            case R.id.mouth_menu2:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",2);
                                break;
                            case R.id.mouth_menu3:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",3);
                                break;
                            case R.id.mouth_menu4:
                                mouth_textView.setText(item.getTitle());
                                setDBTip("mouth",4);
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
                                break;
                            case R.id.skin_menu1:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",1);
                                break;
                            case R.id.skin_menu2:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",2);
                                break;
                            case R.id.skin_menu3:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",3);
                                break;
                            case R.id.skin_menu4:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",4);
                                break;
                            case R.id.skin_menu5:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",5);
                                break;
                            case R.id.skin_menu6:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",6);
                                break;
                            case R.id.skin_menu7:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",7);
                                break;
                            case R.id.skin_menu8:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",8);
                                break;
                            case R.id.skin_menu9:
                                skin_textView.setText(item.getTitle());
                                setDBTip("skin",9);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
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
                                break;
                            case R.id.eye_menu1:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",1);
                                break;
                            case R.id.eye_menu2:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",2);
                                break;
                            case R.id.eye_menu3:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",3);
                                break;
                            case R.id.eye_menu4:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",4);
                                break;
                            case R.id.eye_menu5:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",5);
                                break;
                            case R.id.eye_menu6:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",6);
                                break;
                            case R.id.eye_menu7:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",7);
                                break;
                            case R.id.eye_menu8:
                                eye_textView.setText(item.getTitle());
                                setDBTip("eye",8);
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
                                break;
                            case R.id.ears_menu1:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",1);
                                break;
                            case R.id.ears_menu2:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",2);
                                break;
                            case R.id.ears_menu3:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",3);
                                break;
                            case R.id.ears_menu4:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",4);
                                break;
                            case R.id.ears_menu5:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",5);
                                break;
                            case R.id.ears_menu6:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",6);
                                break;
                            case R.id.ears_menu7:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",7);
                                break;
                            case R.id.ears_menu8:
                                ears_textView.setText(item.getTitle());
                                setDBTip("ears",8);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

}