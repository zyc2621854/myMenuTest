package android.kanzz.com.mymenutest.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.kanzz.com.mymenutest.Activity11;
import android.kanzz.com.mymenutest.Entity.GalleryItem;
import android.kanzz.com.mymenutest.Network.ThumbnailDownloader;
import android.kanzz.com.mymenutest.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class Activity11Adapter extends RecyclerView.Adapter<Activity11Adapter.PhotoHolder> {
    Context mContext;
    List<GalleryItem> datas;
    ThumbnailDownloader<PhotoHolder> mThumbnailDownloader;

    public Activity11Adapter(Context context ,List<GalleryItem> datas,ThumbnailDownloader<PhotoHolder> downloader){
        mContext=context;
        this.datas=datas;
        mThumbnailDownloader=downloader;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public PhotoHolder(View view){
            super(view);
            mImageView=view.findViewById(R.id.activity11_iv);

        }

        public void bindDrawable(Drawable drawable){
            mImageView.setImageDrawable(drawable);
        }
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.activity11_item,viewGroup,false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Drawable placeholder=mContext.getResources().getDrawable(R.drawable.apple);
        holder.bindDrawable(placeholder);
        GalleryItem galleryItem=datas.get(position);//由postion获取不同图片项
        mThumbnailDownloader.queueThumbnail(holder,galleryItem.getUrl());//再用getURl获得每个图片项的url地址，把它和当前装载view的容器传人下载器队列中
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
