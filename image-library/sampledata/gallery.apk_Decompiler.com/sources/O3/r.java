package O3;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.internals.NondestructiveSaveRemasterCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurInterface;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.framework.core.network.ByteBufferWriter;
import com.samsung.scsp.framework.core.network.base.PayloadWriterVisitorImpl;
import com.samsung.scsp.framework.core.network.visitor.PayloadWriterVisitor;
import com.samsung.scsp.media.api.OneDriveRangeUploadManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements ThumbnailLoadedListener, OnCompleteListener, ByteBufferWriter.BufferWriterListener, ResponseListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ r(long j2, boolean[] zArr, long[] jArr) {
        this.d = 3;
        this.e = j2;
        this.f = zArr;
        this.g = jArr;
    }

    public void onComplete(Task task) {
        ((NeuralTranslator) this.f).lambda$getTranslateSupportLanguage$2(this.e, (String) this.g, task);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                ((NondestructiveSaveRemasterCmd) this.f).lambda$preloadThumbnail$3((MediaItem) this.g, this.e, bitmap, uniqueKey, thumbKind);
                return;
            default:
                BlurUtil.lambda$applyBlur$3((BlurInterface) this.f, (MediaItem) this.g, this.e, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public void onResponse(Object obj) {
        OneDriveRangeUploadManager.lambda$getUploadSessionInfo$6(this.e, (boolean[]) this.f, (long[]) this.g, (SCHashMap) obj);
    }

    public void onWritten(long j2) {
        PayloadWriterVisitorImpl.lambda$visit$1((PayloadWriterVisitor.Payload) this.f, (ProgressListener) this.g, this.e, j2);
    }

    public /* synthetic */ r(NeuralTranslator neuralTranslator, long j2, String str) {
        this.d = 1;
        this.f = neuralTranslator;
        this.e = j2;
        this.g = str;
    }

    public /* synthetic */ r(Object obj, Object obj2, long j2, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = j2;
    }
}
