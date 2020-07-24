package is.fb.onlinetutor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ImageView>{
    private Context context;
    private List<Online> onlines;
    public OnlineAdapter(Context c,List<Online> uplod){
        context=c;
        onlines=uplod;

    }
    @NonNull
    @Override
    public ImageView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.online_profile,viewGroup,false);
        return new OnlineAdapter.ImageView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageView imageView, int i) {
        Online offline=onlines.get(i);
        imageView.name.setText(offline.getName());
        imageView.subject.setText(offline.getSubject());
        imageView.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Online_job_detail.class);
                intent.putExtra("name",onlines.get(imageView.getAdapterPosition()).getName());
                intent.putExtra("name",onlines.get(imageView.getAdapterPosition()).getName());
                intent.putExtra("mail",onlines.get(imageView.getAdapterPosition()).getMail());
                intent.putExtra("number",onlines.get(imageView.getAdapterPosition()).getNumber());
                intent.putExtra("fee",onlines.get(imageView.getAdapterPosition()).getFee());
                intent.putExtra("time",onlines.get(imageView.getAdapterPosition()).getTime());
                intent.putExtra("subject",onlines.get(imageView.getAdapterPosition()).getSubject());
                intent.putExtra("education",onlines.get(imageView.getAdapterPosition()).getEducation());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return onlines.size();
    }

    public static class ImageView extends RecyclerView.ViewHolder{
        public TextView name,subject;
        public CardView cardView;
        public ImageView(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.T_name);
            subject=itemView.findViewById(R.id.T_sub);
    cardView=itemView.findViewById(R.id.on);
        }
    }
}
