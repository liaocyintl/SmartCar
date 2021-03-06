package net.liaocy.smartcar.backup;

import android.app.Application;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class Speaker extends Thread{

    private Queue<String> textList;

    private TextToSpeech speech;

    private final String TAG = "BizSpeaker";

    public Speaker(Application context){
        super();
        this.textList = new LinkedList<>();

        //init speech
        this.speech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = speech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, "Not support this language.");
                    } else {
                        Log.i(TAG, "BizSpeaker activated!");


                    }
                }
            }
        });
        //init speech -- end
    }

    public void Speak(String text){
        this.speech.speak(text, TextToSpeech.QUEUE_ADD, null, null);
    }
    public void SpeakNow(String text){
        this.speech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void run() {
        while(true){
            String text = null;
            synchronized(this.textList){
                text = this.textList.poll();
            }

            if (text != null){

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
