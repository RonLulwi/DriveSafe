package com.example.drivesafe;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }
}
