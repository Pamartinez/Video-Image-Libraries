package a6;

import androidx.core.util.Consumer;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ProcessingViewManager f2479a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2480c;

    public /* synthetic */ n(ProcessingViewManager processingViewManager, String str, String str2) {
        this.f2479a = processingViewManager;
        this.b = str;
        this.f2480c = str2;
    }

    public final void accept(Object obj) {
        this.f2479a.lambda$show$1(this.b, this.f2480c, (ArrayList) obj);
    }
}
