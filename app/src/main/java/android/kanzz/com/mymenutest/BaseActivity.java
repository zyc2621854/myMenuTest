package android.kanzz.com.mymenutest;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import cn.bmob.v3.Bmob;

public class BaseActivity extends AppCompatActivity {
    private String Bmob_AppId="38e64a6b21b63fdc61dba5fbf0839aab";
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,Bmob_AppId);
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
}
