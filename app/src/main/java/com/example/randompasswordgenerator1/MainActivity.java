package com.example.randompasswordgenerator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private String[] specialSymbols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button generateButton = (Button) findViewById(R.id.generateButton);
        specialSymbols = new String[]{"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*",
                "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\",
                "]", "^", "_", "`", "{", "|", "}", "~"};

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText numLetters = (EditText) findViewById(R.id.numLetters);
                EditText numNums = (EditText) findViewById(R.id.numNums);
                EditText numSyms = (EditText) findViewById(R.id.numSym);
                TextView passwordResult = (TextView) findViewById(R.id.passwordResult);

                int numL = Integer.parseInt(numLetters.getText().toString());
                int numN = Integer.parseInt(numNums.getText().toString());
                int numS = Integer.parseInt(numSyms.getText().toString());

                if (numL > 20 || numN > 20 || numS > 20) {
                    displayWarning(numL, numN, numS);
                } else {

                    String pswd = generatePswd(numL, numN, numS);

                    Intent startIntent = new Intent(getApplicationContext(), passwordActivity.class);
                    startIntent.putExtra("com.example.pswdResult", pswd);
                    startActivity(startIntent);

                }

            }
        });

    }

    private void displayWarning(int numL, int numN, int numS) {

        if (numL > 20 && numN > 20 && numS > 20) {
            Toast.makeText(MainActivity.this, "Number of letters, numbers, and symbols must be less than 20.", Toast.LENGTH_LONG).show();
        } else if (numL > 20 && numN > 20) {
            Toast.makeText(MainActivity.this, "Number of letters and numbers must be less than 20.", Toast.LENGTH_LONG).show();
        } else if (numL > 20 && numS > 20) {
            Toast.makeText(MainActivity.this, "Number of letters and symbols must be less than 20.", Toast.LENGTH_LONG).show();
        } else if (numN > 20 && numS > 20) {
            Toast.makeText(MainActivity.this, "Number of numbers and symbols must be less than 20.", Toast.LENGTH_LONG).show();
        } else if (numL > 20) {
            Toast.makeText(MainActivity.this, "Number of letters must be less than 20.", Toast.LENGTH_LONG).show();
        } else if (numS > 20) {
            Toast.makeText(MainActivity.this, "Number of symbols must be less than 20.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Number of numbers must be less than 20.", Toast.LENGTH_LONG).show();
        }

    }

    protected String generatePswd(int numLetters, int numNums, int numSym) {

        StringBuilder sb = new StringBuilder();

        while (numLetters != 0 || numNums != 0 || numSym != 0) {

            Random r = new Random();
            int n = r.nextInt(3);

            if (n == 0 && numLetters > 0 || (numNums == 0 && numSym == 0)) {
                int l = r.nextInt(26) + 65;
                sb.append((char) l);
                numLetters--;
            } else if (n == 2 && numSym > 0 || (numNums == 0 && numLetters == 0)) {
                int s = r.nextInt(31);
                sb.append(specialSymbols[s]);
                numSym--;
            } else {
                int i = r.nextInt(10);
                sb.append(i);
                numNums--;
            }

        }

        return sb.toString();

    }

}
