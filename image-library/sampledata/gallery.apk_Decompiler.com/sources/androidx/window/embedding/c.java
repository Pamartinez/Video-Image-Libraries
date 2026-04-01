package androidx.window.embedding;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import androidx.window.layout.SidecarWindowBackend;
import androidx.window.layout.WindowLayoutInfo;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPagerHolder;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.app.service.StoryBaseService;
import com.samsung.android.gallery.app.service.StorySaveService;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerPresenter;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageSaveHelper;
import com.samsung.android.gallery.app.ui.map.search.SearchMapPresenter;
import com.samsung.android.gallery.app.ui.map.staticmarker.MarkerViewHolder;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.commandline.ops.DbDump;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.StoriesPinData;
import com.samsung.android.gallery.module.details.EditDetailsUpdater;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.search.rubin.RubinManager;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.settings.activity.BasePreferenceActivity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.tag.MyTagAdapter2;
import com.samsung.android.gallery.widget.tag.MyTagView;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.DetachableSurfaceReadChannel;
import com.samsung.android.sum.core.channel.StapleSurfaceReadChannel;
import com.samsung.android.sum.core.channel.StapleSurfaceWriteChannel;
import com.samsung.android.sum.core.channel.SurfaceChannelImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ExtensionEmbeddingBackend.SplitListenerWrapper.m31accept$lambda1((ExtensionEmbeddingBackend.SplitListenerWrapper) this.e, (ArrayList) this.f);
                return;
            case 1:
                SidecarWindowBackend.WindowLayoutChangeCallbackWrapper.m32accept$lambda0((SidecarWindowBackend.WindowLayoutChangeCallbackWrapper) this.e, (WindowLayoutInfo) this.f);
                return;
            case 2:
                ((PresentationViewPagerHolder) this.e).lambda$updateImage$0((Bitmap) this.f);
                return;
            case 3:
                ((SearchPicturesPickerPresenter) this.e).lambda$createSearchToolbarDelegate$0((String) this.f);
                return;
            case 4:
                ((StoriesPinModel) this.e).lambda$loadData$4((StoriesPinData) this.f);
                return;
            case 5:
                ((SearchMapPresenter) this.e).lambda$moveTo$3((double[]) this.f);
                return;
            case 6:
                ((HighlightEncodeService) this.e).lambda$onInterruptService$3((HighlightEncoder) this.f);
                return;
            case 7:
                ((MediaCaptureService) this.e).lambda$onSuccess$7((String) this.f);
                return;
            case 8:
                ((Blackboard) this.e).postEvent(EventMessage.obtain(1115, (Uri) this.f));
                return;
            case 9:
                ((StoryBaseService) this.e).lambda$onStartService$1((StoryBaseService.Task) this.f);
                return;
            case 10:
                ((StoryBaseService) this.e).lambda$onInterruptService$0((Intent) this.f);
                return;
            case 11:
                ((StorySaveService) this.e).lambda$startViewer$2((String) this.f);
                return;
            case 12:
                ((MarkerViewHolder) this.e).lambda$addLayoutChangeListener$0((Bitmap) this.f);
                return;
            case 13:
                ((DirectorsViewCache) this.e).lambda$updateData$2((ConcurrentHashMap) this.f);
                return;
            case 14:
                ThreadUtil.postOnUiThread(new c(15, (Consumer) this.f, RubinManager.getRubinState((Context) this.e)));
                return;
            case 15:
                RubinManager.lambda$loadRubinState$0((Consumer) this.e, (String) this.f);
                return;
            case 16:
                ((MyTagAdapter2) this.e).lambda$loadData$0((ArrayList) this.f);
                return;
            case 17:
                ((MyTagView) this.e).lambda$setMediaItem$0((MediaItem) this.f);
                return;
            case 18:
                CollageSaveHelper.lambda$saveImageCollage$2((View) this.e, (int[]) this.f);
                return;
            case 19:
                ((CustomRelationshipKeySet) this.e).lambda$reassignPreference$1((List) this.f);
                return;
            case 20:
                ((DbDump) this.e).lambda$operate$1((Context) this.f);
                return;
            case 21:
                ((Blackboard) this.e).publish((String) this.f, (Object) null);
                return;
            case 22:
                ((EditDetailsUpdater) this.e).lambda$onSave$0((Runnable) this.f);
                return;
            case 23:
                ((BasePreferenceActivity) this.e).lambda$checkValidPreference$5((Consumer) this.f);
                return;
            case 24:
                SharedTransition.startPostponedEnterTransition((SharedTransition.TransitionListener) this.e, (Blackboard) this.f);
                return;
            case 25:
                ((DetachableSurfaceReadChannel) this.e).lambda$onImageAvailable$3((Long) this.f);
                return;
            case 26:
                ((StapleSurfaceReadChannel) this.e).lambda$onImageReceived$1((MediaBuffer) this.f);
                return;
            case 27:
                ((StapleSurfaceReadChannel) this.e).lambda$sendHardwareBuffer$0((Image) this.f);
                return;
            case 28:
                ((StapleSurfaceWriteChannel) this.e).lambda$configure$4((Consumer) this.f);
                return;
            default:
                ((SurfaceChannelImpl) this.e).lambda$onImageReceive$7((Image) this.f);
                return;
        }
    }
}
