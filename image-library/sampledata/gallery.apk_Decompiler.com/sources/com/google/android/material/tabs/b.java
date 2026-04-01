package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sec.android.gallery3d.R;
import h2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends LinearLayout {
    public static final /* synthetic */ int e = 0;
    public final /* synthetic */ TabLayout d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(TabLayout tabLayout, Context context) {
        super(context);
        this.d = tabLayout;
        setWillNotDraw(false);
        setClipToPadding(false);
        setClipChildren(false);
    }

    public final void a(int i2) {
        TabLayout tabLayout = this.d;
        Rect bounds = tabLayout.tabSelectedIndicator.getBounds();
        tabLayout.tabSelectedIndicator.setBounds(bounds.left, 0, bounds.right, i2);
        requestLayout();
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
    }

    public final void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        Typeface typeface;
        c cVar;
        int i11 = i7;
        TabLayout tabLayout = this.d;
        int i12 = 1073741824;
        int i13 = 0;
        boolean z = true;
        if (tabLayout.mode == 13) {
            int childCount = getChildCount();
            int size = View.MeasureSpec.getSize(i2);
            boolean z3 = false;
            int i14 = 0;
            for (int i15 = 0; i15 < childCount; i15++) {
                View childAt = getChildAt(i15);
                if (childAt.getVisibility() == 0) {
                    i14++;
                    if ((childAt instanceof d) && (cVar = ((d) childAt).d) != null && cVar.b != null && !TextUtils.isEmpty(cVar.f1502c)) {
                        z3 = true;
                    }
                }
            }
            if (z3) {
                if (i14 == 1) {
                    i10 = R.dimen.sesl_tablayout_maintab_floating_icon_text_min_width_count_1;
                } else if (i14 != 2) {
                    i10 = R.dimen.sesl_tablayout_maintab_floating_icon_text_min_width_count_over_3;
                } else {
                    i10 = R.dimen.sesl_tablayout_maintab_floating_icon_text_min_width_count_2;
                }
            } else if (i14 == 1) {
                i10 = R.dimen.sesl_tablayout_maintab_floating_icon_only_min_width_count_1;
            } else if (i14 != 2) {
                i10 = R.dimen.sesl_tablayout_maintab_floating_icon_only_min_width_count_over_3;
            } else {
                i10 = R.dimen.sesl_tablayout_maintab_floating_icon_only_min_width_count_2;
            }
            int dimensionPixelSize = getResources().getDimensionPixelSize(i10);
            for (int i16 = 0; i16 < childCount; i16++) {
                View childAt2 = getChildAt(i16);
                if (childAt2.getVisibility() == 0) {
                    childAt2.setMinimumWidth(dimensionPixelSize);
                    if (childAt2 instanceof d) {
                        ((d) childAt2).e.setTypeface(tabLayout.mBoldTypeface);
                    }
                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), i11);
                }
            }
            int i17 = 0;
            for (int i18 = 0; i18 < childCount; i18++) {
                View childAt3 = getChildAt(i18);
                if (childAt3.getVisibility() == 0) {
                    i17 = Math.max(i17, childAt3.getMeasuredWidth());
                    if (childAt3 instanceof d) {
                        d dVar = (d) childAt3;
                        TextView textView = dVar.e;
                        if (dVar.isSelected()) {
                            typeface = tabLayout.mBoldTypeface;
                        } else {
                            typeface = tabLayout.mNormalTypeface;
                        }
                        textView.setTypeface(typeface);
                    }
                }
            }
            if (i17 == 0) {
                super.onMeasure(i2, i7);
                return;
            }
            int i19 = i17 * childCount;
            int min = Math.min(size, getResources().getDimensionPixelSize(R.dimen.sesl_tablayout_maintab_container_max_width));
            if (i19 <= ((min - ((tabLayout.mMainTabSelectedSideMargin * 2) + ((childCount - 1) * tabLayout.mMainTabSeparatorMargin))) - getPaddingLeft()) - getPaddingRight()) {
                while (i13 < childCount) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i13).getLayoutParams();
                    if (layoutParams.width != i17 || layoutParams.weight != 0.0f) {
                        layoutParams.width = i17;
                        layoutParams.weight = 0.0f;
                    }
                    i13++;
                }
                if (tabLayout.tabGravity == 0 && tabLayout.mFirstTabGravity == 1) {
                    tabLayout.tabGravity = 1;
                }
            } else {
                tabLayout.tabGravity = 0;
                tabLayout.updateTabViews(false);
            }
            if (tabLayout.tabGravity != 0) {
                i12 = Integer.MIN_VALUE;
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, i12), i11);
            return;
        }
        super.onMeasure(i2, i7);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            int i20 = tabLayout.mode;
            if (i20 == 11 || i20 == 12) {
                tabLayout.d();
                if (tabLayout.mIsOverScreen) {
                    i8 = tabLayout.mOverScreenMaxWidth;
                } else {
                    i8 = View.MeasureSpec.getSize(i2);
                }
                int childCount2 = getChildCount();
                int[] iArr = new int[childCount2];
                int i21 = 0;
                for (int i22 = 0; i22 < childCount2; i22++) {
                    View childAt4 = getChildAt(i22);
                    if (childAt4.getVisibility() == 0) {
                        childAt4.measure(View.MeasureSpec.makeMeasureSpec(tabLayout.tabMaxWidth, 0), i11);
                        int access$4500 = (tabLayout.mTabMinSideSpace * 2) + childAt4.getMeasuredWidth();
                        iArr[i22] = access$4500;
                        i21 += access$4500;
                    }
                }
                int i23 = i8 / childCount2;
                if (i21 > i8) {
                    while (i13 < childCount2) {
                        ((LinearLayout.LayoutParams) getChildAt(i13).getLayoutParams()).width = iArr[i13];
                        i13++;
                    }
                } else {
                    if (tabLayout.mode == 11) {
                        int i24 = 0;
                        while (true) {
                            if (i24 >= childCount2) {
                                z = false;
                                break;
                            } else if (iArr[i24] > i23) {
                                break;
                            } else {
                                i24++;
                            }
                        }
                    }
                    if (z) {
                        int i25 = (i8 - i21) / childCount2;
                        while (i13 < childCount2) {
                            ((LinearLayout.LayoutParams) getChildAt(i13).getLayoutParams()).width = iArr[i13] + i25;
                            i13++;
                        }
                    } else {
                        while (i13 < childCount2) {
                            ((LinearLayout.LayoutParams) getChildAt(i13).getLayoutParams()).width = i23;
                            i13++;
                        }
                    }
                }
                if (i21 > i8) {
                    i8 = i21;
                }
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), i11);
            } else if (tabLayout.tabGravity == 1 || i20 == 2 || tabLayout.mFirstTabGravity == 1) {
                int childCount3 = getChildCount();
                if (tabLayout.tabGravity == 0 && tabLayout.mFirstTabGravity == 1) {
                    for (int i26 = 0; i26 < childCount3; i26++) {
                        View childAt5 = getChildAt(i26);
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) childAt5.getLayoutParams();
                        layoutParams2.width = -2;
                        layoutParams2.weight = 0.0f;
                        childAt5.measure(View.MeasureSpec.makeMeasureSpec(0, 0), i11);
                    }
                }
                int i27 = 0;
                for (int i28 = 0; i28 < childCount3; i28++) {
                    View childAt6 = getChildAt(i28);
                    if (childAt6.getVisibility() == 0) {
                        i27 = Math.max(i27, childAt6.getMeasuredWidth());
                    }
                }
                if (i27 > 0) {
                    if (i27 * childCount3 <= getMeasuredWidth() - (((int) u.b(getContext(), 16)) * 2)) {
                        boolean z7 = false;
                        while (i13 < childCount3) {
                            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) getChildAt(i13).getLayoutParams();
                            if (layoutParams3.width != i27 || layoutParams3.weight != 0.0f) {
                                layoutParams3.width = i27;
                                layoutParams3.weight = 0.0f;
                                z7 = true;
                            }
                            i13++;
                        }
                        if (tabLayout.tabGravity == 0 && tabLayout.mFirstTabGravity == 1) {
                            tabLayout.tabGravity = 1;
                        }
                        z = z7;
                    } else {
                        tabLayout.tabGravity = 0;
                        tabLayout.updateTabViews(false);
                    }
                    if (z) {
                        super.onMeasure(i2, i7);
                    }
                }
            }
        }
    }
}
