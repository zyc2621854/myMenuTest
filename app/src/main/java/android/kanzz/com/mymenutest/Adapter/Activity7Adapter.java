package android.kanzz.com.mymenutest.Adapter;

import android.kanzz.com.mymenutest.Entity.RecyclerView_item1;
import android.kanzz.com.mymenutest.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Activity7Adapter extends RecyclerView.Adapter<Activity7Adapter.ViewHolder> {


    private List<RecyclerView_item1> mRecyclerView_item1List;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView touXiang;
        TextView author;
        TextView time;
        TextView abstarctText;
        ImageView abstactImage;
        public ViewHolder(View view) {
            super(view);
            touXiang=view.findViewById(R.id.activity7_touxiang_iv);
            author=view.findViewById(R.id.activity7_author_tv);
            time=view.findViewById(R.id.activity7_time_tv);
            abstarctText=view.findViewById(R.id.activity7_abstract_tv);
            abstactImage=view.findViewById(R.id.activity7_abstract_iv);

        }
    }

    public Activity7Adapter(List<RecyclerView_item1> item1) {
        mRecyclerView_item1List=item1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v=View.inflate(parent.getContext(),R.layout.recyclerviewitem1,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.touXiang.setImageResource(mRecyclerView_item1List.get(position).getTouXiang());
        holder.author.setText(mRecyclerView_item1List.get(position).getAuthor());
        holder.time.setText(mRecyclerView_item1List.get(position).getTime());
        holder.abstarctText.setText(mRecyclerView_item1List.get(position).getAbstractText());
        holder.abstactImage.setImageResource(mRecyclerView_item1List.get(position).getAbstractImage());
    }

    @Override
    public int getItemCount(){
        return mRecyclerView_item1List.size();
    }


}
