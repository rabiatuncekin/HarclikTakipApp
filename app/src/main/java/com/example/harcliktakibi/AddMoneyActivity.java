package com.example.harcliktakibi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMoneyActivity extends AppCompatActivity {

    int userId;

    EditText etAmount;
    Button btnSaveMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        userId = getIntent().getIntExtra(
                "user_id",
                1);

        etAmount = findViewById(R.id.etAmount);
        btnSaveMoney = findViewById(R.id.btnSaveMoney);

        btnSaveMoney.setOnClickListener(v -> {

            String amount = etAmount.getText().toString();

            if(amount.isEmpty()){
                Toast.makeText(
                        AddMoneyActivity.this,
                        "Bir miktar giriniz!",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            ApiService apiService =
                    RetrofitClient.getClient().create(ApiService.class);

            Call<String> call =
                    apiService.addMoney(
                            userId,
                            Double.parseDouble(amount)
                    );

            call.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call,
                                       Response<String> response) {

                    if(response.isSuccessful()){

                        String result = response.body();

                        if(result.equals("success")){

                            Toast.makeText(
                                    AddMoneyActivity.this,
                                    "Harçlık eklendi!",
                                    Toast.LENGTH_SHORT
                            ).show();

                            finish();

                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call,
                                      Throwable t) {

                    Toast.makeText(
                            AddMoneyActivity.this,
                            "Bağlantı hatası!",
                            Toast.LENGTH_SHORT
                    ).show();

                }
            });

        });

    }
}