package Pb;

import androidx.activity.BackEventCompat;
import com.samsung.android.gallery.widget.predictiveback.SimpleOnBackPressedCallback;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BackEventCompat e;

    public /* synthetic */ d(BackEventCompat backEventCompat, int i2) {
        this.d = i2;
        this.e = backEventCompat;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                if (obj == null) {
                    null.onBackProgressed(this.e);
                    return;
                }
                throw new ClassCastException();
            default:
                SimpleOnBackPressedCallback.lambda$handleOnBackStarted$0(this.e, (SimpleOnBackPressedCallback.OnBackStartedListener) obj);
                return;
        }
    }
}
