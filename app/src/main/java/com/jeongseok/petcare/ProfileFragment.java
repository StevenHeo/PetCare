package com.jeongseok.petcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;

import java.util.List;

public class ProfileFragment extends Fragment {
    private View v;
    public List<dogDisease> dogDiseaseList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        //db테스트
        TextView textView = v.findViewById(R.id.profileTextView);
        initLoadDB();
        textView.setText(dogDiseaseList.get(1).result1);
        return v;

    }
    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(this.getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        dogDiseaseList = mDbHelper.getTableData();

        mDbHelper.close();
    }

}
