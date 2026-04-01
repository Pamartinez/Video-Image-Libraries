package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PreferenceScreen extends PreferenceGroup {
    private boolean mShouldUseGeneratedIds = true;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.preferenceScreenStyle, 16842891));
    }

    public boolean isOnSameScreenAsChildren() {
        return false;
    }

    public void onClick() {
        PreferenceManager.OnNavigateToScreenListener onNavigateToScreenListener;
        if (getIntent() == null && getFragment() == null && getPreferenceCount() != 0 && (onNavigateToScreenListener = getPreferenceManager().getOnNavigateToScreenListener()) != null) {
            onNavigateToScreenListener.onNavigateToScreen(this);
        }
    }

    public boolean shouldUseGeneratedIds() {
        return this.mShouldUseGeneratedIds;
    }
}
