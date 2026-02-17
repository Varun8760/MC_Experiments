package com.example.emicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etSalary, etLoanAmount, etInterest, etTenure;
    TextView tvTaxResult, tvEMIResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      
        etSalary = findViewById(R.id.etSalary);
        tvTaxResult = findViewById(R.id.tvTaxResult);
        findViewById(R.id.btnTax).setOnClickListener(v -> calculateTax());

   
        etLoanAmount = findViewById(R.id.etLoanAmount);
        etInterest = findViewById(R.id.etInterest);
        etTenure = findViewById(R.id.etTenure);
        tvEMIResult = findViewById(R.id.tvEMIResult);
        findViewById(R.id.btnEMI).setOnClickListener(v -> calculateEMI());
    }

    private void calculateTax() {
        String input = etSalary.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Enter income first", Toast.LENGTH_SHORT).show();
            return;
        }

        double income = Double.parseDouble(input);
        double tax = 0;

        if (income <= 300000) {
            tax = 0;
        } else if (income <= 600000) {
            tax = (income - 300000) * 0.05;
        } else if (income <= 900000) {
            tax = 15000 + (income - 600000) * 0.10;
        } else if (income <= 1200000) {
            tax = 45000 + (income - 900000) * 0.15;
        } else if (income <= 1500000) {
            tax = 90000 + (income - 1200000) * 0.20;
        } else {
            tax = 150000 + (income - 1500000) * 0.30;
        }

        tvTaxResult.setText("Total Tax: ₹" + String.format("%.2f", tax));
    }

    private void calculateEMI() {
        if (etLoanAmount.getText().toString().isEmpty() || etInterest.getText().toString().isEmpty() || etTenure.getText().toString().isEmpty()) {
            Toast.makeText(this, "Fill all EMI fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double p = Double.parseDouble(etLoanAmount.getText().toString());
        double r = Double.parseDouble(etInterest.getText().toString()) / (12 * 100); // Monthly interest
        double n = Double.parseDouble(etTenure.getText().toString());

        double emi = (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        tvEMIResult.setText("EMI: ₹" + String.format("%.2f", emi) + " per month");
    }
}
