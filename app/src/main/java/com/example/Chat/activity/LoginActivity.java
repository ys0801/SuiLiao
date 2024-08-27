package com.example.Chat.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Chat.R;
import com.example.Chat.dataoperation.Database;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button registerButton, loginButton;
    private EditText usernameText, paswdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                //注册时，我们引入了数据库，登录这里可以通过数据库进行验证，验证跳转到首页，不通过进行提示
                if (validateLogin()) {
                    Intent intent1 = new Intent(LoginActivity.this, IndexActivity.class);
                    Bundle bundle = new Bundle();
                    Database database = new Database(LoginActivity.this);
                    bundle.putString("username", usernameText.getText().toString());
                    bundle = database.queryUserInfo(database.getReadableDatabase(), bundle);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //界面组件初始化
    private void init() {
        usernameText = findViewById(R.id.username);
        paswdEdit = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(this);
        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(this);
    }

    /**
     * 登录验证
     *
     * @return
     */
    private boolean validateLogin() {
        String username = usernameText.getText().toString();
        String password = paswdEdit.getText().toString();
        Database database = new Database(LoginActivity.this);
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from orange_user where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
}
