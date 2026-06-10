package com.example.harcliktakibi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRetrofitClient {

    private static final String BASE_URL =
            "https://open.er-api.com/v6/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }

}