package com.example.drivesafe;

import android.widget.Toast;
import com.example.drivesafe.Entities.AlcoholTest;
import com.example.drivesafe.Entities.BypassAttempts;
import com.example.drivesafe.Entities.SystemStates;
import com.example.drivesafe.Entities.UserEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DriveSafeController {
    static final String BASE_URL = "Service_URL";


    private CallBack_createUser callback_createUser;
    private CallBack_logIn callback_logIn;
    private CallBack_changeSystemState callBack_changeSystemState;
    private CallBack_AlcoholTests callBack_AlcoholTests;
    private CallBack_updateUser callBack_updateUser;
    private CallBack_bypassAttempts callBack_bypassAttempts;




    Callback<UserEntity> logInCallback = new Callback<UserEntity>() {
        @Override
        public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
            if(response.isSuccessful()){
                UserEntity user = response.body();
                if(callback_logIn != null && user != null)
                    callback_logIn.logIn(user);
            }else{
                Toast.makeText(App.getAppContext(), "Id or Password are incorrect! ", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<UserEntity> call, Throwable t) {t.printStackTrace();}
    };
    public void login(String id, String password, CallBack_logIn callback_logIn){
        this.callback_logIn = callback_logIn;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DriveSafeAPI driveSafeAPI = retrofit.create(DriveSafeAPI.class);
        Call<UserEntity> call = driveSafeAPI.login(id, password);
        call.enqueue(logInCallback);
    }

    Callback<UserEntity> createUserCallback = new Callback<UserEntity>() {
        @Override
        public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
            if(response.isSuccessful()){
                UserEntity user = response.body();
                if (callback_createUser != null && user != null)
                    callback_createUser.registerUser(user);
            }else
                System.out.println(response.errorBody());
        }

        @Override
        public void onFailure(Call<UserEntity> call, Throwable t) {
            t.printStackTrace();
        }
    };
    public void saveUserToDB(UserEntity user, CallBack_createUser callback_createUser){
        this.callback_createUser = callback_createUser;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DriveSafeAPI driveSafeAPI = retrofit.create(DriveSafeAPI.class);
        Call<UserEntity> call = driveSafeAPI.createUser(user);
        call.enqueue(createUserCallback);
    }

    Callback<Void> changeSystemStateCallBack = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if(callBack_changeSystemState != null)
                callBack_changeSystemState.changeSystemState(response.isSuccessful());
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
        t.printStackTrace();
        }
    };
    public void changeSystemStatus(SystemStates state, String carLicenseNumber, CallBack_changeSystemState callBack_changeSystemState){
        this.callBack_changeSystemState = callBack_changeSystemState;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DriveSafeAPI driveSafeAPI = retrofit.create(DriveSafeAPI.class);
        Call<Void> call;
        if(state == SystemStates.ACTIVE)
            call = driveSafeAPI.activateSystem(carLicenseNumber);
        else
            call = driveSafeAPI.deactivateSystem(carLicenseNumber);
        call.enqueue(changeSystemStateCallBack);
    }

    Callback<List<AlcoholTest>> alcoholTestCallback = new Callback<List<AlcoholTest>>() {
        @Override
        public void onResponse(Call<List<AlcoholTest>> call, Response<List<AlcoholTest>> response) {
            if(response.isSuccessful()){
                List<AlcoholTest> tests = response.body();
                if (callBack_AlcoholTests != null)
                    callBack_AlcoholTests.updateAlcoholTests(tests);
            }else{
                Toast.makeText(App.getAppContext(), "Something Went Wrong, please refresh the page.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<List<AlcoholTest>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    public void getAlcoholTests(String id, CallBack_AlcoholTests callBack_AlcoholTests){
        this.callBack_AlcoholTests = callBack_AlcoholTests;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DriveSafeAPI driveSafeAPI = retrofit.create(DriveSafeAPI.class);
        Call<List<AlcoholTest>> call = driveSafeAPI.getAlcoholTests(id, 20, 0);
        call.enqueue(alcoholTestCallback);

    }

    Callback<Void> updateUserInformation = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if(callBack_updateUser != null)
                callBack_updateUser.updatedUser(response.isSuccessful());
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public void updateUserInfo(String userId, UserEntity user, CallBack_updateUser callBack_updateUser){
        this.callBack_updateUser = callBack_updateUser;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DriveSafeAPI driveSafeAPI = retrofit.create(DriveSafeAPI.class);
        Call<Void> call = driveSafeAPI.updateUser(userId, user);
        call.enqueue(updateUserInformation);
    }

    Callback<List<BypassAttempts>> bypassAttemptsStateCallBack = new Callback<List<BypassAttempts>>() {
        @Override
        public void onResponse(Call<List<BypassAttempts>> call, Response<List<BypassAttempts>> response) {
            if(response.isSuccessful()){
                List<BypassAttempts> bypassAttempts = response.body();
                if (callBack_bypassAttempts != null)
                    callBack_bypassAttempts.updateBypassAttempts(bypassAttempts);
            }else{
                Toast.makeText(App.getAppContext(), "Something Went Wrong, please refresh the page.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<List<BypassAttempts>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public void getBypassAttempts(String userId, CallBack_bypassAttempts callBack_bypassAttempts){
        this.callBack_bypassAttempts = callBack_bypassAttempts;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DriveSafeAPI driveSafeAPI = retrofit.create(DriveSafeAPI.class);
        Call<List<BypassAttempts>> call = driveSafeAPI.getBypassAttempts(userId, 10, 0);
        call.enqueue(bypassAttemptsStateCallBack);


    }

    public interface CallBack_logIn{
        void logIn(UserEntity user);
    }

    public interface CallBack_changeSystemState{
        void changeSystemState(boolean response);
    }

    public interface CallBack_createUser{
        void registerUser(UserEntity user);
    }

    public interface  CallBack_AlcoholTests{
        void updateAlcoholTests(List<AlcoholTest> tests);
    }

    public interface  CallBack_updateUser{
        void updatedUser(boolean response);
    }

    public interface CallBack_bypassAttempts{
        void updateBypassAttempts(List<BypassAttempts> tests);
    }





}
