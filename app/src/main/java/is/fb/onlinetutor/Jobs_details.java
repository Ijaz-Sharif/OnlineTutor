package is.fb.onlinetutor;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Jobs_details extends AppCompatActivity {
  private Toolbar toolbar;
  private TabLayout tabLayout;
  private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPreferences;
    DatabaseReference myRef;
    TextView user_name,user_mail;
    private NavigationView navigationView;
    int a=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_details);
        toolbar=findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.may_viewpager);
        setSupportActionBar(toolbar);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        drawerLayout=findViewById(R.id.lay_out);
        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
       navigationView =findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        user_name=headerView.findViewById(R.id.name_user);
        user_mail=headerView.findViewById(R.id.mail_user);

    }
    private void setUpViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Offline_tutor(),"Offline Tutor");
        viewPagerAdapter.addFragment(new Online_tutor(),"Online Tutor");
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void onStart() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile :
                        startActivity(new Intent(Jobs_details.this,Tutor_profile.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.add_job:
                        chk();
                        break;
                    case R.id.log_out :
                        sharedPreferences=getSharedPreferences("Name",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("name",false);
                        editor.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Jobs_details.this,MainActivity.class));
                        finish();
                        break;
                    case     R.id.about :
                        Toast.makeText(Jobs_details.this,"nothing ",Toast.LENGTH_LONG).show();
                    break;
                }
                return true;
            }
        });
        getData();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }}
        private void getData(){
            SharedPreferences sharedPreference=getSharedPreferences("Name",MODE_PRIVATE);
           final String user_m= sharedPreference.getString("mail","android@gmail.com");
            myRef=  FirebaseDatabase.getInstance().getReference().child("user");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                      if(user_m.equals(dataSnapshot1.child("Mail").getValue(String.class))) {
                          user_name.setText(dataSnapshot1.child("Name").getValue(String.class));
                          user_mail.setText(dataSnapshot1.child("Mail").getValue(String.class));
                          break;
                      }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        private void chk(){

            SharedPreferences sharedPreference=getSharedPreferences("Name",MODE_PRIVATE);
            final String user_m= sharedPreference.getString("mail","android@gmail.com");
            myRef=  FirebaseDatabase.getInstance().getReference().child("aproved");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        if(user_m.equals(dataSnapshot1.child("Mail").getValue(String.class))) {
                            Intent intent=new Intent(Jobs_details.this,Add_job.class);
                            intent.putExtra("edu",dataSnapshot1.child("Education").getValue(String.class));
                            intent.putExtra("num",dataSnapshot1.child("Number").getValue(String.class));
                            intent.putExtra("mail",dataSnapshot1.child("Mail").getValue(String.class));
                            intent.putExtra("name",dataSnapshot1.child("Name").getValue(String.class));
                            startActivity(intent);
                            a=0;
                            break;
                        }
                    }
                    if(a==1){
                        Toast.makeText(Jobs_details.this,"your have no access \nfor this feature",Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
}
