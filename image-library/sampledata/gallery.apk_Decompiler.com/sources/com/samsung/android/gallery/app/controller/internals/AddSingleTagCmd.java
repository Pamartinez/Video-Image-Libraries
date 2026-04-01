package com.samsung.android.gallery.app.controller.internals;

import K5.a;
import M9.o;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.message.AddTagMsgMgr;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddSingleTagCmd extends BaseCommand {
    private Consumer<Void> mCallback;
    private long mId = -1;
    private MediaItem mMediaItem = null;
    private final ArrayList<String> mTags = new ArrayList<>();

    private AddTagMsgMgr.ErrorType getErrorType() {
        ArrayList<String> tagList = getTagList();
        String str = this.mTags.get(0);
        if (tagList.size() >= 30) {
            return AddTagMsgMgr.ErrorType.LIMITED;
        }
        if (tagList.contains(str.toLowerCase())) {
            return AddTagMsgMgr.ErrorType.PRESENT;
        }
        if (str.length() > 50) {
            return AddTagMsgMgr.ErrorType.OVER;
        }
        if (this.mMediaItem.isBroken()) {
            return AddTagMsgMgr.ErrorType.BROKEN;
        }
        return AddTagMsgMgr.ErrorType.NONE;
    }

    private ArrayList<String> getTagList() {
        Cursor query;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            query = DbCompat.query("mp://myTag", new a(17, this));
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("__subCategory");
                    do {
                        arrayList.add(query.getString(columnIndex).toLowerCase());
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void insertToSearchIndex() {
        long currentTimeMillis = System.currentTimeMillis();
        IntelligentSearchIndex.getInstance().indexing(this.mId, (Collection<String>) this.mTags, IntelligentSearchIndex.TagType.USER_TAG, IntelligentSearchIndex.IndexMode.APPEND);
        String str = this.TAG;
        Log.bx(str, "added to search index [" + (System.currentTimeMillis() - currentTimeMillis) + "]");
    }

    private void insertToTagDB() {
        long currentTimeMillis = System.currentTimeMillis();
        new TagEditApi().insertMyTagArray(this.mTags, this.mId, true);
        String str = this.TAG;
        Log.bx(str, "added to tag db [" + (System.currentTimeMillis() - currentTimeMillis) + "]");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getTagList$0(QueryParams queryParams) {
        queryParams.setFileId(this.mId);
    }

    private void moveToDetails() {
        Consumer<Void> consumer = this.mCallback;
        if (consumer != null) {
            consumer.accept((Object) null);
        }
    }

    /* access modifiers changed from: private */
    public void operate() {
        AddTagMsgMgr.ErrorType errorType = getErrorType();
        if (errorType != AddTagMsgMgr.ErrorType.NONE) {
            boolean isImage = this.mMediaItem.isImage();
            showToast(AddTagMsgMgr.getFailedToastMessage(getContext(), errorType, isImage ? 1 : 0, isImage ^ true ? 1 : 0));
            String str = this.TAG;
            Log.bxe(str, "unable to add tag [" + errorType + "]");
            return;
        }
        insertToTagDB();
        insertToSearchIndex();
        refreshData();
        moveToDetails();
    }

    private void refreshData() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS) {
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3052));
        } else {
            BlackboardUtils.forceRefreshPicturesData(getBlackboard(), true);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        String str = objArr[1];
        this.mCallback = objArr[2];
        if (mediaItem == null || TextUtils.isEmpty(str)) {
            Log.bxe(this.TAG, "unable to add tag, null data");
            return;
        }
        this.mId = this.mMediaItem.getFileId();
        this.mTags.add(str.replaceAll(" ", ""));
        ThreadUtil.postOnBgThread(new o(8, this));
    }
}
