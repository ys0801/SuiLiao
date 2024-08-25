package com.example.orangesale_05.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.orangesale_05.R;

public class UserActivity extends Activity implements View.OnClickListener {
    private ImageView userIconImage;
    private TextView usernameText, userSexText, userCityText;
    private LinearLayout usernameLine, userSexline, userCityLine, userSettingLine, userGeneralLine, userSearchProductLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user);
        init();
    }

    /**
     * 组件初始化
     */
    private void init() {
        userIconImage = findViewById(R.id.user_icon);
        usernameText = findViewById(R.id.user_username);
        userSexText = findViewById(R.id.user_sex);
        userCityText = findViewById(R.id.user_city);
        usernameLine = findViewById(R.id.user_username_line);
        userSexline = findViewById(R.id.user_sex_line);
        userCityLine = findViewById(R.id.user_city_line);
        userSettingLine = findViewById(R.id.user_setting);
        userGeneralLine = findViewById(R.id.user_general);
        userSearchProductLine = findViewById(R.id.user_searchProduct);
        userSearchProductLine.setOnClickListener(this);
        setData();
    }

    /**
     * 组件赋值
     */
    private void setData() {
        Intent intent = UserActivity.this.getIntent();
        Bundle bundle = intent.getExtras();
        usernameText.setText(String.format("用户名：%s", bundle.getString("username")));
        userSexText.setText(String.format("性别：%s", bundle.getString("sex")));
        userCityText.setText(String.format("城市：%s", bundle.getString("city")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_searchProduct:
                Intent intent1 = new Intent(UserActivity.this, CategoryActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
