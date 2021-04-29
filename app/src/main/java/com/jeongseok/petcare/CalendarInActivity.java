package com.jeongseok.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class CalendarInActivity extends AppCompatActivity {
    private ImageView back_image;
    private TextView eye_textView;
    private TextView skin_textView;
    private TextView mouth_textView;
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
                mouth_textView = (TextView)findViewById(R.id.mouth_textview);
                getMenuInflater().inflate(R.menu.mouth_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.mouth_menu0:
                                mouth_textView.setText(item.getTitle());
                                break;
                            case R.id.mouth_menu1:
                                mouth_textView.setText(item.getTitle());
                                break;
                            case R.id.mouth_menu2:
                                mouth_textView.setText(item.getTitle());
                                break;
                            case R.id.mouth_menu3:
                                mouth_textView.setText(item.getTitle());
                                break;
                            case R.id.mouth_menu4:
                                mouth_textView.setText(item.getTitle());
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
                skin_textView = (TextView)findViewById(R.id.skin_textview);
                getMenuInflater().inflate(R.menu.skin_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.skin_menu0:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu1:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu2:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu3:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu4:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu5:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu6:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu7:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu8:
                                skin_textView.setText(item.getTitle());
                                break;
                            case R.id.skin_menu9:
                                skin_textView.setText(item.getTitle());
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
                eye_textView = (TextView)findViewById(R.id.eye_textview);
                getMenuInflater().inflate(R.menu.eye_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch( item.getItemId() ){
                            case R.id.eye_menu0:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu1:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu2:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu3:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu4:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu5:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu6:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu7:
                                eye_textView.setText(item.getTitle());
                                break;
                            case R.id.eye_menu8:
                                eye_textView.setText(item.getTitle());
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