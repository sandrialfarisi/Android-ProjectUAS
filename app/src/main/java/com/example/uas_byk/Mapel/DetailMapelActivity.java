package com.example.uas_byk.Mapel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.uas_byk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMapelActivity extends AppCompatActivity {
    public static String EXTRA_JUDUL = "judul";
    public static String EXTRA_PENJELASAN = "penjelasan";

    @BindView(R.id.txt_titleDetail)
    TextView tvJudul;
    @BindView(R.id.txt_DescDetail)
    TextView tvPenjelasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mapel);
        ButterKnife.bind(this);

        String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String penjelasan = getIntent().getStringExtra(EXTRA_PENJELASAN);

        tvJudul.setText(judul);
        tvPenjelasan.setText(penjelasan);
    }

}
