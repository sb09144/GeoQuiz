package com.example.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true)
    };

    private int mCurrentIndex = 0;

    private void checkAnswer(boolean userPressed){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(userPressed == answerIsTrue){
            messageResId = R.string.correct_toast;
            Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
        }
        else {
            messageResId = R.string.incorrect_toast;
            Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        mBackButton = findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mCurrentIndex != 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    int question = mQuestionBank[mCurrentIndex].getTextResId();
                }
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
            }
        });
    }
}
