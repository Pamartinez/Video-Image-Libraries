package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets = new ArrayList<>();

    public ChainRun(ConstraintWidget constraintWidget, int i2) {
        super(constraintWidget);
        this.orientation = i2;
        build();
    }

    private void build() {
        ConstraintWidget constraintWidget;
        int i2;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            int i7 = this.orientation;
            if (i7 == 0) {
                next.widget.horizontalChainRun = this;
            } else if (i7 == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if (this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl() && this.widgets.size() > 1) {
            this.widget = ((WidgetRun) C0212a.i(this.widgets, 1)).widget;
        }
        if (this.orientation == 0) {
            i2 = this.widget.getHorizontalChainStyle();
        } else {
            i2 = this.widget.getVerticalChainStyle();
        }
        this.chainStyle = i2;
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i2 = 0; i2 < this.widgets.size(); i2++) {
            WidgetRun widgetRun = this.widgets.get(i2);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    public void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size >= 1) {
            ConstraintWidget constraintWidget = this.widgets.get(0).widget;
            ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
            if (this.orientation == 0) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
                DependencyNode target = getTarget(constraintAnchor, 0);
                int margin = constraintAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    margin = firstVisibleWidget.mLeft.getMargin();
                }
                if (target != null) {
                    addTarget(this.start, target, margin);
                }
                DependencyNode target2 = getTarget(constraintAnchor2, 0);
                int margin2 = constraintAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    margin2 = lastVisibleWidget.mRight.getMargin();
                }
                if (target2 != null) {
                    addTarget(this.end, target2, -margin2);
                }
            } else {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
                DependencyNode target3 = getTarget(constraintAnchor3, 1);
                int margin3 = constraintAnchor3.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    margin3 = firstVisibleWidget2.mTop.getMargin();
                }
                if (target3 != null) {
                    addTarget(this.start, target3, margin3);
                }
                DependencyNode target4 = getTarget(constraintAnchor4, 1);
                int margin4 = constraintAnchor4.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    margin4 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (target4 != null) {
                    addTarget(this.end, target4, -margin4);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }

    public void applyToWidget() {
        for (int i2 = 0; i2 < this.widgets.size(); i2++) {
            this.widgets.get(i2).applyToWidget();
        }
    }

    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    public long getWrapDimension() {
        int size = this.widgets.size();
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            WidgetRun widgetRun = this.widgets.get(i2);
            j2 = ((long) widgetRun.end.margin) + widgetRun.getWrapDimension() + j2 + ((long) widgetRun.start.margin);
        }
        return j2;
    }

    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.widgets.get(i2).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String str;
        if (this.orientation == 0) {
            str = "horizontal : ";
        } else {
            str = "vertical : ";
        }
        String concat = "ChainRun ".concat(str);
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            String A10 = C0212a.A(concat, "<");
            concat = C0212a.A(A10 + it.next(), "> ");
        }
        return concat;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01b8, code lost:
        if (r2 != r7) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01de, code lost:
        if (r2 != r7) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01e4, code lost:
        r12 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d6, code lost:
        if (r2.resolved != false) goto L_0x00d2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.solver.widgets.analyzer.Dependency r27) {
        /*
            r26 = this;
            r0 = r26
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x043b
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r0.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0010
            goto L_0x043b
        L_0x0010:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r0.widget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r1.getParent()
            if (r1 == 0) goto L_0x0023
            boolean r3 = r1 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r3 == 0) goto L_0x0023
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r1
            boolean r1 = r1.isRtl()
            goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r0.end
            int r3 = r3.value
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r0.start
            int r4 = r4.value
            int r3 = r3 - r4
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r4 = r0.widgets
            int r4 = r4.size()
            r5 = 0
        L_0x0034:
            r6 = -1
            r7 = 8
            if (r5 >= r4) goto L_0x004c
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r8 = r0.widgets
            java.lang.Object r8 = r8.get(r5)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r8 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r8
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r8.widget
            int r8 = r8.getVisibility()
            if (r8 != r7) goto L_0x004d
            int r5 = r5 + 1
            goto L_0x0034
        L_0x004c:
            r5 = r6
        L_0x004d:
            int r8 = r4 + -1
            r9 = r8
        L_0x0050:
            if (r9 < 0) goto L_0x0066
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r9)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r10.widget
            int r10 = r10.getVisibility()
            if (r10 != r7) goto L_0x0065
            int r9 = r9 + -1
            goto L_0x0050
        L_0x0065:
            r6 = r9
        L_0x0066:
            r9 = 0
        L_0x0067:
            r11 = 2
            if (r9 >= r11) goto L_0x0114
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
        L_0x0071:
            if (r13 >= r4) goto L_0x0100
            r27 = 0
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r13)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r10.widget
            int r2 = r2.getVisibility()
            if (r2 != r7) goto L_0x0089
            r20 = r1
            goto L_0x00f7
        L_0x0089:
            int r16 = r16 + 1
            if (r13 <= 0) goto L_0x0094
            if (r13 < r5) goto L_0x0094
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r10.start
            int r2 = r2.margin
            int r14 = r14 + r2
        L_0x0094:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r10.dimension
            int r11 = r2.value
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r10.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 == r12) goto L_0x00a0
            r7 = 1
            goto L_0x00a1
        L_0x00a0:
            r7 = 0
        L_0x00a1:
            if (r7 == 0) goto L_0x00c5
            int r2 = r0.orientation
            if (r2 != 0) goto L_0x00b3
            androidx.constraintlayout.solver.widgets.ConstraintWidget r12 = r10.widget
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r12 = r12.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r12 = r12.dimension
            boolean r12 = r12.resolved
            if (r12 != 0) goto L_0x00b3
            goto L_0x043b
        L_0x00b3:
            r12 = 1
            if (r2 != r12) goto L_0x00c2
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r10.widget
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r2.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r2.dimension
            boolean r2 = r2.resolved
            if (r2 != 0) goto L_0x00c2
            goto L_0x043b
        L_0x00c2:
            r20 = r1
            goto L_0x00d9
        L_0x00c5:
            r20 = r1
            r12 = 1
            int r1 = r10.matchConstraintsType
            if (r1 != r12) goto L_0x00d4
            if (r9 != 0) goto L_0x00d4
            int r11 = r2.wrapValue
            int r15 = r15 + 1
        L_0x00d2:
            r7 = 1
            goto L_0x00d9
        L_0x00d4:
            boolean r1 = r2.resolved
            if (r1 == 0) goto L_0x00d9
            goto L_0x00d2
        L_0x00d9:
            if (r7 != 0) goto L_0x00ec
            int r15 = r15 + 1
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r10.widget
            float[] r1 = r1.mWeight
            int r2 = r0.orientation
            r1 = r1[r2]
            int r2 = (r1 > r27 ? 1 : (r1 == r27 ? 0 : -1))
            if (r2 < 0) goto L_0x00ed
            float r17 = r17 + r1
            goto L_0x00ed
        L_0x00ec:
            int r14 = r14 + r11
        L_0x00ed:
            if (r13 >= r8) goto L_0x00f7
            if (r13 >= r6) goto L_0x00f7
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r10.end
            int r1 = r1.margin
            int r1 = -r1
            int r14 = r14 + r1
        L_0x00f7:
            int r13 = r13 + 1
            r1 = r20
            r7 = 8
            r11 = 2
            goto L_0x0071
        L_0x0100:
            r20 = r1
            r27 = 0
            if (r14 < r3) goto L_0x0111
            if (r15 != 0) goto L_0x0109
            goto L_0x0111
        L_0x0109:
            int r9 = r9 + 1
            r1 = r20
            r7 = 8
            goto L_0x0067
        L_0x0111:
            r1 = r16
            goto L_0x011d
        L_0x0114:
            r20 = r1
            r27 = 0
            r17 = r27
            r1 = 0
            r14 = 0
            r15 = 0
        L_0x011d:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r0.start
            int r2 = r2.value
            if (r20 == 0) goto L_0x0127
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r0.end
            int r2 = r2.value
        L_0x0127:
            r7 = 1056964608(0x3f000000, float:0.5)
            if (r14 <= r3) goto L_0x013e
            r9 = 1073741824(0x40000000, float:2.0)
            if (r20 == 0) goto L_0x0137
            int r10 = r14 - r3
            float r10 = (float) r10
            float r10 = r10 / r9
            float r10 = r10 + r7
            int r9 = (int) r10
            int r2 = r2 + r9
            goto L_0x013e
        L_0x0137:
            int r10 = r14 - r3
            float r10 = (float) r10
            float r10 = r10 / r9
            float r10 = r10 + r7
            int r9 = (int) r10
            int r2 = r2 - r9
        L_0x013e:
            if (r15 <= 0) goto L_0x0243
            int r9 = r3 - r14
            float r9 = (float) r9
            float r10 = (float) r15
            float r10 = r9 / r10
            float r10 = r10 + r7
            int r10 = (int) r10
            r11 = 0
            r12 = 0
        L_0x014a:
            if (r11 >= r4) goto L_0x01fa
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r13 = r0.widgets
            java.lang.Object r13 = r13.get(r11)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r13 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r13
            r16 = r7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r13.widget
            int r7 = r7.getVisibility()
            r21 = r2
            r2 = 8
            if (r7 != r2) goto L_0x016c
        L_0x0162:
            r22 = r9
            r23 = r10
            r24 = r11
            r25 = r12
            goto L_0x01ec
        L_0x016c:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r13.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r7) goto L_0x0162
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r13.dimension
            boolean r7 = r2.resolved
            if (r7 != 0) goto L_0x0162
            int r7 = (r17 > r27 ? 1 : (r17 == r27 ? 0 : -1))
            if (r7 <= 0) goto L_0x018f
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r13.widget
            float[] r7 = r7.mWeight
            r22 = r7
            int r7 = r0.orientation
            r7 = r22[r7]
            float r7 = r7 * r9
            float r7 = r7 / r17
            float r7 = r7 + r16
            int r7 = (int) r7
        L_0x018c:
            r22 = r9
            goto L_0x0191
        L_0x018f:
            r7 = r10
            goto L_0x018c
        L_0x0191:
            int r9 = r0.orientation
            if (r9 != 0) goto L_0x01bb
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r13.widget
            r23 = r10
            int r10 = r9.mMatchConstraintMaxWidth
            int r9 = r9.mMatchConstraintMinWidth
            r24 = r11
            int r11 = r13.matchConstraintsType
            r25 = r12
            r12 = 1
            if (r11 != r12) goto L_0x01ad
            int r2 = r2.wrapValue
            int r2 = java.lang.Math.min(r7, r2)
            goto L_0x01ae
        L_0x01ad:
            r2 = r7
        L_0x01ae:
            int r2 = java.lang.Math.max(r9, r2)
            if (r10 <= 0) goto L_0x01b8
            int r2 = java.lang.Math.min(r10, r2)
        L_0x01b8:
            if (r2 == r7) goto L_0x01e4
            goto L_0x01e0
        L_0x01bb:
            r23 = r10
            r24 = r11
            r25 = r12
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r13.widget
            int r10 = r9.mMatchConstraintMaxHeight
            int r9 = r9.mMatchConstraintMinHeight
            int r11 = r13.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x01d3
            int r2 = r2.wrapValue
            int r2 = java.lang.Math.min(r7, r2)
            goto L_0x01d4
        L_0x01d3:
            r2 = r7
        L_0x01d4:
            int r2 = java.lang.Math.max(r9, r2)
            if (r10 <= 0) goto L_0x01de
            int r2 = java.lang.Math.min(r10, r2)
        L_0x01de:
            if (r2 == r7) goto L_0x01e4
        L_0x01e0:
            int r12 = r25 + 1
            r7 = r2
            goto L_0x01e6
        L_0x01e4:
            r12 = r25
        L_0x01e6:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r2 = r13.dimension
            r2.resolve(r7)
            goto L_0x01ee
        L_0x01ec:
            r12 = r25
        L_0x01ee:
            int r11 = r24 + 1
            r7 = r16
            r2 = r21
            r9 = r22
            r10 = r23
            goto L_0x014a
        L_0x01fa:
            r21 = r2
            r16 = r7
            r25 = r12
            if (r25 <= 0) goto L_0x0236
            int r15 = r15 - r25
            r2 = 0
            r14 = 0
        L_0x0206:
            if (r2 >= r4) goto L_0x0236
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r7 = r0.widgets
            java.lang.Object r7 = r7.get(r2)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r7.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x021b
            goto L_0x0233
        L_0x021b:
            if (r2 <= 0) goto L_0x0224
            if (r2 < r5) goto L_0x0224
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r14 = r14 + r9
        L_0x0224:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r9 = r9.value
            int r14 = r14 + r9
            if (r2 >= r8) goto L_0x0233
            if (r2 >= r6) goto L_0x0233
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r14 = r14 + r7
        L_0x0233:
            int r2 = r2 + 1
            goto L_0x0206
        L_0x0236:
            int r2 = r0.chainStyle
            r7 = 2
            if (r2 != r7) goto L_0x0241
            if (r25 != 0) goto L_0x0241
            r2 = 0
            r0.chainStyle = r2
            goto L_0x0249
        L_0x0241:
            r2 = 0
            goto L_0x0249
        L_0x0243:
            r21 = r2
            r16 = r7
            r2 = 0
            r7 = 2
        L_0x0249:
            if (r14 <= r3) goto L_0x024d
            r0.chainStyle = r7
        L_0x024d:
            if (r1 <= 0) goto L_0x0255
            if (r15 != 0) goto L_0x0255
            if (r5 != r6) goto L_0x0255
            r0.chainStyle = r7
        L_0x0255:
            int r7 = r0.chainStyle
            r12 = 1
            if (r7 != r12) goto L_0x02f8
            if (r1 <= r12) goto L_0x0260
            int r3 = r3 - r14
            int r1 = r1 - r12
            int r3 = r3 / r1
            goto L_0x0269
        L_0x0260:
            if (r1 != r12) goto L_0x0268
            int r3 = r3 - r14
            r18 = 2
            int r3 = r3 / 2
            goto L_0x0269
        L_0x0268:
            r3 = r2
        L_0x0269:
            if (r15 <= 0) goto L_0x026c
            r3 = r2
        L_0x026c:
            r1 = r21
        L_0x026e:
            if (r2 >= r4) goto L_0x043b
            if (r20 == 0) goto L_0x0277
            int r7 = r2 + 1
            int r7 = r4 - r7
            goto L_0x0278
        L_0x0277:
            r7 = r2
        L_0x0278:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r7 = r9.get(r7)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r7.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x0295
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r1)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r7.end
            r7.resolve(r1)
            goto L_0x02f4
        L_0x0295:
            if (r2 <= 0) goto L_0x029c
            if (r20 == 0) goto L_0x029b
            int r1 = r1 - r3
            goto L_0x029c
        L_0x029b:
            int r1 = r1 + r3
        L_0x029c:
            if (r2 <= 0) goto L_0x02ad
            if (r2 < r5) goto L_0x02ad
            if (r20 == 0) goto L_0x02a8
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r1 = r1 - r9
            goto L_0x02ad
        L_0x02a8:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r1 = r1 + r9
        L_0x02ad:
            if (r20 == 0) goto L_0x02b5
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r1)
            goto L_0x02ba
        L_0x02b5:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r1)
        L_0x02ba:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r10 = r9.value
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = r7.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x02cb
            int r11 = r7.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x02cb
            int r10 = r9.wrapValue
        L_0x02cb:
            if (r20 == 0) goto L_0x02cf
            int r1 = r1 - r10
            goto L_0x02d0
        L_0x02cf:
            int r1 = r1 + r10
        L_0x02d0:
            if (r20 == 0) goto L_0x02d9
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r1)
        L_0x02d7:
            r12 = 1
            goto L_0x02df
        L_0x02d9:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r1)
            goto L_0x02d7
        L_0x02df:
            r7.resolved = r12
            if (r2 >= r8) goto L_0x02f4
            if (r2 >= r6) goto L_0x02f4
            if (r20 == 0) goto L_0x02ee
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r1 = r1 - r7
            goto L_0x02f4
        L_0x02ee:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r1 = r1 + r7
        L_0x02f4:
            int r2 = r2 + 1
            goto L_0x026e
        L_0x02f8:
            if (r7 != 0) goto L_0x038d
            int r3 = r3 - r14
            r19 = 1
            int r1 = r1 + 1
            int r3 = r3 / r1
            if (r15 <= 0) goto L_0x0303
            r3 = r2
        L_0x0303:
            r1 = r21
        L_0x0305:
            if (r2 >= r4) goto L_0x043b
            if (r20 == 0) goto L_0x030e
            int r7 = r2 + 1
            int r7 = r4 - r7
            goto L_0x030f
        L_0x030e:
            r7 = r2
        L_0x030f:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r7 = r9.get(r7)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r7.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x032c
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r1)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r7.end
            r7.resolve(r1)
            goto L_0x0389
        L_0x032c:
            if (r20 == 0) goto L_0x0330
            int r1 = r1 - r3
            goto L_0x0331
        L_0x0330:
            int r1 = r1 + r3
        L_0x0331:
            if (r2 <= 0) goto L_0x0342
            if (r2 < r5) goto L_0x0342
            if (r20 == 0) goto L_0x033d
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r1 = r1 - r9
            goto L_0x0342
        L_0x033d:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r1 = r1 + r9
        L_0x0342:
            if (r20 == 0) goto L_0x034a
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r1)
            goto L_0x034f
        L_0x034a:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r1)
        L_0x034f:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r10 = r9.value
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = r7.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0364
            int r11 = r7.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x0364
            int r9 = r9.wrapValue
            int r10 = java.lang.Math.min(r10, r9)
        L_0x0364:
            if (r20 == 0) goto L_0x0368
            int r1 = r1 - r10
            goto L_0x0369
        L_0x0368:
            int r1 = r1 + r10
        L_0x0369:
            if (r20 == 0) goto L_0x0371
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r1)
            goto L_0x0376
        L_0x0371:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r1)
        L_0x0376:
            if (r2 >= r8) goto L_0x0389
            if (r2 >= r6) goto L_0x0389
            if (r20 == 0) goto L_0x0383
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r1 = r1 - r7
            goto L_0x0389
        L_0x0383:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r1 = r1 + r7
        L_0x0389:
            int r2 = r2 + 1
            goto L_0x0305
        L_0x038d:
            r1 = 2
            if (r7 != r1) goto L_0x043b
            int r1 = r0.orientation
            if (r1 != 0) goto L_0x039b
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r0.widget
            float r1 = r1.getHorizontalBiasPercent()
            goto L_0x03a1
        L_0x039b:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r0.widget
            float r1 = r1.getVerticalBiasPercent()
        L_0x03a1:
            if (r20 == 0) goto L_0x03a7
            r7 = 1065353216(0x3f800000, float:1.0)
            float r1 = r7 - r1
        L_0x03a7:
            int r3 = r3 - r14
            float r3 = (float) r3
            float r3 = r3 * r1
            float r3 = r3 + r16
            int r1 = (int) r3
            if (r1 < 0) goto L_0x03b1
            if (r15 <= 0) goto L_0x03b2
        L_0x03b1:
            r1 = r2
        L_0x03b2:
            if (r20 == 0) goto L_0x03b7
            int r1 = r21 - r1
            goto L_0x03b9
        L_0x03b7:
            int r1 = r21 + r1
        L_0x03b9:
            if (r2 >= r4) goto L_0x043b
            if (r20 == 0) goto L_0x03c2
            int r3 = r2 + 1
            int r3 = r4 - r3
            goto L_0x03c3
        L_0x03c2:
            r3 = r2
        L_0x03c3:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r7 = r0.widgets
            java.lang.Object r3 = r7.get(r3)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r3 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r3
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r3.widget
            int r7 = r7.getVisibility()
            r10 = 8
            if (r7 != r10) goto L_0x03e1
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r3.start
            r7.resolve(r1)
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r3.end
            r3.resolve(r1)
            r12 = 1
            goto L_0x0437
        L_0x03e1:
            if (r2 <= 0) goto L_0x03f2
            if (r2 < r5) goto L_0x03f2
            if (r20 == 0) goto L_0x03ed
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r3.start
            int r7 = r7.margin
            int r1 = r1 - r7
            goto L_0x03f2
        L_0x03ed:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r3.start
            int r7 = r7.margin
            int r1 = r1 + r7
        L_0x03f2:
            if (r20 == 0) goto L_0x03fa
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r3.end
            r7.resolve(r1)
            goto L_0x03ff
        L_0x03fa:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r3.start
            r7.resolve(r1)
        L_0x03ff:
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r7 = r3.dimension
            int r9 = r7.value
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = r3.dimensionBehavior
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0411
            int r11 = r3.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x0412
            int r9 = r7.wrapValue
            goto L_0x0412
        L_0x0411:
            r12 = 1
        L_0x0412:
            if (r20 == 0) goto L_0x0416
            int r1 = r1 - r9
            goto L_0x0417
        L_0x0416:
            int r1 = r1 + r9
        L_0x0417:
            if (r20 == 0) goto L_0x041f
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r3.start
            r7.resolve(r1)
            goto L_0x0424
        L_0x041f:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r7 = r3.end
            r7.resolve(r1)
        L_0x0424:
            if (r2 >= r8) goto L_0x0437
            if (r2 >= r6) goto L_0x0437
            if (r20 == 0) goto L_0x0431
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.margin
            int r3 = -r3
            int r1 = r1 - r3
            goto L_0x0437
        L_0x0431:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.margin
            int r3 = -r3
            int r1 = r1 + r3
        L_0x0437:
            int r2 = r2 + 1
            goto L_0x03b9
        L_0x043b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.ChainRun.update(androidx.constraintlayout.solver.widgets.analyzer.Dependency):void");
    }
}
