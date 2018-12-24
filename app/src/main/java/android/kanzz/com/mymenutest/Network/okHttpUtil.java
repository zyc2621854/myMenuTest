package android.kanzz.com.mymenutest.Network;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class okHttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
