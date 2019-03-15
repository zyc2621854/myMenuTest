package android.kanzz.com.mymenutest;

import android.content.Intent;
import android.kanzz.com.mymenutest.Network.FlickrFetchr;
import android.kanzz.com.mymenutest.Network.HttpCallbackListener;
import android.kanzz.com.mymenutest.Network.HttpUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

public class Activity1 extends BaseActivity implements View.OnClickListener {

    private static String Tag="Activity1";

    private Button progressButton;
    private Button offlineButton;
    private Button testNetworkButton;
    private ProgressBar mProgressBar;
    private String address;


    private void initView(){
        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar_horizontal);
        progressButton=(Button)findViewById(R.id.progress_button);
        progressButton.setOnClickListener(this);
        offlineButton=(Button)findViewById(R.id.offline_button);
        offlineButton.setOnClickListener(this);
        testNetworkButton=(Button)findViewById(R.id.testNetwork_button);
        testNetworkButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        initView();
        address="https://www.baidu.com";
        new FetchItemsTask().execute();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.progress_button:
                ShowToast("+++++");
                int progress=mProgressBar.getProgress();
                progress=progress+10;
                if(progress<=100){
                    mProgressBar.setProgress(progress);
                }
                break;
            case R.id.offline_button:
                Intent intent=new Intent("android.kanzz.com.mymenutest.FORCE_OFFLINE");
                sendBroadcast(intent);
                break;
            case R.id.testNetwork_button:
                Log.d("Activity1","已点击");
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Log.d("Activity1",response);
                        ShowToast("成功");
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                break;
        }
    }


}
