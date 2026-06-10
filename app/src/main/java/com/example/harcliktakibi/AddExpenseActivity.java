package com.example.harcliktakibi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExpenseActivity extends AppCompatActivity {

    int userId;

    EditText etCategory, etExpense;
    Button btnSaveExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etCategory = findViewById(R.id.etCategory);
        etExpense = findViewById(R.id.etExpense);
        btnSaveExpense = findViewById(R.id.btnSaveExpense);

        userId = getIntent().getIntExtra(
                "user_id",
                1);

        btnSaveExpense.setOnClickListener(v -> {

            String category = etCategory.getText().toString();
            String amount = etExpense.getText().toString();

            ApiService apiService =
                    RetrofitClient.getClient().create(ApiService.class);

            Call<String> call = apiService.addExpense(
                    userId,
                    Double.parseDouble(amount),
                    category
            );

            call.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call,
                                       Response<String> response) {

                    if (response.isSuccessful()
                            && response.body() != null
                            && response.body().equals("success")) {

                        Toast.makeText(
                                AddExpenseActivity.this,
                                "Harcama eklendi!",
                                Toast.LENGTH_SHORT
                        ).show();

                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call,
                                      Throwable t) {

                    Toast.makeText(
                            AddExpenseActivity.this,
                            "Bağlantı hatası!",
                            Toast.LENGTH_SHORT
                    ).show();

                }
            });

        });

    }
}