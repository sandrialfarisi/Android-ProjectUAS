package com.example.uas_byk;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.uas_byk.Login.LoginFragment;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke HomeActivity activity
//                Intent HomeActivity = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(HomeActivity);
//                finish();
                FragmentManager fm = getSupportFragmentManager();
                LoginFragment fragment = new LoginFragment();
                fm.beginTransaction().replace(R.id.main, fragment).commit();

            }
        }, waktu_loading);

//        if (fragment == null){
//            fragment = new LoginFragment();
//            fm.beginTransaction().add(R.id.fragment_login, fragment).commit();
//        }
//    }
    }
}
