package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Flow extends VirtualLayout {
    private androidx.constraintlayout.solver.widgets.Flow mFlow;

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mFlow = new androidx.constraintlayout.solver.widgets.Flow();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R$styleable.ConstraintLayout_Layout_android_orientation) {
                    this.mFlow.setOrientation(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_padding) {
                    this.mFlow.setPadding(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingStart) {
                    this.mFlow.setPaddingStart(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.mFlow.setPaddingEnd(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.mFlow.setPaddingLeft(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.mFlow.setPaddingTop(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.mFlow.setPaddingRight(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.mFlow.setPaddingBottom(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.mFlow.setWrapMode(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.mFlow.setHorizontalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.mFlow.setVerticalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.mFlow.setFirstHorizontalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.mFlow.setLastHorizontalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.mFlow.setFirstVerticalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.mFlow.setLastVerticalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.mFlow.setHorizontalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.mFlow.setFirstHorizontalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.mFlow.setLastHorizontalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.mFlow.setFirstVerticalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.mFlow.setLastVerticalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.mFlow.setVerticalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.mFlow.setHorizontalAlign(obtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.mFlow.setVerticalAlign(obtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.mFlow.setHorizontalGap(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.mFlow.setVerticalGap(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.mFlow.setMaxElementsWrap(obtainStyledAttributes.getInt(index, -1));
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mHelperWidget = this.mFlow;
        validateParams();
    }

    public void onMeasure(int i2, int i7) {
        onMeasure(this.mFlow, i2, i7);
    }

    public void resolveRtl(ConstraintWidget constraintWidget, boolean z) {
        this.mFlow.applyRtl(z);
    }

    public void setFirstHorizontalBias(float f) {
        this.mFlow.setFirstHorizontalBias(f);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i2) {
        this.mFlow.setFirstHorizontalStyle(i2);
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.mFlow.setFirstVerticalBias(f);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i2) {
        this.mFlow.setFirstVerticalStyle(i2);
        requestLayout();
    }

    public void setHorizontalAlign(int i2) {
        this.mFlow.setHorizontalAlign(i2);
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.mFlow.setHorizontalBias(f);
        requestLayout();
    }

    public void setHorizontalGap(int i2) {
        this.mFlow.setHorizontalGap(i2);
        requestLayout();
    }

    public void setHorizontalStyle(int i2) {
        this.mFlow.setHorizontalStyle(i2);
        requestLayout();
    }

    public void setMaxElementsWrap(int i2) {
        this.mFlow.setMaxElementsWrap(i2);
        requestLayout();
    }

    public void setOrientation(int i2) {
        this.mFlow.setOrientation(i2);
        requestLayout();
    }

    public void setPadding(int i2) {
        this.mFlow.setPadding(i2);
        requestLayout();
    }

    public void setPaddingBottom(int i2) {
        this.mFlow.setPaddingBottom(i2);
        requestLayout();
    }

    public void setPaddingLeft(int i2) {
        this.mFlow.setPaddingLeft(i2);
        requestLayout();
    }

    public void setPaddingRight(int i2) {
        this.mFlow.setPaddingRight(i2);
        requestLayout();
    }

    public void setPaddingTop(int i2) {
        this.mFlow.setPaddingTop(i2);
        requestLayout();
    }

    public void setVerticalAlign(int i2) {
        this.mFlow.setVerticalAlign(i2);
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.mFlow.setVerticalBias(f);
        requestLayout();
    }

    public void setVerticalGap(int i2) {
        this.mFlow.setVerticalGap(i2);
        requestLayout();
    }

    public void setVerticalStyle(int i2) {
        this.mFlow.setVerticalStyle(i2);
        requestLayout();
    }

    public void setWrapMode(int i2) {
        this.mFlow.setWrapMode(i2);
        requestLayout();
    }

    public void onMeasure(androidx.constraintlayout.solver.widgets.VirtualLayout virtualLayout, int i2, int i7) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i7);
        if (virtualLayout != null) {
            virtualLayout.measure(mode, size, mode2, size2);
            setMeasuredDimension(virtualLayout.getMeasuredWidth(), virtualLayout.getMeasuredHeight());
            return;
        }
        setMeasuredDimension(0, 0);
    }
}
