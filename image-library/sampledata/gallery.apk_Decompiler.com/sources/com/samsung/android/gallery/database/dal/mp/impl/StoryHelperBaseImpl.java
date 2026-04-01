package com.samsung.android.gallery.database.dal.mp.impl;

import A.a;
import Vb.b;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class StoryHelperBaseImpl extends BaseImpl {
    protected static final Uri CMH_AUTHORITY_URI = Uri.parse("content://secmedia/cmh");
    protected static final Uri CMH_STORY_HIDE_RULE = Uri.parse("content://secmedia/cmh/hide_rule");
    protected static final Uri CMH_STORY_TABLE_URI = Uri.parse("content://secmedia/cmh/story");

    public StoryHelperBaseImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList, int i2) {
        return applyBatch(arrayList, "location://story/albums", i2);
    }

    public ContentProviderResult[] applyBatchToHideRule(ArrayList<ContentProviderOperation> arrayList, int i2) {
        return applyBatch(arrayList, "location://stories/hideRule", i2);
    }

    public int delete(String str, String[] strArr) {
        return delete(CMH_STORY_TABLE_URI, str, strArr, "location://story/albums");
    }

    public int deleteItems(int i2, String str, String[] strArr) {
        return delete(Uri.parse("content://secmedia/cmh/story/" + i2), str, strArr, "location://story/albums");
    }

    public int deleteToHideRule(ContentValues contentValues, String str, String[] strArr) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int update = getContentResolver().update(CMH_STORY_HIDE_RULE, contentValues, str, strArr);
            if (update > 0) {
                new CmhNotifier("location://stories/hideRule").setType(2).invoke();
            }
            String str2 = this.TAG;
            Log.d(str2, "deleteHideRule" + Logger.vt(Integer.valueOf(update), Long.valueOf(currentTimeMillis)));
            return update;
        } catch (Exception e) {
            String str3 = this.TAG;
            Log.d(str3, "deleteHideRule failed. e=" + e.getMessage());
            return -1;
        }
    }

    public Uri insert(ContentValues contentValues) {
        return insert(CMH_STORY_TABLE_URI, contentValues, "location://story/albums");
    }

    public Uri insertToHideRule(ContentValues contentValues) {
        return insert(CMH_STORY_HIDE_RULE, contentValues, "location://stories/hideRule");
    }

    public int update(ContentValues contentValues, String str, String[] strArr) {
        return update(CMH_STORY_TABLE_URI, contentValues, str, strArr, "location://story/albums", (ArrayList<Integer>) null);
    }

    public int updateToHideRule(ContentValues contentValues, String str, String[] strArr, ArrayList<Integer> arrayList) {
        return update(CMH_STORY_HIDE_RULE, contentValues, str, strArr, "location://stories/hideRule", arrayList);
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList, String str, int i2) {
        try {
            ContentResolver contentResolver = getContentResolver();
            String authority = CMH_AUTHORITY_URI.getAuthority();
            Objects.requireNonNull(authority);
            ContentProviderResult[] applyBatch = contentResolver.applyBatch(authority, arrayList);
            new CmhNotifier(str).setType(i2).invoke();
            return applyBatch;
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.d(str2, "MpCmhApplyBatch failed. e=" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public int delete(Uri uri, String str, String[] strArr, String str2) {
        try {
            int delete = getContentResolver().delete(uri, str, strArr);
            if (delete <= 0) {
                return delete;
            }
            new CmhNotifier(str2).setType(2).invoke();
            return delete;
        } catch (Exception e) {
            String str3 = this.TAG;
            Log.d(str3, "MpCmhDelete failed. e=" + e.getMessage());
            return -1;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues, String str) {
        try {
            Uri insert = getContentResolver().insert(uri, contentValues);
            if (insert == null) {
                return insert;
            }
            new CmhNotifier(str).setType(0).invoke();
            return insert;
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.d(str2, "MpCmhInsert failed. e=" + e.getMessage());
            return null;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr, String str2, ArrayList<Integer> arrayList) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int update = getContentResolver().update(uri, contentValues, str, strArr);
            if (update > 0) {
                new CmhNotifier(str2).withIds(arrayList).setType(1).invoke();
            }
            String str3 = this.TAG;
            Log.d(str3, "MpCmhUpdate" + Logger.vt(Integer.valueOf(update), Long.valueOf(currentTimeMillis)));
            return update;
        } catch (Exception e) {
            String str4 = this.TAG;
            Log.d(str4, "MpCmhUpdate failed. e=" + e.getMessage());
            return -1;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CmhNotifier {
        private static final List<Job> sJobs = Collections.synchronizedList(new ArrayList());
        private static final HashMap<String, HashMap<Integer, String>> sMethod;
        /* access modifiers changed from: private */
        public Bundle extra;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public String method;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Job {
            final Bundle extra;
            final int extraKey;
            final String key;
            final String method;

            public Job(CmhNotifier cmhNotifier) {
                int i2;
                this.key = cmhNotifier.key;
                this.method = cmhNotifier.method;
                Bundle d = cmhNotifier.extra;
                this.extra = d;
                if (d != null) {
                    i2 = d.hashCode();
                } else {
                    i2 = -1;
                }
                this.extraKey = i2;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Job) || hashCode() != obj.hashCode()) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return (this.key + this.method + this.extra).hashCode();
            }
        }

        static {
            HashMap<String, HashMap<Integer, String>> hashMap = new HashMap<>();
            sMethod = hashMap;
            hashMap.put("location://story/albums", new HashMap<Integer, String>() {
                {
                    put(0, "story_update");
                    put(1, "story_update");
                    put(2, "");
                }
            });
            hashMap.put("location://stories/hideRule", new HashMap<Integer, String>() {
                {
                    put(0, "hide_rule_insert");
                    put(1, "hide_rule_update");
                    put(2, "hide_rule_delete");
                }
            });
        }

        public CmhNotifier(String str) {
            this.key = str;
        }

        private void addJob(Job job) {
            List<Job> list = sJobs;
            if (!list.contains(job)) {
                list.add(job);
            }
        }

        /* access modifiers changed from: private */
        public void flushJobs() {
            List<Job> list = sJobs;
            ArrayList arrayList = new ArrayList(list);
            list.clear();
            arrayList.forEach(new a(this));
        }

        private Bundle getExtra() {
            if (this.extra == null) {
                this.extra = new Bundle();
            }
            return this.extra;
        }

        private String getMethod(int i2) {
            return (String) Optional.ofNullable(sMethod.get(this.key)).map(new b(i2, 4)).orElse((Object) null);
        }

        /* access modifiers changed from: private */
        public void invoke() {
            boolean isEmpty = sJobs.isEmpty();
            addJob(new Job(this));
            if (isEmpty) {
                ThreadUtil.postOnBgThreadDelayed(new e(0, this), 300);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$flushJobs$1(Job job) {
            invoke(job.method, (String) null, job.extra);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String lambda$getMethod$0(int i2, HashMap hashMap) {
            return (String) hashMap.get(Integer.valueOf(i2));
        }

        public CmhNotifier setType(int i2) {
            this.method = getMethod(i2);
            return this;
        }

        public CmhNotifier withIds(ArrayList<Integer> arrayList) {
            if (arrayList != null && arrayList.isEmpty()) {
                getExtra().putIntegerArrayList("RULE_ID", arrayList);
            }
            return this;
        }

        private void invoke(String str, String str2, Bundle bundle) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!TextUtils.isEmpty(str)) {
                try {
                    AppResources.getAppContext().getContentResolver().call("com.samsung.storygenprovider", str, str2, bundle);
                } catch (Exception e) {
                    a.s(e, new StringBuilder("invoke failed e="), "CmhNotifier");
                }
                StringBuilder sb2 = new StringBuilder("invoke");
                String l = C0212a.l("method=", str);
                StringBuilder sb3 = new StringBuilder("extra=");
                sb3.append(bundle != null);
                a.A(new Object[]{l, sb3.toString(), Long.valueOf(currentTimeMillis)}, sb2, "CmhNotifier");
            }
        }
    }
}
