package com.example.drivesafe;

import com.example.drivesafe.Entities.AlcoholTest;
import com.example.drivesafe.Entities.BypassAttempts;
import com.example.drivesafe.Entities.UserEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DriveSafeAPI {

    @POST("users")
    Call<UserEntity> createUser(@Body UserEntity user);

    @GET("users/login/{id}")
    Call<UserEntity> login(@Path(value = "id", encoded = true) String id, @Query("password") String password);

    @PUT("users/{id}")
    Call<Void> updateUser(@Path(value = "id", encoded = true) String id, @Body UserEntity user);

    @PUT("users/activate-system/{carLicenseNumber}")
    Call<Void> activateSystem(@Path(value = "carLicenseNumber", encoded = true) String carLicenseNumber);

    @PUT("users/deactivate-system/{carLicenseNumber}")
    Call<Void> deactivateSystem(@Path(value = "carLicenseNumber", encoded = true) String carLicenseNumber);

    @GET("users/alcoholTests/{id}")
    Call<List<AlcoholTest>> getAlcoholTests(@Path(value = "id", encoded = true) String id, @Query("size") int size, @Query("page") int page);

    @GET("users/bypass-attempts/{id}")
    Call<List<BypassAttempts>> getBypassAttempts(@Path(value = "id", encoded = true) String id, @Query("size") int size, @Query("page") int page);

}
