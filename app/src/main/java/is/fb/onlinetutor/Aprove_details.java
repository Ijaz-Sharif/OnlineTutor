package is.fb.onlinetutor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Aprove_details extends AppCompatActivity {
  private TextView name,number,education;
 private ImageView imageView;
private String key=null;
private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprove_details);
        name=findViewById(R.id.t_n);
        number=findViewById(R.id.t_nu);
        education=findViewById(R.id.t_ed);
        imageView=findViewById(R.id.t_d);
        key=getIntent().getExtras().getString("key");
        name.setText(getIntent().getExtras().getString("name"));
        number.setText(getIntent().getExtras().getString("number"));
        education.setText(getIntent().getExtras().getString("degree"));
        Picasso.with(this)
                .load(getIntent().getExtras().getString("degree_pic"))
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(imageView);
       // imageView.setImageURI(Uri.parse(getIntent().getExtras().getString("degree")));
    }
    public void aprove(View v){
       databaseReference= FirebaseDatabase.getInstance().getReference().child("profile");
       databaseReference.child(key).removeValue();
        databaseReference= FirebaseDatabase.getInstance().getReference("aproved").child(getIntent().getExtras().getString("name"));
        databaseReference.child("Name").setValue(getIntent().getExtras().getString("name"));
        databaseReference.child("Number").setValue(getIntent().getExtras().getString("number"));
        databaseReference.child("Education").setValue(getIntent().getExtras().getString("degree"));
        databaseReference.child("degree").setValue(getIntent().getExtras().getString("degree_pic"));
        databaseReference.child("Mail").setValue(getIntent().getExtras().getString("mail"));
       startActivity(new Intent(Aprove_details.this,Admin_aprovel.class));
       finish();
    }
    public void disAprove(View v){
        databaseReference= FirebaseDatabase.getInstance().getReference().child("profile");
        databaseReference.child(key).removeValue();
        startActivity(new Intent(Aprove_details.this,Admin_aprovel.class));
        finish();
    }
}
