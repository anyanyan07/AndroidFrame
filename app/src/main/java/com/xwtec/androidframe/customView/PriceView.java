package com.xwtec.androidframe.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwtec.androidframe.R;

/**
 * Created by ayy on 2018/6/20.
 * Describe:价格自定义View
 */

public class PriceView extends LinearLayout {
    private String price;
    private int integerPartSize;
    private int decimalPartSize;
    private TextView tvIntegerPart;
    private TextView tvDecimalPart;

    public PriceView(Context context) {
        super(context);
        init(context, null);
    }

    public PriceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PriceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PriceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        View contentView = inflate(context, R.layout.price_view_layout, this);
        tvIntegerPart = contentView.findViewById(R.id.tv_integer_part);
        tvDecimalPart = contentView.findViewById(R.id.tv_decimal_part);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PriceView);
        integerPartSize = typedArray.getInt(R.styleable.PriceView_integerPartSize, 13);
        decimalPartSize = typedArray.getInt(R.styleable.PriceView_decimalPartSize, 12);
        typedArray.recycle();
        tvIntegerPart.setTextSize(integerPartSize);
        tvDecimalPart.setTextSize(decimalPartSize);
    }

    private void showPrice() {
        if (TextUtils.isEmpty(price)) {
            return;
        }
        String[] split = price.split("\\.");
        if (split.length >= 1) {
            tvIntegerPart.setText("￥" + split[0]);
        }
        if (split.length >= 2) {
            tvDecimalPart.setText("." + split[1]);
        }
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        showPrice();
    }
}
