package com.example.greendaodemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greendaodemo.Model.GoodsModel;
import com.example.greendaodemo.adapter.MyRecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private GreenDaoManager greenDaoManager;

    @Override
    protected void onResume() {
        super.onResume();
        notifyAdapter(greenDaoManager.findAll());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greenDaoManager = new GreenDaoManager(this);

        //找到RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //设置LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);

        //setAdapter
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (greenDaoManager.findAll()!=null){
            notifyAdapter(greenDaoManager.findAll());
        }
    }

    /**
     * data <--> view 不同查询的时候用不同的数据显示到视图中
     *
     * @param dataSource
     */
    public void notifyAdapter(List<GoodsModel> dataSource) {
        myRecyclerViewAdapter.setDataSource(dataSource);
    }

    /**
     * 插入所有数据
     *
     * @param view
     */
    public void insertGoods(View view) {
        greenDaoManager.insertGoods();
    }

    /**
     * 查询所有数据
     *
     * @param view
     */
    public void findAllGoods(View view) {
        List<GoodsModel> goodsModels = greenDaoManager.findAll();
        notifyAdapter(goodsModels);
    }

    /**
     * 查询水果数据
     *
     * @param view
     */
    public void findFruits(View view) {
        List<GoodsModel> goodsModels = greenDaoManager.queryFruits();
        notifyAdapter(goodsModels);
    }

    /**
     * 查询零食数据
     *
     * @param view
     */
    public void findSnacks(View view) {
        List<GoodsModel> goodsModels = greenDaoManager.querySnacks();
        notifyAdapter(goodsModels);
    }
}
