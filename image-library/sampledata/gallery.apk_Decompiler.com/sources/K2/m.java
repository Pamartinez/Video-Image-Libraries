package k2;

import S1.n;
import U1.a;
import U1.b;
import android.content.Context;
import android.os.Looper;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import ge.s1;
import h2.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends BaseMenuPresenter {
    public MenuBuilder d;
    public h e;
    public boolean f = false;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public Context f1832h;

    /* renamed from: i  reason: collision with root package name */
    public s1 f1833i;

    /* renamed from: j  reason: collision with root package name */
    public final k f1834j;
    public j k;

    public m(Context context) {
        super(context, R$layout.sesl_action_menu_layout, R$layout.sesl_action_menu_item_layout);
        new n(this, Looper.getMainLooper(), 2);
        this.f1834j = new k(this);
    }

    public final void a(MenuBuilder menuBuilder) {
        j jVar = this.k;
        if ((jVar == null || !jVar.isShowing()) && menuBuilder != null && this.e != null && this.f1833i == null && !menuBuilder.getNonActionItems().isEmpty()) {
            j jVar2 = new j(this, this.f1832h, menuBuilder, this.e.L);
            this.k = jVar2;
            s1 s1Var = new s1(2, this, jVar2);
            this.f1833i = s1Var;
            this.e.post(s1Var);
            super.onSubMenuSelected((SubMenuBuilder) null);
        }
    }

    public final boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public final boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public final boolean flagActionItems() {
        return false;
    }

    public final int getId() {
        return this.g;
    }

    public final boolean hideOverflowMenu() {
        MenuView menuView;
        s1 s1Var = this.f1833i;
        if (s1Var == null || (menuView = this.mMenuView) == null) {
            j jVar = this.k;
            if (jVar == null) {
                return false;
            }
            jVar.dismiss();
            return true;
        }
        ((View) menuView).removeCallbacks(s1Var);
        this.f1833i = null;
        return true;
    }

    public final void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.d = menuBuilder;
        this.e.f1814F = menuBuilder;
        this.f1832h = context;
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        a aVar;
        a aVar2;
        if (parcelable instanceof l) {
            h hVar = this.e;
            l lVar = (l) parcelable;
            int i2 = lVar.d;
            int size = hVar.f1814F.size();
            int i7 = 0;
            while (true) {
                if (i7 >= size) {
                    break;
                }
                MenuItem item = hVar.f1814F.getItem(i7);
                if (i2 == item.getItemId()) {
                    hVar.f1826i = i2;
                    hVar.f1827j = i7;
                    item.setChecked(true);
                    break;
                }
                i7++;
            }
            Context context = this.e.getContext();
            i iVar = lVar.e;
            SparseArray sparseArray = new SparseArray(iVar.size());
            for (int i8 = 0; i8 < iVar.size(); i8++) {
                int keyAt = iVar.keyAt(i8);
                b bVar = (b) iVar.valueAt(i8);
                if (bVar != null) {
                    aVar2 = new a(context, bVar);
                } else {
                    aVar2 = null;
                }
                sparseArray.put(keyAt, aVar2);
            }
            h hVar2 = this.e;
            SparseArray sparseArray2 = hVar2.t;
            for (int i10 = 0; i10 < sparseArray.size(); i10++) {
                int keyAt2 = sparseArray.keyAt(i10);
                if (sparseArray2.indexOfKey(keyAt2) < 0) {
                    sparseArray2.append(keyAt2, (a) sparseArray.get(keyAt2));
                }
            }
            d[] dVarArr = hVar2.f1825h;
            if (dVarArr != null) {
                for (d dVar : dVarArr) {
                    if (!(dVar == null || (aVar = (a) sparseArray2.get(dVar.getId())) == null)) {
                        dVar.setBadge(aVar);
                    }
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.os.Parcelable, k2.l, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.util.SparseArray, h2.i] */
    public final Parcelable onSaveInstanceState() {
        b bVar;
        ? obj = new Object();
        obj.d = this.e.getSelectedItemId();
        SparseArray<a> badgeDrawables = this.e.getBadgeDrawables();
        ? sparseArray = new SparseArray();
        for (int i2 = 0; i2 < badgeDrawables.size(); i2++) {
            int keyAt = badgeDrawables.keyAt(i2);
            a valueAt = badgeDrawables.valueAt(i2);
            if (valueAt != null) {
                bVar = valueAt.f840h.f855a;
            } else {
                bVar = null;
            }
            sparseArray.put(keyAt, bVar);
        }
        obj.e = sparseArray;
        return obj;
    }

    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public final void setId(int i2) {
        this.g = i2;
    }

    public final void updateMenuView(boolean z) {
        if (!this.f) {
            if (z) {
                this.e.b();
            } else {
                this.e.j();
            }
        }
    }

    public final void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
    }

    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
    }
}
