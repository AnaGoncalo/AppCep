package com.example.aluno.appcep;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText cep;
    TextView localidade, logradouro;
    Button buscar;

    String ncep = "";
    Cep c ;

/*
    View.OnClickListener clickBuscar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://viacep.com.br/ws/" + ncep +"/json/";

            //new JsonTask.execute(url);
            new JsonTask().execute(url);

            */
/*if(resposta.hashCode() == 200){
                try {
                    c.setCep(resposta.getString("cep"));
                    c.setLocalidade(resposta.getString("localidade"));
                    c.setLogradouro(resposta.getString("logradouro"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            localidade.setText(c.getLocalidade());
            logradouro.setText(c.getLogradouro());*//*

        }
    };
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cep = (EditText) findViewById(R.id.eTextCep);
        localidade = (TextView) findViewById(R.id.textLocalidade);
        logradouro = (TextView) findViewById(R.id.textLogradouro);
        buscar = (Button) findViewById(R.id.btnBuscar);

        ncep = cep.getText().toString();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://viacep.com.br/ws/" + ncep +"/json/";

                //new JsonTask.execute(url);
                new JsonTask().execute(url);
            }
        });
    }

     int resposta = 0;
    public class JsonTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params){

            HttpURLConnection conn = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                resposta = conn.getResponseCode();

                InputStream stream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                String jsonString = buffer.toString();
                return jsonString;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String endereco){
            super.onPostExecute(endereco);

            Gson gson = new Gson();

            c = gson.fromJson(endereco,Cep.class);


                c.setCep(c.getCep());
                c.setLocalidade(c.getLocalidade());
                c.setLogradouro(c.getLogradouro());


            localidade.setText(c.getLocalidade());
            logradouro.setText(c.getLogradouro());
        }
    }
}
