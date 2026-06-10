package com.example.harcliktakibi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,
                    RegisterActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            ApiService apiService =
                    RetrofitClient.getClient().create(ApiService.class);

            Call<String> call =
                    apiService.loginUser(username, password);

            call.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call,
                                       Response<String> response) {

                    if (response.isSuccessful()) {

                        String result = response.body();

                        if (!result.equals("fail")) {

                            Toast.makeText(
                                    LoginActivity.this,
                                    "Giriş başarılı!",
                                    Toast.LENGTH_SHORT
                            ).show();

                            int userId = Integer.parseInt(result);

                            Intent intent = new Intent(
                                    LoginActivity.this,
                                    DashboardActivity.class);

                            intent.putExtra("user_id", userId);

                            startActivity(intent);

                            finish();

                        } else {

                            Toast.makeText(
                                    LoginActivity.this,
                                    "Kullanıcı adı veya şifre hatalı!",
                                    Toast.LENGTH_SHORT
                            ).show();

                        }
                    }

                }

                @Override
                public void onFailure(Call<String> call,
                                      Throwable t) {

                    Toast.makeText(
                            LoginActivity.this,
                            "Bağlantı hatası!",
                            Toast.LENGTH_LONG
                    ).show();

                }
            });

        });

    }
}