package com.google.android.material.tabs;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements ViewPager.OnAdapterChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1500a;
    public final /* synthetic */ TabLayout b;

    public a(TabLayout tabLayout) {
        this.b = tabLayout;
    }

    public final void a(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        TabLayout tabLayout = this.b;
        if (tabLayout.viewPager == viewPager) {
            tabLayout.setPagerAdapter(pagerAdapter2, this.f1500a);
        }
    }
}
