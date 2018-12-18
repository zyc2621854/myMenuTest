package android.kanzz.com.mymenutest.Fragement;

import android.content.Context;
import android.kanzz.com.mymenutest.Adapter.ContactsAdapter2;
import android.kanzz.com.mymenutest.Entity.Contacts;
import android.kanzz.com.mymenutest.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class Find extends Fragment {
    View mView;
    private List<Contacts> mContactsList=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        initContacts();
        layoutManager=new LinearLayoutManager(context);


    }
    private void test(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_find,null);
            mRecyclerView=mView.findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(layoutManager);
            ContactsAdapter2 adapter=new ContactsAdapter2(mContactsList);
            mRecyclerView.setAdapter(adapter);
        }
        return mView;
    }


    private void initContacts(){
        Contacts contacts1=new Contacts("星星","用爱发光",R.drawable.blackstar);
        mContactsList.add(contacts1);
        Contacts contacts2=new Contacts("邮件","有人给你发信息啦！",R.drawable.blackmail);
        mContactsList.add(contacts2);
        Contacts contacts4=new Contacts("音乐","我是真的好听",R.drawable.blackmusic);
        mContactsList.add(contacts4);
        Contacts contacts5=new Contacts("标签","没有东西需要标记，那为什么需要标签",R.drawable.blacktag);
        mContactsList.add(contacts5);
        Contacts contacts6=new Contacts("音乐","我是真的好听",R.drawable.blackmusic);
        mContactsList.add(contacts6);
        Contacts contacts7=new Contacts("音乐","我是真的好听",R.drawable.blackmusic);
        mContactsList.add(contacts7);
        Contacts contacts8=new Contacts("音乐","我是真的好听",R.drawable.blackmusic);
        mContactsList.add(contacts8);
    }
}
