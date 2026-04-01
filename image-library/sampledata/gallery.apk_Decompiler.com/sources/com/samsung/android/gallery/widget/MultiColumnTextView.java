package com.samsung.android.gallery.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.google.android.flexbox.FlexboxLayout;
import db.a;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import q1.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiColumnTextView extends FlexboxLayout {
    public MultiColumnTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    public void lambda$setData$0(AtomicBoolean atomicBoolean, LayoutInflater layoutInflater, int i2, String str) {
        if ("\n".equals(str)) {
            atomicBoolean.set(true);
        } else if (!TextUtils.isEmpty(str)) {
            TextView textView = (TextView) layoutInflater.inflate(i2, this, false);
            if (atomicBoolean.getAndSet(false)) {
                ((g) textView.getLayoutParams()).m = true;
            }
            textView.setText(str);
            addView(textView);
        }
    }

    private void notifyDataChanged() {
        requestLayout();
        invalidate();
    }

    public void setData(List<String> list) {
        setData(list, R$layout.multi_column_textview_single_column);
    }

    public void setData(List<String> list, int i2) {
        removeAllViewsInLayout();
        if (list == null || list.isEmpty()) {
            setVisibility(8);
        } else {
            list.forEach(new a(this, new AtomicBoolean(false), LayoutInflater.from(getContext()), i2));
            setVisibility(0);
        }
        notifyDataChanged();
    }
}
