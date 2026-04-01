package com.samsung.android.gallery.widget.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LayoutRuleHolder {
    final int mBottom;
    final int mEnd;
    final View mTargetView;
    final int mTop;

    public LayoutRuleHolder(View view) {
        this.mTargetView = view;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            this.mEnd = layoutParams2.getRule(19);
            this.mTop = layoutParams2.getRule(6);
            this.mBottom = layoutParams2.getRule(8);
            return;
        }
        this.mBottom = 0;
        this.mTop = 0;
        this.mEnd = 0;
    }

    public void removeRules() {
        ViewGroup.LayoutParams layoutParams = this.mTargetView.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.removeRule(19);
            layoutParams2.removeRule(6);
            layoutParams2.removeRule(8);
        }
    }

    public void restoreRules() {
        ViewGroup.LayoutParams layoutParams = this.mTargetView.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(19, this.mEnd);
            layoutParams2.addRule(6, this.mTop);
            layoutParams2.addRule(8, this.mBottom);
        }
    }
}
