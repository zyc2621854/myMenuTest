package android.kanzz.com.mymenutest.Adapter;

import android.kanzz.com.mymenutest.Activity7;
import android.kanzz.com.mymenutest.Entity.RecyclerView_item1;
import android.kanzz.com.mymenutest.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Activity7Adapter extends RecyclerView.Adapter<Activity7Adapter.ViewHolder> {


    private List<RecyclerView_item1> mRecyclerView_item1List;
    private Toast mToast;

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
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View v=View.inflate(parent.getContext(),R.layout.recyclerviewitem1,null);
        final ViewHolder viewHolder=new ViewHolder(v);
        viewHolder.author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast=Toast.makeText(parent.getContext(),"作者",Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
        viewHolder.abstarctText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast=Toast.makeText(parent.getContext(),"文字哦",Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
        viewHolder.abstactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast=Toast.makeText(parent.getContext(),"图片哦",Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
        return viewHolder;
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
