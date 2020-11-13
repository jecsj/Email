package com.example.email;

import androidx.appcompat.app.AppCompatActivity;


import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private String correo;
    private String contra;

    private EditText mensaje,email;
    private Button enviar,mBtinAnadir;
    private Session session;
    private ListView mListView;
    private EditText mEditText;
    private List<String> mLista =new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correo="prueba16092020@gmail.com";
        contra="prueba.2020";
        //mensaje =  findViewById(R.id.correo);
        email=findViewById(R.id.correo);
        enviar = findViewById(R.id.btnEnviar);
        mBtinAnadir=findViewById(R.id.btnAgregar);
        mBtinAnadir.setOnClickListener(this);
        mListView=findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        mEditText=findViewById(R.id.etLista);

        /*
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Properties propiedades = new Properties();
                propiedades.put("mail.smtp.host", "smtp.googlemail.com");
                propiedades.put("mail.smtp.socketFactory.port", "465");
                propiedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                propiedades.put("mail.smtp.auth", "true");
                propiedades.put("mail.smtp.port", "465");

                try {
                    session = Session.getDefaultInstance(propiedades, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contra);
                        }
                    });

                    if (session != null) {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Primera Prueba JAVA Mail");
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("juliansarasty@hotmail.com"));
                        message.setContent(mensaje.getText().toString(), "text/html; charset=utf-8");

                        Transport.send(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
        enviarcorreo();
    }

    public void enviarcorreo()
    {
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Properties propiedades = new Properties();
                propiedades.put("mail.smtp.host", "smtp.googlemail.com");
                propiedades.put("mail.smtp.socketFactory.port", "465");
                propiedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                propiedades.put("mail.smtp.auth", "true");
                propiedades.put("mail.smtp.port", "465");

                try {
                    session = Session.getDefaultInstance(propiedades, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contra);
                        }
                    });

                    if (session != null) {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Primera Prueba JAVA Mail");
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getText().toString()));
                        String sintomas="Sintomas: ";
                        for (int i=0;i<mLista.size();i++)
                        {
                            sintomas=sintomas+", "+mLista.get(i);
                        }
                        message.setContent(sintomas, "text/html; charset=utf-8");

                        Transport.send(message);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        });

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnAgregar: String texto = mEditText.getText().toString().trim();
                mLista.add(texto);
                mEditText.getText().clear();
                mAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
                mListView.setAdapter(mAdapter);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,"Item Clicked:"+i,Toast.LENGTH_SHORT).show();
    }
}