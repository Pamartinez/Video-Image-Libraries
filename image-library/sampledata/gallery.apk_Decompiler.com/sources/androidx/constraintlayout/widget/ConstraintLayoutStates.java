package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConstraintLayoutStates {
    private final ConstraintLayout mConstraintLayout;
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;
    int mCurrentConstraintNumber = -1;
    int mCurrentStateId = -1;
    ConstraintSet mDefaultConstraintSet;
    private SparseArray<State> mStateList = new SparseArray<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class State {
        int mConstraintID = -1;
        ConstraintSet mConstraintSet;
        int mId;
        ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser xmlPullParser) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R$styleable.State_android_id) {
                    this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == R$styleable.State_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void add(Variant variant) {
            this.mVariants.add(variant);
        }

        public int findMatch(float f, float f5) {
            for (int i2 = 0; i2 < this.mVariants.size(); i2++) {
                if (this.mVariants.get(i2).match(f, f5)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Variant {
        int mConstraintID = -1;
        ConstraintSet mConstraintSet;
        float mMaxHeight = Float.NaN;
        float mMaxWidth = Float.NaN;
        float mMinHeight = Float.NaN;
        float mMinWidth = Float.NaN;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R$styleable.Variant_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
                    }
                } else if (index == R$styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = obtainStyledAttributes.getDimension(index, this.mMaxHeight);
                } else if (index == R$styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = obtainStyledAttributes.getDimension(index, this.mMinHeight);
                } else if (index == R$styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = obtainStyledAttributes.getDimension(index, this.mMaxWidth);
                } else if (index == R$styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = obtainStyledAttributes.getDimension(index, this.mMinWidth);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }

        public boolean match(float f, float f5) {
            if (!Float.isNaN(this.mMinWidth) && f < this.mMinWidth) {
                return false;
            }
            if (!Float.isNaN(this.mMinHeight) && f5 < this.mMinHeight) {
                return false;
            }
            if (!Float.isNaN(this.mMaxWidth) && f > this.mMaxWidth) {
                return false;
            }
            if (Float.isNaN(this.mMaxHeight) || f5 <= this.mMaxHeight) {
                return true;
            }
            return false;
        }
    }

    public ConstraintLayoutStates(Context context, ConstraintLayout constraintLayout, int i2) {
        this.mConstraintLayout = constraintLayout;
        load(context, i2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void load(android.content.Context r6, int r7) {
        /*
            r5 = this;
            android.content.res.Resources r0 = r6.getResources()
            android.content.res.XmlResourceParser r7 = r0.getXml(r7)
            int r0 = r7.getEventType()     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            r1 = 0
        L_0x000d:
            r2 = 1
            if (r0 == r2) goto L_0x0092
            if (r0 == 0) goto L_0x0081
            r2 = 2
            if (r0 == r2) goto L_0x0017
            goto L_0x0084
        L_0x0017:
            java.lang.String r0 = r7.getName()     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            int r2 = r0.hashCode()     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            switch(r2) {
                case -1349929691: goto L_0x005d;
                case 80204913: goto L_0x0048;
                case 1382829617: goto L_0x003f;
                case 1657696882: goto L_0x0036;
                case 1901439077: goto L_0x0023;
                default: goto L_0x0022;
            }     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
        L_0x0022:
            goto L_0x0069
        L_0x0023:
            java.lang.String r2 = "Variant"
            boolean r2 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            if (r2 == 0) goto L_0x0069
            androidx.constraintlayout.widget.ConstraintLayoutStates$Variant r0 = new androidx.constraintlayout.widget.ConstraintLayoutStates$Variant     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            r0.<init>(r6, r7)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            if (r1 == 0) goto L_0x0084
            r1.add(r0)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            goto L_0x0084
        L_0x0036:
            java.lang.String r2 = "layoutDescription"
            boolean r2 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            if (r2 == 0) goto L_0x0069
            goto L_0x0084
        L_0x003f:
            java.lang.String r2 = "StateSet"
            boolean r2 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            if (r2 == 0) goto L_0x0069
            goto L_0x0084
        L_0x0048:
            java.lang.String r2 = "State"
            boolean r2 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            if (r2 == 0) goto L_0x0069
            androidx.constraintlayout.widget.ConstraintLayoutStates$State r1 = new androidx.constraintlayout.widget.ConstraintLayoutStates$State     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            r1.<init>(r6, r7)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            android.util.SparseArray<androidx.constraintlayout.widget.ConstraintLayoutStates$State> r0 = r5.mStateList     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            int r2 = r1.mId     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            r0.put(r2, r1)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            goto L_0x0084
        L_0x005d:
            java.lang.String r2 = "ConstraintSet"
            boolean r2 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            if (r2 == 0) goto L_0x0069
            r5.parseConstraintSet(r6, r7)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            goto L_0x0084
        L_0x0069:
            java.lang.String r2 = "ConstraintLayoutStates"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            r3.<init>()     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            java.lang.String r4 = "unknown tag "
            r3.append(r4)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            r3.append(r0)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            java.lang.String r0 = r3.toString()     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            android.util.Log.v(r2, r0)     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            goto L_0x0084
        L_0x0081:
            r7.getName()     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
        L_0x0084:
            int r0 = r7.next()     // Catch:{ XmlPullParserException -> 0x008e, IOException -> 0x0089 }
            goto L_0x000d
        L_0x0089:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x0092
        L_0x008e:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0092:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayoutStates.load(android.content.Context, int):void");
    }

    private void parseConstraintSet(Context context, XmlPullParser xmlPullParser) {
        int i2;
        ConstraintSet constraintSet = new ConstraintSet();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i7 = 0; i7 < attributeCount; i7++) {
            if ("id".equals(xmlPullParser.getAttributeName(i7))) {
                String attributeValue = xmlPullParser.getAttributeValue(i7);
                if (attributeValue.contains("/")) {
                    i2 = context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName());
                } else {
                    i2 = -1;
                }
                if (i2 == -1) {
                    if (attributeValue.length() > 1) {
                        i2 = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                constraintSet.load(context, xmlPullParser);
                this.mConstraintSetMap.put(i2, constraintSet);
                return;
            }
        }
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    public void updateConstraints(int i2, float f, float f5) {
        ConstraintSet constraintSet;
        int i7;
        State state;
        int findMatch;
        ConstraintSet constraintSet2;
        int i8;
        int i10 = this.mCurrentStateId;
        if (i10 == i2) {
            if (i2 == -1) {
                state = this.mStateList.valueAt(0);
            } else {
                state = this.mStateList.get(i10);
            }
            int i11 = this.mCurrentConstraintNumber;
            if ((i11 == -1 || !state.mVariants.get(i11).match(f, f5)) && this.mCurrentConstraintNumber != (findMatch = state.findMatch(f, f5))) {
                if (findMatch == -1) {
                    constraintSet2 = this.mDefaultConstraintSet;
                } else {
                    constraintSet2 = state.mVariants.get(findMatch).mConstraintSet;
                }
                if (findMatch == -1) {
                    i8 = state.mConstraintID;
                } else {
                    i8 = state.mVariants.get(findMatch).mConstraintID;
                }
                if (constraintSet2 != null) {
                    this.mCurrentConstraintNumber = findMatch;
                    ConstraintsChangedListener constraintsChangedListener = this.mConstraintsChangedListener;
                    if (constraintsChangedListener != null) {
                        constraintsChangedListener.preLayoutChange(-1, i8);
                    }
                    constraintSet2.applyTo(this.mConstraintLayout);
                    ConstraintsChangedListener constraintsChangedListener2 = this.mConstraintsChangedListener;
                    if (constraintsChangedListener2 != null) {
                        constraintsChangedListener2.postLayoutChange(-1, i8);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        this.mCurrentStateId = i2;
        State state2 = this.mStateList.get(i2);
        int findMatch2 = state2.findMatch(f, f5);
        if (findMatch2 == -1) {
            constraintSet = state2.mConstraintSet;
        } else {
            constraintSet = state2.mVariants.get(findMatch2).mConstraintSet;
        }
        if (findMatch2 == -1) {
            i7 = state2.mConstraintID;
        } else {
            i7 = state2.mVariants.get(findMatch2).mConstraintID;
        }
        if (constraintSet == null) {
            Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i2 + ", dim =" + f + ArcCommonLog.TAG_COMMA + f5);
            return;
        }
        this.mCurrentConstraintNumber = findMatch2;
        ConstraintsChangedListener constraintsChangedListener3 = this.mConstraintsChangedListener;
        if (constraintsChangedListener3 != null) {
            constraintsChangedListener3.preLayoutChange(i2, i7);
        }
        constraintSet.applyTo(this.mConstraintLayout);
        ConstraintsChangedListener constraintsChangedListener4 = this.mConstraintsChangedListener;
        if (constraintsChangedListener4 != null) {
            constraintsChangedListener4.postLayoutChange(i2, i7);
        }
    }
}
