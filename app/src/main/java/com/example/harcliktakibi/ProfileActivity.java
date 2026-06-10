package com.example.harcliktakibi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView tvFullName, tvUsername, tvEmail;
    Button btnLogout;

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvFullName = findViewById(R.id.tvFullName);
        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);

        userId = getIntent().getIntExtra(
                "user_id",
                1);

        ApiService apiService =
                RetrofitClient.getClient().create(ApiService.class);

        Call<ProfileModel> call =
                apiService.getProfile(userId);

        call.enqueue(new Callback<ProfileModel>() {

            @Override
            public void onResponse(Call<ProfileModel> call,
                                   Response<ProfileModel> response) {

                if(response.isSuccessful()
                        && response.body()!=null){

                    tvFullName.setText(
                            response.body().getFullname());

                    tvUsername.setText(
                            response.body().getUsername());

                    tvEmail.setText(
                            response.body().getEmail());

                }

            }

            @Override
            public void onFailure(Call<ProfileModel> call,
                                  Throwable t) {

            }
        });

        btnLogout.setOnClickListener(v -> {

            new AlertDialog.Builder(ProfileActivity.this)
                    .setTitle("Çıkış Yap")
                    .setMessage("Çıkış yapmak istediğinize emin misiniz?")
                    .setPositiveButton("Evet",
                            (dialog, which) -> {

                                Intent intent =
                                        new Intent(
                                                ProfileActivity.this,
                                                LoginActivity.class);

                                intent.addFlags(
                                        Intent.FLAG_ACTIVITY_NEW_TASK
                                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);

                                finish();

                            })
                    .setNegativeButton("Hayır", null)
                    .show();

        });

    }
}