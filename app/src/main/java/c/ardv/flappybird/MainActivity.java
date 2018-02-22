package c.ardv.flappybird;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView birdView;
    ImageView obstacleView;
    ImageView obstacleView2;
    RelativeLayout rootLayout;
    TextView scoreView;
    TextView bestScoreView;
    TextView lastScoreView;
    TextView gameOverView;
    RelativeLayout gameOverDialog;
    RelativeLayout startGame;

    int score = 0;
    float birdY = 0;
    float birdV = 0;
    float obstacleX =500;
    float obstacleY = 0;
    float obstacle2X =500;
    float obstacle2Y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = findViewById(R.id.start_game);
        gameOverDialog = findViewById(R.id.game_over_gialog);
        bestScoreView = findViewById(R.id.best_score_view);
        lastScoreView = findViewById(R.id.last_score_view);
        gameOverView = findViewById(R.id.game_over_view);
        scoreView = findViewById(R.id.score_view);
        birdView = findViewById(R.id.bird_view);
        obstacleView = findViewById(R.id.obstacle_view);
        rootLayout = findViewById(R.id.root_layout);
        obstacleView2 = findViewById(R.id.obstacle_view2);

        startGame();
        initializeTimer();

        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birdV = -9f;
            }
        });
    }

    private void initializeTimer() {
        final Handler handler = new Handler();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                onTimer();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onTimerUi();
                    }
                });
            }
        }, 25, 25);
    }

    private void onTimer() {
        obstacleX -= 5;
        if (obstacleView.getTranslationX() < -500) {
            obstacleX = 500;
            score++;
            obstacleY = (int) (Math.random() * 650 - 500);
        }

        obstacle2X -= 5;
        if (obstacleView2.getTranslationX() < -500) {
            obstacle2X = 350;
            score++;
            obstacle2Y = (int) (Math.random() * 400 - 50);
        }

        birdV += 0.3;
        birdY += birdV;


        if (birdY > 750 || birdY < -750) {
            showGameOverDialog();
        }
    }
    private void onTimerUi() {
        birdView.setTranslationY(birdY);

        obstacleView2.setTranslationX(obstacle2X);
        obstacleView2.setTranslationY(obstacle2Y);
        obstacleView.setTranslationX(obstacleX);
        obstacleView.setTranslationY(obstacleY);

        scoreView.setText(String.valueOf(score));
    }

    private void startNewGame(){
        birdY = 0;
        birdV = 0;
        obstacleX =500;
        obstacleY = 0;
        obstacle2X =500;
        obstacle2Y = 0;
        score = 0;
    }
   private void showGameOverDialog() {
        gameOverDialog.setVisibility(View.VISIBLE);

   }


    private void startGame(){
       rootLayout.setVisibility(View.VISIBLE);
   }



    //  private void saveRecord(int record) {
  //      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    //    SharedPreferences.Editor editor = preferences.edit();
    //    editor.putInt("РЕКОРД", record);
    //    editor.apply();
   // }

   // private int getRecord() {
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
       // return preferences.getInt("РЕКОРД", 0);
   // }




}
