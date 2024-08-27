package com.example.Chat.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import Chat.R;
import com.example.Chat.activity.EditInfoActivity;  // 这是你新建的编辑信息的Activity

public class PearsonFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_CODE_EDIT_USERNAME = 1;
    private static final int REQUEST_CODE_EDIT_SEX = 2;
    private static final int REQUEST_CODE_EDIT_CITY = 3;

    private ImageView userIconImage;
    private TextView usernameText, userSexText, userCityText;
    private LinearLayout usernameLine, userSexline, userCityLine, userSettingLine, userGeneralLine;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_user, container, false);
        init(view);
        return view;
    }

    /**
     * 组件初始化
     */
    private void init(View view) {
        userIconImage = view.findViewById(R.id.user_icon);
        usernameText = view.findViewById(R.id.user_username);
        userSexText = view.findViewById(R.id.user_sex);
        userCityText = view.findViewById(R.id.user_city);
        usernameLine = view.findViewById(R.id.user_username_line);
        userSexline = view.findViewById(R.id.user_sex_line);
        userCityLine = view.findViewById(R.id.user_city_line);
        userSettingLine = view.findViewById(R.id.user_setting);
        userGeneralLine = view.findViewById(R.id.user_general);

        // 设置点击事件监听器
        userIconImage.setOnClickListener(this);
        usernameLine.setOnClickListener(this);
        userSexline.setOnClickListener(this);
        userCityLine.setOnClickListener(this);
        userSettingLine.setOnClickListener(this);
        userGeneralLine.setOnClickListener(this);

        // 设置组件数据
        setData();
    }

    /**
     * 组件赋值
     */
    private void setData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            usernameText.setText(String.format("用户名：%s", bundle.getString("username", "未设置")));
            userSexText.setText(String.format("性别：%s", bundle.getString("sex", "未设置")));
            userCityText.setText(String.format("城市：%s", bundle.getString("city", "未设置")));
        } else {
            Toast.makeText(getActivity(), "未接收到用户数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.user_icon:
                // 启动图库选择图片
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 0);
                break;
            case R.id.user_username_line:
                intent = new Intent(getActivity(), EditInfoActivity.class);
                intent.putExtra("info_type", "username");
                startActivityForResult(intent, REQUEST_CODE_EDIT_USERNAME);
                break;
            case R.id.user_sex_line:
                intent = new Intent(getActivity(), EditInfoActivity.class);
                intent.putExtra("info_type", "sex");
                startActivityForResult(intent, REQUEST_CODE_EDIT_SEX);
                break;
            case R.id.user_city_line:
                intent = new Intent(getActivity(), EditInfoActivity.class);
                intent.putExtra("info_type", "city");
                startActivityForResult(intent, REQUEST_CODE_EDIT_CITY);
                break;
            // 处理其他控件的点击事件
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && data != null) {
            switch (requestCode) {
                case 0: // 处理头像更新
                    try {
                        Uri selectedImage = data.getData();
                        // 使用 Glide 加载圆形图像
                        Glide.with(this)
                                .load(selectedImage)
                                .apply(RequestOptions.circleCropTransform()) // 设置圆形裁剪
                                .into(userIconImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case REQUEST_CODE_EDIT_USERNAME:
                    String updatedUsername = data.getStringExtra("updated_info");
                    usernameText.setText(String.format("用户名：%s", updatedUsername));
                    break;
                case REQUEST_CODE_EDIT_SEX:
                    String updatedSex = data.getStringExtra("updated_info");
                    userSexText.setText(String.format("性别：%s", updatedSex));
                    break;
                case REQUEST_CODE_EDIT_CITY:
                    String updatedCity = data.getStringExtra("updated_info");
                    userCityText.setText(String.format("城市：%s", updatedCity));
                    break;
            }
        }
    }

}
