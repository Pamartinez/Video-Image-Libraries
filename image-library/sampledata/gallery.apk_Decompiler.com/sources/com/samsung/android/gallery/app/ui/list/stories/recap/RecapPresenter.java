package com.samsung.android.gallery.app.ui.list.stories.recap;

import B8.b;
import D3.i;
import D7.g;
import Da.f;
import G6.a;
import android.text.TextUtils;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.recap.IRecapView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.StoryRecapMenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapPresenter<V extends IRecapView> extends MvpBasePresenter<V> {
    protected final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new g(22, RecapPresenter.this));
        }
    };
    private final EventHandler mEventHandler;
    private MediaData mMediaData;
    private MediaData mParentData;

    public RecapPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        this.mEventHandler = v.getEventHandler();
    }

    private MediaItem findRecapItem(int i2) {
        MediaData mediaData;
        MediaData mediaData2 = this.mParentData;
        if (mediaData2 != null) {
            if ("location://story/albums".equals(mediaData2.getLocationKey())) {
                mediaData = this.mParentData.getChildMediaData("location://stories/category/transitory");
            } else {
                mediaData = this.mParentData;
            }
            if (mediaData != null) {
                return mediaData.getAllData().stream().filter(new b(i2, 2)).findFirst().orElse((Object) null);
            }
        }
        return null;
    }

    private String getDataLocationKey() {
        return ((IRecapView) this.mView).getLocationKey().replace("location://recap", "location://file");
    }

    private int getStoryId() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "id", -1);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$findRecapItem$1(int i2, MediaItem mediaItem) {
        if (i2 == MediaItemStory.getStoryId(mediaItem)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateNewBadgeState$3(MediaItem mediaItem) {
        StoryHelper.updateStoryViewed(getContext(), mediaItem, false);
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(1077, Integer.valueOf(mediaItem.getAlbumID())));
    }

    /* access modifiers changed from: private */
    public MediaItem loadMediaItemForShare(int i2) {
        MediaItem readCache = this.mMediaData.readCache(i2);
        Optional.ofNullable(getContext()).ifPresent(new a(readCache, 0));
        return readCache;
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        int i2;
        MediaData reopen = reopen();
        if (reopen == null || reopen.getCount() == 0) {
            ((IRecapView) this.mView).finish();
            if (reopen != null) {
                i2 = reopen.getCount();
            } else {
                i2 = -1;
            }
            Log.e((CharSequence) "RecapPresenter", "onDataChangedOnUi#finish", Integer.valueOf(i2));
        } else {
            Log.d("RecapPresenter", "onDataChangedOnUi", Integer.valueOf(reopen.getCount()));
            ((IRecapView) this.mView).onDataChangedOnUi();
        }
        updateNewBadgeState(getHeaderItem());
    }

    private void openMediaData() {
        this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(getDataLocationKey());
    }

    private void openParentData() {
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "from_location", "");
        if (!TextUtils.isEmpty(argValue)) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(argValue);
            this.mParentData = open;
            open.register(this.mDataChangeListener);
        }
        Log.d("RecapPresenter", "openParentData", this.mParentData, Boolean.valueOf(!TextUtils.isEmpty(argValue)));
    }

    private void prepareExtendedShareInternal() {
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET) {
            int realCount = this.mMediaData.getRealCount();
            ShareProvider.prepareExtendedShareList(getContext(), this.mBlackboard, new A5.a(10, this), realCount, 0, realCount, (Runnable) null);
        }
    }

    private MediaData reopen() {
        MediaItem findRecapItem = findRecapItem(getStoryId());
        if (findRecapItem == null) {
            return null;
        }
        BlackboardUtils.publishMediaItem(getBlackboard(), findRecapItem);
        this.mMediaData.reopen(getDataLocationKey());
        return this.mMediaData;
    }

    private void updateNewBadgeState(MediaItem mediaItem) {
        if (mediaItem != null && StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem))) {
            MediaItemStory.setStoryNotifyStatus(mediaItem, 1);
            ThreadUtil.postOnBgThreadDelayed(new f(27, this, mediaItem), 1000);
        }
    }

    public void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.close();
            this.mMediaData = null;
        }
        MediaData mediaData2 = this.mParentData;
        if (mediaData2 != null) {
            mediaData2.unregister(this.mDataChangeListener);
            this.mParentData.close();
            this.mParentData = null;
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create();
    }

    public MenuHandler createMenuHandler() {
        return new StoryRecapMenuHandler();
    }

    public int findHeaderItemPosition() {
        try {
            MediaItem headerItem = getHeaderItem();
            MediaData mediaData = this.mParentData;
            if (mediaData == null || headerItem == null) {
                return -1;
            }
            return mediaData.findPosition((long) MediaItemStory.getStoryId(headerItem));
        } catch (UnsupportedApiException unused) {
            Log.e("RecapPresenter", "findHeaderItemPosition exception");
            return -1;
        }
    }

    public MediaItem getHeaderItem() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            return null;
        }
        return this.mMediaData.read(0);
    }

    public MediaData getMediaData() {
        return this.mMediaData;
    }

    public void onDestroy() {
        super.onDestroy();
        closeMediaData();
    }

    public void onViewAttach() {
        super.onViewAttach();
        openMediaData();
        openParentData();
    }

    public void prepareExtendedShare() {
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET && !isDestroyed()) {
            MediaData mediaData = this.mMediaData;
            if (mediaData == null || mediaData.getRealCount() <= 0) {
                ShareProvider.clearExtendedShareList(((IRecapView) this.mView).getActivity());
                Log.w("RecapPresenter", "prepareExtendedShare fail due to no share data");
                return;
            }
            prepareExtendedShareInternal();
            Log.w((CharSequence) "RecapPresenter", "prepareExtendedShare", Integer.valueOf(this.mMediaData.getRealCount()));
        }
    }

    public void prepareOptionsMenu(Menu menu) {
        super.prepareOptionsMenu(menu);
        MenuCompat.setGroupDividerEnabled(menu, true);
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle((CharSequence) Optional.ofNullable(getHeaderItem()).map(new i(28)).orElse(""));
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }

    public void handlePostEvent(Event event, Object... objArr) {
    }
}
