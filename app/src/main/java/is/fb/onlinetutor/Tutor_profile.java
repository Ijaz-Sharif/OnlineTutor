package is.fb.onlinetutor;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Tutor_profile extends AppCompatActivity {
    private   Button select,upload;
     private  TextInputLayout name,number;
     private ImageView degree_file;
     private ProgressBar progressBar;
    String edu[]={"Select","BS Math","BS English","BS Physics","BSCS","BSES","MSC","Hafiza Quran"};
     Spinner spinner;
    private Uri imgUri;
     ArrayAdapter<String> education;
    StorageReference mRef;
    DatabaseReference dRef;
    String d="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);
        select=findViewById(R.id.file);
        upload=findViewById(R.id.u_f);
        progressBar=findViewById(R.id.pb);
        degree_file=findViewById(R.id.tutor_degree);
        spinner=findViewById(R.id.qualification);
        dRef = FirebaseDatabase.getInstance().getReference("profile");
        name=findViewById(R.id.tutor_name);
        number=findViewById(R.id.tutor_number);
        education=new ArrayAdapter<String>(Tutor_profile.this,android.R.layout.simple_list_item_1,edu);
        spinner.setAdapter(education);
        mRef= FirebaseStorage.getInstance().getReference("profile");
      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          if(position>0){
             d=parent.getItemAtPosition(position).toString();
          }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });
       // T_Profile profile=new T_Profile();
     select.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
      addImg();
         }
     });
     upload.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
      uploadFile();
         }
     });
    }
    private void addImg(){
        Intent intent=new Intent(Intent.ACTION_PICK,android.provider. MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            imgUri  = data.getData();
            degree_file.setImageURI(imgUri);
        }
    }
    // get the extension of file
    private String getFileEx(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadFile(){

        if(imgUri!=null&name.getEditText().getText().toString().trim()!=null&number.getEditText().getText().toString().trim()!=null&d!=null){
            StorageReference storageReference=mRef.child(System.currentTimeMillis()+"."+getFileEx(imgUri));
            storageReference.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            },500);
                            Toast.makeText(Tutor_profile.this,"upload sucessful",Toast.LENGTH_LONG).show();
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            dRef=  FirebaseDatabase.getInstance().getReference("profile").child(name.getEditText().getText().toString().trim());
                            dRef.child("Degree").setValue(downloadUrl.toString());
                            dRef.child("Education").setValue(d);
                            // get the mail
                          SharedPreferences sharedPreferences=getSharedPreferences("Name",MODE_PRIVATE);
                            dRef.child("Mail").setValue( sharedPreferences.getString("mail","abc"));
                            dRef.child("Number").setValue(number.getEditText().getText().toString().trim());
                            dRef.child("Name").setValue(name.getEditText().getText().toString().trim());
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Tutor_profile.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress );
                        }
                    });
        }
        else {
            Toast.makeText(this,"comlpete your profile",Toast.LENGTH_LONG).show();
        }
    }
}
