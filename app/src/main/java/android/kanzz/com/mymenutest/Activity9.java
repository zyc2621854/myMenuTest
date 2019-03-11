package android.kanzz.com.mymenutest;

import android.kanzz.com.mymenutest.Adapter.Activity9ViewPagerAdapter;
import android.kanzz.com.mymenutest.Adapter.ViewPagerFragmentAdapter;
import android.kanzz.com.mymenutest.Fragement.Chat;
import android.kanzz.com.mymenutest.Fragement.Find;
import android.kanzz.com.mymenutest.Fragement.Friend;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Activity9 extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    ViewPagerFragmentAdapter mFragmentAdapter;

    FragmentManager fm;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm=getSupportFragmentManager();
        setContentView(R.layout.activity_9);
        mTabLayout=(TabLayout)findViewById(R.id.activity9_tl);
        mViewPager=(ViewPager)findViewById(R.id.activity9_vp);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Snackbar.make(mTabLayout, "第一次使用SnackBar", Snackbar.LENGTH_SHORT).show();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

//        Activity9ViewPagerAdapter myAdapter=new Activity9ViewPagerAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(myAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);





        Fragment chat = new Chat();
        Fragment friend = new Friend();
        Fragment find = new Find();
        mFragmentList.add(chat);
        mFragmentList.add(friend);
        mFragmentList.add(find);
        mFragmentAdapter=new ViewPagerFragmentAdapter(fm,mFragmentList);
        mViewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),mFragmentList));
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
