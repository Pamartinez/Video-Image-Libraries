package x6;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.switchable.LayoutType;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerVideoHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesPresenter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureImageHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;
import com.samsung.android.gallery.image360.widget.Image360FastOptionMoreMenu;
import com.samsung.android.gallery.module.account.FamilyGroupHelper;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.filebrowser.FileListFragment;
import com.samsung.android.gallery.plugins.filebrowser.FilePreviewFragment;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ f(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((ViewPagerVideoHolder) this.e).lambda$hidePreviewView$0((View) this.f);
                return;
            case 1:
                ((ObjectCaptureImageHandler) this.e).lambda$onCurrentItemChanged$2((Object[]) this.f);
                return;
            case 2:
                ((SwitchableDialog) this.e).lambda$onSwitchDialog$4((LayoutType) this.f);
                return;
            case 3:
                ((CreatureContactDelegate) this.e).lambda$launchContactDetail$2((String) this.f);
                return;
            case 4:
                ((StoryPicturesPresenter) this.e).lambda$moveToStorySpotifySlideShow$0((MediaItem) this.f);
                return;
            case 5:
                ((TextExtractionHandler) this.e).lambda$onTapTranslate$11((Boolean) this.f);
                return;
            case 6:
                ((Image360FastOptionMoreMenu) this.e).lambda$updateAdapter$0((ArrayList) this.f);
                return;
            case 7:
                ((FaceClusterMergeDelegate) this.e).lambda$onBackPressed$14((Bitmap) this.f);
                return;
            case 8:
                ((SearchCreatureHeader2View) this.e).lambda$loadContactIcon$3((CreatureData) this.f);
                return;
            case 9:
                FamilyGroupHelper.requestMyMemberType((Context) this.e, (AtomicReference) this.f);
                return;
            case 10:
                ((SamsungAccountManager) this.e).lambda$getGUID$0((Function) this.f);
                return;
            case 11:
                ((FileListFragment) this.e).lambda$onClick$16((FilePreviewFragment) this.f);
                return;
            case 12:
                ((FileListFragment) this.e).lambda$loadAndUpdate$7((String) this.f);
                return;
            case 13:
                ((FilePreviewFragment) this.e).lambda$initData$1((CharSequence) this.f);
                return;
            default:
                FilePreviewFragment.lambda$loadAndStartViewer$4((MediaItem[]) this.e, (Context) this.f);
                return;
        }
    }
}
