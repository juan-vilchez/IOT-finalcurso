package pe.edu.tecsup.tecemergentes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SensorActivity extends AppCompatActivity {

    Button botregresars;


    private TextView sensorsonido, sensorgas;

    ImageView imagenGas, imagenSonido;


    LinearLayout sonidosenal, gassenal;


    //------------
    private DatabaseReference mirootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        botregresars = findViewById(R.id.button_sensores);

        sensorsonido = findViewById(R.id.valorsensor1);
        sensorgas = findViewById(R.id.valorsensor2);


        imagenGas = findViewById(R.id.imagengas);
        imagenSonido = findViewById(R.id.imagensonido);


        sonidosenal = findViewById(R.id.senalsonido);
        gassenal = findViewById(R.id.senalgas);

        mirootReference = FirebaseDatabase.getInstance().getReference();

        mirootReference.child("sensores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    String sonidofirebase = dataSnapshot.child("sonido").getValue().toString();
                    String gasfirebase = dataSnapshot.child("gas").getValue().toString();

                    sensorsonido.setText(sonidofirebase);
                    sensorgas.setText(gasfirebase);


                    int valorint = Integer.parseInt(sonidofirebase);

                    if (valorint >= 40 & valorint < 80) {
                        sonidosenal.setBackgroundColor(Color.GREEN);
                    } else if (valorint >= 80 & valorint < 119) {
                        sonidosenal.setBackgroundColor(Color.YELLOW);
                    } else if (valorint >= 119) {
                        sonidosenal.setBackgroundColor(Color.RED);
                    } else {
                        sonidosenal.setBackgroundColor(Color.WHITE);
                    }


                    int valorint2 = Integer.parseInt(gasfirebase);

                    if (valorint2 >= 310 & valorint2 <= 410) {
                        gassenal.setBackgroundColor(Color.RED);
                    }else if (valorint2 >= 276 & valorint2 < 310) {
                        gassenal.setBackgroundColor(Color.YELLOW);
                    }else if (valorint2 >= 243 & valorint2 < 276) {
                        gassenal.setBackgroundColor(Color.GREEN);
                    }else if (valorint2 >= 210 & valorint2 < 243) {
                        gassenal.setBackgroundColor(Color.WHITE);
                    }else{
                        gassenal.setBackgroundColor(Color.WHITE);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        botregresars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SensorActivity.this, "Click regresar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SensorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


}
