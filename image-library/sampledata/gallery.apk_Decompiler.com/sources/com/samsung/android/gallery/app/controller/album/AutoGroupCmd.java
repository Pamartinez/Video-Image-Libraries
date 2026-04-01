package com.samsung.android.gallery.app.controller.album;

import A8.C0545b;
import D7.g;
import android.database.Cursor;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoGroupCmd extends BaseCommand {
    final HashMap<String, String> mMap = new HashMap<>();

    /* access modifiers changed from: private */
    public void create() {
        Throwable th;
        String str;
        int i2;
        int i7;
        String str2;
        int i8;
        Cursor rawQuery = new SecMpQueryExecutor().rawQuery("select substr(relative_path,0,length(relative_path)-length(bucket_display_name)-1) as parentName,group_concat(DISTINCT bucket_id) as ids,group_concat(DISTINCT bucket_display_name) from files where relative_path != '/' and length(parentName)>0 group by parentName order by parentName", "get folders");
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        int i10 = 0;
                        String string = rawQuery.getString(0);
                        String string2 = rawQuery.getString(1);
                        String[] split = string.split("/");
                        String[] split2 = string2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                        ArrayList arrayList = new ArrayList();
                        for (String str3 : split2) {
                            int i11 = UnsafeCast.toInt(str3, 0);
                            if (i11 != 0) {
                                arrayList.add(Integer.valueOf(i11));
                            }
                        }
                        String str4 = "";
                        Log.d(this.TAG, "fullPath : " + string);
                        int length = split.length;
                        String str5 = null;
                        int i12 = -1;
                        int i13 = -1;
                        while (true) {
                            String str6 = str5;
                            if (i10 >= length) {
                                break;
                            }
                            String str7 = split[i10];
                            if (i13 == -1) {
                                i2 = AlbumHelper.getInstance().addNewEmptyFolder(str7);
                                str = str7;
                            } else {
                                i2 = AlbumHelper.getInstance().addNewEmptyFolder(i13, str6, str7, 1000000000000000007L);
                                str = str7;
                            }
                            if (i2 != -1) {
                                i2 = FileUtils.getBucketId(str);
                                Log.d(this.TAG, "create group : " + str, Integer.valueOf(i2), str4);
                                this.mMap.put(str, str4 + str);
                            } else {
                                String str8 = this.mMap.get(str);
                                if (str8 != null) {
                                    if (str8.equals(str4 + str)) {
                                        i2 = FileUtils.getBucketId(str);
                                        Log.d(this.TAG, "reuse group : " + str, Integer.valueOf(i2), str4);
                                    }
                                }
                            }
                            if (i2 == -1) {
                                String str9 = str4 + str;
                                if (i13 == -1) {
                                    String str10 = str;
                                    i7 = AlbumHelper.getInstance().addNewEmptyFolder(str9);
                                    str2 = str10;
                                } else {
                                    str2 = str;
                                    i7 = AlbumHelper.getInstance().addNewEmptyFolder(i13, str6, str9, 1000000000000000007L);
                                }
                                Log.d(this.TAG, "create group with parentName : " + str9);
                                if (i7 == -1) {
                                    i8 = FileUtils.getBucketId(str9);
                                    Log.d(this.TAG, "reuse group2 : " + str9);
                                } else {
                                    i8 = FileUtils.getBucketId(str9);
                                    Log.d(this.TAG, "create group2 : " + str9, Integer.valueOf(i8));
                                }
                                this.mMap.put(str9, str4 + str2);
                                i13 = i8;
                                str = str2;
                                str5 = str9;
                            } else {
                                i13 = i2;
                                str5 = str;
                            }
                            str4 = str4 + str + "#";
                            i10++;
                            i12 = i13;
                        }
                        int[] array = arrayList.stream().mapToInt(new C0545b(8)).toArray();
                        if (AlbumHelper.getInstance().updateFolder(array, i12, str5)) {
                            Log.d(this.TAG, "Success : " + string, Logger.v(Integer.valueOf(i12), str5, Arrays.toString(array)));
                        } else {
                            Log.d(this.TAG, "failed : " + string);
                        }
                    } while (rawQuery.moveToNext());
                    Utils.showToast(getContext(), "Done");
                    reloadAlbum();
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
            return;
        }
        return;
        throw th;
    }

    private void reloadAlbum() {
        getBlackboard().postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().execute(new g(26, this));
    }
}
