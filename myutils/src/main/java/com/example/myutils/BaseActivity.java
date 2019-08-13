package com.example.myutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myutils.interfaces.ActivityInitInterface;

public class BaseActivity extends AppCompatActivity implements ActivityInitInterface, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initToolBar(getClass().getSimpleName());
        initDate();
        initOnClick();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

    }

    @Override
    public void initOnClick() {

    }

    @Override
    public void initToolBar(String title) {
        getSupportActionBar().setTitle(title);
    }
}
