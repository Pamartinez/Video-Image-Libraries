package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Optimizer;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final boolean MEASURE = false;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.0.4";
    SparseArray<View> mChildrenByIds = new SparseArray<>();
    /* access modifiers changed from: private */
    public ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<>(4);
    protected ConstraintLayoutStates mConstraintLayoutSpec = null;
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private ConstraintsChangedListener mConstraintsChangedListener;
    private HashMap<String, Integer> mDesignIds = new HashMap<>();
    protected boolean mDirtyHierarchy = true;
    private int mLastMeasureHeight = -1;
    int mLastMeasureHeightMode = 0;
    int mLastMeasureHeightSize = -1;
    private int mLastMeasureWidth = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureWidthSize = -1;
    protected ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMaxHeight = Integer.MAX_VALUE;
    private int mMaxWidth = Integer.MAX_VALUE;
    Measurer mMeasurer = new Measurer(this);
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOnMeasureHeightMeasureSpec = 0;
    private int mOnMeasureWidthMeasureSpec = 0;
    /* access modifiers changed from: private */
    public int mOptimizationLevel = 257;
    private SparseArray<ConstraintWidget> mTempMapIdToWidget = new SparseArray<>();

    /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour = r0
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class Measurer implements BasicMeasure.Measurer {
        ConstraintLayout layout;
        int layoutHeightSpec;
        int layoutWidthSpec;
        int paddingBottom;
        int paddingHeight;
        int paddingTop;
        int paddingWidth;

        public Measurer(ConstraintLayout constraintLayout) {
            this.layout = constraintLayout;
        }

        private boolean isSimilarSpec(int i2, int i7, int i8) {
            if (i2 == i7) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i2);
            View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i7);
            int size = View.MeasureSpec.getSize(i7);
            if (mode2 != 1073741824) {
                return false;
            }
            if ((mode == Integer.MIN_VALUE || mode == 0) && i8 == size) {
                return true;
            }
            return false;
        }

        public void captureLayoutInfos(int i2, int i7, int i8, int i10, int i11, int i12) {
            this.paddingTop = i8;
            this.paddingBottom = i10;
            this.paddingWidth = i11;
            this.paddingHeight = i12;
            this.layoutWidthSpec = i2;
            this.layoutHeightSpec = i7;
        }

        public final void didMeasures() {
            int childCount = this.layout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.layout.getChildAt(i2);
            }
            int size = this.layout.mConstraintHelpers.size();
            if (size > 0) {
                for (int i7 = 0; i7 < size; i7++) {
                    ((ConstraintHelper) this.layout.mConstraintHelpers.get(i7)).updatePostMeasure(this.layout);
                }
            }
        }

        public final void measure(ConstraintWidget constraintWidget, BasicMeasure.Measure measure) {
            int i2;
            int i7;
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            boolean z10;
            boolean z11;
            int i8;
            int i10;
            int i11;
            int i12;
            boolean z12;
            boolean z13;
            boolean z14;
            boolean z15;
            boolean z16;
            boolean z17;
            ConstraintWidget constraintWidget2 = constraintWidget;
            BasicMeasure.Measure measure2 = measure;
            if (constraintWidget2 != null) {
                if (constraintWidget2.getVisibility() == 8 && !constraintWidget2.isInPlaceholder()) {
                    measure2.measuredWidth = 0;
                    measure2.measuredHeight = 0;
                    measure2.measuredBaseline = 0;
                } else if (constraintWidget2.getParent() != null) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure2.horizontalBehavior;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = measure2.verticalBehavior;
                    int i13 = measure2.horizontalDimension;
                    int i14 = measure2.verticalDimension;
                    int i15 = this.paddingTop + this.paddingBottom;
                    int i16 = this.paddingWidth;
                    View view = (View) constraintWidget2.getCompanionWidget();
                    int[] iArr = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour;
                    int i17 = iArr[dimensionBehaviour.ordinal()];
                    if (i17 == 1) {
                        i2 = View.MeasureSpec.makeMeasureSpec(i13, 1073741824);
                    } else if (i17 == 2) {
                        i2 = ViewGroup.getChildMeasureSpec(this.layoutWidthSpec, i16, -2);
                    } else if (i17 == 3) {
                        i2 = ViewGroup.getChildMeasureSpec(this.layoutWidthSpec, constraintWidget2.getHorizontalMargin() + i16, -1);
                    } else if (i17 != 4) {
                        i2 = 0;
                    } else {
                        i2 = ViewGroup.getChildMeasureSpec(this.layoutWidthSpec, i16, -2);
                        if (constraintWidget2.mMatchConstraintDefaultWidth == 1) {
                            z16 = true;
                        } else {
                            z16 = false;
                        }
                        int i18 = measure2.measureStrategy;
                        if (i18 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS || i18 == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) {
                            if (view.getMeasuredHeight() == constraintWidget2.getHeight()) {
                                z17 = true;
                            } else {
                                z17 = false;
                            }
                            if (measure2.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS || !z16 || ((z16 && z17) || constraintWidget2.isResolvedHorizontally())) {
                                i2 = View.MeasureSpec.makeMeasureSpec(constraintWidget2.getWidth(), 1073741824);
                            }
                        }
                    }
                    int i19 = iArr[dimensionBehaviour2.ordinal()];
                    if (i19 == 1) {
                        i7 = View.MeasureSpec.makeMeasureSpec(i14, 1073741824);
                    } else if (i19 == 2) {
                        i7 = ViewGroup.getChildMeasureSpec(this.layoutHeightSpec, i15, -2);
                    } else if (i19 == 3) {
                        i7 = ViewGroup.getChildMeasureSpec(this.layoutHeightSpec, constraintWidget2.getVerticalMargin() + i15, -1);
                    } else if (i19 != 4) {
                        i7 = 0;
                    } else {
                        i7 = ViewGroup.getChildMeasureSpec(this.layoutHeightSpec, i15, -2);
                        if (constraintWidget2.mMatchConstraintDefaultHeight == 1) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        int i20 = measure2.measureStrategy;
                        if (i20 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS || i20 == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) {
                            if (view.getMeasuredWidth() == constraintWidget2.getWidth()) {
                                z15 = true;
                            } else {
                                z15 = false;
                            }
                            if (measure2.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS || !z14 || ((z14 && z15) || constraintWidget2.isResolvedVertically())) {
                                i7 = View.MeasureSpec.makeMeasureSpec(constraintWidget2.getHeight(), 1073741824);
                            }
                        }
                    }
                    ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget2.getParent();
                    if (constraintWidgetContainer == null || !Optimizer.enabled(ConstraintLayout.this.mOptimizationLevel, 256) || view.getMeasuredWidth() != constraintWidget2.getWidth() || view.getMeasuredWidth() >= constraintWidgetContainer.getWidth() || view.getMeasuredHeight() != constraintWidget2.getHeight() || view.getMeasuredHeight() >= constraintWidgetContainer.getHeight() || view.getBaseline() != constraintWidget2.getBaselineDistance() || constraintWidget2.isMeasureRequested() || !isSimilarSpec(constraintWidget2.getLastHorizontalMeasureSpec(), i2, constraintWidget2.getWidth()) || !isSimilarSpec(constraintWidget2.getLastVerticalMeasureSpec(), i7, constraintWidget2.getHeight())) {
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (dimensionBehaviour == dimensionBehaviour3) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (dimensionBehaviour2 == dimensionBehaviour3) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                        if (dimensionBehaviour2 == dimensionBehaviour4 || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.FIXED) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        if (dimensionBehaviour == dimensionBehaviour4 || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        if (!z || constraintWidget2.mDimensionRatio <= 0.0f) {
                            z10 = false;
                        } else {
                            z10 = true;
                        }
                        if (!z3 || constraintWidget2.mDimensionRatio <= 0.0f) {
                            z11 = false;
                        } else {
                            z11 = true;
                        }
                        if (view != null) {
                            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                            int i21 = measure2.measureStrategy;
                            if (i21 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS || i21 == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS || !z || constraintWidget2.mMatchConstraintDefaultWidth != 0 || !z3 || constraintWidget2.mMatchConstraintDefaultHeight != 0) {
                                if (!(view instanceof VirtualLayout) || !(constraintWidget2 instanceof VirtualLayout)) {
                                    view.measure(i2, i7);
                                } else {
                                    ((VirtualLayout) view).onMeasure((VirtualLayout) constraintWidget2, i2, i7);
                                }
                                constraintWidget2.setLastMeasureSpec(i2, i7);
                                int measuredWidth = view.getMeasuredWidth();
                                int measuredHeight = view.getMeasuredHeight();
                                i10 = view.getBaseline();
                                int i22 = constraintWidget2.mMatchConstraintMinWidth;
                                if (i22 > 0) {
                                    i8 = Math.max(i22, measuredWidth);
                                } else {
                                    i8 = measuredWidth;
                                }
                                int i23 = constraintWidget2.mMatchConstraintMaxWidth;
                                if (i23 > 0) {
                                    i8 = Math.min(i23, i8);
                                }
                                int i24 = constraintWidget2.mMatchConstraintMinHeight;
                                if (i24 > 0) {
                                    i12 = Math.max(i24, measuredHeight);
                                } else {
                                    i12 = measuredHeight;
                                }
                                boolean z18 = z9;
                                int i25 = constraintWidget2.mMatchConstraintMaxHeight;
                                if (i25 > 0) {
                                    i12 = Math.min(i25, i12);
                                }
                                if (!Optimizer.enabled(ConstraintLayout.this.mOptimizationLevel, 1)) {
                                    if (z10 && z7) {
                                        i8 = (int) ((((float) i12) * constraintWidget2.mDimensionRatio) + 0.5f);
                                    } else if (z11 && z18) {
                                        i12 = (int) ((((float) i8) / constraintWidget2.mDimensionRatio) + 0.5f);
                                    }
                                }
                                if (!(measuredWidth == i8 && measuredHeight == i12)) {
                                    if (measuredWidth != i8) {
                                        i2 = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
                                    }
                                    if (measuredHeight != i12) {
                                        i7 = View.MeasureSpec.makeMeasureSpec(i12, 1073741824);
                                    }
                                    view.measure(i2, i7);
                                    constraintWidget2.setLastMeasureSpec(i2, i7);
                                    i8 = view.getMeasuredWidth();
                                    i12 = view.getMeasuredHeight();
                                    i10 = view.getBaseline();
                                }
                                i11 = -1;
                            } else {
                                i12 = 0;
                                i11 = -1;
                                i10 = 0;
                                i8 = 0;
                            }
                            if (i10 != i11) {
                                z12 = true;
                            } else {
                                z12 = false;
                            }
                            if (i8 == measure2.horizontalDimension && i12 == measure2.verticalDimension) {
                                z13 = false;
                            } else {
                                z13 = true;
                            }
                            measure2.measuredNeedsSolverPass = z13;
                            if (layoutParams.needsBaseline) {
                                z12 = true;
                            }
                            if (!(!z12 || i10 == -1 || constraintWidget2.getBaselineDistance() == i10)) {
                                measure2.measuredNeedsSolverPass = true;
                            }
                            measure2.measuredWidth = i8;
                            measure2.measuredHeight = i12;
                            measure2.measuredHasBaseline = z12;
                            measure2.measuredBaseline = i10;
                            return;
                        }
                        return;
                    }
                    measure2.measuredWidth = constraintWidget2.getWidth();
                    measure2.measuredHeight = constraintWidget2.getHeight();
                    measure2.measuredBaseline = constraintWidget2.getBaselineDistance();
                }
            }
        }
    }

    public ConstraintLayout(Context context) {
        super(context);
        init((AttributeSet) null, 0, 0);
    }

    private int getPaddingWidth() {
        int max = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int max2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        if (max2 > 0) {
            return max2;
        }
        return max;
    }

    private final ConstraintWidget getTargetWidget(int i2) {
        if (i2 == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(i2);
        if (view == null && (view = findViewById(i2)) != null && view != this && view.getParent() == this) {
            onViewAdded(view);
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    private void init(AttributeSet attributeSet, int i2, int i7) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mLayoutWidget.setMeasurer(this.mMeasurer);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout, i2, i7);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = obtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == R$styleable.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            parseLayoutDescription(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.mConstraintLayoutSpec = null;
                        }
                    }
                } else if (index == R$styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.load(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    private void markHierarchyDirty() {
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    private void setChildrenConstraints() {
        ConstraintLayout constraintLayout;
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ConstraintWidget viewWidget = getViewWidget(getChildAt(i2));
            if (viewWidget != null) {
                viewWidget.reset();
            }
        }
        if (isInEditMode) {
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    getTargetWidget(childAt.getId()).setDebugName(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i8 = 0; i8 < childCount; i8++) {
                getChildAt(i8).getId();
            }
        }
        ConstraintSet constraintSet = this.mConstraintSet;
        if (constraintSet != null) {
            constraintSet.applyToInternal(this, true);
        }
        this.mLayoutWidget.removeAllChildren();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i10 = 0; i10 < size; i10++) {
                this.mConstraintHelpers.get(i10).updatePreLayout(this);
            }
        }
        for (int i11 = 0; i11 < childCount; i11++) {
            getChildAt(i11);
        }
        this.mTempMapIdToWidget.clear();
        this.mTempMapIdToWidget.put(0, this.mLayoutWidget);
        this.mTempMapIdToWidget.put(getId(), this.mLayoutWidget);
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt2 = getChildAt(i12);
            this.mTempMapIdToWidget.put(childAt2.getId(), getViewWidget(childAt2));
        }
        int i13 = 0;
        while (i13 < childCount) {
            View childAt3 = this.getChildAt(i13);
            ConstraintWidget viewWidget2 = this.getViewWidget(childAt3);
            if (viewWidget2 == null) {
                constraintLayout = this;
            } else {
                this.mLayoutWidget.add(viewWidget2);
                constraintLayout = this;
                constraintLayout.applyConstraintsFromLayoutParams(isInEditMode, childAt3, viewWidget2, (LayoutParams) childAt3.getLayoutParams(), this.mTempMapIdToWidget);
            }
            i13++;
            this = constraintLayout;
        }
    }

    private boolean updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            } else if (getChildAt(i2).isLayoutRequested()) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            setChildrenConstraints();
        }
        return z;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void applyConstraintsFromLayoutParams(boolean r17, android.view.View r18, androidx.constraintlayout.solver.widgets.ConstraintWidget r19, androidx.constraintlayout.widget.ConstraintLayout.LayoutParams r20, android.util.SparseArray<androidx.constraintlayout.solver.widgets.ConstraintWidget> r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            r2 = r19
            r7 = r20
            r8 = r21
            r7.validate()
            r9 = 0
            r7.helped = r9
            int r3 = r1.getVisibility()
            r2.setVisibility(r3)
            boolean r3 = r7.isInPlaceholder
            r10 = 1
            if (r3 == 0) goto L_0x0024
            r2.setInPlaceholder(r10)
            r3 = 8
            r2.setVisibility(r3)
        L_0x0024:
            r2.setCompanionWidget(r1)
            boolean r3 = r1 instanceof androidx.constraintlayout.widget.ConstraintHelper
            if (r3 == 0) goto L_0x0036
            androidx.constraintlayout.widget.ConstraintHelper r1 = (androidx.constraintlayout.widget.ConstraintHelper) r1
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r3 = r0.mLayoutWidget
            boolean r3 = r3.isRtl()
            r1.resolveRtl(r2, r3)
        L_0x0036:
            boolean r1 = r7.isGuideline
            r11 = -1
            if (r1 == 0) goto L_0x005a
            r0 = r2
            androidx.constraintlayout.solver.widgets.Guideline r0 = (androidx.constraintlayout.solver.widgets.Guideline) r0
            int r1 = r7.resolvedGuideBegin
            int r2 = r7.resolvedGuideEnd
            float r3 = r7.resolvedGuidePercent
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x004e
            r0.setGuidePercent(r3)
            return
        L_0x004e:
            if (r1 == r11) goto L_0x0054
            r0.setGuideBegin(r1)
            return
        L_0x0054:
            if (r2 == r11) goto L_0x0059
            r0.setGuideEnd(r2)
        L_0x0059:
            return
        L_0x005a:
            int r1 = r7.resolvedLeftToLeft
            int r3 = r7.resolvedLeftToRight
            int r12 = r7.resolvedRightToLeft
            int r13 = r7.resolvedRightToRight
            int r6 = r7.resolveGoneLeftMargin
            int r14 = r7.resolveGoneRightMargin
            float r15 = r7.resolvedHorizontalBias
            int r4 = r7.circleConstraint
            if (r4 == r11) goto L_0x007e
            java.lang.Object r0 = r8.get(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r0
            if (r0 == 0) goto L_0x007b
            float r1 = r7.circleAngle
            int r3 = r7.circleRadius
            r2.connectCircularConstraint(r0, r1, r3)
        L_0x007b:
            r1 = r2
            goto L_0x01a4
        L_0x007e:
            if (r1 == r11) goto L_0x0094
            java.lang.Object r1 = r8.get(r1)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x00aa
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            int r5 = r7.leftMargin
            r4 = r2
            r1 = r19
            r1.immediateConnect(r2, r3, r4, r5, r6)
            goto L_0x00aa
        L_0x0094:
            if (r3 == r11) goto L_0x00aa
            java.lang.Object r1 = r8.get(r3)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x00aa
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            int r5 = r7.leftMargin
            r1 = r19
            r1.immediateConnect(r2, r3, r4, r5, r6)
        L_0x00aa:
            if (r12 == r11) goto L_0x00c2
            java.lang.Object r1 = r8.get(r12)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x00d8
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            int r5 = r7.rightMargin
            r1 = r19
            r6 = r14
            r1.immediateConnect(r2, r3, r4, r5, r6)
            goto L_0x00d8
        L_0x00c2:
            r6 = r14
            if (r13 == r11) goto L_0x00d8
            java.lang.Object r1 = r8.get(r13)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x00d8
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            int r5 = r7.rightMargin
            r4 = r2
            r1 = r19
            r1.immediateConnect(r2, r3, r4, r5, r6)
        L_0x00d8:
            int r1 = r7.topToTop
            if (r1 == r11) goto L_0x00f2
            java.lang.Object r1 = r8.get(r1)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x010c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            int r5 = r7.topMargin
            int r6 = r7.goneTopMargin
            r4 = r2
            r1 = r19
            r1.immediateConnect(r2, r3, r4, r5, r6)
            goto L_0x010c
        L_0x00f2:
            int r1 = r7.topToBottom
            if (r1 == r11) goto L_0x010c
            java.lang.Object r1 = r8.get(r1)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x010c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            int r5 = r7.topMargin
            int r6 = r7.goneTopMargin
            r1 = r19
            r1.immediateConnect(r2, r3, r4, r5, r6)
        L_0x010c:
            int r1 = r7.bottomToTop
            if (r1 == r11) goto L_0x0129
            java.lang.Object r1 = r8.get(r1)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x0126
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            int r5 = r7.bottomMargin
            int r6 = r7.goneBottomMargin
            r1 = r19
            r1.immediateConnect(r2, r3, r4, r5, r6)
        L_0x0126:
            r1 = r19
            goto L_0x0142
        L_0x0129:
            int r1 = r7.bottomToBottom
            if (r1 == r11) goto L_0x0126
            java.lang.Object r1 = r8.get(r1)
            r3 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r3
            if (r3 == 0) goto L_0x0126
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            int r5 = r7.bottomMargin
            int r6 = r7.goneBottomMargin
            r4 = r2
            r1 = r19
            r1.immediateConnect(r2, r3, r4, r5, r6)
        L_0x0142:
            int r2 = r7.baselineToBaseline
            if (r2 == r11) goto L_0x0193
            android.util.SparseArray<android.view.View> r0 = r0.mChildrenByIds
            java.lang.Object r0 = r0.get(r2)
            android.view.View r0 = (android.view.View) r0
            int r2 = r7.baselineToBaseline
            java.lang.Object r2 = r8.get(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r2
            if (r2 == 0) goto L_0x0193
            if (r0 == 0) goto L_0x0193
            android.view.ViewGroup$LayoutParams r3 = r0.getLayoutParams()
            boolean r3 = r3 instanceof androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
            if (r3 == 0) goto L_0x0193
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r0 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r0
            r7.needsBaseline = r10
            r0.needsBaseline = r10
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r1.getAnchor(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.getAnchor(r3)
            r4.connect(r2, r9, r11, r10)
            r1.setHasBaseline(r10)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.widget
            r0.setHasBaseline(r10)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.getAnchor(r0)
            r0.reset()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.getAnchor(r0)
            r0.reset()
        L_0x0193:
            r0 = 0
            int r2 = (r15 > r0 ? 1 : (r15 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x019b
            r1.setHorizontalBiasPercent(r15)
        L_0x019b:
            float r2 = r7.verticalBias
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x01a4
            r1.setVerticalBiasPercent(r2)
        L_0x01a4:
            if (r17 == 0) goto L_0x01b3
            int r0 = r7.editorAbsoluteX
            if (r0 != r11) goto L_0x01ae
            int r2 = r7.editorAbsoluteY
            if (r2 == r11) goto L_0x01b3
        L_0x01ae:
            int r2 = r7.editorAbsoluteY
            r1.setOrigin(r0, r2)
        L_0x01b3:
            boolean r0 = r7.horizontalDimensionFixed
            r2 = -2
            if (r0 != 0) goto L_0x01e9
            int r0 = r7.width
            if (r0 != r11) goto L_0x01e0
            boolean r0 = r7.constrainedWidth
            if (r0 == 0) goto L_0x01c6
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r1.setHorizontalDimensionBehaviour(r0)
            goto L_0x01cb
        L_0x01c6:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            r1.setHorizontalDimensionBehaviour(r0)
        L_0x01cb:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.getAnchor(r0)
            int r3 = r7.leftMargin
            r0.mMargin = r3
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.getAnchor(r0)
            int r3 = r7.rightMargin
            r0.mMargin = r3
            goto L_0x01fc
        L_0x01e0:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r1.setHorizontalDimensionBehaviour(r0)
            r1.setWidth(r9)
            goto L_0x01fc
        L_0x01e9:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.setHorizontalDimensionBehaviour(r0)
            int r0 = r7.width
            r1.setWidth(r0)
            int r0 = r7.width
            if (r0 != r2) goto L_0x01fc
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r1.setHorizontalDimensionBehaviour(r0)
        L_0x01fc:
            boolean r0 = r7.verticalDimensionFixed
            if (r0 != 0) goto L_0x0231
            int r0 = r7.height
            if (r0 != r11) goto L_0x0228
            boolean r0 = r7.constrainedHeight
            if (r0 == 0) goto L_0x020e
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r1.setVerticalDimensionBehaviour(r0)
            goto L_0x0213
        L_0x020e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            r1.setVerticalDimensionBehaviour(r0)
        L_0x0213:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.getAnchor(r0)
            int r2 = r7.topMargin
            r0.mMargin = r2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r1.getAnchor(r0)
            int r2 = r7.bottomMargin
            r0.mMargin = r2
            goto L_0x0244
        L_0x0228:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r1.setVerticalDimensionBehaviour(r0)
            r1.setHeight(r9)
            goto L_0x0244
        L_0x0231:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.setVerticalDimensionBehaviour(r0)
            int r0 = r7.height
            r1.setHeight(r0)
            int r0 = r7.height
            if (r0 != r2) goto L_0x0244
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r1.setVerticalDimensionBehaviour(r0)
        L_0x0244:
            java.lang.String r0 = r7.dimensionRatio
            r1.setDimensionRatio(r0)
            float r0 = r7.horizontalWeight
            r1.setHorizontalWeight(r0)
            float r0 = r7.verticalWeight
            r1.setVerticalWeight(r0)
            int r0 = r7.horizontalChainStyle
            r1.setHorizontalChainStyle(r0)
            int r0 = r7.verticalChainStyle
            r1.setVerticalChainStyle(r0)
            int r0 = r7.matchConstraintDefaultWidth
            int r2 = r7.matchConstraintMinWidth
            int r3 = r7.matchConstraintMaxWidth
            float r4 = r7.matchConstraintPercentWidth
            r1.setHorizontalMatchStyle(r0, r2, r3, r4)
            int r0 = r7.matchConstraintDefaultHeight
            int r2 = r7.matchConstraintMinHeight
            int r3 = r7.matchConstraintMaxHeight
            float r4 = r7.matchConstraintPercentHeight
            r1.setVerticalMatchStyle(r0, r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.applyConstraintsFromLayoutParams(boolean, android.view.View, androidx.constraintlayout.solver.widgets.ConstraintWidget, androidx.constraintlayout.widget.ConstraintLayout$LayoutParams, android.util.SparseArray):void");
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<ConstraintHelper> arrayList = this.mConstraintHelpers;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                this.mConstraintHelpers.get(i2).updatePreDraw(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = (float) getWidth();
            float height = (float) getHeight();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                if (!(childAt.getVisibility() == 8 || (tag = childAt.getTag()) == null || !(tag instanceof String))) {
                    String[] split = ((String) tag).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i8 = (int) ((((float) parseInt) / 1080.0f) * width);
                        int i10 = (int) ((((float) parseInt2) / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = (float) i8;
                        float f5 = (float) i10;
                        float f8 = (float) (i8 + ((int) ((((float) parseInt3) / 1080.0f) * width)));
                        Canvas canvas2 = canvas;
                        canvas2.drawLine(f, f5, f8, f5, paint);
                        float f10 = f;
                        float parseInt4 = (float) (i10 + ((int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * height)));
                        float f11 = f8;
                        canvas2.drawLine(f11, f5, f8, parseInt4, paint);
                        float f12 = f5;
                        float f13 = parseInt4;
                        float f14 = f10;
                        canvas2.drawLine(f11, f13, f14, parseInt4, paint);
                        float f15 = f11;
                        float f16 = f14;
                        float f17 = f12;
                        canvas2.drawLine(f16, f13, f14, f17, paint);
                        float f18 = f17;
                        float f19 = f13;
                        float f20 = f18;
                        paint.setColor(-16711936);
                        float f21 = f15;
                        canvas2.drawLine(f16, f20, f21, f19, paint);
                        canvas2.drawLine(f16, f19, f21, f20, paint);
                    }
                }
            }
        }
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    public void forceLayout() {
        markHierarchyDirty();
        super.forceLayout();
    }

    public Object getDesignInformation(int i2, Object obj) {
        if (i2 != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> hashMap = this.mDesignIds;
        if (hashMap == null || !hashMap.containsKey(str)) {
            return null;
        }
        return this.mDesignIds.get(str);
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public View getViewById(int i2) {
        return this.mChildrenByIds.get(i2);
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    public boolean isRtl() {
        if ((getContext().getApplicationInfo().flags & OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE) == 0 || 1 != getLayoutDirection()) {
            return false;
        }
        return true;
    }

    public void loadLayoutDescription(int i2) {
        if (i2 != 0) {
            try {
                this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i2);
            } catch (Resources.NotFoundException unused) {
                this.mConstraintLayoutSpec = null;
            }
        } else {
            this.mConstraintLayoutSpec = null;
        }
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if ((childAt.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || layoutParams.isVirtualGroup || isInEditMode) && !layoutParams.isInPlaceholder) {
                int x9 = constraintWidget.getX();
                int y = constraintWidget.getY();
                childAt.layout(x9, y, constraintWidget.getWidth() + x9, constraintWidget.getHeight() + y);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i12 = 0; i12 < size; i12++) {
                this.mConstraintHelpers.get(i12).updatePostLayout(this);
            }
        }
    }

    public void onMeasure(int i2, int i7) {
        if (!this.mDirtyHierarchy) {
            int childCount = getChildCount();
            int i8 = 0;
            while (true) {
                if (i8 >= childCount) {
                    break;
                } else if (getChildAt(i8).isLayoutRequested()) {
                    this.mDirtyHierarchy = true;
                    break;
                } else {
                    i8++;
                }
            }
        }
        if (!this.mDirtyHierarchy) {
            int i10 = this.mOnMeasureWidthMeasureSpec;
            if (i10 == i2 && this.mOnMeasureHeightMeasureSpec == i7) {
                resolveMeasuredDimension(i2, i7, this.mLayoutWidget.getWidth(), this.mLayoutWidget.getHeight(), this.mLayoutWidget.isWidthMeasuredTooSmall(), this.mLayoutWidget.isHeightMeasuredTooSmall());
                return;
            } else if (i10 == i2 && View.MeasureSpec.getMode(i2) == 1073741824 && View.MeasureSpec.getMode(i7) == Integer.MIN_VALUE && View.MeasureSpec.getMode(this.mOnMeasureHeightMeasureSpec) == Integer.MIN_VALUE && View.MeasureSpec.getSize(i7) >= this.mLayoutWidget.getHeight()) {
                this.mOnMeasureWidthMeasureSpec = i2;
                this.mOnMeasureHeightMeasureSpec = i7;
                resolveMeasuredDimension(i2, i7, this.mLayoutWidget.getWidth(), this.mLayoutWidget.getHeight(), this.mLayoutWidget.isWidthMeasuredTooSmall(), this.mLayoutWidget.isHeightMeasuredTooSmall());
                return;
            }
        }
        this.mOnMeasureWidthMeasureSpec = i2;
        this.mOnMeasureHeightMeasureSpec = i7;
        this.mLayoutWidget.setRtl(isRtl());
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            if (updateHierarchy()) {
                this.mLayoutWidget.updateHierarchy();
            }
        }
        resolveSystem(this.mLayoutWidget, this.mOptimizationLevel, i2, i7);
        resolveMeasuredDimension(i2, i7, this.mLayoutWidget.getWidth(), this.mLayoutWidget.getHeight(), this.mLayoutWidget.isWidthMeasuredTooSmall(), this.mLayoutWidget.isHeightMeasuredTooSmall());
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Guideline guideline = new Guideline();
            layoutParams.widget = guideline;
            layoutParams.isGuideline = true;
            guideline.setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = true;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mChildrenByIds.remove(view.getId());
        this.mLayoutWidget.remove(getViewWidget(view));
        this.mConstraintHelpers.remove(view);
        this.mDirtyHierarchy = true;
    }

    public void parseLayoutDescription(int i2) {
        this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i2);
    }

    public void removeView(View view) {
        super.removeView(view);
    }

    public void requestLayout() {
        markHierarchyDirty();
        super.requestLayout();
    }

    public void resolveMeasuredDimension(int i2, int i7, int i8, int i10, boolean z, boolean z3) {
        Measurer measurer = this.mMeasurer;
        int i11 = measurer.paddingHeight;
        int resolveSizeAndState = View.resolveSizeAndState(i8 + measurer.paddingWidth, i2, 0);
        int min = Math.min(this.mMaxWidth, resolveSizeAndState & 16777215);
        int min2 = Math.min(this.mMaxHeight, View.resolveSizeAndState(i10 + i11, i7, 0) & 16777215);
        if (z) {
            min |= 16777216;
        }
        if (z3) {
            min2 |= 16777216;
        }
        setMeasuredDimension(min, min2);
        this.mLastMeasureWidth = min;
        this.mLastMeasureHeight = min2;
    }

    public void resolveSystem(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i7, int i8) {
        int i10;
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        int max = Math.max(0, getPaddingTop());
        int max2 = Math.max(0, getPaddingBottom());
        int i11 = max + max2;
        int paddingWidth = getPaddingWidth();
        this.mMeasurer.captureLayoutInfos(i7, i8, max, max2, paddingWidth, i11);
        int max3 = Math.max(0, getPaddingStart());
        int max4 = Math.max(0, getPaddingEnd());
        if (max3 <= 0 && max4 <= 0) {
            max3 = Math.max(0, getPaddingLeft());
        } else if (isRtl()) {
            i10 = max4;
            int i12 = size - paddingWidth;
            int i13 = size2 - i11;
            setSelfDimensionBehaviour(constraintWidgetContainer, mode, i12, mode2, i13);
            constraintWidgetContainer.measure(i2, mode, i12, mode2, i13, this.mLastMeasureWidth, this.mLastMeasureHeight, i10, max);
        }
        i10 = max3;
        int i122 = size - paddingWidth;
        int i132 = size2 - i11;
        setSelfDimensionBehaviour(constraintWidgetContainer, mode, i122, mode2, i132);
        constraintWidgetContainer.measure(i2, mode, i122, mode2, i132, this.mLastMeasureWidth, this.mLastMeasureHeight, i10, max);
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public void setDesignInformation(int i2, Object obj, Object obj2) {
        if (i2 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, (Integer) obj2);
        }
    }

    public void setId(int i2) {
        this.mChildrenByIds.remove(getId());
        super.setId(i2);
        this.mChildrenByIds.put(getId(), this);
    }

    public void setMaxHeight(int i2) {
        if (i2 != this.mMaxHeight) {
            this.mMaxHeight = i2;
            requestLayout();
        }
    }

    public void setMaxWidth(int i2) {
        if (i2 != this.mMaxWidth) {
            this.mMaxWidth = i2;
            requestLayout();
        }
    }

    public void setMinHeight(int i2) {
        if (i2 != this.mMinHeight) {
            this.mMinHeight = i2;
            requestLayout();
        }
    }

    public void setMinWidth(int i2) {
        if (i2 != this.mMinWidth) {
            this.mMinWidth = i2;
            requestLayout();
        }
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.setOnConstraintsChanged(constraintsChangedListener);
        }
    }

    public void setOptimizationLevel(int i2) {
        this.mOptimizationLevel = i2;
        this.mLayoutWidget.setOptimizationLevel(i2);
    }

    public void setSelfDimensionBehaviour(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i7, int i8, int i10) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        Measurer measurer = this.mMeasurer;
        int i11 = measurer.paddingHeight;
        int i12 = measurer.paddingWidth;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        int childCount = getChildCount();
        if (i2 != Integer.MIN_VALUE) {
            if (i2 == 0) {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (childCount == 0) {
                    i7 = Math.max(0, this.mMinWidth);
                }
            } else if (i2 != 1073741824) {
                dimensionBehaviour = dimensionBehaviour2;
            } else {
                i7 = Math.min(this.mMaxWidth - i12, i7);
                dimensionBehaviour = dimensionBehaviour2;
            }
            i7 = 0;
        } else {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i7 = Math.max(0, this.mMinWidth);
            }
        }
        if (i8 != Integer.MIN_VALUE) {
            if (i8 == 0) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (childCount == 0) {
                    i10 = Math.max(0, this.mMinHeight);
                }
            } else if (i8 == 1073741824) {
                i10 = Math.min(this.mMaxHeight - i11, i10);
            }
            i10 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i10 = Math.max(0, this.mMinHeight);
            }
        }
        if (!(i7 == constraintWidgetContainer.getWidth() && i10 == constraintWidgetContainer.getHeight())) {
            constraintWidgetContainer.invalidateMeasures();
        }
        constraintWidgetContainer.setX(0);
        constraintWidgetContainer.setY(0);
        constraintWidgetContainer.setMaxWidth(this.mMaxWidth - i12);
        constraintWidgetContainer.setMaxHeight(this.mMaxHeight - i11);
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setHorizontalDimensionBehaviour(dimensionBehaviour);
        constraintWidgetContainer.setWidth(i7);
        constraintWidgetContainer.setVerticalDimensionBehaviour(dimensionBehaviour2);
        constraintWidgetContainer.setHeight(i10);
        constraintWidgetContainer.setMinWidth(this.mMinWidth - i12);
        constraintWidgetContainer.setMinHeight(this.mMinHeight - i11);
    }

    public void setState(int i2, int i7, int i8) {
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i2, (float) i7, (float) i8);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(attributeSet, i2, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        init(attributeSet, i2, i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int baselineToBaseline = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String constraintTag = null;
        public String dimensionRatio = null;
        int dimensionRatioSide = 1;
        float dimensionRatioValue = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean helped = false;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        boolean horizontalDimensionFixed = true;
        public float horizontalWeight = -1.0f;
        boolean isGuideline = false;
        boolean isHelper = false;
        boolean isInPlaceholder = false;
        boolean isVirtualGroup = false;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int matchConstraintDefaultHeight = 0;
        public int matchConstraintDefaultWidth = 0;
        public int matchConstraintMaxHeight = 0;
        public int matchConstraintMaxWidth = 0;
        public int matchConstraintMinHeight = 0;
        public int matchConstraintMinWidth = 0;
        public float matchConstraintPercentHeight = 1.0f;
        public float matchConstraintPercentWidth = 1.0f;
        boolean needsBaseline = false;
        public int orientation = -1;
        int resolveGoneLeftMargin = -1;
        int resolveGoneRightMargin = -1;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias = 0.5f;
        int resolvedLeftToLeft = -1;
        int resolvedLeftToRight = -1;
        int resolvedRightToLeft = -1;
        int resolvedRightToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        boolean verticalDimensionFixed = true;
        public float verticalWeight = -1.0f;
        ConstraintWidget widget = new ConstraintWidget();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Table {
            public static final SparseIntArray map;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                map = sparseIntArray;
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTag, 51);
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            int i2;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                int i8 = Table.map.get(index);
                switch (i8) {
                    case 1:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 2:
                        int resourceId = obtainStyledAttributes.getResourceId(index, this.circleConstraint);
                        this.circleConstraint = resourceId;
                        if (resourceId != -1) {
                            break;
                        } else {
                            this.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 3:
                        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                        break;
                    case 4:
                        float f = obtainStyledAttributes.getFloat(index, this.circleAngle) % 360.0f;
                        this.circleAngle = f;
                        if (f >= 0.0f) {
                            break;
                        } else {
                            this.circleAngle = (360.0f - f) % 360.0f;
                            break;
                        }
                    case 5:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 6:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 7:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 8:
                        int resourceId2 = obtainStyledAttributes.getResourceId(index, this.leftToLeft);
                        this.leftToLeft = resourceId2;
                        if (resourceId2 != -1) {
                            break;
                        } else {
                            this.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 9:
                        int resourceId3 = obtainStyledAttributes.getResourceId(index, this.leftToRight);
                        this.leftToRight = resourceId3;
                        if (resourceId3 != -1) {
                            break;
                        } else {
                            this.leftToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 10:
                        int resourceId4 = obtainStyledAttributes.getResourceId(index, this.rightToLeft);
                        this.rightToLeft = resourceId4;
                        if (resourceId4 != -1) {
                            break;
                        } else {
                            this.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 11:
                        int resourceId5 = obtainStyledAttributes.getResourceId(index, this.rightToRight);
                        this.rightToRight = resourceId5;
                        if (resourceId5 != -1) {
                            break;
                        } else {
                            this.rightToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 12:
                        int resourceId6 = obtainStyledAttributes.getResourceId(index, this.topToTop);
                        this.topToTop = resourceId6;
                        if (resourceId6 != -1) {
                            break;
                        } else {
                            this.topToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 13:
                        int resourceId7 = obtainStyledAttributes.getResourceId(index, this.topToBottom);
                        this.topToBottom = resourceId7;
                        if (resourceId7 != -1) {
                            break;
                        } else {
                            this.topToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 14:
                        int resourceId8 = obtainStyledAttributes.getResourceId(index, this.bottomToTop);
                        this.bottomToTop = resourceId8;
                        if (resourceId8 != -1) {
                            break;
                        } else {
                            this.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 15:
                        int resourceId9 = obtainStyledAttributes.getResourceId(index, this.bottomToBottom);
                        this.bottomToBottom = resourceId9;
                        if (resourceId9 != -1) {
                            break;
                        } else {
                            this.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 16:
                        int resourceId10 = obtainStyledAttributes.getResourceId(index, this.baselineToBaseline);
                        this.baselineToBaseline = resourceId10;
                        if (resourceId10 != -1) {
                            break;
                        } else {
                            this.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 17:
                        int resourceId11 = obtainStyledAttributes.getResourceId(index, this.startToEnd);
                        this.startToEnd = resourceId11;
                        if (resourceId11 != -1) {
                            break;
                        } else {
                            this.startToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 18:
                        int resourceId12 = obtainStyledAttributes.getResourceId(index, this.startToStart);
                        this.startToStart = resourceId12;
                        if (resourceId12 != -1) {
                            break;
                        } else {
                            this.startToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 19:
                        int resourceId13 = obtainStyledAttributes.getResourceId(index, this.endToStart);
                        this.endToStart = resourceId13;
                        if (resourceId13 != -1) {
                            break;
                        } else {
                            this.endToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 20:
                        int resourceId14 = obtainStyledAttributes.getResourceId(index, this.endToEnd);
                        this.endToEnd = resourceId14;
                        if (resourceId14 != -1) {
                            break;
                        } else {
                            this.endToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 21:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 22:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 23:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 24:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 25:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 26:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 27:
                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                        break;
                    case 28:
                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                        break;
                    case 29:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 30:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 31:
                        int i10 = obtainStyledAttributes.getInt(index, 0);
                        this.matchConstraintDefaultWidth = i10;
                        if (i10 != 1) {
                            break;
                        } else {
                            Log.e(ConstraintLayout.TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                            break;
                        }
                    case 32:
                        int i11 = obtainStyledAttributes.getInt(index, 0);
                        this.matchConstraintDefaultHeight = i11;
                        if (i11 != 1) {
                            break;
                        } else {
                            Log.e(ConstraintLayout.TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            break;
                        }
                    case 33:
                        try {
                            this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinWidth);
                            break;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinWidth = -2;
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxWidth);
                            break;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxWidth = -2;
                                break;
                            }
                        }
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentWidth));
                        this.matchConstraintDefaultWidth = 2;
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinHeight);
                            break;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinHeight = -2;
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxHeight);
                            break;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxHeight = -2;
                                break;
                            }
                        }
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentHeight));
                        this.matchConstraintDefaultHeight = 2;
                        break;
                    default:
                        switch (i8) {
                            case 44:
                                String string = obtainStyledAttributes.getString(index);
                                this.dimensionRatio = string;
                                this.dimensionRatioValue = Float.NaN;
                                this.dimensionRatioSide = -1;
                                if (string == null) {
                                    break;
                                } else {
                                    int length = string.length();
                                    int indexOf = this.dimensionRatio.indexOf(44);
                                    if (indexOf <= 0 || indexOf >= length - 1) {
                                        i2 = 0;
                                    } else {
                                        String substring = this.dimensionRatio.substring(0, indexOf);
                                        if (substring.equalsIgnoreCase("W")) {
                                            this.dimensionRatioSide = 0;
                                        } else if (substring.equalsIgnoreCase("H")) {
                                            this.dimensionRatioSide = 1;
                                        }
                                        i2 = indexOf + 1;
                                    }
                                    int indexOf2 = this.dimensionRatio.indexOf(58);
                                    if (indexOf2 >= 0 && indexOf2 < length - 1) {
                                        String substring2 = this.dimensionRatio.substring(i2, indexOf2);
                                        String substring3 = this.dimensionRatio.substring(indexOf2 + 1);
                                        if (substring2.length() > 0 && substring3.length() > 0) {
                                            try {
                                                float parseFloat = Float.parseFloat(substring2);
                                                float parseFloat2 = Float.parseFloat(substring3);
                                                if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                                                    if (this.dimensionRatioSide != 1) {
                                                        this.dimensionRatioValue = Math.abs(parseFloat / parseFloat2);
                                                        break;
                                                    } else {
                                                        this.dimensionRatioValue = Math.abs(parseFloat2 / parseFloat);
                                                        break;
                                                    }
                                                }
                                            } catch (NumberFormatException unused5) {
                                                break;
                                            }
                                        }
                                    } else {
                                        String substring4 = this.dimensionRatio.substring(i2);
                                        if (substring4.length() <= 0) {
                                            break;
                                        } else {
                                            this.dimensionRatioValue = Float.parseFloat(substring4);
                                            break;
                                        }
                                    }
                                }
                                break;
                            case 45:
                                this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                                break;
                            case 46:
                                this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                                break;
                            case 47:
                                this.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                                break;
                            case 50:
                                this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                                break;
                            case 51:
                                this.constraintTag = obtainStyledAttributes.getString(index);
                                break;
                        }
                }
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0048  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x004f  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0056  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x005c  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0074  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x007c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void resolveLayoutDirection(int r10) {
            /*
                r9 = this;
                int r0 = r9.leftMargin
                int r1 = r9.rightMargin
                super.resolveLayoutDirection(r10)
                int r10 = r9.getLayoutDirection()
                r2 = 0
                r3 = 1
                if (r3 != r10) goto L_0x0011
                r10 = r3
                goto L_0x0012
            L_0x0011:
                r10 = r2
            L_0x0012:
                r4 = -1
                r9.resolvedRightToLeft = r4
                r9.resolvedRightToRight = r4
                r9.resolvedLeftToLeft = r4
                r9.resolvedLeftToRight = r4
                int r5 = r9.goneLeftMargin
                r9.resolveGoneLeftMargin = r5
                int r5 = r9.goneRightMargin
                r9.resolveGoneRightMargin = r5
                float r5 = r9.horizontalBias
                r9.resolvedHorizontalBias = r5
                int r6 = r9.guideBegin
                r9.resolvedGuideBegin = r6
                int r7 = r9.guideEnd
                r9.resolvedGuideEnd = r7
                float r8 = r9.guidePercent
                r9.resolvedGuidePercent = r8
                if (r10 == 0) goto L_0x008e
                int r10 = r9.startToEnd
                if (r10 == r4) goto L_0x003d
                r9.resolvedRightToLeft = r10
            L_0x003b:
                r2 = r3
                goto L_0x0044
            L_0x003d:
                int r10 = r9.startToStart
                if (r10 == r4) goto L_0x0044
                r9.resolvedRightToRight = r10
                goto L_0x003b
            L_0x0044:
                int r10 = r9.endToStart
                if (r10 == r4) goto L_0x004b
                r9.resolvedLeftToRight = r10
                r2 = r3
            L_0x004b:
                int r10 = r9.endToEnd
                if (r10 == r4) goto L_0x0052
                r9.resolvedLeftToLeft = r10
                r2 = r3
            L_0x0052:
                int r10 = r9.goneStartMargin
                if (r10 == r4) goto L_0x0058
                r9.resolveGoneRightMargin = r10
            L_0x0058:
                int r10 = r9.goneEndMargin
                if (r10 == r4) goto L_0x005e
                r9.resolveGoneLeftMargin = r10
            L_0x005e:
                r10 = 1065353216(0x3f800000, float:1.0)
                if (r2 == 0) goto L_0x0066
                float r2 = r10 - r5
                r9.resolvedHorizontalBias = r2
            L_0x0066:
                boolean r2 = r9.isGuideline
                if (r2 == 0) goto L_0x00b2
                int r2 = r9.orientation
                if (r2 != r3) goto L_0x00b2
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r3 == 0) goto L_0x007c
                float r10 = r10 - r8
                r9.resolvedGuidePercent = r10
                r9.resolvedGuideBegin = r4
                r9.resolvedGuideEnd = r4
                goto L_0x00b2
            L_0x007c:
                if (r6 == r4) goto L_0x0085
                r9.resolvedGuideEnd = r6
                r9.resolvedGuideBegin = r4
                r9.resolvedGuidePercent = r2
                goto L_0x00b2
            L_0x0085:
                if (r7 == r4) goto L_0x00b2
                r9.resolvedGuideBegin = r7
                r9.resolvedGuideEnd = r4
                r9.resolvedGuidePercent = r2
                goto L_0x00b2
            L_0x008e:
                int r10 = r9.startToEnd
                if (r10 == r4) goto L_0x0094
                r9.resolvedLeftToRight = r10
            L_0x0094:
                int r10 = r9.startToStart
                if (r10 == r4) goto L_0x009a
                r9.resolvedLeftToLeft = r10
            L_0x009a:
                int r10 = r9.endToStart
                if (r10 == r4) goto L_0x00a0
                r9.resolvedRightToLeft = r10
            L_0x00a0:
                int r10 = r9.endToEnd
                if (r10 == r4) goto L_0x00a6
                r9.resolvedRightToRight = r10
            L_0x00a6:
                int r10 = r9.goneStartMargin
                if (r10 == r4) goto L_0x00ac
                r9.resolveGoneLeftMargin = r10
            L_0x00ac:
                int r10 = r9.goneEndMargin
                if (r10 == r4) goto L_0x00b2
                r9.resolveGoneRightMargin = r10
            L_0x00b2:
                int r10 = r9.endToStart
                if (r10 != r4) goto L_0x00fc
                int r10 = r9.endToEnd
                if (r10 != r4) goto L_0x00fc
                int r10 = r9.startToStart
                if (r10 != r4) goto L_0x00fc
                int r10 = r9.startToEnd
                if (r10 != r4) goto L_0x00fc
                int r10 = r9.rightToLeft
                if (r10 == r4) goto L_0x00d1
                r9.resolvedRightToLeft = r10
                int r10 = r9.rightMargin
                if (r10 > 0) goto L_0x00df
                if (r1 <= 0) goto L_0x00df
                r9.rightMargin = r1
                goto L_0x00df
            L_0x00d1:
                int r10 = r9.rightToRight
                if (r10 == r4) goto L_0x00df
                r9.resolvedRightToRight = r10
                int r10 = r9.rightMargin
                if (r10 > 0) goto L_0x00df
                if (r1 <= 0) goto L_0x00df
                r9.rightMargin = r1
            L_0x00df:
                int r10 = r9.leftToLeft
                if (r10 == r4) goto L_0x00ee
                r9.resolvedLeftToLeft = r10
                int r10 = r9.leftMargin
                if (r10 > 0) goto L_0x00fc
                if (r0 <= 0) goto L_0x00fc
                r9.leftMargin = r0
                return
            L_0x00ee:
                int r10 = r9.leftToRight
                if (r10 == r4) goto L_0x00fc
                r9.resolvedLeftToRight = r10
                int r10 = r9.leftMargin
                if (r10 > 0) goto L_0x00fc
                if (r0 <= 0) goto L_0x00fc
                r9.leftMargin = r0
            L_0x00fc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            int i2 = this.width;
            if (i2 == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                if (this.matchConstraintDefaultWidth == 0) {
                    this.matchConstraintDefaultWidth = 1;
                }
            }
            int i7 = this.height;
            if (i7 == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                if (this.matchConstraintDefaultHeight == 0) {
                    this.matchConstraintDefaultHeight = 1;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.horizontalDimensionFixed = false;
                if (i2 == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (i7 == 0 || i7 == -1) {
                this.verticalDimensionFixed = false;
                if (i7 == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = true;
                this.horizontalDimensionFixed = true;
                this.verticalDimensionFixed = true;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }
}
