package com.jeongseok.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class CalendarInActivity extends AppCompatActivity {
    private ImageView back_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_in);
        back_image = (ImageView)findViewById(R.id.backbtn_image);

        backButton(back_image);

        //입관련
        findViewById(R.id.mouth_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.mouth_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.mouth_menu0:
                                break;
                            case R.id.mouth_menu1:
                                break;
                            case R.id.mouth_menu2:
                                break;
                            case R.id.mouth_menu3:
                                break;
                            case R.id.mouth_menu4:
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        //피부관련
        findViewById(R.id.skin_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.skin_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.skin_menu0:
                                break;
                            case R.id.skin_menu1:
                                break;
                            case R.id.skin_menu2:
                                break;
                            case R.id.skin_menu3:
                                break;
                            case R.id.skin_menu4:
                                break;
                            case R.id.skin_menu5:
                                break;
                            case R.id.skin_menu6:
                                break;
                            case R.id.skin_menu7:
                                break;
                            case R.id.skin_menu8:
                                break;
                            case R.id.skin_menu9:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        //eye관련
        findViewById(R.id.eye_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.eye_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.eye_menu0:
                                break;
                            case R.id.eye_menu1:
                                break;
                            case R.id.eye_menu2:
                                break;
                            case R.id.eye_menu3:
                                break;
                            case R.id.eye_menu4:
                                break;
                            case R.id.eye_menu5:
                                break;
                            case R.id.eye_menu6:
                                break;
                            case R.id.eye_menu7:
                                break;
                            case R.id.eye_menu8:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    private void backButton(ImageView back_image){
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}