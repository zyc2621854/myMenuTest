package android.kanzz.com.mymenutest;

import android.kanzz.com.mymenutest.widget.NumberAddSubView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity10 extends AppCompatActivity {

    NumberAddSubView mNumberAddSubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);
        mNumberAddSubView=(findViewById(R.id.activity10_nas));
        mNumberAddSubView.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onButtonAddClick(View view, int value) {
                Snackbar.make(mNumberAddSubView,"right",Snackbar.LENGTH_SHORT);
            }

            @Override
            public void onButtonSubClick(View view, int value) {
                Snackbar.make(mNumberAddSubView,"left",Snackbar.LENGTH_SHORT);
            }
        });
    }
}
