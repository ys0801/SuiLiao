package com.example.orangesale_05.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.orangesale_05.R;
import com.example.orangesale_05.adapter.Adapter;
import com.example.orangesale_05.entity.Product;
import com.example.orangesale_05.fragment.SetDetailFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends Activity {
    public OnChangeListener onchangedListener;
    private List<Product> productList;
    private List<String> productCategory = new ArrayList<>();
    private ListView titleList;
    private Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_category);
        initData();
        init();
        SetDetailFragment fragment = new SetDetailFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.category_detail, fragment);
        transaction.commit();
        titleList.setOnItemClickListener((parent, view, position, id) -> {
            adapter.setSelectedPosition(position);
            adapter.notifyDataSetInvalidated();
            if (onchangedListener != null) {
                onchangedListener.changeText(productList.get(position));
            }
        });

    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onchangedListener = onChangeListener;
    }

    public interface OnChangeListener {
        void changeText(Product product);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        productList = new ArrayList<>();
        productCategory.add("橘子");
        productCategory.add("橙子");
        productCategory.add("柚子");
        Product product = new Product();
        product.setImageUrlId(R.drawable.arrow_down);
        product.setProductName("橘子");
        product.setProductPrice(new BigDecimal("9.9"));
        Product product1 = new Product();
        product1.setImageUrlId(R.drawable.orange);
        product1.setProductName("橙子");
        product1.setProductPrice(new BigDecimal("29.9"));
        Product product2 = new Product();
        product2.setImageUrlId(R.drawable.arrow_left);
        product2.setProductName("柚子");
        product2.setProductPrice(new BigDecimal("19.9"));
        productList.add(product);
        productList.add(product1);
        productList.add(product2);
    }

    /**
     * 初始化组件
     */
    private void init() {
        titleList = findViewById(R.id.category_title_list);
        adapter = new Adapter(productCategory, CategoryActivity.this);
        titleList.setAdapter(adapter);
    }
}