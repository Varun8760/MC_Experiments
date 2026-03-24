package com.example.gsm;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputRAND = findViewById(R.id.inputRAND);
        final EditText inputKi = findViewById(R.id.inputKi);
        final EditText inputMsg = findViewById(R.id.inputMsg);
        final Button btnRun = findViewById(R.id.btnRunSecurity);
        final TextView txtA3 = findViewById(R.id.txtA3);
        final TextView txtA8 = findViewById(R.id.txtA8);
        final TextView txtA5 = findViewById(R.id.txtA5);

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String randStr = inputRAND.getText().toString();
                String kiStr = inputKi.getText().toString();
                String msgStr = inputMsg.getText().toString();

                if (randStr.length() != 8 || kiStr.length() != 8 || msgStr.length() != 8) {
                    Toast.makeText(MainActivity.this, "All inputs must be 8-bit binary!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int rand = Integer.parseInt(randStr, 2);
                int ki = Integer.parseInt(kiStr, 2);
                int msg = Integer.parseInt(msgStr, 2);

                int sres = rand ^ ki;
                txtA3.setText("A3 SRES: " + String.format("%8s", Integer.toBinaryString(sres)).replace(' ', '0'));

                int kc = Integer.rotateLeft(rand ^ ki, 3) & 0xFF;
                txtA8.setText("A8 Kc: " + String.format("%8s", Integer.toBinaryString(kc)).replace(' ', '0'));

                int ciphertext = msg ^ kc;
                txtA5.setText("A5 CIPHERTEXT: " + String.format("%8s", Integer.toBinaryString(ciphertext)).replace(' ', '0'));
            }
        });
    }
}