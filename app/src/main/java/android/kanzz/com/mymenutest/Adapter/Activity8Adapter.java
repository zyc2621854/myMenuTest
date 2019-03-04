package android.kanzz.com.mymenutest.Adapter;

import android.content.Context;
import android.kanzz.com.mymenutest.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Activity8Adapter extends RecyclerView.Adapter<Activity8Adapter.ViewHolder>{
    Context context;
    List<String> datas;
    public Activity8Adapter(Context context,List<String> datas){
        this.context=context;
        this.datas=datas;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
        View v;
        if (viewType==0){v=View.inflate(viewGroup.getContext(), R.layout.recyclerviewitem2,null);}
        else {v=View.inflate(viewGroup.getContext(), R.layout.recyclerviewitem2a,null);}

        final ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(datas.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return 0;
        }else {
            return 2;
        }
    }

    @Override
    public int getItemCount(){
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView=view.findViewById(R.id.activity8_tv);
        }
    }


}
