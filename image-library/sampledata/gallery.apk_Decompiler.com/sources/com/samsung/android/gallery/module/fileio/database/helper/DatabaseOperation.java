package com.samsung.android.gallery.module.fileio.database.helper;

import A4.J;
import T8.C0578a;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.scsp.media.api.d;
import db.a;
import h9.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DatabaseOperation {
    private static volatile DatabaseOperation sInstance;
    private WeakReference<Context> mContextRef;
    private final ConcurrentHashMap<Integer, Map<String, ArrayList<Object>>> mOperationMap = new ConcurrentHashMap<>();

    private DatabaseOperation(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    private void addMyTagValue(String str, FileItemInterface fileItemInterface, String str2) {
        MyTagCopyOperation.getInstance().addValues(str, fileItemInterface, str2);
    }

    private void addValuesWithApply(DatabaseOpObject databaseOpObject, ContentValues... contentValuesArr) {
        boolean z = databaseOpObject.mImmediateMode;
        int i2 = (z ? 10 : 0) + 1;
        if (z) {
            immediateApply(i2, databaseOpObject.mUri, databaseOpObject.mTagDataArgs, contentValuesArr);
            return;
        }
        ArrayList arrayList = (ArrayList) this.mOperationMap.computeIfAbsent(Integer.valueOf(i2), new d(25)).computeIfAbsent(databaseOpObject.mUri.toString(), new d(26));
        arrayList.addAll(Arrays.asList(contentValuesArr));
        if (arrayList.size() >= 50) {
            applyReady(i2);
        }
    }

    private void applier(Context context, int i2, String str, Object obj, Object obj2) {
        int i7;
        int i8;
        int originType = getOriginType(i2);
        if (originType == 1) {
            try {
                ContentValues[] contentValuesArr = (ContentValues[]) obj2;
                i8 = context.getContentResolver().bulkInsert(Uri.parse(str), contentValuesArr);
                i7 = contentValuesArr.length;
            } catch (Exception e) {
                Log.e("DatabaseOperation", "[DB App Exception][m :" + e.getMessage() + "]");
            }
        } else {
            ArrayList arrayList = (ArrayList) obj2;
            i8 = context.getContentResolver().applyBatch(str, arrayList).length;
            i7 = arrayList.size();
        }
        if (i8 != i7) {
            Log.e("DatabaseOperation", "[DB APPLY FAIL][t : " + originType + "][s : " + i7 + "][e : " + i8 + "]");
        }
        if (originType != 1 && originType != 2) {
            return;
        }
        if (!isImmediate(i2)) {
            MyTagCopyOperation.getInstance().apply(str);
        } else if (obj != null) {
            Object[] objArr = (Object[]) obj;
            MyTagCopyOperation.getInstance().immediateApply((FileItemInterface) objArr[0], (String) objArr[1]);
        }
    }

    private void applyReady(int i2) {
        Optional.ofNullable(this.mOperationMap.get(Integer.valueOf(i2))).ifPresent(new J((Object) this, i2, 10));
    }

    private void applyRequest(int i2, String str, ArrayList<Object> arrayList) {
        Optional.ofNullable(getContext()).ifPresent(new a(this, i2, (ArrayList) arrayList, str));
    }

    private Context getContext() {
        return this.mContextRef.get();
    }

    public static DatabaseOperation getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DatabaseOperation.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new DatabaseOperation(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private int getOriginType(int i2) {
        if (isImmediate(i2)) {
            return i2 - 10;
        }
        return i2;
    }

    private void immediateApply(int i2, Uri uri, Object obj, Object obj2) {
        Optional.ofNullable(getContext()).ifPresent(new b(this, i2, obj2, uri, obj));
    }

    private boolean isImmediate(int i2) {
        if (i2 >= 10) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$add$8(DatabaseOpObject databaseOpObject, Context context) {
        Object obj;
        if (!databaseOpObject.mImmediateMode && (obj = databaseOpObject.mTagDataArgs) != null) {
            Object[] objArr = (Object[]) obj;
            addMyTagValue(databaseOpObject.mUri.toString(), (FileItemInterface) objArr[0], (String) objArr[1]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$add$9(DatabaseOpObject databaseOpObject, ContentProviderOperation.Builder builder) {
        Object[] objArr = (Object[]) databaseOpObject.mSelectionArgs;
        if (objArr != null) {
            builder.withSelection((String) objArr[0], (String[]) objArr[1]);
        }
        if (databaseOpObject.mType != 4) {
            builder.withValues((ContentValues) databaseOpObject.mDataArgs);
        }
        addValuesWithApply(databaseOpObject, builder.build());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$addValuesWithApply$3(Integer num) {
        return new HashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$addValuesWithApply$4(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$addValuesWithApply$5(Integer num) {
        return new HashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$addValuesWithApply$6(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyReady$2(int i2, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            ArrayList arrayList = (ArrayList) entry.getValue();
            if (!(str == null || arrayList == null || arrayList.isEmpty())) {
                applyRequest(i2, str, arrayList);
            }
        }
        map.clear();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ContentValues[] lambda$applyRequest$0(int i2) {
        return new ContentValues[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyRequest$1(int i2, ArrayList arrayList, String str, Context context) {
        Object[] objArr;
        if (getOriginType(i2) == 1) {
            objArr = arrayList.stream().toArray(new C0578a(18));
        } else {
            objArr = new ArrayList(arrayList);
        }
        applier(context, i2, str, (Object) null, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$immediateApply$7(int i2, Object obj, Uri uri, Object obj2, Context context) {
        ArrayList arrayList;
        String authority;
        int originType = getOriginType(i2);
        if (originType == 1) {
            arrayList = obj;
        } else {
            arrayList = new ArrayList(Arrays.asList((ContentProviderOperation[]) obj));
        }
        if (originType == 1) {
            authority = uri.toString();
        } else {
            authority = uri.getAuthority();
        }
        applier(context, i2, authority, obj2, arrayList);
    }

    public synchronized void add(DatabaseOpObject databaseOpObject) {
        ContentProviderOperation.Builder builder;
        try {
            Optional.ofNullable(getContext()).ifPresent(new h9.a(this, databaseOpObject, 0));
            int i2 = databaseOpObject.mType;
            if (i2 == 1) {
                Object obj = databaseOpObject.mDataArgs;
                if (obj instanceof ContentValues[]) {
                    addValuesWithApply(databaseOpObject, (ContentValues[]) obj);
                } else {
                    addValuesWithApply(databaseOpObject, (ContentValues) obj);
                }
            } else {
                Object obj2 = databaseOpObject.mDataArgs;
                if (obj2 instanceof ContentProviderOperation[]) {
                    addValuesWithApply(databaseOpObject, (ContentProviderOperation[]) obj2);
                } else {
                    if (i2 == 2) {
                        builder = ContentProviderOperation.newInsert(databaseOpObject.mUri);
                    } else if (i2 == 3) {
                        builder = ContentProviderOperation.newUpdate(databaseOpObject.mUri);
                    } else if (i2 == 4) {
                        builder = ContentProviderOperation.newDelete(databaseOpObject.mUri);
                    } else {
                        builder = null;
                    }
                    Optional.ofNullable(builder).ifPresent(new h9.a(this, databaseOpObject, 1));
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void apply() {
        applyReady(1);
        applyReady(2);
        applyReady(3);
        applyReady(4);
    }

    public void clear() {
        this.mOperationMap.clear();
        MyTagCopyOperation.getInstance().clear();
    }

    public void finishPreloadMyTag() {
        MyTagCopyOperation.getInstance().finishPreloadData();
    }

    public void quit() {
        apply();
        clear();
        MyTagCopyOperation.getInstance().clear();
    }

    public void startPreloadMyTag(FileItemInterface fileItemInterface) {
        MyTagCopyOperation.getInstance().startPreloadData(fileItemInterface);
    }

    private void addValuesWithApply(DatabaseOpObject databaseOpObject, ContentProviderOperation... contentProviderOperationArr) {
        int i2;
        int i7 = 0;
        ContentProviderOperation contentProviderOperation = contentProviderOperationArr[0];
        if (contentProviderOperation.isInsert()) {
            i2 = 2;
        } else if (contentProviderOperation.isUpdate()) {
            i2 = 3;
        } else {
            i2 = contentProviderOperation.isDelete() ? 4 : -1;
        }
        boolean z = databaseOpObject.mImmediateMode;
        if (z) {
            i7 = 10;
        }
        int i8 = i2 + i7;
        if (z) {
            immediateApply(i8, contentProviderOperation.getUri(), databaseOpObject.mTagDataArgs, contentProviderOperationArr);
            return;
        }
        ArrayList arrayList = (ArrayList) this.mOperationMap.computeIfAbsent(Integer.valueOf(i8), new d(27)).computeIfAbsent(contentProviderOperation.getUri().getAuthority(), new d(28));
        arrayList.addAll(Arrays.asList(contentProviderOperationArr));
        if (arrayList.size() >= 20) {
            applyReady(i8);
        }
    }
}
