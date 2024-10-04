package com.example.counterapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int cntrVal;
    private TextView cntrLabel;
    private Button incBtn, decBtn, resetBtn;

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initVars();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @SuppressLint("DefaultLocale")
    private void initVars(){
        this.settings = getApplicationContext().getSharedPreferences("CNTR_VAL", 0);
        this.editor = this.settings.edit();
        this.cntrVal = settings.getInt("CNTR_VAL",0);
        this.cntrLabel = findViewById(R.id.cntrLabel);
        this.incBtn = findViewById(R.id.incBtn);
        this.decBtn = findViewById(R.id.decBtn);
        this.resetBtn = findViewById(R.id.resetBtn);

        this.incBtn.setOnClickListener(btn->{
            this.cntrVal++;
            updateCntrLabel();
        });

        this.decBtn.setOnClickListener(btn->{
            this.cntrVal = Math.max(0, cntrVal-1);
            updateCntrLabel();
        });

        this.resetBtn.setOnClickListener(btn->{
            this.cntrVal = 0;
            updateCntrLabel();
        });
        this.cntrLabel.setText(String.format("%d",this.cntrVal));
    }

    @SuppressLint("DefaultLocale")
    private void updateCntrLabel(){
        this.editor.putInt("CNTR_VAL", this.cntrVal);
        this.cntrLabel.setText(String.format("%d",this.cntrVal));
        this.editor.apply();
    }
}