package android.kanzz.com.mymenutest;

import android.app.Activity;
import android.content.Intent;
import android.kanzz.com.mymenutest.Fragement.Chat;
import android.kanzz.com.mymenutest.Fragement.Find;
import android.kanzz.com.mymenutest.Fragement.Friend;
import android.kanzz.com.mymenutest.ViewPager.ViewPagerFragmentAdapter;
import android.kanzz.com.mymenutest.view.SlidingTabLayout;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import android.kanzz.com.mymenutest.Entity.User2;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends BaseActivity implements View.OnClickListener{


    private static final String TAG = "MainActivity.TAG";
    public RelativeLayout firstRelativeLayout;
    public RelativeLayout secondRelativeLayout;
    public RelativeLayout thirdRelativeLayout;
    ViewPager mViewPager;
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    FragmentManager mFragmentManager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;

    public void initViewPager() {
         mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
         mViewPager.setAdapter(mViewPagerFragmentAdapter);
         mViewPager.setCurrentItem(0);
         updateBottomRelativeLayoutSelect(true,false,false);
     }

    public void initView() {
         mViewPager = (ViewPager) findViewById(R.id.viewpager);
         firstRelativeLayout = (RelativeLayout) findViewById(R.id.firstRelativeLayout);
         firstRelativeLayout.setOnClickListener(this);
         secondRelativeLayout = (RelativeLayout) findViewById(R.id.secondRelativeLayout);
         secondRelativeLayout.setOnClickListener(this);
         thirdRelativeLayout = (RelativeLayout) findViewById(R.id.thirdRelativeLayout);
         thirdRelativeLayout.setOnClickListener(this);
         mNavigationView=(NavigationView)findViewById(R.id.nav_view);
         mNavigationView.setCheckedItem(R.id.nav_call);
         mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
         toolbar=(Toolbar)findViewById(R.id.toolbar_activity_choose);
        setSupportActionBar(toolbar);
     }

    public void initFragmetList() {
         Fragment chat = new Chat();
         Fragment friend = new Friend();
         Fragment find = new Find();
         mFragmentList.add(chat);
         mFragmentList.add(friend);
         mFragmentList.add(find);
     }





    @Override
    public void onClick(View v){
        switch (v.getId()) {
             case R.id.firstRelativeLayout:
                 mViewPager.setCurrentItem(0);
                 updateBottomRelativeLayoutSelect(true,false,false);
                 break;
             case R.id.secondRelativeLayout:
                 mViewPager.setCurrentItem(1);
                 updateBottomRelativeLayoutSelect(false,true,false);
                 break;
             case R.id.thirdRelativeLayout:
                 mViewPager.setCurrentItem(2);
                 updateBottomRelativeLayoutSelect(false,false,true);
                 break;
//            case R.id.button_test1:
//                mDrawerLayout.openDrawer(Gravity.LEFT);
//                break;
             default:
                 break;
         }
    }

    private void updateBottomRelativeLayoutSelect(boolean f, boolean s, boolean t) {
         firstRelativeLayout.setSelected(f);
         secondRelativeLayout.setSelected(s);
         thirdRelativeLayout.setSelected(t);
     }

    class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
 //            Log.d(TAG,"onPageScrooled");
         }
         @Override
         public void onPageSelected(int position) {
             Log.d(TAG,"onPageSelected");
             boolean[] state = new boolean[3];
             state[position] = true;
             updateBottomRelativeLayoutSelect(state[0],state[1],state[2]);
         }
         @Override
         public void onPageScrollStateChanged(int state) {
             Log.d(TAG,"onPageScrollStateChanged");
         }
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_choose);
        initFragmetList();
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
        initView();
        initViewPager();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
//        initListener();


        /*测试方法*/

//        if(savedInstanceState==null){
//            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//            FragmentTransaction fragment=new SlidingTabsBasicFragment();
//            transaction.replace(R.id.sample_content_fragment,fragment);
//            transaction.commit();
//        }
    }

//    @Override
//    public void onClick(View v){
//        if(v==btn_1)
//        {
//            createUser();
//        }
//    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                ShowToast("You click Backup");
                break;
            case R.id.delete:
                ShowToast("You click Delete");
                break;
            case R.id.settings:
                ShowToast("You click Settings");
                break;
            case R.id.activity1:
                Intent intent1 = new Intent(ChooseActivity.this, Activity1.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.activity2:
                Intent intent2 = new Intent(ChooseActivity.this, Activity2.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.activity3:
                Intent intent3 = new Intent(ChooseActivity.this, Activity3.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.activity4:
                Intent intent4 = new Intent(ChooseActivity.this, Activity4.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.activity5:
                Intent intent5 = new Intent(ChooseActivity.this, Activity5.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.activity6:
                Intent intent6 = new Intent(ChooseActivity.this, Activity6.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.activity7:
                Intent intent7 = new Intent(ChooseActivity.this, Activity7.class);
                startActivity(intent7);
                finish();
                break;
            default:
        }
        return true;
    }

    private void createUser(){
        final User2 u2=new User2();
        u2.setAccount("abcd");
        u2.setPassword("1234");
        u2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    ShowToast("添加数据成功，返回objectId为："+objectId);
                }else {
                    ShowToast("创建数据失败"+e.getMessage());
                }
            }
        });
    }




}
