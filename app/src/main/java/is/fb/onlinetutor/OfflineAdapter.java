package is.fb.onlinetutor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OfflineAdapter extends RecyclerView.Adapter<OfflineAdapter.ImageView> {
    private Context context;
    private List<Offline> offlines;
    public OfflineAdapter(Context c,List<Offline> uplod){
        context=c;
        offlines=uplod;

    }


    @NonNull
    @Override
    public ImageView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.offline_profile,viewGroup,false);
        return new ImageView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageView imageView, int i) {
         Offline offline=offlines.get(i);
         imageView.name.setText(offline.getName());
         imageView.subject.setText(offline.getSubject());
         imageView.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(context,Offline_job_detail.class);
                 intent.putExtra("name",offlines.get(imageView.getAdapterPosition()).getName());
                 intent.putExtra("mail",offlines.get(imageView.getAdapterPosition()).getMail());
                 intent.putExtra("number",offlines.get(imageView.getAdapterPosition()).getNumber());
                 intent.putExtra("fee",offlines.get(imageView.getAdapterPosition()).getFee());
                 intent.putExtra("area",offlines.get(imageView.getAdapterPosition()).getArea());
                 intent.putExtra("time",offlines.get(imageView.getAdapterPosition()).getTime());
                 intent.putExtra("subject",offlines.get(imageView.getAdapterPosition()).getSubject());
                 intent.putExtra("education",offlines.get(imageView.getAdapterPosition()).getEducation());
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return offlines.size();
    }

    public static class ImageView extends RecyclerView.ViewHolder{
   public TextView name,subject;
   CardView cardView;
        public ImageView(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            subject=itemView.findViewById(R.id.sub);
            cardView=itemView.findViewById(R.id.of);

        }
    }
}
