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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_job extends AppCompatActivity {
   private TextInputLayout subject,fee,areas,time;
  private Button submit;
 private   RadioButton offline,online;
  private DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        subject=findViewById(R.id.subject);
        fee=findViewById(R.id.fee);
        areas=findViewById(R.id.area);
        time=findViewById(R.id.time);
        submit=findViewById(R.id.submet);
        offline=findViewById(R.id.offline);
        online=findViewById(R.id.online);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (offline.isChecked()) {
                        if (TextUtils.isEmpty(subject.getEditText().getText().toString())& TextUtils.isEmpty(fee.getEditText().getText().toString())&
                                TextUtils.isEmpty(areas.getEditText().getText().toString()) & TextUtils.isEmpty(time.getEditText().getText().toString())) {
                            Toast.makeText(Add_job.this,"all data enter",Toast.LENGTH_LONG).show();
                        }
                        else {
                            setOffline();
                            Toast.makeText(Add_job.this,"your job post is ready",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Add_job.this,Jobs_details.class));
                        }
                    }
                        else if (online.isChecked()) {
                            if (TextUtils.isEmpty(subject.getEditText().getText().toString())& TextUtils.isEmpty(fee.getEditText().getText().toString())&
                                    TextUtils.isEmpty(time.getEditText().getText().toString())) {
                                Toast.makeText(Add_job.this,"all data enter",Toast.LENGTH_LONG).show();
                            }
                            else {
                                setOnline();
                                Toast.makeText(Add_job.this,"your job post is ready",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Add_job.this,Jobs_details.class));
                            }
                        }
            }
        });
    }
    private void setOffline(){
        mref=  FirebaseDatabase.getInstance().getReference("offline").child(getIntent().getExtras().getString("name"));
        mref.child("Name").setValue(getIntent().getExtras().getString("name"));
        mref.child("Number").setValue(getIntent().getExtras().getString("num"));
        mref.child("Mail").setValue(getIntent().getExtras().getString("mail"));
        mref.child("Education").setValue(getIntent().getExtras().getString("edu"));
        mref.child("Fee").setValue(fee.getEditText().getText().toString().trim());
        mref.child("Time").setValue(time.getEditText().getText().toString().trim());
        mref.child("Subject").setValue(subject.getEditText().getText().toString().trim());
        mref.child("Areas").setValue(areas.getEditText().getText().toString().trim());
    }
    private void setOnline(){
        mref=  FirebaseDatabase.getInstance().getReference("online").child(getIntent().getExtras().getString("name"));
        mref.child("Name").setValue(getIntent().getExtras().getString("name"));
        mref.child("Number").setValue(getIntent().getExtras().getString("num"));
        mref.child("Mail").setValue(getIntent().getExtras().getString("mail"));
        mref.child("Education").setValue(getIntent().getExtras().getString("edu"));
        mref.child("Fee").setValue(fee.getEditText().getText().toString().trim());
        mref.child("Time").setValue(time.getEditText().getText().toString().trim());
        mref.child("Subject").setValue(subject.getEditText().getText().toString().trim());
    }
}
