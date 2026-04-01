package q2;

import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.sec.android.gallery3d.R;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class y extends g {
    private final Context context;
    private final int floatingComponentHeight;
    private final FloatingToolbarLayout floatingToolbarLayout;
    private final int menuEndPaddingInset;
    private final int menuEndTextPaddingInset;
    private final int menuMoreIconEndPaddingInset;
    private final int menuMoreIconStartPaddingInset;
    private final int menuStartPaddingInset;
    private final int menuStartTextPaddingInset;
    private final int navUpStartPadding;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public y(FloatingToolbarLayout floatingToolbarLayout2) {
        super((u) null);
        j.e(floatingToolbarLayout2, "floatingToolbarLayout");
        this.floatingToolbarLayout = floatingToolbarLayout2;
        Context context2 = floatingToolbarLayout2.getContext();
        this.context = context2;
        this.menuStartPaddingInset = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_menu_start_padding_inset);
        this.menuStartTextPaddingInset = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_text_menu_start_padding_inset);
        this.menuEndPaddingInset = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_menu_end_padding_inset);
        this.menuEndTextPaddingInset = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_text_menu_end_padding_inset);
        this.menuMoreIconStartPaddingInset = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_menu_more_icon_start_padding_inset);
        this.menuMoreIconEndPaddingInset = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_menu_more_icon_end_padding_inset);
        this.navUpStartPadding = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_navup_start_padding);
        this.floatingComponentHeight = context2.getResources().getDimensionPixelSize(R.dimen.sesl_projection_bg_toolbar_component_height);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.appcompat.widget.ActionMenuView a() {
        /*
            r2 = this;
            com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout r2 = r2.floatingToolbarLayout
            boolean r0 = r2.U
            r1 = 0
            if (r0 == 0) goto L_0x0014
            androidx.appcompat.widget.ActionBarContextView r2 = r2.getActionModeBarView()
            if (r2 == 0) goto L_0x0012
            androidx.appcompat.widget.ActionMenuView r2 = r2.seslGetMenuView()
            goto L_0x001e
        L_0x0012:
            r2 = r1
            goto L_0x001e
        L_0x0014:
            androidx.appcompat.widget.Toolbar r2 = r2.getToolbar$material_release()
            if (r2 == 0) goto L_0x0012
            androidx.appcompat.widget.ActionMenuView r2 = r2.seslGetMenuView()
        L_0x001e:
            if (r2 == 0) goto L_0x0028
            int r0 = r2.getChildCount()
            if (r0 != 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            return r2
        L_0x0028:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: q2.y.a():androidx.appcompat.widget.ActionMenuView");
    }

    public final View b() {
        View view;
        View view2;
        FloatingToolbarLayout floatingToolbarLayout2 = this.floatingToolbarLayout;
        if (floatingToolbarLayout2.U) {
            ActionBarContextView r = floatingToolbarLayout2.getActionModeBarView();
            if (r != null) {
                view2 = r.seslGetCloseButton();
            } else {
                view2 = null;
            }
            if (view2 == null || view2.getVisibility() != 0 || view2.getParent() == null) {
                return null;
            }
            return view2;
        }
        Toolbar toolbar$material_release = floatingToolbarLayout2.getToolbar$material_release();
        if (toolbar$material_release != null) {
            view = toolbar$material_release.getNavButtonView();
        } else {
            view = null;
        }
        if (view == null || view.getParent() == null) {
            return null;
        }
        return view;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getReferenceView(q2.C0267a r5) {
        /*
            r4 = this;
            java.lang.String r0 = "type"
            kotlin.jvm.internal.j.e(r5, r0)
            android.view.View r0 = r4.b()
            com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout r1 = r4.floatingToolbarLayout
            boolean r2 = r1.U
            r3 = 0
            if (r2 == 0) goto L_0x001e
            androidx.appcompat.widget.ActionBarContextView r1 = r1.getActionModeBarView()
            if (r1 == 0) goto L_0x001c
            android.view.View r1 = r1.seslGetCustomView()
            goto L_0x0028
        L_0x001c:
            r1 = r3
            goto L_0x0028
        L_0x001e:
            androidx.appcompat.widget.Toolbar r1 = r1.getToolbar$material_release()
            if (r1 == 0) goto L_0x001c
            android.view.View r1 = r1.seslGetCustomView()
        L_0x0028:
            androidx.appcompat.widget.ActionMenuView r4 = r4.a()
            int[] r2 = q2.x.f1904a
            int r5 = r5.ordinal()
            r5 = r2[r5]
            r2 = 1
            if (r5 == r2) goto L_0x0045
            r2 = 2
            if (r5 == r2) goto L_0x003f
            r0 = 3
            if (r5 == r0) goto L_0x003e
            goto L_0x0044
        L_0x003e:
            return r4
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            if (r1 == 0) goto L_0x0044
            goto L_0x0047
        L_0x0044:
            return r3
        L_0x0045:
            if (r0 != 0) goto L_0x0048
        L_0x0047:
            return r1
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: q2.y.getReferenceView(q2.a):android.view.View");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.graphics.Rect} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: androidx.appcompat.view.menu.ActionMenuItemView} */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Rect getReferenceViewInset(q2.C0267a r8) {
        /*
            r7 = this;
            java.lang.String r0 = "type"
            kotlin.jvm.internal.j.e(r8, r0)
            int[] r0 = q2.x.f1904a
            int r8 = r8.ordinal()
            r8 = r0[r8]
            r0 = 2
            r1 = 0
            r2 = 1
            if (r8 == r2) goto L_0x009e
            if (r8 == r0) goto L_0x0098
            r3 = 3
            if (r8 != r3) goto L_0x0092
            androidx.appcompat.widget.ActionMenuView r8 = r7.a()
            r3 = 0
            if (r8 == 0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            r8 = r3
        L_0x0021:
            if (r8 == 0) goto L_0x008c
            int r4 = r8.getChildCount()
            if (r4 > 0) goto L_0x002a
            goto L_0x0088
        L_0x002a:
            android.view.View r4 = r8.getChildAt(r1)
            boolean r5 = r4 instanceof androidx.appcompat.view.menu.ActionMenuItemView
            if (r5 == 0) goto L_0x0035
            androidx.appcompat.view.menu.ActionMenuItemView r4 = (androidx.appcompat.view.menu.ActionMenuItemView) r4
            goto L_0x0036
        L_0x0035:
            r4 = r3
        L_0x0036:
            int r5 = r8.getChildCount()
            int r5 = r5 - r2
            android.view.View r5 = r8.getChildAt(r5)
            boolean r6 = r5 instanceof androidx.appcompat.view.menu.ActionMenuItemView
            if (r6 == 0) goto L_0x0046
            r3 = r5
            androidx.appcompat.view.menu.ActionMenuItemView r3 = (androidx.appcompat.view.menu.ActionMenuItemView) r3
        L_0x0046:
            if (r4 == 0) goto L_0x004d
            boolean r4 = r4.seslIsTextButtonVisible()
            goto L_0x004e
        L_0x004d:
            r4 = r1
        L_0x004e:
            if (r3 == 0) goto L_0x0054
            boolean r1 = r3.seslIsTextButtonVisible()
        L_0x0054:
            int r3 = r8.getHeight()
            int r5 = r7.floatingComponentHeight
            int r3 = r3 - r5
            int r3 = r3 / r0
            int r0 = r8.getChildCount()
            if (r0 != r2) goto L_0x006b
            boolean r0 = r8.seslIsShowOverflowButton()
            if (r0 == 0) goto L_0x006b
            int r0 = r7.menuMoreIconStartPaddingInset
            goto L_0x0072
        L_0x006b:
            if (r4 == 0) goto L_0x0070
            int r0 = r7.menuStartTextPaddingInset
            goto L_0x0072
        L_0x0070:
            int r0 = r7.menuStartPaddingInset
        L_0x0072:
            boolean r8 = r8.seslIsShowOverflowButton()
            if (r8 == 0) goto L_0x007b
            int r7 = r7.menuMoreIconEndPaddingInset
            goto L_0x0082
        L_0x007b:
            if (r1 == 0) goto L_0x0080
            int r7 = r7.menuEndTextPaddingInset
            goto L_0x0082
        L_0x0080:
            int r7 = r7.menuEndPaddingInset
        L_0x0082:
            android.graphics.Rect r8 = new android.graphics.Rect
            r8.<init>(r0, r3, r7, r3)
            r3 = r8
        L_0x0088:
            if (r3 != 0) goto L_0x008b
            goto L_0x008c
        L_0x008b:
            return r3
        L_0x008c:
            android.graphics.Rect r7 = new android.graphics.Rect
            r7.<init>()
            return r7
        L_0x0092:
            Dd.a r7 = new Dd.a
            r7.<init>()
            throw r7
        L_0x0098:
            android.graphics.Rect r7 = new android.graphics.Rect
            r7.<init>()
            return r7
        L_0x009e:
            android.view.View r8 = r7.b()
            if (r8 == 0) goto L_0x00b4
            int r8 = r8.getHeight()
            int r2 = r7.floatingComponentHeight
            int r8 = r8 - r2
            int r8 = r8 / r0
            android.graphics.Rect r0 = new android.graphics.Rect
            int r7 = r7.navUpStartPadding
            r0.<init>(r7, r8, r1, r8)
            return r0
        L_0x00b4:
            android.graphics.Rect r7 = new android.graphics.Rect
            r7.<init>()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: q2.y.getReferenceViewInset(q2.a):android.graphics.Rect");
    }
}
