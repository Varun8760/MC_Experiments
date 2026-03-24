package com.example.guiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editName = findViewById(R.id.editName);
        final SeekBar seekAge = findViewById(R.id.seekAge);
        final TextView labelAge = findViewById(R.id.labelAge);
        final RadioGroup genderGroup = findViewById(R.id.genderGroup);
        final Spinner spinnerStream = findViewById(R.id.spinnerStream);
        final Button btnSubmit = findViewById(R.id.btnSubmit);

        String[] streams = {"Computer Science", "Information Technology", "Electronics", "Mechanical"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, streams);
        spinnerStream.setAdapter(adapter);

        seekAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                labelAge.setText("Age: " + (progress + 15));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String age = labelAge.getText().toString();
                String stream = spinnerStream.getSelectedItem().toString();

                int selectedId = genderGroup.getCheckedRadioButtonId();
                String gender = "Not Selected";
                if (selectedId != -1) {
                    gender = ((RadioButton) findViewById(selectedId)).getText().toString();
                }

                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Registration Successful")
                        .setMessage("Name: " + name + "\n" + age + "\nGender: " + gender + "\nStream: " + stream)
                        .setPositiveButton("Done", null)
                        .show();
            }
        });
    }
}