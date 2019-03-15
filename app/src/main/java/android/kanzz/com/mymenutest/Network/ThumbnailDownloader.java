package android.kanzz.com.mymenutest.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ThumbnailDownloader<T> extends HandlerThread {
    private static final String TAG="ThumbnailDownloader";
    private static final int MESSAGE_DOWNLOAD=0;
    private Boolean mHasQuit=false;
    private Handler mRequestHandler;
    private ConcurrentMap<T,String> mRequestMap=new ConcurrentHashMap<>();
    private Handler mResponseHandler;
    private ThumbnailDownloaderListener<T> mThumbnailDownloaderListener;

    public interface ThumbnailDownloaderListener<T>{
        void onThumbnailDownloaded(T target,Bitmap thumbnail);
    }

    public void setThumbnailDownloaderListener(ThumbnailDownloaderListener<T> listener){
        mThumbnailDownloaderListener=listener;
    }




    public ThumbnailDownloader(Handler responseHandler){
        super(TAG);
        mResponseHandler=responseHandler;
    }

    @Override
    public boolean quit() {
        mHasQuit=true;
        return super.quit();
    }
    public void queueThumbnail(T obj,String url){
        Log.i(TAG,"Got a URL:"+url);

        if(url==null){
            mRequestMap.remove(obj);
        }else {
            mRequestMap.put(obj,url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD,obj).sendToTarget();//获得信息后自动变为此对象的target
        }
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==MESSAGE_DOWNLOAD){
                    T mobj=(T)msg.obj;
                    Log.i(TAG,"Got a request for URL:"+mRequestHandler);
                    handleRequest(mobj);
                }
            }
        };
    }

    public void clearQueue(){
        mRequestHandler.removeMessages(MESSAGE_DOWNLOAD);
        mRequestMap.clear();
    }

    private void handleRequest(final T obj){
        try{
            final String url=mRequestMap.get(obj);//不是直接获取url，而是通过传递的obj，把它作为键值查找map中对应的url
            if(url==null){
                return;
            }

            byte[] bitmapBytes=new FlickrFetchr().getUrlBytes(url);
            final Bitmap bitmap= BitmapFactory.decodeByteArray(bitmapBytes,0,bitmapBytes.length);
            Log.i(TAG,"Bitmap created");
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(mRequestMap.get(obj)!=url||mHasQuit){
                        return;
                    }

                    mRequestMap.remove(obj);
                    mThumbnailDownloaderListener.onThumbnailDownloaded(obj,bitmap);
                }
            });
        }catch (Exception ioe){
            Log.e(TAG,"Error downloading image",ioe);
        }
    }
}
