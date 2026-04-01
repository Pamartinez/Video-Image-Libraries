package q3;

import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode.TextActionModeCallback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Ae.a {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextActionModeCallback e;

    public /* synthetic */ a(TextActionModeCallback textActionModeCallback, int i2) {
        this.d = i2;
        this.e = textActionModeCallback;
    }

    public final Object invoke() {
        int i2 = this.d;
        TextActionModeCallback textActionModeCallback = this.e;
        switch (i2) {
            case 0:
                return TextActionModeCallback.onTranslateClicked$lambda$2(textActionModeCallback);
            default:
                return TextActionModeCallback.onCopyClicked$lambda$0(textActionModeCallback);
        }
    }
}
