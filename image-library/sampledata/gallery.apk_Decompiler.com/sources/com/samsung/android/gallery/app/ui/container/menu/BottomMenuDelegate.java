package com.samsung.android.gallery.app.ui.container.menu;

import android.text.TextUtils;
import android.view.MenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.c;
import com.google.android.material.tabs.d;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.bottom.BottomTabLayout;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomMenuDelegate {
    private Blackboard mBlackboard;
    private final ArrayList<MenuItem> mBottomMenu = new ArrayList<>();

    public BottomMenuDelegate(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private void addTab(BottomTabLayout bottomTabLayout, MenuItem menuItem, int i2) {
        boolean z;
        c cachedTab = LayoutCache.getInstance().getCachedTab();
        if (cachedTab == null || !bottomTabLayout.equals(cachedTab.f)) {
            cachedTab = bottomTabLayout.newTab();
        }
        cachedTab.f1501a = Integer.valueOf(menuItem.getItemId());
        CharSequence title = menuItem.getTitle();
        if (TextUtils.isEmpty((CharSequence) null) && !TextUtils.isEmpty(title)) {
            cachedTab.g.setContentDescription(title);
        }
        cachedTab.f1502c = title;
        d dVar = cachedTab.g;
        boolean z3 = false;
        if (dVar != null) {
            dVar.e();
            c cVar = dVar.d;
            if (cVar != null) {
                TabLayout tabLayout = cVar.f;
                if (tabLayout != null) {
                    int selectedTabPosition = tabLayout.getSelectedTabPosition();
                    if (selectedTabPosition != -1 && selectedTabPosition == cVar.d) {
                        z = true;
                        dVar.setSelected(z);
                    }
                } else {
                    throw new IllegalArgumentException("Tab not attached to a TabLayout");
                }
            }
            z = false;
            dVar.setSelected(z);
        }
        cachedTab.b(menuItem.getIcon());
        if (menuItem.getItemId() == i2) {
            z3 = true;
        }
        bottomTabLayout.addTab(cachedTab, z3);
    }

    private void composeBottomTabMenu(MenuBuilder menuBuilder) {
        this.mBottomMenu.clear();
        for (int i2 = 0; i2 < menuBuilder.size(); i2++) {
            MenuItem item = menuBuilder.getItem(i2);
            if (supportMenu(item.getItemId())) {
                this.mBottomMenu.add(item);
            }
        }
    }

    private boolean isAlbumSecondDepth(String str) {
        if (TextUtils.equals(str, "location://albums/fileList") || TextUtils.equals(str, "location://folder/root")) {
            return true;
        }
        return false;
    }

    private void removeTab(BottomTabLayout bottomTabLayout, c cVar) {
        bottomTabLayout.removeTab(cVar);
    }

    public void destroy() {
        this.mBlackboard = null;
    }

    public int getId(String str) {
        int idByLocationKey = TabMenuHelper.getIdByLocationKey(str);
        if (idByLocationKey != -1 || !isAlbumSecondDepth(ArgumentsUtil.removeArgs(str))) {
            return idByLocationKey;
        }
        return R.id.action_albums;
    }

    public void invalidate(BottomTabLayout bottomTabLayout, String str) {
        Trace.beginSection("invalidate");
        int idByLocationKey = TabMenuHelper.getIdByLocationKey(str);
        Iterator<MenuItem> it = this.mBottomMenu.iterator();
        while (it.hasNext()) {
            MenuItem next = it.next();
            c findTab = bottomTabLayout.findTab(next.getItemId());
            if (findTab == null && next.isVisible()) {
                addTab(bottomTabLayout, next, idByLocationKey);
            } else if (findTab != null && !next.isVisible()) {
                removeTab(bottomTabLayout, findTab);
            }
        }
        bottomTabLayout.invalidateTabLayout();
        Trace.endSection();
    }

    public void load() {
        if (this.mBlackboard != null && this.mBottomMenu.isEmpty()) {
            FragmentActivity fragmentActivity = (FragmentActivity) this.mBlackboard.read("data://activity");
            if (fragmentActivity == null) {
                Log.e("BottomMenuDelegate", "fail to load bottom menu");
                return;
            }
            MenuBuilder menuBuilder = new MenuBuilder(fragmentActivity);
            fragmentActivity.getMenuInflater().inflate(TabMenuHelper.getBottomMenuIds(PickerUtil.isNormalLaunchMode(this.mBlackboard)), menuBuilder);
            composeBottomTabMenu(menuBuilder);
        }
    }

    public boolean supportMenu(int i2) {
        if (i2 == R.id.action_menu_list) {
            return PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU;
        }
        if (i2 == R.id.action_stories) {
            if (!Features.isEnabled(Features.SUPPORT_STORY) || Features.isEnabled(Features.IS_UPSM) || PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
                return false;
            }
            return true;
        } else if (i2 == R.id.action_collection) {
            return PreferenceFeatures.OneUi8x.COLLECTION_TAB;
        } else {
            if (i2 == R.id.action_mtp) {
                return !PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU;
            }
            return true;
        }
    }

    public boolean updateTabVisibility(String str, boolean z) {
        if (this.mBottomMenu.isEmpty()) {
            return false;
        }
        int idByLocationKey = TabMenuHelper.getIdByLocationKey(str);
        Iterator<MenuItem> it = this.mBottomMenu.iterator();
        while (it.hasNext()) {
            MenuItem next = it.next();
            if (next.getItemId() == idByLocationKey) {
                next.setVisible(z);
                return true;
            }
        }
        return false;
    }
}
