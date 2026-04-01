package Fa;

import androidx.preference.CheckBoxPreference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.EditMenuOptionsFragment;
import java.util.function.BiConsumer;

/* renamed from: Fa.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0553g implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2808a;
    public final /* synthetic */ EditMenuOptionsFragment b;

    public /* synthetic */ C0553g(EditMenuOptionsFragment editMenuOptionsFragment, int i2) {
        this.f2808a = i2;
        this.b = editMenuOptionsFragment;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2808a;
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) obj;
        SettingPreference settingPreference = (SettingPreference) obj2;
        EditMenuOptionsFragment editMenuOptionsFragment = this.b;
        switch (i2) {
            case 0:
                editMenuOptionsFragment.lambda$initPreference$1(checkBoxPreference, settingPreference);
                return;
            default:
                editMenuOptionsFragment.lambda$initPreference$3(checkBoxPreference, settingPreference);
                return;
        }
    }
}
