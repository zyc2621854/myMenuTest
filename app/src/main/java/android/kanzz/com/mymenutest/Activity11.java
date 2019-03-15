package android.kanzz.com.mymenutest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.kanzz.com.mymenutest.Adapter.Activity11Adapter;
import android.kanzz.com.mymenutest.Entity.GalleryItem;
import android.kanzz.com.mymenutest.Network.FlickrFetchr;
import android.kanzz.com.mymenutest.Network.ThumbnailDownloader;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity11 extends AppCompatActivity {

    private static final String TAG="Activity11";
    private List<GalleryItem> mItemList=new ArrayList<>();
    private ThumbnailDownloader<Activity11Adapter.PhotoHolder> mThumbnailDownloader;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);
        new FetchItemsTask().execute();

        Handler responseHandler=new Handler();
        mThumbnailDownloader=new ThumbnailDownloader<>(responseHandler);
        mThumbnailDownloader.setThumbnailDownloaderListener(
                new ThumbnailDownloader.ThumbnailDownloaderListener<Activity11Adapter.PhotoHolder>() {
                    @Override
                    public void onThumbnailDownloaded(Activity11Adapter.PhotoHolder photoHolder, Bitmap bitmap) {
                        Drawable drawable=new BitmapDrawable(getResources(),bitmap); //转换bitmap
                        photoHolder.bindDrawable(drawable);
                    }
                }
        );
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();
        mRecyclerView=findViewById(R.id.activity11_rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        setAdapter();

    }

    private void setAdapter(){
        mRecyclerView.setAdapter(new Activity11Adapter(this,mItemList,mThumbnailDownloader));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
        mThumbnailDownloader.clearQueue();
    }

    private class FetchItemsTask extends AsyncTask<Void,Void,List<GalleryItem>> {
        @Override
        protected List<GalleryItem> doInBackground(Void... voids) {
            return new FlickrFetchr().fetchItems();

        }

        @Override
        protected void onPostExecute(List<GalleryItem> items) {
            mItemList=items;
            setAdapter();
        }
    }
}
