package com.calfit.calculadoraeimc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class ImcActivity extends AppCompatActivity {


    EditText edpeso, edaltura;
    TextView tvinfra, tvimc, tvnormal, tvsobre, tvo1, tvo2, tvo3;
    Button bcalcular2, btnborrar;
    float peso2, altura2, alturadef, imc;

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    //  IMPORTANTE, LO QUE HAY QUE CORREGIR SON SOLO LOS EDIT TEXT, YA QUE LOS RADIO SON SOLO MULTIPLOS

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_activity);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        btnborrar = findViewById(R.id.borrar);
        bcalcular2 = findViewById(R.id.calcular2);
        edpeso = findViewById(R.id.peso2);
        edaltura = findViewById(R.id.altura2);
        tvinfra = findViewById(R.id.pocoPeso);
        tvnormal = findViewById(R.id.normal);
        tvsobre = findViewById(R.id.sobrepeso);
        tvo1 = findViewById(R.id.obesidad1);
        tvo2 = findViewById(R.id.obesidad2);
        tvo3 = findViewById(R.id.obesidad3);
        tvimc = findViewById(R.id.imc);

        //hay que tener en cuenta que no es lo mismo calcular el imc a un niño que
// a una persona adulta
        bcalcular2.setOnClickListener(v -> {
            alturadef = Float.parseFloat(edpeso.getText().toString());
            peso2 = Float.parseFloat(edpeso.getText().toString());
            altura2 = Float.parseFloat(edaltura.getText().toString());
                alturadef = altura2*altura2;
            imc = peso2 / alturadef;
                tvimc.setText("IMC: " + redondear(imc));
                borrar();
                if (imc < 18.5) {
                    tvinfra.setBackgroundColor(Color.parseColor("#000000"));
                    tvinfra.setTextColor(Color.parseColor("#FFFFFF"));
                }
                if (imc >= 18.5 && imc < 25) {
                    tvnormal.setBackgroundColor(Color.parseColor("#000000"));
                    tvnormal.setTextColor(Color.parseColor("#FFFFFF"));
                }

                if (imc >= 25 && imc < 30) {
                    tvsobre.setBackgroundColor(Color.parseColor("#000000"));
                    tvsobre.setTextColor(Color.parseColor("#FFFFFF"));
                }

                if (imc >= 30 && imc < 35) {
                    tvo1.setBackgroundColor(Color.parseColor("#000000"));
                    tvo1.setTextColor(Color.parseColor("#FFFFFF"));
                }

                if (imc >= 35 && imc < 40) {
                    tvo1.setBackgroundColor(Color.parseColor("#000000"));
                    tvo1.setTextColor(Color.parseColor("#FFFFFF"));

                }
                if (imc >= 40) {
                    tvo1.setBackgroundColor(Color.parseColor("#000000"));
                    tvo1.setTextColor(Color.parseColor("#FFFFFF"));
                }
            });
        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                borrar();
                edpeso.setText("");
                edaltura.setText("");
                tvimc.setText("IMC:");
            }
        });
    }

    public float redondear (float n) {
        float res;
        int valor = 0;
        valor = (int) (n * 100);
        res = (float) valor / 100;
        return res;
    }

    public void borrar () {
        tvinfra.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvinfra.setTextColor(Color.parseColor("#000000"));
        tvnormal.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvnormal.setTextColor(Color.parseColor("#000000"));
        tvsobre.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvsobre.setTextColor(Color.parseColor("#000000"));
        tvo1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvo1.setTextColor(Color.parseColor("#000000"));
        tvo1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvo1.setTextColor(Color.parseColor("#000000"));
        tvo1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvo1.setTextColor(Color.parseColor("#000000"));

    }

    public void Volver(View view) {
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }


    //la linea de resultado es para el resultado solamente, arriba de eso cambiar todo para que se sume como la calculadora de calorias


            @Override
            protected void onStart () {
                super.onStart();
                Toast.makeText(this, "Calculadora IMC", Toast.LENGTH_LONG).show();
                // La actividad está a punto de hacerse visible.
            }

            @Override
            protected void onResume () {
                super.onResume();
                // La actividad se ha vuelto visible (ahora se "reanuda").
            }

            @Override
            protected void onPause () {
                super.onPause();
                // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").

            }

            @Override
            protected void onDestroy () {
                super.onDestroy();
                // La actividad está a punto de ser destruida.
            }

        }
