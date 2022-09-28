package com.hakancevik.storingdata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private TextView textView;
    private SharedPreferences sharedPreferences;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.hakancevik.storingdata", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("storedAge", 0);

        if (storedAge == 0) {
            textView.setText("Your age: ");
        } else {
            textView.setText("Your age: " + storedAge);
        }
    }


    public void save(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        alert.setTitle("Save");
        alert.setMessage("Are you sure?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (!editTextNumber.getText().toString().matches("")) {
                    int userAge = Integer.parseInt(editTextNumber.getText().toString());
                    textView.setText("Your age: " + userAge);

                    sharedPreferences.edit().putInt("storedAge", userAge).apply();
                    Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();

                } else {
                    textView.setText("Please, Enter age!");
                }

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Not saved!", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();

    }


    public void delete(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        alert.setTitle("Delete");
        alert.setMessage("Are you sure?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int userAge = sharedPreferences.getInt("storedAge", 0);

                if (userAge != 0) {
                    sharedPreferences.edit().remove("storedAge").apply();
                    textView.setText("Your Age: ");
                    Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Deleting is canceled!", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();

    }


}