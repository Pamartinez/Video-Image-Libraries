package A9;

import android.app.Activity;
import com.samsung.android.gallery.app.activity.external.PickerSelectionHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.EditDetailsUpdater;
import com.samsung.android.gallery.module.lottie.service.RecapWorker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Serializable f2769h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2770i;

    public /* synthetic */ d(PickerSelectionHandler pickerSelectionHandler, boolean z, Activity activity, String str, ArrayList arrayList) {
        this.d = 1;
        this.f = pickerSelectionHandler;
        this.e = z;
        this.g = activity;
        this.f2769h = str;
        this.f2770i = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((RecapWorker) this.f).lambda$exec$0((AtomicReference) this.g, this.e, (AtomicReference) this.f2769h, (CountDownLatch) this.f2770i, (JSONObject) obj);
                return;
            case 1:
                ((PickerSelectionHandler) this.f).lambda$getUriList$7(this.e, (Activity) this.g, (String) this.f2769h, (ArrayList) this.f2770i, (MediaItem) obj);
                return;
            default:
                ((EditDetailsUpdater) this.f).lambda$updateGroupShotDateTime$6((String) this.g, this.e, (ArrayList) this.f2769h, (Collection) this.f2770i, (MediaItem) obj);
                return;
        }
    }

    public /* synthetic */ d(Object obj, Serializable serializable, boolean z, Serializable serializable2, Object obj2, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = serializable;
        this.e = z;
        this.f2769h = serializable2;
        this.f2770i = obj2;
    }
}
