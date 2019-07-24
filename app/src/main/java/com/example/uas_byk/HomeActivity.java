package com.example.uas_byk;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.example.uas_byk.Login.LoginFragment;
import com.example.uas_byk.Mapel.MapelIndoActivity;
import com.example.uas_byk.Mapel.MapelPenjasActivity;
import com.example.uas_byk.Mapel.MapelPknActivity;
import com.example.uas_byk.Mapel.MapelSenBudActivity;
import com.example.uas_byk.Quis.QuisActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView btnIndo, btnPkn, btnSenbud, btnPenjas, btnQuis;
    private FirebaseAuth mAuth;
    public GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnIndo = findViewById(R.id.btn_bindo);
        btnPkn = findViewById(R.id.btn_ppkn);
        btnSenbud = findViewById(R.id.btn_senibudaya);
        btnPenjas = findViewById(R.id.btn_penjaskes);
        btnQuis = findViewById(R.id.btn_quis);
        mAuth = FirebaseAuth.getInstance();
        btnIndo.setOnClickListener(this);
        btnPkn.setOnClickListener(this);
        btnSenbud.setOnClickListener(this);
        btnPenjas.setOnClickListener(this);
        btnQuis.setOnClickListener(this);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_contact:
                Intent i;
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return true;
            case R.id.action_signout:
                new TTFancyGifDialog.Builder(this)
                        .setTitle("Log Out?")
                        .setMessage("Jangan patah semangat dan terus belajar ya kak.")
                        .setPositiveBtnText("Log Out")
                        .setPositiveBtnBackground("#22b573")
                        .setNegativeBtnText("Cancel")
                        .setNegativeBtnBackground("#c1272d")
                        .setGifResource(R.drawable.a)      //pass your gif, png or jpg
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(HomeActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
                                logOut();
                            }
                        })
                        .OnNegativeClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(HomeActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.btn_bindo:
                i = new Intent(this, MapelIndoActivity.class);
                startActivity(i);
                break;
            case R.id.btn_ppkn:
                i = new Intent(this, MapelPknActivity.class);
                startActivity(i);
                break;
            case R.id.btn_senibudaya:
                i = new Intent(this, MapelSenBudActivity.class);
                startActivity(i);
                break;
            case R.id.btn_penjaskes:
                i = new Intent(this, MapelPenjasActivity.class);
                startActivity(i);
                break;
            case R.id.btn_quis:
                i = new Intent(this, QuisActivity.class);
                startActivity(i);
                finish();
                break;
            default:
                break;
        }

    }

    private void logOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut();
        Toast.makeText(this, "Log Out Success", Toast.LENGTH_SHORT).show();
        FragmentManager fm = getSupportFragmentManager();
        LoginFragment fragment = new LoginFragment();
        fm.beginTransaction().replace(R.id.home, fragment).commit();
        getSupportActionBar().hide();
    }

//    private void SignOut(){
//
//    }

}
