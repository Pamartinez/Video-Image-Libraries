package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Chain {
    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i2) {
        int i7;
        ChainHead[] chainHeadArr;
        int i8;
        if (i2 == 0) {
            i8 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i7 = 0;
        } else {
            i8 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i7 = 2;
        }
        for (int i10 = 0; i10 < i8; i10++) {
            ChainHead chainHead = chainHeadArr[i10];
            chainHead.define();
            if (arrayList == null || arrayList.contains(chainHead.mFirst)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i2, i7, chainHead);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v1, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: androidx.constraintlayout.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: androidx.constraintlayout.solver.SolverVariable} */
    /* JADX WARNING: type inference failed for: r6v12, types: [androidx.constraintlayout.solver.SolverVariable] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r8 == 2) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        if (r8 == 2) goto L_0x002f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x024b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02a1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x03a1  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x03aa  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0473  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x04ab A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x04c1  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x04c4  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x04ca  */
    /* JADX WARNING: Removed duplicated region for block: B:281:0x04cd  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x04d1  */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x04e1  */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x04e5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:312:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0185  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r36, androidx.constraintlayout.solver.LinearSystem r37, int r38, int r39, androidx.constraintlayout.solver.widgets.ChainHead r40) {
        /*
            r0 = r36
            r1 = r37
            r2 = r40
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r2.mFirst
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r2.mLast
            androidx.constraintlayout.solver.widgets.ConstraintWidget r11 = r2.mFirstVisibleWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r12 = r2.mLastVisibleWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r2.mHead
            float r4 = r2.mTotalWeight
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r5 = r5[r38]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r13 = 1
            if (r5 != r6) goto L_0x001d
            r5 = r13
            goto L_0x001e
        L_0x001d:
            r5 = 0
        L_0x001e:
            r6 = 2
            if (r38 != 0) goto L_0x0035
            int r8 = r3.mHorizontalChainStyle
            if (r8 != 0) goto L_0x0027
            r14 = r13
            goto L_0x0028
        L_0x0027:
            r14 = 0
        L_0x0028:
            if (r8 != r13) goto L_0x002c
            r15 = r13
            goto L_0x002d
        L_0x002c:
            r15 = 0
        L_0x002d:
            if (r8 != r6) goto L_0x0031
        L_0x002f:
            r6 = r13
            goto L_0x0032
        L_0x0031:
            r6 = 0
        L_0x0032:
            r13 = r9
            r8 = 0
            goto L_0x0044
        L_0x0035:
            int r8 = r3.mVerticalChainStyle
            if (r8 != 0) goto L_0x003b
            r14 = r13
            goto L_0x003c
        L_0x003b:
            r14 = 0
        L_0x003c:
            if (r8 != r13) goto L_0x0040
            r15 = r13
            goto L_0x0041
        L_0x0040:
            r15 = 0
        L_0x0041:
            if (r8 != r6) goto L_0x0031
            goto L_0x002f
        L_0x0044:
            r18 = r4
            r21 = 0
            if (r8 != 0) goto L_0x011e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            r4 = r4[r39]
            if (r6 == 0) goto L_0x0053
            r20 = 1
            goto L_0x0055
        L_0x0053:
            r20 = 4
        L_0x0055:
            int r22 = r4.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r13.mListDimensionBehaviors
            r7 = r7[r38]
            r23 = r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 != r5) goto L_0x006d
            int[] r7 = r13.mResolvedMatchConstraintDefault
            r7 = r7[r38]
            if (r7 != 0) goto L_0x006d
            r7 = 1
        L_0x006a:
            r24 = r6
            goto L_0x006f
        L_0x006d:
            r7 = 0
            goto L_0x006a
        L_0x006f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r4.mTarget
            if (r6 == 0) goto L_0x007b
            if (r13 == r9) goto L_0x007b
            int r6 = r6.getMargin()
            int r22 = r6 + r22
        L_0x007b:
            r6 = r22
            if (r24 == 0) goto L_0x0085
            if (r13 == r9) goto L_0x0085
            if (r13 == r11) goto L_0x0085
            r20 = 8
        L_0x0085:
            r22 = r7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r4.mTarget
            if (r7 == 0) goto L_0x00b9
            if (r13 != r11) goto L_0x009a
            r25 = r8
            androidx.constraintlayout.solver.SolverVariable r8 = r4.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r7 = r7.mSolverVariable
            r26 = r14
            r14 = 6
            r1.addGreaterThan(r8, r7, r6, r14)
            goto L_0x00a7
        L_0x009a:
            r25 = r8
            r26 = r14
            androidx.constraintlayout.solver.SolverVariable r8 = r4.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r7 = r7.mSolverVariable
            r14 = 8
            r1.addGreaterThan(r8, r7, r6, r14)
        L_0x00a7:
            if (r22 == 0) goto L_0x00ad
            if (r24 != 0) goto L_0x00ad
            r7 = 5
            goto L_0x00af
        L_0x00ad:
            r7 = r20
        L_0x00af:
            androidx.constraintlayout.solver.SolverVariable r8 = r4.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            r1.addEquality(r8, r4, r6, r7)
            goto L_0x00bd
        L_0x00b9:
            r25 = r8
            r26 = r14
        L_0x00bd:
            if (r23 == 0) goto L_0x00f1
            int r4 = r13.getVisibility()
            r14 = 8
            if (r4 == r14) goto L_0x00df
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r13.mListDimensionBehaviors
            r4 = r4[r38]
            if (r4 != r5) goto L_0x00df
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            int r5 = r39 + 1
            r5 = r4[r5]
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            r4 = r4[r39]
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            r6 = 5
            r7 = 0
            r1.addGreaterThan(r5, r4, r7, r6)
            goto L_0x00e0
        L_0x00df:
            r7 = 0
        L_0x00e0:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            r4 = r4[r39]
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r0.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            r14 = 8
            r1.addGreaterThan(r4, r5, r7, r14)
        L_0x00f1:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x010c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r4.mOwner
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r4.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x010c
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            if (r5 == r13) goto L_0x010a
            goto L_0x010c
        L_0x010a:
            r21 = r4
        L_0x010c:
            if (r21 == 0) goto L_0x0113
            r13 = r21
            r8 = r25
            goto L_0x0114
        L_0x0113:
            r8 = 1
        L_0x0114:
            r4 = r18
            r5 = r23
            r6 = r24
            r14 = r26
            goto L_0x0044
        L_0x011e:
            r23 = r5
            r24 = r6
            r26 = r14
            if (r12 == 0) goto L_0x0182
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r10.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0182
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r4 = r4[r5]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r6 = r12.mListDimensionBehaviors
            r6 = r6[r38]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r7) goto L_0x0158
            int[] r6 = r12.mResolvedMatchConstraintDefault
            r6 = r6[r38]
            if (r6 != 0) goto L_0x0158
            if (r24 != 0) goto L_0x0158
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r4.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r6.mOwner
            if (r7 != r0) goto L_0x0158
            androidx.constraintlayout.solver.SolverVariable r7 = r4.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            int r8 = r4.getMargin()
            int r8 = -r8
            r14 = 5
            r1.addEquality(r7, r6, r8, r14)
            goto L_0x016e
        L_0x0158:
            r14 = 5
            if (r24 == 0) goto L_0x016e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r4.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r6.mOwner
            if (r7 != r0) goto L_0x016e
            androidx.constraintlayout.solver.SolverVariable r7 = r4.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            int r8 = r4.getMargin()
            int r8 = -r8
            r13 = 4
            r1.addEquality(r7, r6, r8, r13)
        L_0x016e:
            androidx.constraintlayout.solver.SolverVariable r6 = r4.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r10.mListAnchors
            r5 = r7[r5]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            int r4 = r4.getMargin()
            int r4 = -r4
            r7 = 6
            r1.addLowerThan(r6, r5, r4, r7)
            goto L_0x0183
        L_0x0182:
            r14 = 5
        L_0x0183:
            if (r23 == 0) goto L_0x019c
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r4 = r39 + 1
            r0 = r0[r4]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r10.mListAnchors
            r4 = r5[r4]
            androidx.constraintlayout.solver.SolverVariable r5 = r4.mSolverVariable
            int r4 = r4.getMargin()
            r6 = 8
            r1.addGreaterThan(r0, r5, r4, r6)
        L_0x019c:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r2.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x0249
            int r4 = r0.size()
            r5 = 1
            if (r4 <= r5) goto L_0x0249
            boolean r5 = r2.mHasUndefinedWeights
            if (r5 == 0) goto L_0x01b5
            boolean r5 = r2.mHasComplexMatchWeights
            if (r5 != 0) goto L_0x01b5
            int r5 = r2.mWidgetsMatchCount
            float r5 = (float) r5
            r29 = r5
            goto L_0x01b7
        L_0x01b5:
            r29 = r18
        L_0x01b7:
            r5 = 0
            r28 = r5
            r6 = r21
            r7 = 0
        L_0x01bd:
            if (r7 >= r4) goto L_0x0249
            java.lang.Object r8 = r0.get(r7)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r8
            float[] r13 = r8.mWeight
            r13 = r13[r38]
            int r18 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r18 >= 0) goto L_0x01ee
            boolean r13 = r2.mHasComplexMatchWeights
            if (r13 == 0) goto L_0x01e6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r8.mListAnchors
            int r13 = r39 + 1
            r13 = r8[r13]
            androidx.constraintlayout.solver.SolverVariable r13 = r13.mSolverVariable
            r8 = r8[r39]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
            r36 = r5
            r5 = 4
            r14 = 0
            r1.addEquality(r13, r8, r14, r5)
            r5 = r14
            goto L_0x0208
        L_0x01e6:
            r36 = r5
            r5 = 4
            r13 = 1065353216(0x3f800000, float:1.0)
        L_0x01eb:
            r30 = r13
            goto L_0x01f2
        L_0x01ee:
            r36 = r5
            r5 = 4
            goto L_0x01eb
        L_0x01f2:
            int r13 = (r30 > r36 ? 1 : (r30 == r36 ? 0 : -1))
            if (r13 != 0) goto L_0x020b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r8.mListAnchors
            int r13 = r39 + 1
            r13 = r8[r13]
            androidx.constraintlayout.solver.SolverVariable r13 = r13.mSolverVariable
            r8 = r8[r39]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
            r5 = 0
            r14 = 8
            r1.addEquality(r13, r8, r5, r14)
        L_0x0208:
            r18 = r0
            goto L_0x0240
        L_0x020b:
            r5 = 0
            if (r6 == 0) goto L_0x023b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r6.mListAnchors
            r13 = r6[r39]
            androidx.constraintlayout.solver.SolverVariable r13 = r13.mSolverVariable
            int r14 = r39 + 1
            r6 = r6[r14]
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r18 = r0
            r0 = r5[r39]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            r5 = r5[r14]
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            androidx.constraintlayout.solver.ArrayRow r27 = r1.createRow()
            r33 = r0
            r34 = r5
            r32 = r6
            r31 = r13
            r27.createRowEqualMatchDimensions(r28, r29, r30, r31, r32, r33, r34)
            r0 = r27
            r1.addConstraint(r0)
            goto L_0x023d
        L_0x023b:
            r18 = r0
        L_0x023d:
            r6 = r8
            r28 = r30
        L_0x0240:
            int r7 = r7 + 1
            r5 = r36
            r0 = r18
            r14 = 5
            goto L_0x01bd
        L_0x0249:
            if (r11 == 0) goto L_0x029f
            if (r11 == r12) goto L_0x024f
            if (r24 == 0) goto L_0x029f
        L_0x024f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r9.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            int r4 = r39 + 1
            r2 = r2[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x0260
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            goto L_0x0262
        L_0x0260:
            r0 = r21
        L_0x0262:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x026a
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            r5 = r2
            goto L_0x026c
        L_0x026a:
            r5 = r21
        L_0x026c:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r12.mListAnchors
            r4 = r6[r4]
            if (r0 == 0) goto L_0x029b
            if (r5 == 0) goto L_0x029b
            if (r38 != 0) goto L_0x027d
            float r3 = r3.mHorizontalBiasPercent
            goto L_0x027f
        L_0x027d:
            float r3 = r3.mVerticalBiasPercent
        L_0x027f:
            int r6 = r2.getMargin()
            int r7 = r4.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            r8 = 7
            r35 = r2
            r2 = r0
            r0 = r1
            r1 = r35
            r35 = r4
            r4 = r3
            r3 = r6
            r6 = r35
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x029b:
            r0 = r37
            goto L_0x04a9
        L_0x029f:
            if (r26 == 0) goto L_0x0393
            if (r11 == 0) goto L_0x0393
            int r0 = r2.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02ae
            int r1 = r2.mWidgetsCount
            if (r1 != r0) goto L_0x02ae
            r17 = 1
            goto L_0x02b0
        L_0x02ae:
            r17 = 0
        L_0x02b0:
            r13 = r11
            r14 = r13
        L_0x02b2:
            if (r13 == 0) goto L_0x029b
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r13.mNextChainWidget
            r0 = r0[r38]
        L_0x02b8:
            if (r0 == 0) goto L_0x02c7
            int r1 = r0.getVisibility()
            r6 = 8
            if (r1 != r6) goto L_0x02c9
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r0.mNextChainWidget
            r0 = r0[r38]
            goto L_0x02b8
        L_0x02c7:
            r6 = 8
        L_0x02c9:
            if (r0 != 0) goto L_0x02d7
            if (r13 != r12) goto L_0x02ce
            goto L_0x02d7
        L_0x02ce:
            r18 = r0
            r36 = r13
            r19 = 5
            r13 = r6
            goto L_0x0387
        L_0x02d7:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            r1 = r1[r39]
            androidx.constraintlayout.solver.SolverVariable r2 = r1.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x02e4
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02e6
        L_0x02e4:
            r3 = r21
        L_0x02e6:
            if (r14 == r13) goto L_0x02f1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r39 + 1
            r3 = r3[r4]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0302
        L_0x02f1:
            if (r13 != r11) goto L_0x0302
            if (r14 != r13) goto L_0x0302
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r9.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0300
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0302
        L_0x0300:
            r3 = r21
        L_0x0302:
            int r1 = r1.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            int r4 = r4.getMargin()
            if (r0 == 0) goto L_0x031f
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r0.mListAnchors
            r7 = r7[r39]
            androidx.constraintlayout.solver.SolverVariable r8 = r7.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r13.mListAnchors
            r6 = r6[r5]
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            goto L_0x0333
        L_0x031f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r10.mListAnchors
            r6 = r6[r5]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            if (r7 == 0) goto L_0x032b
            androidx.constraintlayout.solver.SolverVariable r6 = r7.mSolverVariable
            r8 = r6
            goto L_0x032d
        L_0x032b:
            r8 = r21
        L_0x032d:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r13.mListAnchors
            r6 = r6[r5]
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
        L_0x0333:
            if (r7 == 0) goto L_0x033a
            int r7 = r7.getMargin()
            int r4 = r4 + r7
        L_0x033a:
            if (r14 == 0) goto L_0x0345
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r7 = r7[r5]
            int r7 = r7.getMargin()
            int r1 = r1 + r7
        L_0x0345:
            if (r2 == 0) goto L_0x037f
            if (r3 == 0) goto L_0x037f
            if (r8 == 0) goto L_0x037f
            if (r6 == 0) goto L_0x037f
            if (r13 != r11) goto L_0x0357
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r1 = r1[r39]
            int r1 = r1.getMargin()
        L_0x0357:
            if (r13 != r12) goto L_0x0361
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r4 = r4[r5]
            int r4 = r4.getMargin()
        L_0x0361:
            r7 = r4
            r5 = r8
            if (r17 == 0) goto L_0x0368
            r8 = 8
            goto L_0x0369
        L_0x0368:
            r8 = 5
        L_0x0369:
            r4 = 1056964608(0x3f000000, float:0.5)
            r36 = r3
            r3 = r1
            r1 = r2
            r2 = r36
            r18 = r0
            r36 = r13
            r13 = 8
            r19 = 5
            r0 = r37
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0387
        L_0x037f:
            r18 = r0
            r36 = r13
            r13 = 8
            r19 = 5
        L_0x0387:
            int r0 = r36.getVisibility()
            if (r0 == r13) goto L_0x038f
            r14 = r36
        L_0x038f:
            r13 = r18
            goto L_0x02b2
        L_0x0393:
            r13 = 8
            if (r15 == 0) goto L_0x029b
            if (r11 == 0) goto L_0x029b
            int r0 = r2.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03a4
            int r1 = r2.mWidgetsCount
            if (r1 != r0) goto L_0x03a4
            r17 = 1
            goto L_0x03a6
        L_0x03a4:
            r17 = 0
        L_0x03a6:
            r0 = r11
            r14 = r0
        L_0x03a8:
            if (r14 == 0) goto L_0x0458
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r14.mNextChainWidget
            r1 = r1[r38]
        L_0x03ae:
            if (r1 == 0) goto L_0x03bb
            int r2 = r1.getVisibility()
            if (r2 != r13) goto L_0x03bb
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r1.mNextChainWidget
            r1 = r1[r38]
            goto L_0x03ae
        L_0x03bb:
            if (r14 == r11) goto L_0x0445
            if (r14 == r12) goto L_0x0445
            if (r1 == 0) goto L_0x0445
            if (r1 != r12) goto L_0x03c5
            r1 = r21
        L_0x03c5:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.solver.SolverVariable r3 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r2.mTarget
            if (r4 == 0) goto L_0x03d1
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
        L_0x03d1:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            int r2 = r2.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r14.mListAnchors
            r6 = r6[r5]
            int r6 = r6.getMargin()
            if (r1 == 0) goto L_0x03f7
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r1.mListAnchors
            r7 = r7[r39]
            androidx.constraintlayout.solver.SolverVariable r8 = r7.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r7.mTarget
            if (r13 == 0) goto L_0x03f4
            androidx.constraintlayout.solver.SolverVariable r13 = r13.mSolverVariable
            goto L_0x0408
        L_0x03f4:
            r13 = r21
            goto L_0x0408
        L_0x03f7:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r12.mListAnchors
            r7 = r7[r39]
            if (r7 == 0) goto L_0x0400
            androidx.constraintlayout.solver.SolverVariable r8 = r7.mSolverVariable
            goto L_0x0402
        L_0x0400:
            r8 = r21
        L_0x0402:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r13 = r14.mListAnchors
            r13 = r13[r5]
            androidx.constraintlayout.solver.SolverVariable r13 = r13.mSolverVariable
        L_0x0408:
            if (r7 == 0) goto L_0x040f
            int r7 = r7.getMargin()
            int r6 = r6 + r7
        L_0x040f:
            r7 = r6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r0.mListAnchors
            r5 = r6[r5]
            int r5 = r5.getMargin()
            int r5 = r5 + r2
            r2 = r1
            r1 = r3
            r3 = r5
            r5 = r8
            if (r17 == 0) goto L_0x0422
            r8 = 8
            goto L_0x0423
        L_0x0422:
            r8 = 4
        L_0x0423:
            if (r1 == 0) goto L_0x043b
            if (r4 == 0) goto L_0x043b
            if (r5 == 0) goto L_0x043b
            if (r13 == 0) goto L_0x043b
            r6 = r2
            r2 = r4
            r4 = 1056964608(0x3f000000, float:0.5)
            r18 = r6
            r6 = r13
            r20 = 4
            r13 = r0
            r0 = r37
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0442
        L_0x043b:
            r13 = r0
            r18 = r2
            r20 = 4
            r0 = r37
        L_0x0442:
            r1 = r18
            goto L_0x044a
        L_0x0445:
            r13 = r0
            r20 = 4
            r0 = r37
        L_0x044a:
            int r2 = r14.getVisibility()
            r6 = 8
            if (r2 == r6) goto L_0x0453
            r13 = r14
        L_0x0453:
            r14 = r1
            r0 = r13
            r13 = r6
            goto L_0x03a8
        L_0x0458:
            r0 = r37
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r1 = r1[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            int r4 = r39 + 1
            r9 = r3[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r10.mListAnchors
            r3 = r3[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r13 = r3.mTarget
            r8 = 5
            if (r2 == 0) goto L_0x0499
            if (r11 == r12) goto L_0x0481
            androidx.constraintlayout.solver.SolverVariable r3 = r1.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            int r1 = r1.getMargin()
            r0.addEquality(r3, r2, r1, r8)
            goto L_0x0499
        L_0x0481:
            if (r13 == 0) goto L_0x0499
            r3 = r1
            androidx.constraintlayout.solver.SolverVariable r1 = r3.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            int r3 = r3.getMargin()
            androidx.constraintlayout.solver.SolverVariable r5 = r9.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r6 = r13.mSolverVariable
            int r7 = r9.getMargin()
            r4 = 1056964608(0x3f000000, float:0.5)
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0499:
            if (r13 == 0) goto L_0x04a9
            if (r11 == r12) goto L_0x04a9
            androidx.constraintlayout.solver.SolverVariable r1 = r9.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r2 = r13.mSolverVariable
            int r3 = r9.getMargin()
            int r3 = -r3
            r0.addEquality(r1, r2, r3, r8)
        L_0x04a9:
            if (r26 != 0) goto L_0x04ad
            if (r15 == 0) goto L_0x0505
        L_0x04ad:
            if (r11 == 0) goto L_0x0505
            if (r11 == r12) goto L_0x0505
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r2 = r1[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r16 = 1
            int r4 = r39 + 1
            r3 = r3[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            if (r5 == 0) goto L_0x04c4
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            goto L_0x04c6
        L_0x04c4:
            r5 = r21
        L_0x04c6:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r3.mTarget
            if (r6 == 0) goto L_0x04cd
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            goto L_0x04cf
        L_0x04cd:
            r6 = r21
        L_0x04cf:
            if (r10 == r12) goto L_0x04df
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r10.mListAnchors
            r6 = r6[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x04dd
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            r21 = r6
        L_0x04dd:
            r6 = r21
        L_0x04df:
            if (r11 != r12) goto L_0x04e3
            r3 = r1[r4]
        L_0x04e3:
            if (r5 == 0) goto L_0x0505
            if (r6 == 0) goto L_0x0505
            int r1 = r2.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r12.mListAnchors
            r4 = r7[r4]
            int r7 = r4.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            r8 = 5
            r4 = 1056964608(0x3f000000, float:0.5)
            r35 = r3
            r3 = r1
            r1 = r2
            r2 = r5
            r5 = r6
            r6 = r35
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0505:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }
}
