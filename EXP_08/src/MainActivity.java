package com.example.cdmaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private final int[] chipCode = {1, 1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputBit = findViewById(R.id.inputBit);
        Button btnProcess = findViewById(R.id.btnProcess);
        TextView resultText = findViewById(R.id.resultText);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputBit.getText().toString();

                if (input.isEmpty() || (!input.equals("0") && !input.equals("1"))) {
                    Toast.makeText(MainActivity.this, "Please enter 0 or 1", Toast.LENGTH_SHORT).show();
                    return;
                }

                int dataBit = Integer.parseInt(input);


                int d = (dataBit == 1) ? 1 : -1;


                int[] encodedSignal = new int[4];
                StringBuilder signalBuilder = new StringBuilder();
                for (int i = 0; i < chipCode.length; i++) {
                    encodedSignal[i] = d * chipCode[i];
                    signalBuilder.append(encodedSignal[i]).append(", ");
                }


                int sum = 0;
                for (int i = 0; i < encodedSignal.length; i++) {
                    sum += encodedSignal[i] * chipCode[i];
                }


                int recovery = sum / chipCode.length;
                int finalBit = (recovery == 1) ? 1 : 0;


                String output = "User Chip Code: [1, 1, -1, -1]\n" +
                        "Data to send: " + dataBit + " (mapped to " + d + ")\n\n" +
                        "Encoded Signal (Transmitted):\n[" + signalBuilder.toString().replaceAll(", $", "") + "]\n\n" +
                        "Receiver Decoding Sum: " + sum + "\n" +
                        "Normalized Result: " + recovery + "\n\n" +
                        "SUCCESS: Recovered Bit " + finalBit;

                resultText.setText(output);
            }
        });
    }
}