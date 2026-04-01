package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import U5.b;
import android.content.Context;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import androidx.appcompat.view.menu.MenuBuilder;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 H2\u00020\u0001:\u0001HB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0003¢\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\bJ\u0015\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001e\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\u00062\b\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b\"\u0010#J\u0015\u0010&\u001a\u00020\u00002\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b&\u0010'J\u0015\u0010)\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\f¢\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0006¢\u0006\u0004\b+\u0010\bJ\r\u0010,\u001a\u00020\u0000¢\u0006\u0004\b,\u0010-J\u0015\u0010/\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0018¢\u0006\u0004\b/\u0010\u001aJ\r\u00100\u001a\u00020\u0006¢\u0006\u0004\b0\u0010\bJ\r\u00101\u001a\u00020\u0006¢\u0006\u0004\b1\u0010\bR\u0014\u00103\u001a\u0002028\u0002X\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0018\u00109\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020$8\u0002X\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010?R$\u0010B\u001a\u0012\u0012\u0004\u0012\u00020\u001c0@j\b\u0012\u0004\u0012\u00020\u001c`A8\u0002X\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010DR\u0014\u0010F\u001a\u00020E8\u0002X\u0004¢\u0006\u0006\n\u0004\bF\u0010G¨\u0006I"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar;", "", "Landroid/view/Window;", "window", "<init>", "(Landroid/view/Window;)V", "Lme/x;", "clearOnMenuItemClickListener", "()V", "doShow", "registerOrientationHandler", "unregisterOrientationHandler", "", "color", "setMenuTitleColor", "(I)V", "Landroid/view/Menu;", "menu", "addMenu", "(Landroid/view/Menu;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;", "listener", "setToolbarActionListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;)V", "", "useDefaultMenu", "(Z)V", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuItem;", "list", "setToolbarMenuList", "(Ljava/util/List;)V", "Landroid/view/MenuItem$OnMenuItemClickListener;", "menuItemClickListener", "setOnMenuItemClickListener", "(Landroid/view/MenuItem$OnMenuItemClickListener;)V", "Landroid/graphics/Rect;", "rect", "setContentRect", "(Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar;", "suggestedWidth", "setSuggestedWidth", "(I)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar;", "show", "updateLayout", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar;", "isFlexMode", "updateFlexMode", "dismiss", "hide", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Landroid/view/Window;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "popup", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "customMenu", "Landroid/view/Menu;", "contentRect", "Landroid/graphics/Rect;", "orientation", "I", "Z", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "toolbarMenuItemList", "Ljava/util/ArrayList;", "Landroid/view/MenuItem$OnMenuItemClickListener;", "Landroid/view/View$OnLayoutChangeListener;", "orientationChangeHandler", "Landroid/view/View$OnLayoutChangeListener;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCaptureToolbar {
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "ObjectCaptureToolbar";
    private final Rect contentRect = new Rect();
    /* access modifiers changed from: private */
    public final Context context;
    private Menu customMenu;
    private MenuItem.OnMenuItemClickListener menuItemClickListener;
    /* access modifiers changed from: private */
    public int orientation;
    private final View.OnLayoutChangeListener orientationChangeHandler = new ObjectCaptureToolbar$orientationChangeHandler$1(this);
    /* access modifiers changed from: private */
    public final ObjectCapturePopup popup;
    private final ArrayList<ToolbarMenuItem> toolbarMenuItemList = new ArrayList<>();
    private boolean useDefaultMenu = true;
    private final Window window;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar$Companion;", "", "<init>", "()V", "TAG", "", "getVisibleAndEnabledMenuItems", "", "Landroid/view/MenuItem;", "menu", "Landroid/view/Menu;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final List<MenuItem> getVisibleAndEnabledMenuItems(Menu menu) {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (menu != null && i2 < menu.size()) {
                MenuItem item = menu.getItem(i2);
                if (item.isVisible() && item.isEnabled()) {
                    SubMenu subMenu = item.getSubMenu();
                    if (subMenu != null) {
                        arrayList.addAll(getVisibleAndEnabledMenuItems(subMenu));
                    } else {
                        arrayList.add(item);
                    }
                }
                i2++;
            }
            return arrayList;
        }

        private Companion() {
        }
    }

    public ObjectCaptureToolbar(Window window2) {
        j.e(window2, "window");
        Context context2 = window2.getContext();
        j.d(context2, "getContext(...)");
        this.context = context2;
        this.window = window2;
        View decorView = window2.getDecorView();
        j.d(decorView, "getDecorView(...)");
        this.popup = new ObjectCapturePopup(context2, decorView);
    }

    private final void clearOnMenuItemClickListener() {
        this.popup.setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener) null);
        this.menuItemClickListener = null;
    }

    private final void doShow() {
        ArrayList arrayList = new ArrayList();
        MenuBuilder defaultShowAsAction = new MenuBuilder(this.context).setDefaultShowAsAction(2);
        if (this.useDefaultMenu) {
            MenuItem add = defaultShowAsAction.add(0, 0, 0, (CharSequence) this.context.getString(17039361));
            j.d(add, "add(...)");
            arrayList.add(add);
            MenuItem add2 = defaultShowAsAction.add(0, 1, 0, (CharSequence) this.context.getString(R.string.object_capture_popup_main_item_share));
            j.d(add2, "add(...)");
            arrayList.add(add2);
            MenuItem add3 = defaultShowAsAction.add(0, 2, 0, (CharSequence) this.context.getString(R.string.object_capture_popup_main_item_save_as_image));
            j.d(add3, "add(...)");
            arrayList.add(add3);
            arrayList.addAll(Companion.getVisibleAndEnabledMenuItems(this.customMenu));
        } else {
            this.toolbarMenuItemList.forEach(new b(24, arrayList, defaultShowAsAction));
            this.popup.setToolbarMenuItem(this.toolbarMenuItemList);
        }
        this.popup.show(arrayList, this.menuItemClickListener, this.contentRect);
    }

    /* access modifiers changed from: private */
    public static final void doShow$lambda$0(List list, MenuBuilder menuBuilder, ToolbarMenuItem toolbarMenuItem) {
        j.e(toolbarMenuItem, "o");
        if (toolbarMenuItem.isEnabled()) {
            MenuItem add = menuBuilder.add(0, toolbarMenuItem.getId(), toolbarMenuItem.getOrder(), (CharSequence) toolbarMenuItem.getTitle());
            j.d(add, "add(...)");
            list.add(add);
        }
    }

    private final void registerOrientationHandler() {
        unregisterOrientationHandler();
        this.window.getDecorView().addOnLayoutChangeListener(this.orientationChangeHandler);
    }

    private final void unregisterOrientationHandler() {
        this.window.getDecorView().removeOnLayoutChangeListener(this.orientationChangeHandler);
    }

    public final ObjectCaptureToolbar addMenu(Menu menu) {
        j.e(menu, "menu");
        this.customMenu = menu;
        return this;
    }

    public final void dismiss() {
        unregisterOrientationHandler();
        clearOnMenuItemClickListener();
        this.popup.dismiss();
    }

    public final void hide() {
        this.popup.hide();
    }

    public final ObjectCaptureToolbar setContentRect(Rect rect) {
        j.e(rect, "rect");
        this.contentRect.set(rect);
        return this;
    }

    public final void setMenuTitleColor(int i2) {
        this.popup.setMenuTitleColor(i2);
    }

    public final void setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.menuItemClickListener = onMenuItemClickListener;
    }

    public final ObjectCaptureToolbar setSuggestedWidth(int i2) {
        this.popup.setSuggestedWidth(i2);
        return this;
    }

    public final void setToolbarActionListener(ToolbarActionListener toolbarActionListener) {
        this.popup.setToolbarActionListener(toolbarActionListener);
    }

    public final void setToolbarMenuList(List<ToolbarMenuItem> list) {
        j.e(list, "list");
        this.toolbarMenuItemList.clear();
        this.toolbarMenuItemList.addAll(list);
    }

    public final void show() {
        registerOrientationHandler();
        doShow();
    }

    public final void updateFlexMode(boolean z) {
        this.popup.setFlexMode(z);
    }

    public final ObjectCaptureToolbar updateLayout() {
        if (this.popup.isShowing()) {
            doShow();
        }
        return this;
    }

    public final void useDefaultMenu(boolean z) {
        this.useDefaultMenu = z;
    }
}
