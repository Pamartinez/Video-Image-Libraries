package j6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmPickerView;

/* renamed from: j6.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0474c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmPickerView e;

    public /* synthetic */ C0474c(BgmPickerView bgmPickerView, int i2) {
        this.d = i2;
        this.e = bgmPickerView;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        BgmPickerView bgmPickerView = this.e;
        switch (i2) {
            case 0:
                bgmPickerView.onTipCardNotNowClicked(view);
                return;
            default:
                bgmPickerView.onTipCardDownloadAllClicked(view);
                return;
        }
    }
}
