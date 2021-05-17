package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    private Button editButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editButton = (Button)findViewById(R.id.editSave_btn);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(EditActivity.this,HomeActivity.class);
                startActivity(next_intent);
            }
        });
    }
}
