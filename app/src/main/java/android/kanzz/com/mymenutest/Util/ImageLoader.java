package android.kanzz.com.mymenutest.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImageLoader {
    private LruCache<String,Bitmap> mMemoryCache;
    private Context mContext;

    private ImageLoader(Context context){
        mContext=context.getApplicationContext();
        int maxMemory=(int)(Runtime.getRuntime().maxMemory()/1024);
        int cacheSize=maxMemory/8;
        mMemoryCache=new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };
    }

    private Bitmap loadBitmapFromMemCache(String url){
        final String key=hashKeyFromUrl(url);
        Bitmap bitmap=getBitmapFromMemCache(key);
        return bitmap;
    }

    private String hashKeyFromUrl(String url){
        String cacheKey;
        try{
            final MessageDigest mDigest=MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey=bytesToHExString(mDigest.digest());
        }catch (NoSuchAlgorithmException e){
            cacheKey=String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHExString(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            String hex=Integer.toHexString(0xFF&bytes[i]);
            if(hex.length()==1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private void addBitmapToMemoryCache(String key,Bitmap bitmap){
        if(getBitmapFromMemCache(key)==null){
            mMemoryCache.put(key,bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String key){
        return mMemoryCache.get(key);
    }
}
