package com.samsung.android.gallery.app.ui.list.albums.pictures;

import A4.C0369d;
import Ab.b;
import C3.C0391a;
import C4.i;
import G6.a;
import J6.c;
import M4.r;
import N2.j;
import android.os.Bundle;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import com.samsung.android.gallery.widget.dialog.BiometricPromptCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPanePresenter<V extends IAlbumsBaseView> extends AlbumsBasePresenter<V> {
    private int mCurrentPosition = -1;

    public AlbumsPanePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        blackboard.erase("data://albums/moveTo/target");
    }

    /* access modifiers changed from: private */
    /* renamed from: findAndMoveToNewAlbum */
    public void lambda$moveToNewAlbum$4(MediaData mediaData, String str) {
        int bucketId = FileUtils.getBucketId(str);
        int albumPosition = getAlbumPosition(mediaData, bucketId);
        if (albumPosition < 0) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "findAndMoveToNewAlbum failed " + bucketId);
            return;
        }
        MediaItem read = mediaData.read(albumPosition);
        StringCompat stringCompat2 = this.TAG;
        Log.d(stringCompat2, "findAndMoveToNewAlbum " + bucketId + ArcCommonLog.TAG_COMMA + read);
        if (read != null) {
            this.mBlackboard.erase("data://albums/moveTo/new");
            refreshAlbum(read, albumPosition);
            this.mCurrentPosition = albumPosition;
            ThreadUtil.postOnUiThread(new r(this, albumPosition, 2));
        }
    }

    private int getAlbumPosition(MediaData mediaData, int i2) {
        try {
            return mediaData.findPosition((long) i2);
        } catch (UnsupportedApiException unused) {
            return -1;
        }
    }

    private String getRequestKey(MediaItem mediaItem) {
        String appendArgs = ArgumentsUtil.appendArgs(getRequestKeyWithType(mediaItem, "location://albums/fileList"), "count", Integer.toString(mediaItem.getCount()));
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return appendArgs;
        }
        if (BucketUtils.isFavourite(mediaItem.getAlbumID())) {
            appendArgs = ArgumentsUtil.appendArgs(appendArgs, "with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        }
        return ArgumentsUtil.appendArgs(appendArgs, "type", String.valueOf(mediaItem.getAlbumType().toInt()));
    }

    private void handleAlbum(int i2, MediaItem mediaItem) {
        IAlbumPicturesView iAlbumPicturesView;
        this.mBlackboard.erase("data://albums/moveTo/new");
        this.mCurrentPosition = i2;
        String appendArgs = ArgumentsUtil.appendArgs(getRequestKey(mediaItem), Message.KEY_POSITION, Integer.toString(i2));
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && (iAlbumPicturesView = (IAlbumPicturesView) ((IAlbumsBaseView) this.mView).getParentFragment()) != null) {
            iAlbumPicturesView.changeAlbum(appendArgs);
            refreshAlbum(mediaItem, i2);
            if (isSelectionMode()) {
                ((IAlbumsBaseView) this.mView).getToolbar().setCheckSelectAll(false);
            }
        } else if (BlackboardUtils.publishDataRequestForce(this.mBlackboard, appendArgs)) {
            Log.d(this.TAG, "album pictures data request on item click");
            refreshAlbum(mediaItem, i2);
            if (isSelectionMode()) {
                ((IAlbumsBaseView) this.mView).getToolbar().setCheckSelectAll(false);
            }
        } else {
            Log.w(this.TAG, "album pictures not ready.");
        }
    }

    private boolean isValidAlbum(MediaItem mediaItem) {
        if (mediaItem == null || FileUtils.isEmptyDummyImage(mediaItem.getPath()) || !FileUtils.exists(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToTargetAlbum$1(MediaData mediaData, MediaItem mediaItem) {
        int albumPosition = getAlbumPosition(mediaData, mediaItem.getAlbumID());
        refreshAlbum(mediaItem, albumPosition);
        this.mCurrentPosition = albumPosition;
        ThreadUtil.postOnUiThread(new r(this, albumPosition, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToTargetAlbum$3(MediaData mediaData, int i2) {
        int albumPosition = getAlbumPosition(mediaData, i2);
        if (albumPosition >= 0) {
            refreshAlbum(mediaData.read(albumPosition), albumPosition);
            this.mCurrentPosition = albumPosition;
            ThreadUtil.postOnUiThread(new r(this, albumPosition, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onListItemClickInternal$7(int i2, MediaItem mediaItem, Integer num) {
        if (num.intValue() == 0) {
            handleAlbum(i2, mediaItem);
        } else if (num.intValue() == 2) {
            handleAlbum(i2, mediaItem);
            BiometricPromptCompat.setupScreenLock(getActivity());
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$refreshAlbumSetting$6(MediaItem mediaItem, MediaItem mediaItem2) {
        Blackboard applicationInstance = Blackboard.getApplicationInstance();
        Integer valueOf = Integer.valueOf(mediaItem2.getAlbumID());
        if (mediaItem == null) {
            mediaItem = mediaItem2;
        }
        applicationInstance.post("global://album/setting/dataChanged", new Object[]{valueOf, mediaItem});
    }

    private boolean moveToNewAlbum(MediaData mediaData) {
        String str = (String) this.mBlackboard.read("data://albums/moveTo/new");
        if (str == null) {
            return false;
        }
        ThreadUtil.postOnBgThread(new c(this, mediaData, str, 12));
        return true;
    }

    private void moveToTargetAlbum(MediaData mediaData, MediaItem mediaItem) {
        ThreadUtil.postOnBgThread(new c(this, mediaData, mediaItem, 11));
    }

    /* access modifiers changed from: private */
    public void onMoveToNextAlbum(Object obj, Bundle bundle) {
        MediaItem mediaItem = null;
        MediaData mediaData = ((IAlbumsBaseView) this.mView).getMediaData((String) null);
        if (mediaData != null) {
            int i2 = this.mCurrentPosition;
            int count = mediaData.getCount();
            if (count == 0) {
                onNavigationPressed((View) null);
                return;
            }
            if (i2 >= count) {
                i2 = count - 1;
            }
            int i7 = i2;
            while (true) {
                if (i7 >= count) {
                    i7 = -1;
                    break;
                }
                MediaItem read = mediaData.read(i7);
                if (isValidAlbum(read)) {
                    mediaItem = read;
                    break;
                }
                i7++;
            }
            if (i7 < 0) {
                int i8 = i2 - 1;
                while (true) {
                    if (i8 < 0) {
                        break;
                    }
                    MediaItem read2 = mediaData.read(i8);
                    if (isValidAlbum(read2)) {
                        i7 = i8;
                        mediaItem = read2;
                        break;
                    }
                    i8--;
                }
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("onMoveToNextAlbum ");
            j.x(sb2, this.mCurrentPosition, " > ", i7, ArcCommonLog.TAG_COMMA);
            sb2.append(mediaItem);
            sb2.append(", c=");
            sb2.append(count);
            Log.d(stringCompat, sb2.toString());
            if (i7 >= 0) {
                this.mCurrentPosition = i7;
                refreshAlbum(mediaItem, i7);
                return;
            }
            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    private void refreshAlbum(MediaItem mediaItem, int i2) {
        if (mediaItem != null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "refreshAlbum " + mediaItem.getAlbumID());
            refreshAlbumSetting(mediaItem);
            String appendArgs = ArgumentsUtil.appendArgs(getRequestKey(mediaItem), Message.KEY_POSITION, Integer.toString(i2));
            this.mBlackboard.publish("data://albums/current", mediaItem);
            this.mBlackboard.postEvent(EventMessage.obtain(2001, appendArgs));
            this.mBlackboard.postEvent(EventMessage.obtain(2004, mediaItem.getAlbumID(), new Object[]{Boolean.TRUE, mediaItem.getPath(), mediaItem}));
            return;
        }
        Log.e(this.TAG, "refreshAlbum fail because item is null");
    }

    private void refreshAlbumSetting(MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            Optional.ofNullable((MediaItem) this.mBlackboard.read("data://albums/current")).ifPresent(new a(mediaItem, 2));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: scrollToPosition */
    public void lambda$moveToTargetAlbum$2(int i2) {
        int i7;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "scrollToPosition " + i2);
        if (((IAlbumsBaseView) this.mView).isPortrait()) {
            i7 = 2;
        } else {
            i7 = 1;
        }
        Optional.ofNullable(((IAlbumsBaseView) this.mView).getListView()).ifPresent(new C0369d(i2 - i7, 8));
    }

    public MenuDataBinding createMenuDataBinding() {
        return null;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://albums/moveTo/next", new C0391a(12, this)));
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 3008) {
            return true;
        }
        return super.handleEvent(eventMessage);
    }

    public void notifyDataChanged(MediaData mediaData) {
        int i2;
        super.notifyDataChanged(mediaData);
        MediaItem mediaItem = (MediaItem) this.mBlackboard.pop("data://albums/moveTo/target");
        Integer num = (Integer) this.mBlackboard.pop("data://albums/moveTo/rename");
        if (mediaItem != null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "notifyDataChanged " + MediaItemUtil.getDebugLog(mediaItem));
            moveToTargetAlbum(mediaData, mediaItem);
        } else if (num != null) {
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "notifyDataChanged " + num);
            moveToTargetAlbum(mediaData, num.intValue());
        } else if (!moveToNewAlbum(mediaData)) {
            MediaItem mediaItem2 = (MediaItem) this.mBlackboard.read("data://albums/current");
            if (mediaItem2 != null) {
                i2 = getAlbumPosition(mediaData, mediaItem2.getAlbumID());
            } else {
                i2 = -1;
            }
            StringCompat stringCompat3 = this.TAG;
            Log.d(stringCompat3, "notifyDataChanged " + MediaItemUtil.getDebugLog(mediaItem2) + ArcCommonLog.TAG_COMMA + this.mCurrentPosition + " > " + i2);
            if (i2 >= 0 && this.mCurrentPosition != i2) {
                this.mCurrentPosition = i2;
                lambda$moveToTargetAlbum$2(i2);
            }
            refreshAlbumSetting((MediaItem) null);
        }
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        IAlbumPicturesView iAlbumPicturesView = (IAlbumPicturesView) ((IAlbumsBaseView) this.mView).getParentFragment();
        if (iAlbumPicturesView != null) {
            iAlbumPicturesView.onAlbumPaneDataPrepared();
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        GalleryListView listView;
        IAlbumPicturesView iAlbumPicturesView = (IAlbumPicturesView) ((IAlbumsBaseView) this.mView).getParentFragment();
        if (iAlbumPicturesView != null && (listView = iAlbumPicturesView.getListView()) != null && listView.isOngoingPinchAnimation()) {
            Log.d(this.TAG, "onListItemClickInternal skip by pinch");
        } else if (!PocFeatures.SUPPORT_LOCKED_ALBUM || !MediaItemUtil.containsLockedAlbum(mediaItem)) {
            handleAlbum(i2, mediaItem);
        } else {
            new BiometricPromptCompat().setTitle(R.string.open_locked_album).setCallback(new i((Object) this, i2, (Object) mediaItem, 4)).authenticateCustom(getActivity());
        }
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        lambda$moveToTargetAlbum$2(this.mCurrentPosition);
    }

    private void moveToTargetAlbum(MediaData mediaData, int i2) {
        ThreadUtil.postOnBgThread(new b((Object) this, (Object) mediaData, i2, 24));
    }
}
