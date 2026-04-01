package com.samsung.android.gallery.widget.bottom;

import android.view.Menu;
import android.view.MenuItem;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import k2.q;
import nb.C0702c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomBarData {
    public boolean allSelected;
    public InitListener initListener;
    public ArrayList<MenuItem> items;
    public ArrayList<MenuItem> itemsMore;
    public RecyclerView listView;
    public q listener;
    public String locationKey;
    public int menuResId;
    public boolean showDeleteAll;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface InitListener {
        void onInit(String str, BottomBar bottomBar);
    }

    public boolean containsAll(Menu menu) {
        if (menu != null) {
            HashSet hashSet = new HashSet();
            ArrayList<MenuItem> arrayList = this.items;
            if (arrayList != null) {
                arrayList.forEach(new C0702c(hashSet, 0));
            }
            ArrayList<MenuItem> arrayList2 = this.itemsMore;
            if (arrayList2 != null) {
                arrayList2.forEach(new C0702c(hashSet, 1));
            }
            int i2 = 0;
            while (i2 < menu.size()) {
                if (hashSet.contains(Integer.valueOf(menu.getItem(i2).getItemId()))) {
                    i2++;
                }
            }
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BottomBarData) {
            BottomBarData bottomBarData = (BottomBarData) obj;
            if (this.menuResId != bottomBarData.menuResId || !equals(this.items, bottomBarData.items) || !equals(this.itemsMore, bottomBarData.itemsMore)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        int i2;
        StringBuilder sb2 = new StringBuilder("BottomBarData[");
        if (this.allSelected) {
            str = "a";
        } else {
            str = "p";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList<MenuItem> arrayList = this.items;
        int i7 = 0;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        sb2.append(i2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList<MenuItem> arrayList2 = this.itemsMore;
        if (arrayList2 != null) {
            i7 = arrayList2.size();
        }
        return C0086a.l(sb2, i7, "]");
    }

    private boolean equals(List<MenuItem> list, List<MenuItem> list2) {
        if (list == null || list2 == null || list.size() != list2.size()) {
            return list == null && list2 == null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).getItemId() != list2.get(i2).getItemId()) {
                return false;
            }
        }
        return true;
    }
}
