package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\r\u0010\u0010J\u001f\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0013\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/ViewUtils;", "", "<init>", "()V", "Landroid/view/View;", "view", "Landroid/util/Size;", "measure", "(Landroid/view/View;)Landroid/util/Size;", "", "width", "height", "Lme/x;", "setSize", "(Landroid/view/View;II)V", "size", "(Landroid/view/View;Landroid/util/Size;)V", "setWidth", "(Landroid/view/View;I)V", "setHeight", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewUtils {
    public static final ViewUtils INSTANCE = new ViewUtils();

    private ViewUtils() {
    }

    public static final Size measure(View view) {
        j.e(view, "view");
        view.measure(0, 0);
        return new Size(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public static final void setHeight(View view, int i2) {
        j.e(view, "view");
        setSize(view, view.getLayoutParams().width, i2);
    }

    public static final void setSize(View view, int i2, int i7) {
        j.e(view, "view");
        view.setMinimumWidth(i2);
        view.setMinimumHeight(i7);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(0, 0);
        }
        layoutParams.width = i2;
        layoutParams.height = i7;
        view.setLayoutParams(layoutParams);
    }

    public static final void setWidth(View view, int i2) {
        j.e(view, "view");
        setSize(view, i2, view.getLayoutParams().height);
    }

    public static final void setSize(View view, Size size) {
        j.e(view, "view");
        j.e(size, "size");
        setSize(view, size.getWidth(), size.getHeight());
    }
}
