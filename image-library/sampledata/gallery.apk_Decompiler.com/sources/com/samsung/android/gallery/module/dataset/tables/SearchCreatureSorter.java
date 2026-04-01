package com.samsung.android.gallery.module.dataset.tables;

import T3.a;
import U9.b;
import Z8.d;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCreatureSorter extends SearchSorter {
    public SearchCreatureSorter(String str) {
        super(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$sort$0(String str) {
        return str.split(NumericEnum.SEP, 2)[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sort$2(ArrayList arrayList, AbstractSorter.SortData sortData) {
        ArrayList<AbstractSorter.SortData> arrayList2 = this.mSortedList;
        arrayList.add(0, arrayList2.remove(arrayList2.indexOf(sortData)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sort$3(ArrayList arrayList, String str) {
        this.mSortedList.stream().filter(new a(3, this, str)).findFirst().ifPresent(new d(this, arrayList, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sort$4(AbstractSorter.SortData sortData) {
        this.mSortedList.add(0, sortData);
    }

    /* renamed from: equalsData */
    public boolean lambda$sort$1(AbstractSorter.SortData sortData, String str) {
        String[] strArr = (String[]) sortData.getSortingValue();
        return TextUtils.equals(str, strArr[2] + NumericEnum.SEP + strArr[3]);
    }

    public void sort(Cursor cursor) {
        ArrayList arrayList;
        super.sort(cursor);
        synchronized (this.mSortedList) {
            try {
                Object obj = this.mPriorityValue;
                if (obj instanceof ArrayList) {
                    arrayList = (ArrayList) obj;
                } else {
                    arrayList = null;
                }
                if (arrayList != null && !arrayList.isEmpty()) {
                    List list = (List) arrayList.stream().map(new V8.a(17)).collect(Collectors.toList());
                    ArrayList arrayList2 = new ArrayList();
                    if (!list.isEmpty()) {
                        list.forEach(new d(this, arrayList2, 0));
                    }
                    arrayList2.forEach(new b(19, this));
                }
            } catch (Exception e) {
                Log.e((CharSequence) getClass().getSimpleName(), "sort failed", (Throwable) e);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
