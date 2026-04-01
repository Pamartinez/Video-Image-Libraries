package androidx.appcompat.view;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionBarPolicy {
    private Context mContext;

    private ActionBarPolicy(Context context) {
        this.mContext = context;
    }

    public static ActionBarPolicy get(Context context) {
        return new ActionBarPolicy(context);
    }

    public boolean enableHomeButtonByDefault() {
        return false;
    }

    public int getEmbeddedMenuWidthLimit() {
        return (int) (((float) this.mContext.getResources().getDisplayMetrics().widthPixels) * 0.7f);
    }

    public int getMaxActionButtons() {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i7 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i2 > 600) {
            return 5;
        }
        if (i2 > 960 && i7 > 720) {
            return 5;
        }
        if (i2 > 720 && i7 > 960) {
            return 5;
        }
        if (i2 >= 500) {
            return 4;
        }
        if (i2 > 640 && i7 > 480) {
            return 4;
        }
        if (i2 > 480 && i7 > 640) {
            return 4;
        }
        if (i2 >= 360) {
            return 3;
        }
        return 2;
    }

    public boolean hasEmbeddedTabs() {
        return false;
    }

    public boolean hasNavigationBar() {
        if (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) {
            return false;
        }
        return true;
    }

    public boolean showsOverflowMenuButton() {
        return true;
    }
}
