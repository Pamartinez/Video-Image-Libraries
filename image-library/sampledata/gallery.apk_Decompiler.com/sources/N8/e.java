package N8;

import android.content.ClipData;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.clip.objectcapture.SaveAsStickerCallback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements SaveAsStickerCallback, ObjectCaptureDrawHelper.OnStartDragListener {
    public final /* synthetic */ ObjectCaptureHelper d;

    public /* synthetic */ e(ObjectCaptureHelper objectCaptureHelper) {
        this.d = objectCaptureHelper;
    }

    public void a() {
        this.d.lambda$setToolbarMenu$7();
    }

    public ClipData onStarDrag() {
        return this.d.getClipData();
    }
}
