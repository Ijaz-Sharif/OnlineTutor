package is.fb.onlinetutor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_aprovel extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private DatabaseReference mdata;
    private List<T_Profile> mupload;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_aprovel);
        recyclerView=findViewById(R.id.recl);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mupload=new ArrayList<>();
        mdata= FirebaseDatabase.getInstance().getReference().child("profile");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    T_Profile upload=new T_Profile(postSnapshot.child("Name").getValue(String.class),postSnapshot.child("Education").getValue(String.class),postSnapshot.child("Degree").getValue(String.class)
                            ,postSnapshot.child("Number").getValue(String.class),postSnapshot.child("Mail").getValue(String.class));
                    upload.setKey( postSnapshot.getKey());
                   // upload.setMail(postSnapshot.child("Mail").getValue(String.class));
                    mupload.add(upload);
                }
                imageAdapter=new ImageAdapter(Admin_aprovel.this,mupload);
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Admin_aprovel.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
