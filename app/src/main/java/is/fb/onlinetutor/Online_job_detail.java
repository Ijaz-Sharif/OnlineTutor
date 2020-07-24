package is.fb.onlinetutor;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Online_job_detail extends AppCompatActivity {
   private TextView na,nu,ed,ma,su,fe,ti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_job_detail);
        na=findViewById(R.id.na1);
        nu=findViewById(R.id.nu1);
        ed=findViewById(R.id.qa1);
        ma=findViewById(R.id.gm1);
        su=findViewById(R.id.su1);
        fe=findViewById(R.id.fe1);
        ti=findViewById(R.id.ti1);
        na.setText(getIntent().getExtras().getString("name"));
        nu.setText(getIntent().getExtras().getString("number"));
        ed.setText(getIntent().getExtras().getString("education"));
        ma.setText(getIntent().getExtras().getString("mail"));
        su.setText(getIntent().getExtras().getString("subject"));
        fe.setText(getIntent().getExtras().getString("fee"));
        ti.setText(getIntent().getExtras().getString("time"));
    }
    public void cal(View v){
        makePhoneCall();
    }
    private void makePhoneCall(){
        if(ContextCompat.checkSelfPermission(Online_job_detail.this,
                android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Online_job_detail.this,
                    new String[]{  android.Manifest.permission.CALL_PHONE},1);
        }
        else {
            String dial="tel:" +getIntent().getExtras().getString("number") ;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==requestCode){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
        }
    }
}
