package is.fb.onlinetutor;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Offline_tutor extends Fragment {
    private RecyclerView recyclerView;
    private OfflineAdapter offlineAdapter;
    private DatabaseReference mdata;
    private List<Offline> mupload;
    public Offline_tutor() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_offline_tutor, container, false);
        recyclerView=view.findViewById(R.id.offline_job);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mupload=new ArrayList<>();
        mdata= FirebaseDatabase.getInstance().getReference().child("offline");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Offline upload=new Offline(postSnapshot.child("Name").getValue(String.class),postSnapshot.child("Subject").getValue(String.class)
                            ,postSnapshot.child("Education").getValue(String.class)
                            ,postSnapshot.child("Fee").getValue(String.class),postSnapshot.child("Time").getValue(String.class),
                            postSnapshot.child("Areas").getValue(String.class),postSnapshot.child("Mail").getValue(String.class)
                    ,postSnapshot.child("Number").getValue(String.class));
                    mupload.add(upload);
                }
                 offlineAdapter=new OfflineAdapter(getContext(),mupload);
                recyclerView.setAdapter(offlineAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
