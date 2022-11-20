package com.example.heath_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heath_calculator.modal.Person;

import org.w3c.dom.Text;

public class UserScreen extends AppCompatActivity {
    Person person;
    TextView txtName, txtAge, txtGender, txtIntensity, txtGoal, txtHeight, txtWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        // Create object Person from the input screen
        Intent intent = getIntent();
        person = (Person) intent.getExtras().get("person");
        Button updateBtn = (Button) findViewById(R.id.update);
        Button btnBmi = (Button) findViewById(R.id.bmi);
        Button btnTdee = (Button) findViewById(R.id.tdee);


        // Set data
        txtName = (TextView) findViewById(R.id.txtName);
        txtName.setText(person.getName());

        txtAge = (TextView) findViewById(R.id.txtAge);
        txtAge.setText(person.getAge()+ "");

        txtGender = (TextView) findViewById(R.id.txtGender);
        txtGender.setText(person.getGender());

        txtHeight = (TextView) findViewById(R.id.txtHeight);
        txtHeight.setText(person.getHeight() + "");

        txtWeight = (TextView) findViewById(R.id.txtWeight);
        txtWeight.setText(person.getWeight() + "");

        txtIntensity = (TextView) findViewById(R.id.txtIntensity);
        txtIntensity.setText(person.getIntensity());

        txtGoal = (TextView) findViewById(R.id.txtGoal);
        txtGoal.setText(person.getGoal());


//        Toast.makeText(getApplicationContext(), person.getName().toString(), Toast.LENGTH_LONG).show();
        // Update
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserScreen.this, InputScreen.class);
                intent.putExtra("person", person);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        String bmiTitle = "Your BMI result is: ";
        String tdeeTitle = "Your Total Daily Energy Expenditure result is: ";

        String bmiResult = calculateBMI(person.getWeight(),person.getHeight())+ " (kg/m2)";
        String tdeeResult = calculateTdee(person) + " Calories";


        //BMI
        btnBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judgeResult = "";
                // BMI for Male and Female is different
                switch (person.getGender()){
                    case "Male":
                        if (calculateBMI(person.getWeight(),person.getHeight()) > 30.0){
                            judgeResult = "This is considered overweight";
                        }else  if (calculateBMI(person.getWeight(),person.getHeight()) < 20) {
                            judgeResult = "This is considered thin";
                        }else {
                            judgeResult = "This is considered normal";
                        }
                    break;
                    case "Female":
                        if (calculateBMI(person.getWeight(),person.getHeight()) > 25){
                            judgeResult = "This is considered overweight";
                        }else  if (calculateBMI(person.getWeight(),person.getHeight()) < 18.5) {
                            judgeResult = "This is considered thin";
                        }else {
                            judgeResult = "This is considered normal";
                        }
                    break;
                }
                // Open Result Dialog
                openDialog(Gravity.CENTER, bmiTitle, bmiResult, judgeResult );
            }
        });

        // TDEE
        btnTdee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judgeResult = "";
                double tenPercentOfTheResult = calculateTdee(person) * 0.1;
                double loseWeight = Math.round((calculateTdee(person) - tenPercentOfTheResult) * 100.0)/100.0;
                double gainWeight = Math.round((calculateTdee(person) + tenPercentOfTheResult) * 100.0)/100.0;
                switch (person.getGoal()){
                    case "Lose Weight" :
                        judgeResult = loseWeight +" calories needed for you to lose weight";
                        break;
                    case "Maintain Weight" :
                        judgeResult = calculateTdee(person) +" calories needed for you to maintain weight";
                        break;
                    case "Gain Weight" :
                        judgeResult = gainWeight +" calories needed for you to gain weight";
                        break;
                }
                openDialog(Gravity.CENTER, tdeeTitle, tdeeResult, judgeResult);
            }
        });
    }

    // Calculate BMI
    public double calculateBMI(float weight, float height){
        double result = weight/ (Math.pow((height/100),2));
        double roundedResult = Math.round(result * 100.0) /100.0;
        return roundedResult;
    }
    // Calculate TDEE

    public double calculateTdee(Person person){
        // Intensity Index
        double intensityIndex = 0;
        switch (person.getIntensity()){
            case "Low" :
                intensityIndex = 1.2;
                break;
            case "Light" :
                intensityIndex = 1.375;
                break;
            case "Moderate" :
                intensityIndex = 1.55;
                break;
            case "Active" :
                intensityIndex = 1.725;
                break;
            case "Very Active" :
                intensityIndex = 1.9;
                break;
        }
        // Calculate BMR (Basal Metabolic Rate)
        double BMR = 0;
        switch (person.getGender()){
            case "Male" :
                BMR = (13.397 * person.getWeight()) + (4.799 * person.getHeight()) - (5.677 * person.getAge()) + 88.362;
                break;
            case "Female" :
                BMR = (9.247  * person.getWeight()) + (3.098  * person.getHeight()) - (4.330  * person.getAge()) + 447.593;
                break;
        }
        double tdeeResult = Math.round(BMR * intensityIndex * 100.0) / 100.0;
        return tdeeResult;

    }

    public void openDialog(int gravity, String title, String result, String judgeResult){
        final Dialog dialog = new Dialog(UserScreen.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowManager = window.getAttributes();
        windowManager.gravity = gravity;
        window.setAttributes(windowManager);

        TextView textView = dialog.findViewById(R.id.titleDialog);
        TextView resultView = dialog.findViewById(R.id.resultDialog);
        TextView judge = dialog.findViewById(R.id.judgeDialog);
        textView.setText(title);
        resultView.setText(result);
        judge.setText(judgeResult);


        Button btnClose = dialog.findViewById(R.id.close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}