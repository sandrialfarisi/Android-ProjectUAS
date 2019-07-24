package com.example.uas_byk.Quis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_byk.HomeActivity;
import com.example.uas_byk.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuisActivity extends AppCompatActivity {
    Button b1, b2, b3, b4, quit;
    TextView t1_question, mCountDownView, mQuestionsView, mScoreView;
    int total = 0;
    int correct = 0;
    private int mQuestions = 0;
    private int mScore = 0;
    DatabaseReference reference;
    int wrong = 0;
    long data = 0;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quis);
        mQuestionsView = findViewById(R.id.question_count);
        mScoreView = findViewById(R.id.score);
        mCountDownView = findViewById(R.id.tvCountdown);
        b1 = findViewById(R.id.choice1);
        b2 = findViewById(R.id.choice2);
        b3 = findViewById(R.id.choice3);
        b4 = findViewById(R.id.choice4);

        t1_question = findViewById(R.id.question);
        updateQuestion();
    }


//    public void detailQuestion() {
//
//                    updateQuestion();
//                }
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                finish();
//            }
//        });
//    }


    public void updateQuestion() {
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        total++;
        reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    data = (dataSnapshot.getChildrenCount());
                }
                if (total > data) {
                    total--;
                    countDownTimer.cancel();
                    Intent i = new Intent(QuisActivity.this, ResultActivity.class);
                    i.putExtra("total", String.valueOf(total));
                    i.putExtra("correct", String.valueOf(correct));
                    i.putExtra("incorrect", String.valueOf(wrong));
                    i.putExtra("points", String.valueOf(mScore));
                    startActivity(i);
                    finish();
                } else {
                    reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total)); //vragen doorlopen database
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                final QuisItem question = dataSnapshot.getValue(QuisItem.class);
                                reverseTimer();
                                t1_question.setText(question.getQuestion());

                                b1.setText(question.getOption1());
                                b2.setText(question.getOption2());
                                b3.setText(question.getOption3());
                                b4.setText(question.getOption4());
                                mQuestions = mQuestions + 1;
                                updateQuestionCount(mQuestions);
                                b1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        b1.setEnabled(false);
                                        b2.setEnabled(false);
                                        b3.setEnabled(false);
                                        b4.setEnabled(false);
                                        if (b1.getText().toString().equals(question.getAnswer())) {
                                            mScore = mScore + 10;
                                            updateScore(mScore);
                                            Toast.makeText(QuisActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                                            b1.setBackgroundColor(Color.GREEN);

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);
                                        } else {
                                            Toast.makeText(QuisActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                                            // answer is wrong ... we will find the correct answer, and make it green
                                            wrong++;
                                            b1.setBackgroundColor(Color.RED);

                                            if (b2.getText().toString().equals(question.getAnswer())) {
                                                b2.setBackgroundColor(Color.GREEN);
                                            } else if (b3.getText().toString().equals(question.getAnswer())) {
                                                b3.setBackgroundColor(Color.GREEN);
                                            } else if (b4.getText().toString().equals(question.getAnswer())) {
                                                b4.setBackgroundColor(Color.GREEN);
                                            }

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);


                                        }
                                    }
                                });

                                b2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        b1.setEnabled(false);
                                        b2.setEnabled(false);
                                        b3.setEnabled(false);
                                        b4.setEnabled(false);
                                        if (b2.getText().toString().equals(question.getAnswer())) {
                                            mScore = mScore + 10;
                                            updateScore(mScore);
                                            Toast.makeText(QuisActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                                            b2.setBackgroundColor(Color.GREEN);

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);
                                        } else {
                                            Toast.makeText(QuisActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                                            // answer is wrong ... we will find the correct answer, and make it green
                                            wrong++;
                                            b2.setBackgroundColor(Color.RED);

                                            if (b1.getText().toString().equals(question.getAnswer())) {
                                                b1.setBackgroundColor(Color.GREEN);
                                            } else if (b3.getText().toString().equals(question.getAnswer())) {
                                                b3.setBackgroundColor(Color.GREEN);
                                            } else if (b4.getText().toString().equals(question.getAnswer())) {
                                                b4.setBackgroundColor(Color.GREEN);
                                            }

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);


                                        }
                                    }
                                });

                                b3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        b1.setEnabled(false);
                                        b2.setEnabled(false);
                                        b3.setEnabled(false);
                                        b4.setEnabled(false);
                                        if (b3.getText().toString().equals(question.getAnswer())) {
                                            mScore = mScore + 10;
                                            updateScore(mScore);
                                            Toast.makeText(QuisActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                                            b3.setBackgroundColor(Color.GREEN);

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);
                                        } else {
                                            Toast.makeText(QuisActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                                            // answer is wrong ... we will find the correct answer, and make it green
                                            wrong++;
                                            b3.setBackgroundColor(Color.RED);

                                            if (b1.getText().toString().equals(question.getAnswer())) {
                                                b1.setBackgroundColor(Color.GREEN);
                                            } else if (b2.getText().toString().equals(question.getAnswer())) {
                                                b2.setBackgroundColor(Color.GREEN);
                                            } else if (b4.getText().toString().equals(question.getAnswer())) {
                                                b4.setBackgroundColor(Color.GREEN);
                                            }

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);


                                        }
                                    }
                                });

                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        b1.setEnabled(false);
                                        b2.setEnabled(false);
                                        b3.setEnabled(false);
                                        b4.setEnabled(false);
                                        if (b4.getText().toString().equals(question.getAnswer())) {
                                            mScore = mScore + 10;
                                            updateScore(mScore);
                                            Toast.makeText(QuisActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                                            b4.setBackgroundColor(Color.GREEN);

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    correct++;
                                                    b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);
                                        } else {
                                            Toast.makeText(QuisActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                                            // answer is wrong ... we will find the correct answer, and make it green
                                            wrong++;
                                            b4.setBackgroundColor(Color.RED);

                                            if (b1.getText().toString().equals(question.getAnswer())) {
                                                b1.setBackgroundColor(Color.GREEN);
                                            } else if (b2.getText().toString().equals(question.getAnswer())) {
                                                b2.setBackgroundColor(Color.GREEN);
                                            } else if (b3.getText().toString().equals(question.getAnswer())) {
                                                b3.setBackgroundColor(Color.GREEN);
                                            }

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                                    updateQuestion();
                                                }
                                            }, 1500);
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            countDownTimer.cancel();
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                countDownTimer.cancel();
                finish();
            }
        });
    }


    private void updateQuestionCount(int point) {
        mQuestionsView.setText("Questions : " + mQuestions);
    }

    private void updateScore(int point) {
        mScoreView.setText("Score : " + mScore);
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    public void reverseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onFinish() {
                mCountDownView.setText("Finished!");
                updateQuestion();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                mCountDownView.setText("Timer : " + millisUntilFinished / 1000);
            }

        };
        countDownTimer.start();
    }
}