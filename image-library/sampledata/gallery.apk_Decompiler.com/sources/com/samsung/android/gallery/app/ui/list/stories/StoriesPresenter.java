package com.samsung.android.gallery.app.ui.list.stories;

import C4.j;
import O3.l;
import O3.y;
import Qb.e;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.story.StorySaveCmd;
import com.samsung.android.gallery.app.controller.story.StoryShareCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IPinView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.MenuFactory;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBasePresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPresenter<V extends IStoriesView> extends StoriesBasePresenter<V> {
    private final AtomicBoolean mIsDrawerSliding = new AtomicBoolean(false);
    private MediaItem mPendingShareItem;
    private MenuDataBinding mPopupMenuDataBinding;

    public StoriesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void changedDrawerState(boolean z) {
        this.mIsDrawerSliding.set(z);
        if (z) {
            if (!isDestroyed()) {
                stopAllPreview(false);
            }
        } else if (!isDestroyed()) {
            checkPreviewCandidate();
        }
    }

    private void erasePendingShareEvent() {
        this.mPendingShareItem = null;
        PendingShare.clear();
    }

    private MenuDataBinding getPopupMenuDataBinding() {
        if (this.mPopupMenuDataBinding == null) {
            this.mPopupMenuDataBinding = MenuFactory.createPopupMenu();
        }
        return this.mPopupMenuDataBinding;
    }

    private void moveToStoryPictures(int i2, MediaItem mediaItem) {
        int albumID = mediaItem.getAlbumID();
        String build = new UriBuilder(C0086a.i(albumID, "location://story/albums/fileList/")).appendArg("id", albumID).appendArg(Message.KEY_POSITION, i2).build();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "moveToStoryPictures id=" + albumID);
        this.mBlackboard.post("command://MoveURL", build);
    }

    /* access modifiers changed from: private */
    public void onViewResumeOnBg() {
        if (this.mPendingShareItem != null) {
            Log.d(this.TAG, "has pendingShareItem");
            new ShareViaCmd().execute(this, new MediaItem[]{this.mPendingShareItem}, null);
            erasePendingShareEvent();
        }
    }

    private boolean sameItem(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        boolean z3;
        if (mediaItem != null) {
            z = true;
        } else {
            z = false;
        }
        if (mediaItem2 != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if ((!z || !z3) || (mediaItem != mediaItem2 && !mediaItem.equals(mediaItem2))) {
            return false;
        }
        return true;
    }

    private void stopShareService(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.StoryShareService");
            intent.setAction("com.samsung.android.gallery.app.service.DELETE_SERVICE");
            context.startService(intent);
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create();
    }

    public MediaItem getCurrentItem() {
        return (MediaItem) getBlackboard().read("data://focused_item");
    }

    public List<ListViewHolder> getItemView(MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        int findFirstVisibleItemPositionCompat = ((IStoriesView) this.mView).getListView().findFirstVisibleItemPositionCompat();
        int findLastVisibleItemPositionCompat = ((IStoriesView) this.mView).getListView().findLastVisibleItemPositionCompat();
        while (findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat) {
            ListViewHolder listViewHolder = (ListViewHolder) ((IStoriesView) this.mView).getListView().findViewHolderForAdapterPosition(findFirstVisibleItemPositionCompat);
            if ((listViewHolder == null || !sameItem(listViewHolder.getMediaItem(), mediaItem)) && !(findFirstVisibleItemPositionCompat == findLastVisibleItemPositionCompat && arrayList.size() == 0)) {
                findFirstVisibleItemPositionCompat++;
            } else {
                arrayList.add(listViewHolder);
                return arrayList;
            }
        }
        return arrayList;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1084) {
            if (!isDestroyed()) {
                checkPreviewCandidate();
            }
            return true;
        } else if (i2 != 1112) {
            switch (i2) {
                case 1080:
                    stopAllPreview(false);
                    StorySaveCmd storySaveCmd = new StorySaveCmd();
                    Object obj = eventMessage.obj;
                    storySaveCmd.execute(this, obj, getItemView((MediaItem) obj), Boolean.TRUE);
                    return true;
                case 1081:
                    stopAllPreview(false);
                    StoryShareCmd storyShareCmd = new StoryShareCmd();
                    Object obj2 = eventMessage.obj;
                    storyShareCmd.execute(this, obj2, getItemView((MediaItem) obj2), Boolean.TRUE);
                    return true;
                case 1082:
                    V v = this.mView;
                    if (v == null || !((IStoriesView) v).isViewResumed()) {
                        Log.d(this.TAG, "save pendingShareItem");
                        this.mPendingShareItem = (MediaItem) eventMessage.obj;
                    } else {
                        new ShareViaCmd().execute(this, new MediaItem[]{(MediaItem) eventMessage.obj}, null);
                        erasePendingShareEvent();
                        stopShareService(getContext());
                    }
                    return true;
                default:
                    return super.handleEvent(eventMessage);
            }
        } else {
            changedDrawerState(SheetBehaviorCompat.isInTransition(((Integer) eventMessage.obj).intValue()));
            return true;
        }
    }

    public boolean isDrawerSliding() {
        return this.mIsDrawerSliding.get();
    }

    public void onExitSelectionMode(Object obj, Bundle bundle) {
        super.onExitSelectionMode(obj, bundle);
        IPinView pinView = ((IStoriesView) this.mView).getPinView();
        if (pinView != null) {
            pinView.show();
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        stopAllPreview(false);
        moveToStoryPictures(i2, mediaItem);
        removeOtherTabs(getLocationKey());
    }

    public void onListItemMenuClickInternal(View view, PointF pointF, MediaItem mediaItem) {
        if (setInputBlock(this.TAG + "_onListItemMenuClickInternal")) {
            Blackboard blackboard = this.mBlackboard;
            MenuDataBinding popupMenuDataBinding = getPopupMenuDataBinding();
            IStoriesView iStoriesView = (IStoriesView) this.mView;
            Objects.requireNonNull(iStoriesView);
            Optional.ofNullable(MenuFactory.createPopupMenu(blackboard, view, pointF, mediaItem, R.menu.menu_stories, popupMenuDataBinding, new y(26, iStoriesView))).ifPresent(new l(28));
            postAnalyticsLog(AnalyticsScreenId.SCREEN_EVENT_VIEW_NORMAL.toString(), AnalyticsEventId.EVENT_STORIES_ITEM_MENU);
        }
    }

    public void onViewResume() {
        super.onViewResume();
        ThreadUtil.postOnBgThread(new e(9, this));
    }

    public void updateLayout() {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            stopAllPreview(false);
            if (!isDestroyed() && !((IStoriesView) this.mView).isViewHidden()) {
                checkPreviewCandidate();
            }
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new StoriesMenuUpdater(v, this);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoriesMenuUpdater extends ListMenuUpdater {
        public StoriesMenuUpdater(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
            super(iBaseListView, iMenuDelegation);
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            boolean z;
            boolean z3;
            boolean z7 = true;
            if (getSelectedItemCountForMenuUpdate() == 1) {
                z = true;
            } else {
                z = false;
            }
            setMenuVisibility(menu, (int) R.id.action_rename_story_album_in_list, (BooleanSupplier) new j(z, 4));
            boolean z9 = PreferenceFeatures.OneUi40.SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE;
            if (!z9 || !z) {
                z3 = false;
            } else {
                z3 = true;
            }
            menu.setGroupVisible(R.id.select_mode, z3);
            if (!z9 || !z) {
                z7 = false;
            }
            menu.setGroupEnabled(R.id.select_mode, z7);
            menu.setGroupVisible(R.id.popup_mode, false);
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            boolean z = true;
            if (getSelectedItemCountForMenuUpdate() != 1) {
                z = false;
            }
            setMenuVisibility(menu, (int) R.id.action_rename_story_album_in_list, z);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$0(boolean z) {
            return z;
        }
    }
}
