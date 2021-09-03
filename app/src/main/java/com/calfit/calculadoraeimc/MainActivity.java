package com.calfit.calculadoraeimc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    private EditText et1, et2, et3;
    private TextView tv1, tv2, tv3;
    private Button bcalcular, boton2, boton3;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    // corregido todos los editText y setError

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton2 = findViewById(R.id.volver2);
        boton3 = findViewById(R.id.button3);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete (InitializationStatus initializationStatus) {
                createPersonalizedAd();
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    Intent intent = new Intent(MainActivity.this, ComoseguirActivity.class);
                    startActivity(intent);
                }
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        bcalcular = findViewById(R.id.calcular2);
        et1 = findViewById(R.id.edad2);
        et2 = findViewById(R.id.altura2);
        et3 = findViewById(R.id.peso2);
        tv1 = findViewById(R.id.resultado);
        tv2 = findViewById(R.id.resultado2);
        tv3 = findViewById(R.id.resultado3);
        rb1 = findViewById(R.id.rb_hombre10);
        rb2 = findViewById(R.id.rb_mujer10);
        rb3 = findViewById(R.id.rbNada);
        rb4 = findViewById(R.id.rbPoca);
        rb5 = findViewById(R.id.rbMod);
        rb6 = findViewById(R.id.rbIntensa);
        boton2 = findViewById(R.id.volver2);


        bcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String edad_String = et1.getText().toString();
                String altura_String = et2.getText().toString();
                String peso_String = et3.getText().toString();
                String hombre_S = rb1.getText().toString();
                String mujer_S = rb2.getText().toString();
                String nada_S = rb3.getText().toString();
                String poca_S = rb4.getText().toString();
                String mod_S = rb5.getText().toString();
                String intensa_S = rb6.getText().toString();

                if (edad_String.isEmpty() || altura_String.isEmpty() || peso_String.isEmpty() || hombre_S.isEmpty() || mujer_S.isEmpty() || nada_S.isEmpty() || poca_S.isEmpty() || mod_S.isEmpty() || intensa_S.isEmpty()) {
                    et1.setError("No dejes ningún campo vacío");
                    et2.setError("No dejes ningún campo vacío");
                    et3.setError("No dejes ningún campo vacío");

                } else {


                    //Método para el botón calcular


                    int edad_int = Integer.parseInt(edad_String);
                    int altura_int = Integer.parseInt(altura_String);
                    int peso_int = Integer.parseInt(peso_String);

//El codigo if else de la calculadora pasarlo abajo con sus respectivas variables convertidas
                    // abajo cambiar variables del otro código por las de aca ( este es solo un ejemplo de sumas para darme una idea)

                    //Hombres
                    if (rb1.isChecked() && rb3.isChecked()) {
                        int bmr = (int) (66 + (13.7 * peso_int) + (5 * altura_int) - (6.75 * edad_int));
                        float resultado = Float.parseFloat(String.valueOf(bmr * 1.2));
                        float resultado2 = Float.parseFloat(String.valueOf((bmr * 1.2 - 470)));
                        float resultado3 = Float.parseFloat(String.valueOf((bmr * 1.2 + 480)));
                        tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                        tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                        tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");


                    } else {
                        if (rb1.isChecked() && rb4.isChecked()) {
                            int bmr = (int) (66 + (13.7 * peso_int) + (5 * altura_int) - (6.75 * edad_int));
                            float resultado = Float.parseFloat(String.valueOf(bmr * 1.375));
                            float resultado2 = Float.parseFloat(String.valueOf(bmr * 1.375 - 470));
                            float resultado3 = Float.parseFloat(String.valueOf(bmr * 1.375 + 480));
                            tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                            tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                            tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");

                        } else if (rb1.isChecked() && rb5.isChecked()) {
                            int bmr = (int) (66 + (13.7 * peso_int) + (5 * altura_int) - (6.75 * edad_int));
                            float resultado = Float.parseFloat(String.valueOf(bmr * 1.55));
                            float resultado2 = Float.parseFloat(String.valueOf(bmr * 1.55 - 470));
                            float resultado3 = Float.parseFloat(String.valueOf(bmr * 1.55 + 480));
                            tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                            tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                            tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");

                        } else if (rb1.isChecked() && rb6.isChecked()) {
                            int bmr = (int) (66 + (13.7 * peso_int) + (5 * altura_int) - (6.75 * edad_int));
                            float resultado = Float.parseFloat(String.valueOf(bmr * 1.725));
                            float resultado2 = Float.parseFloat(String.valueOf((bmr * 1.725 - 470)));
                            float resultado3 = Float.parseFloat(String.valueOf((bmr * 1.725 + 480)));
                            tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                            tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                            tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");

                            //Mujeres
                        } else if (rb2.isChecked() && rb3.isChecked()) {
                            int bmr = (int) (665 + (9.6 * peso_int) + (1.8 * altura_int) - (4.7 * edad_int));
                            float resultado = Float.parseFloat(String.valueOf(bmr * 1.2));
                            float resultado2 = Float.parseFloat(String.valueOf(bmr * 1.2 - 470));
                            float resultado3 = Float.parseFloat(String.valueOf(bmr * 1.2 + 480));
                            tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                            tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                            tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");

                        } else if (rb2.isChecked() && rb4.isChecked()) {
                            int bmr = (int) (665 + (9.6 * peso_int) + (1.8 * altura_int) - (4.7 * edad_int));
                            float resultado = Float.parseFloat(String.valueOf(bmr * 1.375));
                            float resultado2 = Float.parseFloat(String.valueOf(bmr * 1.375 - 470));
                            float resultado3 = Float.parseFloat(String.valueOf(bmr * 1.375 + 480));
                            tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                            tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                            tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");

                        } else if (rb2.isChecked() && rb5.isChecked()) {
                            int bmr = (int) (665 + (9.6 * peso_int) + (1.8 * altura_int) - (4.7 * edad_int));
                            float resultado = Float.parseFloat(String.valueOf(bmr * 1.55));
                            float resultado2 = Float.parseFloat(String.valueOf(bmr * 1.55 - 470));
                            float resultado3 = Float.parseFloat(String.valueOf(bmr * 1.55 + 480));
                            tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                            tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                            tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");

                        } else if (rb2.isChecked() && rb6.isChecked()) {
                            int bmr = (int) (665 + (9.6 * peso_int) + (1.8 * altura_int) - (4.7 * edad_int));
                            float resultado = Float.parseFloat(String.valueOf(bmr * 1.725));
                            float resultado2 = Float.parseFloat(String.valueOf(bmr * 1.725 - 470));
                            float resultado3 = Float.parseFloat(String.valueOf(bmr * 1.725 + 480));
                            tv1.setText("Las calorias necesarias para mantener tu peso actual son: " + redondear(resultado) + " kcal");
                            tv2.setText("Las calorias necesarias para perder 0-5 a 1kg a la semana son: " + redondear(resultado2) + " kcal");
                            tv3.setText("Las calorias necesarias para ganar 0-5 a 1kg a la semana son: " + redondear(resultado3) + " kcal");

                        }


                    }
                }

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

    private void createPersonalizedAd () {
        AdRequest adRequest = new AdRequest.Builder().build();

        createInterstitialAd(adRequest);
    }

    private void createInterstitialAd (AdRequest adRequest) {
        InterstitialAd.load(this, "ca-app-pub-6166270378015046/9581936158", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded (@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.d("---AdMob", "onAdLoaded");

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent () {
                        // Called when fullscreen content is dismissed.
                        Log.d("AdMob", "The ad was dismissed.");

                        Intent intent = new Intent(MainActivity.this, ComoseguirActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent (AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("AdMob", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent () {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("AdMob", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad (@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("---AdMob", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }

    //la linea de resultado es para el resultado solamente, arriba de eso cambiar todo para que se sume como la calculadora de calorias


            public void ImcActivity (View view) {
                Intent imc_activity = new Intent(MainActivity.this, ImcActivity.class);
                startActivity(imc_activity);
            }

        public void comoSeguir (View view) {
        Intent comoseguir = new Intent(this, ComoseguirActivity.class);
        startActivity(comoseguir);            }

    public void menu2 (View view) {
        Intent menu2 = new Intent(this, MainActivity.class);
        startActivity(menu2);

    }

            @Override
            protected void onStart () {
                super.onStart();
                Toast.makeText(this, "Bienvenida/o :)", Toast.LENGTH_LONG).show();
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
                Toast.makeText(this, "Hasta la próxima :)", Toast.LENGTH_LONG).show();
                // La actividad está a punto de ser destruida.
            }

        }

