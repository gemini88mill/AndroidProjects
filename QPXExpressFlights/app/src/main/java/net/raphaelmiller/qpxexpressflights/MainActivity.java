package net.raphaelmiller.qpxexpressflights;

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

    public void sendData(View view){

        Intent intent = new Intent(this, DisplayDataActivity.class);
        EditText originInputVal = (EditText) findViewById(R.id.originInput);
        EditText destinationInputVal = (EditText) findViewById(R.id.destinationInput);
        EditText departureInputVal = (EditText) findViewById(R.id.departureInput);
        EditText returningInputVal = (EditText) findViewById(R.id.returnDateInput);

        String origin = originInputVal.getText().toString();
        String destination = destinationInputVal.getText().toString();
        String departure = departureInputVal.getText().toString();
        String returning = returningInputVal.getText().toString();

        Bundle bundle = new Bundle();

        bundle.putString("origin", origin);
        bundle.putString("destination", destination);
        bundle.putString("departure", departure);
        bundle.putString("returning", returning);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
