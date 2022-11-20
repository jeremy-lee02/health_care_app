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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGetStarted = (Button) findViewById(R.id.btnGetStarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(Gravity.CENTER);
            }
        });
    }

    public void openDialog(int gravity){
        final Dialog dialog = new Dialog(MainActivity.this);
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
        TextView header = dialog.findViewById(R.id.headerDialog);

        header.setText("Welcome to HappyFit!");
        textView.setText("This application will compute your body mass index (BMI) as well as your total daily energy expenditure (TDEE)." +
                "To get an accurate result, make sure you enter your information correctly.\n \n" + "Note: Pregnant women and professional athletes are not recommended to use this feature.");
        Button btnClose = dialog.findViewById(R.id.close);
        btnClose.setText("Start");
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputScreen.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}