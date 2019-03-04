package android.kanzz.com.mymenutest.Adapter;

import android.content.Context;
import android.kanzz.com.mymenutest.Activity7;
import android.kanzz.com.mymenutest.BaseActivity;
import android.kanzz.com.mymenutest.Entity.RecyclerView_baseitem;
import android.kanzz.com.mymenutest.Entity.RecyclerView_item1;
import android.kanzz.com.mymenutest.Entity.RecyclerView_itemTitle;
import android.kanzz.com.mymenutest.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.List;

public class Activity7Adapter extends RecyclerView.Adapter{


    public final static int TITLE=1001;
    public final static int BODY=1002;

    private List<RecyclerView_baseitem> mRecyclerView_item1List;
    private Toast mToast;
    private Context context;
    private LayoutInflater mInflater;

    class TitleHolder extends RecyclerView.ViewHolder{
        SliderLayout slider;
        PagerIndicator mIndicator;

        public TitleHolder(View view){
            super(view);
            slider=(SliderLayout)view.findViewById(R.id.activity7_slider);
            mIndicator=(PagerIndicator)view.findViewById(R.id.custom_indicator);
        }
    }

    class BodyHolder extends RecyclerView.ViewHolder {
        ImageView touXiang;
        TextView author;
        TextView time;
        TextView abstractText;
        ImageView abstractImage;
        public BodyHolder(View view) {
            super(view);
            touXiang=view.findViewById(R.id.activity7_touxiang_iv);
            author=view.findViewById(R.id.activity7_author_tv);
            time=view.findViewById(R.id.activity7_time_tv);
            abstractText=view.findViewById(R.id.activity7_abstract_tv);
            abstractImage=view.findViewById(R.id.activity7_abstract_iv);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mRecyclerView_item1List.size() > 0) {
            return mRecyclerView_item1List.get(position).getViewType();
        }
        return super.getItemViewType(position);
    }



    public Activity7Adapter(List<RecyclerView_baseitem> item1) {
        mRecyclerView_item1List=item1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        if(context==null){
            context=parent.getContext();
        }
        if(mInflater==null){
            mInflater=LayoutInflater.from(context);
        }

        View view;
        switch (viewType){
            case TITLE:
                view=mInflater.inflate(R.layout.activity7_itemtest,parent,false);
                return new TitleHolder(view);
            case BODY:
                view=mInflater.inflate(R.layout.recyclerviewitem1,parent,false);
                return new BodyHolder(view);
        }
        return null;

//        View v=View.inflate(parent.getContext(),R.layout.recyclerviewitem1,null);
//        final ViewHolder viewHolder=new ViewHolder(v);
//
//
//        viewHolder.author.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mToast=Toast.makeText(parent.getContext(),"作者",Toast.LENGTH_SHORT);
//                mToast.show();
//            }
//        });
//        viewHolder.abstarctText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mToast=Toast.makeText(parent.getContext(),"文字哦",Toast.LENGTH_SHORT);
//                mToast.show();
//            }
//        });
//        viewHolder.abstactImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mToast=Toast.makeText(parent.getContext(),"图片哦",Toast.LENGTH_SHORT);
//                mToast.show();
//            }
//        });
//        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TitleHolder){
            RecyclerView_itemTitle titleBean=(RecyclerView_itemTitle)mRecyclerView_item1List.get(position);
            for(int i=0;i<titleBean.getImageR().size();i++){
                TextSliderView textSliderView=new TextSliderView(context);
                textSliderView
                        .image(titleBean.getImageR().get(i))
                        .description(titleBean.getDescription().get(i))
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                String text=slider.getDescription();
                                new Toast(context).makeText(context,text,Toast.LENGTH_SHORT);
                            }
                        });
                ((TitleHolder)holder).slider.addSlider(textSliderView);
            }

            ((TitleHolder)holder).slider.setCustomAnimation(new DescriptionAnimation());
            ((TitleHolder)holder).slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            ((TitleHolder)holder).slider.setDuration(3000);
            ((TitleHolder)holder).slider.setCustomIndicator(((TitleHolder)holder).mIndicator);
        }

        if(holder instanceof BodyHolder) {
            RecyclerView_item1 bodyBean=(RecyclerView_item1) mRecyclerView_item1List.get(position);
            ((BodyHolder)holder).touXiang.setImageResource(bodyBean.getTouXiang());
            ((BodyHolder)holder).author.setText(bodyBean.getAuthor());
            ((BodyHolder)holder).time.setText(bodyBean.getTime());
            ((BodyHolder)holder).abstractText.setText(bodyBean.getAbstractText());
            ((BodyHolder)holder).abstractImage.setImageResource(bodyBean.getAbstractImage());
        }

    }

    @Override
    public int getItemCount(){
        return mRecyclerView_item1List.size();
    }


}
