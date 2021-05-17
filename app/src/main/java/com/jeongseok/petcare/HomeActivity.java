package com.jeongseok.petcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bnv = findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalendarFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.calendar:
                    selectedFragment = new CalendarFragment();
                    break;
                case R.id.statistic:
                    selectedFragment = new StatisticFragment();
                    break;
                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;
                default:
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}