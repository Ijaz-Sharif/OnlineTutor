package is.fb.onlinetutor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHoler> {
    private Context context;
    private List<T_Profile> t_profiles;
    public ImageAdapter(Context c,List<T_Profile> uplod){
      context=c;
      t_profiles=uplod;

    }
    @NonNull
    @Override
    public ImageAdapter.ImageViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.profile,viewGroup,false);
        return  new ImageViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageAdapter.ImageViewHoler imageViewHoler, int i) {
        final T_Profile curentUpload=t_profiles.get(i);

        imageViewHoler.n.setText(curentUpload.getT_name());
        imageViewHoler.number.setText(curentUpload.getT_number());
        imageViewHoler.edu.setText(curentUpload.getD_name());
        imageViewHoler.m.setText(curentUpload.getMail());
        Picasso.with(context)
                .load(curentUpload.getE_image())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(imageViewHoler.imageView);
        imageViewHoler.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Aprove_details.class);
                intent.putExtra("name",t_profiles.get(imageViewHoler.getAdapterPosition()).getT_name());
                intent.putExtra("number",t_profiles.get(imageViewHoler.getAdapterPosition()).getT_number());
                intent.putExtra("degree",t_profiles.get(imageViewHoler.getAdapterPosition()).getD_name());
                intent.putExtra("degree_pic",t_profiles.get(imageViewHoler.getAdapterPosition()).getE_image());
                intent.putExtra("key",t_profiles.get(imageViewHoler.getAdapterPosition()).getKey());
                intent.putExtra("mail",t_profiles.get(imageViewHoler.getAdapterPosition()).getMail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return t_profiles.size();
    }
    public static class ImageViewHoler extends RecyclerView.ViewHolder{
         TextView n,number,edu,m;
         ImageView imageView;
         CardView cardView;
        public ImageViewHoler(@NonNull View itemView) {
            super(itemView);
            n=itemView.findViewById(R.id.teacher_name);
            m=itemView.findViewById(R.id.m);
            number=itemView.findViewById(R.id.teacher_number);
            edu=itemView.findViewById(R.id.eduaction);
            imageView=itemView.findViewById(R.id.teacher_degree);
            cardView=itemView.findViewById(R.id.cardview);

        }
    }
}
