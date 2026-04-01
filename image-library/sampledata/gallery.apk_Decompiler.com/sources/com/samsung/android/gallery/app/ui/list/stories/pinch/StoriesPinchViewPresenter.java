package com.samsung.android.gallery.app.ui.list.stories.pinch;

import A4.C0385u;
import A4.W;
import Ab.b;
import C4.i;
import D7.g;
import F6.c;
import F6.d;
import F6.e;
import F6.f;
import Fa.C0571z;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.StoriesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.StoryData;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinchViewPresenter<V extends IStoriesPinchView> extends StoriesPresenter<V> {
    private long mDepthInSelectionAlbumId = -1;
    private boolean mPendingStoriesDataUpdate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoriesPinchMenuUpdater extends StoriesPresenter.StoriesMenuUpdater {
        private final IStoriesPinchView mStoriesView;
        private final boolean mSupportHeaderSelection;

        public StoriesPinchMenuUpdater(IStoriesPinchView iStoriesPinchView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
            super(iStoriesPinchView, iMenuDelegation);
            this.mStoriesView = iStoriesPinchView;
            this.mSupportHeaderSelection = supportHeaderSelection(iStoriesPinchView);
        }

        private boolean justOnePinItemSelected() {
            MediaItem[] selectedItems;
            MediaItem mediaItem;
            if (getSelectedItemCountForMenuUpdate() != 1 || (selectedItems = this.mInterface.getSelectedItems()) == null || selectedItems.length != 1 || (mediaItem = selectedItems[0]) == null) {
                return false;
            }
            return StoryType.isTransitoryType(MediaItemStory.getStoryType(mediaItem));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$0(MenuDataBinding.SelectionMode selectionMode) {
            if (!hasData() || MenuDataBinding.SelectionMode.SELECT.equals(selectionMode)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$1() {
            return false;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updatePopupMenuAction$2() {
            return !LocationKey.isStoriesFavorite(getLocationKey());
        }

        public MenuDataBinding.ItemMode getItemMode() {
            MenuDataBinding.ItemMode itemMode = super.getItemMode();
            if (!this.mSupportHeaderSelection || !itemMode.equals(MenuDataBinding.ItemMode.NO_ITEM) || !this.mStoriesView.hasData()) {
                return itemMode;
            }
            return MenuDataBinding.ItemMode.ANY_ITEM;
        }

        public boolean hasData() {
            if (getView().getDataCount() > 0) {
                return true;
            }
            return false;
        }

        public boolean supportHeaderSelection(IStoriesPinchView iStoriesPinchView) {
            return iStoriesPinchView.supportPinSelection();
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            super.updateOptionsMenuAction(menu, selectionMode);
            setMenuVisibility(menu, (int) R.id.action_favorite_view, (BooleanSupplier) new W(2, this, selectionMode));
            if (this.mSupportHeaderSelection && justOnePinItemSelected()) {
                setMenuVisibility(menu, (int) R.id.action_rename_story_album_in_list, (BooleanSupplier) new f(0));
            }
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            updateOptionsMenuAction(menu, selectionMode);
            setMenuVisibility(menu, (int) R.id.action_select, !MenuDataBinding.SelectionMode.SELECT.equals(selectionMode));
            setMenuVisibility(menu, (int) R.id.action_create_story_album, (BooleanSupplier) new C0385u(3, this));
        }
    }

    public StoriesPinchViewPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void enterDepthInSelectionMode() {
        if (!isSelectionMode() && isDepthInSelection() && this.mDepthInSelectionAlbumId != -1) {
            enterSelectionMode(0);
            if (((IStoriesPinchView) this.mView).getAdapter() instanceof StoriesPinchViewAdapter) {
                StoriesPinchViewAdapter storiesPinchViewAdapter = (StoriesPinchViewAdapter) ((IStoriesPinchView) this.mView).getAdapter();
                try {
                    int findPosition = ((IStoriesPinchView) this.mView).getMediaData((String) null).findPosition(this.mDepthInSelectionAlbumId);
                    this.mDepthInSelectionAlbumId = -1;
                    storiesPinchViewAdapter.onSelectOuter(storiesPinchViewAdapter.getViewPosition(findPosition), true);
                } catch (UnsupportedApiException unused) {
                }
            }
        }
    }

    private void exitSelectionIfNoData() {
        if (!isPickerMode() && isSelectionMode() && !((IStoriesPinchView) this.mView).hasData()) {
            ((IStoriesPinchView) this.mView).exitSelectionMode(false);
            ((IStoriesPinchView) this.mView).updateSelectionToolBar();
            Log.d(this.TAG, "no data - exit selection");
        }
    }

    private int getDepthInSelectionAlbum() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "id", -1);
    }

    private int getLowerBound(IStoriesPinchView iStoriesPinchView, GalleryListView galleryListView) {
        if (galleryListView == null || iStoriesPinchView == null || iStoriesPinchView.getView() == null) {
            return 0;
        }
        return galleryListView.getHeight();
    }

    private boolean isDepthInSelection() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "depth_in_selection", false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$scrollCompletelyVisible$3(boolean z, GalleryAppBarLayout galleryAppBarLayout, GalleryListView galleryListView, int i2, int i7) {
        if (z) {
            galleryAppBarLayout.setExpanded(false, false);
        }
        galleryListView.scrollToPositionWithOffset(i2, i7);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateStoryTheme$5(int i2, MediaItem mediaItem) {
        if (mediaItem.getAlbumID() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateStoryTheme$6(Object[] objArr, int i2, MediaItem mediaItem) {
        StoryData.of(mediaItem).setTheme(objArr[1].toString(), objArr[2].getRawName(), BgmExtraInfo.getBgmExtraInfoString(objArr[3]));
        Log.d(this.TAG, "story theme is updated", Integer.valueOf(i2));
    }

    private void moveToHighlight(int i2, MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new b((Object) this, i2, (Object) mediaItem, 12));
    }

    /* access modifiers changed from: private */
    /* renamed from: moveToHighlightInternal */
    public void lambda$moveToHighlight$0(int i2, MediaItem mediaItem) {
        int i7;
        boolean isStoriesFavorite = LocationKey.isStoriesFavorite(getLocationKey());
        StoryUriBuilder storyUriBuilder = new StoryUriBuilder(this.mBlackboard, (ThumbnailInterface) mediaItem);
        if (isStoriesFavorite) {
            i7 = -1;
        } else {
            i7 = i2;
        }
        String build = storyUriBuilder.withPosition(i7).withFavorite(isStoriesFavorite).withBlurBitmap().appendArg("story_trip_in_year", Boolean.valueOf(isStoryTripInYear())).build();
        IStoriesPinchView iStoriesPinchView = (IStoriesPinchView) getView();
        this.mBlackboard.post("command://MoveURL", build);
        this.mBlackboard.publish("data://story_transition_view_radius", new int[]{getTransitionViewRadius(iStoriesPinchView), 0});
        ThreadUtil.runOnUiThread(new b((Object) this, (Object) iStoriesPinchView, i2, 13));
        Log.d(this.TAG, "move to highlight id", Integer.valueOf(MediaItemStory.getStoryId(mediaItem)), Integer.valueOf(MediaItemStory.getStoryType(mediaItem)));
    }

    /* access modifiers changed from: private */
    /* renamed from: scrollCompletelyVisible */
    public void lambda$moveToHighlightInternal$1(IStoriesPinchView iStoriesPinchView, int i2) {
        BaseListViewAdapter baseListViewAdapter;
        GalleryListView galleryListView;
        int viewPosition;
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        boolean z;
        View view = null;
        if (iStoriesPinchView != null) {
            baseListViewAdapter = iStoriesPinchView.getAdapter();
        } else {
            baseListViewAdapter = null;
        }
        if (iStoriesPinchView != null) {
            galleryListView = iStoriesPinchView.getListView();
        } else {
            galleryListView = null;
        }
        if (iStoriesPinchView != null) {
            view = iStoriesPinchView.getView();
        }
        if (baseListViewAdapter != null && galleryListView != null && view != null && (findViewHolderForAdapterPosition = galleryListView.findViewHolderForAdapterPosition(viewPosition)) != null && findViewHolderForAdapterPosition.itemView.getHeight() > 0) {
            GalleryAppBarLayout appbarLayout = iStoriesPinchView.getAppbarLayout();
            int lowerBound = getLowerBound(iStoriesPinchView, galleryListView);
            float y = findViewHolderForAdapterPosition.itemView.getY();
            if (y < ((float) ((int) galleryListView.getY()))) {
                ThreadUtil.postOnUiThreadDelayed(new d(galleryListView, (viewPosition = baseListViewAdapter.getViewPosition(i2)), 0), (long) 500);
            } else if (((float) findViewHolderForAdapterPosition.itemView.getHeight()) + y > ((float) lowerBound)) {
                if (appbarLayout == null || appbarLayout.seslIsCollapsed()) {
                    z = false;
                } else {
                    z = true;
                }
                ThreadUtil.postOnUiThreadDelayed(new e(z, appbarLayout, galleryListView, viewPosition, (lowerBound - findViewHolderForAdapterPosition.itemView.getHeight()) - 10), (long) 500);
            } else if (appbarLayout != null && !appbarLayout.seslIsCollapsed() && y + ((float) findViewHolderForAdapterPosition.itemView.getHeight()) > ((float) (lowerBound - appbarLayout.getVisibleHeight()))) {
                ThreadUtil.postOnUiThreadDelayed(new g(7, appbarLayout), (long) 500);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setPendingStoriesDataUpdate(Object obj, Bundle bundle) {
        V v = this.mView;
        if (v == null) {
            return;
        }
        if (((IStoriesPinchView) v).isViewHidden()) {
            this.mPendingStoriesDataUpdate = true;
        } else if (!((IStoriesPinchView) this.mView).isDestroyed()) {
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), false);
            Log.d(this.TAG, "setPendingStoriesDataUpdate", Boolean.valueOf(((IStoriesPinchView) this.mView).isInMultiWindowMode()), Boolean.valueOf(((IStoriesPinchView) this.mView).isViewResumed()));
        }
    }

    /* access modifiers changed from: private */
    public void updateStoryTheme(Object obj, Bundle bundle) {
        Log.d(this.TAG, "updateStoryTheme");
        Object[] objArr = (Object[]) obj;
        if (StoryHelper.isValidObject(objArr, 4)) {
            int intValue = ((Integer) objArr[0]).intValue();
            Arrays.stream(getAllItems()).filter(new C0571z(4)).filter(new B8.b(intValue, 1)).findFirst().ifPresent(new i((Object) this, (Object) objArr, intValue, 2));
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://stories/defaultTheme/changed", new c(this, 0)));
        arrayList.add(new SubscriberInfo("global://stories/data_pendingUpdate", new c(this, 1)));
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(getLocationKey());
    }

    public int getTransitionViewRadius(IStoriesPinchView iStoriesPinchView) {
        Context context;
        if (isDestroyed() || (context = getContext()) == null) {
            return 0;
        }
        return iStoriesPinchView.getDimenHelper(context).getBorderRadius(iStoriesPinchView.getDepthFromGridSize(iStoriesPinchView.getListView().getColumnCount()), iStoriesPinchView.isDrawerOpen());
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1089) {
            return super.handleEvent(eventMessage);
        }
        ((IStoriesPinchView) this.mView).updateFavorite(eventMessage.arg1, eventMessage.arg2, ((Boolean) eventMessage.obj).booleanValue());
        return true;
    }

    public boolean isPickerMode() {
        return false;
    }

    public boolean isSelectAll() {
        if (isOnAdvancedMouseFocused()) {
            if (((IStoriesPinchView) this.mView).getAdapter().getAdvancedMouseFocusManager().getTotalCount() == ((IStoriesPinchView) this.mView).getTotalSelectableCount()) {
                return true;
            }
            return false;
        } else if (getSelectedItemCount() == ((IStoriesPinchView) this.mView).getTotalSelectableCount()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isStoryTripInYear() {
        return false;
    }

    public void notifyDataChanged(MediaData mediaData) {
        super.notifyDataChanged(mediaData);
        this.mPendingStoriesDataUpdate = false;
        enterDepthInSelectionMode();
        exitSelectionIfNoData();
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && this.mPendingStoriesDataUpdate) {
            StoryUpdateNotifier.getInstance().notifyStory(getApplicationContext(), true);
            this.mPendingStoriesDataUpdate = false;
            Log.d(this.TAG, "onHiddenChanged hasPending update");
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (!SharedTransition.isInReturnTransition(getBlackboard())) {
            stopAllPreview(false);
            moveToHighlight(i2, mediaItem);
            removeOtherTabs(getLocationKey());
        }
    }

    public void onListItemFavoriteClickInternal(View view, MediaItem mediaItem, int i2) {
        boolean z;
        if (!isSelectionMode()) {
            updateFavoriteInfo(mediaItem, i2);
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(mediaItem.getTitle());
            sb2.append(" favorite state : ");
            if (MediaItemStory.getStoryFavorite(mediaItem) > 0) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            Log.d(stringCompat, sb2.toString());
        }
    }

    public void onLocationKeyAssigned() {
        super.onLocationKeyAssigned();
        this.mDepthInSelectionAlbumId = (long) getDepthInSelectionAlbum();
    }

    public boolean setInputBlock(String str) {
        return setInputBlock(str, 400);
    }

    public boolean showDeleteAllWarning() {
        return isSelectAll();
    }

    public void updateFavoriteInfo(MediaItem mediaItem, int i2) {
        new UpdateStoryFavoriteCmd().execute(this, new MediaItem[]{mediaItem}, 0, Integer.valueOf(i2));
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new StoriesPinchMenuUpdater(v, this);
    }
}
