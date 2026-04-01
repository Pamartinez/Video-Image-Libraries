package com.samsung.android.gallery.settings.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.google.android.material.tabs.c;
import com.samsung.android.gallery.app.remote.DlnaManager;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabFragment;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerFragment;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerPresenter;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DlnaDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.HdrContentsDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.KeyguardDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.bixby.bixbycard.PhotoDataFetcher;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import com.samsung.android.gallery.module.service.download.CloudDownloadTask;
import com.samsung.android.gallery.module.story.ExportType;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.SendChannelRouter;
import com.samsung.android.sum.core.channel.SurfaceChannelImpl;
import com.samsung.android.sum.core.controller.MediaFilterController;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterGroup;
import com.samsung.android.sum.core.filter.MediaFilterRetriever;
import com.samsung.android.sum.core.filter.MediaFilterTracer;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.graph.GraphNodeBase;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import com.samsung.android.sum.core.graph.MFGraph;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.plugin.NativeUniImgpPlugin;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((BasePreferenceActivity) this.e).lambda$commitFragment$0((String) this.f, (Boolean) obj);
                return;
            case 1:
                ((Bundle) this.e).putString((String) obj, (String) ((Map) this.f).get((String) obj));
                return;
            case 2:
                SendChannelRouter.lambda$broadcast$1((MediaBuffer) this.e, (MediaBuffer) this.f, (BufferChannel) obj);
                return;
            case 3:
                ((SurfaceChannelImpl) this.e).lambda$new$2((BufferChannel) this.f, (MediaBuffer) obj);
                return;
            case 4:
                MediaFilterController.lambda$sendMessage$3((Request) this.e, (ConditionVariable) this.f, (Message) obj);
                return;
            case 5:
                ((MutableMediaFormat) this.e).set((String) obj, ((MutableMediaBuffer) this.f).getFixedFormat().get((String) obj));
                return;
            case 6:
                ((MediaFilterRetriever) this.e).lambda$retrieve$2((MediaFilterGroup) this.f, (MediaFilter) obj);
                return;
            case 7:
                ((MediaFilterTracer) this.e).lambda$makeReport$0((Message) this.f, (Message) obj);
                return;
            case 8:
                ((Event) this.e).put((String) obj, ((MediaBuffer) this.f).getFormat().get((String) obj));
                return;
            case 9:
                ((GraphNodeBase) this.e).lambda$applyGraphOption$11((MediaFilterRetriever) this.f, obj);
                return;
            case 10:
                MFDescriptorGraph.lambda$toMediaFilterGraph$4((MFGraph.Builder) this.e, (List) this.f, (Pair) obj);
                return;
            case 11:
                ((Message) this.e).lambda$new$1((Bundle) this.f, (String) obj);
                return;
            case 12:
                ((NativeUniImgpPlugin) this.e).lambda$run$3((HashMap) this.f, (MediaBuffer) obj);
                return;
            case 13:
                ((PhotoDataFetcher) this.e).lambda$getPhotoData$0((List) this.f, (QueryParams) obj);
                return;
            case 14:
                ((CloudDownloadTask) this.e).lambda$downloadInternal$1((MediaItem) this.f, (CloudDownloadMonitor) obj);
                return;
            case 15:
                DelegateComposite.lambda$onCreateInternal$1((Bundle) this.e, (ArrayList) this.f, (AbsDelegate) obj);
                return;
            case 16:
                DelegateComposite.lambda$onAttachInternal$13((Context) this.e, (ArrayList) this.f, (AbsDelegate) obj);
                return;
            case 17:
                DelegateComposite.lambda$onBindViewInternal$4((View) this.e, (ArrayList) this.f, (AbsDelegate) obj);
                return;
            case 18:
                ((ExportHandler) this.e).lambda$handleExportDoneInternal$3((ExportType) this.f, (MediaItem) obj);
                return;
            case 19:
                ((ExportHandler) this.e).lambda$loadHiddenMediaItem$9((String) this.f, (File) obj);
                return;
            case 20:
                ((RestoreUserData) this.e).lambda$init$1((ArrayList) this.f, (FileOpJob) obj);
                return;
            case 21:
                ViewUtils.lambda$getBitmapFromSurfaceView$1((AtomicReference) this.e, (CountDownLatch) this.f, (Bitmap) obj);
                return;
            case 22:
                ((CategoryFragment) this.e).lambda$adjustEmptyViewMargin$3((WindowInsets) this.f, (FloatingToolbarLayout) obj);
                return;
            case 23:
                ((VuContainerFragment) this.e).lambda$printLog$7((String) this.f, (TextView) obj);
                return;
            case 24:
                ((VuContainerPresenter) this.e).lambda$createGlobalSubscriberList$0((ArrayList) this.f, (KeyguardDelegate) obj);
                return;
            case 25:
                ((BottomTabTouchDelegate) this.e).lambda$addOnTabTouchListener$0((BottomTabTouchDelegate.OnMenuTabTouchListener) this.f, (View) obj);
                return;
            case 26:
                ((DlnaDelegate) this.e).lambda$connectOriginalContents$2((MediaItem) this.f, (DlnaManager) obj);
                return;
            case 27:
                ((HdrContentsDelegate) this.e).lambda$onHandleEvent$5((ContentModel) this.f, (Handler) obj);
                return;
            case 28:
                ((BottomTabFragment) this.e).lambda$publishDataRequest$5((c) this.f, (Blackboard) obj);
                return;
            default:
                BottomTabFragment.lambda$showFragment$4((FragmentTransaction) this.e, (String) this.f, (MvpBaseFragment) obj);
                return;
        }
    }

    public /* synthetic */ e(PhotoDataFetcher photoDataFetcher, List list) {
        this.d = 13;
        this.e = photoDataFetcher;
        this.f = list;
    }
}
