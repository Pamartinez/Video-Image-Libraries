package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import A.a;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MenuItem;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0012\u001a\u00020\u0003H\u0016J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J5\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/MenuItemDao;", "", "itemId", "", "groupId", "title", "", "icon", "Landroid/graphics/drawable/Drawable;", "<init>", "(IILjava/lang/String;Landroid/graphics/drawable/Drawable;)V", "getItemId", "()I", "getGroupId", "getTitle", "()Ljava/lang/String;", "getIcon", "()Landroid/graphics/drawable/Drawable;", "hashCode", "equals", "", "o", "component1", "component2", "component3", "component4", "copy", "toString", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MenuItemDao {
    public static final Companion Companion = new Companion((e) null);
    private final int groupId;
    private final Drawable icon;
    private final int itemId;
    private final String title;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J$\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u0007¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/MenuItemDao$Companion;", "", "<init>", "()V", "of", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/MenuItemDao;", "menuItem", "Landroid/view/MenuItem;", "collectionEquals", "", "menuItems1", "", "menuItems2", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean collectionEquals(Collection<? extends MenuItem> collection, Collection<? extends MenuItem> collection2) {
            j.e(collection, "menuItems1");
            j.e(collection2, "menuItems2");
            if (collection.size() != collection2.size()) {
                return false;
            }
            Iterator<? extends MenuItem> it = collection2.iterator();
            for (MenuItem of2 : collection) {
                if (!of(of2).equals(of((MenuItem) it.next()))) {
                    return false;
                }
            }
            return true;
        }

        public final MenuItemDao of(MenuItem menuItem) {
            String str;
            j.e(menuItem, "menuItem");
            int itemId = menuItem.getItemId();
            int groupId = menuItem.getGroupId();
            CharSequence title = menuItem.getTitle();
            if (title != null) {
                str = title.toString();
            } else {
                str = null;
            }
            return new MenuItemDao(itemId, groupId, str, menuItem.getIcon());
        }

        private Companion() {
        }
    }

    public MenuItemDao(int i2, int i7, String str, Drawable drawable) {
        this.itemId = i2;
        this.groupId = i7;
        this.title = str;
        this.icon = drawable;
    }

    public static final boolean collectionEquals(Collection<? extends MenuItem> collection, Collection<? extends MenuItem> collection2) {
        return Companion.collectionEquals(collection, collection2);
    }

    public static /* synthetic */ MenuItemDao copy$default(MenuItemDao menuItemDao, int i2, int i7, String str, Drawable drawable, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i2 = menuItemDao.itemId;
        }
        if ((i8 & 2) != 0) {
            i7 = menuItemDao.groupId;
        }
        if ((i8 & 4) != 0) {
            str = menuItemDao.title;
        }
        if ((i8 & 8) != 0) {
            drawable = menuItemDao.icon;
        }
        return menuItemDao.copy(i2, i7, str, drawable);
    }

    public static final MenuItemDao of(MenuItem menuItem) {
        return Companion.of(menuItem);
    }

    public final int component1() {
        return this.itemId;
    }

    public final int component2() {
        return this.groupId;
    }

    public final String component3() {
        return this.title;
    }

    public final Drawable component4() {
        return this.icon;
    }

    public final MenuItemDao copy(int i2, int i7, String str, Drawable drawable) {
        return new MenuItemDao(i2, i7, str, drawable);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MenuItemDao)) {
            return false;
        }
        MenuItemDao menuItemDao = (MenuItemDao) obj;
        if (this.itemId != menuItemDao.itemId || this.groupId != menuItemDao.groupId || !TextUtils.equals(this.title, menuItemDao.title) || !j.a(this.icon, menuItemDao.icon)) {
            return false;
        }
        return true;
    }

    public final int getGroupId() {
        return this.groupId;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final int getItemId() {
        return this.itemId;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.itemId), Integer.valueOf(this.groupId), this.title, this.icon});
    }

    public String toString() {
        int i2 = this.itemId;
        int i7 = this.groupId;
        String str = this.title;
        Drawable drawable = this.icon;
        StringBuilder h5 = a.h(i2, i7, "MenuItemDao(itemId=", ", groupId=", ", title=");
        h5.append(str);
        h5.append(", icon=");
        h5.append(drawable);
        h5.append(")");
        return h5.toString();
    }
}
