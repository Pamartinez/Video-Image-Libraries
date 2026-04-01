package A2;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.appcompat.R$animator;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.fragment.app.strictmode.Violation;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.ConditionVariable;
import com.google.android.material.tabs.TabLayout;
import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.app.activity.external.PickerSelectionHandler;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListBottomHandler;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoiceAdapter;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewFragment;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderPeople;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderTitle;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedDataHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedStoryLoader;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.StoryRelatedView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.dynamicview.DynamicViewPlayerHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.aiedit.PortraitDetector;
import com.samsung.android.gallery.module.album.hide.AlbumsHideManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.service.RecapVideoMaker;
import com.samsung.android.gallery.plugins.motionphoto.Functions;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewHolder;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.function.Consumer;
import yc.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ d(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.f;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                com.google.android.material.tabs.d dVar = (com.google.android.material.tabs.d) obj;
                int i7 = TabLayout.d;
                dVar.setStateListAnimator(AnimatorInflater.loadStateListAnimator(((TabLayout) obj2).getContext(), R$animator.sesl_recoil_button_selector));
                dVar.getStateListAnimator().jumpToCurrentState();
                return;
            case 1:
                ((BaseListBottomHandler) obj2).lambda$updateListViewBottomPadding$2((GalleryListView) obj);
                return;
            case 2:
                BaseListDelegate.lambda$bindImage$0((Bitmap[]) obj2, (MediaItem) obj);
                return;
            case 3:
                ((BaseListFragment) obj2).lambda$initializeAdapter$0((BaseListViewAdapter) obj);
                return;
            case 4:
                ((ListViewHolder) obj2).bind((MediaItem) obj);
                return;
            case 5:
                ((ThumbnailPreviewDelegate) obj2).lambda$setPreviewTimer$1((PreviewViewHolder) obj);
                return;
            case 6:
                ((DynamicViewPlayerHandler) obj2).lambda$createDynamicViewPlayInfo$0((MediaItem) obj);
                return;
            case 7:
                ((PortraitDetector) obj2).lambda$releaseAsync$0((b) obj);
                return;
            case 8:
                ((RecapVideoMaker) obj2).lambda$createVideo$2((Consumer) obj);
                return;
            case 9:
                ((FastOptionItemView) obj2).lambda$dismissToolTip$0((CharSequence) obj);
                return;
            case 10:
                FragmentStrictMode.handlePolicyViolation$lambda$1((String) obj2, (Violation) obj);
                return;
            case 11:
                ((SearchRelationshipPreviewFragment) obj2).lambda$initRelationshipDelegate$1((PdcSearchDelegate) obj);
                return;
            case 12:
                ((StoryHeaderPeople) obj2).lambda$loadPeopleData$0((ArrayList) obj);
                return;
            case 13:
                ((StoryHeaderTitle) obj2).lambda$updateSubTitle$1((MediaItem) obj);
                return;
            case 14:
                ((StoryHeaderTitle) obj2).lambda$updateSubTitle$0((String) obj);
                return;
            case 15:
                ((Functions) obj2).lambda$capture$1((Bitmap) obj);
                return;
            case 16:
                ((Functions) obj2).lambda$share$4((Intent) obj);
                return;
            case 17:
                ((Functions) obj2).lambda$onDeleteConfirmed$7((String) obj);
                return;
            case 18:
                ((MotionPhotoViewHolder) obj2).lambda$bindView$2((MotionPhotoViewCompat) obj);
                return;
            case 19:
                ((Consumer) obj2).accept((MediaHelper.VideoInfo) obj);
                return;
            case 20:
                ((VideoCtrlView) obj2).lambda$updateSeekHandler$2((ViewGroup.MarginLayoutParams) obj);
                return;
            case 21:
                ((FilmStripVideoViewHolder) obj2).lambda$bindSearchView$3((MediaItem) obj);
                return;
            case 22:
                ((GalleryActivityHandler) obj2).lambda$onBixbyAction$7(obj);
                return;
            case 23:
                ((AlbumsBaseViewAdapter) obj2).lambda$setItemHeight$2((ListViewHolder) obj);
                return;
            case 24:
                ((AlbumsHideManager) obj2).lambda$hideAlbum$1((AlbumsHideManager.OnAlbumHideListener) obj);
                return;
            case 25:
                AudioManagerCompat.lambda$getAudioManager$0((Context) obj2, (ConditionVariable) obj);
                return;
            case 26:
                ((PickerSelectionHandler) obj2).lambda$startPrivateModeSetAsAlertDialog$1((MediaItem) obj);
                return;
            case 27:
                ((AlbumChoiceAdapter) obj2).lambda$onBindViewHolder$0((ListViewHolder) obj);
                return;
            case 28:
                ((RelatedStoryLoader.Requester) obj2).lambda$request$0((Consumer) obj);
                return;
            default:
                ((StoryRelatedView) obj2).lambda$loadData$0((RelatedDataHolder) obj);
                return;
        }
    }
}
