package com.example.harcliktakibi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public interface ApiService {

    @FormUrlEncoded
    @POST("register.php")
    Call<String> registerUser(
            @Field("fullname") String fullname,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("login.php")
    Call<String> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("add_money.php")
    Call<String> addMoney(
            @Field("user_id") int user_id,
            @Field("total_money") double total_money
    );
    @GET("get_money.php")
    Call<String> getMoney(
            @Query("user_id") int user_id
    );
    @FormUrlEncoded
    @POST("add_expense.php")
    Call<String> addExpense(
            @Field("user_id") int userId,
            @Field("spent_money") double spentMoney,
            @Field("category") String category
    );
    @FormUrlEncoded
    @POST("getChartData.php")
    Call<List<ChartModel>> getChartData(
            @Field("user_id") int user_id
    );

    @GET("get_profile.php")
    Call<ProfileModel> getProfile(
            @Query("user_id") int user_id
    );
}