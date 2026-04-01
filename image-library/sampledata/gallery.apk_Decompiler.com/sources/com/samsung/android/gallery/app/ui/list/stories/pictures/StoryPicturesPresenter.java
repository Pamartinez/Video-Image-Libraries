package com.samsung.android.gallery.app.ui.list.stories.pictures;

import a6.C0418a;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.widget.Toolbar;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.story.StorySaveCmd;
import com.samsung.android.gallery.app.controller.story.StoryShareCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.app.ui.list.stories.util.CoverHelper;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.StoryPicturesMenuHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import com.samsung.android.gallery.widget.bottom.BottomBarData;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import x6.f;
import y6.c;
import y6.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesPresenter<V extends IStoryPicturesView> extends StoryPicturesBasePresenter<V> {
    /* access modifiers changed from: private */
    public final AtomicBoolean mAnimating = new AtomicBoolean(false);
    private boolean mIsClickedShare;
    private boolean mIsSaving;
    /* access modifiers changed from: private */
    public Animation mOptionViewAnim;
    private ListViewHolder mOptionViewHolder;
    private MediaItem mPendingShareItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class StoryPicturesMenuUpdater extends ListMenuUpdater {
        public StoryPicturesMenuUpdater(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
            super(iBaseListView, iMenuDelegation);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$getBottomBarInitListener$1(String str, BottomBar bottomBar) {
            if (LocationKey.isStoryPictures(str)) {
                bottomBar.setBackgroundColor(bottomBar.getContext().getColor(R.color.memory_pictures_bottom_navigation_view_bg_color));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateOptionsMenuAction$0(MenuItem menuItem) {
            boolean z;
            if (isUpsm() || StoryPicturesPresenter.this.getSelectedItemCount() <= 0 || !getAddToSharedAlbumVisibility(StoryPicturesPresenter.this.getSelectedItemCount())) {
                z = false;
            } else {
                z = true;
            }
            menuItem.setVisible(z);
            menuItem.setEnabled(z);
        }

        private boolean supportPin() {
            return PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN;
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, com.samsung.android.gallery.widget.bottom.BottomBarData$InitListener] */
        public BottomBarData.InitListener getBottomBarInitListener() {
            return new Object();
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            boolean z;
            boolean z3;
            boolean z7;
            if (StoryPicturesPresenter.this.isSelectionMode()) {
                Optional.ofNullable(menu.findItem(R.id.action_add_to_shared_album)).ifPresent(new c(this));
            } else if (supportPin()) {
                MenuItem findItem = menu.findItem(R.id.action_pintotop);
                boolean z9 = false;
                if (findItem != null) {
                    if (MediaItemStory.getStoryFavorite(StoryPicturesPresenter.this.getHeaderItem()) <= 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    findItem.setVisible(z3);
                    if (MediaItemStory.getStoryFavorite(StoryPicturesPresenter.this.getHeaderItem()) <= 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    findItem.setEnabled(z7);
                }
                MenuItem findItem2 = menu.findItem(R.id.action_unpin);
                if (findItem2 != null) {
                    if (MediaItemStory.getStoryFavorite(StoryPicturesPresenter.this.getHeaderItem()) > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    findItem2.setVisible(z);
                    if (MediaItemStory.getStoryFavorite(StoryPicturesPresenter.this.getHeaderItem()) > 0) {
                        z9 = true;
                    }
                    findItem2.setEnabled(z9);
                }
            }
        }
    }

    public StoryPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void erasePendingShareEvent() {
        this.mPendingShareItem = null;
        PendingShare.clear();
    }

    private List<ListViewHolder> findOptionTargetViewHolder(ListViewHolder listViewHolder) {
        GalleryListView galleryListView;
        ArrayList arrayList = new ArrayList();
        V v = this.mView;
        if (v != null) {
            galleryListView = ((IStoryPicturesView) v).getListView();
        } else {
            galleryListView = null;
        }
        if (galleryListView != null) {
            int childAdapterPosition = galleryListView.getChildAdapterPosition(listViewHolder.getRootView());
            int min = Math.min(childAdapterPosition + 2, ((IStoryPicturesView) this.mView).getAdapter().getItemCount() - 1);
            for (int max = Math.max(0, childAdapterPosition - 2); max <= min; max++) {
                ListViewHolder listViewHolder2 = (ListViewHolder) galleryListView.findViewHolderForAdapterPosition(max);
                if (listViewHolder2 != null && listViewHolder2.getRootView().getY() == listViewHolder.getRootView().getY()) {
                    arrayList.add(listViewHolder2);
                }
            }
            return arrayList;
        }
        arrayList.add(listViewHolder);
        return arrayList;
    }

    private void finishOptionView() {
        ListViewHolder listViewHolder = this.mOptionViewHolder;
        if (listViewHolder != null) {
            onOptionViewSwiped(listViewHolder, false);
            this.mOptionViewHolder = null;
            this.mIsClickedShare = false;
        }
    }

    private boolean isTouchedOnOptionButton(ViewGroup viewGroup, ListViewHolder listViewHolder, MotionEvent motionEvent) {
        boolean z;
        View decoView = listViewHolder.getDecoView(55);
        if (decoView == null) {
            return false;
        }
        int i2 = 0;
        while (i2 < ((ViewGroup) decoView).getChildCount()) {
            try {
                View childAt = ((ViewGroup) ((ViewGroup) decoView).getChildAt(i2)).getChildAt(0);
                Rect rect = new Rect();
                childAt.getDrawingRect(rect);
                viewGroup.offsetDescendantRectToMyCoords(childAt, rect);
                if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    if (i2 == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.mIsClickedShare = z;
                    return true;
                }
                i2++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean isTouchedOnOptionView(ViewGroup viewGroup, ListViewHolder listViewHolder, MotionEvent motionEvent) {
        View decoView = listViewHolder.getDecoView(53);
        if (decoView == null) {
            return false;
        }
        Rect rect = new Rect();
        decoView.getDrawingRect(rect);
        viewGroup.offsetDescendantRectToMyCoords(decoView, rect);
        return rect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToSearchTagView$1(MediaItem mediaItem, int i2) {
        String storyChunkData = MediaItemStory.getStoryChunkData(mediaItem);
        String searchLocationKey = LocationKey.getSearchLocationKey("location://search/fileList/Keyword", storyChunkData);
        this.mBlackboard.post("command://MoveURL", ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(searchLocationKey, "sub", storyChunkData), "title", TranslationManager.getInstance().getTranslatedString("location://search/fileList/Category/Scene", storyChunkData)), "term", "scenetag"));
        StringCompat stringCompat = this.TAG;
        StringBuilder o2 = C0086a.o(i2, "moveToSearchTagView {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        o2.append(!TextUtils.isEmpty(storyChunkData));
        o2.append("}");
        Log.d(stringCompat, o2.toString());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToStorySpotifySlideShow$0(MediaItem mediaItem) {
        long currentTimeMillis = System.currentTimeMillis();
        moveToStorySpotifySlideShowInternal(mediaItem, BitmapUtils.blur(getContext(), ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.SMALL_CROP_KIND)));
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "moveToStorySpotifySlideShow +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onOptionSaveSelected$3(List list) {
        new StorySaveCmd().execute(this, getFocusItemInCard(list), list, Boolean.FALSE);
        this.mIsSaving = true;
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_ITEM_CARD_OPTION_SAVE_STORY);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onOptionShareSelected$2(List list) {
        new StoryShareCmd().execute(this, getFocusItemInCard(list), list, Boolean.FALSE);
        this.mIsSaving = true;
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_ITEM_CARD_OPTION_SHARE_STORY);
    }

    private void moveToSearchLocationView(MediaItem mediaItem, int i2) {
        String storyChunkData = MediaItemStory.getStoryChunkData(mediaItem);
        String str = "location://search/fileList/Keyword/" + ArgumentsUtil.encode(storyChunkData);
        this.mBlackboard.erase(str);
        this.mBlackboard.post("command://MoveURL", ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str, "sub", storyChunkData), "title", storyChunkData));
        StringCompat stringCompat = this.TAG;
        StringBuilder o2 = C0086a.o(i2, "moveToSearchLocationView {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        o2.append(!TextUtils.isEmpty(storyChunkData));
        o2.append("}");
        Log.d(stringCompat, o2.toString());
    }

    private void moveToSearchTagView(MediaItem mediaItem, int i2) {
        SimpleThreadPool.getInstance().execute(new C0418a((Object) this, (Object) mediaItem, i2, 15));
    }

    private void moveToStorySpotifySlideShow(MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new f(4, this, mediaItem));
        BlackboardUtils.requestViewerBitmap(this.mBlackboard, mediaItem, true);
        postAnalyticsLog(AnalyticsEventId.EVENT_SHOW_MOTION_VIEW_STORY);
    }

    private void moveToStorySpotifySlideShowInternal(MediaItem mediaItem, Bitmap bitmap) {
        int albumID = mediaItem.getAlbumID();
        this.mBlackboard.publish(LocationKey.getHeaderCacheKey("data://user/storyBlurBitmap", String.valueOf(mediaItem.getFileId())), bitmap);
        this.mBlackboard.publish(LocationKey.getHeaderCacheKey("stories", albumID), mediaItem);
        this.mBlackboard.post("command://MoveURL", new UriBuilder(C0086a.i(albumID, "location://story/albums/spotify/")).appendArg("id", albumID).build());
    }

    /* access modifiers changed from: private */
    public void onViewResumeOnBg() {
        if (this.mPendingShareItem != null) {
            Log.d(this.TAG, "has pendingShareItem");
            new ShareViaCmd().execute(this, new MediaItem[]{this.mPendingShareItem}, null);
            erasePendingShareEvent();
        }
    }

    /* access modifiers changed from: private */
    public void saveHistory() {
        MediaItem headerItem;
        if (!PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 && (headerItem = getHeaderItem()) != null && !isDestroyed()) {
            LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.VIEW_STORY, Collections.singletonList(headerItem));
        }
    }

    private void slideOptionView(List<ListViewHolder> list, boolean z) {
        int i2;
        ListViewHolder listViewHolder = (ListViewHolder) C0086a.f(1, list);
        final View decoView = listViewHolder.getDecoView(53);
        final View decoView2 = listViewHolder.getDecoView(55);
        final View decoView3 = listViewHolder.getDecoView(54);
        if (decoView == null) {
            return;
        }
        if (decoView.getVisibility() == 0 && z) {
            return;
        }
        if (decoView.getVisibility() == 8 && !z) {
            return;
        }
        if (!z || !this.mAnimating.getAndSet(true)) {
            if (z) {
                if (list.size() > 1) {
                    int i7 = 0;
                    for (ListViewHolder listViewHolder2 : list) {
                        i7 += listViewHolder2.itemView.getWidth();
                    }
                    ViewGroup.LayoutParams layoutParams = decoView.getLayoutParams();
                    if (layoutParams.width != i7) {
                        layoutParams.width = i7;
                        decoView.setLayoutParams(layoutParams);
                    }
                }
                ViewUtils.setVisibility(decoView, 0);
                this.mOptionViewHolder = listViewHolder;
                this.mIsSaving = false;
                postAnalyticsLog(AnalyticsEventId.EVENT_SHOW_ITEM_CARD_OPTION_STORY);
            } else {
                Animation animation = this.mOptionViewAnim;
                if (animation != null) {
                    animation.cancel();
                    this.mOptionViewAnim = null;
                }
                this.mOptionViewHolder = null;
                postAnalyticsLog(AnalyticsEventId.EVENT_HIDE_ITEM_CARD_OPTION_STORY);
            }
            Context context = getContext();
            if (z) {
                i2 = R.anim.slide_in_from_end;
            } else {
                i2 = R.anim.slide_out_to_end;
            }
            Animation loadAnimation = AnimationUtils.loadAnimation(context, i2);
            this.mOptionViewAnim = loadAnimation;
            final boolean z3 = z;
            loadAnimation.setAnimationListener(new SimpleAnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    if (!z3) {
                        ViewUtils.setVisibility(decoView, 8);
                    }
                    StoryPicturesPresenter.this.mAnimating.set(false);
                    StoryPicturesPresenter.this.mOptionViewAnim = null;
                }

                public void onAnimationStart(Animation animation) {
                    decoView3.setX(decoView2.getX() + ((float) decoView.getWidth()));
                    decoView3.setY(decoView2.getY());
                }
            });
            decoView2.startAnimation(this.mOptionViewAnim);
        }
    }

    private void stopShareService(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.StoryShareService");
            intent.setAction("com.samsung.android.gallery.app.service.DELETE_SERVICE");
            context.startService(intent);
        }
    }

    public MenuHandler createMenuHandler() {
        return new StoryPicturesMenuHandler();
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        if (!super.equalsItem(mediaItem, mediaItem2) || mediaItem.getCount() != mediaItem2.getCount()) {
            z = false;
        } else {
            z = true;
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.R)) {
            return z;
        }
        if (!z || mediaItem.getDateModified() != mediaItem2.getDateModified()) {
            return false;
        }
        return true;
    }

    public MediaItem getFocusItemInCard(List<ListViewHolder> list) {
        for (ListViewHolder next : list) {
            if (((PreviewViewHolder) next).isPreviewing()) {
                return next.getMediaItem();
            }
        }
        return list.get(0).getMediaItem();
    }

    public MediaItem getUpdatedHeaderItem(MediaItem mediaItem) {
        if (!MediaItemStory.isCollageStory(mediaItem)) {
            return mediaItem;
        }
        MediaItem clone = mediaItem.clone();
        CoverHelper.changeAttributeOriginalCoverItem(clone);
        return clone;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        switch (eventMessage.what) {
            case 1082:
                V v = this.mView;
                if (v == null || !((IStoryPicturesView) v).isViewResumed()) {
                    Log.d(this.TAG, "save pendingShareItem");
                    this.mPendingShareItem = (MediaItem) eventMessage.obj;
                } else {
                    new ShareViaCmd().execute(this, new MediaItem[]{(MediaItem) eventMessage.obj}, null);
                    erasePendingShareEvent();
                    stopShareService(getContext());
                }
                return true;
            case 1083:
                this.mIsSaving = false;
                return true;
            case 1084:
                this.mIsSaving = false;
                if (!isDestroyed()) {
                    checkPreviewCandidate();
                }
                return true;
            default:
                return super.handleEvent(eventMessage);
        }
    }

    public boolean handleTouchEvent(ViewGroup viewGroup, MotionEvent motionEvent) {
        GalleryListView galleryListView;
        V v = this.mView;
        if (v != null) {
            galleryListView = ((IStoryPicturesView) v).getListView();
        } else {
            galleryListView = null;
        }
        if (this.mOptionViewHolder == null || galleryListView == null) {
            return false;
        }
        int findLastVisibleItemPositionCompat = galleryListView.findLastVisibleItemPositionCompat();
        for (int findFirstVisibleItemPositionCompat = galleryListView.findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
            ListViewHolder listViewHolder = (ListViewHolder) galleryListView.findViewHolderForAdapterPosition(findFirstVisibleItemPositionCompat);
            if (listViewHolder != null && this.mOptionViewHolder == listViewHolder) {
                if (isTouchedOnOptionButton(viewGroup, listViewHolder, motionEvent)) {
                    return false;
                }
                if (isTouchedOnOptionView(viewGroup, listViewHolder, motionEvent)) {
                    if (motionEvent.getAction() == 1) {
                        return true;
                    }
                    return false;
                }
            }
        }
        finishOptionView();
        return true;
    }

    public boolean isOptionViewOpened() {
        if (this.mOptionViewHolder != null) {
            return true;
        }
        return false;
    }

    public void loadHeaderItem() {
        super.loadHeaderItem();
        this.mHeaderItem = getUpdatedHeaderItem(this.mHeaderItem);
        GalleryToolbar toolbar = ((IStoryPicturesView) this.mView).getToolbar();
        if (toolbar != null) {
            updateToolbar(toolbar);
        }
    }

    public void moveStorySpotifySlideShow() {
        moveToStorySpotifySlideShow(getHeaderItem());
    }

    public void moveToSearchView(MediaItem mediaItem) {
        int storyChunkType = MediaItemStory.getStoryChunkType(mediaItem);
        if (storyChunkType == 3) {
            moveToSearchLocationView(mediaItem, storyChunkType);
        } else if (storyChunkType == 2) {
            moveToSearchTagView(mediaItem, storyChunkType);
        } else {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "invalid chunkType=" + storyChunkType);
        }
    }

    public boolean onBackPressed() {
        if (this.mOptionViewHolder != null) {
            finishOptionView();
            return true;
        } else if (!this.mIsSaving) {
            return false;
        } else {
            Log.d(this.TAG, "onBackPressed - Story card is being saved.");
            return true;
        }
    }

    public void onConfigurationChanged() {
        finishOptionView();
    }

    public boolean onHandleOptionMenu(ListViewHolder listViewHolder, int i2) {
        if (this.mOptionViewHolder != null) {
            if (i2 == 1003) {
                onOptionSaveSelected(listViewHolder);
                return true;
            } else if (this.mIsClickedShare || i2 == 1002) {
                onOptionShareSelected(listViewHolder);
                this.mIsClickedShare = false;
                return true;
            }
        }
        return false;
    }

    public void onOptionSaveSelected(ListViewHolder listViewHolder) {
        Log.d(this.TAG, "onOptionSaveSelected");
        List<ListViewHolder> findOptionTargetViewHolder = findOptionTargetViewHolder(listViewHolder);
        if (findOptionTargetViewHolder.size() == 0) {
            Log.d(this.TAG, "no viewHolder for save");
        }
        stopAllPreview(false);
        slideOptionView(findOptionTargetViewHolder, false);
        SimpleThreadPool.getInstance().execute(new d(this, findOptionTargetViewHolder, 0));
    }

    public void onOptionShareSelected(ListViewHolder listViewHolder) {
        Log.d(this.TAG, "onOptionShareSelected");
        List<ListViewHolder> findOptionTargetViewHolder = findOptionTargetViewHolder(listViewHolder);
        if (findOptionTargetViewHolder.size() == 0) {
            Log.d(this.TAG, "no viewHolder for share");
        }
        stopAllPreview(false);
        slideOptionView(findOptionTargetViewHolder, false);
        SimpleThreadPool.getInstance().execute(new d(this, findOptionTargetViewHolder, 1));
    }

    public void onOptionViewSwiped(ListViewHolder listViewHolder, boolean z) {
        if (!isSelectionMode()) {
            List<ListViewHolder> findOptionTargetViewHolder = findOptionTargetViewHolder(listViewHolder);
            if (findOptionTargetViewHolder.size() > 0) {
                slideOptionView(findOptionTargetViewHolder, z);
            }
        }
    }

    public void onRelatedItemClick(MediaItem mediaItem) {
        if (!isSelectionMode()) {
            getBlackboard().post("command://MoveURL", new UriBuilder(LocationKey.getStoryPicturesAliasKey() + "/" + MediaItemStory.getStoryId(mediaItem)).appendArg("id", MediaItemStory.getStoryId(mediaItem)).appendArg("fromNobody", true).build());
            postAnalyticsLog(AnalyticsEventId.EVENT_SHOW_RELATED_STORY);
        }
    }

    public void onStoriesDataChangedInternal() {
        V v = this.mView;
        if (v != null) {
            ((IStoryPicturesView) v).notifyStoriesDataChanged();
        }
    }

    public void onTransitionEnd() {
        super.onTransitionEnd();
        V v = this.mView;
        if (v != null) {
            ((IStoryPicturesView) v).onTransitionEnd();
        }
    }

    public void onViewResume() {
        super.onViewResume();
        ThreadUtil.postOnBgThread(new c(this, 0));
        ThreadUtil.postOnBgThreadDelayed(new c(this, 1), Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) null);
        if (!isSelectionMode()) {
            setNavigationUpButton(toolbar);
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new StoryPicturesMenuUpdater(v, this);
    }
}
