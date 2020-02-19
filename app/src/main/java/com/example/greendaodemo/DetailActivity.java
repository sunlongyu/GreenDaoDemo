package com.example.greendaodemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greendaodemo.Model.GoodsModel;

public class DetailActivity extends AppCompatActivity {


    private EditText infoEditText;
    private GoodsModel goodsModel;
    private GreenDaoManager greenDaoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        greenDaoManager = new GreenDaoManager(this);
        goodsModel = getIntent().getParcelableExtra("good_model");

        infoEditText = findViewById(R.id.infoEditText);
        infoEditText.setText(goodsModel.getInfo());
    }

    /**
     * 保存编辑点击事件
     *
     * @param v
     */
    public void onEditClick(View v) {
        String info = infoEditText.getText().toString();
        goodsModel.setInfo(info);
        greenDaoManager.updateGoodsInfo(goodsModel);
        onBackPressed();
    }

    /**
     * 删除商品
     *
     * @param v
     */
    public void onDelClick(View v) {
        String info = infoEditText.getText().toString();
        goodsModel.setInfo(info);
        greenDaoManager.deleteGoodsInfo(goodsModel);
        onBackPressed();
    }
}
