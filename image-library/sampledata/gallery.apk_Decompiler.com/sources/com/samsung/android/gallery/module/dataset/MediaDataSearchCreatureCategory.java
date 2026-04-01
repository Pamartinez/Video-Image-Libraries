package com.samsung.android.gallery.module.dataset;

import Ad.C0720a;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.module.dataset.tables.SearchCreatureSorter;
import com.samsung.android.gallery.module.dataset.tables.SearchSorter;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchCreatureCategory extends MediaDataSearchCategory {
    private ArrayList<String> mTop5Items;

    public MediaDataSearchCreatureCategory(Blackboard blackboard, String str) {
        this(blackboard, str, false);
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Object, java.lang.Runnable] */
    private void checkTop5Validity() {
        try {
            if (this.mSorter != null && !this.mTop5Items.isEmpty()) {
                ArrayList<String> arrayList = new ArrayList<>();
                this.mTop5Items.forEach(new I(4, this, arrayList));
                if (!arrayList.isEmpty()) {
                    if (TextUtils.equals(this.mTop5Items.get(0), arrayList.get(0))) {
                        if (this.mTop5Items.size() != arrayList.size()) {
                            this.mTop5Items = arrayList;
                            SimpleThreadPool.getInstance().execute(new C0605j(arrayList, 1));
                            return;
                        }
                        return;
                    }
                }
                this.mTop5Items.clear();
                SimpleThreadPool.getInstance().execute(new Object());
            }
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "checkTop5Validity failed", (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkTop5Validity$0(ArrayList arrayList, String str) {
        Iterator<AbstractSorter.SortData> it = this.mSorter.getSortedList().iterator();
        while (it.hasNext()) {
            String[] strArr = (String[]) it.next().getSortingValue();
            String str2 = str.split(NumericEnum.SEP, 2)[1];
            if (TextUtils.equals(str2, strArr[2] + NumericEnum.SEP + strArr[3])) {
                arrayList.add(str);
                return;
            }
        }
    }

    public ArrayList<String> getPriorityItems() {
        return this.mTop5Items;
    }

    public SearchSorter getSearchSorter() {
        return new SearchCreatureSorter(this.mLocationKey);
    }

    public void sort(Cursor[] cursorArr) {
        super.sort(cursorArr);
        checkTop5Validity();
    }

    public void swap(Cursor[] cursorArr) {
        if (this.mSorter != null) {
            if (!this.mBlackboard.isEmpty("data:///SearchTop5Creature")) {
                this.mTop5Items = (ArrayList) Arrays.stream(((String) this.mBlackboard.read("data:///SearchTop5Creature", "")).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).collect(Collectors.toList());
            } else {
                this.mTop5Items = MyQueryUtil.getTop5List();
            }
            ArrayList<String> arrayList = (ArrayList) this.mTop5Items.stream().limit((long) Math.min(this.mTop5Items.size(), 6)).collect(Collectors.toCollection(new C0720a(1)));
            this.mTop5Items = arrayList;
            this.mSorter.setPriorityValues(arrayList);
        }
        super.swap(cursorArr);
    }

    public MediaDataSearchCreatureCategory(Blackboard blackboard, String str, boolean z) {
        super(blackboard, str, z);
        this.mTop5Items = new ArrayList<>();
    }
}
