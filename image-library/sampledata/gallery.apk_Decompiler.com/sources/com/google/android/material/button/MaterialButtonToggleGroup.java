package com.google.android.material.button;

import D2.a;
import G0.e;
import Hf.C0772v;
import Z1.d;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.timepicker.f;
import com.sec.android.gallery3d.R;
import h2.p;
import h2.u;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import x2.C0334a;
import x2.C0343j;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final /* synthetic */ int n = 0;
    public final ArrayList d = new ArrayList();
    public final e e = new e((Object) this);
    public final LinkedHashSet f = new LinkedHashSet();
    public final C0772v g = new C0772v(1, this);

    /* renamed from: h  reason: collision with root package name */
    public Integer[] f1434h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1435i = false;

    /* renamed from: j  reason: collision with root package name */
    public boolean f1436j;
    public boolean k;
    public final int l;
    public HashSet m = new HashSet();

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        super(a.a(context, attributeSet, R.attr.materialButtonToggleGroupStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_MaterialButtonToggleGroup), attributeSet, R.attr.materialButtonToggleGroupStyle);
        AttributeSet attributeSet2 = attributeSet;
        TypedArray d2 = p.d(getContext(), attributeSet2, Q1.a.w, R.attr.materialButtonToggleGroupStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_MaterialButtonToggleGroup, new int[0]);
        setSingleSelection(d2.getBoolean(3, false));
        this.l = d2.getResourceId(1, -1);
        this.k = d2.getBoolean(2, false);
        setChildrenDrawingOrderEnabled(true);
        setEnabled(d2.getBoolean(0, true));
        d2.recycle();
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (c(i2)) {
                return i2;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (c(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i2 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            if ((getChildAt(i7) instanceof MaterialButton) && c(i7)) {
                i2++;
            }
        }
        return i2;
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(ViewCompat.generateViewId());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.e);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public final void a() {
        LinearLayout.LayoutParams layoutParams;
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex != -1) {
            for (int i2 = firstVisibleChildIndex + 1; i2 < getChildCount(); i2++) {
                MaterialButton materialButton = (MaterialButton) getChildAt(i2);
                int min = Math.min(materialButton.getStrokeWidth(), ((MaterialButton) getChildAt(i2 - 1)).getStrokeWidth());
                ViewGroup.LayoutParams layoutParams2 = materialButton.getLayoutParams();
                if (layoutParams2 instanceof LinearLayout.LayoutParams) {
                    layoutParams = (LinearLayout.LayoutParams) layoutParams2;
                } else {
                    layoutParams = new LinearLayout.LayoutParams(layoutParams2.width, layoutParams2.height);
                }
                if (getOrientation() == 0) {
                    MarginLayoutParamsCompat.setMarginEnd(layoutParams, 0);
                    MarginLayoutParamsCompat.setMarginStart(layoutParams, -min);
                    layoutParams.topMargin = 0;
                } else {
                    layoutParams.bottomMargin = 0;
                    layoutParams.topMargin = -min;
                    MarginLayoutParamsCompat.setMarginStart(layoutParams, 0);
                }
                materialButton.setLayoutParams(layoutParams);
            }
            if (getChildCount() != 0 && firstVisibleChildIndex != -1) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ((MaterialButton) getChildAt(firstVisibleChildIndex)).getLayoutParams();
                if (getOrientation() == 1) {
                    layoutParams3.topMargin = 0;
                    layoutParams3.bottomMargin = 0;
                    return;
                }
                MarginLayoutParamsCompat.setMarginEnd(layoutParams3, 0);
                MarginLayoutParamsCompat.setMarginStart(layoutParams3, 0);
                layoutParams3.leftMargin = 0;
                layoutParams3.rightMargin = 0;
            }
        }
    }

    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e("MButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i2, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        b(materialButton.getId(), materialButton.f1432o);
        C0344k shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.d.add(new Z1.e(shapeAppearanceModel.e, shapeAppearanceModel.f2124h, shapeAppearanceModel.f, shapeAppearanceModel.g));
        materialButton.setEnabled(isEnabled());
        ViewCompat.setAccessibilityDelegate(materialButton, new d(this));
    }

    public final void b(int i2, boolean z) {
        if (i2 == -1) {
            Log.e("MButtonToggleGroup", "Button ID is not valid: " + i2);
            return;
        }
        HashSet hashSet = new HashSet(this.m);
        if (z && !hashSet.contains(Integer.valueOf(i2))) {
            if (this.f1436j && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i2));
        } else if (!z && hashSet.contains(Integer.valueOf(i2))) {
            if (!this.k || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i2));
            }
        } else {
            return;
        }
        d(hashSet);
    }

    public final boolean c(int i2) {
        if (getChildAt(i2).getVisibility() != 8) {
            return true;
        }
        return false;
    }

    public final void d(Set set) {
        HashSet hashSet = this.m;
        this.m = new HashSet(set);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            int id = ((MaterialButton) getChildAt(i2)).getId();
            boolean contains = set.contains(Integer.valueOf(id));
            View findViewById = findViewById(id);
            if (findViewById instanceof MaterialButton) {
                this.f1435i = true;
                ((MaterialButton) findViewById).setChecked(contains);
                this.f1435i = false;
            }
            if (hashSet.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                set.contains(Integer.valueOf(id));
                Iterator it = this.f.iterator();
                while (it.hasNext()) {
                    ((f) it.next()).a();
                }
            }
        }
        invalidate();
    }

    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.g);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            treeMap.put((MaterialButton) getChildAt(i2), Integer.valueOf(i2));
        }
        this.f1434h = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void e() {
        boolean z;
        Z1.e eVar;
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i2 = 0; i2 < childCount; i2++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i2);
            if (materialButton.getVisibility() != 8) {
                C0343j e7 = materialButton.getShapeAppearanceModel().e();
                Z1.e eVar2 = (Z1.e) this.d.get(i2);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    if (getOrientation() == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    C0334a aVar = Z1.e.e;
                    if (i2 == firstVisibleChildIndex) {
                        if (!z) {
                            eVar = new Z1.e(eVar2.f967a, aVar, eVar2.b, aVar);
                        } else if (u.c(this)) {
                            eVar = new Z1.e(aVar, aVar, eVar2.b, eVar2.f968c);
                        } else {
                            eVar = new Z1.e(eVar2.f967a, eVar2.d, aVar, aVar);
                        }
                    } else if (i2 != lastVisibleChildIndex) {
                        eVar2 = null;
                    } else if (!z) {
                        eVar = new Z1.e(aVar, eVar2.d, aVar, eVar2.f968c);
                    } else if (u.c(this)) {
                        eVar = new Z1.e(eVar2.f967a, eVar2.d, aVar, aVar);
                    } else {
                        eVar = new Z1.e(aVar, aVar, eVar2.b, eVar2.f968c);
                    }
                    eVar2 = eVar;
                }
                if (eVar2 == null) {
                    e7.e = new C0334a(0.0f);
                    e7.f = new C0334a(0.0f);
                    e7.g = new C0334a(0.0f);
                    e7.f2119h = new C0334a(0.0f);
                } else {
                    e7.e = eVar2.f967a;
                    e7.f2119h = eVar2.d;
                    e7.f = eVar2.b;
                    e7.g = eVar2.f968c;
                }
                materialButton.setShapeAppearanceModel(e7.a());
            }
        }
    }

    public int getCheckedButtonId() {
        if (!this.f1436j || this.m.isEmpty()) {
            return -1;
        }
        return ((Integer) this.m.iterator().next()).intValue();
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            int id = ((MaterialButton) getChildAt(i2)).getId();
            if (this.m.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    public final int getChildDrawingOrder(int i2, int i7) {
        Integer[] numArr = this.f1434h;
        if (numArr != null && i7 < numArr.length) {
            return numArr[i7].intValue();
        }
        Log.w("MButtonToggleGroup", "Child order wasn't updated");
        return i7;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        int i2 = this.l;
        if (i2 != -1) {
            d(Collections.singleton(Integer.valueOf(i2)));
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i2;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
        int visibleButtonCount = getVisibleButtonCount();
        if (this.f1436j) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        wrap.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, visibleButtonCount, false, i2));
    }

    public final void onMeasure(int i2, int i7) {
        e();
        a();
        super.onMeasure(i2, i7);
    }

    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal((Z1.a) null);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.d.remove(indexOfChild);
        }
        e();
        a();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            ((MaterialButton) getChildAt(i2)).setEnabled(z);
        }
    }

    public void setSelectionRequired(boolean z) {
        this.k = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.f1436j != z) {
            this.f1436j = z;
            d(new HashSet());
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            ((MaterialButton) getChildAt(i2)).setA11yClassName((this.f1436j ? RadioButton.class : ToggleButton.class).getName());
        }
    }

    public void setSingleSelection(int i2) {
        setSingleSelection(getResources().getBoolean(i2));
    }
}
