package c4;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV2;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.filter.DecoratePluginInOutStreamFilter;
import com.samsung.android.sum.core.filter.EncoderFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.message.ResponseHolder;
import com.samsung.android.sum.core.service.LocalServiceProxy;
import com.samsung.android.sum.core.service.RemoteServiceProxy;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* renamed from: c4.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0438h implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ C0438h(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1134));
                return;
            case 1:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1133));
                return;
            case 2:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1084));
                return;
            case 3:
                ((Blackboard) obj).postEvent(EventMessage.obtain(3077));
                return;
            case 4:
                ((CollageViewHolder) obj).stopPreview();
                return;
            case 5:
                ((AbsEditorTransitionHandler) obj).onDestroy();
                return;
            case 6:
                ((AbsEditorTransitionHandler) obj).onDestroy();
                return;
            case 7:
                DecoratePluginInOutStreamFilter.lambda$run$3((Exception) obj);
                return;
            case 8:
                DecoratePluginInOutStreamFilter.lambda$run$5((Exception) obj);
                return;
            case 9:
                EncoderFilter.lambda$configCodec$2((Exception) obj);
                return;
            case 10:
                ((MediaFilter) obj).release();
                return;
            case 11:
                ((MediaFilter) obj).prepare();
                return;
            case 12:
                MediaMuxerFilter.lambda$onMessageReceived$1((Exception) obj);
                return;
            case 13:
                ((MediaBuffer) obj).release();
                return;
            case 14:
                LocalServiceProxy.lambda$release$3((ResponseHolder) obj);
                return;
            case 15:
                RemoteServiceProxy.lambda$release$6((ResponseHolder) obj);
                return;
            case 16:
                ((GalleryAppBarLayout) obj).setTitle((int) R.string.hide_content);
                return;
            case 17:
                Utils.closeSilently((Cursor) obj);
                return;
            case 18:
                ((FragmentActivity) obj).invalidateOptionsMenu();
                return;
            case 19:
                ((Blackboard) obj).post("debug://TimeInflateQuery", Long.valueOf(System.currentTimeMillis()));
                return;
            case 20:
                ((PicturesViewAdapter) obj).checkVisibleViewHoldersForXLarge();
                return;
            case 21:
                PicturesFragment.lambda$handleResolutionChange$8((PicturesViewAdapter) obj);
                return;
            case 22:
                ((GalleryAppBarLayout) obj).setTitle((CharSequence) null);
                return;
            case 23:
                ((Blackboard) obj).post("debug://TimeInflateQuery", Long.valueOf(System.currentTimeMillis()));
                return;
            case 24:
                PicturesPinchAnimationManagerV2.lambda$addHeaderView$0((ViewGroup.LayoutParams) obj);
                return;
            case 25:
                PicturesPresenter.lambda$handleEvent$1((PicturesViewAdapter) obj);
                return;
            case 26:
                ((PicturesViewAdapter) obj).onPrepareDelete();
                return;
            case 27:
                ((PicturesViewAdapter) obj).onAbortDelete();
                return;
            case 28:
                ((PicturesViewAdapter) obj).updateMutableContent();
                return;
            default:
                ((View) obj).setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
                return;
        }
    }
}
