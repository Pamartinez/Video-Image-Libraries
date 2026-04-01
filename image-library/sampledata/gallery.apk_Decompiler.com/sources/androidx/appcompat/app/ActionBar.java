package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ActionMode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActionBar {
    public boolean closeOptionsMenu() {
        return false;
    }

    public abstract boolean collapseActionView();

    public abstract void dispatchMenuVisibilityChanged(boolean z);

    public abstract int getDisplayOptions();

    public abstract Context getThemedContext();

    public boolean invalidateOptionsMenu() {
        return false;
    }

    public abstract boolean onKeyShortcut(int i2, KeyEvent keyEvent);

    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean openOptionsMenu() {
        return false;
    }

    public abstract void setDefaultDisplayHomeAsUpEnabled(boolean z);

    public abstract void setDisplayHomeAsUpEnabled(boolean z);

    public abstract void setDisplayShowHomeEnabled(boolean z);

    public abstract void setShowHideAnimationEnabled(boolean z);

    public abstract void setTitle(int i2);

    public abstract void setTitle(CharSequence charSequence);

    public abstract void setWindowTitle(CharSequence charSequence);

    public ActionMode startActionMode(ActionMode.Callback callback) {
        return null;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity = 8388627;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBarLayout);
            this.gravity = obtainStyledAttributes.getInt(R$styleable.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public void onDestroy() {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }
}
