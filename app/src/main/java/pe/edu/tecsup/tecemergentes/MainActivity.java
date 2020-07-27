package pe.edu.tecsup.tecemergentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Button sensorid, dispositivoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorid = findViewById(R.id.id_sensores);
        dispositivoid = findViewById(R.id.id_leds);



        dispositivoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Click disp b", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RemoteActivity.class);
                startActivity(intent);
            }
        });
        sensorid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Click sensor b", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(intent);
            }
        });

    }
}
