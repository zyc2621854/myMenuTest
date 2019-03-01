package android.kanzz.com.mymenutest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.http.bean.Init;

public class Activity7 extends AppCompatActivity {

    SliderLayout slider;
    PagerIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
        }
        InitView();
        List<Integer> imageR=new ArrayList<Integer>();
        List<String> description=new ArrayList<String>();
        imageR.add(R.drawable.img_1_small1);
        imageR.add(R.drawable.img_1_small2);
        imageR.add(R.drawable.img_2_small1);
        description.add("育儿");
        description.add("IT");
        description.add("耐克");



        for(int i=0;i<imageR.size();i++){
            TextSliderView textSliderView=new TextSliderView(this);
            textSliderView.image(imageR.get(i)).description(description.get(i));
            slider.addSlider(textSliderView);
        }


        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setDuration(3000);
        slider.setCustomIndicator(mIndicator);



    }



    protected void InitView(){
        slider=(SliderLayout)findViewById(R.id.activity7_slider);
        mIndicator=(PagerIndicator)findViewById(R.id.custom_indicator);
    }

    @Override
    protected void onStop() {
        slider.stopAutoCycle();
        super.onStop();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate();
//        return super.onCreateOptionsMenu(menu);
//    }
}
