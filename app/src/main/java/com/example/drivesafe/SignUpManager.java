package com.example.drivesafe;

import android.util.Patterns;
import android.widget.Toast;
import com.example.drivesafe.Entities.User;

public class SignUpManager {

    private User user;

    public boolean validateName(String name){
        return name.matches("[a-zA-Z]+");
    }

    public boolean validateCity(String city){
        return city.matches("[a-zA-Z]+[\\s[a-zA-Z]+]*");
    }

    public boolean validateLicensePlate(String licensePlate){
        return licensePlate.matches("[1-9]\\d{6,7}");
    }

    public boolean validateEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean validateID(String id) {
        // Check that the input contains exactly 9 digits
        if (!id.matches("[0-9]{9}")) {
            return false;
        }

        // Convert the ID to an integer array
        int[] idArray = new int[9];
        for (int i = 0; i < 9; i++) {
            idArray[i] = Integer.parseInt(String.valueOf(id.charAt(i)));
        }

        // Calculate the checksum
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            int digit = idArray[i];
            if (i % 2 == 0) {
                digit *= 1;
            } else {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        int checksum = (10 - (sum % 10)) % 10;

        // Check that the checksum matches the last digit of the input
        return checksum == idArray[8];
    }

    public boolean validatePassword(String password, String passwordConfirm){
        return password.equals(passwordConfirm);
    }

    public void showToast(String msg){
        Toast.makeText(App.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
