package com.example.harcliktakibi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullName, etUsername, etEmail, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.etFullName);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            String fullname = etFullName.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            ApiService apiService =
                    RetrofitClient.getClient().create(ApiService.class);

            Call<String> call =
                    apiService.registerUser(
                            fullname,
                            username,
                            email,
                            password
                    );

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call,
                                       Response<String> response) {

                    if (response.isSuccessful()) {

                        String result = response.body();

                        if (result.equals("success")) {

                            Toast.makeText(
                                    RegisterActivity.this,
                                    "Kayıt başarılı!",
                                    Toast.LENGTH_SHORT
                            ).show();

                            finish();

                        } else {

                            Toast.makeText(
                                    RegisterActivity.this,
                                    "Kayıt başarısız!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    Toast.makeText(
                            RegisterActivity.this,
                            "Bağlantı hatası!",
                            Toast.LENGTH_LONG
                    ).show();

                }
            });

        });

    }
}