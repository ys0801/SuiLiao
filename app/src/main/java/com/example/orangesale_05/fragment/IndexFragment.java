package com.example.orangesale_05.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.orangesale_05.R;
import com.example.orangesale_05.adapter.ProductAdapter;
import com.example.orangesale_05.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment implements View.OnClickListener {
    private SearchView searchView;
    private LinearLayout orangeLine, youziLine, juziLine, xiguaLine, liLine, appleLine, lemonLine, mangguoLine;
    private GridView gridView;
    private List<Product> productList;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.xiaoxi, container, false);
        init(view);
        return view;
    }

    /**
     * 初始化组件
     */
    private void init(View view) {

    }


    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化商品数据
     */
    private void initData() {


    }
}
