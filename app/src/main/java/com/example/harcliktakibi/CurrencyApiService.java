package com.example.harcliktakibi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyApiService {

    @GET("latest/TRY")
    Call<CurrencyResponse> getRates();

}