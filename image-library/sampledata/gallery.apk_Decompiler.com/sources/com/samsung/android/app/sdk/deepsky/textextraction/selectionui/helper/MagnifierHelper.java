package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Magnifier;
import androidx.core.content.res.ResourcesCompat;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.util.VectorUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\u000e\u001a\u00020\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J+\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00182\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0015¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\"\u0010 \u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b \u0010\"\"\u0004\b#\u0010$R\"\u0010%\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b%\u0010!\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010$R\u0018\u0010'\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/MagnifierHelper;", "", "Landroid/content/Context;", "context", "Landroid/view/View;", "teView", "<init>", "(Landroid/content/Context;Landroid/view/View;)V", "", "Landroid/graphics/Point;", "linePoly", "", "scaleFactor", "", "canShowMagnifier", "([Landroid/graphics/Point;F)Z", "", "lineHeight", "Landroid/widget/Magnifier;", "initMagnifier", "(I)Landroid/widget/Magnifier;", "Lme/x;", "setMagnifierZoom", "(IF)V", "Landroid/view/MotionEvent;", "event", "handleTouchEvent", "(Landroid/view/MotionEvent;[Landroid/graphics/Point;F)Z", "dismiss", "()V", "Landroid/content/Context;", "Landroid/view/View;", "isShowing", "Z", "()Z", "setShowing", "(Z)V", "isEnabled", "setEnabled", "magnifier", "Landroid/widget/Magnifier;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MagnifierHelper {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private boolean isEnabled = true;
    private boolean isShowing;
    private Magnifier magnifier;
    private final View teView;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/MagnifierHelper$Companion;", "", "<init>", "()V", "TAG", "", "LINE_RATIO_IN_MAGNIFIER", "", "LINE_TILT_THRESHOLD", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public MagnifierHelper(Context context2, View view) {
        j.e(context2, "context");
        j.e(view, "teView");
        this.context = context2;
        this.teView = view;
    }

    private final boolean canShowMagnifier(Point[] pointArr, float f) {
        if (!this.isEnabled || !this.isShowing || f != 1.0f || VectorUtil.INSTANCE.isTilted(pointArr, 25.0f)) {
            return false;
        }
        return true;
    }

    private final Magnifier initMagnifier(int i2) {
        Magnifier build = new Magnifier.Builder(this.teView).setCornerRadius(this.context.getResources().getDimension(R$dimen.magnifier_corner_radius)).setDefaultSourceToMagnifierOffset(0, this.context.getResources().getDimensionPixelOffset(R$dimen.magnifier_vertical_offset) + (-i2)).setOverlay(ResourcesCompat.getDrawable(this.context.getResources(), R$drawable.magnifer_boundary, (Resources.Theme) null)).build();
        j.d(build, "build(...)");
        return build;
    }

    private final void setMagnifierZoom(int i2, float f) {
        Magnifier magnifier2 = this.magnifier;
        if (magnifier2 != null) {
            magnifier2.setZoom(((((float) magnifier2.getHeight()) / ((float) i2)) * 0.5f) / f);
        }
    }

    public final void dismiss() {
        this.isShowing = false;
        Magnifier magnifier2 = this.magnifier;
        if (magnifier2 != null) {
            magnifier2.dismiss();
        }
        this.magnifier = null;
    }

    public final boolean handleTouchEvent(MotionEvent motionEvent, Point[] pointArr, float f) {
        j.e(motionEvent, "event");
        j.e(pointArr, "linePoly");
        if (!canShowMagnifier(pointArr, f)) {
            dismiss();
            return false;
        } else if (motionEvent.getAction() != 2) {
            return false;
        } else {
            int calcDistanceFromPointToPoint = VectorUtil.INSTANCE.calcDistanceFromPointToPoint(pointArr[0], pointArr[3]);
            if (this.magnifier == null) {
                this.magnifier = initMagnifier(calcDistanceFromPointToPoint);
            }
            setMagnifierZoom(calcDistanceFromPointToPoint, f);
            int[] iArr = new int[2];
            this.teView.getLocationOnScreen(iArr);
            float rawX = ((motionEvent.getRawX() - ((float) iArr[0])) / f) + ((float) this.context.getResources().getDimensionPixelOffset(R$dimen.magnifier_horizontal_offset));
            float f5 = ((float) ((pointArr[0].y + pointArr[2].y) / 2)) * f;
            Magnifier magnifier2 = this.magnifier;
            if (magnifier2 == null) {
                return true;
            }
            magnifier2.show(rawX, f5);
            return true;
        }
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public final void setShowing(boolean z) {
        this.isShowing = z;
    }
}
