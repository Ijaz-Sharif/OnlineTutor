package is.fb.onlinetutor;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_login extends AppCompatActivity {
   TextView textView;
   private  TextInputLayout mail,pas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        textView=findViewById(R.id.notadmin);
        mail=findViewById(R.id.mail_input_admin);
        pas=findViewById(R.id.pas_input_admin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_login.this,MainActivity.class));
            }
        });
    }
    public void login(View view){
        if(mail.getEditText().getText().toString().trim().equals("sanazafar654@gmail.com")&pas.getEditText().getText().toString().trim().equals("36602")){
         startActivity(new Intent(this,Admin_aprovel.class));
        }
        else {
            Toast.makeText(this,"unable to access\nwrong mil or password",Toast.LENGTH_LONG).show();
        }
    }
}
