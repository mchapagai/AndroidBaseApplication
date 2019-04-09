package com.example.mchapagai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.mchapagai.R;

public class MaterialView extends View {

    private String str; // TODO: use a default from R.string...
    private int colorId = Color.RED; // TODO: use a default from R.color...
    private float dimension = 0; // TODO: use a default from R.dimen...
    private Drawable drawableId;
    private TextPaint textpaint;
    private float textWidth;
    private float textHeight;

    public MaterialView(Context context) {
        super(context);
        init(null, 0);
    }

    public MaterialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MaterialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MaterialView, defStyle, 0);

        str = a.getString(
                R.styleable.MaterialView_str);
        colorId = a.getColor(
                R.styleable.MaterialView_colorId,
                colorId);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        dimension = a.getDimension(
                R.styleable.MaterialView_dimension,
                dimension);

        if (a.hasValue(R.styleable.MaterialView_drawableId)) {
            drawableId = a.getDrawable(
                    R.styleable.MaterialView_drawableId);
            drawableId.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        textpaint = new TextPaint();
        textpaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textpaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        textpaint.setTextSize(dimension);
        textpaint.setColor(colorId);
        textWidth = textpaint.measureText(str);

        Paint.FontMetrics fontMetrics = textpaint.getFontMetrics();
        textHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(str,
                paddingLeft + (contentWidth - textWidth) / 2,
                paddingTop + (contentHeight + textHeight) / 2,
                textpaint);

        // Draw the example drawable on top of the text.
        if (drawableId != null) {
            drawableId.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            drawableId.draw(canvas);
        }
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getStr() {
        return str;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param str The example string attribute value to use.
     */
    public void setStr(String str) {
        this.str = str;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getColorId() {
        return colorId;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param color The example color attribute value to use.
     */
    public void setColorId(int color) {
        colorId = color;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getDimension() {
        return dimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param dimension The example dimension attribute value to use.
     */
    public void setDimension(float dimension) {
        this.dimension = dimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getDrawableId() {
        return drawableId;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param drawable The example drawable attribute value to use.
     */
    public void setDrawableId(Drawable drawable) {
        drawableId = drawable;
    }
}
