package r6;

import D5.a;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchAnalysisTipHeaderView;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.LastPageDataHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ShrinkToCameraHandler;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ e(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((MediaItem) obj).setBroken(true);
                return;
            case 1:
                ((MediaItem) obj).setBroken(true);
                return;
            case 2:
                ((View) obj).post(new a((View) obj, 8));
                return;
            case 3:
                ((QueryParams) obj).printQuery();
                return;
            case 4:
                ((Button) obj).setAccessibilityTraversalAfter(R$id.moreinfo_basic_info_simple);
                return;
            case 5:
                ((QueryParams) obj).setEssentialFaceOnly(true);
                return;
            case 6:
                ((AnimatorSet) obj).cancel();
                return;
            case 7:
                ((LastPageDataHolder.UpdateListener) obj).resetCountDown();
                return;
            case 8:
                ((LastPageDataHolder.UpdateListener) obj).focusPositionChanged();
                return;
            case 9:
                ((Window) obj).setFlags(SerializeOptions.SORT, SerializeOptions.SORT);
                return;
            case 10:
                ((AnimatorSet) obj).cancel();
                return;
            case 11:
                ((View) obj).setAlpha(1.0f);
                return;
            case 12:
                ((SearchHeaderView) obj).handleResolutionChanged();
                return;
            case 13:
                ((SearchHeaderView) obj).onPause();
                return;
            case 14:
                ((SearchHeaderView) obj).resetViewBy();
                return;
            case 15:
                ((SearchHeaderView) obj).onResume();
                return;
            case 16:
                ((SearchAnalysisTipHeaderView) obj).dataChanged();
                return;
            case 17:
                ((SearchHeaderView) obj).recycle();
                return;
            case 18:
                ((SearchHeaderView) obj).onDestroyView();
                return;
            case 19:
                ((SuggesterView) obj).onConfigurationChanged();
                return;
            case 20:
                ((SuggesterView) obj).updateEmptyViewPadding();
                return;
            case 21:
                ((ForegroundViewController) obj).reloadSuggestionData();
                return;
            case 22:
                ((ForegroundViewController) obj).initFloatingToolbarLayout();
                return;
            case 23:
                ((SearchPicturesAdapter) obj).clearExpansion();
                return;
            case 24:
                ((ForegroundViewController) obj).requestSuggestionData(false, true);
                return;
            case 25:
                ((ForegroundViewController) obj).onViewResume();
                return;
            case 26:
                ((ArrayList) obj).clear();
                return;
            case 27:
                SimpleAnimator.create((View) obj, R.anim.viewer_open_exit, (Animation.AnimationListener) null);
                return;
            case 28:
                SimpleAnimator.create((View) obj, R.anim.viewer_close_enter, (Animation.AnimationListener) null);
                return;
            default:
                ShrinkToCameraHandler.lambda$onHideMainView$3((IMediaPlayerView) obj);
                return;
        }
    }
}
