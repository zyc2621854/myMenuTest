package android.kanzz.com.mymenutest;

import android.kanzz.com.mymenutest.Adapter.Activity8Adapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class Activity8 extends BaseActivity {
    List<String> datas=new ArrayList<>();
    RecyclerView rv;
    Activity8Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);
        InitList();
        InitRecyclerView();
    }

    protected void InitList(){
        for(int i=0;i<20;i++)
        {
            datas.add("item"+i);
        }
    }

    protected void InitRecyclerView(){
        rv=findViewById(R.id.recycler_view2);
        mAdapter=new Activity8Adapter(this,datas);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);

        rv.setAdapter(mAdapter);
//        rv.setLayoutManager(linearLayoutManager);
//        rv.setLayoutManager(gridLayoutManager);
        rv.setLayoutManager(staggeredGridLayoutManager);
    }
}
