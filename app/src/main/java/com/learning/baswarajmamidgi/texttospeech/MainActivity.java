package com.learning.baswarajmamidgi.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private EditText data_tospeak;
    private FloatingActionButton speakout;
    private  TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_tospeak = findViewById(R.id.data_speak);
        speakout = findViewById(R.id.speakout);

        textToSpeech  = new TextToSpeech(this,this);
        
        speakout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakData();
            }
        });
    }

    private void speakData() {

        String data = data_tospeak.getText().toString();

        textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);


    }

    @Override
    public void onInit(int i) {

        if(i == TextToSpeech.SUCCESS)
        {
            int result = textToSpeech.setLanguage(Locale.US);
            if(result  == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA)
            {
                Toast.makeText(this, "This language is not supported...", Toast.LENGTH_SHORT).show();
            }
            else{
                speakout.setEnabled(true);
                speakData();
            }

        }
        else
        {
            Toast.makeText(this, "Initialization failed...", Toast.LENGTH_SHORT).show();
        }
    }
}
