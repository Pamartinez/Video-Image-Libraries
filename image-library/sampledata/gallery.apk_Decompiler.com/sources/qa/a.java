package Qa;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessingViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenSubItemPool;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remaster.ErrorReason;
import com.samsung.android.gallery.module.thumbnail.IconResources;
import com.samsung.android.gallery.support.library.v0.Sem80ApiCompatImpl;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.filter.NNFilter;
import com.samsung.android.sum.core.filter.factory.PluginFilterCreator;
import com.samsung.android.sum.core.functional.BufferProcessor;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.service.ServiceProxy;
import java.util.List;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2866a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2867c;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.f2866a = i2;
        this.b = obj;
        this.f2867c = obj2;
    }

    public final Object apply(Object obj) {
        switch (this.f2866a) {
            case 0:
                return ((Sem80ApiCompatImpl) this.b).lambda$getDexInfo$3((Context) this.f2867c, (String) obj);
            case 1:
                return ((RemasterProcessingViewHandler) this.b).lambda$setCancelingText$8((ErrorReason) this.f2867c, (TextView) obj);
            case 2:
                return ((SingleTakenSubItemPool) this.b).lambda$get$0((MediaItem) this.f2867c, (Long) obj);
            case 3:
                return ((ViewPagerDelegate) this.b).lambda$findHoldingPosition$1((MediaItem) this.f2867c, (MediaItem) obj);
            case 4:
                return ((AbsEditorTransitionHandler) this.b).lambda$waitNewMediaItemLoaded$2((String) this.f2867c, (MediaItem) obj);
            case 5:
                return ((GridSpans) this.b).lambda$computeHeight$2((Context) this.f2867c, (Integer) obj);
            case 6:
                return ((ServiceProxy) obj).request(Request.of((int) Message.PROCESS_DATA).setInputBuffer((List<MediaBuffer>) (List) this.b).setOutputBuffer((List<MediaBuffer>) (List) this.f2867c));
            case 7:
                return ((PluginDescriptor) obj).getExtra((String) this.b, this.f2867c);
            case 8:
                return ((NNFilter) this.b).lambda$run$1((MediaBuffer) this.f2867c, (BufferProcessor) obj);
            case 9:
                return PluginFilterCreator.lambda$createNNFilter$10((Context) this.f2867c, (Pair) this.b, (String) obj);
            default:
                return IconResources.lambda$getIconDrawable$0((Context) this.f2867c, (Bitmap) this.b, (String) obj);
        }
    }

    public /* synthetic */ a(Context context, Object obj, int i2) {
        this.f2866a = i2;
        this.f2867c = context;
        this.b = obj;
    }
}
