package com.samsung.android.gallery.module.tag;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.MediaStore;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.impl.GroupMediaImpl;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.providers.MediaUriGmp;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TagEditor {
    private static final boolean GED = false;
    protected final boolean IS_GTE_Q;
    protected final boolean IS_GTE_R;
    protected final boolean IS_LT_Q;
    protected final String TAG = "TagEditor";
    protected QueryParams mParams;
    protected RawQueryExecutor mQueryExecutor;

    public TagEditor() {
        boolean z;
        QueryParams queryParams = new QueryParams();
        this.mParams = queryParams;
        RawQueryExecutor rawQueryExecutor = queryParams.inQueryExecutor;
        this.mQueryExecutor = rawQueryExecutor;
        if (rawQueryExecutor == null) {
            this.mQueryExecutor = new SecMpQueryExecutor();
        }
        if (this.mParams.mPrintQuery) {
            this.mQueryExecutor.enableLogcatOnce();
        }
        int osVersion = this.mParams.getOsVersion();
        boolean z3 = false;
        if (osVersion >= 30) {
            z = true;
        } else {
            z = false;
        }
        this.IS_GTE_R = z;
        z3 = osVersion >= 29 ? true : z3;
        this.IS_GTE_Q = z3;
        this.IS_LT_Q = !z3;
    }

    private void runFavoriteOperation(Uri uri, ArrayList<ContentProviderOperation> arrayList, long j2, boolean z) {
        ContentProviderOperation contentProviderOperation;
        if (j2 > 0 && uri != null) {
            if (this.IS_GTE_R) {
                contentProviderOperation = ContentProviderOperation.newUpdate(uri).withSelection("_id = ? ", new String[]{Long.toString(j2)}).withValue("is_favorite", Boolean.valueOf(z)).build();
            } else {
                contentProviderOperation = ContentProviderOperation.newUpdate(uri).withValue("is_favorite", Boolean.valueOf(z)).build();
            }
            arrayList.add(contentProviderOperation);
        }
    }

    private int updateFavouriteGedMp(boolean z, Collection<Long> collection, boolean z3) {
        Uri uri;
        if (collection.size() <= 0) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_favorite", Boolean.valueOf(z));
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "(", ")");
        for (Long longValue : collection) {
            stringJoiner.add(String.valueOf(longValue.longValue()));
        }
        String str = "_id in " + stringJoiner.toString();
        ContentResolver contentResolver = getContentResolver();
        if (z3) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        return contentResolver.update(uri, contentValues, str, (String[]) null);
    }

    public int addFavoriteInBulk(List<? extends FileItemInterface> list) {
        TagEditor tagEditor;
        ArrayList arrayList;
        Uri uri;
        TagEditor tagEditor2;
        Throwable th;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (FileItemInterface fileItemInterface : list) {
            if (this.IS_GTE_R) {
                if (fileItemInterface.isImage()) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                }
                Uri uri2 = uri;
                if (fileItemInterface.isBurstShot()) {
                    Cursor groupShotCursor = new GroupMediaImpl(DbCompatGroup.getGroupShotQueryParams(fileItemInterface)).getGroupShotCursor();
                    if (groupShotCursor != null) {
                        try {
                            int columnIndex = groupShotCursor.getColumnIndex("__fileMediaId");
                            while (groupShotCursor.moveToNext()) {
                                TagEditor tagEditor3 = this;
                                tagEditor3.runFavoriteOperation(uri2, arrayList3, groupShotCursor.getLong(columnIndex), true);
                                this = tagEditor3;
                            }
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    tagEditor2 = this;
                    if (groupShotCursor != null) {
                        groupShotCursor.close();
                    }
                } else {
                    tagEditor2 = this;
                    tagEditor2.runFavoriteOperation(uri2, arrayList3, fileItemInterface.getMediaId(), true);
                }
                arrayList = arrayList3;
                Uri secUri = ContentUri.getSecUri(fileItemInterface);
                TagEditor tagEditor4 = tagEditor2;
                FileItemInterface fileItemInterface2 = fileItemInterface;
                tagEditor = tagEditor4;
                tagEditor.runFavoriteOperation(secUri, arrayList2, fileItemInterface2.getFileId(), true);
            } else {
                FileItemInterface fileItemInterface3 = fileItemInterface;
                tagEditor = this;
                arrayList = arrayList3;
                if (tagEditor.IS_GTE_Q) {
                    tagEditor.runFavoriteOperation(ContentUri.getSecUri(fileItemInterface3), arrayList2, fileItemInterface3.getMediaId(), true);
                } else {
                    arrayList3 = arrayList;
                    tagEditor.runFavoriteOperation(tagEditor.getFavoriteUri(fileItemInterface3), arrayList3, fileItemInterface3.getMediaId(), true);
                    this = tagEditor;
                }
            }
            arrayList3 = arrayList;
            this = tagEditor;
        }
        TagEditor tagEditor5 = this;
        int i2 = 0;
        try {
            i2 = tagEditor5.getContentResolver().applyBatch(MediaUri.getInstance().getSecMediaUri().getAuthority(), arrayList2).length;
            return i2 + tagEditor5.getContentResolver().applyBatch("media", arrayList3).length;
        } catch (OperationApplicationException | RemoteException | NullPointerException e) {
            e.printStackTrace();
            return i2;
        }
        throw th;
    }

    public String getBurstShotIdColumnName() {
        if (this.IS_GTE_Q) {
            return "burst_group_id";
        }
        return BundleKey.GROUP_ID;
    }

    public ContentResolver getContentResolver() {
        return this.mQueryExecutor.getContentResolver();
    }

    public Uri getFavoriteUri(FileItemInterface fileItemInterface) {
        if (GED) {
            return new MediaUriGmp().getSecFilesUri();
        }
        if (!fileItemInterface.isBurstShot() || fileItemInterface.getGroupMediaId() <= 0) {
            return ContentUri.getWritableUri(fileItemInterface);
        }
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    public String getFavoriteWhereCondition(FileItemInterface fileItemInterface) {
        if (GED) {
            return "_id=" + fileItemInterface.getFileId();
        } else if (!fileItemInterface.isBurstShot() || fileItemInterface.getGroupMediaId() <= 0) {
            return null;
        } else {
            return getBurstShotIdColumnName() + " = " + fileItemInterface.getGroupMediaId();
        }
    }

    public void removeFavoriteInBulk(List<? extends FileItemInterface> list) {
        TagEditor tagEditor;
        ArrayList arrayList;
        Uri uri;
        TagEditor tagEditor2;
        Throwable th;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (FileItemInterface fileItemInterface : list) {
            if (this.IS_GTE_R) {
                if (fileItemInterface.isImage()) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                }
                Uri uri2 = uri;
                if (fileItemInterface.isBurstShot()) {
                    Cursor groupShotCursor = new GroupMediaImpl(DbCompatGroup.getGroupShotQueryParams(fileItemInterface)).getGroupShotCursor();
                    if (groupShotCursor != null) {
                        try {
                            int columnIndex = groupShotCursor.getColumnIndex("__fileMediaId");
                            while (groupShotCursor.moveToNext()) {
                                TagEditor tagEditor3 = this;
                                tagEditor3.runFavoriteOperation(uri2, arrayList3, groupShotCursor.getLong(columnIndex), false);
                                this = tagEditor3;
                            }
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    tagEditor2 = this;
                    if (groupShotCursor != null) {
                        groupShotCursor.close();
                    }
                } else {
                    tagEditor2 = this;
                    tagEditor2.runFavoriteOperation(uri2, arrayList3, fileItemInterface.getMediaId(), false);
                }
                arrayList = arrayList3;
                Uri secUri = ContentUri.getSecUri(fileItemInterface);
                TagEditor tagEditor4 = tagEditor2;
                FileItemInterface fileItemInterface2 = fileItemInterface;
                tagEditor = tagEditor4;
                tagEditor.runFavoriteOperation(secUri, arrayList2, fileItemInterface2.getFileId(), false);
            } else {
                FileItemInterface fileItemInterface3 = fileItemInterface;
                tagEditor = this;
                arrayList = arrayList3;
                if (tagEditor.IS_GTE_Q) {
                    tagEditor.runFavoriteOperation(ContentUri.getSecUri(fileItemInterface3), arrayList2, fileItemInterface3.getMediaId(), false);
                } else {
                    arrayList3 = arrayList;
                    tagEditor.runFavoriteOperation(tagEditor.getFavoriteUri(fileItemInterface3), arrayList3, fileItemInterface3.getMediaId(), false);
                    this = tagEditor;
                }
            }
            arrayList3 = arrayList;
            this = tagEditor;
        }
        TagEditor tagEditor5 = this;
        try {
            tagEditor5.getContentResolver().applyBatch(MediaUri.getInstance().getSecMediaUri().getAuthority(), arrayList2);
            tagEditor5.getContentResolver().applyBatch("media", arrayList3);
            return;
        } catch (OperationApplicationException | RemoteException | NullPointerException e) {
            e.printStackTrace();
            return;
        }
        throw th;
    }

    public int setFavorite(FileItemInterface fileItemInterface, boolean z) {
        int i2;
        if (this.IS_GTE_R) {
            long mediaId = fileItemInterface.getMediaId();
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(mediaId));
            return updateFavouriteGedMp(z, arrayList, fileItemInterface.isImage());
        }
        if (this.IS_GTE_Q) {
            i2 = setFavoriteToSecMp(fileItemInterface, z);
        } else {
            i2 = 0;
        }
        return setFavoriteP(fileItemInterface, z) + i2;
    }

    public int setFavoriteP(FileItemInterface fileItemInterface, boolean z) {
        if (!fileItemInterface.isCloudOnly()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_favorite", Boolean.valueOf(z));
            return getContentResolver().update(getFavoriteUri(fileItemInterface), contentValues, getFavoriteWhereCondition(fileItemInterface), (String[]) null);
        }
        throw new AssertionError("use SCloudWrapper.OperateApi.setFavorite");
    }

    public int setFavoriteToSecMp(FileItemInterface fileItemInterface, boolean z) {
        int i2;
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_favorite", Boolean.valueOf(z));
        Uri secUri = ContentUri.getSecUri(fileItemInterface);
        String favoriteWhereCondition = getFavoriteWhereCondition(fileItemInterface);
        if (secUri != null) {
            i2 = getContentResolver().update(secUri, contentValues, favoriteWhereCondition, (String[]) null);
        } else {
            i2 = 0;
        }
        if (i2 < 1) {
            Log.e("TagEditor", "fail FileItemInterface : " + secUri + ArcCommonLog.TAG_COMMA + favoriteWhereCondition);
        }
        return i2;
    }
}
