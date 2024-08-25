package com.example.orangesale_05.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.orangesale_05.R;
import com.example.orangesale_05.fragment.IndexFragment;
import com.example.orangesale_05.fragment.PearsonFragment;
import com.example.orangesale_05.fragment.ProductFragment;
import com.example.orangesale_05.fragment.ShoppingCartFragment;

public class IndexActivity extends Activity implements View.OnClickListener {
    private IndexFragment indexFragment;
    private ProductFragment productFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private PearsonFragment pearsonFragment;
    private LinearLayout indexLine, productLine, shoppingCartLine, pearsonLine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initIndexFragment();
    }

    /**
     * 组件初始化
     */
    private void init() {
        indexLine = findViewById(R.id.content_index);
        indexLine.setOnClickListener(this);
        productLine = findViewById(R.id.content_product);
        productLine.setOnClickListener(this);
        shoppingCartLine = findViewById(R.id.content_cart);
        shoppingCartLine.setOnClickListener(this);
        pearsonLine = findViewById(R.id.content_pearson);
        pearsonLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_index:
                initIndexFragment();
                break;
            case R.id.content_product:
                initproductFragment();
                break;
            case R.id.content_cart:
                initshoppingCartFragment();
                break;
            case R.id.content_pearson:
                initpearsonFragment();
                break;
        }
    }

    /**
     * 初始化首页Fragment
     */
    private void initIndexFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (indexFragment == null) {
            indexFragment = new IndexFragment();
        }
        transaction.replace(R.id.main_content, indexFragment);
        transaction.commit();
    }

    /**
     * 初始化产品Fragment
     */
    private void initproductFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (productFragment == null) {
            productFragment = new ProductFragment();
        }
        transaction.replace(R.id.main_content, productFragment);
        transaction.commit();
    }

    /**
     * 初始化购物车Fragment
     */
    private void initshoppingCartFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (shoppingCartFragment == null) {
            shoppingCartFragment = new ShoppingCartFragment();
        }
        transaction.replace(R.id.main_content, shoppingCartFragment);
        transaction.commit();
    }

    /**
     * 初始化个人Fragment
     */
    private void initpearsonFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (pearsonFragment == null) {
            Intent intent = IndexActivity.this.getIntent();
            Bundle bundle = intent.getExtras();
            pearsonFragment = new PearsonFragment();
            pearsonFragment.setArguments(bundle);
        }
        transaction.replace(R.id.main_content, pearsonFragment);
        transaction.commit();
    }

}
