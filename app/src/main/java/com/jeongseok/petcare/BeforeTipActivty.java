package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;
import com.jeongseok.petcare.localdbPet.myTipDisease;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BeforeTipActivty  extends FragmentActivity {
    private TextView todayText;
    private ImageView bf_doctorImg;
    private TextView bf_doctorText;
    private Button todayCheck_button;
    private TextView memoText;
    private myTipDisease tipVO = null;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TipAdapter tipAdapter;

    public List<myTipDisease> myTipDiseaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tipVO = tipVO.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_tip_detail);

        todayText=(TextView)findViewById(R.id.todayTv);
        bf_doctorText=(TextView)findViewById(R.id.bf_doctorText);
        bf_doctorImg = (ImageView)findViewById(R.id.bf_doctorIcon);
        todayCheck_button =(Button)findViewById(R.id.todayCheck_btn);
        memoText=(TextView)findViewById(R.id.bf_memo);

        Intent intent = getIntent();
        String today = intent.getExtras().getString("today");
        String selectday=intent.getExtras().getString("selectday");
        todayText.setText(today.toString());

        recyclerView = (RecyclerView)findViewById(R.id.tip_RecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        myTipDiseaseList = mDbHelper.selectMyTipList(selectday);
        Log.i("image", myTipDiseaseList.get(0).getImage());
        getDoctor(myTipDiseaseList.get(0).getImage());
        memoText.setText(myTipDiseaseList.get(0).getMemo());
        ArrayList<ItemTip> list = new ArrayList<>();
            for (int i = 0; i < myTipDiseaseList.size(); i++) {
                list.add(new ItemTip(myTipDiseaseList.get(i).getDisease(), myTipDiseaseList.get(i).getResult1(), myTipDiseaseList.get(i).getResult2(), myTipDiseaseList.get(i).getTip()));
            }
        mDbHelper.close();
        Log.i("list", myTipDiseaseList.toString());
        tipAdapter = new TipAdapter(this,list);
        recyclerView.setAdapter(tipAdapter);

        todayCheck_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(BeforeTipActivty.this, HomeActivity.class);
                startActivity(next_intent);
            }
        });

    }

    public void getDoctor(String str){
        Log.i("image", str);
        if( str.equals("좋음")){
            bf_doctorImg.setImageResource(R.drawable.good_ic);
            bf_doctorText.setText(str);
        }else if( str.equals("보통")){
            bf_doctorImg.setImageResource(R.drawable.normal_ic);
            bf_doctorText.setText(str);
        }else if( str.equals("나쁨")){
            bf_doctorImg.setImageResource(R.drawable.bad_ic);
            bf_doctorText.setText(str);
        }
    }



}
