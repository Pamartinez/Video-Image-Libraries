package k2;

import D2.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import com.sec.android.gallery3d.R;
import h2.p;
import og.k;
import x2.C0334a;
import x2.C0340g;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class s extends FrameLayout {
    private int mMaxItemCount;
    MenuBuilder.Callback mSelectedCallback = new n((BottomNavigationView) this);
    private final e menu;
    private MenuInflater menuInflater;
    private final h menuView;
    private final m presenter;
    /* access modifiers changed from: private */
    public q selectedListener;

    public s(Context context, AttributeSet attributeSet) {
        super(a.a(context, attributeSet, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView), attributeSet, R.attr.bottomNavigationStyle);
        Context context2 = getContext();
        p.a(context2, attributeSet, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView);
        int[] iArr = Q1.a.f617G;
        AttributeSet attributeSet2 = attributeSet;
        p.b(context2, attributeSet2, iArr, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView, 13, 11, 18);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context2, attributeSet2, iArr, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView);
        Class<?> cls = getClass();
        getMaxItemCount();
        e eVar = new e(context2, cls);
        this.menu = eVar;
        h createNavigationBarMenuView = createNavigationBarMenuView(context2);
        this.menuView = createNavigationBarMenuView;
        m mVar = new m(context2);
        this.presenter = mVar;
        int maxItemCount = getMaxItemCount();
        this.mMaxItemCount = maxItemCount;
        setMaxItemCount(maxItemCount);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        createNavigationBarMenuView.setLayoutParams(layoutParams);
        seslSetViewType(obtainStyledAttributes.getInteger(19, 3));
        mVar.e = createNavigationBarMenuView;
        mVar.g = 1;
        createNavigationBarMenuView.setPresenter(mVar);
        eVar.addMenuPresenter(mVar);
        mVar.initForMenu(getContext(), eVar);
        if (obtainStyledAttributes.hasValue(6)) {
            createNavigationBarMenuView.setIconTintList(obtainStyledAttributes.getColorStateList(6));
        } else {
            createNavigationBarMenuView.setIconTintList(createNavigationBarMenuView.c());
        }
        setItemIconSize(obtainStyledAttributes.getDimensionPixelSize(5, getResources().getDimensionPixelSize(R.dimen.sesl_navigation_bar_icon_size)));
        if (obtainStyledAttributes.hasValue(13)) {
            setItemTextAppearanceInactive(obtainStyledAttributes.getResourceId(13, 0));
        }
        if (obtainStyledAttributes.hasValue(18)) {
            seslSetLabelTextAppearance(obtainStyledAttributes.getResourceId(18, 0));
        }
        if (obtainStyledAttributes.hasValue(11)) {
            setItemTextAppearanceActive(obtainStyledAttributes.getResourceId(11, 0));
        }
        setItemTextAppearanceActiveBoldEnabled(obtainStyledAttributes.getBoolean(12, true));
        if (obtainStyledAttributes.hasValue(14)) {
            setItemTextColor(obtainStyledAttributes.getColorStateList(14));
        }
        Drawable background = getBackground();
        ColorStateList B = c.B(background);
        if (background == null || B != null) {
            C0340g gVar = new C0340g(C0344k.b(context2, attributeSet2, R.attr.bottomNavigationStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomNavigationView).a());
            if (B != null) {
                gVar.k(B);
            }
            gVar.i(context2);
            ViewCompat.setBackground(this, gVar);
        }
        if (background instanceof ColorDrawable) {
            createNavigationBarMenuView.setBackgroundColorDrawable((ColorDrawable) background);
        }
        if (obtainStyledAttributes.hasValue(8)) {
            setItemPaddingTop(obtainStyledAttributes.getDimensionPixelSize(8, 0));
        }
        if (obtainStyledAttributes.hasValue(7)) {
            setItemPaddingBottom(obtainStyledAttributes.getDimensionPixelSize(7, 0));
        }
        if (obtainStyledAttributes.hasValue(0)) {
            setActiveIndicatorLabelPadding(obtainStyledAttributes.getDimensionPixelSize(0, 0));
        }
        if (obtainStyledAttributes.hasValue(2)) {
            setElevation((float) obtainStyledAttributes.getDimensionPixelSize(2, 0));
        }
        DrawableCompat.setTintList(getBackground().mutate(), B1.a.v(context2, obtainStyledAttributes, 1));
        setLabelVisibilityMode(obtainStyledAttributes.getInteger(15, -1));
        int resourceId = obtainStyledAttributes.getResourceId(4, 0);
        if (resourceId != 0) {
            createNavigationBarMenuView.setItemBackgroundRes(resourceId);
        } else {
            setItemRippleColor(B1.a.v(context2, obtainStyledAttributes, 9));
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(10, 0);
        if (resourceId2 != 0) {
            createNavigationBarMenuView.setItemStateListAnimator(resourceId2);
        }
        int resourceId3 = obtainStyledAttributes.getResourceId(3, 0);
        if (resourceId3 != 0) {
            setItemActiveIndicatorEnabled(true);
            TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(resourceId3, Q1.a.f616F);
            setItemActiveIndicatorWidth(obtainStyledAttributes2.getDimensionPixelSize(1, 0));
            setItemActiveIndicatorHeight(obtainStyledAttributes2.getDimensionPixelSize(0, 0));
            setItemActiveIndicatorMarginHorizontal(obtainStyledAttributes2.getDimensionPixelOffset(3, 0));
            setItemActiveIndicatorColor(B1.a.u(context2, obtainStyledAttributes2, 2));
            setItemActiveIndicatorShapeAppearance(C0344k.a(context2, obtainStyledAttributes2.getResourceId(4, 0), 0, new C0334a((float) 0)).a());
            obtainStyledAttributes2.recycle();
        }
        if (obtainStyledAttributes.hasValue(16)) {
            inflateMenu(obtainStyledAttributes.getResourceId(16, 0));
        }
        if (obtainStyledAttributes.hasValue(17)) {
            createNavigationBarMenuView.setExclusiveCheckable(obtainStyledAttributes.getBoolean(17, true));
        }
        obtainStyledAttributes.recycle();
        addView(createNavigationBarMenuView);
        eVar.setCallback(this.mSelectedCallback);
        createNavigationBarMenuView.setOverflowSelectedCallback(this.mSelectedCallback);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    public abstract h createNavigationBarMenuView(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.menuView.getActiveIndicatorLabelPadding();
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.menuView.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorHeight() {
        return this.menuView.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.menuView.getItemActiveIndicatorMarginHorizontal();
    }

    public C0344k getItemActiveIndicatorShapeAppearance() {
        return this.menuView.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.menuView.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.menuView.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.menuView.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.menuView.getItemRippleColor();
    }

    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.menu;
    }

    public MenuView getMenuView() {
        return this.menuView;
    }

    public m getPresenter() {
        return this.presenter;
    }

    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void inflateMenu(int i2) {
        this.presenter.f = true;
        getMenuInflater().inflate(i2, this.menu);
        m mVar = this.presenter;
        mVar.f = false;
        mVar.updateMenuView(true);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        k.Q(this);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof r)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        r rVar = (r) parcelable;
        super.onRestoreInstanceState(rVar.getSuperState());
        this.menu.restorePresenterStates(rVar.d);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, androidx.customview.view.AbsSavedState, k2.r] */
    public Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        absSavedState.d = bundle;
        this.menu.savePresenterStates(bundle);
        return absSavedState;
    }

    public boolean seslHasOverflowButton() {
        return this.menuView.f1820M;
    }

    public void seslSetGroupDividerEnabled(boolean z) {
        this.menuView.setGroupDividerEnabled(z);
    }

    public void seslSetLabelTextAppearance(int i2) {
        h hVar = this.menuView;
        hVar.f1815G = i2;
        d[] dVarArr = hVar.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setTextAppearanceInactive(i2);
                ColorStateList colorStateList = hVar.m;
                if (colorStateList != null) {
                    dVar.setTextColor(colorStateList);
                }
            }
        }
        d dVar2 = hVar.L;
        if (dVar2 != null) {
            dVar2.setTextAppearanceInactive(i2);
            ColorStateList colorStateList2 = hVar.m;
            if (colorStateList2 != null) {
                hVar.L.setTextColor(colorStateList2);
            }
        }
    }

    public void seslSetViewType(int i2) {
        this.menuView.setViewType(i2);
    }

    public void seslShowOverflowMenu() {
        m mVar;
        if (seslHasOverflowButton()) {
            h hVar = this.menuView;
            if (hVar.f1820M && (mVar = hVar.E) != null) {
                mVar.a(hVar.f1821N);
            }
        }
    }

    public void setActiveIndicatorLabelPadding(int i2) {
        this.menuView.setActiveIndicatorLabelPadding(i2);
    }

    public void setElevation(float f) {
        super.setElevation(f);
        k.O(this, f);
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.menuView.setItemActiveIndicatorColor(colorStateList);
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.menuView.setItemActiveIndicatorEnabled(z);
    }

    public void setItemActiveIndicatorHeight(int i2) {
        this.menuView.setItemActiveIndicatorHeight(i2);
    }

    public void setItemActiveIndicatorMarginHorizontal(int i2) {
        this.menuView.setItemActiveIndicatorMarginHorizontal(i2);
    }

    public void setItemActiveIndicatorShapeAppearance(C0344k kVar) {
        this.menuView.setItemActiveIndicatorShapeAppearance(kVar);
    }

    public void setItemActiveIndicatorWidth(int i2) {
        this.menuView.setItemActiveIndicatorWidth(i2);
    }

    public void setItemBackground(Drawable drawable) {
        this.menuView.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i2) {
        this.menuView.setItemBackgroundRes(i2);
    }

    public void setItemIconSize(int i2) {
        this.menuView.setItemIconSize(i2);
    }

    public void setItemIconSizeRes(int i2) {
        setItemIconSize(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.menuView.setIconTintList(colorStateList);
    }

    public void setItemPaddingBottom(int i2) {
        this.menuView.setItemPaddingBottom(i2);
    }

    public void setItemPaddingTop(int i2) {
        this.menuView.setItemPaddingTop(i2);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.menuView.setItemRippleColor(colorStateList);
    }

    public void setItemTextAppearanceActive(int i2) {
        this.menuView.setItemTextAppearanceActive(i2);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.menuView.setItemTextAppearanceActiveBoldEnabled(z);
    }

    public void setItemTextAppearanceInactive(int i2) {
        this.menuView.setItemTextAppearanceInactive(i2);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.menuView.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.menuView.getLabelVisibilityMode() != i2) {
            this.menuView.setLabelVisibilityMode(i2);
            this.presenter.updateMenuView(false);
        }
    }

    public void setMaxItemCount(int i2) {
        this.menuView.setMaxItemCount(i2);
    }

    public void setOnItemSelectedListener(q qVar) {
        this.selectedListener = qVar;
    }

    public void setSelectedItemId(int i2) {
        MenuItem findItem = this.menu.findItem(i2);
        if (findItem != null && !this.menu.performItemAction(findItem, this.presenter, 0)) {
            findItem.setChecked(true);
        }
    }

    public void setOnItemClickListener(o oVar) {
    }

    public void setOnItemReselectedListener(p pVar) {
    }
}
