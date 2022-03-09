package com.example.SRE;

import static com.example.SRE.Function.fetchName;
import static com.example.SRE.Function.wishme;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.SREE.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SpeechRecognizer recognizer;
    private  TextView vtresult;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withContext(this)
                .withPermission(Manifest.permission.RECORD_AUDIO)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        System.exit(0);
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
        findById();
        initializeTextToSpeech();
        initializeResult();
        fetchName("call chetan sawant ans say hi to him");
    }

    private void initializeTextToSpeech() {
        tts  = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (tts.getEngines().size() == 0) {
                    Toast.makeText(MainActivity.this, "Engine is not available", Toast.LENGTH_SHORT).show();
                } else {
                    String s =wishme();
                    speak("Hi i am SREE..."+s);
                }
            }
        });

    }

    private void speak(String msg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    private void findById(){
        vtresult= (TextView)findViewById(R.id.tv_result);
    }
    private void initializeResult(){
        if(SpeechRecognizer.isRecognitionAvailable(this)){
            recognizer=SpeechRecognizer.createSpeechRecognizer(this);
            recognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    ArrayList<String> result =bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    Toast.makeText(MainActivity.this, ""+result.get(0), Toast.LENGTH_SHORT).show();
                    vtresult.setText(result.get(0));
                    response(result.get(0));
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }
    }

    private void response(String msg) {
        String msgs = msg.toLowerCase();
        if (msgs.indexOf("hi") != -1) {
            speak("Hello sir ! How are you ?");
        }

        if (msgs.indexOf("hello") != -1) {
            speak("Hello sir ! How are you ?");
        }

        if (msgs.indexOf("fine") != -1) {
            speak("it's good to know you are fine...How may I help you  ?");
        }
        if (msgs.indexOf("not fine") != -1) {
            speak("please take care..");
        }

        if (msgs.indexOf("how") != -1) {
            if (msgs.indexOf("you") != -1) {
                speak("I am cool...");
            }
        }
        if (msgs.indexOf("your") != -1) {
            if (msgs.indexOf("friend") != -1) {
                speak("krishna...");
            }
        }
        if (msgs.indexOf("who") != -1) {
            if (msgs.indexOf("made") != -1) {
                speak("Krishna vardhan...");
            }
        }
        if (msgs.indexOf("your") != -1) {
            if (msgs.indexOf("age") != -1) {
                speak("sorry its a secret...");
            }
        }
        if (msgs.indexOf("your") != -1) {
            if (msgs.indexOf("name") != -1) {
                speak("My name is SREE...");
            }
        }
        if (msgs.indexOf("are") != -1) {
            if (msgs.indexOf("single") != -1) {
                speak("yes,i am single but not ready to mingle...");
            }
        }
        if (msgs.indexOf("ready") != -1) {
            if (msgs.indexOf("mingle") != -1) {
                speak("sorry sir i don't carry this bullshit emotions ...");
            }
        }
        if (msgs.indexOf("thank") != -1) {
            if (msgs.indexOf("you") != -1) {
                speak("your welcome...");
            }
        }
        if (msgs.indexOf("i love ") != -1) {
            if (msgs.indexOf("you") != -1) {
                speak("don't, are you single ?...");
            }
        }
        if (msgs.indexOf("no i am ") != -1) {
            if (msgs.indexOf("not single") != -1) {
                speak("stay loyal to your loved ones...");
            }
        }

        if (msgs.indexOf("time") != -1) {
            if (msgs.indexOf("now") != -1) {
                Date date = new Date();
                String time = DateUtils.formatDateTime(this, date.getTime(), DateUtils.FORMAT_SHOW_TIME);
                speak("The time now is " + time);
            }
        }

        if (msgs.indexOf("today") != -1) {
            if (msgs.indexOf("date") != -1) {
                SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
                Calendar cal = Calendar.getInstance();
                String today = df.format(cal.getTime());
                speak("The Today's date is " + today);
            }
        }

        if (msgs.indexOf("open") != -1) {
            if (msgs.indexOf("google") != -1) {
                Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(k);
            }
            if (msgs.indexOf("browser") != -1) {
                Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(k);
            }
            if (msgs.indexOf("chrome") != -1) {
                Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(k);
            }
            if (msgs.indexOf("youtube") != -1) {
                Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
                startActivity(k);
            }
            if (msgs.indexOf("facebook") != -1) {
                Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                startActivity(k);
            }
            if (msgs.indexOf("brave") != -1) {
                Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.brave.com"));
                startActivity(k);
            }
            if (msgs.indexOf("instagram") != -1) {
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.Instagram.android");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("whatsapp") != -1) {
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("twitter") != -1) {
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.twitter.android");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("snapchat") != -1) {
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.snapchat.android");
                ctx.startActivity(i);
            }
        }


    }

    public void startRecording(View view) {

        Intent i =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
        recognizer.startListening(i);
    }
}