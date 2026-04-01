package com.samsung.android.gallery.module.suggested;

import A4.C0383s;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.local.SuggestedLocalApi;
import com.samsung.android.gallery.database.dal.mp.helper.RevitalizedApi;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.database.dbtype.SuggestedType;
import com.samsung.android.gallery.module.abstraction.RemasterSuggestGroup;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewParser;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewType;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import com.samsung.android.sum.core.types.NumericEnum;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import oa.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedLocalUpdater extends Subscriber {
    private final Blackboard mBlackBoard;
    private final WeakReference<Context> mContext;

    /* renamed from: com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType[] r0 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType = r0
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_STORY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_CLEAN_OUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_MOTION_PHOTO_CLIP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_BURST_SIMILAR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_DUPLICATED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_REVITALIZATION     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_HIGHLIGHT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.gallery.module.suggested.SuggestedUpdateType r1 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_PORTRAIT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UpdateItem {
        /* access modifiers changed from: private */
        public String coverRectRatio;
        /* access modifiers changed from: private */
        public int deleteType;
        /* access modifiers changed from: private */
        public int extra;
        /* access modifiers changed from: private */
        public String extraData;
        /* access modifiers changed from: private */
        public String extraValue;
        /* access modifiers changed from: private */
        public long fileId;
        /* access modifiers changed from: private */
        public int height;
        /* access modifiers changed from: private */
        public long mediaId;
        /* access modifiers changed from: private */
        public int mediaType;
        /* access modifiers changed from: private */
        public int orientation;
        /* access modifiers changed from: private */
        public String path;
        /* access modifiers changed from: private */
        public int storageType;
        /* access modifiers changed from: private */
        public int suggestedType;
        /* access modifiers changed from: private */
        public String title;
        /* access modifiers changed from: private */
        public long videoThumbStartTime;
        /* access modifiers changed from: private */
        public int width;

        public /* synthetic */ UpdateItem(int i2) {
            this();
        }

        private UpdateItem() {
            this.fileId = -1;
        }
    }

    public SuggestedLocalUpdater(Context context, Blackboard blackboard) {
        super(blackboard);
        this.mContext = new WeakReference<>(context);
        this.mBlackBoard = blackboard;
    }

    private boolean addBadQualityImageToLocalDb(HashMap<String, UpdateItem> hashMap) {
        return addCleanOutSuggestionToLocalDb(hashMap, 2);
    }

    private boolean addCleanOutBurstSimilarToLocalDb(HashMap<String, UpdateItem> hashMap) {
        return addCleanOutContentsToLocalDb(hashMap, new SuggestedBurstSimilar());
    }

    private boolean addCleanOutContentsToLocalDb(HashMap<String, UpdateItem> hashMap, SuggestedInterface suggestedInterface) {
        Cursor cleanOutContentsCoverCursor;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "start to addCleanOutContentsToLocalDb : " + suggestedInterface);
        UpdateItem updateItem = new UpdateItem(0);
        try {
            cleanOutContentsCoverCursor = suggestedInterface.getCleanOutContentsCoverCursor();
            if (cleanOutContentsCoverCursor != null) {
                if (cleanOutContentsCoverCursor.moveToFirst()) {
                    updateItem = buildMultiple(cleanOutContentsCoverCursor);
                    setSuggestedType(updateItem, suggestedInterface.getType().ordinal());
                }
            }
            if (cleanOutContentsCoverCursor != null) {
                cleanOutContentsCoverCursor.close();
            }
            return updateOperation(updateItem, hashMap, suggestedInterface.getType());
        } catch (SQLiteException e) {
            Log.e(this.TAG, e.getMessage());
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private boolean addCleanOutMotionPhotoToLocalDb(HashMap<String, UpdateItem> hashMap) {
        return addCleanOutContentsToLocalDb(hashMap, new SuggestedMotionPhoto());
    }

    private boolean addCleanOutSuggestionToLocalDb(HashMap<String, UpdateItem> hashMap, int i2) {
        Cursor cleanOutSuggestedCoverCursor;
        Log.d(this.TAG, "start to addCleanOutToLocalDb");
        UpdateItem updateItem = new UpdateItem(0);
        try {
            cleanOutSuggestedCoverCursor = DbCompat.suggestedApi().getCleanOutSuggestedCoverCursor(true, i2);
            if (cleanOutSuggestedCoverCursor != null) {
                if (showCleanOutCard(cleanOutSuggestedCoverCursor) && cleanOutSuggestedCoverCursor.moveToFirst()) {
                    updateItem = buildMultiple(cleanOutSuggestedCoverCursor);
                    setSuggestedType(updateItem, SuggestedUpdateType.TYPE_CLEAN_OUT.ordinal());
                }
            }
            if (cleanOutSuggestedCoverCursor != null) {
                cleanOutSuggestedCoverCursor.close();
            }
            return updateOperation(updateItem, hashMap, SuggestedUpdateType.TYPE_CLEAN_OUT);
        } catch (SQLiteException e) {
            Log.e(this.TAG, e.getMessage());
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private synchronized void addDataToLocalDb() {
        try {
            HashMap hashMap = new HashMap();
            getLegacyMap(hashMap);
            boolean addExpiredImageToLocalDb = addExpiredImageToLocalDb(hashMap);
            if (!PreferenceFeatures.OneUi41.DISABLE_CLEANOUT_BAD_QUALITY_IMAGE) {
                addExpiredImageToLocalDb |= addBadQualityImageToLocalDb(hashMap);
            }
            if (PreferenceFeatures.OneUi41.REMASTER_SUGGESTIONS) {
                addExpiredImageToLocalDb |= addRevitalizationToLocalDb(hashMap);
            }
            if (PreferenceFeatures.OneUi41.HIGHLIGHT_SUGGESTIONS) {
                addExpiredImageToLocalDb |= addHighlightToLocalDb(hashMap);
            }
            if (PreferenceFeatures.OneUi41.PORTRAIT_SUGGESTIONS) {
                addExpiredImageToLocalDb |= addPortraitToLocalDb(hashMap);
            }
            if (PreferenceFeatures.OneUi41.CLEANOUT_MOTION_PHOTO_CLIP) {
                addExpiredImageToLocalDb |= addCleanOutMotionPhotoToLocalDb(hashMap);
            }
            if (PreferenceFeatures.OneUi41.CLEANOUT_DUPLICATED_IMAGE) {
                addExpiredImageToLocalDb |= addDuplicatedImageToLocalDb(hashMap);
            }
            if (PocFeatures.isEnabled(PocFeatures.CleanOutBurstSimilar)) {
                addExpiredImageToLocalDb |= addCleanOutBurstSimilarToLocalDb(hashMap);
            }
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "addDataToLocalDb: bNeedUpdate is " + addExpiredImageToLocalDb);
            if (addExpiredImageToLocalDb) {
                this.mBlackBoard.postEvent(EventMessage.obtain(1032, 1, 0, "location://suggestions"));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private boolean addDuplicatedImageToLocalDb(HashMap<String, UpdateItem> hashMap) {
        return addCleanOutContentsToLocalDb(hashMap, new SuggestedDuplicatedImage());
    }

    private boolean addExpiredImageToLocalDb(HashMap<String, UpdateItem> hashMap) {
        return addCleanOutSuggestionToLocalDb(hashMap, 1);
    }

    private boolean addHighlightToLocalDb(HashMap<String, UpdateItem> hashMap) {
        return addCleanOutContentsToLocalDb(hashMap, new SuggestedHighlight());
    }

    private void addOperation(ContentProviderOperation contentProviderOperation, ArrayList<ContentProviderOperation> arrayList) {
        arrayList.add(contentProviderOperation);
        if (arrayList.size() >= 500) {
            updateInBatch(arrayList);
            arrayList.clear();
        }
    }

    private boolean addPortraitToLocalDb(HashMap<String, UpdateItem> hashMap) {
        return addCleanOutContentsToLocalDb(hashMap, new SuggestedPortrait());
    }

    private boolean addRevitalizationToLocalDb(HashMap<String, UpdateItem> hashMap) {
        Cursor revitalizedCoverCursor;
        Log.d(this.TAG, "start to addRevitalizationToLocalDb");
        UpdateItem updateItem = new UpdateItem(0);
        try {
            revitalizedCoverCursor = new RevitalizedApi().getRevitalizedCoverCursor();
            if (revitalizedCoverCursor != null) {
                if (revitalizedCoverCursor.getCount() == 0) {
                    SuggestedHelper.getInstance().updateAutoItem(13, 1, SuggestedType.REMASTER.toInt());
                } else {
                    updateItem = buildRevitalizeMultiple(revitalizedCoverCursor);
                    setSuggestedType(updateItem, SuggestedUpdateType.TYPE_REVITALIZATION.ordinal());
                }
            }
            if (revitalizedCoverCursor != null) {
                revitalizedCoverCursor.close();
            }
            return updateOperation(updateItem, hashMap, SuggestedUpdateType.TYPE_REVITALIZATION);
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private UpdateItem build(Cursor cursor) {
        UpdateItem updateItem = new UpdateItem(0);
        updateItem.fileId = getLongValue(cursor, cursor.getColumnIndex("__absID"), -1);
        updateItem.mediaId = getLongValue(cursor, cursor.getColumnIndex("__mediaId"), -1);
        updateItem.suggestedType = getValue(cursor, cursor.getColumnIndex("__Type"), -1);
        updateItem.title = getValue(cursor, cursor.getColumnIndex("__Title"), (String) null);
        updateItem.path = getValue(cursor, cursor.getColumnIndex("__data"), (String) null);
        updateItem.width = getValue(cursor, cursor.getColumnIndex("__width"), 0);
        updateItem.height = getValue(cursor, cursor.getColumnIndex("__height"), 0);
        updateItem.orientation = getValue(cursor, cursor.getColumnIndex("__orientation"), 0);
        updateItem.coverRectRatio = getValue(cursor, cursor.getColumnIndex("__rect"), (String) null);
        updateItem.mediaType = getValue(cursor, cursor.getColumnIndex("__mediaType"), 1);
        updateItem.storageType = getValue(cursor, cursor.getColumnIndex("__storageType"), 1);
        updateItem.extra = getValue(cursor, cursor.getColumnIndex(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA), -1);
        updateItem.extraData = getValue(cursor, cursor.getColumnIndex("extraData"), (String) null);
        updateItem.extraValue = getValue(cursor, cursor.getColumnIndex("extraValue"), (String) null);
        updateItem.deleteType = getValue(cursor, cursor.getColumnIndex("__deleteType"), -1);
        updateItem.videoThumbStartTime = (long) getValue(cursor, cursor.getColumnIndex("video_thumb_start_time"), 0);
        return updateItem;
    }

    private UpdateItem buildMultiple(Cursor cursor) {
        return buildMultiple(cursor, new ArrayList());
    }

    private UpdateItem buildRevitalizeMultiple(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                if (!RemasterSuggestGroup.NONE.equals(RemasterSuggestGroup.get(VslMesDetectorCompat.decodeEnhances(MediaItemSuggest.getRevitalizedType(MediaItemBuilder.create(cursor)), true)))) {
                    arrayList.add(Integer.valueOf(cursor.getPosition()));
                }
            } while (cursor.moveToNext());
        }
        if (arrayList.isEmpty()) {
            return new UpdateItem(0);
        }
        return buildMultiple(cursor, arrayList);
    }

    private ContentProviderOperation createDeleteOp(UpdateItem updateItem, boolean z) {
        long j2;
        ArrayList arrayList = new ArrayList();
        if (z) {
            j2 = updateItem.mediaId;
        } else {
            j2 = updateItem.fileId;
        }
        arrayList.add(Long.valueOf(j2));
        return new SuggestedLocalApi().getDeleteOperation(arrayList, z);
    }

    private ContentProviderOperation createInsertOp(UpdateItem updateItem) {
        return new SuggestedLocalApi().getInsertOperation(getSuggestedContentValues(updateItem, false));
    }

    private ContentProviderOperation createUpdateOp(UpdateItem updateItem, boolean z) {
        long j2;
        ArrayList arrayList = new ArrayList();
        if (z) {
            j2 = updateItem.mediaId;
        } else {
            j2 = updateItem.fileId;
        }
        arrayList.add(Long.valueOf(j2));
        return new SuggestedLocalApi().getUpdateOperation(getSuggestedContentValues(updateItem, true), arrayList, z);
    }

    private String getKey(long j2, int i2) {
        return j2 + "/" + i2;
    }

    private void getLegacyMap(HashMap<String, UpdateItem> hashMap) {
        Cursor loadSuggestedInfo;
        long j2;
        try {
            loadSuggestedInfo = new SuggestedLocalApi().loadSuggestedInfo();
            if (loadSuggestedInfo != null) {
                if (loadSuggestedInfo.moveToFirst()) {
                    do {
                        UpdateItem build = build(loadSuggestedInfo);
                        if (isStorySuggestedItem(build)) {
                            j2 = build.mediaId;
                        } else {
                            j2 = build.fileId;
                        }
                        hashMap.put(getKey(j2, build.suggestedType), build);
                    } while (loadSuggestedInfo.moveToNext());
                }
            }
            if (loadSuggestedInfo != null) {
                loadSuggestedInfo.close();
                return;
            }
            return;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private long getLongValue(Cursor cursor, int i2, int i7) {
        if (i2 < 0) {
            return (long) i7;
        }
        return cursor.getLong(i2);
    }

    private ContentValues getSuggestedContentValues(UpdateItem updateItem, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__Type", Integer.valueOf(updateItem.suggestedType));
        contentValues.put("__Title", updateItem.title);
        contentValues.put("__absID", Long.valueOf(updateItem.fileId));
        contentValues.put("__mediaId", Long.valueOf(updateItem.mediaId));
        contentValues.put("__data", updateItem.path);
        contentValues.put("__width", Integer.valueOf(updateItem.width));
        contentValues.put("__height", Integer.valueOf(updateItem.height));
        contentValues.put("__orientation", Integer.valueOf(updateItem.orientation));
        contentValues.put("__rect", updateItem.coverRectRatio);
        contentValues.put("__mediaType", Integer.valueOf(updateItem.mediaType));
        contentValues.put("__storageType", Integer.valueOf(updateItem.storageType));
        contentValues.put(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA, Integer.valueOf(updateItem.extra));
        contentValues.put("extraData", updateItem.extraData);
        contentValues.put("extraValue", updateItem.extraValue);
        if (!z) {
            long currentTimeMillis = System.currentTimeMillis();
            contentValues.put(GroupContract.Group.CREATED_TIME, Long.valueOf(currentTimeMillis));
            contentValues.put("modifiedTime", Long.valueOf(currentTimeMillis));
            contentValues.put("accessedTime", Long.valueOf(currentTimeMillis));
        }
        contentValues.put("__deleteType", Integer.valueOf(updateItem.deleteType));
        contentValues.put("video_thumb_start_time", Long.valueOf(updateItem.videoThumbStartTime));
        return contentValues;
    }

    private int getValue(Cursor cursor, int i2, int i7) {
        return i2 < 0 ? i7 : cursor.getInt(i2);
    }

    private long getVideoThumbStartTime(int i2, byte[] bArr) {
        DynamicViewInfo parse;
        DynamicViewPlayInfo build;
        int suggestionsType = DynamicViewType.getSuggestionsType(i2);
        if (bArr == null || bArr.length <= 0 || (parse = DynamicViewParser.parse(bArr, i2, false)) == null || (build = new DynamicViewInfo.PlayInfoBuilder(parse).setFileDuration(i2).setPlayType(suggestionsType).build()) == null || build.getSize() <= 0) {
            return 0;
        }
        return build.getVideoThumbStartTime();
    }

    private boolean isCleanOutBurstSimilarItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.CLEANOUT_BURST_SIMILAR.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isCleanOutDuplicatedItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.CLEANOUT_DUPLICATED_IMAGE.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isCleanOutItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.CLEANOUT.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isCleanOutMotionPhotoClipItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isHighlightItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.HIGHLIGHT.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == 102) {
            return true;
        }
        return false;
    }

    private boolean isPortraitItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.PORTRAIT.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isRevitalizedItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.REMASTER.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isSame(UpdateItem updateItem, UpdateItem updateItem2) {
        if (isStorySuggestedItem(updateItem)) {
            return isSameStorySuggested(updateItem, updateItem2);
        }
        if (isCleanOutItem(updateItem)) {
            return isSameCleanOut(updateItem, updateItem2);
        }
        return isSameCommon(updateItem, updateItem2);
    }

    private boolean isSameCleanOut(UpdateItem updateItem, UpdateItem updateItem2) {
        if (updateItem.extra == updateItem2.extra && TextUtils.equals(updateItem.extraData, updateItem2.extraData) && updateItem.fileId == updateItem2.fileId && updateItem.deleteType == updateItem2.deleteType && updateItem.orientation == updateItem2.orientation && updateItem.storageType == updateItem2.storageType) {
            return true;
        }
        return false;
    }

    private boolean isSameCommon(UpdateItem updateItem, UpdateItem updateItem2) {
        if (updateItem.extra == updateItem2.extra && TextUtils.equals(updateItem.extraData, updateItem2.extraData) && updateItem.fileId == updateItem2.fileId && updateItem.orientation == updateItem2.orientation && updateItem.suggestedType == updateItem2.suggestedType && updateItem.storageType == updateItem2.storageType) {
            return true;
        }
        return false;
    }

    private boolean isSameStorySuggested(UpdateItem updateItem, UpdateItem updateItem2) {
        if (!TextUtils.equals(updateItem.path, updateItem2.path) || !TextUtils.equals(updateItem.coverRectRatio, updateItem2.coverRectRatio) || !TextUtils.equals(updateItem.extraData, updateItem2.extraData) || !TextUtils.equals(updateItem.extraValue, updateItem2.extraValue) || updateItem.extra != updateItem2.extra || !TextUtils.equals(updateItem.title, updateItem2.title) || updateItem.storageType != updateItem2.storageType) {
            return false;
        }
        return true;
    }

    private boolean isStorySuggestedItem(UpdateItem updateItem) {
        if (updateItem.suggestedType == SuggestedType.COLLAGE.toInt() || updateItem.suggestedType == SuggestedType.AGIF.toInt() || updateItem.suggestedType == SuggestedType.VIDEO_COLLAGE.toInt() || updateItem.suggestedType == SuggestedType.REDISCOVER_DAY.toInt() || updateItem.suggestedType == SuggestedType.THEN_AND_NOW.toInt()) {
            return true;
        }
        return false;
    }

    private boolean isTypeMatched(SuggestedUpdateType suggestedUpdateType, UpdateItem updateItem) {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$suggested$SuggestedUpdateType[suggestedUpdateType.ordinal()]) {
            case 1:
                return isStorySuggestedItem(updateItem);
            case 2:
                return isCleanOutItem(updateItem);
            case 3:
                return isCleanOutMotionPhotoClipItem(updateItem);
            case 4:
                return isCleanOutBurstSimilarItem(updateItem);
            case 5:
                return isCleanOutDuplicatedItem(updateItem);
            case 6:
                return isRevitalizedItem(updateItem);
            case 7:
                return isHighlightItem(updateItem);
            case 8:
                return isPortraitItem(updateItem);
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public void onCurrent(Object obj, Bundle bundle) {
        if ("location://suggestions".equals(ArgumentsUtil.removeArgs((String) obj))) {
            Log.d(this.TAG, "onCurrent: doUpdate");
            doUpdate();
        }
    }

    /* access modifiers changed from: private */
    public void onSuggestedDataChanged(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        if (eventMessage == null || !isListeningEvent(eventMessage)) {
            Log.d(this.TAG, "not listening event");
        } else {
            doUpdate();
        }
    }

    private void setSuggestedType(UpdateItem updateItem, int i2) {
        if (i2 == SuggestedUpdateType.TYPE_CLEAN_OUT.ordinal()) {
            updateItem.suggestedType = SuggestedType.CLEANOUT.toInt();
        } else if (i2 == SuggestedUpdateType.TYPE_MOTION_PHOTO_CLIP.ordinal()) {
            updateItem.suggestedType = SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.toInt();
        } else if (i2 == SuggestedUpdateType.TYPE_BURST_SIMILAR.ordinal()) {
            updateItem.suggestedType = SuggestedType.CLEANOUT_BURST_SIMILAR.toInt();
        } else if (i2 == SuggestedUpdateType.TYPE_DUPLICATED.ordinal()) {
            updateItem.suggestedType = SuggestedType.CLEANOUT_DUPLICATED_IMAGE.toInt();
        } else if (i2 == SuggestedUpdateType.TYPE_REVITALIZATION.ordinal()) {
            updateItem.suggestedType = SuggestedType.REMASTER.toInt();
        } else if (i2 == SuggestedUpdateType.TYPE_HIGHLIGHT.ordinal()) {
            updateItem.suggestedType = SuggestedType.HIGHLIGHT.toInt();
        } else if (i2 == SuggestedUpdateType.TYPE_PORTRAIT.ordinal()) {
            updateItem.suggestedType = SuggestedType.PORTRAIT.toInt();
        } else if (updateItem.extraData.equals(String.valueOf(StoryType.VIDEO_COLLAGE.getValue()))) {
            updateItem.suggestedType = SuggestedType.VIDEO_COLLAGE.toInt();
        } else if (updateItem.extraData.equals(String.valueOf(StoryType.AGIF.getValue()))) {
            updateItem.suggestedType = SuggestedType.AGIF.toInt();
        } else if (updateItem.extraData.equals(String.valueOf(StoryType.COLLAGE.getValue()))) {
            updateItem.suggestedType = SuggestedType.COLLAGE.toInt();
        } else if (updateItem.extraData.equals(String.valueOf(StoryType.COLLAGE_THEN_AND_NOW.getValue()))) {
            updateItem.suggestedType = SuggestedType.THEN_AND_NOW.toInt();
        } else if (updateItem.extraData.equals(String.valueOf(StoryType.REDISCOVER_DAY.getValue()))) {
            updateItem.suggestedType = SuggestedType.REDISCOVER_DAY.toInt();
        } else {
            throw new IllegalArgumentException("Invalid suggested type");
        }
    }

    private boolean showCleanOutCard(Cursor cursor) {
        if (cursor.getCount() >= 1) {
            return true;
        }
        return false;
    }

    private void updateInBatch(ArrayList<ContentProviderOperation> arrayList) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ContentProviderResult[] applyBatch = this.mContext.get().getContentResolver().applyBatch(new SuggestedLocalApi().getTableUri().getAuthority(), arrayList);
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "Suggested DB updated : " + applyBatch.length + " +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (OperationApplicationException | RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateOperation(com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater.UpdateItem r9, java.util.HashMap<java.lang.String, com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater.UpdateItem> r10, com.samsung.android.gallery.module.suggested.SuggestedUpdateType r11) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            long r1 = r9.fileId
            int r3 = r9.suggestedType
            java.lang.String r1 = r8.getKey(r1, r3)
            long r2 = r9.fileId
            r4 = -1
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0040
            boolean r2 = r10.containsKey(r1)
            if (r2 == 0) goto L_0x0038
            java.lang.Object r2 = r10.get(r1)
            com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater$UpdateItem r2 = (com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater.UpdateItem) r2
            boolean r2 = r8.isSame(r2, r9)
            if (r2 != 0) goto L_0x0040
            android.content.ContentProviderOperation r9 = r8.createUpdateOp(r9, r4)
            r8.addOperation(r9, r0)
        L_0x0036:
            r9 = r3
            goto L_0x0041
        L_0x0038:
            android.content.ContentProviderOperation r9 = r8.createInsertOp(r9)
            r8.addOperation(r9, r0)
            goto L_0x0036
        L_0x0040:
            r9 = r4
        L_0x0041:
            java.util.Collection r10 = r10.values()
            java.util.Iterator r10 = r10.iterator()
        L_0x0049:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x0093
            java.lang.Object r2 = r10.next()
            com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater$UpdateItem r2 = (com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater.UpdateItem) r2
            long r5 = r2.fileId
            int r7 = r2.suggestedType
            java.lang.String r5 = r8.getKey(r5, r7)
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x0049
            boolean r5 = r8.isTypeMatched(r11, r2)
            if (r5 == 0) goto L_0x0049
            com.samsung.android.gallery.module.suggested.SuggestedUpdateType r5 = com.samsung.android.gallery.module.suggested.SuggestedUpdateType.TYPE_CLEAN_OUT
            boolean r5 = r5.equals(r11)
            if (r5 == 0) goto L_0x008b
            int r5 = r2.deleteType
            if (r5 == r3) goto L_0x0082
            int r5 = r2.deleteType
            r6 = 2
            if (r5 != r6) goto L_0x0049
        L_0x0082:
            android.content.ContentProviderOperation r9 = r8.createDeleteOp(r2, r4)
            r8.addOperation(r9, r0)
        L_0x0089:
            r9 = r3
            goto L_0x0049
        L_0x008b:
            android.content.ContentProviderOperation r9 = r8.createDeleteOp(r2, r4)
            r8.addOperation(r9, r0)
            goto L_0x0089
        L_0x0093:
            boolean r10 = r0.isEmpty()
            if (r10 != 0) goto L_0x009c
            r8.updateInBatch(r0)
        L_0x009c:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater.updateOperation(com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater$UpdateItem, java.util.HashMap, com.samsung.android.gallery.module.suggested.SuggestedUpdateType):boolean");
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("location://variable/currentv1", new a(this, 0)).setTriggerPreloadedData());
        arrayList.add(new SubscriberInfo("command://event/DataChanged", new a(this, 1)));
    }

    public void doUpdate() {
        try {
            addDataToLocalDb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreate() {
        super.onCreate();
    }

    private UpdateItem buildMultiple(Cursor cursor, ArrayList<Integer> arrayList) {
        String str;
        String str2;
        long j2;
        String str3;
        Cursor cursor2 = cursor;
        ArrayList<Integer> arrayList2 = arrayList;
        UpdateItem updateItem = new UpdateItem(0);
        if (arrayList2.isEmpty()) {
            IntStream.range(0, cursor2.getCount()).forEach(new C0383s(3, arrayList2));
        }
        cursor2.moveToPosition(arrayList2.get(0).intValue());
        updateItem.fileId = getLongValue(cursor2, cursor2.getColumnIndex("__absID"), -1);
        updateItem.mediaId = getLongValue(cursor2, cursor2.getColumnIndex("__mediaId"), -1);
        updateItem.suggestedType = getValue(cursor2, cursor2.getColumnIndex("__Type"), -1);
        updateItem.title = getValue(cursor2, cursor2.getColumnIndex("__Title"), (String) null);
        updateItem.path = getValue(cursor2, cursor2.getColumnIndex("__data"), (String) null);
        String str4 = "__width";
        updateItem.width = getValue(cursor2, cursor2.getColumnIndex(str4), 0);
        updateItem.height = getValue(cursor2, cursor2.getColumnIndex("__height"), 0);
        updateItem.orientation = getValue(cursor2, cursor2.getColumnIndex("__orientation"), 0);
        updateItem.coverRectRatio = getValue(cursor2, cursor2.getColumnIndex("__rect"), (String) null);
        updateItem.mediaType = getValue(cursor2, cursor2.getColumnIndex("__mediaType"), 1);
        updateItem.storageType = getValue(cursor2, cursor2.getColumnIndex("__storageType"), 1);
        if (cursor2.getColumnIndex("revitalized_type") > 0) {
            updateItem.extra = arrayList2.size();
        } else {
            updateItem.extra = getValue(cursor2, cursor2.getColumnIndex(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA), -1);
        }
        updateItem.deleteType = getValue(cursor2, cursor2.getColumnIndex("__deleteType"), -1);
        String str5 = "__fileDuration";
        String str6 = "__mediaId";
        String str7 = "__dynamicViewInfo";
        if (cursor2.getColumnIndex(str5) == -1 || cursor2.getColumnIndex(str7) == -1) {
            str = "__data";
            updateItem.videoThumbStartTime = 0;
        } else {
            str = "__data";
            updateItem.videoThumbStartTime = getVideoThumbStartTime(getValue(cursor2, cursor2.getColumnIndex(str5), -1), cursor2.getBlob(cursor2.getColumnIndex(str7)));
        }
        StringBuilder sb2 = new StringBuilder();
        int i2 = 1;
        while (arrayList2.size() > i2) {
            int i7 = i2 + 1;
            if (!cursor2.moveToPosition(arrayList2.get(i2).intValue())) {
                break;
            }
            if (cursor2.getColumnIndex(str5) == -1 || cursor2.getColumnIndex(str7) == -1) {
                i2 = i7;
                str3 = str6;
                str2 = str7;
                j2 = 0;
            } else {
                i2 = i7;
                str3 = str6;
                str2 = str7;
                j2 = getVideoThumbStartTime(getValue(cursor2, cursor2.getColumnIndex(str5), -1), cursor2.getBlob(cursor2.getColumnIndex(str7)));
            }
            String str8 = str3;
            sb2.append(getValue(cursor2, cursor2.getColumnIndex(str3), -1));
            sb2.append(NumericEnum.SEP);
            String str9 = str;
            String str10 = str9;
            sb2.append(getValue(cursor2, cursor2.getColumnIndex(str9), ""));
            sb2.append(NumericEnum.SEP);
            sb2.append(getValue(cursor2, cursor2.getColumnIndex(str4), 0));
            sb2.append(NumericEnum.SEP);
            sb2.append(getValue(cursor2, cursor2.getColumnIndex("__height"), 0));
            sb2.append(NumericEnum.SEP);
            sb2.append(getValue(cursor2, cursor2.getColumnIndex("__orientation"), 0));
            sb2.append(NumericEnum.SEP);
            sb2.append(getValue(cursor2, cursor2.getColumnIndex("__rect"), ""));
            sb2.append(NumericEnum.SEP);
            sb2.append(getValue(cursor2, cursor2.getColumnIndex("__mediaType"), 1));
            sb2.append(NumericEnum.SEP);
            sb2.append(getValue(cursor2, cursor2.getColumnIndex("__storageType"), 1));
            sb2.append(NumericEnum.SEP);
            sb2.append(j2);
            sb2.append("|");
            arrayList2 = arrayList;
            str7 = str2;
            str5 = str5;
            str6 = str8;
            str = str10;
            str4 = str4;
        }
        updateItem.extraData = sb2.toString();
        updateItem.extraValue = getValue(cursor2, cursor2.getColumnIndex("extraValue"), (String) null);
        return updateItem;
    }

    private String getValue(Cursor cursor, int i2, String str) {
        return i2 < 0 ? str : cursor.getString(i2);
    }
}
