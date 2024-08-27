package com.example.Chat.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.example.Chat.R;
import com.example.Chat.dataoperation.Database;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private EditText usernameEdit, passwordEdit, surePasswordEdit;
    private TextView cityText;
    private CityPicker cityPicker;
    private Button regButton;
    private RadioGroup sexGroup;
    private String sexStr = "男";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        init();
    }

    /**
     * 界面组件初始化
     */
    private void init() {
        cityText = findViewById(R.id.reg_province);
        cityText.setOnClickListener(this);
        usernameEdit = findViewById(R.id.reg_username);
        passwordEdit = findViewById(R.id.reg_password);
        surePasswordEdit = findViewById(R.id.reg_sure_password);
        regButton = findViewById(R.id.reg_register);
        regButton.setOnClickListener(this);
        sexGroup = findViewById(R.id.sex);
        sexGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_province:
                initCityPicker();
                cityPicker.show();
                break;
            case R.id.reg_register:
                //注册验证方法
                validateRegister();
                break;

        }
    }

    /**
     * 初始化城市选择器
     */
    public void initCityPicker() {
        cityPicker = new CityPicker.Builder(RegisterActivity.this)
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#EFB81C")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("江西省")
                .city("赣州市")
                .district("章贡区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                String province = strings[0];
                String city = strings[1];
                String district = strings[2];
                cityText.setText(String.format("%s %s %s", province, city, district));
            }

            @Override
            public void onCancel() {
            }
        });

    }

    /**
     * 注册验证
     */
    public void validateRegister() {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String surePassword = surePasswordEdit.getText().toString();
        String city = cityText.getText().toString();
        //判断两次密码是否输入一致
        if (password.equals(surePassword)) {
            //这里也可以再进行其它的验证，如是否符合要求等，符合就进行插入数据库操作
            if (!username.equals("") || !password.equals("")) {
                if (!city.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("password", password);
                    bundle.putString("sex", sexStr);
                    bundle.putString("city", city);
                    //本节将使用安卓自身带的SQLite数据库的方式存储注册的信息（之前直接传输显示在界面是的方式）
                    Database database = new Database(RegisterActivity.this);
                    SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
                    insertData(sqLiteDatabase, bundle);
                    Intent intent = new Intent(RegisterActivity.this, IndexActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "请选择地址", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "账号或密码未填写", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //根据用户选择来改变sex的值
        sexStr = checkedId == R.id.reg_man ? "男" : "女";
    }

    /**
     * 插入数据库的值
     *
     * @param sqLiteDatabase
     * @param bundle
     */
    private void insertData(SQLiteDatabase sqLiteDatabase, Bundle bundle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", bundle.getString("username"));
        contentValues.put("password", bundle.getString("password"));
        contentValues.put("sex", bundle.getString("sex"));
        contentValues.put("city", bundle.getString("city"));
        sqLiteDatabase.insert("orange_user", null, contentValues);
        sqLiteDatabase.close();
    }
}
