package android.kanzz.com.mymenutest.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Activity9ViewPagerAdapter extends FragmentPagerAdapter{
    private static String a[]={"嘻嘻","哈哈","尕尕"};
    public Activity9ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return a[position];
    }

    @Override
    public Fragment getItem(int i) {
        return new Fragment();
    }
}
