package com.example.drivesafe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarInfoAPI {
    @GET("datastore_search")
    Call<ApiResponse> getCarInfoByPlateNumber(@Query("resource_id") String resourceId, @Query("filters") String filters);

}
