package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import C3.C0391a;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.story.SaveManageContentsCmd;
import com.samsung.android.gallery.app.controller.story.ShareStoryToAlbumCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.abstraction.ListViewServiceBixby;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.headeritem.HeaderItem;
import com.samsung.android.gallery.app.ui.list.stories.headeritem.HeaderItemBuilder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.IStoryHighlightListV2View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.Mode;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.menu.AbsMenuDelegator;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.menu.MenuDelegatorOneUi61;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.abstraction.StoryItemCuration;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import k6.c;
import k6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListV2Presenter<V extends IStoryHighlightListV2View> extends PicturesPresenter<V> {
    private AbsMenuDelegator mMenuDelegator;
    private HeaderItem mStoryHeaderItem;

    public StoryHighlightListV2Presenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        this.mBixbyProxy = new ListViewServiceBixby(this);
        createMenuBinder(v);
    }

    private void closeStoryHeaderItem() {
        HeaderItem headerItem = this.mStoryHeaderItem;
        if (headerItem != null) {
            headerItem.close();
            this.mStoryHeaderItem = null;
        }
    }

    private AbsMenuDelegator createMenuBinder(V v) {
        if (this.mMenuDelegator == null) {
            this.mMenuDelegator = new MenuDelegatorOneUi61(v, this);
        }
        return this.mMenuDelegator;
    }

    private int getMaxEditCurationCount() {
        int i2;
        MediaData mediaData = ((IStoryHighlightListV2View) this.mView).getMediaData((String) null);
        if (mediaData == null) {
            return StoryItemCuration.MAX_CURATION;
        }
        ArrayList<MediaItem> fullData = mediaData.getFullData();
        if (fullData != null) {
            i2 = fullData.size();
        } else {
            i2 = 0;
        }
        if (!PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 || !isTrip()) {
            return Math.min(i2, StoryItemCuration.MAX_CURATION);
        }
        return i2;
    }

    private void handleEditCurationDone() {
        StoryHighlightListV2Adapter<IStoryHighlightListV2View> adapter = ((IStoryHighlightListV2View) this.mView).getAdapter();
        if (Mode.EDIT_CURATION.equals(adapter.getMode())) {
            new SaveManageContentsCmd().execute(this, adapter.getShowItems(), adapter.getHideItems());
            ThreadUtil.postOnUiThreadDelayed(new e(this, 1), 100);
            return;
        }
        Log.e((CharSequence) this.TAG, "invalid action for curation", adapter.getMode());
    }

    private void handleEnterEditMode(int i2) {
        Mode mode;
        int i7;
        StoryHighlightListV2Adapter<IStoryHighlightListV2View> adapter = ((IStoryHighlightListV2View) this.mView).getAdapter();
        if (adapter != null) {
            if (i2 == 1124) {
                mode = Mode.EDIT_CURATION;
            } else {
                mode = Mode.REMOVE;
            }
            Mode mode2 = Mode.EDIT_CURATION;
            loggingEnterEditMode(mode2.equals(mode));
            reopenDataWithMode(false);
            if (mode2.equals(mode)) {
                i7 = getMaxEditCurationCount();
            } else {
                i7 = -1;
            }
            enterSelectionMode(i7);
            adapter.setMode(mode);
            this.mMenuDelegator.setMode(mode);
            Log.d(this.TAG, "change mode", mode);
        }
    }

    private void handleEnterSelectionMode() {
        StoryHighlightListV2Adapter<IStoryHighlightListV2View> adapter = ((IStoryHighlightListV2View) this.mView).getAdapter();
        if (adapter != null && Mode.CURATION.equals(adapter.getMode())) {
            Mode mode = Mode.SELECT;
            adapter.setMode(mode);
            this.mMenuDelegator.setMode(mode);
            if (!((IStoryHighlightListV2View) this.mView).isTouchOngoing()) {
                invalidateOptionsMenu();
            }
        }
    }

    private boolean isTrip() {
        return "location://stories/category/trip".equals(ArgumentsUtil.getArgValue(((IStoryHighlightListV2View) this.mView).getLocationKey(), "categoryKey"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEditCurationDone$2() {
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExitSelectionMode$1() {
        if (!isDestroyed()) {
            View bottomBar = getBottomBar();
            if (bottomBar instanceof BottomBar) {
                this.mMenuDelegator.resetBottomBar((BottomBar) bottomBar);
            }
        }
    }

    private void loggingEnterEditMode(boolean z) {
        if (z) {
            postAnalyticsLog(AnalyticsEventId.EVENT_STORY_MANAGE_CONTENTS);
        } else {
            postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_LIST_REMOVE_IMAGES_VIDEOS);
        }
    }

    /* access modifiers changed from: private */
    public void onHeaderUpdated(MediaItem mediaItem) {
        Log.d(this.TAG, "onHeaderUpdated", MediaItemUtil.getSimpleLog(mediaItem));
    }

    private void openStoryHeaderItem() {
        if (this.mStoryHeaderItem == null) {
            this.mStoryHeaderItem = new HeaderItemBuilder(this.mView).setHeaderUpdateListener(new c(this, 1)).build().open();
        }
    }

    private void reopenDataWithMode(boolean z) {
        String str;
        String dataLocationKey = ((IStoryHighlightListV2View) this.mView).getDataLocationKey();
        if (z) {
            str = "highlight_list_curation";
        } else {
            str = "highlight_list_full";
        }
        String appendArgs = ArgumentsUtil.appendArgs(dataLocationKey, "highlight_list_mode", str);
        getMediaData().reopen(appendArgs);
        Log.d(this.TAG, "reopenDataWithMode", appendArgs);
    }

    public MenuDataBinding createMenuDataBinding() {
        return this.mMenuDelegator.getMenuDataBinding();
    }

    public MenuHandler createMenuHandler() {
        return this.mMenuDelegator.getMenuHandler();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://StoryHighlightListViewChanged", new C0391a(29, this)).setWorkingOnUI());
    }

    public void enterSelectionMode(int i2) {
        GalleryListView listView = ((IStoryHighlightListV2View) this.mView).getListView();
        if (!((IStoryHighlightListV2View) this.mView).isViewActive() || listView == null) {
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "view is null or fragment inactive = " + listView);
            return;
        }
        ((IStoryHighlightListV2View) this.mView).enterSelectionMode(i2);
        selectOneItemOnEnterSelection();
    }

    public View getBottomBar() {
        return getActivity().findViewById(R.id.bottom_bar);
    }

    public MediaItem getHeaderItem() {
        HeaderItem headerItem = this.mStoryHeaderItem;
        if (headerItem != null) {
            return headerItem.getHeaderItem();
        }
        return null;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 1129) {
            switch (i2) {
                case 1124:
                case 1125:
                    handleEnterEditMode(i2);
                    return true;
                case 1126:
                    handleEditCurationDone();
                    return true;
                default:
                    return super.handleEvent(eventMessage);
            }
        } else {
            V v = this.mView;
            if (v != null) {
                if (!PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY || ((IStoryHighlightListV2View) v).getEventHandler() == null) {
                    new ChooseSharedAlbumCmd().execute(this, getSelectedItems());
                } else {
                    EventHandler eventHandler = ((IStoryHighlightListV2View) this.mView).getEventHandler();
                    new ShareStoryToAlbumCmd().execute(this, eventHandler.getEffectTheme().name(), eventHandler.getBgmExtraInfo(), eventHandler.getFilter().getRawName());
                }
            }
            return true;
        }
    }

    public void invalidateOptionsMenu() {
        this.mMenuDelegator.invalidateOptionMenu();
    }

    public void onDestroy() {
        super.onDestroy();
        View bottomBar = getBottomBar();
        if (bottomBar instanceof BottomBar) {
            this.mMenuDelegator.resetBottomBar((BottomBar) bottomBar);
        }
    }

    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        super.onEnterSelectionMode(obj, bundle);
        handleEnterSelectionMode();
    }

    public void onExitSelectionMode(Object obj, Bundle bundle) {
        super.onExitSelectionMode(obj, bundle);
        AbsMenuDelegator absMenuDelegator = this.mMenuDelegator;
        Mode mode = Mode.CURATION;
        absMenuDelegator.setMode(mode);
        ((IStoryHighlightListV2View) this.mView).getAdapter().setMode(mode);
        reopenDataWithMode(true);
        ThreadUtil.postOnUiThreadDelayed(new e(this, 0), 200);
    }

    public void onItemSelected(boolean z) {
        if (Mode.REMOVE.equals(((IStoryHighlightListV2View) this.mView).getAdapter().getMode()) && z) {
            if (isSelectAll()) {
                Utils.showToast(getContext(), (int) R.string.cannot_remove_all_items_from_story, 0);
            } else if (((IStoryHighlightListV2View) this.mView).getAdapter().isSelectedEntireShowItems()) {
                Utils.showToast(getContext(), (int) R.string.cannot_remove_everything_shown_in_story, 0);
            }
        }
    }

    public void onViewAttach() {
        super.onViewAttach();
        openStoryHeaderItem();
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mMenuDelegator.setToolbar(getToolbar());
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        closeStoryHeaderItem();
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return createMenuBinder(v);
    }

    public void notifyDataChanged(MediaData mediaData) {
    }

    public void prepareBottomMenu(Menu menu) {
    }

    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }
}
