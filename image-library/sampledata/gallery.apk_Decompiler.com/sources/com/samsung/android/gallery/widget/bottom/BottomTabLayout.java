package com.samsung.android.gallery.widget.bottom;

import A.a;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.c;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.List;
import java.util.Optional;
import nb.C0703d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabLayout extends TabLayout {
    private Animation mSlideDownAnim;
    private Animation mSlideUpAnim;
    private boolean mTabVisible = true;

    public BottomTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(false);
    }

    private void addTabViews() {
        int tabCount = getTabCount();
        for (int i2 = 0; i2 < tabCount; i2++) {
            c tabAt = getTabAt(i2);
            ViewGroup viewGroup = (ViewGroup) getTabView(i2);
            if (!(tabAt == null || viewGroup == null)) {
                viewGroup.setPadding(0, 0, 0, 0);
            }
        }
    }

    private void clearBadge() {
        int tabCount = getTabCount();
        for (int i2 = 0; i2 < tabCount; i2++) {
            seslShowDotBadge(i2, false);
        }
    }

    private void doSlideDownAnimation() {
        if (this.mTabVisible) {
            this.mTabVisible = false;
            if (this.mSlideDownAnim == null) {
                Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R$anim.floating_fade_out);
                this.mSlideDownAnim = loadAnimation;
                loadAnimation.setAnimationListener(new SimpleAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        BottomTabLayout.this.getSlideView().setVisibility(8);
                        BottomTabLayout.this.setAutoGoToTopOffsetMove(true);
                    }
                });
            }
            setAutoGoToTopOffsetMove(false);
            getSlideView().setVisibility(4);
            getSlideView().startAnimation(this.mSlideDownAnim);
        }
    }

    private void doSlideUpAnimation() {
        if (!this.mTabVisible) {
            this.mTabVisible = true;
            if (this.mSlideUpAnim == null) {
                Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R$anim.floating_fade_in);
                this.mSlideUpAnim = loadAnimation;
                loadAnimation.setAnimationListener(new SimpleAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        BottomTabLayout.this.setAutoGoToTopOffsetMove(true);
                        BottomTabLayout.this.getSlideView().requestLayout();
                    }
                });
            }
            setAutoGoToTopOffsetMove(false);
            getSlideView().setVisibility(0);
            getSlideView().startAnimation(this.mSlideUpAnim);
        }
    }

    /* access modifiers changed from: private */
    public View getSlideView() {
        View view = (View) getParent();
        if (view != null) {
            return view;
        }
        return this;
    }

    private int getTabTag(c cVar) {
        Integer num = cVar.f1501a;
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    private View getTabView(int i2) {
        ViewGroup tabViewGroup = getTabViewGroup();
        if (tabViewGroup == null || tabViewGroup.getChildCount() <= i2) {
            return null;
        }
        return tabViewGroup.getChildAt(i2);
    }

    private ViewGroup getTabViewGroup() {
        if (getChildCount() <= 0) {
            return null;
        }
        View childAt = getChildAt(0);
        if (childAt instanceof ViewGroup) {
            return (ViewGroup) childAt;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidateTabLayout$1() {
        try {
            int tabCount = getTabCount();
            for (int i2 = 0; i2 < tabCount; i2++) {
                setTabContentDescription(getTabAt(i2), getTabView(i2));
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("invalidateTabLayout#setTabContentDescription failed. e="), "BottomTabLayout");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLayout$0() {
        Optional.ofNullable(getParent()).ifPresent(new m7.c(17));
    }

    /* access modifiers changed from: private */
    public void setAutoGoToTopOffsetMove(boolean z) {
        View slideView = getSlideView();
        if (slideView instanceof FloatingBottomLayout) {
            FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) slideView;
            if (z) {
                floatingBottomLayout.f1894G = Boolean.TRUE;
                floatingBottomLayout.getFloatingScrollableManager$material_release().f = true;
                return;
            }
            floatingBottomLayout.f1894G = Boolean.FALSE;
            floatingBottomLayout.getFloatingScrollableManager$material_release().f = false;
        }
    }

    private void setTabContentDescription(c cVar, View view) {
        if (cVar != null && view != null) {
            view.setContentDescription(cVar.f1502c);
            view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                    int eventType = accessibilityEvent.getEventType();
                    if (eventType == 32768) {
                        BottomTabLayout.this.setFocusable(true);
                    } else if (eventType == 65536) {
                        BottomTabLayout.this.setFocusable(false);
                    }
                    super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                }
            });
        }
    }

    public void applyAnimation(boolean z) {
        if (z) {
            doSlideUpAnimation();
        } else {
            doSlideDownAnimation();
        }
    }

    public void blockFocus(boolean z) {
        int i2;
        if (z) {
            i2 = 393216;
        } else {
            i2 = 262144;
        }
        setDescendantFocusability(i2);
    }

    public c findTab(int i2) {
        int tabCount = getTabCount();
        for (int i7 = 0; i7 < tabCount; i7++) {
            c tabAt = getTabAt(i7);
            if (tabAt != null && i2 == getTabTag(tabAt)) {
                return tabAt;
            }
        }
        return null;
    }

    public View getMenuTabView() {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            return null;
        }
        for (int tabCount = getTabCount() - 1; tabCount >= 0; tabCount--) {
            c tabAt = getTabAt(tabCount);
            if (tabAt != null && getTabTag(tabAt) == R$id.action_menu_list) {
                return getTabView(tabCount);
            }
        }
        return null;
    }

    public void invalidateTabLayout() {
        addTabViews();
        postDelayed(new C0703d(this, 1), 1000);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getLayoutParams().height = getResources().getDimensionPixelSize(R$dimen.bottom_tab_floating_height);
        ViewUtils.setHeight((View) getParent(), -2);
        int tabCount = getTabCount();
        for (int i2 = 0; i2 < tabCount; i2++) {
            ViewMarginUtils.setPadding(getTabView(i2), 0);
        }
        invalidateTabLayout();
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        if (z) {
            post(new C0703d(this, 0));
        }
    }

    public void refresh(boolean z) {
        int i2;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        setVisibility(i2);
        clearBadge();
        invalidate();
    }

    public void setTabSelected(int i2, boolean z) {
        int tabCount = getTabCount();
        int i7 = 0;
        while (i7 < tabCount) {
            c tabAt = getTabAt(i7);
            if (tabAt == null || i2 != getTabTag(tabAt)) {
                i7++;
            } else {
                TabLayout tabLayout = tabAt.f;
                if (tabLayout != null) {
                    int selectedTabPosition = tabLayout.getSelectedTabPosition();
                    if (selectedTabPosition != -1 && selectedTabPosition == tabAt.d) {
                        return;
                    }
                    if (z) {
                        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
                            setTabContentDescription(findTab(R$id.action_menu_list), getTabView(i7));
                        }
                        tabAt.a();
                        setTabContentDescription(tabAt, getTabView(i7));
                        return;
                    }
                    tabAt.a();
                    return;
                }
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
        }
        Log.e("BottomTabLayout", "setTabSelected {" + i2 + "} failed");
    }

    public void updateBadge(int i2, boolean z) {
        int tabCount = getTabCount();
        int i7 = 0;
        while (i7 < tabCount) {
            c tabAt = getTabAt(i7);
            if (tabAt == null || i2 != getTabTag(tabAt)) {
                i7++;
            } else {
                seslShowDotBadge(i7, z, getContext().getString(R$string.new_content_available));
                return;
            }
        }
        StringBuilder h5 = a.h(i2, tabCount, "updateBadge failed {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(z);
        h5.append("}");
        Log.e("BottomTabLayout", h5.toString());
    }

    public void updateFloatingView(View view) {
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            ViewParent parent = getParent();
            if (parent instanceof FloatingBottomLayout) {
                FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) parent;
                floatingBottomLayout.a(List.of(this));
                floatingBottomLayout.setRecyclerView(recyclerView);
                floatingBottomLayout.h();
            }
        }
    }
}
