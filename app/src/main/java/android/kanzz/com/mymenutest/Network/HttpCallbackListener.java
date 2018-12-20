package android.kanzz.com.mymenutest.Network;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
