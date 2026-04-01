package x3;

import Ae.b;
import android.graphics.Canvas;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.AccurateWidthTextView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ AccurateWidthTextView e;

    public /* synthetic */ a(AccurateWidthTextView accurateWidthTextView, int i2) {
        this.d = i2;
        this.e = accurateWidthTextView;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        AccurateWidthTextView accurateWidthTextView = this.e;
        Canvas canvas = (Canvas) obj;
        switch (i2) {
            case 0:
                return AccurateWidthTextView.onDraw$lambda$1(accurateWidthTextView, canvas);
            default:
                return AccurateWidthTextView.onDraw$lambda$2(accurateWidthTextView, canvas);
        }
    }
}
