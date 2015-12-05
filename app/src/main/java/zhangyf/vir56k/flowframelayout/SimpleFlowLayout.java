package zhangyf.vir56k.flowframelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * name: android 简单的流布局自定义view
 * 作者：张云飞
 * 特点：可以不断添加多个子view，计算位置，自动换行。
 * 适用： 热门标签
 * Created by zhangyunfei on 15/12/4.
 */
public class SimpleFlowLayout extends ViewGroup {
    public SimpleFlowLayout(Context context) {
        super(context);
    }

    public SimpleFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMax = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMax = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int widthNeed = 0;
        int heightNeed = 0;
        int x = 0;
        int y = 0;
        int currentLineHeight = 0;
        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            child.measure(widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();//获得子view的 外边距
            //测算子view宽度，本行这句代码有问题，不能计算子view的自动换行 int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            //使用viewGroup的measureChildWithMargins测算宽度，在这个方法里处理了 LayoutParams的match_parent等方式的处理
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;

            if (x + childWidth > widthMax) {//换行处理，本行高度和x轴都清零，y轴下移（加上上次的行高）
                y += currentLineHeight;
                currentLineHeight = 0;
                x = 0;
            }
            x += childWidth;
            currentLineHeight = Math.max(currentLineHeight, childHeight);

            widthNeed = Math.max(widthNeed, x);//加入了这个 子view后，留下最大宽度
            heightNeed = Math.max(heightNeed, y + currentLineHeight);//对比上次的，留下最大的高度
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthMax : widthNeed,
                heightMode == MeasureSpec.EXACTLY ? heightMax : heightNeed);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int widthMax = getWidth();
        int x, y;
        x = 0;
        y = 0;
        View child;
        int left = 0;
        int top = 0;
        int currentLineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (x + childWidth > widthMax) {//换行处理
                y += currentLineHeight;
                x = 0;
                currentLineHeight = 0;
            }
            left = x + lp.leftMargin;
            top = y + lp.topMargin;
            //定位子view的位置
            child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());

            x += childWidth;
            currentLineHeight = Math.max(currentLineHeight, childHeight);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
