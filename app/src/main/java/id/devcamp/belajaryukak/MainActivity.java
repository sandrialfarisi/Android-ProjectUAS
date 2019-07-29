package id.devcamp.belajaryukak;

import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.WindowManager;

import id.devcamp.belajaryukak.Login.LoginFragment;



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
                FragmentManager fm = getSupportFragmentManager();
                LoginFragment fragment = new LoginFragment();
                fm.beginTransaction().replace(R.id.main, fragment).commit();

            }
        }, waktu_loading);
    }
}
