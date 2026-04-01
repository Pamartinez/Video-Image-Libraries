package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConstraintSet {
    /* access modifiers changed from: private */
    public static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private static SparseIntArray mapToConstant;
    private HashMap<Integer, Constraint> mConstraints = new HashMap<>();
    private boolean mForceId = true;
    private HashMap<String, ConstraintAttribute> mSavedAttributes = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Constraint {
        public final Layout layout = new Layout();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();
        int mViewId;
        public final Motion motion = new Motion();
        public final PropertySet propertySet = new PropertySet();
        public final Transform transform = new Transform();

        /* access modifiers changed from: private */
        public void fillFrom(int i2, ConstraintLayout.LayoutParams layoutParams) {
            this.mViewId = i2;
            Layout layout2 = this.layout;
            layout2.leftToLeft = layoutParams.leftToLeft;
            layout2.leftToRight = layoutParams.leftToRight;
            layout2.rightToLeft = layoutParams.rightToLeft;
            layout2.rightToRight = layoutParams.rightToRight;
            layout2.topToTop = layoutParams.topToTop;
            layout2.topToBottom = layoutParams.topToBottom;
            layout2.bottomToTop = layoutParams.bottomToTop;
            layout2.bottomToBottom = layoutParams.bottomToBottom;
            layout2.baselineToBaseline = layoutParams.baselineToBaseline;
            layout2.startToEnd = layoutParams.startToEnd;
            layout2.startToStart = layoutParams.startToStart;
            layout2.endToStart = layoutParams.endToStart;
            layout2.endToEnd = layoutParams.endToEnd;
            layout2.horizontalBias = layoutParams.horizontalBias;
            layout2.verticalBias = layoutParams.verticalBias;
            layout2.dimensionRatio = layoutParams.dimensionRatio;
            layout2.circleConstraint = layoutParams.circleConstraint;
            layout2.circleRadius = layoutParams.circleRadius;
            layout2.circleAngle = layoutParams.circleAngle;
            layout2.editorAbsoluteX = layoutParams.editorAbsoluteX;
            layout2.editorAbsoluteY = layoutParams.editorAbsoluteY;
            layout2.orientation = layoutParams.orientation;
            layout2.guidePercent = layoutParams.guidePercent;
            layout2.guideBegin = layoutParams.guideBegin;
            layout2.guideEnd = layoutParams.guideEnd;
            layout2.mWidth = layoutParams.width;
            layout2.mHeight = layoutParams.height;
            layout2.leftMargin = layoutParams.leftMargin;
            layout2.rightMargin = layoutParams.rightMargin;
            layout2.topMargin = layoutParams.topMargin;
            layout2.bottomMargin = layoutParams.bottomMargin;
            layout2.verticalWeight = layoutParams.verticalWeight;
            layout2.horizontalWeight = layoutParams.horizontalWeight;
            layout2.verticalChainStyle = layoutParams.verticalChainStyle;
            layout2.horizontalChainStyle = layoutParams.horizontalChainStyle;
            layout2.constrainedWidth = layoutParams.constrainedWidth;
            layout2.constrainedHeight = layoutParams.constrainedHeight;
            layout2.widthDefault = layoutParams.matchConstraintDefaultWidth;
            layout2.heightDefault = layoutParams.matchConstraintDefaultHeight;
            layout2.widthMax = layoutParams.matchConstraintMaxWidth;
            layout2.heightMax = layoutParams.matchConstraintMaxHeight;
            layout2.widthMin = layoutParams.matchConstraintMinWidth;
            layout2.heightMin = layoutParams.matchConstraintMinHeight;
            layout2.widthPercent = layoutParams.matchConstraintPercentWidth;
            layout2.heightPercent = layoutParams.matchConstraintPercentHeight;
            layout2.mConstraintTag = layoutParams.constraintTag;
            layout2.goneTopMargin = layoutParams.goneTopMargin;
            layout2.goneBottomMargin = layoutParams.goneBottomMargin;
            layout2.goneLeftMargin = layoutParams.goneLeftMargin;
            layout2.goneRightMargin = layoutParams.goneRightMargin;
            layout2.goneStartMargin = layoutParams.goneStartMargin;
            layout2.goneEndMargin = layoutParams.goneEndMargin;
            layout2.endMargin = layoutParams.getMarginEnd();
            this.layout.startMargin = layoutParams.getMarginStart();
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout2 = this.layout;
            layoutParams.leftToLeft = layout2.leftToLeft;
            layoutParams.leftToRight = layout2.leftToRight;
            layoutParams.rightToLeft = layout2.rightToLeft;
            layoutParams.rightToRight = layout2.rightToRight;
            layoutParams.topToTop = layout2.topToTop;
            layoutParams.topToBottom = layout2.topToBottom;
            layoutParams.bottomToTop = layout2.bottomToTop;
            layoutParams.bottomToBottom = layout2.bottomToBottom;
            layoutParams.baselineToBaseline = layout2.baselineToBaseline;
            layoutParams.startToEnd = layout2.startToEnd;
            layoutParams.startToStart = layout2.startToStart;
            layoutParams.endToStart = layout2.endToStart;
            layoutParams.endToEnd = layout2.endToEnd;
            layoutParams.leftMargin = layout2.leftMargin;
            layoutParams.rightMargin = layout2.rightMargin;
            layoutParams.topMargin = layout2.topMargin;
            layoutParams.bottomMargin = layout2.bottomMargin;
            layoutParams.goneStartMargin = layout2.goneStartMargin;
            layoutParams.goneEndMargin = layout2.goneEndMargin;
            layoutParams.goneTopMargin = layout2.goneTopMargin;
            layoutParams.goneBottomMargin = layout2.goneBottomMargin;
            layoutParams.horizontalBias = layout2.horizontalBias;
            layoutParams.verticalBias = layout2.verticalBias;
            layoutParams.circleConstraint = layout2.circleConstraint;
            layoutParams.circleRadius = layout2.circleRadius;
            layoutParams.circleAngle = layout2.circleAngle;
            layoutParams.dimensionRatio = layout2.dimensionRatio;
            layoutParams.editorAbsoluteX = layout2.editorAbsoluteX;
            layoutParams.editorAbsoluteY = layout2.editorAbsoluteY;
            layoutParams.verticalWeight = layout2.verticalWeight;
            layoutParams.horizontalWeight = layout2.horizontalWeight;
            layoutParams.verticalChainStyle = layout2.verticalChainStyle;
            layoutParams.horizontalChainStyle = layout2.horizontalChainStyle;
            layoutParams.constrainedWidth = layout2.constrainedWidth;
            layoutParams.constrainedHeight = layout2.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = layout2.widthDefault;
            layoutParams.matchConstraintDefaultHeight = layout2.heightDefault;
            layoutParams.matchConstraintMaxWidth = layout2.widthMax;
            layoutParams.matchConstraintMaxHeight = layout2.heightMax;
            layoutParams.matchConstraintMinWidth = layout2.widthMin;
            layoutParams.matchConstraintMinHeight = layout2.heightMin;
            layoutParams.matchConstraintPercentWidth = layout2.widthPercent;
            layoutParams.matchConstraintPercentHeight = layout2.heightPercent;
            layoutParams.orientation = layout2.orientation;
            layoutParams.guidePercent = layout2.guidePercent;
            layoutParams.guideBegin = layout2.guideBegin;
            layoutParams.guideEnd = layout2.guideEnd;
            layoutParams.width = layout2.mWidth;
            layoutParams.height = layout2.mHeight;
            String str = layout2.mConstraintTag;
            if (str != null) {
                layoutParams.constraintTag = str;
            }
            layoutParams.setMarginStart(layout2.startMargin);
            layoutParams.setMarginEnd(this.layout.endMargin);
            layoutParams.validate();
        }

        public Constraint clone() {
            Constraint constraint = new Constraint();
            constraint.layout.copyFrom(this.layout);
            constraint.motion.copyFrom(this.motion);
            constraint.propertySet.copyFrom(this.propertySet);
            constraint.transform.copyFrom(this.transform);
            constraint.mViewId = this.mViewId;
            return constraint;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Layout {
        private static SparseIntArray mapToConstant;
        public int baselineToBaseline = -1;
        public int bottomMargin = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String dimensionRatio = null;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endMargin = -1;
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
        public int heightDefault = 0;
        public int heightMax = -1;
        public int heightMin = -1;
        public float heightPercent = 1.0f;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        public float horizontalWeight = -1.0f;
        public int leftMargin = -1;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public boolean mApply = false;
        public boolean mBarrierAllowsGoneWidgets = true;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public String mConstraintTag;
        public int mHeight;
        public int mHelperType = -1;
        public boolean mIsGuideline = false;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public int orientation = -1;
        public int rightMargin = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startMargin = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topMargin = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        public float verticalWeight = -1.0f;
        public int widthDefault = 0;
        public int widthMax = -1;
        public int widthMin = -1;
        public float widthPercent = 1.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            mapToConstant.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
            mapToConstant.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
            mapToConstant.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
            mapToConstant.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
            mapToConstant.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
            mapToConstant.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
            mapToConstant.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            mapToConstant.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            mapToConstant.append(R$styleable.Layout_layout_editor_absoluteX, 6);
            mapToConstant.append(R$styleable.Layout_layout_editor_absoluteY, 7);
            mapToConstant.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
            mapToConstant.append(R$styleable.Layout_layout_constraintGuide_end, 18);
            mapToConstant.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
            mapToConstant.append(R$styleable.Layout_android_orientation, 26);
            mapToConstant.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
            mapToConstant.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
            mapToConstant.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
            mapToConstant.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
            mapToConstant.append(R$styleable.Layout_layout_goneMarginLeft, 13);
            mapToConstant.append(R$styleable.Layout_layout_goneMarginTop, 16);
            mapToConstant.append(R$styleable.Layout_layout_goneMarginRight, 14);
            mapToConstant.append(R$styleable.Layout_layout_goneMarginBottom, 11);
            mapToConstant.append(R$styleable.Layout_layout_goneMarginStart, 15);
            mapToConstant.append(R$styleable.Layout_layout_goneMarginEnd, 12);
            mapToConstant.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
            mapToConstant.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
            mapToConstant.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            mapToConstant.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
            mapToConstant.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
            mapToConstant.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
            mapToConstant.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
            mapToConstant.append(R$styleable.Layout_layout_constraintLeft_creator, 76);
            mapToConstant.append(R$styleable.Layout_layout_constraintTop_creator, 76);
            mapToConstant.append(R$styleable.Layout_layout_constraintRight_creator, 76);
            mapToConstant.append(R$styleable.Layout_layout_constraintBottom_creator, 76);
            mapToConstant.append(R$styleable.Layout_layout_constraintBaseline_creator, 76);
            mapToConstant.append(R$styleable.Layout_android_layout_marginLeft, 23);
            mapToConstant.append(R$styleable.Layout_android_layout_marginRight, 27);
            mapToConstant.append(R$styleable.Layout_android_layout_marginStart, 30);
            mapToConstant.append(R$styleable.Layout_android_layout_marginEnd, 8);
            mapToConstant.append(R$styleable.Layout_android_layout_marginTop, 33);
            mapToConstant.append(R$styleable.Layout_android_layout_marginBottom, 2);
            mapToConstant.append(R$styleable.Layout_android_layout_width, 22);
            mapToConstant.append(R$styleable.Layout_android_layout_height, 21);
            mapToConstant.append(R$styleable.Layout_layout_constraintCircle, 61);
            mapToConstant.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
            mapToConstant.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
            mapToConstant.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
            mapToConstant.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
            mapToConstant.append(R$styleable.Layout_chainUseRtl, 71);
            mapToConstant.append(R$styleable.Layout_barrierDirection, 72);
            mapToConstant.append(R$styleable.Layout_barrierMargin, 73);
            mapToConstant.append(R$styleable.Layout_constraint_referenced_ids, 74);
            mapToConstant.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void copyFrom(Layout layout) {
            this.mIsGuideline = layout.mIsGuideline;
            this.mWidth = layout.mWidth;
            this.mApply = layout.mApply;
            this.mHeight = layout.mHeight;
            this.guideBegin = layout.guideBegin;
            this.guideEnd = layout.guideEnd;
            this.guidePercent = layout.guidePercent;
            this.leftToLeft = layout.leftToLeft;
            this.leftToRight = layout.leftToRight;
            this.rightToLeft = layout.rightToLeft;
            this.rightToRight = layout.rightToRight;
            this.topToTop = layout.topToTop;
            this.topToBottom = layout.topToBottom;
            this.bottomToTop = layout.bottomToTop;
            this.bottomToBottom = layout.bottomToBottom;
            this.baselineToBaseline = layout.baselineToBaseline;
            this.startToEnd = layout.startToEnd;
            this.startToStart = layout.startToStart;
            this.endToStart = layout.endToStart;
            this.endToEnd = layout.endToEnd;
            this.horizontalBias = layout.horizontalBias;
            this.verticalBias = layout.verticalBias;
            this.dimensionRatio = layout.dimensionRatio;
            this.circleConstraint = layout.circleConstraint;
            this.circleRadius = layout.circleRadius;
            this.circleAngle = layout.circleAngle;
            this.editorAbsoluteX = layout.editorAbsoluteX;
            this.editorAbsoluteY = layout.editorAbsoluteY;
            this.orientation = layout.orientation;
            this.leftMargin = layout.leftMargin;
            this.rightMargin = layout.rightMargin;
            this.topMargin = layout.topMargin;
            this.bottomMargin = layout.bottomMargin;
            this.endMargin = layout.endMargin;
            this.startMargin = layout.startMargin;
            this.goneLeftMargin = layout.goneLeftMargin;
            this.goneTopMargin = layout.goneTopMargin;
            this.goneRightMargin = layout.goneRightMargin;
            this.goneBottomMargin = layout.goneBottomMargin;
            this.goneEndMargin = layout.goneEndMargin;
            this.goneStartMargin = layout.goneStartMargin;
            this.verticalWeight = layout.verticalWeight;
            this.horizontalWeight = layout.horizontalWeight;
            this.horizontalChainStyle = layout.horizontalChainStyle;
            this.verticalChainStyle = layout.verticalChainStyle;
            this.widthDefault = layout.widthDefault;
            this.heightDefault = layout.heightDefault;
            this.widthMax = layout.widthMax;
            this.heightMax = layout.heightMax;
            this.widthMin = layout.widthMin;
            this.heightMin = layout.heightMin;
            this.widthPercent = layout.widthPercent;
            this.heightPercent = layout.heightPercent;
            this.mBarrierDirection = layout.mBarrierDirection;
            this.mBarrierMargin = layout.mBarrierMargin;
            this.mHelperType = layout.mHelperType;
            this.mConstraintTag = layout.mConstraintTag;
            int[] iArr = layout.mReferenceIds;
            if (iArr != null) {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.mReferenceIds = null;
            }
            this.mReferenceIdString = layout.mReferenceIdString;
            this.constrainedWidth = layout.constrainedWidth;
            this.constrainedHeight = layout.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = layout.mBarrierAllowsGoneWidgets;
        }

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                int i7 = mapToConstant.get(index);
                if (i7 == 80) {
                    this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                } else if (i7 != 81) {
                    switch (i7) {
                        case 1:
                            this.baselineToBaseline = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToBaseline);
                            break;
                        case 2:
                            this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                            break;
                        case 3:
                            this.bottomToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToBottom);
                            break;
                        case 4:
                            this.bottomToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToTop);
                            break;
                        case 5:
                            this.dimensionRatio = obtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                            break;
                        case 7:
                            this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                            break;
                        case 8:
                            this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                            break;
                        case 9:
                            this.endToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToEnd);
                            break;
                        case 10:
                            this.endToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToStart);
                            break;
                        case 11:
                            this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                            break;
                        case 12:
                            this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                            break;
                        case 13:
                            this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                            break;
                        case 14:
                            this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                            break;
                        case 15:
                            this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                            break;
                        case 16:
                            this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                            break;
                        case 17:
                            this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                            break;
                        case 18:
                            this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                            break;
                        case 19:
                            this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                            break;
                        case 20:
                            this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                            break;
                        case 21:
                            this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                            break;
                        case 22:
                            this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                            break;
                        case 23:
                            this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                            break;
                        case 24:
                            this.leftToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToLeft);
                            break;
                        case 25:
                            this.leftToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToRight);
                            break;
                        case 26:
                            this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                            break;
                        case 27:
                            this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                            break;
                        case 28:
                            this.rightToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToLeft);
                            break;
                        case 29:
                            this.rightToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToRight);
                            break;
                        case 30:
                            this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                            break;
                        case 31:
                            this.startToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToEnd);
                            break;
                        case 32:
                            this.startToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToStart);
                            break;
                        case 33:
                            this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                            break;
                        case 34:
                            this.topToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToBottom);
                            break;
                        case 35:
                            this.topToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToTop);
                            break;
                        case 36:
                            this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                            break;
                        case 37:
                            this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                            break;
                        case 38:
                            this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                            break;
                        case 39:
                            this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                            break;
                        case 40:
                            this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                            break;
                        default:
                            switch (i7) {
                                case 54:
                                    this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                    break;
                                case 55:
                                    this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                    break;
                                case 56:
                                    this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                    break;
                                case 57:
                                    this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                    break;
                                case 58:
                                    this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                    break;
                                case 59:
                                    this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                    break;
                                default:
                                    switch (i7) {
                                        case 61:
                                            this.circleConstraint = ConstraintSet.lookupID(obtainStyledAttributes, index, this.circleConstraint);
                                            break;
                                        case 62:
                                            this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                            break;
                                        case 63:
                                            this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                            break;
                                        default:
                                            switch (i7) {
                                                case 69:
                                                    this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 70:
                                                    this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    break;
                                                case 72:
                                                    this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                                    break;
                                                case 73:
                                                    this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                                    break;
                                                case 74:
                                                    this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                                    break;
                                                case 75:
                                                    this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                                    break;
                                                case 76:
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                                    break;
                                                case 77:
                                                    this.mConstraintTag = obtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                                    break;
                                            }
                                    }
                            }
                    }
                } else {
                    this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Motion {
        private static SparseIntArray mapToConstant;
        public int mAnimateRelativeTo = -1;
        public boolean mApply = false;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPathMotionArc = -1;
        public float mPathRotate = Float.NaN;
        public String mTransitionEasing = null;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R$styleable.Motion_motionPathRotate, 1);
            mapToConstant.append(R$styleable.Motion_pathMotionArc, 2);
            mapToConstant.append(R$styleable.Motion_transitionEasing, 3);
            mapToConstant.append(R$styleable.Motion_drawPath, 4);
            mapToConstant.append(R$styleable.Motion_animate_relativeTo, 5);
            mapToConstant.append(R$styleable.Motion_motionStagger, 6);
        }

        public void copyFrom(Motion motion) {
            this.mApply = motion.mApply;
            this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
            this.mTransitionEasing = motion.mTransitionEasing;
            this.mPathMotionArc = motion.mPathMotionArc;
            this.mDrawPath = motion.mDrawPath;
            this.mPathRotate = motion.mPathRotate;
            this.mMotionStagger = motion.mMotionStagger;
        }

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case 3:
                        if (obtainStyledAttributes.peekValue(index).type != 3) {
                            this.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        } else {
                            this.mTransitionEasing = obtainStyledAttributes.getString(index);
                            break;
                        }
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.lookupID(obtainStyledAttributes, index, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PropertySet {
        public float alpha = 1.0f;
        public boolean mApply = false;
        public float mProgress = Float.NaN;
        public int mVisibilityMode = 0;
        public int visibility = 0;

        public void copyFrom(PropertySet propertySet) {
            this.mApply = propertySet.mApply;
            this.visibility = propertySet.visibility;
            this.alpha = propertySet.alpha;
            this.mProgress = propertySet.mProgress;
            this.mVisibilityMode = propertySet.mVisibilityMode;
        }

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R$styleable.PropertySet_android_alpha) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == R$styleable.PropertySet_android_visibility) {
                    this.visibility = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = ConstraintSet.VISIBILITY_FLAGS[this.visibility];
                } else if (index == R$styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == R$styleable.PropertySet_motionProgress) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Transform {
        private static SparseIntArray mapToConstant;
        public boolean applyElevation = false;
        public float elevation = 0.0f;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R$styleable.Transform_android_rotation, 1);
            mapToConstant.append(R$styleable.Transform_android_rotationX, 2);
            mapToConstant.append(R$styleable.Transform_android_rotationY, 3);
            mapToConstant.append(R$styleable.Transform_android_scaleX, 4);
            mapToConstant.append(R$styleable.Transform_android_scaleY, 5);
            mapToConstant.append(R$styleable.Transform_android_transformPivotX, 6);
            mapToConstant.append(R$styleable.Transform_android_transformPivotY, 7);
            mapToConstant.append(R$styleable.Transform_android_translationX, 8);
            mapToConstant.append(R$styleable.Transform_android_translationY, 9);
            mapToConstant.append(R$styleable.Transform_android_translationZ, 10);
            mapToConstant.append(R$styleable.Transform_android_elevation, 11);
        }

        public void copyFrom(Transform transform) {
            this.mApply = transform.mApply;
            this.rotation = transform.rotation;
            this.rotationX = transform.rotationX;
            this.rotationY = transform.rotationY;
            this.scaleX = transform.scaleX;
            this.scaleY = transform.scaleY;
            this.transformPivotX = transform.transformPivotX;
            this.transformPivotY = transform.transformPivotY;
            this.translationX = transform.translationX;
            this.translationY = transform.translationY;
            this.translationZ = transform.translationZ;
            this.applyElevation = transform.applyElevation;
            this.elevation = transform.elevation;
        }

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case 9:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                        break;
                    case 11:
                        this.applyElevation = true;
                        this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        mapToConstant = sparseIntArray;
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        mapToConstant.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        mapToConstant.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        mapToConstant.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        mapToConstant.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        mapToConstant.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        mapToConstant.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        mapToConstant.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        mapToConstant.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        mapToConstant.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        mapToConstant.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        mapToConstant.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        mapToConstant.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        mapToConstant.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        mapToConstant.append(R$styleable.Constraint_android_orientation, 27);
        mapToConstant.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        mapToConstant.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        mapToConstant.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        mapToConstant.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        mapToConstant.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        mapToConstant.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        mapToConstant.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        mapToConstant.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        mapToConstant.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        mapToConstant.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        mapToConstant.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        mapToConstant.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        mapToConstant.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        mapToConstant.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        mapToConstant.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        mapToConstant.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        mapToConstant.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        mapToConstant.append(R$styleable.Constraint_layout_constraintLeft_creator, 82);
        mapToConstant.append(R$styleable.Constraint_layout_constraintTop_creator, 82);
        mapToConstant.append(R$styleable.Constraint_layout_constraintRight_creator, 82);
        mapToConstant.append(R$styleable.Constraint_layout_constraintBottom_creator, 82);
        mapToConstant.append(R$styleable.Constraint_layout_constraintBaseline_creator, 82);
        mapToConstant.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        mapToConstant.append(R$styleable.Constraint_android_layout_marginRight, 28);
        mapToConstant.append(R$styleable.Constraint_android_layout_marginStart, 31);
        mapToConstant.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        mapToConstant.append(R$styleable.Constraint_android_layout_marginTop, 34);
        mapToConstant.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        mapToConstant.append(R$styleable.Constraint_android_layout_width, 23);
        mapToConstant.append(R$styleable.Constraint_android_layout_height, 21);
        mapToConstant.append(R$styleable.Constraint_android_visibility, 22);
        mapToConstant.append(R$styleable.Constraint_android_alpha, 43);
        mapToConstant.append(R$styleable.Constraint_android_elevation, 44);
        mapToConstant.append(R$styleable.Constraint_android_rotationX, 45);
        mapToConstant.append(R$styleable.Constraint_android_rotationY, 46);
        mapToConstant.append(R$styleable.Constraint_android_rotation, 60);
        mapToConstant.append(R$styleable.Constraint_android_scaleX, 47);
        mapToConstant.append(R$styleable.Constraint_android_scaleY, 48);
        mapToConstant.append(R$styleable.Constraint_android_transformPivotX, 49);
        mapToConstant.append(R$styleable.Constraint_android_transformPivotY, 50);
        mapToConstant.append(R$styleable.Constraint_android_translationX, 51);
        mapToConstant.append(R$styleable.Constraint_android_translationY, 52);
        mapToConstant.append(R$styleable.Constraint_android_translationZ, 53);
        mapToConstant.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        mapToConstant.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        mapToConstant.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        mapToConstant.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        mapToConstant.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        mapToConstant.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        mapToConstant.append(R$styleable.Constraint_layout_constraintCircle, 61);
        mapToConstant.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        mapToConstant.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        mapToConstant.append(R$styleable.Constraint_animate_relativeTo, 64);
        mapToConstant.append(R$styleable.Constraint_transitionEasing, 65);
        mapToConstant.append(R$styleable.Constraint_drawPath, 66);
        mapToConstant.append(R$styleable.Constraint_transitionPathRotate, 67);
        mapToConstant.append(R$styleable.Constraint_motionStagger, 79);
        mapToConstant.append(R$styleable.Constraint_android_id, 38);
        mapToConstant.append(R$styleable.Constraint_motionProgress, 68);
        mapToConstant.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        mapToConstant.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        mapToConstant.append(R$styleable.Constraint_chainUseRtl, 71);
        mapToConstant.append(R$styleable.Constraint_barrierDirection, 72);
        mapToConstant.append(R$styleable.Constraint_barrierMargin, 73);
        mapToConstant.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        mapToConstant.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        mapToConstant.append(R$styleable.Constraint_pathMotionArc, 76);
        mapToConstant.append(R$styleable.Constraint_layout_constraintTag, 77);
        mapToConstant.append(R$styleable.Constraint_visibilityMode, 78);
        mapToConstant.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        mapToConstant.append(R$styleable.Constraint_layout_constrainedHeight, 81);
    }

    private int[] convertReferenceString(View view, String str) {
        int i2;
        Object designInformation;
        String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i7 = 0;
        int i8 = 0;
        while (i7 < split.length) {
            String trim = split[i7].trim();
            try {
                i2 = R$id.class.getField(trim).getInt((Object) null);
            } catch (Exception unused) {
                i2 = 0;
            }
            if (i2 == 0) {
                i2 = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i2 == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i2 = ((Integer) designInformation).intValue();
            }
            iArr[i8] = i2;
            i7++;
            i8++;
        }
        if (i8 != split.length) {
            return Arrays.copyOf(iArr, i8);
        }
        return iArr;
    }

    private Constraint fillFromAttributeList(Context context, AttributeSet attributeSet) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Constraint);
        populateConstraint(context, constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private Constraint get(int i2) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i2))) {
            this.mConstraints.put(Integer.valueOf(i2), new Constraint());
        }
        return this.mConstraints.get(Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public static int lookupID(TypedArray typedArray, int i2, int i7) {
        int resourceId = typedArray.getResourceId(i2, i7);
        if (resourceId == -1) {
            return typedArray.getInt(i2, -1);
        }
        return resourceId;
    }

    private void populateConstraint(Context context, Constraint constraint, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (!(index == R$styleable.Constraint_android_id || R$styleable.Constraint_android_layout_marginStart == index || R$styleable.Constraint_android_layout_marginEnd == index)) {
                constraint.motion.mApply = true;
                constraint.layout.mApply = true;
                constraint.propertySet.mApply = true;
                constraint.transform.mApply = true;
            }
            switch (mapToConstant.get(index)) {
                case 1:
                    Layout layout = constraint.layout;
                    layout.baselineToBaseline = lookupID(typedArray, index, layout.baselineToBaseline);
                    break;
                case 2:
                    Layout layout2 = constraint.layout;
                    layout2.bottomMargin = typedArray.getDimensionPixelSize(index, layout2.bottomMargin);
                    break;
                case 3:
                    Layout layout3 = constraint.layout;
                    layout3.bottomToBottom = lookupID(typedArray, index, layout3.bottomToBottom);
                    break;
                case 4:
                    Layout layout4 = constraint.layout;
                    layout4.bottomToTop = lookupID(typedArray, index, layout4.bottomToTop);
                    break;
                case 5:
                    constraint.layout.dimensionRatio = typedArray.getString(index);
                    break;
                case 6:
                    Layout layout5 = constraint.layout;
                    layout5.editorAbsoluteX = typedArray.getDimensionPixelOffset(index, layout5.editorAbsoluteX);
                    break;
                case 7:
                    Layout layout6 = constraint.layout;
                    layout6.editorAbsoluteY = typedArray.getDimensionPixelOffset(index, layout6.editorAbsoluteY);
                    break;
                case 8:
                    Layout layout7 = constraint.layout;
                    layout7.endMargin = typedArray.getDimensionPixelSize(index, layout7.endMargin);
                    break;
                case 9:
                    Layout layout8 = constraint.layout;
                    layout8.endToEnd = lookupID(typedArray, index, layout8.endToEnd);
                    break;
                case 10:
                    Layout layout9 = constraint.layout;
                    layout9.endToStart = lookupID(typedArray, index, layout9.endToStart);
                    break;
                case 11:
                    Layout layout10 = constraint.layout;
                    layout10.goneBottomMargin = typedArray.getDimensionPixelSize(index, layout10.goneBottomMargin);
                    break;
                case 12:
                    Layout layout11 = constraint.layout;
                    layout11.goneEndMargin = typedArray.getDimensionPixelSize(index, layout11.goneEndMargin);
                    break;
                case 13:
                    Layout layout12 = constraint.layout;
                    layout12.goneLeftMargin = typedArray.getDimensionPixelSize(index, layout12.goneLeftMargin);
                    break;
                case 14:
                    Layout layout13 = constraint.layout;
                    layout13.goneRightMargin = typedArray.getDimensionPixelSize(index, layout13.goneRightMargin);
                    break;
                case 15:
                    Layout layout14 = constraint.layout;
                    layout14.goneStartMargin = typedArray.getDimensionPixelSize(index, layout14.goneStartMargin);
                    break;
                case 16:
                    Layout layout15 = constraint.layout;
                    layout15.goneTopMargin = typedArray.getDimensionPixelSize(index, layout15.goneTopMargin);
                    break;
                case 17:
                    Layout layout16 = constraint.layout;
                    layout16.guideBegin = typedArray.getDimensionPixelOffset(index, layout16.guideBegin);
                    break;
                case 18:
                    Layout layout17 = constraint.layout;
                    layout17.guideEnd = typedArray.getDimensionPixelOffset(index, layout17.guideEnd);
                    break;
                case 19:
                    Layout layout18 = constraint.layout;
                    layout18.guidePercent = typedArray.getFloat(index, layout18.guidePercent);
                    break;
                case 20:
                    Layout layout19 = constraint.layout;
                    layout19.horizontalBias = typedArray.getFloat(index, layout19.horizontalBias);
                    break;
                case 21:
                    Layout layout20 = constraint.layout;
                    layout20.mHeight = typedArray.getLayoutDimension(index, layout20.mHeight);
                    break;
                case 22:
                    PropertySet propertySet = constraint.propertySet;
                    propertySet.visibility = typedArray.getInt(index, propertySet.visibility);
                    PropertySet propertySet2 = constraint.propertySet;
                    propertySet2.visibility = VISIBILITY_FLAGS[propertySet2.visibility];
                    break;
                case 23:
                    Layout layout21 = constraint.layout;
                    layout21.mWidth = typedArray.getLayoutDimension(index, layout21.mWidth);
                    break;
                case 24:
                    Layout layout22 = constraint.layout;
                    layout22.leftMargin = typedArray.getDimensionPixelSize(index, layout22.leftMargin);
                    break;
                case 25:
                    Layout layout23 = constraint.layout;
                    layout23.leftToLeft = lookupID(typedArray, index, layout23.leftToLeft);
                    break;
                case 26:
                    Layout layout24 = constraint.layout;
                    layout24.leftToRight = lookupID(typedArray, index, layout24.leftToRight);
                    break;
                case 27:
                    Layout layout25 = constraint.layout;
                    layout25.orientation = typedArray.getInt(index, layout25.orientation);
                    break;
                case 28:
                    Layout layout26 = constraint.layout;
                    layout26.rightMargin = typedArray.getDimensionPixelSize(index, layout26.rightMargin);
                    break;
                case 29:
                    Layout layout27 = constraint.layout;
                    layout27.rightToLeft = lookupID(typedArray, index, layout27.rightToLeft);
                    break;
                case 30:
                    Layout layout28 = constraint.layout;
                    layout28.rightToRight = lookupID(typedArray, index, layout28.rightToRight);
                    break;
                case 31:
                    Layout layout29 = constraint.layout;
                    layout29.startMargin = typedArray.getDimensionPixelSize(index, layout29.startMargin);
                    break;
                case 32:
                    Layout layout30 = constraint.layout;
                    layout30.startToEnd = lookupID(typedArray, index, layout30.startToEnd);
                    break;
                case 33:
                    Layout layout31 = constraint.layout;
                    layout31.startToStart = lookupID(typedArray, index, layout31.startToStart);
                    break;
                case 34:
                    Layout layout32 = constraint.layout;
                    layout32.topMargin = typedArray.getDimensionPixelSize(index, layout32.topMargin);
                    break;
                case 35:
                    Layout layout33 = constraint.layout;
                    layout33.topToBottom = lookupID(typedArray, index, layout33.topToBottom);
                    break;
                case 36:
                    Layout layout34 = constraint.layout;
                    layout34.topToTop = lookupID(typedArray, index, layout34.topToTop);
                    break;
                case 37:
                    Layout layout35 = constraint.layout;
                    layout35.verticalBias = typedArray.getFloat(index, layout35.verticalBias);
                    break;
                case 38:
                    constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                    break;
                case 39:
                    Layout layout36 = constraint.layout;
                    layout36.horizontalWeight = typedArray.getFloat(index, layout36.horizontalWeight);
                    break;
                case 40:
                    Layout layout37 = constraint.layout;
                    layout37.verticalWeight = typedArray.getFloat(index, layout37.verticalWeight);
                    break;
                case 41:
                    Layout layout38 = constraint.layout;
                    layout38.horizontalChainStyle = typedArray.getInt(index, layout38.horizontalChainStyle);
                    break;
                case 42:
                    Layout layout39 = constraint.layout;
                    layout39.verticalChainStyle = typedArray.getInt(index, layout39.verticalChainStyle);
                    break;
                case 43:
                    PropertySet propertySet3 = constraint.propertySet;
                    propertySet3.alpha = typedArray.getFloat(index, propertySet3.alpha);
                    break;
                case 44:
                    Transform transform = constraint.transform;
                    transform.applyElevation = true;
                    transform.elevation = typedArray.getDimension(index, transform.elevation);
                    break;
                case 45:
                    Transform transform2 = constraint.transform;
                    transform2.rotationX = typedArray.getFloat(index, transform2.rotationX);
                    break;
                case 46:
                    Transform transform3 = constraint.transform;
                    transform3.rotationY = typedArray.getFloat(index, transform3.rotationY);
                    break;
                case 47:
                    Transform transform4 = constraint.transform;
                    transform4.scaleX = typedArray.getFloat(index, transform4.scaleX);
                    break;
                case 48:
                    Transform transform5 = constraint.transform;
                    transform5.scaleY = typedArray.getFloat(index, transform5.scaleY);
                    break;
                case 49:
                    Transform transform6 = constraint.transform;
                    transform6.transformPivotX = typedArray.getDimension(index, transform6.transformPivotX);
                    break;
                case 50:
                    Transform transform7 = constraint.transform;
                    transform7.transformPivotY = typedArray.getDimension(index, transform7.transformPivotY);
                    break;
                case 51:
                    Transform transform8 = constraint.transform;
                    transform8.translationX = typedArray.getDimension(index, transform8.translationX);
                    break;
                case 52:
                    Transform transform9 = constraint.transform;
                    transform9.translationY = typedArray.getDimension(index, transform9.translationY);
                    break;
                case 53:
                    Transform transform10 = constraint.transform;
                    transform10.translationZ = typedArray.getDimension(index, transform10.translationZ);
                    break;
                case 54:
                    Layout layout40 = constraint.layout;
                    layout40.widthDefault = typedArray.getInt(index, layout40.widthDefault);
                    break;
                case 55:
                    Layout layout41 = constraint.layout;
                    layout41.heightDefault = typedArray.getInt(index, layout41.heightDefault);
                    break;
                case 56:
                    Layout layout42 = constraint.layout;
                    layout42.widthMax = typedArray.getDimensionPixelSize(index, layout42.widthMax);
                    break;
                case 57:
                    Layout layout43 = constraint.layout;
                    layout43.heightMax = typedArray.getDimensionPixelSize(index, layout43.heightMax);
                    break;
                case 58:
                    Layout layout44 = constraint.layout;
                    layout44.widthMin = typedArray.getDimensionPixelSize(index, layout44.widthMin);
                    break;
                case 59:
                    Layout layout45 = constraint.layout;
                    layout45.heightMin = typedArray.getDimensionPixelSize(index, layout45.heightMin);
                    break;
                case 60:
                    Transform transform11 = constraint.transform;
                    transform11.rotation = typedArray.getFloat(index, transform11.rotation);
                    break;
                case 61:
                    Layout layout46 = constraint.layout;
                    layout46.circleConstraint = lookupID(typedArray, index, layout46.circleConstraint);
                    break;
                case 62:
                    Layout layout47 = constraint.layout;
                    layout47.circleRadius = typedArray.getDimensionPixelSize(index, layout47.circleRadius);
                    break;
                case 63:
                    Layout layout48 = constraint.layout;
                    layout48.circleAngle = typedArray.getFloat(index, layout48.circleAngle);
                    break;
                case 64:
                    Motion motion = constraint.motion;
                    motion.mAnimateRelativeTo = lookupID(typedArray, index, motion.mAnimateRelativeTo);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type != 3) {
                        constraint.motion.mTransitionEasing = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                        break;
                    } else {
                        constraint.motion.mTransitionEasing = typedArray.getString(index);
                        break;
                    }
                case 66:
                    constraint.motion.mDrawPath = typedArray.getInt(index, 0);
                    break;
                case 67:
                    Motion motion2 = constraint.motion;
                    motion2.mPathRotate = typedArray.getFloat(index, motion2.mPathRotate);
                    break;
                case 68:
                    PropertySet propertySet4 = constraint.propertySet;
                    propertySet4.mProgress = typedArray.getFloat(index, propertySet4.mProgress);
                    break;
                case 69:
                    constraint.layout.widthPercent = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    constraint.layout.heightPercent = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    Layout layout49 = constraint.layout;
                    layout49.mBarrierDirection = typedArray.getInt(index, layout49.mBarrierDirection);
                    break;
                case 73:
                    Layout layout50 = constraint.layout;
                    layout50.mBarrierMargin = typedArray.getDimensionPixelSize(index, layout50.mBarrierMargin);
                    break;
                case 74:
                    constraint.layout.mReferenceIdString = typedArray.getString(index);
                    break;
                case 75:
                    Layout layout51 = constraint.layout;
                    layout51.mBarrierAllowsGoneWidgets = typedArray.getBoolean(index, layout51.mBarrierAllowsGoneWidgets);
                    break;
                case 76:
                    Motion motion3 = constraint.motion;
                    motion3.mPathMotionArc = typedArray.getInt(index, motion3.mPathMotionArc);
                    break;
                case 77:
                    constraint.layout.mConstraintTag = typedArray.getString(index);
                    break;
                case 78:
                    PropertySet propertySet5 = constraint.propertySet;
                    propertySet5.mVisibilityMode = typedArray.getInt(index, propertySet5.mVisibilityMode);
                    break;
                case 79:
                    Motion motion4 = constraint.motion;
                    motion4.mMotionStagger = typedArray.getFloat(index, motion4.mMotionStagger);
                    break;
                case 80:
                    Layout layout52 = constraint.layout;
                    layout52.constrainedWidth = typedArray.getBoolean(index, layout52.constrainedWidth);
                    break;
                case 81:
                    Layout layout53 = constraint.layout;
                    layout53.constrainedHeight = typedArray.getBoolean(index, layout53.constrainedHeight);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                    break;
            }
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        applyToInternal(constraintLayout, true);
        constraintLayout.setConstraintSet((ConstraintSet) null);
        constraintLayout.requestLayout();
    }

    public void applyToInternal(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.mConstraints.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(childAt));
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else if (id != -1) {
                if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                    if (childAt instanceof Barrier) {
                        constraint.layout.mHelperType = 1;
                    }
                    int i7 = constraint.layout.mHelperType;
                    if (i7 != -1 && i7 == 1) {
                        Barrier barrier = (Barrier) childAt;
                        barrier.setId(id);
                        barrier.setType(constraint.layout.mBarrierDirection);
                        barrier.setMargin(constraint.layout.mBarrierMargin);
                        barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
                        Layout layout = constraint.layout;
                        int[] iArr = layout.mReferenceIds;
                        if (iArr != null) {
                            barrier.setReferencedIds(iArr);
                        } else {
                            String str = layout.mReferenceIdString;
                            if (str != null) {
                                layout.mReferenceIds = convertReferenceString(barrier, str);
                                barrier.setReferencedIds(constraint.layout.mReferenceIds);
                            }
                        }
                    }
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                    layoutParams.validate();
                    constraint.applyTo(layoutParams);
                    if (z) {
                        ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                    }
                    childAt.setLayoutParams(layoutParams);
                    PropertySet propertySet = constraint.propertySet;
                    if (propertySet.mVisibilityMode == 0) {
                        childAt.setVisibility(propertySet.visibility);
                    }
                    childAt.setAlpha(constraint.propertySet.alpha);
                    childAt.setRotation(constraint.transform.rotation);
                    childAt.setRotationX(constraint.transform.rotationX);
                    childAt.setRotationY(constraint.transform.rotationY);
                    childAt.setScaleX(constraint.transform.scaleX);
                    childAt.setScaleY(constraint.transform.scaleY);
                    if (!Float.isNaN(constraint.transform.transformPivotX)) {
                        childAt.setPivotX(constraint.transform.transformPivotX);
                    }
                    if (!Float.isNaN(constraint.transform.transformPivotY)) {
                        childAt.setPivotY(constraint.transform.transformPivotY);
                    }
                    childAt.setTranslationX(constraint.transform.translationX);
                    childAt.setTranslationY(constraint.transform.translationY);
                    childAt.setTranslationZ(constraint.transform.translationZ);
                    Transform transform = constraint.transform;
                    if (transform.applyElevation) {
                        childAt.setElevation(transform.elevation);
                    }
                } else {
                    Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = this.mConstraints.get(num);
            int i8 = constraint2.layout.mHelperType;
            if (i8 != -1 && i8 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                Layout layout2 = constraint2.layout;
                int[] iArr2 = layout2.mReferenceIds;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = layout2.mReferenceIdString;
                    if (str2 != null) {
                        layout2.mReferenceIds = convertReferenceString(barrier2, str2);
                        barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                    }
                }
                barrier2.setType(constraint2.layout.mBarrierDirection);
                barrier2.setMargin(constraint2.layout.mBarrierMargin);
                ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.validateParams();
                constraint2.applyTo(generateDefaultLayoutParams);
                constraintLayout.addView(barrier2, generateDefaultLayoutParams);
            }
            if (constraint2.layout.mIsGuideline) {
                Guideline guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                constraint2.applyTo(generateDefaultLayoutParams2);
                constraintLayout.addView(guideline, generateDefaultLayoutParams2);
            }
        }
    }

    public void clone(Context context, int i2) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null));
    }

    public void constrainCircle(int i2, int i7, int i8, float f) {
        Layout layout = get(i2).layout;
        layout.circleConstraint = i7;
        layout.circleRadius = i8;
        layout.circleAngle = f;
    }

    public void load(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    Constraint fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        fillFromAttributeList.layout.mIsGuideline = true;
                    }
                    this.mConstraints.put(Integer.valueOf(fillFromAttributeList.mViewId), fillFromAttributeList);
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e7) {
            e7.printStackTrace();
        }
    }

    public void clone(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.mConstraints.clear();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (!this.mForceId || id != -1) {
                if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                    this.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                constraint.mCustomConstraints = ConstraintAttribute.extractAttributes(this.mSavedAttributes, childAt);
                constraint.fillFrom(id, layoutParams);
                constraint.propertySet.visibility = childAt.getVisibility();
                constraint.propertySet.alpha = childAt.getAlpha();
                constraint.transform.rotation = childAt.getRotation();
                constraint.transform.rotationX = childAt.getRotationX();
                constraint.transform.rotationY = childAt.getRotationY();
                constraint.transform.scaleX = childAt.getScaleX();
                constraint.transform.scaleY = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (!(((double) pivotX) == MapUtil.INVALID_LOCATION && ((double) pivotY) == MapUtil.INVALID_LOCATION)) {
                    Transform transform = constraint.transform;
                    transform.transformPivotX = pivotX;
                    transform.transformPivotY = pivotY;
                }
                constraint.transform.translationX = childAt.getTranslationX();
                constraint.transform.translationY = childAt.getTranslationY();
                constraint.transform.translationZ = childAt.getTranslationZ();
                Transform transform2 = constraint.transform;
                if (transform2.applyElevation) {
                    transform2.elevation = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    constraint.layout.mBarrierAllowsGoneWidgets = barrier.allowsGoneWidget();
                    constraint.layout.mReferenceIds = barrier.getReferencedIds();
                    constraint.layout.mBarrierDirection = barrier.getType();
                    constraint.layout.mBarrierMargin = barrier.getMargin();
                }
                i2++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0167, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(android.content.Context r8, org.xmlpull.v1.XmlPullParser r9) {
        /*
            r7 = this;
            int r0 = r9.getEventType()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r1 = 0
            r2 = r1
        L_0x0006:
            r3 = 1
            if (r0 == r3) goto L_0x0176
            if (r0 == 0) goto L_0x0164
            r4 = 2
            java.lang.String r5 = "Constraint"
            if (r0 == r4) goto L_0x0037
            r3 = 3
            if (r0 == r3) goto L_0x0015
            goto L_0x0167
        L_0x0015:
            java.lang.String r0 = r9.getName()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.String r3 = "ConstraintSet"
            boolean r3 = r3.equals(r0)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r3 == 0) goto L_0x0023
            goto L_0x0176
        L_0x0023:
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            java.util.HashMap<java.lang.Integer, androidx.constraintlayout.widget.ConstraintSet$Constraint> r0 = r7.mConstraints     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            int r3 = r2.mViewId     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.put(r3, r2)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r2 = r1
            goto L_0x0167
        L_0x0037:
            java.lang.String r0 = r9.getName()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            int r4 = r0.hashCode()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.String r6 = "XML parser error must be within a Constraint "
            switch(r4) {
                case -2025855158: goto L_0x0137;
                case -1984451626: goto L_0x010a;
                case -1269513683: goto L_0x00dc;
                case -1238332596: goto L_0x00ae;
                case -71750448: goto L_0x0096;
                case 1331510167: goto L_0x0080;
                case 1791837707: goto L_0x0056;
                case 1803088381: goto L_0x0046;
                default: goto L_0x0044;
            }
        L_0x0044:
            goto L_0x0167
        L_0x0046:
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            androidx.constraintlayout.widget.ConstraintSet$Constraint r2 = r7.fillFromAttributeList(r8, r0)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x0056:
            java.lang.String r3 = "CustomAttribute"
            boolean r0 = r0.equals(r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            if (r2 == 0) goto L_0x0067
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r0 = r2.mCustomConstraints     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            androidx.constraintlayout.widget.ConstraintAttribute.parse(r8, r9, r0)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x0067:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r6)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            int r9 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.String r8 = r8.toString()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r7.<init>(r8)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            throw r7     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
        L_0x0080:
            java.lang.String r4 = "Barrier"
            boolean r0 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            androidx.constraintlayout.widget.ConstraintSet$Constraint r2 = r7.fillFromAttributeList(r8, r0)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            androidx.constraintlayout.widget.ConstraintSet$Layout r0 = r2.layout     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.mHelperType = r3     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x0096:
            java.lang.String r4 = "Guideline"
            boolean r0 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            androidx.constraintlayout.widget.ConstraintSet$Constraint r2 = r7.fillFromAttributeList(r8, r0)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            androidx.constraintlayout.widget.ConstraintSet$Layout r0 = r2.layout     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.mIsGuideline = r3     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.mApply = r3     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x00ae:
            java.lang.String r3 = "Transform"
            boolean r0 = r0.equals(r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            if (r2 == 0) goto L_0x00c3
            androidx.constraintlayout.widget.ConstraintSet$Transform r0 = r2.transform     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.fillFromAttributeList(r8, r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x00c3:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r6)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            int r9 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.String r8 = r8.toString()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r7.<init>(r8)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            throw r7     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
        L_0x00dc:
            java.lang.String r3 = "PropertySet"
            boolean r0 = r0.equals(r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            if (r2 == 0) goto L_0x00f1
            androidx.constraintlayout.widget.ConstraintSet$PropertySet r0 = r2.propertySet     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.fillFromAttributeList(r8, r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x00f1:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r6)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            int r9 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.String r8 = r8.toString()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r7.<init>(r8)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            throw r7     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
        L_0x010a:
            java.lang.String r3 = "Motion"
            boolean r0 = r0.equals(r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            if (r2 == 0) goto L_0x011e
            androidx.constraintlayout.widget.ConstraintSet$Motion r0 = r2.motion     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.fillFromAttributeList(r8, r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x011e:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r6)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            int r9 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.String r8 = r8.toString()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r7.<init>(r8)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            throw r7     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
        L_0x0137:
            java.lang.String r3 = "Layout"
            boolean r0 = r0.equals(r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            if (r0 == 0) goto L_0x0167
            if (r2 == 0) goto L_0x014b
            androidx.constraintlayout.widget.ConstraintSet$Layout r0 = r2.layout     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r0.fillFromAttributeList(r8, r3)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0167
        L_0x014b:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r6)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            int r9 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r8.append(r9)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            java.lang.String r8 = r8.toString()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            r7.<init>(r8)     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            throw r7     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
        L_0x0164:
            r9.getName()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
        L_0x0167:
            int r0 = r9.next()     // Catch:{ XmlPullParserException -> 0x0172, IOException -> 0x016d }
            goto L_0x0006
        L_0x016d:
            r7 = move-exception
            r7.printStackTrace()
            goto L_0x0176
        L_0x0172:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0176:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }
}
