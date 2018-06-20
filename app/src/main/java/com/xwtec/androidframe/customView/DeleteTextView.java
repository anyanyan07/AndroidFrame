package com.xwtec.androidframe.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.xwtec.androidframe.R;

/**
 * Created by ayy on 2018/6/20.
 * Describe:中间带删除线的TextView
 */

public class DeleteTextView extends TextView {
    private Paint paint;
    private int deleteLineColor;

    public DeleteTextView(Context context) {
        super(context);
        init(context, null);
    }

    public DeleteTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DeleteTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DeleteTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(ConvertUtils.dp2px(1f));
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DeleteTextView);
        deleteLineColor = typedArray.getColor(R.styleable.DeleteTextView_deleteLineColor, Color.GRAY);
        typedArray.recycle();
        paint.setColor(deleteLineColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画删除线
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        canvas.drawLine(0, measuredHeight / 2, measuredWidth, measuredHeight / 2, paint);
    }
}
