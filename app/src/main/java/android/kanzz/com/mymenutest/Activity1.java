package android.kanzz.com.mymenutest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Activity1 extends BaseActivity implements View.OnClickListener {

    private Button progressButton;
    private ProgressBar mProgressBar;

    private void initView(){
        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar_horizontal);
        progressButton=(Button)findViewById(R.id.progress_button);
        progressButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        initView();
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
        }
    }
}
