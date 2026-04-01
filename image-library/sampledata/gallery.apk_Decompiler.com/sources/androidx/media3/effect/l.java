package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DefaultVideoFrameProcessor.Factory f1010a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DebugViewProvider f1011c;
    public final /* synthetic */ ColorInfo d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ VideoFrameProcessingTaskExecutor f;
    public final /* synthetic */ Executor g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VideoFrameProcessor.Listener f1012h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ GlObjectsProvider f1013i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ boolean f1014j;

    public /* synthetic */ l(DefaultVideoFrameProcessor.Factory factory, Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, Executor executor, VideoFrameProcessor.Listener listener, GlObjectsProvider glObjectsProvider, boolean z3) {
        this.f1010a = factory;
        this.b = context;
        this.f1011c = debugViewProvider;
        this.d = colorInfo;
        this.e = z;
        this.f = videoFrameProcessingTaskExecutor;
        this.g = executor;
        this.f1012h = listener;
        this.f1013i = glObjectsProvider;
        this.f1014j = z3;
    }

    public final Object call() {
        return this.f1010a.lambda$create$0(this.b, this.f1011c, this.d, this.e, this.f, this.g, this.f1012h, this.f1013i, this.f1014j);
    }
}
