package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPickerDelegate;
import java.util.function.Consumer;

/* renamed from: o6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0497a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmPickerDelegate e;

    public /* synthetic */ C0497a(BgmPickerDelegate bgmPickerDelegate, int i2) {
        this.d = i2;
        this.e = bgmPickerDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BgmPickerDelegate bgmPickerDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                bgmPickerDelegate.lambda$addListenEvent$3(objArr);
                return;
            case 1:
                bgmPickerDelegate.onBgmChanged(objArr);
                return;
            case 2:
                bgmPickerDelegate.hideBgmPicker(objArr);
                return;
            default:
                bgmPickerDelegate.downloadAllBgm(objArr);
                return;
        }
    }
}
