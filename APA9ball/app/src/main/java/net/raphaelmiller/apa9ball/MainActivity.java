package net.raphaelmiller.apa9ball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBundle(View view){
        Intent intent = new Intent(this, ScoreKeeperActivity.class);

        Bundle startupBundle = new Bundle();

        EditText playerOneName = (EditText) findViewById(R.id.playerOneName);
        EditText playerOneSKill = (EditText) findViewById(R.id.playerOneSkill);
        EditText playerTwoName = (EditText) findViewById(R.id.playerTwoName);
        EditText playerTwoSkill = (EditText) findViewById(R.id.playerTwoSkill);

        startupBundle.putString("playerOneName", playerOneName.getText().toString());
        startupBundle.putString("playerOneSkill", playerOneSKill.getText().toString());
        startupBundle.putString("playerTwoName", playerTwoName.getText().toString());
        startupBundle.putString("playerTwoSkill", playerTwoSkill.getText().toString());

        intent.putExtras(startupBundle);

        startActivity(intent);
    }
}
