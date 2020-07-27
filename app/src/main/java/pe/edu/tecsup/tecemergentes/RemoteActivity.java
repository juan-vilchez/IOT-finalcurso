package pe.edu.tecsup.tecemergentes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RemoteActivity extends AppCompatActivity {


    Button botregresarr;

    //------------
    private DatabaseReference mirootReference2;
    TextView estadodispositivo, estadodispositivo2;
    RelativeLayout cartaestado, cartaestado2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        final Button customButton = findViewById(R.id.custom_button);
        Switch switchEnableButton = findViewById(R.id.switch_enable_button);

        //----
        final Button customButton2 = findViewById(R.id.custom_button2);
        Switch switchEnableButton2 = findViewById(R.id.switch_enable_button2);

        botregresarr =  findViewById(R.id.button_dispositivo);
        estadodispositivo = findViewById(R.id.valorestadodispositivo);
        cartaestado = findViewById(R.id.cartadispositivo);

        //----
        estadodispositivo2 = findViewById(R.id.valorestadodispositivo2);
        cartaestado2 = findViewById(R.id.cartadispositivo2);




        mirootReference2 = FirebaseDatabase.getInstance().getReference();

        mirootReference2.child("dispositivoIOT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String estado = dataSnapshot.child("led").getValue().toString();

                    //----
                    String estado2 = dataSnapshot.child("motor").getValue().toString();


                    final String estadogeneral = estado;
                    //---
                    final String estadogeneral2 = estado2;


                    String estadoreal = "indefinido";
                    //---
                    String estadoreal2 = "indefinido";


                    if(estado.equals("0")){
                        estadoreal = "Apagado";
                        estadodispositivo.setTextColor(Color.GRAY);
                    }
                    if(estado.equals("1")){
                        estadoreal = "Encendido";

                        estadodispositivo.setTextColor(Color.GREEN);
                    }

                    //------------------
                    if(estado2.equals("0")){
                        estadoreal2 = "Apagado";
                        estadodispositivo2.setTextColor(Color.GRAY);
                    }
                    if(estado2.equals("1")){
                        estadoreal2 = "En marcha";

                        estadodispositivo2.setTextColor(Color.GREEN);
                    }

                    estadodispositivo.setText(estadoreal);
                    estadodispositivo2.setText(estadoreal2);
                    customButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RemoteActivity.this, "Click", Toast.LENGTH_SHORT).show();
                            //mirootReference2.child("dispositivoIOT").child("led").setValue("1");
                            validarestado(estadogeneral);
                        }
                    });

                    customButton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RemoteActivity.this, "Click2", Toast.LENGTH_SHORT).show();
                            //mirootReference2.child("dispositivoIOT").child("led").setValue("1");
                            validarestado2(estadogeneral2);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        switchEnableButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    customButton.setEnabled(true);
                } else {
                    customButton.setEnabled(false);
                }
            }
        });



        switchEnableButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    customButton2.setEnabled(true);
                } else {
                    customButton2.setEnabled(false);
                }
            }
        });






        botregresarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RemoteActivity.this, "Click regresar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RemoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    public void validarestado(String estado){
        if(estado.equals("0")){
            mirootReference2.child("dispositivoIOT").child("led").setValue("1");
        }
        if(estado.equals("1")){
            mirootReference2.child("dispositivoIOT").child("led").setValue("0");
        }
    }

    public void validarestado2(String estado){
        if(estado.equals("0")){
            mirootReference2.child("dispositivoIOT").child("motor").setValue("1");
        }
        if(estado.equals("1")){
            mirootReference2.child("dispositivoIOT").child("motor").setValue("0");
        }
    }

}
