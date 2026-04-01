package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Optional;
import r6.e;
import v7.C0528a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsDecorBgHandler extends ViewerObject implements FragmentLifeCycle {
    private ConstraintLayout mDecorLayout;
    private ArrayList<Drawable> mDecorViewChangedBg;
    private ArrayList<Drawable> mDecorViewDefaultBg;
    private ArrayList<View> mDecorViewIconList;

    private void addDecorDrawable(View view, ArrayList<View> arrayList, ArrayList<Drawable> arrayList2, ArrayList<Drawable> arrayList3) {
        Drawable drawable = getDrawable(view);
        if (drawable != null) {
            arrayList.add(view);
            Drawable newDrawable = drawable.mutate().getConstantState().newDrawable();
            if (newDrawable instanceof GradientDrawable) {
                ((GradientDrawable) newDrawable).setColor(this.mModel.getContext().getColor(getChangedBgColor()));
            } else {
                ((GradientDrawable) ((LayerDrawable) newDrawable).getDrawable(0)).setColor(this.mModel.getContext().getColor(getChangedBgColor()));
            }
            arrayList2.add(drawable);
            arrayList3.add(newDrawable);
        }
    }

    private void findDecorDrawable(ViewGroup viewGroup, ArrayList<View> arrayList, ArrayList<Drawable> arrayList2, ArrayList<Drawable> arrayList3) {
        if (getDrawable(viewGroup) != null) {
            addDecorDrawable(viewGroup, arrayList, arrayList2, arrayList3);
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != null) {
                if (childAt instanceof ViewGroup) {
                    findDecorDrawable((ViewGroup) childAt, arrayList, arrayList2, arrayList3);
                } else if (getDrawable(childAt) != null) {
                    addDecorDrawable(childAt, arrayList, arrayList2, arrayList3);
                }
            }
        }
    }

    private Drawable getDrawable(View view) {
        if (view.getBackground() instanceof GradientDrawable) {
            return view.getBackground();
        }
        if (!(view.getBackground() instanceof LayerDrawable) || ((LayerDrawable) view.getBackground()).getNumberOfLayers() <= 0 || !(((LayerDrawable) view.getBackground()).getDrawable(0) instanceof GradientDrawable)) {
            return null;
        }
        return view.getBackground();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mDecorLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        onDecorWidgetInflated(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        onDecorWidgetInflated(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        onDecorWidgetInflated(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResolutionChanged$4() {
        this.mDecorViewIconList = null;
        updateDecorViewIconBgColor();
    }

    private void restoreDecorViewIconBgColor() {
        ArrayList<Drawable> arrayList;
        if (this.mDecorViewIconList != null && (arrayList = this.mDecorViewDefaultBg) != null) {
            updateBackground(arrayList);
        }
    }

    private void setDecorViewIconBgColor() {
        if (this.mDecorViewIconList == null) {
            this.mDecorViewIconList = new ArrayList<>();
            this.mDecorViewDefaultBg = new ArrayList<>();
            ArrayList<Drawable> arrayList = new ArrayList<>();
            this.mDecorViewChangedBg = arrayList;
            findDecorDrawable(this.mDecorLayout, this.mDecorViewIconList, this.mDecorViewDefaultBg, arrayList);
        }
        updateBackground(this.mDecorViewChangedBg);
    }

    private void updateBackground(ArrayList<Drawable> arrayList) {
        for (int i2 = 0; i2 < this.mDecorViewIconList.size(); i2++) {
            this.mDecorViewIconList.get(i2).setBackground(arrayList.get(i2));
        }
    }

    private void updateDecorViewIconBgColor() {
        if (supportChangeBg()) {
            setDecorViewIconBgColor();
        } else {
            restoreDecorViewIconBgColor();
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.DECOR_LAYOUT, new C0528a(this, 0));
        this.mActionInvoker.add(ViewerAction.QUICK_CROP_VIEW, new C0528a(this, 1));
        this.mActionInvoker.add(ViewerAction.SHOT_MODE_BUTTON, new C0528a(this, 2));
        this.mActionInvoker.add(ViewerAction.UPDATE_DECOR_WIDGET_BG, new C0528a(this, 3));
    }

    public abstract int getChangedBgColor();

    public void onDecorWidgetInflated(View view) {
        ArrayList<Drawable> arrayList;
        if (supportChangeBg()) {
            ArrayList<View> arrayList2 = this.mDecorViewIconList;
            if (!(arrayList2 == null || (arrayList = this.mDecorViewDefaultBg) == null)) {
                findDecorDrawable((ViewGroup) view, arrayList2, arrayList, this.mDecorViewChangedBg);
            }
            setDecorViewIconBgColor();
        }
    }

    public void onFinalized() {
        super.onFinalized();
        Optional.ofNullable(this.mDecorViewIconList).ifPresent(new e(26));
        Optional.ofNullable(this.mDecorViewDefaultBg).ifPresent(new e(26));
        Optional.ofNullable(this.mDecorViewChangedBg).ifPresent(new e(26));
    }

    public void onResolutionChanged() {
        ViewUtils.postOnGlobalLayout(this.mDecorLayout, new t8.e(10, this));
    }

    public void onViewAttached() {
        super.onViewAttached();
        updateDecorViewIconBgColor();
    }

    public abstract boolean supportChangeBg();
}
