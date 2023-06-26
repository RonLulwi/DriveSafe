package com.example.drivesafe;

import com.example.drivesafe.Entities.Car;
import com.example.drivesafe.Entities.CarInfoBoundary;
import com.example.drivesafe.Entities.SystemStates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarInfoController {

    static final String BASE_URL = "https://data.gov.il/api/3/action/";
    static final String RESOURCE_id = "053cea08-09bc-40ec-8f7a-156f0677aff3";
    private Callback_CarInfo callback_carInfo;

    Callback<ApiResponse> carInfoCallBack = new Callback<ApiResponse>() {
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            if(response.isSuccessful() && response.body()!=null){
                List<CarInfoBoundary> car = response.body().getResult().getCars();
                CarInfoBoundary carInfo = null;
                if(callback_carInfo != null){
                    Car newCar = new Car();
                    if(car != null && !car.isEmpty()){
                        carInfo = car.get(0);
                        newCar = CarInfoConverter.toEntity(carInfo);

                    }
                    callback_carInfo.getCarInfo(newCar);
                }
            }else
                System.out.println(response.errorBody());
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public void fetchCarInfo(String plateNumber, Callback_CarInfo callback_carInfo){
        this.callback_carInfo = callback_carInfo;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        CarInfoAPI carInfoAPI = retrofit.create(CarInfoAPI.class);
        String filters = "{\"mispar_rechev\": \""+plateNumber+"\"}";
        Call<ApiResponse> call = carInfoAPI.getCarInfoByPlateNumber(RESOURCE_id, filters);
        call.enqueue(carInfoCallBack);
    }
    public interface Callback_CarInfo{
        void getCarInfo(Car car);
    }


    private static class CarInfoConverter{
        public static Car toEntity(CarInfoBoundary boundary){
            Car entity = new Car();
            entity.setModel(boundary.getCarModel());
            entity.setLicenseNumber(boundary.getLicensePlateNumber());
            entity.setManufacture(boundary.getManufacture());
            entity.setManufactureYear(Integer.parseInt(boundary.getManufactureYear()));
            entity.setSystemState(SystemStates.DEACTIVE);
            return entity;
        }

    }


}
