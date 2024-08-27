package com.example.Chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import Chat.R;

public class EditInfoActivity extends Activity {

    private EditText editText;
    private String infoType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        editText = findViewById(R.id.edit_text);

        infoType = getIntent().getStringExtra("info_type");
        TextView title = findViewById(R.id.title_text);
        title.setText("编辑" + infoType);

        // 设置保存按钮的点击事件
        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedInfo = editText.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updated_info", updatedInfo);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
