package com.example.uas_byk.Quis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.uas_byk.HomeActivity;
import com.example.uas_byk.R;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    TextView t1, t2, t3, t4;
    Button retake, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t1 = findViewById(R.id.tvTotal);
        t2 = findViewById(R.id.tvAnswer);
        t3 = findViewById(R.id.tvWrong);
        t4 = findViewById(R.id.tvPoints);
        retake = findViewById(R.id.retake_button);
        end = findViewById(R.id.end_button);

        end.setOnClickListener(this);
        retake.setOnClickListener(this);

        Intent i = getIntent();
        String questions = i.getStringExtra("total");
        String correct = i.getStringExtra("correct");
        String wrong = i.getStringExtra("incorrect");
        String points = i.getStringExtra("points");

        t1.setText(questions);
        t2.setText(correct);
        t3.setText(wrong);
        t4.setText(points);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == end) {
            startActivity(new Intent(ResultActivity.this, HomeActivity.class));
            finish();
        }
        if (view == retake) {
            startActivity(new Intent(ResultActivity.this, QuisActivity.class));
            finish();
        }
    }
}
