package net.raphaelmiller.apa9ball;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ScoreKeeperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        String playerOneName = bundle.getString("playerOneName");
        String playerTwoName = bundle.getString("playerTwoName");

        TextView playerOneDisplay = (TextView) findViewById(R.id.playerOneName);
        TextView playerTwoDisplay = (TextView) findViewById(R.id.playerTwoName);

        playerOneDisplay.setText(playerOneName);
        playerTwoDisplay.setText(playerTwoName);
    }

}
