package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.analyzer.ChainRun;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConstraintWidget {
    public static float DEFAULT_BIAS = 0.5f;
    private boolean OPTIMIZE_WRAP = false;
    private boolean OPTIMIZE_WRAP_ON_RESOLVED = true;
    private boolean hasBaseline = false;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public HorizontalWidgetRun horizontalRun = null;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget = {true, true};
    protected ArrayList<ConstraintAnchor> mAnchors;
    public ConstraintAnchor mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
    int mBaselineDistance;
    public ConstraintAnchor mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
    ConstraintAnchor mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
    private float mCircleConstraintAngle = 0.0f;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution = -1;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtuaLayout = false;
    private boolean[] mIsInBarrier;
    private int mLastHorizontalMeasureSpec = 0;
    private int mLastVerticalMeasureSpec = 0;
    public ConstraintAnchor mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight = 0;
    public int mMatchConstraintDefaultWidth = 0;
    public int mMatchConstraintMaxHeight = 0;
    public int mMatchConstraintMaxWidth = 0;
    public int mMatchConstraintMinHeight = 0;
    public int mMatchConstraintMinWidth = 0;
    public float mMatchConstraintPercentHeight = 1.0f;
    public float mMatchConstraintPercentWidth = 1.0f;
    private int[] mMaxDimension = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    private boolean mMeasureRequested = true;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio = 1.0f;
    int mResolvedDimensionRatioSide = -1;
    boolean mResolvedHasRatio = false;
    public int[] mResolvedMatchConstraintDefault = new int[2];
    public ConstraintAnchor mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
    public ConstraintAnchor mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution = -1;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    protected int mX;
    protected int mY;
    public boolean measured = false;
    private boolean resolvedHorizontal = false;
    private boolean resolvedVertical = false;
    public WidgetRun[] run = new WidgetRun[2];
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public VerticalWidgetRun verticalRun = null;

    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type;
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour;

        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008f */
        static {
            /*
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour = r0
                r1 = 1
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type[] r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type = r4
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x004e }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0058 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0062 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x006d }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0078 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0083 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x008f }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x009b }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.NONE     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:328:0x052f, code lost:
        if (r0[1] == r3) goto L_0x0537;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x04c5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x04f5  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x04ff A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:337:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:340:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(androidx.constraintlayout.solver.LinearSystem r33, boolean r34, boolean r35, boolean r36, boolean r37, androidx.constraintlayout.solver.SolverVariable r38, androidx.constraintlayout.solver.SolverVariable r39, androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour r40, boolean r41, androidx.constraintlayout.solver.widgets.ConstraintAnchor r42, androidx.constraintlayout.solver.widgets.ConstraintAnchor r43, int r44, int r45, int r46, int r47, float r48, boolean r49, boolean r50, boolean r51, boolean r52, boolean r53, int r54, int r55, int r56, int r57, float r58, boolean r59) {
        /*
            r32 = this;
            r0 = r32
            r1 = r33
            r12 = r42
            r13 = r43
            r14 = r46
            r2 = r47
            r4 = r56
            r5 = r57
            androidx.constraintlayout.solver.SolverVariable r6 = r1.createObjectVariable(r12)
            androidx.constraintlayout.solver.SolverVariable r7 = r1.createObjectVariable(r13)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r12.getTarget()
            androidx.constraintlayout.solver.SolverVariable r15 = r1.createObjectVariable(r8)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r13.getTarget()
            androidx.constraintlayout.solver.SolverVariable r8 = r1.createObjectVariable(r8)
            androidx.constraintlayout.solver.Metrics r9 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            if (r9 == 0) goto L_0x003a
            androidx.constraintlayout.solver.Metrics r9 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            long r10 = r9.nonresolvedWidgets
            r16 = 1
            long r10 = r10 + r16
            r9.nonresolvedWidgets = r10
        L_0x003a:
            boolean r10 = r12.isConnected()
            boolean r11 = r13.isConnected()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.mCenter
            boolean r16 = r9.isConnected()
            if (r11 == 0) goto L_0x004d
            int r9 = r10 + 1
            goto L_0x004e
        L_0x004d:
            r9 = r10
        L_0x004e:
            if (r16 == 0) goto L_0x0052
            int r9 = r9 + 1
        L_0x0052:
            r17 = r10
            if (r49 == 0) goto L_0x0059
            r18 = 3
            goto L_0x005b
        L_0x0059:
            r18 = r54
        L_0x005b:
            int[] r19 = androidx.constraintlayout.solver.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour
            int r20 = r40.ordinal()
            r10 = r19[r20]
            r19 = r11
            r11 = 1
            if (r10 == r11) goto L_0x0071
            r11 = 2
            if (r10 == r11) goto L_0x0071
            r11 = 3
            if (r10 == r11) goto L_0x0071
            r11 = 4
            if (r10 == r11) goto L_0x0076
        L_0x0071:
            r10 = r18
        L_0x0073:
            r18 = 0
            goto L_0x007c
        L_0x0076:
            r10 = r18
            if (r10 == r11) goto L_0x0073
            r18 = 1
        L_0x007c:
            int r11 = r0.mVisibility
            r3 = 8
            if (r11 != r3) goto L_0x0086
            r11 = 0
            r18 = 0
            goto L_0x0088
        L_0x0086:
            r11 = r45
        L_0x0088:
            if (r59 == 0) goto L_0x00aa
            if (r17 != 0) goto L_0x009a
            if (r19 != 0) goto L_0x009a
            if (r16 != 0) goto L_0x009a
            r3 = r44
            r1.addEquality(r6, r3)
        L_0x0095:
            r23 = r8
            r8 = 8
            goto L_0x00ad
        L_0x009a:
            if (r17 == 0) goto L_0x0095
            if (r19 != 0) goto L_0x0095
            int r3 = r12.getMargin()
            r23 = r8
            r8 = 8
            r1.addEquality(r6, r15, r3, r8)
            goto L_0x00ad
        L_0x00aa:
            r23 = r8
            r8 = r3
        L_0x00ad:
            if (r18 != 0) goto L_0x00d6
            if (r41 == 0) goto L_0x00c6
            r3 = 3
            r8 = 0
            r1.addEquality(r7, r6, r8, r3)
            r8 = 8
            if (r14 <= 0) goto L_0x00bd
            r1.addGreaterThan(r7, r6, r14, r8)
        L_0x00bd:
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r2 >= r3) goto L_0x00c9
            r1.addLowerThan(r7, r6, r2, r8)
            goto L_0x00c9
        L_0x00c6:
            r1.addEquality(r7, r6, r11, r8)
        L_0x00c9:
            r3 = r5
            r5 = r7
            r2 = r9
            r24 = r18
            r11 = r23
            r18 = r37
        L_0x00d2:
            r23 = r4
            goto L_0x01bc
        L_0x00d6:
            r2 = 2
            if (r9 == r2) goto L_0x00f9
            if (r49 != 0) goto L_0x00f9
            r2 = 1
            if (r10 == r2) goto L_0x00e0
            if (r10 != 0) goto L_0x00f9
        L_0x00e0:
            int r2 = java.lang.Math.max(r4, r11)
            if (r5 <= 0) goto L_0x00ea
            int r2 = java.lang.Math.min(r5, r2)
        L_0x00ea:
            r8 = 8
            r1.addEquality(r7, r6, r2, r8)
            r18 = r37
            r3 = r5
            r5 = r7
            r2 = r9
            r11 = r23
            r24 = 0
            goto L_0x00d2
        L_0x00f9:
            r2 = -2
            if (r4 != r2) goto L_0x00fe
            r3 = r11
            goto L_0x00ff
        L_0x00fe:
            r3 = r4
        L_0x00ff:
            if (r5 != r2) goto L_0x0103
            r2 = r11
            goto L_0x0104
        L_0x0103:
            r2 = r5
        L_0x0104:
            if (r11 <= 0) goto L_0x010a
            r4 = 1
            if (r10 == r4) goto L_0x010a
            r11 = 0
        L_0x010a:
            r8 = 8
            if (r3 <= 0) goto L_0x0115
            r1.addGreaterThan(r7, r6, r3, r8)
            int r11 = java.lang.Math.max(r11, r3)
        L_0x0115:
            r4 = 1
            if (r2 <= 0) goto L_0x0124
            if (r35 == 0) goto L_0x011d
            if (r10 != r4) goto L_0x011d
            goto L_0x0120
        L_0x011d:
            r1.addLowerThan(r7, r6, r2, r8)
        L_0x0120:
            int r11 = java.lang.Math.min(r11, r2)
        L_0x0124:
            if (r10 != r4) goto L_0x014a
            if (r35 == 0) goto L_0x012c
            r1.addEquality(r7, r6, r11, r8)
            goto L_0x013d
        L_0x012c:
            if (r51 == 0) goto L_0x0136
            r4 = 5
            r1.addEquality(r7, r6, r11, r4)
            r1.addLowerThan(r7, r6, r11, r8)
            goto L_0x013d
        L_0x0136:
            r4 = 5
            r1.addEquality(r7, r6, r11, r4)
            r1.addLowerThan(r7, r6, r11, r8)
        L_0x013d:
            r5 = r7
            r24 = r18
            r11 = r23
            r18 = r37
            r23 = r3
            r3 = r2
            r2 = r9
            goto L_0x01bc
        L_0x014a:
            r11 = 2
            if (r10 != r11) goto L_0x01af
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = r12.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            if (r4 == r5) goto L_0x0178
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = r12.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r4 != r8) goto L_0x015e
            goto L_0x0178
        L_0x015e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.getAnchor(r5)
            androidx.constraintlayout.solver.SolverVariable r4 = r1.createObjectVariable(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.getAnchor(r8)
            androidx.constraintlayout.solver.SolverVariable r5 = r1.createObjectVariable(r5)
        L_0x0176:
            r8 = r4
            goto L_0x018f
        L_0x0178:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.getAnchor(r5)
            androidx.constraintlayout.solver.SolverVariable r4 = r1.createObjectVariable(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.getAnchor(r8)
            androidx.constraintlayout.solver.SolverVariable r5 = r1.createObjectVariable(r5)
            goto L_0x0176
        L_0x018f:
            androidx.constraintlayout.solver.ArrayRow r4 = r1.createRow()
            r41 = r7
            r7 = r5
            r5 = r41
            r41 = r2
            r2 = r9
            r11 = r23
            r9 = r58
            androidx.constraintlayout.solver.ArrayRow r4 = r4.createRowDimensionRatio(r5, r6, r7, r8, r9)
            r1.addConstraint(r4)
            r18 = r37
            r23 = r3
            r24 = 0
        L_0x01ac:
            r3 = r41
            goto L_0x01bc
        L_0x01af:
            r41 = r2
            r5 = r7
            r2 = r9
            r11 = r23
            r23 = r3
            r24 = r18
            r18 = 1
            goto L_0x01ac
        L_0x01bc:
            if (r59 == 0) goto L_0x04f5
            if (r51 == 0) goto L_0x01ca
            r15 = r38
            r11 = r39
            r3 = 2
            r8 = 0
            r13 = 8
            goto L_0x04fd
        L_0x01ca:
            if (r17 != 0) goto L_0x01d4
            if (r19 != 0) goto L_0x01d4
            if (r16 != 0) goto L_0x01d4
        L_0x01d0:
            r2 = r11
            r8 = 0
            goto L_0x04c1
        L_0x01d4:
            if (r17 == 0) goto L_0x01d9
            if (r19 != 0) goto L_0x01d9
            goto L_0x01d0
        L_0x01d9:
            if (r17 != 0) goto L_0x020b
            if (r19 == 0) goto L_0x020b
            int r2 = r13.getMargin()
            int r2 = -r2
            r8 = 8
            r1.addEquality(r5, r11, r2, r8)
            if (r35 == 0) goto L_0x01d0
            boolean r2 = r0.OPTIMIZE_WRAP
            if (r2 == 0) goto L_0x0201
            boolean r2 = r6.isFinalValue
            if (r2 == 0) goto L_0x0201
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x0201
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r2 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r2
            if (r34 == 0) goto L_0x01fd
            r2.addHorizontalWrapMinVariable(r12)
            goto L_0x01d0
        L_0x01fd:
            r2.addVerticalWrapMinVariable(r12)
            goto L_0x01d0
        L_0x0201:
            r2 = r38
            r4 = 5
            r8 = 0
            r1.addGreaterThan(r6, r2, r8, r4)
        L_0x0208:
            r2 = r11
            goto L_0x04c1
        L_0x020b:
            r2 = r38
            r8 = 0
            if (r17 == 0) goto L_0x0208
            if (r19 == 0) goto L_0x0208
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r12.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r4.mOwner
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r13.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r7.mOwner
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.getParent()
            r16 = 6
            if (r24 == 0) goto L_0x034d
            if (r10 != 0) goto L_0x028a
            if (r3 != 0) goto L_0x024f
            if (r23 != 0) goto L_0x024f
            boolean r3 = r15.isFinalValue
            if (r3 == 0) goto L_0x0242
            boolean r3 = r11.isFinalValue
            if (r3 == 0) goto L_0x0242
            int r0 = r12.getMargin()
            r3 = 8
            r1.addEquality(r6, r15, r0, r3)
            int r0 = r13.getMargin()
            int r0 = -r0
            r1.addEquality(r5, r11, r0, r3)
            return
        L_0x0242:
            r3 = 8
            r17 = r3
            r19 = r17
            r21 = r8
            r25 = r21
            r22 = 1
            goto L_0x025b
        L_0x024f:
            r3 = 8
            r22 = r8
            r17 = 5
            r19 = 5
            r21 = 1
            r25 = 1
        L_0x025b:
            boolean r3 = r4 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r3 != 0) goto L_0x0277
            boolean r3 = r7 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r3 == 0) goto L_0x0264
            goto L_0x0277
        L_0x0264:
            r3 = r6
            r6 = r11
            r28 = r17
            r27 = r19
            r17 = r21
            r26 = r22
            r8 = 5
            r22 = 8
            r11 = r39
            r19 = r16
            goto L_0x03a5
        L_0x0277:
            r3 = r6
            r6 = r11
            r19 = r16
            r28 = r17
            r17 = r21
            r26 = r22
            r8 = 5
            r22 = 8
        L_0x0284:
            r27 = 4
        L_0x0286:
            r11 = r39
            goto L_0x03a5
        L_0x028a:
            r8 = 1
            r22 = 8
            if (r10 != r8) goto L_0x029d
            r3 = r6
            r6 = r11
            r19 = r16
            r28 = r22
            r8 = 5
            r17 = 1
            r25 = 1
            r26 = 0
            goto L_0x0284
        L_0x029d:
            r8 = 3
            if (r10 != r8) goto L_0x033f
            int r8 = r0.mResolvedDimensionRatioSide
            r1 = -1
            if (r8 != r1) goto L_0x02ca
            if (r52 == 0) goto L_0x02be
            r1 = r33
            r3 = r6
            r6 = r11
            r28 = r22
            r8 = 5
            r17 = 1
            if (r35 == 0) goto L_0x02bb
            r19 = 5
        L_0x02b4:
            r25 = 1
            r26 = 1
            r27 = 5
            goto L_0x0286
        L_0x02bb:
            r19 = 4
            goto L_0x02b4
        L_0x02be:
            r1 = r33
            r3 = r6
            r6 = r11
            r19 = r22
            r28 = r19
            r8 = 5
            r17 = 1
            goto L_0x02b4
        L_0x02ca:
            if (r49 == 0) goto L_0x02ed
            r1 = r55
            r8 = 2
            if (r1 == r8) goto L_0x02d9
            r8 = 1
            if (r1 != r8) goto L_0x02d5
            goto L_0x02d9
        L_0x02d5:
            r8 = r22
            r1 = 5
            goto L_0x02db
        L_0x02d9:
            r1 = 4
            r8 = 5
        L_0x02db:
            r27 = r1
            r3 = r6
            r28 = r8
            r6 = r11
            r19 = r16
            r8 = 5
            r17 = 1
            r25 = 1
            r26 = 1
            r1 = r33
            goto L_0x0286
        L_0x02ed:
            if (r3 <= 0) goto L_0x0301
            r1 = r33
            r3 = r6
            r6 = r11
            r19 = r16
            r8 = 5
            r17 = 1
            r25 = 1
            r26 = 1
            r27 = 5
        L_0x02fe:
            r28 = 5
            goto L_0x0286
        L_0x0301:
            if (r3 != 0) goto L_0x032f
            if (r23 != 0) goto L_0x032f
            if (r52 != 0) goto L_0x0317
            r1 = r33
            r3 = r6
            r6 = r11
            r19 = r16
            r27 = r22
            r8 = 5
            r17 = 1
            r25 = 1
            r26 = 1
            goto L_0x02fe
        L_0x0317:
            if (r4 == r9) goto L_0x031d
            if (r7 == r9) goto L_0x031d
            r8 = 4
            goto L_0x031e
        L_0x031d:
            r8 = 5
        L_0x031e:
            r1 = r33
            r3 = r6
            r28 = r8
            r6 = r11
            r19 = r16
            r8 = 5
            r17 = 1
            r25 = 1
            r26 = 1
            goto L_0x0284
        L_0x032f:
            r1 = r33
            r3 = r6
            r6 = r11
            r19 = r16
            r8 = 5
            r17 = 1
            r25 = 1
            r26 = 1
        L_0x033c:
            r27 = 4
            goto L_0x02fe
        L_0x033f:
            r1 = r33
            r3 = r6
            r6 = r11
            r19 = r16
            r8 = 5
            r17 = 0
            r25 = 0
            r26 = 0
            goto L_0x033c
        L_0x034d:
            r22 = 8
            boolean r1 = r15.isFinalValue
            if (r1 == 0) goto L_0x0392
            boolean r1 = r11.isFinalValue
            if (r1 == 0) goto L_0x0392
            int r0 = r12.getMargin()
            int r1 = r13.getMargin()
            r2 = 8
            r49 = r33
            r53 = r48
            r52 = r0
            r56 = r1
            r57 = r2
            r55 = r5
            r50 = r6
            r54 = r11
            r51 = r15
            r49.addCentering(r50, r51, r52, r53, r54, r55, r56, r57)
            r1 = r49
            r6 = r54
            if (r35 == 0) goto L_0x053c
            if (r18 == 0) goto L_0x053c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r13.mTarget
            if (r0 == 0) goto L_0x0389
            int r3 = r13.getMargin()
        L_0x0386:
            r11 = r39
            goto L_0x038b
        L_0x0389:
            r3 = 0
            goto L_0x0386
        L_0x038b:
            if (r6 == r11) goto L_0x053c
            r8 = 5
            r1.addGreaterThan(r11, r5, r3, r8)
            return
        L_0x0392:
            r1 = r33
            r3 = r6
            r6 = r11
            r8 = 5
            r11 = r39
            r28 = r8
            r19 = r16
            r17 = 1
            r25 = 1
            r26 = 0
            r27 = 4
        L_0x03a5:
            if (r25 == 0) goto L_0x03b0
            if (r15 != r6) goto L_0x03b0
            if (r4 == r9) goto L_0x03b0
            r25 = 0
            r29 = 0
            goto L_0x03b2
        L_0x03b0:
            r29 = 1
        L_0x03b2:
            if (r17 == 0) goto L_0x03f4
            if (r24 != 0) goto L_0x03cb
            if (r50 != 0) goto L_0x03cb
            if (r52 != 0) goto L_0x03cb
            if (r15 != r2) goto L_0x03cb
            if (r6 != r11) goto L_0x03cb
            r19 = r9
            r9 = r22
            r28 = r9
            r17 = 0
            r29 = 0
        L_0x03c8:
            r30 = r4
            goto L_0x03d4
        L_0x03cb:
            r17 = r19
            r19 = r9
            r9 = r17
            r17 = r35
            goto L_0x03c8
        L_0x03d4:
            int r4 = r12.getMargin()
            r31 = r8
            int r8 = r13.getMargin()
            r11 = r15
            r15 = r2
            r2 = r3
            r3 = r11
            r14 = r7
            r11 = r19
            r13 = r22
            r12 = r30
            r7 = r5
            r5 = r48
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            r5 = r6
            r6 = r2
            r2 = r5
            r5 = r7
            goto L_0x0400
        L_0x03f4:
            r11 = r15
            r15 = r2
            r2 = r6
            r6 = r3
            r3 = r11
            r12 = r4
            r14 = r7
            r11 = r9
            r13 = r22
            r17 = r35
        L_0x0400:
            int r4 = r0.mVisibility
            if (r4 != r13) goto L_0x040c
            boolean r4 = r43.hasDependents()
            if (r4 != 0) goto L_0x040c
            goto L_0x053c
        L_0x040c:
            if (r25 == 0) goto L_0x0432
            if (r17 == 0) goto L_0x041f
            if (r3 == r2) goto L_0x041f
            if (r24 != 0) goto L_0x041f
            boolean r4 = r12 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r4 != 0) goto L_0x041c
            boolean r4 = r14 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r4 == 0) goto L_0x041f
        L_0x041c:
            r4 = r16
            goto L_0x0421
        L_0x041f:
            r4 = r28
        L_0x0421:
            int r7 = r42.getMargin()
            r1.addGreaterThan(r6, r3, r7, r4)
            int r7 = r43.getMargin()
            int r7 = -r7
            r1.addLowerThan(r5, r2, r7, r4)
            r28 = r4
        L_0x0432:
            if (r17 == 0) goto L_0x0444
            if (r53 == 0) goto L_0x0444
            boolean r4 = r12 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r4 != 0) goto L_0x0444
            boolean r4 = r14 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r4 != 0) goto L_0x0444
            r4 = r16
            r7 = r4
            r20 = 1
            goto L_0x044a
        L_0x0444:
            r4 = r27
            r7 = r28
            r20 = r29
        L_0x044a:
            if (r20 == 0) goto L_0x0497
            if (r26 == 0) goto L_0x0477
            if (r52 == 0) goto L_0x0452
            if (r36 == 0) goto L_0x0477
        L_0x0452:
            if (r12 == r11) goto L_0x0459
            if (r14 != r11) goto L_0x0457
            goto L_0x0459
        L_0x0457:
            r16 = r4
        L_0x0459:
            boolean r8 = r12 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r8 != 0) goto L_0x0461
            boolean r8 = r14 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r8 == 0) goto L_0x0463
        L_0x0461:
            r16 = 5
        L_0x0463:
            boolean r8 = r12 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r8 != 0) goto L_0x046b
            boolean r8 = r14 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r8 == 0) goto L_0x046d
        L_0x046b:
            r16 = 5
        L_0x046d:
            if (r52 == 0) goto L_0x0471
            r8 = 5
            goto L_0x0473
        L_0x0471:
            r8 = r16
        L_0x0473:
            int r4 = java.lang.Math.max(r8, r4)
        L_0x0477:
            if (r17 == 0) goto L_0x0487
            int r4 = java.lang.Math.min(r7, r4)
            if (r49 == 0) goto L_0x0487
            if (r52 != 0) goto L_0x0487
            if (r12 == r11) goto L_0x0485
            if (r14 != r11) goto L_0x0487
        L_0x0485:
            r11 = 4
            goto L_0x0488
        L_0x0487:
            r11 = r4
        L_0x0488:
            int r4 = r42.getMargin()
            r1.addEquality(r6, r3, r4, r11)
            int r4 = r43.getMargin()
            int r4 = -r4
            r1.addEquality(r5, r2, r4, r11)
        L_0x0497:
            if (r17 == 0) goto L_0x04a7
            if (r15 != r3) goto L_0x04a0
            int r4 = r42.getMargin()
            goto L_0x04a1
        L_0x04a0:
            r4 = 0
        L_0x04a1:
            if (r3 == r15) goto L_0x04a7
            r8 = 5
            r1.addGreaterThan(r6, r15, r4, r8)
        L_0x04a7:
            if (r17 == 0) goto L_0x04bf
            if (r24 == 0) goto L_0x04bf
            if (r46 != 0) goto L_0x04bf
            if (r23 != 0) goto L_0x04bf
            if (r24 == 0) goto L_0x04b9
            r8 = 3
            if (r10 != r8) goto L_0x04b9
            r8 = 0
            r1.addGreaterThan(r5, r6, r8, r13)
            goto L_0x04c3
        L_0x04b9:
            r8 = 0
            r4 = 5
            r1.addGreaterThan(r5, r6, r8, r4)
            goto L_0x04c3
        L_0x04bf:
            r8 = 0
            goto L_0x04c3
        L_0x04c1:
            r17 = r35
        L_0x04c3:
            if (r17 == 0) goto L_0x053c
            if (r18 == 0) goto L_0x053c
            r13 = r43
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r13.mTarget
            if (r3 == 0) goto L_0x04d4
            int r3 = r13.getMargin()
        L_0x04d1:
            r11 = r39
            goto L_0x04d6
        L_0x04d4:
            r3 = r8
            goto L_0x04d1
        L_0x04d6:
            if (r2 == r11) goto L_0x053c
            boolean r2 = r0.OPTIMIZE_WRAP
            if (r2 == 0) goto L_0x04f0
            boolean r2 = r5.isFinalValue
            if (r2 == 0) goto L_0x04f0
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.mParent
            if (r0 == 0) goto L_0x04f0
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r0 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r0
            if (r34 == 0) goto L_0x04ec
            r0.addHorizontalWrapMaxVariable(r13)
            return
        L_0x04ec:
            r0.addVerticalWrapMaxVariable(r13)
            return
        L_0x04f0:
            r4 = 5
            r1.addGreaterThan(r11, r5, r3, r4)
            return
        L_0x04f5:
            r15 = r38
            r11 = r39
            r8 = 0
            r13 = 8
            r3 = 2
        L_0x04fd:
            if (r2 >= r3) goto L_0x053c
            if (r35 == 0) goto L_0x053c
            if (r18 == 0) goto L_0x053c
            r1.addGreaterThan(r6, r15, r8, r13)
            if (r34 != 0) goto L_0x0511
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 != 0) goto L_0x050f
            goto L_0x0511
        L_0x050f:
            r3 = r8
            goto L_0x0512
        L_0x0511:
            r3 = 1
        L_0x0512:
            if (r34 != 0) goto L_0x0535
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x0535
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.mOwner
            float r2 = r0.mDimensionRatio
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x0532
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r2 = r0[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x0532
            r20 = 1
            r0 = r0[r20]
            if (r0 != r3) goto L_0x0532
            goto L_0x0537
        L_0x0532:
            r20 = r8
            goto L_0x0537
        L_0x0535:
            r20 = r3
        L_0x0537:
            if (r20 == 0) goto L_0x053c
            r1.addGreaterThan(r11, r5, r8, r13)
        L_0x053c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.applyConstraints(androidx.constraintlayout.solver.LinearSystem, boolean, boolean, boolean, boolean, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.solver.widgets.ConstraintAnchor, androidx.constraintlayout.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r2 = r2[r3 + 1];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isChainHead(int r3) {
        /*
            r2 = this;
            int r3 = r3 * 2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r2.mListAnchors
            r0 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r0.mTarget
            if (r1 == 0) goto L_0x001b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == r0) goto L_0x001b
            r0 = 1
            int r3 = r3 + r0
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            if (r3 == 0) goto L_0x001b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != r2) goto L_0x001b
            return r0
        L_0x001b:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.isChainHead(int):boolean");
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet<ConstraintWidget> hashSet, int i2, boolean z) {
        if (z) {
            if (hashSet.contains(this)) {
                Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem, this);
                hashSet.remove(this);
                addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
            } else {
                return;
            }
        }
        if (i2 == 0) {
            HashSet<ConstraintAnchor> dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator<ConstraintAnchor> it = dependents.iterator();
                while (it.hasNext()) {
                    it.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
            }
            HashSet<ConstraintAnchor> dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator<ConstraintAnchor> it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    it2.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator<ConstraintAnchor> it3 = dependents3.iterator();
            while (it3.hasNext()) {
                it3.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet<ConstraintAnchor> dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator<ConstraintAnchor> it4 = dependents4.iterator();
            while (it4.hasNext()) {
                it4.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet<ConstraintAnchor> dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator<ConstraintAnchor> it5 = dependents5.iterator();
            while (it5.hasNext()) {
                it5.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
    }

    public boolean addFirst() {
        if ((this instanceof VirtualLayout) || (this instanceof Guideline)) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x02d8  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x02e1  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x02ff  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0301  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0304  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0306  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0427  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0471 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x04a2  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x04a4  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x04ab  */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x0570  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0577  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x05a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(androidx.constraintlayout.solver.LinearSystem r51, boolean r52) {
        /*
            r50 = this;
            r0 = r50
            r1 = r51
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mLeft
            androidx.constraintlayout.solver.SolverVariable r2 = r1.createObjectVariable(r2)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mRight
            androidx.constraintlayout.solver.SolverVariable r3 = r1.createObjectVariable(r3)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.mTop
            androidx.constraintlayout.solver.SolverVariable r4 = r1.createObjectVariable(r4)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r5 = r1.createObjectVariable(r5)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r0.mBaseline
            androidx.constraintlayout.solver.SolverVariable r6 = r1.createObjectVariable(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r0.mParent
            r8 = 1
            r9 = 0
            if (r7 == 0) goto L_0x0043
            if (r7 == 0) goto L_0x0034
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r7.mListDimensionBehaviors
            r10 = r10[r9]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r10 != r11) goto L_0x0034
            r10 = r8
            goto L_0x0035
        L_0x0034:
            r10 = r9
        L_0x0035:
            if (r7 == 0) goto L_0x0041
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r7.mListDimensionBehaviors
            r7 = r7[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r11) goto L_0x0041
            r7 = r8
            goto L_0x0045
        L_0x0041:
            r7 = r9
            goto L_0x0045
        L_0x0043:
            r7 = r9
            r10 = r7
        L_0x0045:
            int r11 = r0.mVisibility
            r12 = 8
            if (r11 != r12) goto L_0x005c
            boolean r11 = r0.hasDependencies()
            if (r11 != 0) goto L_0x005c
            boolean[] r11 = r0.mIsInBarrier
            boolean r13 = r11[r9]
            if (r13 != 0) goto L_0x005c
            boolean r11 = r11[r8]
            if (r11 != 0) goto L_0x005c
            return
        L_0x005c:
            boolean r11 = r0.resolvedHorizontal
            r13 = 5
            if (r11 != 0) goto L_0x0065
            boolean r14 = r0.resolvedVertical
            if (r14 == 0) goto L_0x00e2
        L_0x0065:
            if (r11 == 0) goto L_0x0094
            int r11 = r0.mX
            r1.addEquality(r2, r11)
            int r11 = r0.mX
            int r14 = r0.mWidth
            int r11 = r11 + r14
            r1.addEquality(r3, r11)
            if (r10 == 0) goto L_0x0094
            androidx.constraintlayout.solver.widgets.ConstraintWidget r11 = r0.mParent
            if (r11 == 0) goto L_0x0094
            boolean r14 = r0.OPTIMIZE_WRAP_ON_RESOLVED
            if (r14 == 0) goto L_0x008b
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r11 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r11
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r14 = r0.mLeft
            r11.addVerticalWrapMinVariable(r14)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r14 = r0.mRight
            r11.addHorizontalWrapMaxVariable(r14)
            goto L_0x0094
        L_0x008b:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r11.mRight
            androidx.constraintlayout.solver.SolverVariable r11 = r1.createObjectVariable(r11)
            r1.addGreaterThan(r11, r3, r9, r13)
        L_0x0094:
            boolean r11 = r0.resolvedVertical
            if (r11 == 0) goto L_0x00d5
            int r11 = r0.mY
            r1.addEquality(r4, r11)
            int r11 = r0.mY
            int r14 = r0.mHeight
            int r11 = r11 + r14
            r1.addEquality(r5, r11)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBaseline
            boolean r11 = r11.hasDependents()
            if (r11 == 0) goto L_0x00b5
            int r11 = r0.mY
            int r14 = r0.mBaselineDistance
            int r11 = r11 + r14
            r1.addEquality(r6, r11)
        L_0x00b5:
            if (r7 == 0) goto L_0x00d5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r11 = r0.mParent
            if (r11 == 0) goto L_0x00d5
            boolean r14 = r0.OPTIMIZE_WRAP_ON_RESOLVED
            if (r14 == 0) goto L_0x00cc
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r11 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r11
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r14 = r0.mTop
            r11.addVerticalWrapMinVariable(r14)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r14 = r0.mBottom
            r11.addVerticalWrapMaxVariable(r14)
            goto L_0x00d5
        L_0x00cc:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r11.mBottom
            androidx.constraintlayout.solver.SolverVariable r11 = r1.createObjectVariable(r11)
            r1.addGreaterThan(r11, r5, r9, r13)
        L_0x00d5:
            boolean r11 = r0.resolvedHorizontal
            if (r11 == 0) goto L_0x00e2
            boolean r11 = r0.resolvedVertical
            if (r11 == 0) goto L_0x00e2
            r0.resolvedHorizontal = r9
            r0.resolvedVertical = r9
            return
        L_0x00e2:
            androidx.constraintlayout.solver.Metrics r11 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            r17 = 1
            if (r11 == 0) goto L_0x00ee
            long r13 = r11.widgets
            long r13 = r13 + r17
            r11.widgets = r13
        L_0x00ee:
            if (r52 == 0) goto L_0x017e
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r13 = r0.horizontalRun
            if (r13 == 0) goto L_0x017e
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r14 = r0.verticalRun
            if (r14 == 0) goto L_0x017e
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r15 = r13.start
            r19 = r8
            boolean r8 = r15.resolved
            if (r8 == 0) goto L_0x0180
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r8 = r13.end
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x0180
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r8 = r14.start
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x0180
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r8 = r14.end
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x0180
            if (r11 == 0) goto L_0x011a
            long r13 = r11.graphSolved
            long r13 = r13 + r17
            r11.graphSolved = r13
        L_0x011a:
            int r8 = r15.value
            r1.addEquality(r2, r8)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r0.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            r1.addEquality(r3, r2)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.start
            int r2 = r2.value
            r1.addEquality(r4, r2)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            r1.addEquality(r5, r2)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.baseline
            int r2 = r2.value
            r1.addEquality(r6, r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x0179
            if (r10 == 0) goto L_0x0160
            boolean[] r2 = r0.isTerminalWidget
            boolean r2 = r2[r9]
            if (r2 == 0) goto L_0x0160
            boolean r2 = r0.isInHorizontalChain()
            if (r2 != 0) goto L_0x0160
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mRight
            androidx.constraintlayout.solver.SolverVariable r2 = r1.createObjectVariable(r2)
            r1.addGreaterThan(r2, r3, r9, r12)
        L_0x0160:
            if (r7 == 0) goto L_0x0179
            boolean[] r2 = r0.isTerminalWidget
            boolean r2 = r2[r19]
            if (r2 == 0) goto L_0x0179
            boolean r2 = r0.isInVerticalChain()
            if (r2 != 0) goto L_0x0179
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mBottom
            androidx.constraintlayout.solver.SolverVariable r2 = r1.createObjectVariable(r2)
            r1.addGreaterThan(r2, r5, r9, r12)
        L_0x0179:
            r0.resolvedHorizontal = r9
            r0.resolvedVertical = r9
            return
        L_0x017e:
            r19 = r8
        L_0x0180:
            if (r11 == 0) goto L_0x0188
            long r13 = r11.linearSolved
            long r13 = r13 + r17
            r11.linearSolved = r13
        L_0x0188:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r0.mParent
            if (r8 == 0) goto L_0x01fd
            boolean r8 = r0.isChainHead(r9)
            if (r8 == 0) goto L_0x019d
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r8 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r8
            r8.addChain(r0, r9)
            r8 = r19
            r11 = r8
            goto L_0x01a3
        L_0x019d:
            boolean r8 = r0.isInHorizontalChain()
            r11 = r19
        L_0x01a3:
            boolean r13 = r0.isChainHead(r11)
            if (r13 == 0) goto L_0x01b2
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r13 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r13
            r13.addChain(r0, r11)
            r11 = 1
            goto L_0x01b6
        L_0x01b2:
            boolean r11 = r0.isInVerticalChain()
        L_0x01b6:
            if (r8 != 0) goto L_0x01d6
            if (r10 == 0) goto L_0x01d6
            int r13 = r0.mVisibility
            if (r13 == r12) goto L_0x01d6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r0.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x01d6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r0.mRight
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x01d6
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r13.mRight
            androidx.constraintlayout.solver.SolverVariable r13 = r1.createObjectVariable(r13)
            r14 = 1
            r1.addGreaterThan(r13, r3, r9, r14)
        L_0x01d6:
            if (r11 != 0) goto L_0x01fa
            if (r7 == 0) goto L_0x01fa
            int r13 = r0.mVisibility
            if (r13 == r12) goto L_0x01fa
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r0.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x01fa
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r0.mBottom
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x01fa
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r0.mBaseline
            if (r13 != 0) goto L_0x01fa
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r13.mBottom
            androidx.constraintlayout.solver.SolverVariable r13 = r1.createObjectVariable(r13)
            r14 = 1
            r1.addGreaterThan(r13, r5, r9, r14)
        L_0x01fa:
            r20 = r8
            goto L_0x0200
        L_0x01fd:
            r11 = r9
            r20 = r11
        L_0x0200:
            int r8 = r0.mWidth
            int r13 = r0.mMinWidth
            if (r8 >= r13) goto L_0x0207
            goto L_0x0208
        L_0x0207:
            r13 = r8
        L_0x0208:
            int r14 = r0.mHeight
            int r15 = r0.mMinHeight
            if (r14 >= r15) goto L_0x0211
        L_0x020e:
            r17 = r9
            goto L_0x0213
        L_0x0211:
            r15 = r14
            goto L_0x020e
        L_0x0213:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r9 = r0.mListDimensionBehaviors
            r12 = r9[r17]
            r21 = r4
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r22 = r5
            if (r12 == r4) goto L_0x0223
            r5 = 1
        L_0x0220:
            r19 = 1
            goto L_0x0226
        L_0x0223:
            r5 = r17
            goto L_0x0220
        L_0x0226:
            r9 = r9[r19]
            r23 = r6
            if (r9 == r4) goto L_0x0230
            r6 = 1
        L_0x022d:
            r24 = r11
            goto L_0x0233
        L_0x0230:
            r6 = r17
            goto L_0x022d
        L_0x0233:
            int r11 = r0.mDimensionRatioSide
            r0.mResolvedDimensionRatioSide = r11
            r25 = r13
            float r13 = r0.mDimensionRatio
            r0.mResolvedDimensionRatio = r13
            r26 = r13
            int r13 = r0.mMatchConstraintDefaultWidth
            r27 = r13
            int r13 = r0.mMatchConstraintDefaultHeight
            r28 = 0
            int r28 = (r26 > r28 ? 1 : (r26 == r28 ? 0 : -1))
            r29 = r13
            if (r28 <= 0) goto L_0x02c0
            int r13 = r0.mVisibility
            r30 = r15
            r15 = 8
            if (r13 == r15) goto L_0x02bd
            r13 = 3
            if (r12 != r4) goto L_0x025c
            if (r27 != 0) goto L_0x025c
            r15 = r13
            goto L_0x025e
        L_0x025c:
            r15 = r27
        L_0x025e:
            if (r9 != r4) goto L_0x0265
            if (r29 != 0) goto L_0x0265
            r31 = r13
            goto L_0x0267
        L_0x0265:
            r31 = r29
        L_0x0267:
            if (r12 != r4) goto L_0x0277
            if (r9 != r4) goto L_0x0277
            if (r15 != r13) goto L_0x0277
            r32 = r3
            r3 = r31
            if (r3 != r13) goto L_0x027b
            r0.setupDimensionRatio(r10, r7, r5, r6)
            goto L_0x0293
        L_0x0277:
            r32 = r3
            r3 = r31
        L_0x027b:
            r5 = 4
            if (r12 != r4) goto L_0x029a
            if (r15 != r13) goto L_0x029a
            r6 = r17
            r0.mResolvedDimensionRatioSide = r6
            float r6 = (float) r14
            float r13 = r26 * r6
            int r13 = (int) r13
            r25 = r13
            if (r9 == r4) goto L_0x0293
            r13 = r5
            r5 = r23
            r23 = r3
        L_0x0291:
            r3 = 0
            goto L_0x02ca
        L_0x0293:
            r13 = r15
            r5 = r23
            r23 = r3
            r3 = 1
            goto L_0x02ca
        L_0x029a:
            if (r9 != r4) goto L_0x0293
            if (r3 != r13) goto L_0x0293
            r14 = 1
            r0.mResolvedDimensionRatioSide = r14
            r6 = -1
            if (r11 != r6) goto L_0x02aa
            r6 = 1065353216(0x3f800000, float:1.0)
            float r6 = r6 / r26
            r0.mResolvedDimensionRatio = r6
        L_0x02aa:
            float r6 = r0.mResolvedDimensionRatio
            float r8 = (float) r8
            float r6 = r6 * r8
            int r6 = (int) r6
            if (r12 == r4) goto L_0x02ba
            r3 = r23
            r23 = r5
            r5 = r3
            r30 = r6
            r13 = r15
            goto L_0x0291
        L_0x02ba:
            r30 = r6
            goto L_0x0293
        L_0x02bd:
            r32 = r3
            goto L_0x02c3
        L_0x02c0:
            r30 = r15
            goto L_0x02bd
        L_0x02c3:
            r5 = r23
            r13 = r27
            r23 = r29
            goto L_0x0291
        L_0x02ca:
            int[] r6 = r0.mResolvedMatchConstraintDefault
            r17 = 0
            r6[r17] = r13
            r19 = 1
            r6[r19] = r23
            r0.mResolvedHasRatio = r3
            if (r3 == 0) goto L_0x02e1
            int r6 = r0.mResolvedDimensionRatioSide
            r8 = -1
            if (r6 == 0) goto L_0x02df
            if (r6 != r8) goto L_0x02e2
        L_0x02df:
            r11 = 1
            goto L_0x02e3
        L_0x02e1:
            r8 = -1
        L_0x02e2:
            r11 = 0
        L_0x02e3:
            if (r3 == 0) goto L_0x02ef
            int r6 = r0.mResolvedDimensionRatioSide
            r14 = 1
            if (r6 == r14) goto L_0x02ec
            if (r6 != r8) goto L_0x02ef
        L_0x02ec:
            r28 = 1
            goto L_0x02f1
        L_0x02ef:
            r28 = 0
        L_0x02f1:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r6 = r0.mListDimensionBehaviors
            r17 = 0
            r6 = r6[r17]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r6 != r8) goto L_0x0301
            boolean r6 = r0 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r6 == 0) goto L_0x0301
            r9 = 1
            goto L_0x0302
        L_0x0301:
            r9 = 0
        L_0x0302:
            if (r9 == 0) goto L_0x0306
            r6 = 0
            goto L_0x0308
        L_0x0306:
            r6 = r25
        L_0x0308:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mCenter
            boolean r12 = r12.isConnected()
            r19 = 1
            r27 = r12 ^ 1
            boolean[] r12 = r0.mIsInBarrier
            r14 = r21
            r17 = 0
            boolean r21 = r12[r17]
            boolean r29 = r12[r19]
            int r12 = r0.mHorizontalResolution
            r15 = 2
            r31 = 0
            if (r12 == r15) goto L_0x0427
            boolean r12 = r0.resolvedHorizontal
            if (r12 != 0) goto L_0x0427
            if (r52 == 0) goto L_0x039a
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r12 = r0.horizontalRun
            if (r12 == 0) goto L_0x039a
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r15 = r12.start
            r26 = r3
            boolean r3 = r15.resolved
            if (r3 == 0) goto L_0x033b
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r12.end
            boolean r3 = r3.resolved
            if (r3 != 0) goto L_0x0341
        L_0x033b:
            r12 = r32
            r15 = 8
            goto L_0x039d
        L_0x0341:
            if (r52 == 0) goto L_0x038e
            int r3 = r15.value
            r1.addEquality(r2, r3)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r3 = r0.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.value
            r12 = r32
            r1.addEquality(r12, r3)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.mParent
            if (r3 == 0) goto L_0x0373
            if (r10 == 0) goto L_0x0373
            boolean[] r3 = r0.isTerminalWidget
            r6 = 0
            boolean r3 = r3[r6]
            if (r3 == 0) goto L_0x0373
            boolean r3 = r0.isInHorizontalChain()
            if (r3 != 0) goto L_0x0373
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mRight
            androidx.constraintlayout.solver.SolverVariable r3 = r1.createObjectVariable(r3)
            r15 = 8
            r1.addGreaterThan(r3, r12, r6, r15)
        L_0x0373:
            r43 = r2
            r48 = r4
            r47 = r5
            r34 = r7
            r49 = r8
            r3 = r10
            r32 = r12
        L_0x0380:
            r45 = r14
            r19 = r20
            r46 = r22
            r20 = r24
            r33 = r26
        L_0x038a:
            r22 = r13
            goto L_0x043e
        L_0x038e:
            r43 = r2
            r48 = r4
            r47 = r5
            r34 = r7
            r49 = r8
            r3 = r10
            goto L_0x0380
        L_0x039a:
            r26 = r3
            goto L_0x033b
        L_0x039d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.mParent
            if (r3 == 0) goto L_0x03a8
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mRight
            androidx.constraintlayout.solver.SolverVariable r3 = r1.createObjectVariable(r3)
            goto L_0x03aa
        L_0x03a8:
            r3 = r31
        L_0x03aa:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r15 = r0.mParent
            if (r15 == 0) goto L_0x03b5
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r15.mLeft
            androidx.constraintlayout.solver.SolverVariable r15 = r1.createObjectVariable(r15)
            goto L_0x03b7
        L_0x03b5:
            r15 = r31
        L_0x03b7:
            boolean[] r1 = r0.isTerminalWidget
            r17 = 0
            boolean r1 = r1[r17]
            r32 = r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r0.mListDimensionBehaviors
            r33 = r8
            r8 = r1[r17]
            r34 = r7
            r7 = r3
            r3 = r10
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mLeft
            r35 = r17
            r17 = r11
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
            r36 = r12
            int r12 = r0.mX
            r37 = r14
            int r14 = r0.mMinWidth
            r38 = r1
            int[] r1 = r0.mMaxDimension
            r1 = r1[r35]
            r39 = r1
            float r1 = r0.mHorizontalBiasPercent
            r40 = r1
            r19 = 1
            r1 = r38[r19]
            if (r1 != r4) goto L_0x03ee
            r18 = r19
            goto L_0x03f0
        L_0x03ee:
            r18 = r35
        L_0x03f0:
            int r1 = r0.mMatchConstraintMinWidth
            r41 = r1
            int r1 = r0.mMatchConstraintMaxWidth
            r42 = r1
            float r1 = r0.mMatchConstraintPercentWidth
            r43 = r2
            r2 = 1
            r48 = r4
            r47 = r5
            r19 = r20
            r46 = r22
            r20 = r24
            r5 = r32
            r49 = r33
            r4 = r34
            r32 = r36
            r45 = r37
            r16 = r40
            r24 = r41
            r25 = r42
            r22 = r13
            r33 = r26
            r26 = r1
            r13 = r6
            r6 = r15
            r15 = r39
            r1 = r51
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            goto L_0x043e
        L_0x0427:
            r43 = r2
            r33 = r3
            r48 = r4
            r47 = r5
            r34 = r7
            r49 = r8
            r3 = r10
            r45 = r14
            r19 = r20
            r46 = r22
            r20 = r24
            goto L_0x038a
        L_0x043e:
            if (r52 == 0) goto L_0x0492
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            if (r2 == 0) goto L_0x0492
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r2.start
            boolean r5 = r4.resolved
            if (r5 == 0) goto L_0x0492
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.end
            boolean r2 = r2.resolved
            if (r2 == 0) goto L_0x0492
            int r2 = r4.value
            r4 = r45
            r1.addEquality(r4, r2)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            r5 = r46
            r1.addEquality(r5, r2)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.baseline
            int r2 = r2.value
            r6 = r47
            r1.addEquality(r6, r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x048d
            if (r20 != 0) goto L_0x048d
            if (r34 == 0) goto L_0x048d
            boolean[] r7 = r0.isTerminalWidget
            r14 = 1
            boolean r7 = r7[r14]
            if (r7 == 0) goto L_0x0489
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mBottom
            androidx.constraintlayout.solver.SolverVariable r2 = r1.createObjectVariable(r2)
            r7 = 0
            r15 = 8
            r1.addGreaterThan(r2, r5, r7, r15)
            goto L_0x0490
        L_0x0489:
            r7 = 0
        L_0x048a:
            r15 = 8
            goto L_0x0490
        L_0x048d:
            r7 = 0
            r14 = 1
            goto L_0x048a
        L_0x0490:
            r8 = r7
            goto L_0x049d
        L_0x0492:
            r4 = r45
            r5 = r46
            r6 = r47
            r7 = 0
            r14 = 1
            r15 = 8
            r8 = r14
        L_0x049d:
            int r2 = r0.mVerticalResolution
            r9 = 2
            if (r2 != r9) goto L_0x04a4
            r9 = r7
            goto L_0x04a5
        L_0x04a4:
            r9 = r8
        L_0x04a5:
            if (r9 == 0) goto L_0x0570
            boolean r2 = r0.resolvedVertical
            if (r2 != 0) goto L_0x0570
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r0.mListDimensionBehaviors
            r2 = r2[r14]
            r8 = r49
            if (r2 != r8) goto L_0x04b9
            boolean r2 = r0 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r2 == 0) goto L_0x04b9
            r9 = r14
            goto L_0x04ba
        L_0x04b9:
            r9 = r7
        L_0x04ba:
            if (r9 == 0) goto L_0x04be
            r13 = r7
            goto L_0x04c0
        L_0x04be:
            r13 = r30
        L_0x04c0:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x04cb
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mBottom
            androidx.constraintlayout.solver.SolverVariable r2 = r1.createObjectVariable(r2)
            goto L_0x04cd
        L_0x04cb:
            r2 = r31
        L_0x04cd:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r0.mParent
            if (r8 == 0) goto L_0x04d7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mTop
            androidx.constraintlayout.solver.SolverVariable r31 = r1.createObjectVariable(r8)
        L_0x04d7:
            int r8 = r0.mBaselineDistance
            if (r8 > 0) goto L_0x04df
            int r8 = r0.mVisibility
            if (r8 != r15) goto L_0x0515
        L_0x04df:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r0.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 == 0) goto L_0x0506
            int r8 = r0.getBaselineDistance()
            r1.addEquality(r6, r4, r8, r15)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r0.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            androidx.constraintlayout.solver.SolverVariable r8 = r1.createObjectVariable(r8)
            r1.addEquality(r6, r8, r7, r15)
            if (r34 == 0) goto L_0x0503
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r6 = r1.createObjectVariable(r6)
            r8 = 5
            r1.addGreaterThan(r2, r6, r7, r8)
        L_0x0503:
            r27 = r7
            goto L_0x0515
        L_0x0506:
            int r8 = r0.mVisibility
            if (r8 != r15) goto L_0x050e
            r1.addEquality(r6, r4, r7, r15)
            goto L_0x0515
        L_0x050e:
            int r8 = r0.getBaselineDistance()
            r1.addEquality(r6, r4, r8, r15)
        L_0x0515:
            boolean[] r6 = r0.isTerminalWidget
            boolean r6 = r6[r14]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r0.mListDimensionBehaviors
            r10 = r8
            r8 = r10[r14]
            r11 = r10
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mTop
            r12 = r11
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBottom
            r15 = r12
            int r12 = r0.mY
            r44 = r14
            int r14 = r0.mMinHeight
            r17 = r7
            int[] r7 = r0.mMaxDimension
            r7 = r7[r44]
            float r1 = r0.mVerticalBiasPercent
            r15 = r15[r17]
            r16 = r1
            r1 = r48
            if (r15 != r1) goto L_0x053e
            r18 = r44
            goto L_0x0540
        L_0x053e:
            r18 = r17
        L_0x0540:
            int r1 = r0.mMatchConstraintMinHeight
            int r15 = r0.mMatchConstraintMaxHeight
            r24 = r1
            float r1 = r0.mMatchConstraintPercentHeight
            r25 = r15
            r15 = r7
            r7 = r2
            r2 = 0
            r17 = r20
            r20 = r19
            r19 = r17
            r17 = r23
            r23 = r22
            r22 = r17
            r26 = r1
            r45 = r4
            r46 = r5
            r5 = r6
            r17 = r28
            r21 = r29
            r6 = r31
            r1 = r51
            r4 = r3
            r3 = r34
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r7 = r0
            goto L_0x0575
        L_0x0570:
            r7 = r0
            r45 = r4
            r46 = r5
        L_0x0575:
            if (r33 == 0) goto L_0x058d
            int r0 = r7.mResolvedDimensionRatioSide
            r6 = 8
            r14 = 1
            if (r0 != r14) goto L_0x0590
            float r5 = r7.mResolvedDimensionRatio
            r0 = r51
            r3 = r32
            r4 = r43
            r2 = r45
            r1 = r46
            r0.addRatio(r1, r2, r3, r4, r5, r6)
        L_0x058d:
            r1 = r51
            goto L_0x05a0
        L_0x0590:
            float r5 = r7.mResolvedDimensionRatio
            r0 = r51
            r1 = r32
            r2 = r43
            r4 = r45
            r3 = r46
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            r1 = r0
        L_0x05a0:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x05c6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.getTarget()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.getOwner()
            float r2 = r7.mCircleConstraintAngle
            r3 = 1119092736(0x42b40000, float:90.0)
            float r2 = r2 + r3
            double r2 = (double) r2
            double r2 = java.lang.Math.toRadians(r2)
            float r2 = (float) r2
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r7.mCenter
            int r3 = r3.getMargin()
            r1.addCenterPoint(r7, r0, r2, r3)
        L_0x05c6:
            r6 = 0
            r7.resolvedHorizontal = r6
            r7.resolvedVertical = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.solver.LinearSystem, boolean):void");
    }

    public boolean allowedInBarrier() {
        if (this.mVisibility != 8) {
            return true;
        }
        return false;
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i2);
        }
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        immediateConnect(type, constraintWidget, type, i2, 0);
        this.mCircleConstraintAngle = f;
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public void ensureWidgetRuns() {
        if (this.horizontalRun == null) {
            this.horizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.verticalRun == null) {
            this.verticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public float getBiasPercent(int i2) {
        if (i2 == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i2 == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public DimensionBehaviour getDimensionBehaviour(int i2) {
        if (i2 == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i2 == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public int getHorizontalMargin() {
        int i2;
        ConstraintAnchor constraintAnchor = this.mLeft;
        if (constraintAnchor != null) {
            i2 = constraintAnchor.mMargin;
        } else {
            i2 = 0;
        }
        ConstraintAnchor constraintAnchor2 = this.mRight;
        if (constraintAnchor2 != null) {
            return i2 + constraintAnchor2.mMargin;
        }
        return i2;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public int getLength(int i2) {
        if (i2 == 0) {
            return getWidth();
        }
        if (i2 == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r1 = r1.mBottom;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.constraintlayout.solver.widgets.ConstraintWidget getNextChainMember(int r2) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x000f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mRight
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r2.mTarget
            if (r0 != r1) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r2.mOwner
            return r1
        L_0x000f:
            r0 = 1
            if (r2 != r0) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mBottom
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r2.mTarget
            if (r0 != r1) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r2.mOwner
            return r1
        L_0x001f:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.getNextChainMember(int):androidx.constraintlayout.solver.widgets.ConstraintWidget");
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r1 = r1.mTop;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.constraintlayout.solver.widgets.ConstraintWidget getPreviousChainMember(int r2) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x000f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r2.mTarget
            if (r0 != r1) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r2.mOwner
            return r1
        L_0x000f:
            r0 = 1
            if (r2 != r0) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r2.mTarget
            if (r0 != r1) goto L_0x001f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r2.mOwner
            return r1
        L_0x001f:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.getPreviousChainMember(int):androidx.constraintlayout.solver.widgets.ConstraintWidget");
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public WidgetRun getRun(int i2) {
        if (i2 == 0) {
            return this.horizontalRun;
        }
        if (i2 == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public int getVerticalMargin() {
        int i2;
        if (this.mLeft != null) {
            i2 = this.mTop.mMargin;
        } else {
            i2 = 0;
        }
        if (this.mRight != null) {
            return i2 + this.mBottom.mMargin;
        }
        return i2;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.mX;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.mY;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.mY;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public boolean hasDanglingDimension(int i2) {
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        if (i2 == 0) {
            if (this.mLeft.mTarget != null) {
                i11 = 1;
            } else {
                i11 = 0;
            }
            if (this.mRight.mTarget != null) {
                i12 = 1;
            } else {
                i12 = 0;
            }
            if (i11 + i12 < 2) {
                return true;
            }
            return false;
        }
        if (this.mTop.mTarget != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        if (this.mBottom.mTarget != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i13 = i7 + i8;
        if (this.mBaseline.mTarget != null) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        if (i13 + i10 < 2) {
            return true;
        }
        return false;
    }

    public boolean hasDependencies() {
        int size = this.mAnchors.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mAnchors.get(i2).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, int i7) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i2, i7, true);
    }

    public boolean isInHorizontalChain() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null && constraintAnchor2.mTarget == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        if (constraintAnchor4 == null || constraintAnchor4.mTarget != constraintAnchor3) {
            return false;
        }
        return true;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public boolean isInVerticalChain() {
        ConstraintAnchor constraintAnchor = this.mTop;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null && constraintAnchor2.mTarget == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.mBottom;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        if (constraintAnchor4 == null || constraintAnchor4.mTarget != constraintAnchor3) {
            return false;
        }
        return true;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtuaLayout;
    }

    public boolean isMeasureRequested() {
        if (!this.mMeasureRequested || this.mVisibility == 8) {
            return false;
        }
        return true;
    }

    public boolean isResolvedHorizontally() {
        if (this.resolvedHorizontal) {
            return true;
        }
        if (!this.mLeft.hasFinalValue() || !this.mRight.hasFinalValue()) {
            return false;
        }
        return true;
    }

    public boolean isResolvedVertically() {
        if (this.resolvedVertical) {
            return true;
        }
        if (!this.mTop.hasFinalValue() || !this.mBottom.hasFinalValue()) {
            return false;
        }
        return true;
    }

    public boolean oppositeDimensionsTied() {
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && dimensionBehaviourArr[1] == dimensionBehaviour2) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtuaLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mAnchors.get(i2).reset();
            }
        }
    }

    public void resetFinalResolution() {
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        int size = this.mAnchors.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mAnchors.get(i2).resetFinalResolution();
        }
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    public void setBaselineDistance(int i2) {
        boolean z;
        this.mBaselineDistance = i2;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        this.hasBaseline = z;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x0090
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x0090
        L_0x000b:
            int r1 = r9.length()
            r2 = 44
            int r2 = r9.indexOf(r2)
            r3 = 0
            r4 = 1
            r5 = -1
            if (r2 <= 0) goto L_0x0039
            int r6 = r1 + -1
            if (r2 >= r6) goto L_0x0039
            java.lang.String r6 = r9.substring(r3, r2)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002b
            goto L_0x0036
        L_0x002b:
            java.lang.String r3 = "H"
            boolean r3 = r6.equalsIgnoreCase(r3)
            if (r3 == 0) goto L_0x0035
            r3 = r4
            goto L_0x0036
        L_0x0035:
            r3 = r5
        L_0x0036:
            int r2 = r2 + r4
            r5 = r3
            r3 = r2
        L_0x0039:
            r2 = 58
            int r2 = r9.indexOf(r2)
            if (r2 < 0) goto L_0x0077
            int r1 = r1 - r4
            if (r2 >= r1) goto L_0x0077
            java.lang.String r1 = r9.substring(r3, r2)
            int r2 = r2 + r4
            java.lang.String r9 = r9.substring(r2)
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x0086
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0086
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            int r2 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0086
            int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0086
            if (r5 != r4) goto L_0x0071
            float r9 = r9 / r1
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0071:
            float r1 = r1 / r9
            float r9 = java.lang.Math.abs(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0077:
            java.lang.String r9 = r9.substring(r3)
            int r1 = r9.length()
            if (r1 <= 0) goto L_0x0086
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0086:
            r9 = r0
        L_0x0087:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008f
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r5
        L_0x008f:
            return
        L_0x0090:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setFinalBaseline(int i2) {
        if (this.hasBaseline) {
            int i7 = i2 - this.mBaselineDistance;
            int i8 = this.mHeight + i7;
            this.mY = i7;
            this.mTop.setFinalValue(i7);
            this.mBottom.setFinalValue(i8);
            this.mBaseline.setFinalValue(i2);
            this.resolvedVertical = true;
        }
    }

    public void setFinalHorizontal(int i2, int i7) {
        this.mLeft.setFinalValue(i2);
        this.mRight.setFinalValue(i7);
        this.mX = i2;
        this.mWidth = i7 - i2;
        this.resolvedHorizontal = true;
    }

    public void setFinalLeft(int i2) {
        this.mLeft.setFinalValue(i2);
        this.mX = i2;
    }

    public void setFinalTop(int i2) {
        this.mTop.setFinalValue(i2);
        this.mY = i2;
    }

    public void setFinalVertical(int i2, int i7) {
        this.mTop.setFinalValue(i2);
        this.mBottom.setFinalValue(i7);
        this.mY = i2;
        this.mHeight = i7 - i2;
        if (this.hasBaseline) {
            this.mBaseline.setFinalValue(i2 + this.mBaselineDistance);
        }
        this.resolvedVertical = true;
    }

    public void setFrame(int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        int i13 = i8 - i2;
        int i14 = i10 - i7;
        this.mX = i2;
        this.mY = i7;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (dimensionBehaviour == dimensionBehaviour2 && i13 < (i12 = this.mWidth)) {
            i13 = i12;
        }
        if (dimensionBehaviourArr[1] == dimensionBehaviour2 && i14 < (i11 = this.mHeight)) {
            i14 = i11;
        }
        this.mWidth = i13;
        this.mHeight = i14;
        int i15 = this.mMinHeight;
        if (i14 < i15) {
            this.mHeight = i15;
        }
        int i16 = this.mMinWidth;
        if (i13 < i16) {
            this.mWidth = i16;
        }
    }

    public void setHasBaseline(boolean z) {
        this.hasBaseline = z;
    }

    public void setHeight(int i2) {
        this.mHeight = i2;
        int i7 = this.mMinHeight;
        if (i2 < i7) {
            this.mHeight = i7;
        }
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setHorizontalChainStyle(int i2) {
        this.mHorizontalChainStyle = i2;
    }

    public void setHorizontalDimension(int i2, int i7) {
        this.mX = i2;
        int i8 = i7 - i2;
        this.mWidth = i8;
        int i10 = this.mMinWidth;
        if (i8 < i10) {
            this.mWidth = i10;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setHorizontalMatchStyle(int i2, int i7, int i8, float f) {
        this.mMatchConstraintDefaultWidth = i2;
        this.mMatchConstraintMinWidth = i7;
        if (i8 == Integer.MAX_VALUE) {
            i8 = 0;
        }
        this.mMatchConstraintMaxWidth = i8;
        this.mMatchConstraintPercentWidth = f;
        if (f > 0.0f && f < 1.0f && i2 == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setInBarrier(int i2, boolean z) {
        this.mIsInBarrier[i2] = z;
    }

    public void setInPlaceholder(boolean z) {
        this.inPlaceholder = z;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtuaLayout = z;
    }

    public void setLastMeasureSpec(int i2, int i7) {
        this.mLastHorizontalMeasureSpec = i2;
        this.mLastVerticalMeasureSpec = i7;
        setMeasureRequested(false);
    }

    public void setMaxHeight(int i2) {
        this.mMaxDimension[1] = i2;
    }

    public void setMaxWidth(int i2) {
        this.mMaxDimension[0] = i2;
    }

    public void setMeasureRequested(boolean z) {
        this.mMeasureRequested = z;
    }

    public void setMinHeight(int i2) {
        if (i2 < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i2;
        }
    }

    public void setMinWidth(int i2) {
        if (i2 < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i2;
        }
    }

    public void setOrigin(int i2, int i7) {
        this.mX = i2;
        this.mY = i7;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setVerticalChainStyle(int i2) {
        this.mVerticalChainStyle = i2;
    }

    public void setVerticalDimension(int i2, int i7) {
        this.mY = i2;
        int i8 = i7 - i2;
        this.mHeight = i8;
        int i10 = this.mMinHeight;
        if (i8 < i10) {
            this.mHeight = i10;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public void setVerticalMatchStyle(int i2, int i7, int i8, float f) {
        this.mMatchConstraintDefaultHeight = i2;
        this.mMatchConstraintMinHeight = i7;
        if (i8 == Integer.MAX_VALUE) {
            i8 = 0;
        }
        this.mMatchConstraintMaxHeight = i8;
        this.mMatchConstraintPercentHeight = f;
        if (f > 0.0f && f < 1.0f && i2 == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setVisibility(int i2) {
        this.mVisibility = i2;
    }

    public void setWidth(int i2) {
        this.mWidth = i2;
        int i7 = this.mMinWidth;
        if (i2 < i7) {
            this.mWidth = i7;
        }
    }

    public void setX(int i2) {
        this.mX = i2;
    }

    public void setY(int i2) {
        this.mY = i2;
    }

    public void setupDimensionRatio(boolean z, boolean z3, boolean z7, boolean z9) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z7 && !z9) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z7 && z9) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i2 = this.mMatchConstraintMinWidth;
            if (i2 > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (i2 == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        String str2 = "";
        if (this.mType != null) {
            str = C0212a.p(new StringBuilder("type: "), this.mType, " ");
        } else {
            str = str2;
        }
        sb2.append(str);
        if (this.mDebugName != null) {
            str2 = C0212a.p(new StringBuilder("id: "), this.mDebugName, " ");
        }
        sb2.append(str2);
        sb2.append("(");
        sb2.append(this.mX);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.mY);
        sb2.append(") - (");
        sb2.append(this.mWidth);
        sb2.append(" x ");
        return C0086a.l(sb2, this.mHeight, ")");
    }

    public void updateFromRuns(boolean z, boolean z3) {
        int i2;
        int i7;
        boolean isResolved = z & this.horizontalRun.isResolved();
        boolean isResolved2 = z3 & this.verticalRun.isResolved();
        HorizontalWidgetRun horizontalWidgetRun = this.horizontalRun;
        int i8 = horizontalWidgetRun.start.value;
        VerticalWidgetRun verticalWidgetRun = this.verticalRun;
        int i10 = verticalWidgetRun.start.value;
        int i11 = horizontalWidgetRun.end.value;
        int i12 = verticalWidgetRun.end.value;
        int i13 = i12 - i10;
        if (i11 - i8 < 0 || i13 < 0 || i8 == Integer.MIN_VALUE || i8 == Integer.MAX_VALUE || i10 == Integer.MIN_VALUE || i10 == Integer.MAX_VALUE || i11 == Integer.MIN_VALUE || i11 == Integer.MAX_VALUE || i12 == Integer.MIN_VALUE || i12 == Integer.MAX_VALUE) {
            i11 = 0;
            i8 = 0;
            i12 = 0;
            i10 = 0;
        }
        int i14 = i11 - i8;
        int i15 = i12 - i10;
        if (isResolved) {
            this.mX = i8;
        }
        if (isResolved2) {
            this.mY = i10;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (isResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i14 < (i7 = this.mWidth)) {
                i14 = i7;
            }
            this.mWidth = i14;
            int i16 = this.mMinWidth;
            if (i14 < i16) {
                this.mWidth = i16;
            }
        }
        if (isResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i15 < (i2 = this.mHeight)) {
                i15 = i2;
            }
            this.mHeight = i15;
            int i17 = this.mMinHeight;
            if (i15 < i17) {
                this.mHeight = i17;
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (z && (horizontalWidgetRun = this.horizontalRun) != null) {
            DependencyNode dependencyNode = horizontalWidgetRun.start;
            if (dependencyNode.resolved) {
                DependencyNode dependencyNode2 = horizontalWidgetRun.end;
                if (dependencyNode2.resolved) {
                    objectVariableValue = dependencyNode.value;
                    objectVariableValue3 = dependencyNode2.value;
                }
            }
        }
        if (z && (verticalWidgetRun = this.verticalRun) != null) {
            DependencyNode dependencyNode3 = verticalWidgetRun.start;
            if (dependencyNode3.resolved) {
                DependencyNode dependencyNode4 = verticalWidgetRun.end;
                if (dependencyNode4.resolved) {
                    objectVariableValue2 = dependencyNode3.value;
                    objectVariableValue4 = dependencyNode4.value;
                }
            }
        }
        int i2 = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i2 < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue = 0;
            objectVariableValue4 = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2) {
        ConstraintAnchor.Type type3;
        ConstraintAnchor.Type type4;
        boolean z;
        ConstraintAnchor.Type type5 = ConstraintAnchor.Type.CENTER;
        if (type != type5) {
            ConstraintAnchor.Type type6 = ConstraintAnchor.Type.CENTER_X;
            if (type == type6 && (type2 == (type4 = ConstraintAnchor.Type.LEFT) || type2 == ConstraintAnchor.Type.RIGHT)) {
                ConstraintAnchor anchor = getAnchor(type4);
                ConstraintAnchor anchor2 = constraintWidget.getAnchor(type2);
                ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.RIGHT);
                anchor.connect(anchor2, 0);
                anchor3.connect(anchor2, 0);
                getAnchor(type6).connect(anchor2, 0);
                return;
            }
            ConstraintAnchor.Type type7 = ConstraintAnchor.Type.CENTER_Y;
            if (type == type7 && (type2 == (type3 = ConstraintAnchor.Type.TOP) || type2 == ConstraintAnchor.Type.BOTTOM)) {
                ConstraintAnchor anchor4 = constraintWidget.getAnchor(type2);
                getAnchor(type3).connect(anchor4, 0);
                getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor4, 0);
                getAnchor(type7).connect(anchor4, 0);
            } else if (type == type6 && type2 == type6) {
                ConstraintAnchor.Type type8 = ConstraintAnchor.Type.LEFT;
                getAnchor(type8).connect(constraintWidget.getAnchor(type8), 0);
                ConstraintAnchor.Type type9 = ConstraintAnchor.Type.RIGHT;
                getAnchor(type9).connect(constraintWidget.getAnchor(type9), 0);
                getAnchor(type6).connect(constraintWidget.getAnchor(type2), 0);
            } else if (type == type7 && type2 == type7) {
                ConstraintAnchor.Type type10 = ConstraintAnchor.Type.TOP;
                getAnchor(type10).connect(constraintWidget.getAnchor(type10), 0);
                ConstraintAnchor.Type type11 = ConstraintAnchor.Type.BOTTOM;
                getAnchor(type11).connect(constraintWidget.getAnchor(type11), 0);
                getAnchor(type7).connect(constraintWidget.getAnchor(type2), 0);
            } else {
                ConstraintAnchor anchor5 = getAnchor(type);
                ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
                if (anchor5.isValidConnection(anchor6)) {
                    ConstraintAnchor.Type type12 = ConstraintAnchor.Type.BASELINE;
                    if (type == type12) {
                        ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.TOP);
                        ConstraintAnchor anchor8 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                        if (anchor7 != null) {
                            anchor7.reset();
                        }
                        if (anchor8 != null) {
                            anchor8.reset();
                        }
                        i2 = 0;
                    } else if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM) {
                        ConstraintAnchor anchor9 = getAnchor(type12);
                        if (anchor9 != null) {
                            anchor9.reset();
                        }
                        ConstraintAnchor anchor10 = getAnchor(type5);
                        if (anchor10.getTarget() != anchor6) {
                            anchor10.reset();
                        }
                        ConstraintAnchor opposite = getAnchor(type).getOpposite();
                        ConstraintAnchor anchor11 = getAnchor(type7);
                        if (anchor11.isConnected()) {
                            opposite.reset();
                            anchor11.reset();
                        }
                    } else if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                        ConstraintAnchor anchor12 = getAnchor(type5);
                        if (anchor12.getTarget() != anchor6) {
                            anchor12.reset();
                        }
                        ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                        ConstraintAnchor anchor13 = getAnchor(type6);
                        if (anchor13.isConnected()) {
                            opposite2.reset();
                            anchor13.reset();
                        }
                    }
                    anchor5.connect(anchor6, i2);
                }
            }
        } else if (type2 == type5) {
            ConstraintAnchor.Type type13 = ConstraintAnchor.Type.LEFT;
            ConstraintAnchor anchor14 = getAnchor(type13);
            ConstraintAnchor.Type type14 = ConstraintAnchor.Type.RIGHT;
            ConstraintAnchor anchor15 = getAnchor(type14);
            ConstraintAnchor.Type type15 = ConstraintAnchor.Type.TOP;
            ConstraintAnchor anchor16 = getAnchor(type15);
            ConstraintAnchor.Type type16 = ConstraintAnchor.Type.BOTTOM;
            ConstraintAnchor anchor17 = getAnchor(type16);
            boolean z3 = true;
            if ((anchor14 == null || !anchor14.isConnected()) && (anchor15 == null || !anchor15.isConnected())) {
                connect(type13, constraintWidget, type13, 0);
                connect(type14, constraintWidget, type14, 0);
                z = true;
            } else {
                z = false;
            }
            if ((anchor16 == null || !anchor16.isConnected()) && (anchor17 == null || !anchor17.isConnected())) {
                connect(type15, constraintWidget, type15, 0);
                connect(type16, constraintWidget, type16, 0);
            } else {
                z3 = false;
            }
            if (z && z3) {
                getAnchor(type5).connect(constraintWidget.getAnchor(type5), 0);
            } else if (z) {
                ConstraintAnchor.Type type17 = ConstraintAnchor.Type.CENTER_X;
                getAnchor(type17).connect(constraintWidget.getAnchor(type17), 0);
            } else if (z3) {
                ConstraintAnchor.Type type18 = ConstraintAnchor.Type.CENTER_Y;
                getAnchor(type18).connect(constraintWidget.getAnchor(type18), 0);
            }
        } else {
            ConstraintAnchor.Type type19 = ConstraintAnchor.Type.LEFT;
            if (type2 == type19 || type2 == ConstraintAnchor.Type.RIGHT) {
                connect(type19, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0);
                getAnchor(type5).connect(constraintWidget.getAnchor(type2), 0);
                return;
            }
            ConstraintAnchor.Type type20 = ConstraintAnchor.Type.TOP;
            if (type2 == type20 || type2 == ConstraintAnchor.Type.BOTTOM) {
                connect(type20, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0);
                getAnchor(type5).connect(constraintWidget.getAnchor(type2), 0);
            }
        }
    }
}
