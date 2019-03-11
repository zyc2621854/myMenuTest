package android.kanzz.com.mymenutest.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private static String a[]={"嘻嘻","哈哈","尕尕"};
    private List<Fragment> mList=new ArrayList<Fragment>();
    public ViewPagerFragmentAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.mList=list;
    }

    @Override
    public Fragment getItem(int position){
        return mList.get(position);
    }

    @Override
    public int getCount(){
        return mList!=null?mList.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return a[position];
    }
}
