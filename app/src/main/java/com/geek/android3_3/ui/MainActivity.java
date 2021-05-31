package com.geek.android3_3.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geek.android3_3.R;
import com.geek.android3_3.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment()).commit();
    }


}