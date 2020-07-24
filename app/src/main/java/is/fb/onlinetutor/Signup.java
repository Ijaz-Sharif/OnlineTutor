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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
 private   Button reg;
 private   TextInputLayout user_name;
 private    TextInputLayout user_mail;
 private    TextInputLayout user_pas;
 private FirebaseAuth firebaseAuth;
 private String name;
 private  String mail;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        reg=findViewById(R.id.register);
        user_name=findViewById(R.id.user_nam);
        user_mail=findViewById(R.id.user_mail_input);
        user_pas=findViewById(R.id.user_pas_input);
        firebaseAuth=FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("user");
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name=user_name.getEditText().getText().toString().trim();
                 mail=user_mail.getEditText().getText().toString().trim();
                String pas=user_pas.getEditText().getText().toString().trim();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(mail) ||TextUtils.isEmpty(pas)){
                    Toast.makeText(Signup.this,"enter all values",Toast.LENGTH_LONG).show();
                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(mail,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Signup.this,"sucess fully register",Toast.LENGTH_LONG).show();
                                add();
                                startActivity(new Intent(Signup.this,Jobs_details.class));
                                finish();
                              SharedPreferences  sharedPreferences=getSharedPreferences("Name",MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putBoolean("name",true);
                                editor.commit();

                            }
                            else {
                                Toast.makeText(Signup.this,"not sucess fully register"+task.getException(),Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
    }
    private void add(){
      myRef=  FirebaseDatabase.getInstance().getReference("user").child(name);
        myRef.child("Name").setValue(name);
        myRef.child("Mail").setValue(mail);
        SharedPreferences sharedPreference=getSharedPreferences("Name",MODE_PRIVATE);
        SharedPreferences.Editor editor1=sharedPreference.edit();
        editor1.putString("mail",user_mail.getEditText().getText().toString().trim());
        editor1.commit();
    }
}
