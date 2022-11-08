package com.example.heath_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class InputScreen extends AppCompatActivity {
    int age;
    int updatedAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);

        Button buttonAdd = (Button) findViewById(R.id.btnAdd);
        Button buttonMinus = (Button) findViewById(R.id.btnMinus);
        Button buttonNext = (Button) findViewById(R.id.next);
        Button buttonBack =  (Button) findViewById(R.id.back);
        EditText inputAge = (EditText) findViewById(R.id.inputAge);
        EditText inputName = (EditText) findViewById(R.id.inputName);
        LinearLayout step1 = (LinearLayout) findViewById(R.id.step1);
        LinearLayout step2 = (LinearLayout) findViewById(R.id.step2);

        //Dropdown Intensity
        Spinner dropdown1 = findViewById(R.id.spinner);
        String[] intensity = new String[]{"Low", "Light", "Moderate", "Active", "Very Active"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, intensity);

        dropdown1.setAdapter(adapter1);

        //Dropdown Gender
        Spinner dropdown2 = findViewById(R.id.spinnerGender);
        String[] genders = new String[]{"Male", "Female"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        dropdown2.setAdapter(adapter2);

        //DropDown Goals
        Spinner dropdown3 = findViewById(R.id.spinnerGoals);
        String[] goals = new String[]{"Lose Weight", "Maintain Weight", "Gain Weight"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, goals);
        dropdown3.setAdapter(adapter3);


        // Add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = Integer.parseInt(inputAge.getText().toString());
                updatedAge = age + 1;
                inputAge.setText(updatedAge + "");
            }
        });
        // Minus Button
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = Integer.parseInt(inputAge.getText().toString());
                if (age > 0){
                    updatedAge = age - 1;
                    inputAge.setText(updatedAge + "");
                }else {
                    inputAge.setText("0");
                }
            }
        });

        //Back Button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (step1.getVisibility() == View.VISIBLE){
                    Intent intent = new Intent(InputScreen.this, MainActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                if (step2.getVisibility() == view.VISIBLE){
                    step2.setVisibility(View.GONE);
                    step1.setVisibility(View.VISIBLE);
                }
            }
        });

        //Next Button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (step1.getVisibility() == View.VISIBLE){
                    step1.setVisibility(View.GONE);
                    step2.setVisibility(View.VISIBLE);
                }
                if (step2.getVisibility() == View.VISIBLE){
                    buttonNext.setText(R.string.finish);
                }
            }
        });
    }
}