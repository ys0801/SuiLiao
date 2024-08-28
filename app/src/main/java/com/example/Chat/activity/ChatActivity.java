package com.example.Chat.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import Chat.R;

public class ChatActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_chat);
    }
}
