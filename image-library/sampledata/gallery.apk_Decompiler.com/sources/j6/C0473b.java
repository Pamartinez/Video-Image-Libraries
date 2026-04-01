package j6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmPickerView;
import com.samsung.android.gallery.module.story.EffectTheme;
import java.util.function.Consumer;

/* renamed from: j6.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0473b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmPickerView e;

    public /* synthetic */ C0473b(BgmPickerView bgmPickerView, int i2) {
        this.d = i2;
        this.e = bgmPickerView;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BgmPickerView bgmPickerView = this.e;
        switch (i2) {
            case 0:
                bgmPickerView.onSelectTheme((EffectTheme) obj);
                return;
            default:
                bgmPickerView.lambda$onUpdated$0((String) obj);
                return;
        }
    }
}
