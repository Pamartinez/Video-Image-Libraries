package Ab;

import B2.C0003d;
import B2.l;
import B2.x;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.samsung.android.gallery.app.activity.DlnaActivity;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewFragment;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecommendationFragment;
import com.samsung.android.gallery.app.ui.list.search.recommendation.SearchMyTagAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegateV2;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderMapView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderPeopleViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryCoverViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesLegacyCoverViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesLegacyHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.DecorLayoutDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverAudioController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverVideoShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.GroupCountUi;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioEraserController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.FlipCoverVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.HighlightFrcHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoSaveClipHandler;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;
import com.samsung.android.gallery.settings.ui.SearchCustomViewHolder;
import com.samsung.android.gallery.settings.ui.SearchSettingFragment;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((FastOptionItemView) obj).lambda$initView$2(view);
                return;
            case 1:
                C0003d dVar = (C0003d) obj;
                EditText editText = dVar.f44i;
                if (editText != null) {
                    Editable text = editText.getText();
                    if (text != null) {
                        text.clear();
                    }
                    dVar.p();
                    return;
                }
                return;
            case 2:
                ((l) obj).t();
                return;
            case 3:
                x xVar = (x) obj;
                EditText editText2 = xVar.f;
                if (editText2 != null) {
                    int selectionEnd = editText2.getSelectionEnd();
                    EditText editText3 = xVar.f;
                    if (editText3 == null || !(editText3.getTransformationMethod() instanceof PasswordTransformationMethod)) {
                        xVar.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        xVar.f.setTransformationMethod((TransformationMethod) null);
                    }
                    if (selectionEnd >= 0) {
                        xVar.f.setSelection(selectionEnd);
                    }
                    xVar.p();
                    return;
                }
                return;
            case 4:
                ((StoryHeaderMapView) obj).onMapViewClicked(view);
                return;
            case 5:
                ((StoryHeaderPeopleViewHolder) obj).relatedPeopleThumbClicked(view);
                return;
            case 6:
                ((FlipCoverAudioController) obj).lambda$setAudioButton$1(view);
                return;
            case 7:
                ((FlipCoverVideoShotModeHandler) obj).onClick(view);
                return;
            case 8:
                ((VideoCtrlView) obj).lambda$new$0(view);
                return;
            case 9:
                ((DlnaActivity) obj).onNavigationPressed(view);
                return;
            case 10:
                ((RecommendationFragment) obj).lambda$setFlexBoxListView$1(view);
                return;
            case 11:
                ((SearchMyTagAdapter) obj).lambda$onBindViewHolder$1(view);
                return;
            case 12:
                ((GroupCountUi) obj).onSelectClicked(view);
                return;
            case 13:
                ((PanoramaActivity) obj).lambda$bindToolbar$5(view);
                return;
            case 14:
                ((FloatingRecommendationDelegate) obj).lambda$initHistoryList$0(view);
                return;
            case 15:
                ((FloatingRecommendationDelegateV2) obj).onNavigationClicked(view);
                return;
            case 16:
                ((StoryCoverViewHolder) obj).onClick(view);
                return;
            case 17:
                ((StoryPicturesLegacyCoverViewHolder) obj).lambda$bindView$0(view);
                return;
            case 18:
                ((StoryPicturesLegacyHeaderViewHolder) obj).lambda$bindView$0(view);
                return;
            case 19:
                ((LiveEffectVideoController) obj).lambda$onBind$2(view);
                return;
            case 20:
                ((MotionVideoController) obj).lambda$onBind$2(view);
                return;
            case 21:
                ((SearchCustomViewHolder) obj).onClickListItem(view);
                return;
            case 22:
                ((SearchSettingFragment) obj).lambda$onViewCreated$0(view);
                return;
            case 23:
                ((FolderViewFragment) obj).lambda$onEmptyViewVisibilityChanged$0(view);
                return;
            case 24:
                ((DecorLayoutDelegate) obj).lambda$initView$2(view);
                return;
            case 25:
                ((AudioController) obj).lambda$setAudioButton$5(view);
                return;
            case 26:
                ((AudioEraserController) obj).lambda$initAudioEraserButton$14(view);
                return;
            case 27:
                ((FlipCoverVideoController) obj).onPlayPauseViewClicked(view);
                return;
            case 28:
                ((HighlightFrcHandler) obj).lambda$initView$0(view);
                return;
            default:
                ((InstantSlowMoSaveClipHandler) obj).lambda$bindView$5(view);
                return;
        }
    }
}
