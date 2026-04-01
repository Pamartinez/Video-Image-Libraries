package e5;

import B2.A;
import D0.e;
import android.graphics.Bitmap;
import androidx.appcompat.widget.SearchView;
import androidx.room.InvalidationTracker;
import com.samsung.android.gallery.app.ui.container.abstraction.TabFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.YearQueryCluster;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.MusicPickerHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.ReorderCallback;
import com.samsung.android.gallery.app.ui.tipcard.VideoAlbumTipCard;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.ColorCorrectHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerFragment;
import com.samsung.android.gallery.bixby.bixbycard.GalleryCardProvider;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.service.download.DownloadTask;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation;
import com.samsung.android.gallery.widget.animations.SimpleShrinkView;
import com.samsung.android.gallery.widget.animations.photostacking.DescriptionAnimation;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingViHandler;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimListView;
import com.samsung.android.gallery.widget.animations.viewer.InstantSlowMoSaveClipAnimation;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listview.pinch.v3.FakeViewParent;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerInnerView;
import ee.d0;
import ge.A1;
import ge.C1015d;
import ge.V0;
import ge.z1;

/* renamed from: e5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0451a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0451a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((PicturesFragment) obj).invalidateOptionsMenu();
                return;
            case 1:
                ((PicturesPinchAnimationManager) obj).lambda$setFakeViewLayoutAlpha$2();
                return;
            case 2:
                ((PropertyAnimator) obj).notifyPropertyAnimationEnd();
                return;
            case 3:
                ((FakeViewParent) obj).setAlpha(1.0f);
                return;
            case 4:
                ((PicturesViewAdapter) obj).lambda$handleDataUpdateAnimation$8();
                return;
            case 5:
                ((GalleryCardProvider) obj).updateCard();
                return;
            case 6:
                ((DownloadTask) obj).showStartMessage();
                return;
            case 7:
                ((QuickViewTransitionAnimation) obj).onShrinkViewReady();
                return;
            case 8:
                ((SimpleShrinkView) obj).onViewReady();
                return;
            case 9:
                ObjectDictionary.decreaseRefCounter((Bitmap) obj);
                return;
            case 10:
                ((MediaData) obj).notifyIndexingDone();
                return;
            case 11:
                ((YearQueryCluster) obj).lambda$init$2();
                return;
            case 12:
                ((VideoAlbumTipCard) obj).lambda$onDoneBtnClicked$1();
                return;
            case 13:
                ((DescriptionAnimation) obj).lambda$playInternal$2();
                return;
            case 14:
                ((ThrowingViHandler) obj).lambda$play$0();
                return;
            case 15:
                ((InvalidationTracker) obj).onAutoCloseCallback();
                return;
            case 16:
                ((MusicPickerHandler) obj).startSoundPickerCmd();
                return;
            case 17:
                ((ColorCorrectHandler) obj).lambda$handleBlackboardEvent$1();
                return;
            case 18:
                C1015d dVar = (C1015d) obj;
                e eVar = dVar.d;
                if (eVar != null) {
                    d0 d0Var = (d0) eVar.e;
                    if (!d0Var.f && !d0Var.e) {
                        eVar.B();
                    }
                }
                dVar.f4501c = null;
                return;
            case 19:
                ((V0) obj).o();
                return;
            case 20:
                A1 a12 = ((z1) obj).d;
                a12.e.a(new A(15, (Object) a12));
                return;
            case 21:
                ((SearchView) obj).lambda$seslSetSviEnabled$1();
                return;
            case 22:
                ((TabFragment) obj).lambda$loadBadge$0();
                return;
            case 23:
                ((SearchAnalysisTipView) obj).lambda$checkAndUpdateAnalysisTip$1();
                return;
            case 24:
                ((TextExpandAnimListView) obj).playItemAnimation();
                return;
            case 25:
                ((InstantSlowMoSaveClipAnimation) obj).lambda$hide$1();
                return;
            case 26:
                ((IMediaPlayerInnerView) obj).requestLayout();
                return;
            case 27:
                ((CategoryPresenter) obj).lambda$postAnalyticsLogCreatureCount$4();
                return;
            case 28:
                ((VuContainerFragment) obj).lambda$new$8();
                return;
            default:
                ((ReorderCallback) obj).lambda$drop$1();
                return;
        }
    }
}
