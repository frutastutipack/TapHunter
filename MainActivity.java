
package com.example.taphunter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private TextView scoreText, timerText;
    private View target;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        target = findViewById(R.id.target);

        target.setOnClickListener(v -> {
            score++;
            scoreText.setText("Puntos: " + score);
            moveTarget();
        });

        startTimer();
    }

    private void moveTarget() {
        View parent = (View) target.getParent();
        int maxX = parent.getWidth() - target.getWidth();
        int maxY = parent.getHeight() - target.getHeight();

        if (maxX > 0 && maxY > 0) {
            target.setX(random.nextInt(maxX));
            target.setY(random.nextInt(maxY));
        }
    }

    private void startTimer() {
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("Tiempo: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timerText.setText("Fin del juego");
                target.setEnabled(false);
            }
        }.start();
    }
}
