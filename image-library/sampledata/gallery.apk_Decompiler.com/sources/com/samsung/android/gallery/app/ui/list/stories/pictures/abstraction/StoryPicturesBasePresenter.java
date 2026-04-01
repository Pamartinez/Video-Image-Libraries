package com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction;

import A4.C0381p;
import I5.e;
import N8.c;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.creature.EditCreatureNameCmd;
import com.samsung.android.gallery.app.controller.story.AddToStoryCmd;
import com.samsung.android.gallery.app.controller.story.RenameStoryCmd;
import com.samsung.android.gallery.app.controller.story.StartHighlightPlayerCmd;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesBaseView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.sum.core.message.Message;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import z5.f;
import z5.l;
import z6.C0543a;
import z6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryPicturesBasePresenter<V extends IStoryPicturesBaseView> extends PicturesPresenter<V> implements StoryHeaderViewListener {
    protected MediaItem mHeaderItem;
    /* access modifiers changed from: private */
    public MediaData mStoriesMediaData;
    private final MediaData.OnDataChangeListener mStoriesMediaDataListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new b(StoryPicturesBasePresenter.this, 2));
        }
    };

    public StoryPicturesBasePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void forceReloadStoriesData() {
        ((IStoryPicturesBaseView) this.mView).getMediaData(getParentLocationKey()).reopen(getParentLocationKey());
    }

    private MediaItem getHeaderFromCache(int i2) {
        if (UnsafeCast.isInvalid(i2)) {
            return null;
        }
        return (MediaItem) this.mBlackboard.pop(LocationKey.getHeaderCacheKey("stories", i2));
    }

    /* access modifiers changed from: private */
    public int getHeaderId() {
        return UnsafeCast.toInt(ArgumentsUtil.getArgValue(getLocationKey(), "id"));
    }

    private int getHeaderPosition() {
        return UnsafeCast.toInt(ArgumentsUtil.getArgValue(getLocationKey(), Message.KEY_POSITION));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onAddItemFromPhotoEditor$5(Uri uri, ThreadPool.JobContext jobContext) {
        ArrayList arrayList = new ArrayList();
        Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new c(uri, 1));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.create(query));
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        if (arrayList.isEmpty()) {
            Log.w(this.TAG, "Edited image list is empty");
            return Boolean.TRUE;
        }
        new AddToStoryCmd().execute(this, this.mHeaderItem, arrayList.toArray(new MediaItem[arrayList.size()]), DbCompat.storyApi().getStoryAlbumContentsIds(MediaItemStory.getStoryId(this.mHeaderItem)));
        return Boolean.TRUE;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHeaderItemLoadingCompleted$3() {
        ((IStoryPicturesBaseView) this.mView).updateHeader(this.mHeaderItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$updateStoryNotifyStatus$2(ThreadPool.JobContext jobContext) {
        Context applicationContext = getApplicationContext();
        DbCompat.storyBadgeApi().updateNotifyStatusViewed(this.mHeaderItem.getAlbumID());
        StoryUpdateNotifier.getInstance().notifyStory(applicationContext, true);
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public void onAddItemFromPhotoEditor(Object obj, Bundle bundle) {
        Object[] objArr = (Object[]) obj;
        if (objArr != null && objArr.length >= 2) {
            MediaData mediaData = ((IStoryPicturesBaseView) this.mView).getMediaData((String) null);
            if (mediaData == null || mediaData.getCount() < 200) {
                MediaItem mediaItem = (MediaItem) objArr[0];
                Uri uri = (Uri) objArr[1];
                if (mediaItem == null) {
                    return;
                }
                if (uri != null || mediaItem.getAlbumID() == MediaItemStory.getStoryId(this.mHeaderItem)) {
                    ThreadPool.getInstance().submit(new e(11, this, uri));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onStoriesDataChangedOnUi() {
        if (((IStoryPicturesBaseView) this.mView).isDestroyed()) {
            Log.e(this.TAG, "destroyed");
        } else if (this.mStoriesMediaData.getCount() > 0 && getLocationKey() != null) {
            int headerPosition = getHeaderPosition();
            if (headerPosition < 0) {
                MediaItem readById = this.mStoriesMediaData.readById((long) getHeaderId());
                if (readById != null) {
                    onHeaderItemLoadingCompleted(readById);
                } else {
                    Log.d(this.TAG, "header is not found, wait next notify");
                }
            } else {
                this.mStoriesMediaData.readAsync(headerPosition, new l(this), (BooleanSupplier) null);
            }
            onStoriesDataChangedInternal();
        }
    }

    private void updateStoryNotifyStatus() {
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem != null && StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem))) {
            ThreadPool.getInstance().submit(new C0381p(19, this));
            MediaItemStory.setNewStoryLabel(this.mHeaderItem, false);
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://StoryPicturesViewChanged", new C0543a(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://user/PhotoEditor", new C0543a(this, 1)).setWorkingOnUI());
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || MediaItemStory.getStoryId(mediaItem) != MediaItemStory.getStoryId(mediaItem2) || MediaItemStory.hasStoryHighlightVideo(mediaItem) != MediaItemStory.hasStoryHighlightVideo(mediaItem2) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(MediaItemStory.getStoryTimeDuration(mediaItem), MediaItemStory.getStoryTimeDuration(mediaItem2)) || !TextUtils.equals(MediaItemStory.getStoryCoverRectRatio(mediaItem), MediaItemStory.getStoryCoverRectRatio(mediaItem2))) {
            return false;
        }
        return true;
    }

    public void forceReloadData() {
        forceReloadStoriesData();
        super.forceReloadData();
    }

    public MediaItem getHeaderItem() {
        return this.mHeaderItem;
    }

    public String getParentLocationKey() {
        return "location://story/albums";
    }

    public final MediaItem getStoryAlbumById(int i2) {
        MediaData mediaData = this.mStoriesMediaData;
        if (mediaData != null) {
            return mediaData.readById((long) i2);
        }
        return null;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1070) {
            Object[] objArr = (Object[]) eventMessage.obj;
            new EditCreatureNameCmd().execute(this, objArr[0], objArr[1]);
        } else if (i2 != 3001) {
            return super.handleEvent(eventMessage);
        } else {
            ((IStoryPicturesBaseView) this.mView).updateHeaderContents(getHeaderItem());
        }
        return true;
    }

    public void loadHeaderItem() {
        int headerPosition;
        if (PocFeatures.TBD.RECOVER_LAST_STACK) {
            loadHeaderItemAsync();
            return;
        }
        if (this.mHeaderItem == null && (headerPosition = getHeaderPosition()) >= 0) {
            this.mHeaderItem = this.mStoriesMediaData.read(headerPosition);
        }
        if (this.mHeaderItem == null) {
            int headerId = getHeaderId();
            MediaItem readById = this.mStoriesMediaData.readById((long) headerId);
            this.mHeaderItem = readById;
            if (readById == null) {
                this.mHeaderItem = getHeaderFromCache(headerId);
                Log.e(this.TAG, "StoryPicturePresenter header unavailable");
            }
        }
    }

    public void loadHeaderItemAsync() {
        if (this.mHeaderItem != null) {
            return;
        }
        if (this.mStoriesMediaData.isDataAvailable()) {
            int headerPosition = getHeaderPosition();
            if (headerPosition >= 0) {
                this.mHeaderItem = this.mStoriesMediaData.read(headerPosition);
            }
            if (this.mHeaderItem == null) {
                int headerId = getHeaderId();
                MediaItem readById = this.mStoriesMediaData.readById((long) headerId);
                this.mHeaderItem = readById;
                if (readById == null) {
                    this.mHeaderItem = getHeaderFromCache(headerId);
                    Log.e(this.TAG, "StoryPicturePresenter header unavailable");
                    return;
                }
                return;
            }
            return;
        }
        this.mStoriesMediaData.register(new MediaData.SimpleDataChangeListener(true) {
            public void onDataChanged() {
                int Q = StoryPicturesBasePresenter.this.getHeaderId();
                StoryPicturesBasePresenter storyPicturesBasePresenter = StoryPicturesBasePresenter.this;
                storyPicturesBasePresenter.mHeaderItem = storyPicturesBasePresenter.mStoriesMediaData.readById((long) Q);
                Log.e(StoryPicturesBasePresenter.this.TAG, "StoryPicturePresenter get header async");
            }
        });
    }

    public void notifyDataChanged(MediaData mediaData) {
        if (mediaData == null || mediaData.getCount() == 0) {
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        super.onEnterSelectionMode(obj, bundle);
        ((IStoryPicturesBaseView) this.mView).setEnabledHeader(false);
    }

    public void onExitSelectionMode(Object obj, Bundle bundle) {
        super.onExitSelectionMode(obj, bundle);
        ((IStoryPicturesBaseView) this.mView).setEnabledHeader(true);
    }

    public void onHeaderItemLoadingCompleted(MediaItem mediaItem) {
        MediaItem updatedHeaderItem = getUpdatedHeaderItem(mediaItem);
        boolean equalsItem = equalsItem(this.mHeaderItem, updatedHeaderItem);
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("onHeaderItemLoadingCompleted changed=");
        sb2.append(!equalsItem);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(MediaItemUtil.getDebugLog(mediaItem));
        Log.d(stringCompat, sb2.toString());
        if (equalsItem || mediaItem == null) {
            Log.e(this.TAG, "skip update header");
            return;
        }
        this.mHeaderItem = updatedHeaderItem;
        ThreadUtil.postOnUiThread(new b(this, 1));
    }

    public void onHighlightPlayClicked() {
        MediaItem headerItem = getHeaderItem();
        if (headerItem != null) {
            new StartHighlightPlayerCmd().execute(this, Integer.valueOf(headerItem.getAlbumID()));
        }
    }

    public void onTitleClicked() {
        new RenameStoryCmd().execute(this, getHeaderItem());
    }

    public void onTransitionEnd() {
        GalleryToolbar toolbar = ((IStoryPicturesBaseView) this.mView).getToolbar();
        if (toolbar != null) {
            toolbar.setAlpha(1.0f);
            toolbar.setNavigationOnClickListener(new f(2, this));
        }
        updateStoryNotifyStatus();
    }

    public void onViewAttach() {
        super.onViewAttach();
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(getParentLocationKey());
        this.mStoriesMediaData = open;
        open.register(this.mStoriesMediaDataListener);
        loadHeaderItem();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        MediaData mediaData = this.mStoriesMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mStoriesMediaDataListener);
            this.mStoriesMediaData.close();
            this.mStoriesMediaData = null;
        }
    }

    public void onViewResume() {
        super.onViewResume();
        ThreadUtil.postOnUiThreadDelayed(new b(this, 0), 200);
    }

    public void onStoriesDataChangedInternal() {
    }

    public MediaItem getUpdatedHeaderItem(MediaItem mediaItem) {
        return mediaItem;
    }
}
