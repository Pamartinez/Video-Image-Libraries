package androidx.core.view.insets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.R$id;
import androidx.core.view.insets.Protection;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProtectionLayout extends FrameLayout {
    private static final Object PROTECTION_VIEW = new Object();
    private ProtectionGroup mGroup;
    private final List<Protection> mProtections;

    public ProtectionLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addProtectionView(android.content.Context r7, int r8, androidx.core.view.insets.Protection r9) {
        /*
            r6 = this;
            androidx.core.view.insets.Protection$Attributes r0 = r9.getAttributes()
            int r1 = r9.getSide()
            r2 = 1
            r3 = 4
            r4 = -1
            if (r1 == r2) goto L_0x0045
            r2 = 2
            if (r1 == r2) goto L_0x003e
            if (r1 == r3) goto L_0x0035
            r2 = 8
            if (r1 != r2) goto L_0x001d
            int r9 = r0.getHeight()
            r1 = 80
            goto L_0x004b
        L_0x001d:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Unexpected side: "
            r7.<init>(r8)
            int r8 = r9.getSide()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0035:
            int r9 = r0.getWidth()
            r1 = 5
        L_0x003a:
            r5 = r4
            r4 = r9
            r9 = r5
            goto L_0x004b
        L_0x003e:
            int r9 = r0.getHeight()
            r1 = 48
            goto L_0x004b
        L_0x0045:
            int r9 = r0.getWidth()
            r1 = 3
            goto L_0x003a
        L_0x004b:
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r2.<init>(r4, r9, r1)
            androidx.core.graphics.Insets r9 = r0.getMargin()
            int r1 = r9.left
            r2.leftMargin = r1
            int r1 = r9.top
            r2.topMargin = r1
            int r1 = r9.right
            r2.rightMargin = r1
            int r9 = r9.bottom
            r2.bottomMargin = r9
            android.view.View r9 = new android.view.View
            r9.<init>(r7)
            java.lang.Object r7 = PROTECTION_VIEW
            r9.setTag(r7)
            float r7 = r0.getTranslationX()
            r9.setTranslationX(r7)
            float r7 = r0.getTranslationY()
            r9.setTranslationY(r7)
            float r7 = r0.getAlpha()
            r9.setAlpha(r7)
            boolean r7 = r0.isVisible()
            if (r7 == 0) goto L_0x008a
            r3 = 0
        L_0x008a:
            r9.setVisibility(r3)
            android.graphics.drawable.Drawable r7 = r0.getDrawable()
            r9.setBackground(r7)
            androidx.core.view.insets.ProtectionLayout$1 r7 = new androidx.core.view.insets.ProtectionLayout$1
            r7.<init>(r2, r9)
            r0.setCallback(r7)
            r6.addView(r9, r8, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.insets.ProtectionLayout.addProtectionView(android.content.Context, int, androidx.core.view.insets.Protection):void");
    }

    private void addProtectionViews() {
        if (!this.mProtections.isEmpty()) {
            this.mGroup = new ProtectionGroup(getOrInstallSystemBarStateMonitor(), this.mProtections);
            int childCount = getChildCount();
            int size = this.mGroup.size();
            for (int i2 = 0; i2 < size; i2++) {
                addProtectionView(getContext(), i2 + childCount, this.mGroup.getProtection(i2));
            }
        }
    }

    private SystemBarStateMonitor getOrInstallSystemBarStateMonitor() {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        int i2 = R$id.tag_system_bar_state_monitor;
        Object tag = viewGroup.getTag(i2);
        if (tag instanceof SystemBarStateMonitor) {
            return (SystemBarStateMonitor) tag;
        }
        SystemBarStateMonitor systemBarStateMonitor = new SystemBarStateMonitor(viewGroup);
        viewGroup.setTag(i2, systemBarStateMonitor);
        return systemBarStateMonitor;
    }

    private void maybeUninstallSystemBarStateMonitor() {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        int i2 = R$id.tag_system_bar_state_monitor;
        Object tag = viewGroup.getTag(i2);
        if (tag instanceof SystemBarStateMonitor) {
            SystemBarStateMonitor systemBarStateMonitor = (SystemBarStateMonitor) tag;
            if (!systemBarStateMonitor.hasCallback()) {
                systemBarStateMonitor.detachFromWindow();
                viewGroup.setTag(i2, (Object) null);
            }
        }
    }

    private void removeProtectionViews() {
        if (this.mGroup != null) {
            removeViews(getChildCount() - this.mGroup.size(), this.mGroup.size());
            int size = this.mGroup.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mGroup.getProtection(i2).getAttributes().setCallback((Protection.Attributes.Callback) null);
            }
            this.mGroup.dispose();
            this.mGroup = null;
        }
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        int i7;
        if (!(view == null || view.getTag() == PROTECTION_VIEW)) {
            ProtectionGroup protectionGroup = this.mGroup;
            if (protectionGroup != null) {
                i7 = protectionGroup.size();
            } else {
                i7 = 0;
            }
            int childCount = getChildCount() - i7;
            if (i2 > childCount || i2 < 0) {
                i2 = childCount;
            }
        }
        super.addView(view, i2, layoutParams);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mGroup != null) {
            removeProtectionViews();
        }
        addProtectionViews();
        requestApplyInsets();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeProtectionViews();
        maybeUninstallSystemBarStateMonitor();
    }

    public void setProtections(List<Protection> list) {
        this.mProtections.clear();
        this.mProtections.addAll(list);
        if (isAttachedToWindow()) {
            removeProtectionViews();
            addProtectionViews();
            requestApplyInsets();
        }
    }

    public ProtectionLayout(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ProtectionLayout(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mProtections = new ArrayList();
    }
}
