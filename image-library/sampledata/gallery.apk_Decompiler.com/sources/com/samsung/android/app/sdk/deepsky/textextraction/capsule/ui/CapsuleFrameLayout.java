package com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.view.ViewGroupKt;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleFrameLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "isTouchStartFromOutside", "", "isTouchPointFromOutside", "rawX", "", "rawY", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleFrameLayout extends FrameLayout {
    private boolean isTouchStartFromOutside;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CapsuleFrameLayout(Context context) {
        this(context, (AttributeSet) null, 2, (e) null);
        j.e(context, "context");
    }

    private final boolean isTouchPointFromOutside(int i2, int i7) {
        boolean z;
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.capsule_parent);
        j.b(linearLayout);
        Iterator it = ViewGroupKt.getChildren(linearLayout).iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            View view = (View) it.next();
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i8 = iArr[0];
            if (new Rect(i8, iArr[1], view.getWidth() + i8, view.getHeight() + iArr[1]).contains(i2, i7)) {
                z = true;
                break;
            }
        }
        return !z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        j.e(motionEvent, "ev");
        if (motionEvent.getActionMasked() == 0) {
            this.isTouchStartFromOutside = isTouchPointFromOutside((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
        return this.isTouchStartFromOutside;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CapsuleFrameLayout(Context context, AttributeSet attributeSet, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CapsuleFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
    }
}
