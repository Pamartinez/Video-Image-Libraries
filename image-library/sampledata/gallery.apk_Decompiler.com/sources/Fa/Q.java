package Fa;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingFragment e;

    public /* synthetic */ Q(SettingFragment settingFragment, int i2) {
        this.d = i2;
        this.e = settingFragment;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$onViewCreated$0((FragmentActivity) obj);
                return;
            case 1:
                this.e.lambda$onConfigurationChanged$2((ActionBar) obj);
                return;
            default:
                this.e.removeStoryAndRecapData(((Boolean) obj).booleanValue());
                return;
        }
    }
}
