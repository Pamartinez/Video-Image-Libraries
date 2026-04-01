package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot;

import A4.A;
import A4.C0372g;
import A4.C0384t;
import L5.b;
import android.database.Cursor;
import android.graphics.RectF;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupShotItemLoader extends AbsGroupItemLoader implements FragmentLifeCycle, GroupLoader {
    private final String mDbKey;
    private final GroupType mGroupType;
    protected final AtomicBoolean mLoading = new AtomicBoolean(false);

    public GroupShotItemLoader(String str, GroupType groupType) {
        this.mDbKey = str;
        this.mGroupType = groupType;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCursor$2(MediaItem mediaItem, QueryParams queryParams) {
        queryParams.setGroupTypes(this.mGroupType).setGroupMediaFilter(mediaItem.getBucketID(), mediaItem.getGroupMediaId());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindInternal$0() {
        MediaItem representativeItem = this.mModel.getRepresentativeItem();
        if (representativeItem != null) {
            loadGroupShotMediaItems(representativeItem);
        } else {
            Log.e(this.TAG, "cannot loadGroupShotMediaItems. mediaitem is null");
        }
    }

    public Cursor getCursor(MediaItem mediaItem) {
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !mediaItem.isPrivateItem()) {
            return DbCompat.query(this.mDbKey, new A(7, (Object) this, (Object) mediaItem));
        }
        return PrivateDatabase.getInstance().queryGroup(mediaItem.getBucketID(), this.mGroupType.value, mediaItem.getGroupMediaId());
    }

    public GroupLoader.SubItemsInfo loadSubItems(MediaItem mediaItem, long j2) {
        GroupLoader.SubItemsInfo subItemsInfo = new GroupLoader.SubItemsInfo();
        if (mediaItem == null) {
            return subItemsInfo;
        }
        long fileId = mediaItem.getFileId();
        subItemsInfo.mBestItemIndex = -1;
        this.mLoading.set(true);
        this.mModel.setGroupItemLoading(true);
        Cursor cursor = getCursor(mediaItem);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    RectF rectF = (RectF) Optional.ofNullable(getBestItem()).map(new C0384t(25)).orElse((Object) null);
                    do {
                        MediaItem load = MediaItemLoader.load(cursor);
                        load.setCropRect(rectF);
                        if (load.getFileId() == fileId) {
                            subItemsInfo.mCurrentMediaItem = load;
                            subItemsInfo.mBestItemIndex = subItemsInfo.mSubItemList.size();
                        }
                        if (load.getFileId() == j2) {
                            subItemsInfo.mCurrentMediaItem = load;
                            subItemsInfo.mCurrentIndex = subItemsInfo.mSubItemList.size();
                        }
                        subItemsInfo.mSubItemList.add(load);
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    } while (!this.mLoading.get());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        if (this.mLoading.get()) {
            setCurrentIndex(subItemsInfo);
            this.mLoading.set(false);
            this.mModel.setGroupItemLoading(false);
            return subItemsInfo;
        }
        Log.d(this.TAG, "loading canceled");
        return new GroupLoader.SubItemsInfo();
        throw th;
    }

    public void onBindInternal() {
        int intValue = ((Integer) Optional.ofNullable(this.mModel.getRepresentativeItem()).map(new b(17)).orElse(0)).intValue();
        int subMediaItemCount = this.mModel.getSubMediaItemCount();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onBind " + intValue + " / " + subMediaItemCount);
        if (intValue != subMediaItemCount) {
            this.mThread.runOnBgThread(new C0372g(18, this));
        }
    }
}
