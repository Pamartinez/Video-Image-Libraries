package bc;

import He.F;
import Kd.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.transition.Transition;
import android.util.Pair;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkerWrapper;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.FloatingAutoCompleteDelegate;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.SharedTransitionHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageViewHolder;
import com.samsung.android.gallery.app.ui.map.staticmarker.StaticMarkerMapFragment;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.ExecuteTriggerType;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.LiveFocusOnlyHandler;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.SelfieFocusHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerFragment;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.JumpToTimelineDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.PhotoEditorTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.commandline.ops.DbDump;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.ExportType;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.SerialExecutor;
import com.samsung.android.gallery.widget.abstraction.SimpleTransitionListener;
import com.samsung.android.gallery.widget.animations.photostacking.ImageInfo;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingViHandler;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.transitory.StoriesViewPagerAdapter;
import com.samsung.android.gallery.widget.tag.MyTagView;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;
import com.samsung.android.sdk.scs.ai.extension.lts.LongTextSummarizer;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect;
import com.samsung.o3dp.lib3dphotography.O3DPhotoPipeline;
import ee.C0970c;
import ee.C0971d;
import ee.C0989w;
import ee.a0;
import ee.e0;
import fe.c;
import fe.i;
import fe.x;
import ge.C1019e0;
import ge.C1025g0;
import ge.C1031i0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ d(Object obj, Object obj2, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = obj;
        this.g = obj2;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((StoriesViewPagerAdapter) this.e).lambda$onThumbnailLoadCompleted$1((MediaItem) this.f, (ListViewHolder) this.g);
                return;
            case 1:
                GuidingLightEffect.show$lambda$2((GuidingLightEffect.ShowAnimationType) this.e, (GuidingLightEffect) this.f, (Long) this.g);
                return;
            case 2:
                ((StaticMarkerMapFragment) this.e).lambda$loadFinish$0((UniqueKey) this.f, (Bitmap) this.g);
                return;
            case 3:
                ((MyTagView) this.e).lambda$updateTagData$5((ArrayList) this.f, (ArrayList) this.g);
                return;
            case 4:
                ((CollageAdapter) this.e).lambda$onBindViewHolder$2((MediaItem) this.f, (CollageViewHolder) this.g);
                return;
            case 5:
                ((AbsEditorTransitionHandler) this.e).lambda$setTransitionListener$0((SimpleTransitionListener) this.f, (Transition) this.g);
                return;
            case 6:
                ((PhotoEditorTransitionHandler) this.e).lambda$prepareReturnTransition$1((MediaItem) this.f, (MediaItem) this.g);
                return;
            case 7:
                ((DbDump) this.e).lambda$operate$0((Context) this.f, (String) this.g);
                return;
            case 8:
                ((SerialExecutor) this.e).lambda$execNext$0((Pair) this.f, this.g);
                return;
            case 9:
                ((LongTextSummarizer) this.e).lambda$process$2((String) this.f, (AtomicReference) this.g);
                return;
            case 10:
                ((O3DPhotoPipeline) this.e).lambda$recordVideo$1((String) this.f, (Bitmap) this.g);
                return;
            case 11:
                ((ThrowingViHandler) this.e).lambda$bindImage$2((ImageView) this.f, (ImageInfo) this.g);
                return;
            case 12:
                c cVar = (c) this.e;
                a0 a0Var = (a0) this.g;
                Iterator it = ((ArrayList) this.f).iterator();
                while (it.hasNext()) {
                    i iVar = (i) it.next();
                    synchronized (iVar) {
                        iVar.b(a0Var, a0Var, false);
                    }
                }
                if (cVar.r.getAndSet(0) > 0) {
                    cVar.t.g(false);
                }
                x xVar = cVar.q;
                xVar.getClass();
                xVar.d(a0.f);
                a aVar = cVar.t;
                F.t(aVar.d, "transportShutdown() must be called before transportTerminated().");
                C1031i0 i0Var = (C1031i0) aVar.f;
                C0971d dVar = i0Var.f4519i;
                C0970c cVar2 = C0970c.INFO;
                C1019e0 e0Var = (C1019e0) aVar.e;
                dVar.c(cVar2, "{0} Terminated", e0Var.c());
                C0989w wVar = (C0989w) i0Var.g.f4312c.remove(Long.valueOf(e0Var.c().f4315c));
                e0 e0Var2 = i0Var.k;
                e0Var2.execute(new V1.c(i0Var, e0Var, false, 1));
                Iterator it2 = i0Var.f4520j.iterator();
                if (!it2.hasNext()) {
                    e0Var2.execute(new C1025g0(aVar, 1));
                    cVar.f4337a.B(cVar.b);
                    cVar.n.B(cVar.f4342o);
                    return;
                } else if (it2.next() == null) {
                    e0Var.getAttributes();
                    throw null;
                } else {
                    throw new ClassCastException();
                }
            case 13:
                ((ExportHandler) this.e).lambda$handleExport$1((MediaItem) this.f, (ExportType) this.g);
                return;
            case 14:
                ((ExportHandler) this.e).lambda$moveToStorage$12((MediaItem) this.f, (Consumer) this.g);
                return;
            case 15:
                ((SharedTransitionHandler) this.e).lambda$loadTransitionInfo$4((MediaItem) this.f, (Bitmap) this.g);
                return;
            case 16:
                ((AiEditHandler) this.e).lambda$executeAfterDetailsCollapsed$7((ExecuteTriggerType) this.f, (Runnable) this.g);
                return;
            case 17:
                ((DebugSmartCropRectInfo) this.e).lambda$initThumbnail$3((Context) this.f, (ImageView) this.g);
                return;
            case 18:
                ((EventHandler) this.e).lambda$postEvent$0((Event) this.f, (Object[]) this.g);
                return;
            case 19:
                ((FloatingAutoCompleteDelegate) this.e).lambda$onAutoCompleteItemsLoaded$0((ArrayList) this.f, (String) this.g);
                return;
            case 20:
                ((LiveFocusOnlyHandler) this.e).lambda$executeInternal$0((EventContext) this.g, (MediaItem) this.f);
                return;
            case 21:
                ((SelfieFocusHandler) this.e).lambda$executeInternal$0((EventContext) this.g, (MediaItem) this.f);
                return;
            case 22:
                ((MediaPlayerViewImp) this.e).lambda$open$0((MediaItem) this.f, (VideoReqInfo) this.g);
                return;
            case 23:
                FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) this.f;
                ((CategoryFragment) this.e).lambda$adjustEmptyViewMargin$2(floatingToolbarLayout, (WindowInsets) this.g);
                return;
            case 24:
                ((CategoryPresenter) this.e).lambda$updateSubTitle$2((Toolbar) this.f, (String) this.g);
                return;
            case 25:
                ((VuContainerFragment) this.e).lambda$printLog$6((TextView) this.f, (String) this.g);
                return;
            case 26:
                ((JumpToTimelineDelegate) this.e).lambda$handleJump$1((ViewerObjectComposite) this.g, (MediaItem) this.f);
                return;
            case 27:
                ((DebugLogger) this.e).lambda$apply$0((String) this.f, (String) this.g);
                return;
            case 28:
                ((CustomCover) this.e).lambda$bindImage$1((MediaItem) this.f, (Bitmap) this.g);
                return;
            default:
                ((Processor) this.e).lambda$startWork$1((ListenableFuture) this.f, (WorkerWrapper) this.g);
                return;
        }
    }

    public /* synthetic */ d(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }
}
