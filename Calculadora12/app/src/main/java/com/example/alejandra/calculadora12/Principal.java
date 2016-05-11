package com.example.alejandra.calculadora12;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Principal extends AppCompatActivity {

 EditText num1, num2,resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        num1 = (EditText) findViewById(R.id.txtNum1);
        num2 = (EditText) findViewById(R.id.txtNum2);
        resultado = (EditText) findViewById(R.id.txtResultado);

        calcular();
    }

    private void opera(int op) {
        String num = num1.getText().toString();
        String numt = num2.getText().toString();
        if (!num.equals("") && !numt.equals("")) {
            int answer_n = Integer.parseInt(num);
            int answer_nt = Integer.parseInt(numt);
            String result;
            switch (op) {
                case 1:
                    result = mas(answer_n, answer_nt);
                    Toast.makeText(this, "Suma : " + result, Toast.LENGTH_SHORT).show();
                    save(answer_n + " + " + answer_nt + " = " + result);
                    break;
                case 2:
                    result = men(answer_n, answer_nt);
                    Toast.makeText(this, "Resta : " + result, Toast.LENGTH_SHORT).show();
                    save(answer_n + " - " + answer_nt + " = " + result);
                    break;
                case 3:
                    result = mult(answer_n, answer_nt);
                    Toast.makeText(this, "Mul : " + result, Toast.LENGTH_SHORT).show();
                    save(answer_n + " * " + answer_nt + " = " + result);
                    break;
                case 4:
                    result = div(answer_n, answer_nt);
                    Toast.makeText(this, "Div : " + result, Toast.LENGTH_SHORT).show();
                    save(answer_n + " / " + answer_nt + " = " + result);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(this, "Ingrese un numero entero ", Toast.LENGTH_SHORT).show();
        }
    }

    public void mas(View view) {
        opera(1);
        calcular();
    }

    public void men(View view) {
        opera(2);
        calcular();
    }

    public void multi(View view) {
        opera(3);
        calcular();
    }

    public void div(View view) {
        opera(4);
        calcular();
    }



    private String mas(int num1, int num2) {
        int add = num1 + num2;
        String result = "" + add;
        return result;
    }

    private String men(int num1, int num2) {
        int sub = num1 - num2;
        String result = "" + sub;
        return result;
    }

    private String mult(int num1, int num2) {
        int mul = num1 * num2;
        String result = "" + mul;
        return result;
    }

    private String div(int num1, int num2) {
        if (num2 != 0) {
            float div = num1 / num2;
            String result = "" + div;
            return result;
        } else {
            return "No se puede divir entre 0";
        }
    }

    private void calcular(){
        String nomarchivo="basic_op.txt";
        nomarchivo=nomarchivo.replace('/','-');
        boolean enco = false;
        String[] archivos = fileList();
        for(int f = 0;f<archivos.length;f++)
            if(nomarchivo.equals(archivos[f]))
                enco=true;
        if(enco==true){
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(nomarchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                resultado.setText(todo);
            } catch (IOException e) {
            }
        }else  {

        }
    }

    private void save(String data_1){
        String nomarchivo="basic_op.txt";
        nomarchivo=nomarchivo.replace('/','-');
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    nomarchivo, Activity.MODE_PRIVATE));
            archivo.write(resultado.getText().toString()+"\n"+data_1);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }

    }
}
