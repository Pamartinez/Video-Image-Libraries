package com.samsung.android.gallery.app.service;

import M8.c;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.groupshot.BurstShotFormat;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.gallery.module.groupshot.SingleTakenShotFormat;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveGroupShotService extends AbsProgressService {
    private MediaItem mBestItem = null;
    private boolean mDeleteRemain = false;
    private String mDialogTitle = null;
    private boolean mIsContainVideo = false;
    private MediaItem[] mItems = null;
    private int mSaveCount = 0;
    private int mSavedCount = 0;
    private int mTotalCount = 0;

    public SaveGroupShotService() {
        super("SaveGroupShotService", "com.samsung.android.gallery.app.service.SaveGroupShotService");
    }

    private void deleteGroupShot() {
        try {
            MediaItem mediaItem = this.mBestItem;
            if (mediaItem == null) {
                MediaItem[] mediaItemArr = this.mItems;
                if (mediaItemArr == null || mediaItemArr.length <= 0) {
                    mediaItem = null;
                } else {
                    mediaItem = mediaItemArr[0];
                }
            }
            if (mediaItem != null) {
                TrashDeleteHelper deleteHelper = TrashHelperFactory.getDeleteHelper(this);
                deleteHelper.deleteItem(mediaItem);
                deleteHelper.dump(getTrashLogType(), this.mLocationKey);
                deleteHelper.done();
                return;
            }
            Log.w("SaveGroupShotService", "unable to delete group shot.");
        } catch (Exception e) {
            Log.e("SaveGroupShotService", "unable to delete remained group shot. " + e.getMessage());
            e.printStackTrace();
        }
    }

    private GroupShotFormat getGroupShotFormat(MediaItem mediaItem) {
        if (mediaItem.isSingleTakenShot()) {
            return new SingleTakenShotFormat();
        }
        return new BurstShotFormat();
    }

    private double getLatitude(MediaItem mediaItem) {
        MediaItem mediaItem2 = this.mBestItem;
        if (mediaItem2 == null) {
            return mediaItem.getLatitude();
        }
        return mediaItem2.getLatitude();
    }

    private double getLongitude(MediaItem mediaItem) {
        MediaItem mediaItem2 = this.mBestItem;
        if (mediaItem2 == null) {
            return mediaItem.getLongitude();
        }
        return mediaItem2.getLongitude();
    }

    private String getNotificationMessage() {
        if (this.mTotalCount > 1) {
            return getString(R.string.saving_n_items, new Object[]{Integer.valueOf(this.mSaveCount), Integer.valueOf(this.mTotalCount)});
        }
        return getString(R.string.saving_one_item);
    }

    private ArrayList<String> getTagList(long j2) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = DbCompat.query("mp://myTag", new c(j2, 2));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(query.getString(query.getColumnIndex("__subCategory")));
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    private TrashLogType getTrashLogType() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash)) {
            return TrashLogType.MOVE_TO_TRASH_SINGLE;
        }
        return TrashLogType.DELETE_SINGLE;
    }

    private void saveItem(MediaItem mediaItem, GroupShotFormat groupShotFormat) {
        String str;
        Object obj;
        GroupShotFormat groupShotFormat2 = groupShotFormat;
        Object[] saveAsNew = groupShotFormat2.saveAsNew(this, mediaItem, getLatitude(mediaItem), getLongitude(mediaItem), FileUtils.getBaseName(mediaItem.getTitle()) + "_saved");
        if (saveAsNew == null || (obj = saveAsNew[0]) == null) {
            if (saveAsNew == null) {
                str = "[result null]";
            } else {
                str = "[result[0] null]";
            }
            Log.w("SaveGroupShotService", "unable to save group shot item. ".concat(str));
            return;
        }
        this.mSavedCount++;
        updateTags((Uri) obj);
    }

    private void showResult() {
        int i2;
        if (this.mSavedCount > 0) {
            Resources resources = getResources();
            if (this.mIsContainVideo) {
                i2 = R.plurals.item_saved;
            } else {
                i2 = R.plurals.picture_saved;
            }
            int i7 = this.mSavedCount;
            Utils.showThemeToast((Context) this, resources.getQuantityString(i2, i7, new Object[]{Integer.valueOf(i7)}));
            return;
        }
        Utils.showThemeToast((Context) this, (int) R.string.unable_to_save);
    }

    private void updateTags(Uri uri) {
        if (this.mBestItem != null) {
            new TagEditApi().insertMyTagArray(getTagList(this.mBestItem.getFileId()), ContentUris.parseId(uri), false);
        } else {
            Log.w("SaveGroupShotService", "unable to update tags.");
        }
    }

    public boolean addJobs(Intent intent) {
        int i2;
        this.mBestItem = (MediaItem) this.mBlackboard.pop("data://user/bestItem");
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        this.mItems = mediaItemArr;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w("SaveGroupShotService", "items are empty. adding failed.");
            return false;
        }
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                addToQueue(new ProgressJob(mediaItem));
            }
        }
        if (isQueueEmpty()) {
            Log.w("SaveGroupShotService", "queue is empty. adding failed.");
            return false;
        }
        int queueSize = getQueueSize();
        this.mTotalCount = queueSize;
        if (queueSize > 1) {
            i2 = R.string.saving_items;
        } else {
            i2 = R.string.saving_item;
        }
        this.mDialogTitle = getString(i2);
        return true;
    }

    public void doJob(ProgressJob progressJob) {
        try {
            this.mSaveCount++;
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            this.mNotificationTitle = mediaItem.getTitle();
            this.mNotificationMessage = getNotificationMessage();
            Log.d("SaveGroupShotService", "do job [" + this.mSaveCount + "][" + this.mTotalCount + "]");
            this.mDialogHelper.updateDialog(this.mSaveCount, this.mTotalCount, getPercentage());
            updateNotification();
            saveItem(mediaItem, getGroupShotFormat(mediaItem));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ((a) this.mJobCallback).a();
            throw th;
        }
        ((a) this.mJobCallback).a();
    }

    public String getChannelName() {
        return getString(R.string.save_image);
    }

    public int getPercentage() {
        int i2 = this.mTotalCount;
        if (i2 == 0) {
            return 0;
        }
        return (this.mSaveCount * 100) / i2;
    }

    public void onCleanInternal() {
        this.mDialogHelper.dismissDialog();
        showResult();
        super.onCleanInternal();
    }

    public void onContinueInternal() {
        this.mDialogHelper.showDialog(this.mDialogTitle, this.mSaveCount, this.mTotalCount, getPercentage());
    }

    public void onEndInternal() {
        if (this.mDeleteRemain) {
            deleteGroupShot();
            setChangeCollectOff();
            setCleanDelay(1000);
        }
        super.onEndInternal();
    }

    public boolean onPrepareInternal(Intent intent) {
        this.mDeleteRemain = intent.getBooleanExtra("is_delete_remain", false);
        this.mIsContainVideo = intent.getBooleanExtra("is_contain_video", false);
        return super.onPrepareInternal(intent);
    }

    public void onStartInternal() {
        super.onStartInternal();
        DialogHelper dialogHelper = this.mDialogHelper;
        String str = this.mDialogTitle;
        int i2 = this.mTotalCount;
        dialogHelper.showDialog(str, 1, i2, 100 / i2);
    }
}
