package com.app.maurote.convertidormoneda;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // mauro: quitar subrayado del Edit text en XML: android:background="@null"
    //mauro: declarar vaviables para asignar tipografia a texto
    TextView txt_pesos_mexicanos, txt_resultado, txt_SAR, txt_cotizacion, txt_SMX;
    private Typeface script;

    //mauro: declarar variables
    private EditText num1, num2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mauro: instanciar variables (vincular con XML)
        txt_pesos_mexicanos = (TextView) findViewById(R.id.txt_pesos_mexicanos);
        txt_resultado = (TextView) findViewById(R.id.txt_resultado);
        txt_SAR = (TextView) findViewById(R.id.txt_SAR);
        txt_cotizacion = (TextView) findViewById(R.id.txt_cotizacion);
        txt_SMX = (TextView) findViewById(R.id.txt_SMX);

        tv1 = (TextView)findViewById(R.id.txt_resultado);
        num1 = (EditText)findViewById(R.id.txt_num1);
        num2 = (EditText)findViewById(R.id.txt_num2);


        //mauro: asignar tipografía a texto
        String fuente = "fuentes/maurote.ttf";
        this.script = Typeface.createFromAsset(getAssets(), fuente);
        txt_pesos_mexicanos.setTypeface(script);
        txt_resultado.setTypeface(script);
        txt_SAR.setTypeface(script);
        txt_cotizacion.setTypeface(script);
        txt_SMX.setTypeface(script);


        //Memoria
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        num2.setText(preferences.getString("String",""));

        }

    //Este método realiza la convercion
    public void Convertir(View view){
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString("String",num2.getText().toString());
        obj_editor.commit();

        try{
            //mauro: declarar e instanciar variables (vincular con XML)
            double dnum1 = Double.parseDouble(num1.getText().toString());
            double dnum2 = Double.parseDouble(num2.getText().toString());

            double resultado = dnum1 * dnum2;


            //tv1.setText(String.valueOf(resultado + " Pesos Argentinos")); // mauro: sin numedor decimales controladas
            tv1.setText(String.format("%.2f",resultado) + " Pesos Argentinos"); // mauro: numeros decimales controladas (java): <String.format("%.2f",resultado)>


        } catch (Exception e){
            Toast toast = Toast.makeText(this,"Debe introducir un numero", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

        }

    }

    public void Autor(View view){
        Toast toast = Toast.makeText(this,"Programado por Mauro Peralta", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }


}
