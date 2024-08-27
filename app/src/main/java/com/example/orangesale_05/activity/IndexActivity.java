package com.example.orangesale_05.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.orangesale_05.R;
import com.example.orangesale_05.fragment.Contact;
import com.example.orangesale_05.fragment.Document;
import com.example.orangesale_05.fragment.Message;
import com.example.orangesale_05.fragment.PearsonFragment;

public class IndexActivity extends Activity implements View.OnClickListener {
    private Message message;
    private Document document;
    private Contact contact;
    private PearsonFragment pearsonFragment;
    private LinearLayout messageLine, documentLine, contactLine, pearsonLine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initMessageFragment();
    }

    /**
     * 组件初始化
     */
    private void init() {
        messageLine = findViewById(R.id.content_message);
        messageLine.setOnClickListener(this);
        documentLine = findViewById(R.id.content_document);
        documentLine.setOnClickListener(this);
        contactLine = findViewById(R.id.content_contact);
        contactLine.setOnClickListener(this);
        pearsonLine = findViewById(R.id.content_pearson);
        pearsonLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_message:
                initMessageFragment();
                break;
            case R.id.content_document:
                initDocumentFragment();
                break;
            case R.id.content_contact:
                initContactFragment();
                break;
            case R.id.content_pearson:
                initpearsonFragment();
                break;
        }
    }

    /**
     * 初始化消息页面Message
     */
    private void initMessageFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (message == null) {
            message = new Message();
        }
        transaction.replace(R.id.main_content, message);
        transaction.commit();
    }

    /**
     * 初始化文件Document
     */
    private void initDocumentFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (document == null) {
            document = new Document();
        }
        transaction.replace(R.id.main_content, document);
        transaction.commit();
    }

    /**
     * 初始化联系人Contact
     */
    private void initContactFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (contact == null) {
            contact = new Contact();
        }
        transaction.replace(R.id.main_content, contact);
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
