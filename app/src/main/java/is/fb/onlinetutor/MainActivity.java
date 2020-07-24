package is.fb.onlinetutor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   TextView textView;
   Button log,sing_up;
   private TextInputLayout mail;
    private TextInputLayout pas;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.myadmin);
        log=findViewById(R.id.login);
        sing_up=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.mail_input);
        pas=findViewById(R.id.pas_input);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Admin_login.class));
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        login();
            }
        });
        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Signup.class));
            }
        });
    }
    public void login(){
        String ma=mail.getEditText().getText().toString().trim();
        String pa=pas.getEditText().getText().toString().trim();
        if(TextUtils.isEmpty(ma)||TextUtils.isEmpty(pa)){
            Toast.makeText(this,"enter both values",Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(ma,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"wrong mail or password"+task.getException(),Toast.LENGTH_LONG).show();
                    }
                    else if(task.isSuccessful()){
                        sharedPreferences=getSharedPreferences("Name",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("name",true);
                        editor.commit();
                       SharedPreferences sharedPreference=getSharedPreferences("Name",MODE_PRIVATE);
                        SharedPreferences.Editor editor1=sharedPreference.edit();
                        editor1.putString("mail",mail.getEditText().getText().toString().trim());
                        editor1.commit();
                        startActivity(new Intent(MainActivity.this,Jobs_details.class));
                             finish();
                    }
                }
            });
        }
    }
}
