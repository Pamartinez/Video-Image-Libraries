package D9;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.VideoPreview;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.widget.dialog.BiometricPromptCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Consumer e;

    public /* synthetic */ e(int i2, Consumer consumer) {
        this.d = i2;
        this.e = consumer;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Consumer consumer = this.e;
        switch (i2) {
            case 0:
                MdeSharingService.getInstance().requestSpaceDeletion((String) obj, new a(2, consumer));
                return;
            case 1:
                VideoPreview.lambda$execute$0(consumer, (RecyclerView) obj);
                return;
            default:
                BiometricPromptCompat.lambda$showCustomPinPrompt$0(consumer, (Boolean) obj);
                return;
        }
    }
}
