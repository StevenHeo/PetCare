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

import java.util.ArrayList;
import java.util.List;

public class TipActivity extends FragmentActivity {

    Button tipCheck_button;
    CalendarFragment calendarFragment;
    dogDisease dogVO = null;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TipAdapter tipAdapter;

    public List<dogDisease> dogDiseaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dogVO = dogVO.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        tipCheck_button =(Button)findViewById(R.id.tipCheck_btn);;

        recyclerView = (RecyclerView)findViewById(R.id.tip_RecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        dogDiseaseList = mDbHelper.selectMyTipTable();
        ArrayList<ItemTip> list = new ArrayList<>();
        if(dogDiseaseList.size()==0){
            list.add(new ItemTip("상태 진단을 원하신다면?","이전 화면으로 돌아가 \n강아지의 현재 상태를 선택해주세요!"));
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
                DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
                mDbHelper.createDatabase();
                mDbHelper.open();
                mDbHelper.updateMyTipTable();
                mDbHelper.close();
                startActivity(next_intent);
            }
        });

    }



}
