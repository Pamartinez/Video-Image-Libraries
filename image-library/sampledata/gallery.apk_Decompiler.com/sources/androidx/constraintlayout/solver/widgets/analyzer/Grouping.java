package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Grouping {
    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i2, ArrayList<WidgetGroup> arrayList, WidgetGroup widgetGroup) {
        int i7;
        int findGroupInDependents;
        if (i2 == 0) {
            i7 = constraintWidget.horizontalGroup;
        } else {
            i7 = constraintWidget.verticalGroup;
        }
        int i8 = 0;
        if (i7 != -1 && (widgetGroup == null || i7 != widgetGroup.id)) {
            int i10 = 0;
            while (true) {
                if (i10 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = arrayList.get(i10);
                if (widgetGroup2.getId() == i7) {
                    if (widgetGroup != null) {
                        widgetGroup.moveTo(i2, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i10++;
                }
            }
        } else if (i7 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if ((constraintWidget instanceof HelperWidget) && (findGroupInDependents = ((HelperWidget) constraintWidget).findGroupInDependents(i2)) != -1) {
                int i11 = 0;
                while (true) {
                    if (i11 >= arrayList.size()) {
                        break;
                    }
                    WidgetGroup widgetGroup3 = arrayList.get(i11);
                    if (widgetGroup3.getId() == findGroupInDependents) {
                        widgetGroup = widgetGroup3;
                        break;
                    }
                    i11++;
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup(i2);
            }
            arrayList.add(widgetGroup);
        }
        if (widgetGroup.add(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                ConstraintAnchor anchor = guideline.getAnchor();
                if (guideline.getOrientation() == 0) {
                    i8 = 1;
                }
                anchor.findDependents(i8, arrayList, widgetGroup);
            }
            if (i2 == 0) {
                constraintWidget.horizontalGroup = widgetGroup.getId();
                constraintWidget.mLeft.findDependents(i2, arrayList, widgetGroup);
                constraintWidget.mRight.findDependents(i2, arrayList, widgetGroup);
            } else {
                constraintWidget.verticalGroup = widgetGroup.getId();
                constraintWidget.mTop.findDependents(i2, arrayList, widgetGroup);
                constraintWidget.mBaseline.findDependents(i2, arrayList, widgetGroup);
                constraintWidget.mBottom.findDependents(i2, arrayList, widgetGroup);
            }
            constraintWidget.mCenter.findDependents(i2, arrayList, widgetGroup);
        }
        return widgetGroup;
    }

    private static WidgetGroup findGroup(ArrayList<WidgetGroup> arrayList, int i2) {
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            WidgetGroup widgetGroup = arrayList.get(i7);
            if (i2 == widgetGroup.id) {
                return widgetGroup;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:178:0x0351  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x038d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean simpleSolvingPass(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r17, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measurer r18) {
        /*
            r0 = r17
            java.util.ArrayList r1 = r0.getChildren()
            int r2 = r1.size()
            r3 = 0
            r4 = r3
        L_0x000c:
            if (r4 >= r2) goto L_0x0033
            java.lang.Object r5 = r1.get(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r0.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r0.getVerticalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r5.getVerticalDimensionBehaviour()
            boolean r6 = validInGroup(r6, r7, r8, r9)
            if (r6 != 0) goto L_0x002b
            return r3
        L_0x002b:
            boolean r5 = r5 instanceof androidx.constraintlayout.solver.widgets.Flow
            if (r5 == 0) goto L_0x0030
            return r3
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x000c
        L_0x0033:
            androidx.constraintlayout.solver.Metrics r4 = r0.mMetrics
            if (r4 == 0) goto L_0x003e
            long r5 = r4.grouping
            r7 = 1
            long r5 = r5 + r7
            r4.grouping = r5
        L_0x003e:
            r5 = r3
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0045:
            r12 = 1
            if (r5 >= r2) goto L_0x011d
            java.lang.Object r13 = r1.get(r5)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r13
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = r0.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = r0.getVerticalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r13.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = r13.getVerticalDimensionBehaviour()
            boolean r3 = validInGroup(r14, r15, r3, r4)
            if (r3 != 0) goto L_0x006e
            androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measure r3 = r0.mMeasure
            int r4 = androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r14 = r18
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer.measure(r13, r14, r3, r4)
            goto L_0x0070
        L_0x006e:
            r14 = r18
        L_0x0070:
            boolean r3 = r13 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r3 == 0) goto L_0x0097
            r4 = r13
            androidx.constraintlayout.solver.widgets.Guideline r4 = (androidx.constraintlayout.solver.widgets.Guideline) r4
            int r15 = r4.getOrientation()
            if (r15 != 0) goto L_0x0087
            if (r8 != 0) goto L_0x0084
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
        L_0x0084:
            r8.add(r4)
        L_0x0087:
            int r15 = r4.getOrientation()
            if (r15 != r12) goto L_0x0097
            if (r6 != 0) goto L_0x0094
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x0094:
            r6.add(r4)
        L_0x0097:
            boolean r4 = r13 instanceof androidx.constraintlayout.solver.widgets.HelperWidget
            if (r4 == 0) goto L_0x00da
            boolean r4 = r13 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r4 == 0) goto L_0x00c3
            r4 = r13
            androidx.constraintlayout.solver.widgets.Barrier r4 = (androidx.constraintlayout.solver.widgets.Barrier) r4
            int r15 = r4.getOrientation()
            if (r15 != 0) goto L_0x00b2
            if (r7 != 0) goto L_0x00af
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x00af:
            r7.add(r4)
        L_0x00b2:
            int r15 = r4.getOrientation()
            if (r15 != r12) goto L_0x00da
            if (r9 != 0) goto L_0x00bf
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x00bf:
            r9.add(r4)
            goto L_0x00da
        L_0x00c3:
            r4 = r13
            androidx.constraintlayout.solver.widgets.HelperWidget r4 = (androidx.constraintlayout.solver.widgets.HelperWidget) r4
            if (r7 != 0) goto L_0x00cd
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x00cd:
            r7.add(r4)
            if (r9 != 0) goto L_0x00d7
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x00d7:
            r9.add(r4)
        L_0x00da:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r13.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x00f6
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r13.mRight
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x00f6
            if (r3 != 0) goto L_0x00f6
            boolean r4 = r13 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r4 != 0) goto L_0x00f6
            if (r10 != 0) goto L_0x00f3
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x00f3:
            r10.add(r13)
        L_0x00f6:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r13.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x0118
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r13.mBottom
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x0118
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r13.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x0118
            if (r3 != 0) goto L_0x0118
            boolean r3 = r13 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r3 != 0) goto L_0x0118
            if (r11 != 0) goto L_0x0115
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
        L_0x0115:
            r11.add(r13)
        L_0x0118:
            int r5 = r5 + 1
            r3 = 0
            goto L_0x0045
        L_0x011d:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            if (r6 == 0) goto L_0x013a
            java.util.Iterator r4 = r6.iterator()
        L_0x0128:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x013a
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.Guideline r5 = (androidx.constraintlayout.solver.widgets.Guideline) r5
            r6 = 0
            r13 = 0
            findDependents(r5, r13, r3, r6)
            goto L_0x0128
        L_0x013a:
            r6 = 0
            r13 = 0
            if (r7 == 0) goto L_0x015b
            java.util.Iterator r4 = r7.iterator()
        L_0x0142:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x015b
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.HelperWidget r5 = (androidx.constraintlayout.solver.widgets.HelperWidget) r5
            androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup r7 = findDependents(r5, r13, r3, r6)
            r5.addDependents(r3, r13, r7)
            r7.cleanup(r3)
            r6 = 0
            r13 = 0
            goto L_0x0142
        L_0x015b:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x0183
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x016f:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0183
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r13 = 0
            findDependents(r5, r13, r3, r6)
            goto L_0x016f
        L_0x0183:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x01ab
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0197:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01ab
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r13 = 0
            findDependents(r5, r13, r3, r6)
            goto L_0x0197
        L_0x01ab:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x01d3
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x01bf:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01d3
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r13 = 0
            findDependents(r5, r13, r3, r6)
            goto L_0x01bf
        L_0x01d3:
            r6 = 0
            r13 = 0
            if (r10 == 0) goto L_0x01eb
            java.util.Iterator r4 = r10.iterator()
        L_0x01db:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01eb
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            findDependents(r5, r13, r3, r6)
            goto L_0x01db
        L_0x01eb:
            if (r8 == 0) goto L_0x0201
            java.util.Iterator r4 = r8.iterator()
        L_0x01f1:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0201
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.Guideline r5 = (androidx.constraintlayout.solver.widgets.Guideline) r5
            findDependents(r5, r12, r3, r6)
            goto L_0x01f1
        L_0x0201:
            if (r9 == 0) goto L_0x021f
            java.util.Iterator r4 = r9.iterator()
        L_0x0207:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x021f
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.HelperWidget r5 = (androidx.constraintlayout.solver.widgets.HelperWidget) r5
            androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup r7 = findDependents(r5, r12, r3, r6)
            r5.addDependents(r3, r12, r7)
            r7.cleanup(r3)
            r6 = 0
            goto L_0x0207
        L_0x021f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x0246
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0233:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0246
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            findDependents(r5, r12, r3, r6)
            goto L_0x0233
        L_0x0246:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x026d
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x025a:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x026d
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            findDependents(r5, r12, r3, r6)
            goto L_0x025a
        L_0x026d:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x0294
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0281:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0294
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            findDependents(r5, r12, r3, r6)
            goto L_0x0281
        L_0x0294:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x02bb
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x02a8:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02bb
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            findDependents(r5, r12, r3, r6)
            goto L_0x02a8
        L_0x02bb:
            r6 = 0
            if (r11 == 0) goto L_0x02d2
            java.util.Iterator r4 = r11.iterator()
        L_0x02c2:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02d2
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            findDependents(r5, r12, r3, r6)
            goto L_0x02c2
        L_0x02d2:
            r4 = 0
        L_0x02d3:
            if (r4 >= r2) goto L_0x02ff
            java.lang.Object r5 = r1.get(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            boolean r7 = r5.oppositeDimensionsTied()
            if (r7 == 0) goto L_0x02fc
            int r7 = r5.horizontalGroup
            androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup r7 = findGroup(r3, r7)
            int r5 = r5.verticalGroup
            androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup r5 = findGroup(r3, r5)
            if (r7 == 0) goto L_0x02fc
            if (r5 == 0) goto L_0x02fc
            r13 = 0
            r7.moveTo(r13, r5)
            r8 = 2
            r5.setOrientation(r8)
            r3.remove(r7)
        L_0x02fc:
            int r4 = r4 + 1
            goto L_0x02d3
        L_0x02ff:
            int r1 = r3.size()
            if (r1 > r12) goto L_0x0308
            r16 = 0
            return r16
        L_0x0308:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r0.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r2) goto L_0x0348
            java.util.Iterator r1 = r3.iterator()
            r2 = r6
            r4 = 0
        L_0x0316:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x033a
            java.lang.Object r5 = r1.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup r5 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup) r5
            int r7 = r5.getOrientation()
            if (r7 != r12) goto L_0x0329
            goto L_0x0316
        L_0x0329:
            r13 = 0
            r5.setAuthoritative(r13)
            androidx.constraintlayout.solver.LinearSystem r7 = r0.getSystem()
            int r7 = r5.measureWrap(r7, r13)
            if (r7 <= r4) goto L_0x0316
            r2 = r5
            r4 = r7
            goto L_0x0316
        L_0x033a:
            if (r2 == 0) goto L_0x0348
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0.setHorizontalDimensionBehaviour(r1)
            r0.setWidth(r4)
            r2.setAuthoritative(r12)
            goto L_0x0349
        L_0x0348:
            r2 = r6
        L_0x0349:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r0.getVerticalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r4) goto L_0x038a
            java.util.Iterator r1 = r3.iterator()
            r3 = r6
            r13 = 0
        L_0x0357:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x037b
            java.lang.Object r4 = r1.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup r4 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup) r4
            int r5 = r4.getOrientation()
            if (r5 != 0) goto L_0x036a
            goto L_0x0357
        L_0x036a:
            r5 = 0
            r4.setAuthoritative(r5)
            androidx.constraintlayout.solver.LinearSystem r5 = r0.getSystem()
            int r5 = r4.measureWrap(r5, r12)
            if (r5 <= r13) goto L_0x0357
            r3 = r4
            r13 = r5
            goto L_0x0357
        L_0x037b:
            if (r3 == 0) goto L_0x038a
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0.setVerticalDimensionBehaviour(r1)
            r0.setHeight(r13)
            r3.setAuthoritative(r12)
            r4 = r3
            goto L_0x038b
        L_0x038a:
            r4 = r6
        L_0x038b:
            if (r2 != 0) goto L_0x0393
            if (r4 == 0) goto L_0x0390
            goto L_0x0393
        L_0x0390:
            r16 = 0
            return r16
        L_0x0393:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.Grouping.simpleSolvingPass(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measurer):boolean");
    }

    public static boolean validInGroup(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        boolean z;
        boolean z3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour6;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour3 == dimensionBehaviour7 || dimensionBehaviour3 == (dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != dimensionBehaviour6)) {
            z = true;
        } else {
            z = false;
        }
        if (dimensionBehaviour4 == dimensionBehaviour7 || dimensionBehaviour4 == (dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != dimensionBehaviour5)) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z || z3) {
            return true;
        }
        return false;
    }
}
