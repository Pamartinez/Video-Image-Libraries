package e6;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.CreateMovieMultiEditCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.ISearchAutoCompleteViewV2;
import com.samsung.android.gallery.app.ui.list.stories.highlight.ToolbarUpdater;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.media.AudioManagerHelper;
import com.samsung.android.gallery.module.story.transcode.transcoder.ITranscoder;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimListView;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.ScaleDelegate;
import java.util.function.Consumer;

/* renamed from: e6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0453a implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ C0453a(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Blackboard) obj).post("lifecycle://ON_ExitSelectionMode", Boolean.FALSE);
                return;
            case 1:
                ((Animator.AnimatorListener) obj).onAnimationEnd((Animator) null);
                return;
            case 2:
                ((Animator) obj).start();
                return;
            case 3:
                ((Animator) obj).pause();
                return;
            case 4:
                ((Animator) obj).resume();
                return;
            case 5:
                ((Activity) obj).setResult(-1);
                return;
            case 6:
                Log.d("YearQueryCluster", "getCache Fail. " + ((ClusterItem) obj));
                return;
            case 7:
                new CreateMovieMultiEditCmd().execute((EventContext) obj, ((EventContext) obj).getSelectedItems());
                return;
            case 8:
                ViewUtils.setVisibility((TextView) obj, 4);
                return;
            case 9:
                ((TextView) obj).setVisibility(4);
                return;
            case 10:
                ((View) obj).setVisibility(4);
                return;
            case 11:
                ((BaseListPresenter) obj).forceReloadData();
                return;
            case 12:
                ((IReorderHandler) obj).resetDrag();
                return;
            case 13:
                ((IReorderHandler) obj).setDragging(true);
                return;
            case 14:
                ((ToolbarUpdater) obj).onMultiWindowModeChanged();
                return;
            case 15:
                ((ToolbarUpdater) obj).handleResolutionChange();
                return;
            case 16:
                ((ToolbarUpdater) obj).initView();
                return;
            case 17:
                ((RecyclerView) ((View) obj)).seslSetRecoilEnabled(false);
                return;
            case 18:
                TextExpandAnimListView.lambda$updateViewList$0((TextExpandAnimView) obj);
                return;
            case 19:
                ((TextExpandAnimView) obj).getItemAnimation().play();
                return;
            case 20:
                ((TextExpandAnimView) obj).getItemAnimation().cancel();
                return;
            case 21:
                ((ViewGroup) obj).setOnTouchListener((View.OnTouchListener) null);
                return;
            case 22:
                ((ViewGroup) obj).setOnTouchListener((View.OnTouchListener) null);
                return;
            case 23:
                ((ViewGroup) obj).setOnTouchListener((View.OnTouchListener) null);
                return;
            case 24:
                ((ISearchAutoCompleteViewV2) obj).onLoadAutoCompleteItems();
                return;
            case 25:
                ((BgmOptions) obj).release();
                return;
            case 26:
                ((AudioManagerHelper) obj).setAudioFocusEnabled(false);
                return;
            case 27:
                ((ITranscoder) obj).interrupt();
                return;
            case 28:
                ((ITranscoder) obj).release();
                return;
            default:
                ((ScaleDelegate) obj).resetScale();
                return;
        }
    }
}
