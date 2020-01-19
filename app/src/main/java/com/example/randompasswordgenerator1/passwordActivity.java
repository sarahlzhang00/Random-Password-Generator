package com.example.randompasswordgenerator1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class passwordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        if (getIntent().hasExtra("com.example.pswdResult")) {
            TextView pswdResult = (TextView) findViewById(R.id.passwordResult);
            String result = getIntent().getExtras().getString("com.example.pswdResult");
            pswdResult.setText(result);
        }

    }
}
