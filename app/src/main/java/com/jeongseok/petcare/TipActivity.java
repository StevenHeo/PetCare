package com.jeongseok.petcare;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;
import com.jeongseok.petcare.localdbPet.myTipDisease;

import java.util.ArrayList;
import java.util.List;

public class TipActivity extends FragmentActivity {
    private ImageView doctorImg;
    private TextView doctorText;
    private Button tipCheck_button;
    private CalendarFragment calendarFragment;
    private dogDisease dogVO = null;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TipAdapter tipAdapter;

    public List<myTipDisease> dogDiseaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dogVO = dogVO.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);

        doctorImg = (ImageView)findViewById(R.id.doctorIcon);
        doctorText = (TextView)findViewById(R.id.doctorText);
        tipCheck_button =(Button)findViewById(R.id.tipCheck_btn);

        recyclerView = (RecyclerView)findViewById(R.id.tip_RecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String day = intent.getExtras().getString("day");
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        String str=setDoctor(doctorImg,doctorText);
        mDbHelper.updateImage(str,day);
        mDbHelper.close();

        mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        dogDiseaseList = mDbHelper.selectMyTipTable(day);
        ArrayList<ItemTip> list = new ArrayList<>();
       if(dogDiseaseList.size()==0){
           list.add(new ItemTip("\n?????? ????????? ????????????????","???????????? ????????? ??????????????????","?????? ????????? ????????????!",""));
        }else {
            for (int i = 0; i < dogDiseaseList.size(); i++) {

                list.add(new ItemTip(dogDiseaseList.get(i).getDisease(), dogDiseaseList.get(i).getResult1(), dogDiseaseList.get(i).getResult2(), dogDiseaseList.get(i).getTip()));
            }
        }
        mDbHelper.close();

        tipAdapter = new TipAdapter(this,list);
        recyclerView.setAdapter(tipAdapter);

        calendarFragment = new CalendarFragment();
        tipCheck_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(TipActivity.this, HomeActivity.class);
                startActivity(next_intent);
            }
        });

    }

    public String setDoctor(ImageView imageView,TextView textView){
        Intent intent = getIntent();
        String str="";
        int score = intent.getExtras().getInt("healthScore");
        if(score>=90){
            imageView.setImageResource(R.drawable.good_ic);
            str="??????";
        }else if(score>=40){
            imageView.setImageResource(R.drawable.normal_ic);
            str="??????";
        }else if(score >1) {
            imageView.setImageResource(R.drawable.bad_ic);
            str = "??????";
        }else{
            imageView.setImageResource(R.drawable.normal_ic);
            str="??????";
        }
        textView.setText(str);
        return str;
    }



}
