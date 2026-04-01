package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CheckedTextViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AppCompatCheckedTextViewHelper {
    private ColorStateList mCheckMarkTintList = null;
    private PorterDuff.Mode mCheckMarkTintMode = null;
    private boolean mHasCheckMarkTint = false;
    private boolean mHasCheckMarkTintMode = false;
    private boolean mSkipNextApply;
    private final CheckedTextView mView;

    public AppCompatCheckedTextViewHelper(CheckedTextView checkedTextView) {
        this.mView = checkedTextView;
    }

    public void applyCheckMarkTint() {
        Drawable checkMarkDrawable = CheckedTextViewCompat.getCheckMarkDrawable(this.mView);
        if (checkMarkDrawable == null) {
            return;
        }
        if (this.mHasCheckMarkTint || this.mHasCheckMarkTintMode) {
            Drawable mutate = DrawableCompat.wrap(checkMarkDrawable).mutate();
            if (this.mHasCheckMarkTint) {
                DrawableCompat.setTintList(mutate, this.mCheckMarkTintList);
            }
            if (this.mHasCheckMarkTintMode) {
                DrawableCompat.setTintMode(mutate, this.mCheckMarkTintMode);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.mView.getDrawableState());
            }
            this.mView.setCheckMarkDrawable(mutate);
        }
    }

    public ColorStateList getSupportCheckMarkTintList() {
        return this.mCheckMarkTintList;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        return this.mCheckMarkTintMode;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|(2:6|7)|10|11|(1:15)|16|(1:18)|19|(1:21)|22|23) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CheckedTextView r0 = r10.mView
            android.content.Context r0 = r0.getContext()
            int[] r3 = androidx.appcompat.R$styleable.CheckedTextView
            r8 = 0
            androidx.appcompat.widget.TintTypedArray r9 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r11, r3, r12, r8)
            android.widget.CheckedTextView r1 = r10.mView
            android.content.Context r2 = r1.getContext()
            android.content.res.TypedArray r5 = r9.getWrappedTypeArray()
            r7 = 0
            r4 = r11
            r6 = r12
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r1, r2, r3, r4, r5, r6, r7)
            int r11 = androidx.appcompat.R$styleable.CheckedTextView_checkMarkCompat     // Catch:{ all -> 0x0039 }
            boolean r12 = r9.hasValue(r11)     // Catch:{ all -> 0x0039 }
            if (r12 == 0) goto L_0x003c
            int r11 = r9.getResourceId(r11, r8)     // Catch:{ all -> 0x0039 }
            if (r11 == 0) goto L_0x003c
            android.widget.CheckedTextView r12 = r10.mView     // Catch:{ NotFoundException -> 0x003c }
            android.content.Context r0 = r12.getContext()     // Catch:{ NotFoundException -> 0x003c }
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r11)     // Catch:{ NotFoundException -> 0x003c }
            r12.setCheckMarkDrawable(r11)     // Catch:{ NotFoundException -> 0x003c }
            goto L_0x0057
        L_0x0039:
            r0 = move-exception
            r10 = r0
            goto L_0x0083
        L_0x003c:
            int r11 = androidx.appcompat.R$styleable.CheckedTextView_android_checkMark     // Catch:{ all -> 0x0039 }
            boolean r12 = r9.hasValue(r11)     // Catch:{ all -> 0x0039 }
            if (r12 == 0) goto L_0x0057
            int r11 = r9.getResourceId(r11, r8)     // Catch:{ all -> 0x0039 }
            if (r11 == 0) goto L_0x0057
            android.widget.CheckedTextView r12 = r10.mView     // Catch:{ all -> 0x0039 }
            android.content.Context r0 = r12.getContext()     // Catch:{ all -> 0x0039 }
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r11)     // Catch:{ all -> 0x0039 }
            r12.setCheckMarkDrawable(r11)     // Catch:{ all -> 0x0039 }
        L_0x0057:
            int r11 = androidx.appcompat.R$styleable.CheckedTextView_checkMarkTint     // Catch:{ all -> 0x0039 }
            boolean r12 = r9.hasValue(r11)     // Catch:{ all -> 0x0039 }
            if (r12 == 0) goto L_0x0068
            android.widget.CheckedTextView r12 = r10.mView     // Catch:{ all -> 0x0039 }
            android.content.res.ColorStateList r11 = r9.getColorStateList(r11)     // Catch:{ all -> 0x0039 }
            androidx.core.widget.CheckedTextViewCompat.setCheckMarkTintList(r12, r11)     // Catch:{ all -> 0x0039 }
        L_0x0068:
            int r11 = androidx.appcompat.R$styleable.CheckedTextView_checkMarkTintMode     // Catch:{ all -> 0x0039 }
            boolean r12 = r9.hasValue(r11)     // Catch:{ all -> 0x0039 }
            if (r12 == 0) goto L_0x007f
            android.widget.CheckedTextView r10 = r10.mView     // Catch:{ all -> 0x0039 }
            r12 = -1
            int r11 = r9.getInt(r11, r12)     // Catch:{ all -> 0x0039 }
            r12 = 0
            android.graphics.PorterDuff$Mode r11 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r11, r12)     // Catch:{ all -> 0x0039 }
            androidx.core.widget.CheckedTextViewCompat.setCheckMarkTintMode(r10, r11)     // Catch:{ all -> 0x0039 }
        L_0x007f:
            r9.recycle()
            return
        L_0x0083:
            r9.recycle()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCheckedTextViewHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    public void onSetCheckMarkDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyCheckMarkTint();
    }

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        this.mCheckMarkTintList = colorStateList;
        this.mHasCheckMarkTint = true;
        applyCheckMarkTint();
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        this.mCheckMarkTintMode = mode;
        this.mHasCheckMarkTintMode = true;
        applyCheckMarkTint();
    }
}
