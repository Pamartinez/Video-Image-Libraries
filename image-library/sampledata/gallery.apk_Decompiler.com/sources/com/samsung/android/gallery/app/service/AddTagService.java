package com.samsung.android.gallery.app.service;

import V8.a;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.message.AddTagMsgMgr;
import com.samsung.android.gallery.module.tag.UserTagManager;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddTagService extends AbsProgressService {
    private static final boolean SUPPORT_TAG_EDITOR = Features.isEnabled(Features.SUPPORT_TAG_EDITOR);
    private int mBrokenImageCount = 0;
    private int mBrokenVideoCount = 0;
    private final ArrayList<ContentValues> mContentValues = new ArrayList<>();
    private AddTagMsgMgr.ErrorType mErrorType = AddTagMsgMgr.ErrorType.NONE;
    private final ArrayList<Long> mIdList = new ArrayList<>();
    private IntelligentSearchIndex mIntelligentSearch;
    private int mLastIndex = 0;
    private ArrayList<String> mSelectedTags;
    private String mTag;
    private int mTagAddedImageCount = 0;
    private int mTagAddedVideoCount = 0;
    private final Map<String, ArrayList<Long>> mTagIdMap = new HashMap();
    private Map<Long, ArrayList<String>> mTagMap;
    private int mTaggedCount = 0;
    private int mTotalCount = 0;

    public AddTagService() {
        super("AddTagService", "com.samsung.android.gallery.app.service.AddTagService");
    }

    private void addContentValue(long j2, boolean z, boolean z3) {
        this.mContentValues.add(TagEditApi.buildContentValues(j2, this.mTag));
        this.mIdList.add(Long.valueOf(j2));
        increaseAddedCount(z);
    }

    private void addContentValues(long j2, boolean z, ArrayList<String> arrayList) {
        this.mContentValues.addAll(TagEditApi.buildContentValuesArray(j2, arrayList));
        increaseAddedCount(z);
    }

    private void addTagIdMap(long j2, String str) {
        this.mTagIdMap.computeIfAbsent(str, new a(26)).add(Long.valueOf(j2));
    }

    private int getAvailableCount(List<String> list) {
        int i2;
        if (list != null) {
            i2 = list.size();
        } else {
            i2 = 0;
        }
        return 30 - i2;
    }

    private Cursor getGroupCursor(MediaItem mediaItem) {
        return DbCompatGroup.getGroupCursor(mediaItem);
    }

    private String getNotificationMessage() {
        int i2;
        if (this.mTotalCount == 1) {
            i2 = R.string.adding_tag;
        } else {
            i2 = R.string.adding_tags;
        }
        return getString(i2);
    }

    private void increaseAddedCount(boolean z) {
        if (z) {
            this.mTagAddedVideoCount++;
        } else {
            this.mTagAddedImageCount++;
        }
    }

    private boolean isAddAllNewMultipleTag() {
        ArrayList<String> arrayList = this.mSelectedTags;
        if (arrayList == null || arrayList.size() == 1 || this.mSelectedTags.size() != this.mTagIdMap.size() || !this.mTagIdMap.keySet().containsAll(this.mSelectedTags)) {
            return false;
        }
        ArrayList arrayList2 = this.mTagIdMap.get(this.mSelectedTags.get(0));
        for (int i2 = 1; i2 < this.mSelectedTags.size(); i2++) {
            if (!arrayList2.equals(this.mTagIdMap.get(this.mSelectedTags.get(i2)))) {
                return false;
            }
        }
        return true;
    }

    private boolean isBroken(FileItemInterface fileItemInterface) {
        if (!fileItemInterface.isBroken()) {
            return false;
        }
        if (fileItemInterface.isImage()) {
            this.mBrokenImageCount++;
        } else {
            this.mBrokenVideoCount++;
        }
        setErrorType(AddTagMsgMgr.ErrorType.BROKEN);
        return true;
    }

    private boolean isContains(List<String> list, String str) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (String equalsIgnoreCase : list) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$addTagIdMap$0(String str) {
        return new ArrayList();
    }

    private void operate(long j2, boolean z, boolean z3) {
        List list = this.mTagMap.get(Long.valueOf(j2));
        if (list == null || list.isEmpty()) {
            addContentValue(j2, z, !z3);
        } else if (list.size() >= 30) {
            setErrorType(AddTagMsgMgr.ErrorType.LIMITED);
        } else if (isContains(list, this.mTag)) {
            setErrorType(AddTagMsgMgr.ErrorType.PRESENT);
        } else {
            addContentValue(j2, z, !z3);
        }
        updateTag(z3);
    }

    private void operateTagEditor(long j2, boolean z, boolean z3) {
        List list = this.mTagMap.get(Long.valueOf(j2));
        int availableCount = getAvailableCount(list);
        if (availableCount <= 0) {
            setErrorType(AddTagMsgMgr.ErrorType.LIMITED);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<String> arrayList2 = this.mSelectedTags;
        if (arrayList2 != null) {
            Iterator<String> it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (!isContains(list, next)) {
                    addTagIdMap(j2, next);
                    arrayList.add(next);
                    if (arrayList.size() >= availableCount) {
                        setErrorType(AddTagMsgMgr.ErrorType.LIMITED);
                        break;
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            setErrorType(AddTagMsgMgr.ErrorType.PRESENT);
            return;
        }
        addContentValues(j2, z, arrayList);
        updateTag(j2, z3, arrayList);
    }

    private void searchIndexing() {
        ArrayList<String> arrayList = this.mSelectedTags;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (isAddAllNewMultipleTag()) {
                this.mIntelligentSearch.indexing((Collection<Long>) this.mTagIdMap.get(this.mSelectedTags.get(0)), (Collection<String>) this.mSelectedTags, IntelligentSearchIndex.TagType.USER_TAG, IntelligentSearchIndex.IndexMode.APPEND);
            } else {
                Iterator<String> it = this.mSelectedTags.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    ArrayList arrayList2 = this.mTagIdMap.get(next);
                    if (!(arrayList2 == null || arrayList2.size() == 0)) {
                        this.mIntelligentSearch.indexing((Collection<Long>) arrayList2, next, IntelligentSearchIndex.TagType.USER_TAG, IntelligentSearchIndex.IndexMode.APPEND);
                    }
                }
            }
            this.mTagIdMap.clear();
        }
    }

    private void setErrorType(AddTagMsgMgr.ErrorType errorType) {
        if (this.mErrorType == AddTagMsgMgr.ErrorType.NONE) {
            this.mErrorType = errorType;
        }
    }

    private void showResult() {
        String str;
        int i2 = this.mTagAddedImageCount;
        int i7 = this.mTagAddedVideoCount;
        int i8 = i2 + i7;
        if (i8 > 0) {
            str = AddTagMsgMgr.getSucceedToastMessage(this, i8, i2, i7);
        } else {
            str = AddTagMsgMgr.getFailedToastMessage(this, this.mErrorType, this.mBrokenImageCount, this.mBrokenVideoCount);
        }
        if (str != null) {
            Utils.showToast((Context) this, str);
        }
    }

    private void updateTag(boolean z) {
        int size = this.mContentValues.size();
        if (size >= 100 || (z && size > 0)) {
            long currentTimeMillis = System.currentTimeMillis();
            int bulkInsertMyTags = new TagEditApi().bulkInsertMyTags((ContentValues[]) this.mContentValues.toArray(new ContentValues[0]));
            this.mContentValues.clear();
            long currentTimeMillis2 = System.currentTimeMillis();
            UserTagManager.addTagData(this.mIdList, this.mTag);
            long currentTimeMillis3 = System.currentTimeMillis();
            this.mIntelligentSearch.indexing((Collection<Long>) this.mIdList, this.mTag, IntelligentSearchIndex.TagType.USER_TAG, IntelligentSearchIndex.IndexMode.APPEND);
            this.mIdList.clear();
            Log.d("AddTagService", "update tag [" + bulkInsertMyTags + "][" + size + "][" + (currentTimeMillis2 - currentTimeMillis) + "][" + (currentTimeMillis3 - currentTimeMillis2) + "][" + (System.currentTimeMillis() - currentTimeMillis3) + "]");
        }
    }

    public boolean addJobs(Intent intent) {
        Cursor groupCursor;
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w("AddTagService", "items are empty. adding failed.");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null && !isBroken(mediaItem)) {
                if (mediaItem.isGroupShot()) {
                    try {
                        groupCursor = getGroupCursor(mediaItem);
                        if (groupCursor != null) {
                            if (groupCursor.moveToFirst()) {
                                do {
                                    MediaItem create = MediaItemBuilder.create(groupCursor);
                                    int i2 = this.mLastIndex + 1;
                                    this.mLastIndex = i2;
                                    addToQueue(new ProgressJob(create, Integer.valueOf(i2)));
                                    arrayList.add(Long.valueOf(create.getFileId()));
                                } while (groupCursor.moveToNext());
                            }
                        }
                        if (groupCursor != null) {
                            groupCursor.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                } else {
                    int i7 = this.mLastIndex + 1;
                    this.mLastIndex = i7;
                    addToQueue(new ProgressJob(mediaItem, Integer.valueOf(i7)));
                    arrayList.add(Long.valueOf(mediaItem.getFileId()));
                }
            }
        }
        if (isQueueEmpty()) {
            Log.w("AddTagService", "queue is empty. adding failed.");
            return false;
        }
        this.mTotalCount = getQueueSize();
        this.mNotificationMessage = getNotificationMessage();
        this.mTagMap = new TagEditApi().queryTagListFromIds((Long[]) arrayList.toArray(new Long[0]));
        Log.d("AddTagService", "adding done [" + this.mTotalCount + "]");
        return true;
        throw th;
    }

    public void doJob(ProgressJob progressJob) {
        try {
            boolean z = true;
            this.mTaggedCount++;
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            int intValue = ((Integer) progressJob.getParam(1)).intValue();
            this.mNotificationTitle = mediaItem.getTitle();
            Log.d("AddTagService", "do job [" + this.mTaggedCount + "][" + this.mTotalCount + "]");
            this.mDialogHelper.updateDialog(this.mTaggedCount, this.mTotalCount, getPercentage());
            updateNotification();
            if (SUPPORT_TAG_EDITOR) {
                long fileId = mediaItem.getFileId();
                boolean isVideo = mediaItem.isVideo();
                if (intValue != this.mLastIndex) {
                    z = false;
                }
                operateTagEditor(fileId, isVideo, z);
            } else {
                long fileId2 = mediaItem.getFileId();
                boolean isVideo2 = mediaItem.isVideo();
                if (intValue != this.mLastIndex) {
                    z = false;
                }
                operate(fileId2, isVideo2, z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
            throw th;
        }
        ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
    }

    public void forceRefreshData() {
        BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, true);
    }

    public String getChannelName() {
        return getString(R.string.add_tag);
    }

    public int getPercentage() {
        int i2 = this.mTotalCount;
        if (i2 == 0) {
            return 0;
        }
        return (this.mTaggedCount * 100) / i2;
    }

    public void onCleanInternal() {
        this.mDialogHelper.dismissDialog();
        showResult();
        forceRefreshData();
        super.onCleanInternal();
    }

    public void onContinueInternal() {
        this.mDialogHelper.showDialog(getString(R.string.processing), this.mTaggedCount, this.mTotalCount, getPercentage());
    }

    public void onEndInternal() {
        updateTag(true);
        searchIndexing();
        super.onEndInternal();
    }

    public boolean onPrepareInternal(Intent intent) {
        this.mIntelligentSearch = IntelligentSearchIndex.getInstance();
        if (SUPPORT_TAG_EDITOR) {
            this.mSelectedTags = intent.getStringArrayListExtra("selected_tag");
        } else {
            String stringExtra = intent.getStringExtra("tag_name");
            if (TextUtils.isEmpty(stringExtra)) {
                Log.w("AddTagService", "wrong tag [" + stringExtra + "]");
                return false;
            }
            this.mTag = stringExtra.trim();
        }
        return super.onPrepareInternal(intent);
    }

    public void onStartInternal() {
        super.onStartInternal();
        DialogHelper dialogHelper = this.mDialogHelper;
        String string = getString(R.string.processing);
        int i2 = this.mTotalCount;
        dialogHelper.showDialog(string, 1, i2, 100 / i2);
    }

    private void updateTag(long j2, boolean z, ArrayList<String> arrayList) {
        int size = this.mContentValues.size();
        if (size >= 100 || (z && size > 0)) {
            long currentTimeMillis = System.currentTimeMillis();
            int bulkInsertMyTags = new TagEditApi().bulkInsertMyTags((ContentValues[]) this.mContentValues.toArray(new ContentValues[0]));
            this.mContentValues.clear();
            Log.d("AddTagService", "update tag", Logger.vt(Integer.valueOf(bulkInsertMyTags), Integer.valueOf(size), Long.valueOf(currentTimeMillis)));
        }
        UserTagManager.addTagData(j2, arrayList);
    }
}
