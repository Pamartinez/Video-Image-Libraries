package Fa;

import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.preference.PreferenceManager;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;
import java.util.function.Consumer;

/* renamed from: Fa.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0549c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BasePreferenceFragment e;

    public /* synthetic */ C0549c(BasePreferenceFragment basePreferenceFragment, int i2) {
        this.d = i2;
        this.e = basePreferenceFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BasePreferenceFragment basePreferenceFragment = this.e;
        switch (i2) {
            case 0:
                basePreferenceFragment.lambda$updateMainLayoutBackgroundColor$3((View) obj);
                return;
            case 1:
                basePreferenceFragment.lambda$onResume$2((ActionBar) obj);
                return;
            default:
                basePreferenceFragment.lambda$resetPreferenceScreen$4((PreferenceManager) obj);
                return;
        }
    }
}
