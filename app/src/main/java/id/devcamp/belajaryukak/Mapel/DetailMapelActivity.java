package id.devcamp.belajaryukak.Mapel;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;



import butterknife.BindView;
import butterknife.ButterKnife;
import id.devcamp.belajaryukak.R;


public class DetailMapelActivity extends AppCompatActivity {
    public static String EXTRA_JUDUL = "judul";
    public static String EXTRA_DESC1 = "desc1";
    public static String EXTRA_DESC2 = "desc2";
    public static String EXTRA_DESC3 = "desc3";

    @BindView(R.id.txt_titleDetail)
    TextView tvJudul;
    @BindView(R.id.txt_DescDetail)
    TextView tvDesc1;
    @BindView(R.id.txt_DescDetail2)
    TextView tvDesc2;
    @BindView(R.id.txt_DescDetail3)
    TextView tvDesc3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mapel);
        ButterKnife.bind(this);

        String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String desc1 = getIntent().getStringExtra(EXTRA_DESC1);
        String desc2 = getIntent().getStringExtra(EXTRA_DESC2);
        String desc3 = getIntent().getStringExtra(EXTRA_DESC3);

        tvJudul.setText(judul);
        tvDesc1.setText(desc1);
        tvDesc2.setText(desc2);
        tvDesc3.setText(desc3);
    }

}
