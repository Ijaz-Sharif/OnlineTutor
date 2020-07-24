package is.fb.onlinetutor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {
  ProgressBar pb;
  int counter=0;
  private  FirebaseAuth firebaseAuth;
  private SharedPreferences sharedPreferences;
  private boolean b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        sharedPreferences=getSharedPreferences("Name",MODE_PRIVATE);
         b= sharedPreferences.getBoolean("name",false);
        firebaseAuth=FirebaseAuth.getInstance();
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if(b){
                        Intent intent=new Intent(Splash_Screen.this,Jobs_details.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                    Intent intent=new Intent(Splash_Screen.this,MainActivity.class);
                    startActivity(intent);
                    finish();}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        prog();
    }
    public  void prog(){
        pb=findViewById(R.id.p);
        Timer t=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
            }
        };
        t.schedule(timerTask,0,30);
    }
}
