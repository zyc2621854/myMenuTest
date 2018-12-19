package android.kanzz.com.mymenutest;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.kanzz.com.mymenutest.ActivityCollector.ActivityCollector;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import cn.bmob.v3.Bmob;

public class BaseActivity extends AppCompatActivity {
    private String Bmob_AppId="38e64a6b21b63fdc61dba5fbf0839aab";
    Toast mToast;
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,Bmob_AppId);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.kanzz.com.mymenutest.FORCE_OFFLINE");//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        receiver=new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void ShowToast(String text){
        if(!TextUtils.isEmpty(text)){
            if(mToast==null){
                mToast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
            }else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline.Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent=new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
