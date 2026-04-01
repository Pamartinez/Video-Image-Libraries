package com.samsung.android.gallery.bixby.bixby.util;

import B5.e;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.search.SearchAlbumData;
import com.samsung.android.gallery.bixby.bixby.search.SearchInfo;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionHelper {
    private SearchAlbumData getAlbumGroupData(Cursor cursor, HashMap<Integer, String> hashMap) {
        int i2 = cursor.getInt(cursor.getColumnIndex("__bucketID"));
        int i7 = cursor.getInt(cursor.getColumnIndex("album_count"));
        String string = cursor.getString(cursor.getColumnIndex("__Title"));
        String albumPath = getAlbumPath(i2, cursor.getString(cursor.getColumnIndex("default_cover_path")), hashMap);
        AlbumType albumType = AlbumType.get(cursor.getInt(cursor.getColumnIndex("__albumType")));
        SearchAlbumData.Builder albumPath2 = new SearchAlbumData.Builder().setAlbumId(i2).setAlbumType(albumType).setAlbumPath(albumPath);
        if (BucketUtils.isVirtualAlbum(i2)) {
            string = AlbumTitleHelper.getAlbumTitle(i2);
        }
        SearchAlbumData.Builder albumName = albumPath2.setAlbumName(string);
        if (i7 == 0 && !albumType.equals(AlbumType.Folder)) {
            albumName.setEmptyAlbum();
        }
        return albumName.build();
    }

    private void getAlbumGroupList(ArrayList<SearchAlbumData> arrayList, ArrayList<String> arrayList2, ActionHelperParams actionHelperParams) {
        Cursor albumCursor;
        LocalAlbumsApi localAlbumsApi = new LocalAlbumsApi();
        HashMap<Integer, String> cloudAlbumList = AlbumHelper.getInstance().getCloudAlbumList();
        try {
            albumCursor = localAlbumsApi.getAlbumCursor(false, false, false, false);
            if (albumCursor != null) {
                if (albumCursor.moveToFirst()) {
                    do {
                        SearchAlbumData albumGroupData = getAlbumGroupData(albumCursor, cloudAlbumList);
                        if (!isInvalidData(actionHelperParams, albumGroupData.getAlbumType())) {
                            arrayList.add(albumGroupData);
                            arrayList2.add(albumGroupData.getAlbumName());
                        }
                    } while (albumCursor.moveToNext());
                }
            }
            if (albumCursor != null) {
                albumCursor.close();
                return;
            }
            return;
        } catch (Exception e) {
            Log.bxe("ActionHelper", "query album group list failed, e=" + e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private String getAlbumPath(int i2, String str, HashMap<Integer, String> hashMap) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("/storage/emulated/")) {
            return FileUtils.getParent(str);
        }
        if (!str.startsWith("/data/sec/cloud/")) {
            return null;
        }
        String str2 = hashMap.get(Integer.valueOf(i2));
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return FileUtils.getParent(str2);
    }

    private String getDumpLog(String str, ArrayList<String> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new e(stringJoiner, 2));
        return str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + stringJoiner;
    }

    private SearchAlbumData getMatchedAlbum(Context context, ArrayList<SearchAlbumData> arrayList, ArrayList<String> arrayList2, ActionHelperParams actionHelperParams) {
        int indexOfMatched;
        SearchAlbumData build = new SearchAlbumData.Builder().build();
        if (arrayList2.isEmpty()) {
            Log.bxe("ActionHelper", "searched album list empty");
            return build;
        }
        boolean usePattern = actionHelperParams.usePattern();
        String name = actionHelperParams.getName();
        String directoryName = AlbumTitleHelper.getDirectoryName(context, QueryBuilder.escapeString(name));
        Log.bx("ActionHelper", "searched album list " + Logger.getEncodedString(getDumpLog(name, directoryName, arrayList2)));
        ActionHandlerUtil actionHandlerUtil = new ActionHandlerUtil();
        int indexOfMatched2 = actionHandlerUtil.getIndexOfMatched(directoryName, arrayList2, usePattern);
        if (indexOfMatched2 != -1) {
            return arrayList.get(indexOfMatched2);
        }
        if (Objects.equals(name, directoryName) || (indexOfMatched = actionHandlerUtil.getIndexOfMatched(name, arrayList2, usePattern)) == -1) {
            return build;
        }
        return arrayList.get(indexOfMatched);
    }

    private int getMatchedStory(String str, ArrayList<String> arrayList, ArrayList<Integer> arrayList2) {
        if (arrayList2.isEmpty()) {
            Log.bxe("ActionHelper", "searched story list empty");
            return -1;
        }
        Log.bx("ActionHelper", "searched story list " + Logger.getEncodedString(getDumpLog(str, arrayList)));
        int indexOfMatched = new ActionHandlerUtil().getIndexOfMatched(str, arrayList, true);
        if (indexOfMatched != -1) {
            return arrayList2.get(indexOfMatched).intValue();
        }
        return -1;
    }

    private void getStoryList(ArrayList<Integer> arrayList, ArrayList<String> arrayList2) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.STORIES, (Consumer<QueryParams>) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(Integer.valueOf(query.getInt(query.getColumnIndex("__albumID"))));
                        arrayList2.add(query.getString(query.getColumnIndex("__Title")));
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            Log.bxe("ActionHelper", "query story list failed, e=" + e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private boolean isInvalidData(ActionHelperParams actionHelperParams, AlbumType albumType) {
        if (actionHelperParams.isIncludeAlbumOnly() && AlbumType.isFolder(albumType)) {
            return true;
        }
        if (actionHelperParams.isIncludeGroupOnly() && !AlbumType.isFolder(albumType)) {
            return true;
        }
        if (!actionHelperParams.isExcludeVirtual() || !AlbumType.isVirtual(albumType)) {
            return false;
        }
        return true;
    }

    public SearchInfo getSearchInfoFromUri(Context context, Uri uri) {
        ActionHandlerUtil actionHandlerUtil = new ActionHandlerUtil();
        SearchInfo searchInfo = new SearchInfo();
        long j2 = UnsafeCast.toLong(uri.getQueryParameter("KEY_TIME_FROM"), 0);
        long j3 = UnsafeCast.toLong(uri.getQueryParameter("KEY_TIME_TO"), 0);
        long[] jArr = {j2, j3};
        if (!(j2 == 0 || j3 == 0)) {
            searchInfo.setTimeInfo(jArr);
        }
        String queryParameter = uri.getQueryParameter("KEY_CONTENT_TYPE");
        if (queryParameter != null) {
            searchInfo.setContentType(actionHandlerUtil.getTranslatedName(context, queryParameter));
        }
        searchInfo.setUri(uri.toString());
        searchInfo.setCountry(uri.getQueryParameter("KEY_COUNTRY"), uri.getQueryParameter("KEY_COUNTRY_CODE"));
        searchInfo.setLocation(uri.getQueryParameter("KEY_LOCATION"));
        searchInfo.setOrderType(uri.getQueryParameter("KEY_ORDER_TYPE"));
        searchInfo.setPoi(uri.getQueryParameter("KEY_POI"));
        searchInfo.setTag(uri.getQueryParameter("KEY_TAG"));
        searchInfo.setTitle(uri.getQueryParameter("KEY_TITLE"));
        searchInfo.setTime(uri.getQueryParameter("KEY_TIME"));
        searchInfo.setUtterance(uri.getQueryParameter("KEY_UTT"));
        return searchInfo;
    }

    public SearchAlbumData queryAlbumAndGroupDataFromName(Context context, ActionHelperParams actionHelperParams) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        getAlbumGroupList(arrayList, arrayList2, actionHelperParams);
        return getMatchedAlbum(context, arrayList, arrayList2, actionHelperParams);
    }

    public int queryChannelIdFromName(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        getStoryList(arrayList, arrayList2);
        return getMatchedStory(str, arrayList2, arrayList);
    }

    private String getDumpLog(String str, String str2, ArrayList<String> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new e(stringJoiner, 2));
        return str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + stringJoiner;
    }
}
