package com.example.heath_calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.heath_calculator.modal.Person;

public class InputScreen extends AppCompatActivity {
    int age;
    int updatedAge;
    String goal;
    String intense;
    String gender;
    Person person;
    LinearLayout step1;
    LinearLayout step2;
    LinearLayout step3;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);


        Button buttonAdd = (Button) findViewById(R.id.btnAdd);
        Button buttonMinus = (Button) findViewById(R.id.btnMinus);
        Button buttonBack =  (Button) findViewById(R.id.back);
        Button buttonNext = (Button) findViewById(R.id.next);
        Button buttonFinish = (Button) findViewById(R.id.finish);
        EditText inputAge = (EditText) findViewById(R.id.inputAge);
        EditText inputName = (EditText) findViewById(R.id.inputName);
        EditText height = (EditText) findViewById(R.id.inputHeight);
        EditText weight = (EditText) findViewById(R.id.inputWeight);
        step1 = (LinearLayout) findViewById(R.id.step1);
        step2 = (LinearLayout) findViewById(R.id.step2);
        step3 = (LinearLayout) findViewById(R.id.step3);


        //Dropdown Intensity
        Spinner dropdown1 = findViewById(R.id.spinner);
        String[] intensity = new String[]{"Low", "Light", "Moderate", "Active", "Very Active"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.layout_spinner, intensity);
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown);
        dropdown1.setAdapter(adapter1);



        //Dropdown Gender
        Spinner dropdown2 = findViewById(R.id.spinnerGender);
        String[] genders = new String[]{"Male", "Female"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.layout_spinner, genders);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown);
        dropdown2.setAdapter(adapter2);


        //DropDown Goals
        Spinner dropdown3 = findViewById(R.id.spinnerGoals);
        String[] goals = new String[]{"Lose Weight", "Maintain Weight", "Gain Weight"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.layout_spinner, goals);
        adapter3.setDropDownViewResource(R.layout.spinner_dropdown);
        dropdown3.setAdapter(adapter3);


        // Add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputAge.getText().toString().equals("")){
                    age = Integer.parseInt(inputAge.getText().toString());
                }else {
                    age = 0;
                }
                updatedAge = age + 1;
                inputAge.setText(updatedAge + "");
            }
        });
        // Minus Button
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputAge.getText().toString().equals("")){
                    age = Integer.parseInt(inputAge.getText().toString());
                }else {
                    age = 0;
                }
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
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputScreen.this);
                    alertDialog.setTitle("Are you sure to Exit");
                    alertDialog.setMessage("Exiting will delete all your data");
                    // Exist if select yes
                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(InputScreen.this, MainActivity.class);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                    // Nothing happen
                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog.show();
                    buttonFinish.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.VISIBLE);
                }
                if (step2.getVisibility() == view.VISIBLE){
                    step2.setVisibility(View.GONE);
                    step1.setVisibility(View.VISIBLE);
                    step3.setVisibility(View.GONE);
                    buttonFinish.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.VISIBLE);
                }
                if (step3.getVisibility() == view.VISIBLE){
                    step2.setVisibility(View.VISIBLE);
                    step1.setVisibility(View.GONE);
                    step3.setVisibility(View.GONE);
                    buttonFinish.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.VISIBLE);
                }
            }
        });

        //Next Button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (step1.getVisibility() == View.VISIBLE){
                    if (inputName.getText().toString().equals("") || Integer.parseInt( inputAge.getText().toString()) < 10){
                        step1.setVisibility(View.VISIBLE);
                        step2.setVisibility(View.GONE);
                        step3.setVisibility(View.GONE);
                        Toast.makeText(InputScreen.this,"Please enter your name and age must larger than 10",Toast.LENGTH_LONG).show();
                    }else {
                        step1.setVisibility(View.GONE);
                        step2.setVisibility(View.VISIBLE);
                        step3.setVisibility(View.GONE);
                    }
                }else if (step2.getVisibility() == View.VISIBLE){
                    float heightValue = Float.parseFloat(height.getText().toString());
                    float weightValue = Float.parseFloat(weight.getText().toString());
                    if ((heightValue > 300 && heightValue < 100) || weightValue < 10){
                        step3.setVisibility(View.GONE);
                        step2.setVisibility(View.VISIBLE);
                        step1.setVisibility(View.GONE);
                    }else{
                        step3.setVisibility(View.VISIBLE);
                        step2.setVisibility(View.GONE);
                        step1.setVisibility(View.GONE);
                    }
                }
                if (step3.getVisibility() == View.VISIBLE) {
                    step1.setVisibility(View.GONE);
                    step2.setVisibility(View.GONE);
                    buttonFinish.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.GONE);
                }
            }
        });
        // Finish Button
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Name, Age, Height, Weight, Gender, Intensity, Goal
                Intent intent = new Intent(InputScreen.this, UserScreen.class);
                intense = dropdown1.getSelectedItem().toString();
                goal = dropdown3.getSelectedItem().toString();
                gender = dropdown2.getSelectedItem().toString();

                // Create person object
                person = new Person(
                        inputName.getText().toString(),Integer.parseInt(inputAge.getText().toString()),
                        Float.parseFloat(weight.getText().toString()),Float.parseFloat(height.getText().toString()),
                        gender,intense,goal);
                intent.putExtra("person", person);
                startActivityForResult(intent, 100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK){
                EditText inputAge = (EditText) findViewById(R.id.inputAge);
                EditText inputName = (EditText) findViewById(R.id.inputName);
                EditText height = (EditText) findViewById(R.id.inputHeight);
                EditText weight = (EditText) findViewById(R.id.inputWeight);
                step1 = (LinearLayout) findViewById(R.id.step1);
                step2 = (LinearLayout) findViewById(R.id.step2);
                step3 = (LinearLayout) findViewById(R.id.step3);
                Button buttonNext = (Button) findViewById(R.id.next);
                Button buttonFinish = (Button) findViewById(R.id.finish);
                Person updatePerson = (Person) data.getExtras().get("person");
                System.out.println(updatePerson.toString());
                inputName.setText(updatePerson.getName());
                inputAge.setText(updatePerson.getAge() + "");
                height.setText(updatePerson.getHeight()+"");
                weight.setText(updatePerson.getWeight()+ "");
                step1.setVisibility(View.VISIBLE);
                step2.setVisibility(View.GONE);
                step3.setVisibility(View.GONE);
                buttonFinish.setVisibility(View.GONE);
                buttonNext.setVisibility(View.VISIBLE);
            }
        }

    }
}