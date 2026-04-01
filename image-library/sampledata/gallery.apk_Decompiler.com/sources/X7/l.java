package x7;

import Y1.f;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.samsung.android.gallery.app.ui.dialog.tag.AddTagAdapter;
import com.samsung.android.gallery.app.ui.dialog.tag.AddTagDialog;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchMapHeaderView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureVideoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.directorsview.DirectorsViewHandler;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.lottie.recorder.RecordingOperation;
import com.samsung.android.gallery.plugins.filebrowser.FileBrowseActivity;
import com.samsung.android.gallery.widget.editdetails.EditDetailsEditText;
import com.samsung.android.gallery.widget.effects.GalleryGuidingLightEffect;
import com.samsung.android.gallery.widget.effects.RenderEffectBlur;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ l(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((ObjectCaptureVideoHandler) obj).lambda$onVideoStarted$4();
                return;
            case 1:
                ((EditDetailsEditText) obj).showSip();
                return;
            case 2:
                f fVar = (f) obj;
                fVar.b = false;
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) fVar.e;
                ViewDragHelper viewDragHelper = sideSheetBehavior.l;
                if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                    fVar.b(fVar.f951c);
                    return;
                } else if (sideSheetBehavior.k == 2) {
                    sideSheetBehavior.setStateInternal(fVar.f951c);
                    return;
                } else {
                    return;
                }
            case 3:
                ((CreatureContactDelegate) obj).lambda$launchContactDetail$3();
                return;
            case 4:
                ((CreaturePicturesDelegate) obj).publishCreatureHeaderItem();
                return;
            case 5:
                ((GalleryGuidingLightEffect) obj).invalidateEffect();
                return;
            case 6:
                ((RenderEffectBlur) obj).invalidateBlurTargetView();
                return;
            case 7:
                ((AddTagAdapter) obj).notifyDataSetChanged();
                return;
            case 8:
                ((AddTagDialog) obj).showSoftInput();
                return;
            case 9:
                ((FaceClusterMergeDelegate) obj).lambda$loadRelatedFaceClusterData$1();
                return;
            case 10:
                ((SearchCreatureHeader2View) obj).lambda$bindView$0();
                return;
            case 11:
                ((SearchMapHeaderView) obj).initMapFragment();
                return;
            case 12:
                ((DirectorsViewHandler) obj).lambda$updateDirectorsUi$2();
                return;
            case 13:
                ((SamsungAccountManager) obj).notifyChange();
                return;
            case 14:
                ((RecordingOperation) obj).lambda$new$0();
                return;
            default:
                ((FileBrowseActivity) obj).onBackPressed();
                return;
        }
    }
}
