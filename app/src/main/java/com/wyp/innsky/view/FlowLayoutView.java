package com.wyp.innsky.view;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yingping_wang on 2018/11/22.
 */
public class FlowLayoutView extends ViewGroup {
    public FlowLayoutView(Context context) {
        super(context);
    }

    public FlowLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayoutView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0, lineWidth = 0, lineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;
            if (childWidth + lineWidth > widthMeasure) {
                //需要换行
                width = Math.max(childWidth, lineWidth);
                height += lineHeight;
                //换行后，需要重新赋值
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                //不需要换行
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }
            //上面的计算，没有针对最后一行做 height的加操作和width的比对操作，所以需要特殊处理
            if (i == getChildCount() - 1) {
                height += lineHeight;
                width = Math.max(lineWidth, width);
            }
        }
        setMeasuredDimension(widthMeasureMode == MeasureSpec.EXACTLY ? widthMeasure : width,
                heightMeasureMode == MeasureSpec.EXACTLY ? heightMeasure : height);
    }


    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0, top = 0, right = 0, bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(left, top, left + childView.getMeasuredWidth(), top + childView.getMeasuredHeight());
        }

    }

    private static class MarginLayoutParams extends ViewGroup.MarginLayoutParams {

        public MarginLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public MarginLayoutParams(int width, int height) {
            super(width, height);
        }

        public MarginLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
