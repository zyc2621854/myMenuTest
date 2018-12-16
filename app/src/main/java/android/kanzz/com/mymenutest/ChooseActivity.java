package android.kanzz.com.mymenutest;

import android.app.Activity;
import android.content.Intent;
import android.kanzz.com.mymenutest.view.SlidingTabLayout;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import android.kanzz.com.mymenutest.Entity.User2;
import android.widget.TextView;

public class ChooseActivity extends BaseActivity implements View.OnClickListener{


//    private Button btn_1;

    private void initView(){
//        btn_1=(Button)findViewById(R.id.button_1);
    }

    @Override
    public void onClick(View v){
        
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initView();
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
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity1:
                Intent intent1 = new Intent(ChooseActivity.this, Activity1.class);
                startActivity(intent1);
                break;
            case R.id.activity2:
                Intent intent2 = new Intent(ChooseActivity.this, Activity1.class);
                startActivity(intent2);
                break;
            case R.id.activity3:
                Intent intent3 = new Intent(ChooseActivity.this, Activity1.class);
                startActivity(intent3);
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
