package com.samsung.android.gallery.module.myquery;

import A.a;
import A4.A;
import B5.e;
import C3.C0392b;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.local.table.MyQueryTable;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.creature.base.MyQueryInterface;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MyQueryColumn;
import com.samsung.android.gallery.module.data.MyQueryItemLoader;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MyQueryUtil {
    private static final MyQueryInterface MY_QUERY_INTERFACE = new MyQueryInterface() {
        public ArrayList<String> getTop5List() {
            return MyQueryUtil.getTop5List();
        }

        public boolean isTop5Creature(String str, ArrayList<String> arrayList) {
            return MyQueryUtil.isTop5Creature(str, arrayList);
        }

        public void mergeTop5List(ArrayList<String> arrayList, String str) {
            MyQueryUtil.mergeTop5List(arrayList, str);
        }

        public void removeTop5List(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            MyQueryUtil.removeTop5List(arrayList, arrayList2);
        }
    };

    public static boolean createMyQuery(String str, int i2, String str2) {
        return SearchMyQuery.getInstance().insert(str, i2, str2, SearchMyQuery.CREATE_TYPE.MY_QUERY, (MediaItem) null);
    }

    public static void deleteTop5() {
        SearchMyQuery.getInstance().deleteByCreateType(SearchMyQuery.CREATE_TYPE.TOP5.ordinal());
    }

    private static int getInt(Cursor cursor, int i2, int i7) {
        if (i2 < 0) {
            return i7;
        }
        return cursor.getInt(i2);
    }

    public static MyQueryInterface getInterface() {
        return MY_QUERY_INTERFACE;
    }

    private static String getMainCategoryBy(int i2, String str) {
        if (i2 == SearchMyQuery.CREATE_TYPE.MY_QUERY.ordinal()) {
            return "My query";
        }
        return str;
    }

    public static HashMap<Integer, String> getMyQueryFilterData() {
        Cursor myQueryCursor;
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            myQueryCursor = SearchMyQuery.getInstance().getMyQueryCursor();
            if (myQueryCursor != null) {
                if (myQueryCursor.moveToFirst()) {
                    do {
                        hashMap.put(Integer.valueOf(myQueryCursor.getInt(myQueryCursor.getColumnIndex("_id"))), myQueryCursor.getString(myQueryCursor.getColumnIndex("__filterData")));
                    } while (myQueryCursor.moveToNext());
                }
            }
            if (myQueryCursor != null) {
                myQueryCursor.close();
            }
            return hashMap;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getMyQueryFilterData failed e="), "MyQueryUtil");
            return hashMap;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static ArrayList<String> getMyQueryName() {
        Cursor myQueryCursor;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            myQueryCursor = SearchMyQuery.getInstance().getMyQueryCursor();
            if (myQueryCursor != null) {
                if (myQueryCursor.moveToFirst()) {
                    do {
                        arrayList.add(myQueryCursor.getString(myQueryCursor.getColumnIndex("__myQueryName")));
                    } while (myQueryCursor.moveToNext());
                }
            }
            if (myQueryCursor != null) {
                myQueryCursor.close();
            }
            return arrayList;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getMyQueryName failed e="), "MyQueryUtil");
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static String getString(Cursor cursor, int i2, String str) {
        if (i2 < 0) {
            return str;
        }
        return cursor.getString(i2);
    }

    public static ArrayList<String> getTop5List() {
        Cursor top5Cursor;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            top5Cursor = SearchMyQuery.getInstance().getTop5Cursor();
            if (top5Cursor != null) {
                if (top5Cursor.moveToFirst()) {
                    String string = top5Cursor.getString(top5Cursor.getColumnIndex("__filterData"));
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.addAll(Arrays.asList(string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
                    }
                }
            }
            if (top5Cursor != null) {
                top5Cursor.close();
            }
            return arrayList;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getTop5List failed e="), "MyQueryUtil");
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static boolean isMeIncludedOnCandidates(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList == null) {
            arrayList = getTop5List();
        }
        return arrayList2.stream().anyMatch(new C0392b(arrayList.get(0), 5));
    }

    public static boolean isTop5Creature(String str, ArrayList<String> arrayList) {
        return arrayList.stream().anyMatch(new C0392b(str, 4));
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isTop5Empty() {
        /*
            com.samsung.android.gallery.module.myquery.SearchMyQuery r0 = com.samsung.android.gallery.module.myquery.SearchMyQuery.getInstance()
            android.database.Cursor r0 = r0.getTop5Cursor()
            if (r0 == 0) goto L_0x001d
            int r1 = r0.getCount()     // Catch:{ all -> 0x0013 }
            if (r1 > 0) goto L_0x0011
            goto L_0x001d
        L_0x0011:
            r1 = 0
            goto L_0x001e
        L_0x0013:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0018 }
            goto L_0x001c
        L_0x0018:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001c:
            throw r1
        L_0x001d:
            r1 = 1
        L_0x001e:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.myquery.MyQueryUtil.isTop5Empty():boolean");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$mergeTop5List$3(ArrayList arrayList, Integer[] numArr, String str) {
        if (isTop5Creature(str, arrayList)) {
            if (numArr[0] == null) {
                numArr[0] = Integer.valueOf(arrayList.indexOf(str));
            }
            arrayList.remove(str);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$mergeTop5List$4(ArrayList arrayList, String str, Integer num) {
        arrayList.add(num.intValue(), str);
        updateTop5List(arrayList);
    }

    public static MediaItem load(Cursor cursor) {
        MyQueryColumn.initialize(cursor);
        MediaItem loadDefaultOnly = MyQueryItemLoader.loadDefaultOnly(cursor);
        if (loadDefaultOnly == null) {
            return null;
        }
        String string = getString(cursor, MyQueryColumn.FILTER_DATA.index, "");
        int i2 = getInt(cursor, MyQueryColumn.CREATE_TYPE.index, 0);
        SearchMyQuery.MyQueryData parse = MyQueryFilterDataUtil.parse(string);
        if (parse != null) {
            loadDefaultOnly.setCategory(getMainCategoryBy(i2, parse.getMainCategory()));
            loadDefaultOnly.setSubCategory(parse.getSubCategory());
            loadDefaultOnly.setTitle(parse.getMainFilterTitle());
            if (loadDefaultOnly.isCreature()) {
                CreatureData.of(loadDefaultOnly).creatureName = parse.getMainFilterTitle();
            }
            loadDefaultOnly.setExtra(ExtrasID.SELECTED_FILTER, parse.getSelectedFilter());
            loadDefaultOnly.setExtra(ExtrasID.MY_QUERY_MAIN_CATEGORY, parse.getMainCategory());
            loadDefaultOnly.setExtra(ExtrasID.MY_QUERY_TERM, parse.getTerm());
            if (TextUtils.equals(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE, parse.onlyThem())) {
                loadDefaultOnly.setExtra(ExtrasID.MY_QUERY_PEOPLE_ONLY_THEM, SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            }
            if (i2 != SearchMyQuery.CREATE_TYPE.MY_QUERY.ordinal()) {
                loadDefaultOnly.setExtra(ExtrasID.CREATURE_RECOMMENDED_IDS, MyQueryFilterDataUtil.getCreatureIdentityInfoList(parse.getMainCategory(), parse.getSubCategory(), parse.getSelectedFilter()));
            }
        }
        return loadDefaultOnly;
    }

    /* access modifiers changed from: private */
    public static void mergeTop5List(ArrayList<String> arrayList, String str) {
        ArrayList<String> top5List = getTop5List();
        if (!top5List.isEmpty()) {
            String str2 = top5List.get(0);
            if (isTop5Creature(str, top5List)) {
                if (isMeIncludedOnCandidates(top5List, arrayList)) {
                    arrayList.remove(str2);
                    top5List.remove(str2);
                    top5List.remove(str);
                    top5List.add(0, str);
                }
                removeTop5List(top5List, arrayList);
            } else if (isMeIncludedOnCandidates(top5List, arrayList)) {
                arrayList.remove(str2);
                top5List.remove(str2);
                top5List.add(0, str);
                removeTop5List(top5List, arrayList);
            } else {
                Integer[] numArr = {null};
                arrayList.forEach(new A(21, (Object) top5List, (Object) numArr));
                Optional.ofNullable(numArr[0]).ifPresent(new A(22, (Object) top5List, str));
            }
        }
    }

    public static void removeTop5List(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList == null) {
            arrayList = getTop5List();
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String next = it.next();
            if (i2 != 0 || !arrayList2.stream().anyMatch(new C0392b(next, 2))) {
                arrayList2.stream().filter(new C0392b(next, 3)).findAny().ifPresent(new f4.a(arrayList3, 17));
                i2++;
            } else {
                deleteTop5();
                return;
            }
        }
        if (!arrayList3.isEmpty()) {
            arrayList.removeAll(arrayList3);
        }
        updateTop5List(arrayList);
    }

    public static void replaceUnifiedId(String str, String str2) {
        Throwable th;
        SearchMyQuery.MyQueryData parse;
        Cursor cursorWithUnifiedId = SearchMyQuery.getInstance().getCursorWithUnifiedId(str);
        if (cursorWithUnifiedId != null) {
            try {
                if (cursorWithUnifiedId.moveToFirst()) {
                    do {
                        int i2 = cursorWithUnifiedId.getInt(cursorWithUnifiedId.getColumnIndex(MyQueryTable.CREATE_TYPE_ENTRY));
                        String string = cursorWithUnifiedId.getString(cursorWithUnifiedId.getColumnIndex("__filterData"));
                        String replace = string.replace(str, str2);
                        if (TextUtils.equals(string, replace) && i2 == SearchMyQuery.CREATE_TYPE.MY_QUERY.ordinal() && (parse = MyQueryFilterDataUtil.parse(replace)) != null) {
                            String replace2 = parse.selectedFilter.replace(String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str)), String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str2)));
                            parse.selectedFilter = replace2;
                            replace = MyQueryFilterDataUtil.composeFilterData(parse.mainFilterTitle, parse.mainCategory, parse.subCategory, replace2, parse.term, parse.onlyThem);
                        }
                        Log.d("MyQueryUtil", "replaceUnifiedId from " + string + "\nto " + replace);
                        SearchMyQuery.getInstance().updateFilterData(cursorWithUnifiedId.getInt(cursorWithUnifiedId.getColumnIndex("_id")), replace);
                    } while (cursorWithUnifiedId.moveToNext());
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (cursorWithUnifiedId != null) {
            cursorWithUnifiedId.close();
            return;
        }
        return;
        throw th;
    }

    public static void updateTop5List(ArrayList<String> arrayList) {
        Log.d("MyQueryUtil", "updateTop5List top5List {" + arrayList + "}");
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new e(stringJoiner, 2));
        SearchMyQuery.getInstance().updateTop5(stringJoiner.toString());
    }
}
