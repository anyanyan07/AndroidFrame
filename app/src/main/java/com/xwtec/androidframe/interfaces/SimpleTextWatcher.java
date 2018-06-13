package com.xwtec.androidframe.interfaces;

import android.text.TextWatcher;

/**
 * Created by Administrator on 2018/5/22.
 */

public abstract class SimpleTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
