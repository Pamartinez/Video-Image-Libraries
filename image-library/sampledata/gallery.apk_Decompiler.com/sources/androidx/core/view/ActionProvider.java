package androidx.core.view;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActionProvider {
    private final Context mContext;
    private SubUiVisibilityListener mSubUiVisibilityListener;
    private VisibilityListener mVisibilityListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SubUiVisibilityListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface VisibilityListener {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public ActionProvider(Context context) {
        this.mContext = context;
    }

    public abstract boolean hasSubMenu();

    public abstract boolean isVisible();

    public abstract View onCreateActionView(MenuItem menuItem);

    public abstract boolean onPerformDefaultAction();

    public abstract void onPrepareSubMenu(SubMenu subMenu);

    public abstract boolean overridesItemVisibility();

    public void reset() {
        this.mVisibilityListener = null;
        this.mSubUiVisibilityListener = null;
    }

    public void setSubUiVisibilityListener(SubUiVisibilityListener subUiVisibilityListener) {
        this.mSubUiVisibilityListener = subUiVisibilityListener;
    }

    public abstract void setVisibilityListener(VisibilityListener visibilityListener);
}
