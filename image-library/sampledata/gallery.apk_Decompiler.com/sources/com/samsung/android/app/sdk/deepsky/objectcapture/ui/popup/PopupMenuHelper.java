package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import a6.C0419b;
import android.view.MenuItem;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarActionListener;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000o\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005*\u00012\b\u0000\u0018\u0000 52\u00020\u0001:\u00015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ%\u0010\u000f\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001e\u001a\u00020\b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000b¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010!\u001a\u00020 2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010#R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001c0$8\u0002X\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010(R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00060)8\u0002X\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010-\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0004¢\u0006\u0006\n\u0004\b3\u00104¨\u00066"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupMenuHelper;", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "popup", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;)V", "Landroid/view/MenuItem;", "menuItem", "Lme/x;", "onMenuItemClick", "(Landroid/view/MenuItem;)V", "", "menuItems", "Landroid/view/MenuItem$OnMenuItemClickListener;", "menuItemClickListener", "updateMenuItems", "(Ljava/util/List;Landroid/view/MenuItem$OnMenuItemClickListener;)V", "clickListener", "setOnMenuItemClickListener", "(Landroid/view/MenuItem$OnMenuItemClickListener;)V", "Landroid/view/View;", "menuItemButton", "setButtonTagAndClickListener", "(Landroid/view/View;Landroid/view/MenuItem;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;", "listener", "setToolbarActionListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuItem;", "list", "setToolbarMenuItem", "(Ljava/util/List;)V", "", "isLayoutRequired", "(Ljava/util/List;)Z", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "", "toolbarMenuItems", "Ljava/util/List;", "toolbarActionListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/MenuItemDao;", "menuItemsMap", "Ljava/util/Map;", "onMenuItemClickListener", "Landroid/view/MenuItem$OnMenuItemClickListener;", "Landroid/view/View$OnClickListener;", "menuItemButtonOnClickListener", "Landroid/view/View$OnClickListener;", "com/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupMenuHelper$accessibilityDelegate$1", "accessibilityDelegate", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupMenuHelper$accessibilityDelegate$1;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PopupMenuHelper {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "PopupMenuHelper";
    private final PopupMenuHelper$accessibilityDelegate$1 accessibilityDelegate = new PopupMenuHelper$accessibilityDelegate$1();
    private final View.OnClickListener menuItemButtonOnClickListener = new C0419b(7, this);
    private final Map<MenuItemDao, MenuItem> menuItemsMap = new LinkedHashMap();
    private MenuItem.OnMenuItemClickListener onMenuItemClickListener;
    private final ObjectCapturePopup popup;
    private ToolbarActionListener toolbarActionListener;
    private final List<ToolbarMenuItem> toolbarMenuItems = new ArrayList();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/PopupMenuHelper$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public PopupMenuHelper(ObjectCapturePopup objectCapturePopup) {
        j.e(objectCapturePopup, "popup");
        this.popup = objectCapturePopup;
    }

    /* access modifiers changed from: private */
    public static final void menuItemButtonOnClickListener$lambda$0(PopupMenuHelper popupMenuHelper, View view) {
        MenuItemDao menuItemDao;
        MenuItem menuItem;
        j.e(view, "v");
        if (popupMenuHelper.onMenuItemClickListener == null || popupMenuHelper.popup.isDiscardTouch()) {
            MenuItem.OnMenuItemClickListener onMenuItemClickListener2 = popupMenuHelper.onMenuItemClickListener;
            boolean isDiscardTouch = popupMenuHelper.popup.isDiscardTouch();
            LibLogger.w(TAG, "menu item click skipped: " + onMenuItemClickListener2 + ArcCommonLog.TAG_COMMA + isDiscardTouch);
            return;
        }
        Object tag = view.getTag();
        if (tag instanceof MenuItemDao) {
            menuItemDao = (MenuItemDao) tag;
        } else {
            menuItemDao = null;
        }
        if (menuItemDao != null && (menuItem = popupMenuHelper.menuItemsMap.get(menuItemDao)) != null) {
            popupMenuHelper.onMenuItemClick(menuItem);
        }
    }

    public final boolean isLayoutRequired(List<? extends MenuItem> list) {
        j.e(list, "menuItems");
        return !MenuItemDao.Companion.collectionEquals(list, this.menuItemsMap.values());
    }

    public final void onMenuItemClick(MenuItem menuItem) {
        j.e(menuItem, "menuItem");
        Iterable<ToolbarMenuItem> iterable = this.toolbarMenuItems;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (ToolbarMenuItem toolbarMenuItem : iterable) {
                if (toolbarMenuItem.getId() == menuItem.getItemId() && toolbarMenuItem.getUseDefaultOption()) {
                    int itemId = menuItem.getItemId();
                    ToolbarActionListener toolbarActionListener2 = this.toolbarActionListener;
                    MenuItem.OnMenuItemClickListener onMenuItemClickListener2 = this.onMenuItemClickListener;
                    LibLogger.d(TAG, "onMenuItemClick click : " + itemId + ", listeners = " + toolbarActionListener2 + ArcCommonLog.TAG_COMMA + onMenuItemClickListener2);
                    switch (menuItem.getItemId()) {
                        case 0:
                            ToolbarActionListener toolbarActionListener3 = this.toolbarActionListener;
                            if (toolbarActionListener3 != null) {
                                toolbarActionListener3.copy();
                                break;
                            }
                            break;
                        case 1:
                            ToolbarActionListener toolbarActionListener4 = this.toolbarActionListener;
                            if (toolbarActionListener4 != null) {
                                toolbarActionListener4.share();
                                break;
                            }
                            break;
                        case 2:
                            ToolbarActionListener toolbarActionListener5 = this.toolbarActionListener;
                            if (toolbarActionListener5 != null) {
                                toolbarActionListener5.saveAsImage();
                                break;
                            }
                            break;
                        case 3:
                            ToolbarActionListener toolbarActionListener6 = this.toolbarActionListener;
                            if (toolbarActionListener6 != null) {
                                toolbarActionListener6.saveAsSticker();
                                break;
                            }
                            break;
                        case 4:
                            ToolbarActionListener toolbarActionListener7 = this.toolbarActionListener;
                            if (toolbarActionListener7 != null) {
                                toolbarActionListener7.edit();
                                break;
                            }
                            break;
                        case 5:
                            ToolbarActionListener toolbarActionListener8 = this.toolbarActionListener;
                            if (toolbarActionListener8 != null) {
                                toolbarActionListener8.selectAll();
                                break;
                            }
                            break;
                        case 6:
                            ToolbarActionListener toolbarActionListener9 = this.toolbarActionListener;
                            if (toolbarActionListener9 != null) {
                                toolbarActionListener9.saveAsGif();
                                break;
                            }
                            break;
                    }
                    MenuItem.OnMenuItemClickListener onMenuItemClickListener3 = this.onMenuItemClickListener;
                    if (onMenuItemClickListener3 != null) {
                        onMenuItemClickListener3.onMenuItemClick(menuItem);
                        return;
                    }
                    return;
                }
            }
        }
        LibLogger.w(TAG, "Not useDefaultOption: onMenuItemClick return");
        MenuItem.OnMenuItemClickListener onMenuItemClickListener4 = this.onMenuItemClickListener;
        if (onMenuItemClickListener4 != null) {
            onMenuItemClickListener4.onMenuItemClick(menuItem);
        }
    }

    public final void setButtonTagAndClickListener(View view, MenuItem menuItem) {
        j.e(view, "menuItemButton");
        j.e(menuItem, "menuItem");
        view.setTag(MenuItemDao.Companion.of(menuItem));
        view.setAccessibilityDelegate(this.accessibilityDelegate);
        view.setOnClickListener(this.menuItemButtonOnClickListener);
    }

    public final void setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener2) {
        this.onMenuItemClickListener = onMenuItemClickListener2;
    }

    public final void setToolbarActionListener(ToolbarActionListener toolbarActionListener2) {
        this.toolbarActionListener = toolbarActionListener2;
    }

    public final void setToolbarMenuItem(List<ToolbarMenuItem> list) {
        j.e(list, "list");
        this.toolbarMenuItems.clear();
        this.toolbarMenuItems.addAll(list);
    }

    public final void updateMenuItems(List<? extends MenuItem> list, MenuItem.OnMenuItemClickListener onMenuItemClickListener2) {
        j.e(list, "menuItems");
        this.menuItemsMap.clear();
        for (MenuItem menuItem : list) {
            this.menuItemsMap.put(MenuItemDao.Companion.of(menuItem), menuItem);
        }
        this.onMenuItemClickListener = onMenuItemClickListener2;
    }
}
