package com.example.harcliktakibi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;


public class DashboardActivity extends AppCompatActivity {
    int userId;
    TextView tvUsd, tvEur, tvGbp;
    TextView tvTotalMoney, tvSpentMoney, tvRemainingMoney;
    Button btnProfile, btnAddMoney, btnAddExpense;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userId = getIntent().getIntExtra(
                "user_id",
                1);
        // TextView'ler
        tvTotalMoney = findViewById(R.id.tvTotalMoney);
        tvSpentMoney = findViewById(R.id.tvSpentMoney);
        tvRemainingMoney = findViewById(R.id.tvRemainingMoney);

        // Butonlar
        btnProfile = findViewById(R.id.btnProfile);
        btnAddMoney = findViewById(R.id.btnAddMoney);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        // Pasta grafik
        pieChart = findViewById(R.id.pieChart);

        tvUsd = findViewById(R.id.tvUsd);
        tvEur = findViewById(R.id.tvEur);
        tvGbp = findViewById(R.id.tvGbp);

        // Profil ekranı
        btnProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    ProfileActivity.class);

            intent.putExtra("user_id", userId);

            startActivity(intent);

        });

        // Harçlık ekleme ekranı
        btnAddMoney.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    AddMoneyActivity.class);

            intent.putExtra("user_id", userId);

            startActivity(intent);

        });

        // Harcama ekleme ekranı
        btnAddExpense.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    AddExpenseActivity.class);

            intent.putExtra("user_id", userId);

            startActivity(intent);

        });



        loadMoney();
        showPieChart();
        loadCurrency();


    }

    @Override
    protected void onResume() {
        super.onResume();

        loadMoney();
        pieChart = findViewById(R.id.pieChart);

        showPieChart();
    }

    private void loadMoney() {

        ApiService apiService =
                RetrofitClient.getClient().create(ApiService.class);

        Call<String> call = apiService.getMoney(userId);

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call,
                                   Response<String> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    String result = response.body();

                    String[] values = result.split(",");

                    if(values.length < 2){
                        return;
                    }

                    double totalMoney =
                            Double.parseDouble(values[0]);

                    double spentMoney =
                            Double.parseDouble(values[1]);

                    double remainingMoney =
                            totalMoney - spentMoney;

                    tvTotalMoney.setText(totalMoney + " TL");
                    tvSpentMoney.setText(spentMoney + " TL");
                    tvRemainingMoney.setText(remainingMoney + " TL");

                }

            }

            @Override
            public void onFailure(Call<String> call,
                                  Throwable t) {

                tvTotalMoney.setText("Bağlantı Hatası");

            }
        });

    }

    private void showPieChart() {

        ApiService apiService =
                RetrofitClient.getClient().create(ApiService.class);

        Call<List<ChartModel>> call =
                apiService.getChartData(userId);

        call.enqueue(new Callback<List<ChartModel>>() {

            @Override
            public void onResponse(Call<List<ChartModel>> call,
                                   Response<List<ChartModel>> response) {

                if (response.isSuccessful()
                        && response.body() != null
                        && !response.body().isEmpty()) {

                    ArrayList<PieEntry> entries =
                            new ArrayList<>();

                    for (ChartModel chart : response.body()) {

                        entries.add(
                                new PieEntry(
                                        chart.getTotal(),
                                        chart.getCategory()
                                )
                        );
                    }

                    PieDataSet dataSet =
                            new PieDataSet(entries, "Harcamalar");

                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    PieData data = new PieData(dataSet);

                    pieChart.setData(data);

                    pieChart.getDescription().setEnabled(false);

                    pieChart.invalidate();

                }

            }

            private void loadCurrency() {

                CurrencyApiService apiService =
                        CurrencyRetrofitClient.getClient()
                                .create(CurrencyApiService.class);

                Call<CurrencyResponse> call =
                        apiService.getRates();

                call.enqueue(new Callback<CurrencyResponse>() {

                    @Override
                    public void onResponse(
                            Call<CurrencyResponse> call,
                            Response<CurrencyResponse> response) {

                        if(response.isSuccessful()
                                && response.body()!=null){

                            double usd =
                                    response.body()
                                            .getRates()
                                            .get("USD");

                            double eur =
                                    response.body()
                                            .getRates()
                                            .get("EUR");

                            double gbp =
                                    response.body()
                                            .getRates()
                                            .get("GBP");

                            tvUsd.setText(
                                    "1 TRY = " + usd + " USD");

                            tvEur.setText(
                                    "1 TRY = " + eur + " EUR");

                            tvGbp.setText(
                                    "1 TRY = " + gbp + " GBP");

                        }

                    }

                    @Override
                    public void onFailure(
                            Call<CurrencyResponse> call,
                            Throwable t) {

                    }

                });

            }

            @Override
            public void onFailure(Call<List<ChartModel>> call,
                                  Throwable t) {

            }
        });


    }
    private void loadCurrency() {

        CurrencyApiService apiService =
                CurrencyRetrofitClient.getClient()
                        .create(CurrencyApiService.class);

        Call<CurrencyResponse> call =
                apiService.getRates();

        call.enqueue(new Callback<CurrencyResponse>() {

            @Override
            public void onResponse(
                    Call<CurrencyResponse> call,
                    Response<CurrencyResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    double usd = response.body()
                            .getRates()
                            .get("USD");

                    double eur = response.body()
                            .getRates()
                            .get("EUR");

                    double gbp = response.body()
                            .getRates()
                            .get("GBP");

                    tvUsd.setText("USD : " + usd);
                    tvEur.setText("EUR : " + eur);
                    tvGbp.setText("GBP : " + gbp);

                }

            }

            @Override
            public void onFailure(
                    Call<CurrencyResponse> call,
                    Throwable t) {

                tvUsd.setText("Kur alınamadı");

            }

        });

    }
}