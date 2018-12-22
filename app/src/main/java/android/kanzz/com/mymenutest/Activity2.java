package android.kanzz.com.mymenutest;

import android.kanzz.com.mymenutest.Adapter.FruitAdapter;
import android.kanzz.com.mymenutest.Entity.Fruit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activity2 extends AppCompatActivity {

    private Fruit[] fruits={new Fruit("Apple",R.drawable.apple),
            new Fruit("Banana",R.drawable.banana),
            new Fruit("Orange",R.drawable.orange),
            new Fruit("Watermelon",R.drawable.watermelon),
            new Fruit("Pear",R.drawable.pear),
            new Fruit("Grape",R.drawable.grape),
            new Fruit("Pineapple",R.drawable.pineapple),
            new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Cherry",R.drawable.cherry),
            new Fruit("Mango",R.drawable.mango)};
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter mFruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        initFruits();
        RecyclerView recyclerView=findViewById(R.id.recycler_view1);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        mFruitAdapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(mFruitAdapter);

    }

    private void initFruits(){
        fruitList.clear();
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
}
