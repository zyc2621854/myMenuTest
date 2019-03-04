package android.kanzz.com.mymenutest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.kanzz.com.mymenutest.Adapter.Activity7Adapter;
import android.kanzz.com.mymenutest.Entity.RecyclerView_baseitem;
import android.kanzz.com.mymenutest.Entity.RecyclerView_item1;
import android.kanzz.com.mymenutest.Entity.RecyclerView_itemTitle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.http.bean.Init;

import static org.litepal.LitePalApplication.getContext;

public class Activity7 extends BaseActivity {


    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    List<RecyclerView_baseitem> item1 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
        }
        InitView();
        InitTitle();
        InitListItem();
        InitRecyclerView();



//        slider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }

    protected void InitTitle() {
        List<Integer> imageR = new ArrayList<Integer>();
        final List<String> description = new ArrayList<String>();
        imageR.add(R.drawable.img_1_small1);
        imageR.add(R.drawable.img_1_small2);
        imageR.add(R.drawable.img_2_small1);
        description.add("育儿");
        description.add("IT");
        description.add("耐克");
        RecyclerView_itemTitle titleBean = new RecyclerView_itemTitle();
        titleBean.setImageR(imageR);
        titleBean.setDescription(description);
        titleBean.setViewType(Activity7Adapter.TITLE);
        item1.add(titleBean);
    }

    protected void InitListItem() {
        RecyclerView_item1 item_a = new RecyclerView_item1(R.drawable.apple, "apple", "2018-2-22", "abcd", R.drawable.blackbrain);
        item_a.setViewType(Activity7Adapter.BODY);
        item1.add(item_a);
        RecyclerView_item1 item_b = new RecyclerView_item1(R.drawable.banana, "banana", "2018-2-23", "efgh", R.drawable.blackmail);
        item_b.setViewType(Activity7Adapter.BODY);
        item1.add(item_b);
        RecyclerView_item1 item_c = new RecyclerView_item1(R.drawable.cherry, "cherry", "2018-2-24", "hijk", R.drawable.blackmusic);
        item_c.setViewType(Activity7Adapter.BODY);
        item1.add(item_c);
    }

    protected void InitRecyclerView() {
        LinearLayoutManager lm = new LinearLayoutManager(this);
        Activity7Adapter adapter = new Activity7Adapter(item1);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(lm);
    }


    protected void InitView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity7);
        setSupportActionBar(mToolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity7_rv);
    }
}

//    @Override
//    protected void onStop() {
//        slider.stopAutoCycle();
//        super.onStop();
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity7_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.activity7_menu_item1:
//                slider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
//            case R.id.activity7_menu_item2:
//                slider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
//            case R.id.activity7_menu_item3:
//                slider.setPresetTransformer(SliderLayout.Transformer.Fade);
//        }
//        return true;
//    }
//}
