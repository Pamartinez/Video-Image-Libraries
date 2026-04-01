package com.samsung.android.imagetranslation.jni;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Size;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.geometry.SmallestSurroundingRectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SceneText {
    private static final double FIRST_CHAR_LENGTH_LIMIT = 1.5d;
    private static final int MINIMUM_LINE_SIZE = 5;
    static final String TAG = "SceneText";
    private int blockType;
    private boolean hasOnlyNumber;
    private boolean isParagraphRendering;
    private double mArea = -1.0d;
    private int mBlockHeight;
    private int mBlockWidth;
    private int mComponentNum = 0;
    private CopyOnWriteArrayList<SceneText> mComponents = null;
    private int mDeviceOrientation;
    private float mFontSize = 0.0f;
    private int mGoogleBlockIdx = -1;
    private ArrayList<String> mLangs = null;
    private double mMarginY;
    private TextOrientation mOrient = TextOrientation.CCW_0;
    private Point[] mPaddedBoxPoly;
    private Point[] mPoly = null;
    private ArrayList<String> mSplitTRSLines = new ArrayList<>();
    private int mTextAlignment;
    private Point[] mTrackedPoly;
    private String mTrsLang = null;
    private Point[] mTrsPoly = null;
    private int mTrsTextHeight;
    private Bitmap mTrsTextMask = null;
    private Point[] mTrsTextMaskPoly = null;
    private int mTrsTextWidth;
    private String mTrsValue = null;
    private SceneTextType mType = null;
    private Point[] mUpdatedTrsPoly = null;
    private String mValue = null;
    private boolean mVerticalType = false;
    private String trlUnit;

    /* renamed from: com.samsung.android.imagetranslation.jni.SceneText$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$imagetranslation$jni$SceneText$SceneTextType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.imagetranslation.jni.SceneText$SceneTextType[] r0 = com.samsung.android.imagetranslation.jni.SceneText.SceneTextType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$imagetranslation$jni$SceneText$SceneTextType = r0
                com.samsung.android.imagetranslation.jni.SceneText$SceneTextType r1 = com.samsung.android.imagetranslation.jni.SceneText.SceneTextType.PARAGRAPH     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$imagetranslation$jni$SceneText$SceneTextType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.imagetranslation.jni.SceneText$SceneTextType r1 = com.samsung.android.imagetranslation.jni.SceneText.SceneTextType.LINE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$imagetranslation$jni$SceneText$SceneTextType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.imagetranslation.jni.SceneText$SceneTextType r1 = com.samsung.android.imagetranslation.jni.SceneText.SceneTextType.WORD     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.imagetranslation.jni.SceneText.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SceneTextType {
        PARAGRAPH,
        LINE,
        WORD,
        CHARACTER
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TextOrientation {
        CCW_0,
        CCW_90,
        CCW_180,
        CCW_270
    }

    private boolean adjustFirstCharLength(SceneText sceneText) {
        char c5;
        char c6;
        char c8;
        char c10;
        if (sceneText.getComponents() == null) {
            return false;
        }
        CopyOnWriteArrayList<SceneText> components = sceneText.getComponents();
        Iterator<SceneText> it = components.iterator();
        double d = MapUtil.INVALID_LOCATION;
        int i2 = 0;
        while (true) {
            c5 = 2;
            c6 = 3;
            if (!it.hasNext()) {
                break;
            }
            CopyOnWriteArrayList<SceneText> components2 = it.next().getComponents();
            if (components2 != null) {
                Iterator<SceneText> it2 = components2.iterator();
                while (it2.hasNext()) {
                    Point[] poly = it2.next().getPoly();
                    if (poly != null) {
                        d += (calcDistance(poly[0], poly[1]) + calcDistance(poly[3], poly[2])) / 2.0d;
                        i2++;
                    }
                }
            }
        }
        double d2 = d / ((double) i2);
        Iterator<SceneText> it3 = components.iterator();
        boolean z = false;
        while (it3.hasNext()) {
            SceneText next = it3.next();
            if (!(next.getComponentNum() <= 1 || next.getComponents() == null || next.getComponents().get(0).getPoly() == null)) {
                Point[] poly2 = next.getComponents().get(0).getPoly();
                double calcDistance = (calcDistance(poly2[0], poly2[1]) + calcDistance(poly2[c6], poly2[c5])) / 2.0d;
                if (calcDistance > FIRST_CHAR_LENGTH_LIMIT * d2) {
                    double d3 = d2 / calcDistance;
                    Point point = poly2[0];
                    Point point2 = poly2[1];
                    int i7 = point2.x;
                    c8 = c5;
                    c10 = c6;
                    point.x = i7 - ((int) (((double) (i7 - point.x)) * d3));
                    int i8 = point2.y;
                    point.y = i8 - ((int) (((double) (i8 - point.y)) * d3));
                    Point point3 = poly2[c10];
                    Point point4 = poly2[c8];
                    int i10 = point4.x;
                    point3.x = i10 - ((int) (((double) (i10 - point3.x)) * d3));
                    int i11 = point4.y;
                    point3.y = i11 - ((int) (d3 * ((double) (i11 - point3.y))));
                    next.getComponents().get(0).setPoly(poly2);
                    next.setPoly(reCalcPoly(next.getComponents()));
                    z = true;
                    c5 = c8;
                    c6 = c10;
                }
            }
            c8 = c5;
            c10 = c6;
            c5 = c8;
            c6 = c10;
        }
        return z;
    }

    private double calcDistance(Point point, Point point2) {
        return Math.sqrt(Math.pow((double) (point.x - point2.x), 2.0d) + Math.pow((double) (point.y - point2.y), 2.0d));
    }

    private boolean isOutside_ROI(SceneText sceneText) {
        return false;
    }

    private boolean isRegular_expression(SceneText sceneText) {
        String[] strArr = {"^[0-9|\\p{Punct}|\\p{InArrows}|\\p{Blank}|\\p{Space}|\\=|\\_|\\-|\\.|\\/|\\s|\\,|\\*|\\'|\"|[?]|[*]|[+]|[|]|\\(|\\)|\\{|\\}|\\^|\\!|\\#|\\%|\\;|\\:|\\~|\\<|\\>|\\\\|\\+|\\!|\\@|\\^|\\&]+$", "^[0-9a-zA-Z|\\_|\\-|\\.]+[@][0-9a-zA-Z|\\_|\\-]+[\\.][0-9a-zA-Z]+$", "^[0-9a-zA-Z|\\_|\\-|\\/|\\:]+\\.[0-9a-zA-Z|\\_|\\-]+\\.[0-9a-zA-Z|\\.]+[\\s]*$"};
        for (int i2 = 0; i2 < 3; i2++) {
            if (Pattern.matches(strArr[i2], sceneText.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isSmallLine(SceneText sceneText) {
        Point[] pointArr = sceneText.mPoly;
        double pow = Math.pow((double) (pointArr[0].y - pointArr[3].y), 2.0d);
        Point[] pointArr2 = sceneText.mPoly;
        double sqrt = Math.sqrt(Math.pow((double) (pointArr2[0].x - pointArr2[3].x), 2.0d) + pow);
        Point[] pointArr3 = sceneText.mPoly;
        double pow2 = Math.pow((double) (pointArr3[0].y - pointArr3[1].y), 2.0d);
        Point[] pointArr4 = sceneText.mPoly;
        double sqrt2 = Math.sqrt(Math.pow((double) (pointArr4[0].x - pointArr4[1].x), 2.0d) + pow2);
        if (sqrt < 5.0d || sqrt2 < 5.0d) {
            return true;
        }
        return false;
    }

    private Point[] reCalcPoly(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        ArrayList arrayList = new ArrayList();
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            for (Point pointF : Arrays.asList(it.next().getPoly())) {
                arrayList.add(new PointF(pointF));
            }
        }
        return SmallestSurroundingRectangle.computePolys(arrayList);
    }

    private boolean removeFirstSpecialChar(SceneText sceneText) {
        String value = sceneText.getValue();
        if (!(sceneText.getComponents() == null || sceneText.getComponents().size() <= 0 || sceneText.getComponents().get(0).getComponents() == null)) {
            SceneText sceneText2 = sceneText.getComponents().get(0);
            if (sceneText2.mComponents.size() > 1 && Pattern.matches("\\p{Punct}", value.substring(0, 1))) {
                sceneText2.mComponents.remove(0);
                String value2 = sceneText2.getValue();
                sceneText2.mValue = "";
                sceneText2.mValue = value2.substring(1);
                sceneText2.setPoly(reCalcPoly(sceneText2.getComponents()));
                String value3 = sceneText.getValue();
                sceneText.mValue = "";
                sceneText.mValue = value3.substring(1);
                sceneText.setPoly(reCalcPoly(sceneText.getComponents()));
                return true;
            }
        }
        return false;
    }

    private void removeLines() {
        int i2;
        String str;
        int componentNum = getComponentNum();
        boolean z = false;
        if (componentNum > 0) {
            int i7 = 0;
            boolean z3 = false;
            boolean z7 = false;
            while (true) {
                if (i7 >= componentNum) {
                    break;
                }
                SceneText sceneText = getComponents().get(i7);
                if (!isSmallLine(sceneText) && !isRegular_expression(sceneText) && !isOutside_ROI(sceneText)) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z3) {
                    this.mComponents.remove(i7);
                    this.mComponentNum--;
                    componentNum--;
                    i7--;
                    z7 = true;
                }
                i7++;
            }
            if (z3) {
                StringBuilder sb2 = new StringBuilder();
                if (getComponentNum() > 0) {
                    sb2.append(this.mComponents.get(0).mValue);
                }
                if (getComponentNum() > 1) {
                    for (i2 = 1; i2 < getComponentNum(); i2++) {
                        sb2.append(10);
                        sb2.append(this.mComponents.get(i2).mValue);
                    }
                }
                if (sb2.length() == 0) {
                    str = null;
                } else {
                    str = sb2.toString();
                }
                this.mValue = str;
            }
            z = z7;
        }
        if (z && getComponentNum() > 0) {
            setPoly(reCalcPoly(getComponents()));
        }
    }

    private void repairText() {
        int size;
        CopyOnWriteArrayList<SceneText> components;
        if (AnonymousClass1.$SwitchMap$com$samsung$android$imagetranslation$jni$SceneText$SceneTextType[this.mType.ordinal()] == 1 && getComponents() != null && (size = getComponents().size()) > 0 && (components = getComponents()) != null) {
            Iterator<SceneText> it = components.iterator();
            boolean z = false;
            while (it.hasNext()) {
                SceneText next = it.next();
                if (!next.getVerticalType()) {
                    if (removeFirstSpecialChar(next)) {
                        z = true;
                    }
                    if (adjustFirstCharLength(next)) {
                        z = true;
                    }
                }
            }
            for (int i2 = 0; i2 < size; i2++) {
                SceneText sceneText = getComponents().get(i2);
                SceneText sceneText2 = new SceneText();
                SceneText sceneText3 = new SceneText();
                CopyOnWriteArrayList<SceneText> copyOnWriteArrayList = sceneText2.mComponents;
                if (copyOnWriteArrayList == null) {
                    sceneText2.mComponents = new CopyOnWriteArrayList<>();
                } else {
                    copyOnWriteArrayList.clear();
                }
                sceneText2.mPoly = new Point[4];
                sceneText2.mType = sceneText.mType;
                sceneText2.setLanguages(sceneText.mLangs);
                sceneText2.setGoogleBlockIdx(sceneText.mGoogleBlockIdx);
                sceneText2.setVerticalType(sceneText.getVerticalType());
                CopyOnWriteArrayList<SceneText> copyOnWriteArrayList2 = sceneText3.mComponents;
                if (copyOnWriteArrayList2 == null) {
                    sceneText3.mComponents = new CopyOnWriteArrayList<>();
                } else {
                    copyOnWriteArrayList2.clear();
                }
                sceneText3.mPoly = new Point[4];
                sceneText3.mType = sceneText.mType;
                sceneText3.setLanguages(sceneText.mLangs);
                sceneText3.setGoogleBlockIdx(sceneText.mGoogleBlockIdx);
                sceneText3.setVerticalType(sceneText.getVerticalType());
            }
            if (z) {
                setPoly(reCalcPoly(getComponents()));
                StringBuilder sb2 = new StringBuilder(this.mComponents.get(0).mValue);
                for (int i7 = 1; i7 < getComponentNum(); i7++) {
                    sb2.append(10);
                    sb2.append(this.mComponents.get(i7).mValue);
                }
                this.mValue = sb2.toString();
            }
        }
    }

    public Size calculateBlockDimensions() {
        Point[] paddedBoxPoly = getPaddedBoxPoly();
        for (int i2 = 0; i2 < paddedBoxPoly.length; i2++) {
            Point point = paddedBoxPoly[i2];
            point.x = Math.max(point.x, 0);
            Point point2 = paddedBoxPoly[i2];
            point2.y = Math.max(point2.y, 0);
        }
        this.mBlockWidth = (int) Math.sqrt(Math.pow((double) (paddedBoxPoly[1].y - paddedBoxPoly[0].y), 2.0d) + Math.pow((double) (paddedBoxPoly[1].x - paddedBoxPoly[0].x), 2.0d));
        this.mBlockHeight = (int) Math.sqrt(Math.pow((double) (paddedBoxPoly[3].y - paddedBoxPoly[0].y), 2.0d) + Math.pow((double) (paddedBoxPoly[3].x - paddedBoxPoly[0].x), 2.0d));
        LTTLogger.i(TAG, "Block W: " + this.mBlockWidth + " H: " + this.mBlockHeight);
        return new Size(this.mBlockWidth, this.mBlockHeight);
    }

    public double getArea() {
        return this.mArea;
    }

    public int getBlockType() {
        return this.blockType;
    }

    public int getComponentNum() {
        CopyOnWriteArrayList<SceneText> copyOnWriteArrayList = this.mComponents;
        if (copyOnWriteArrayList != null) {
            return copyOnWriteArrayList.size();
        }
        return 0;
    }

    public CopyOnWriteArrayList<SceneText> getComponents() {
        return this.mComponents;
    }

    public int getDeviceOrientation() {
        return this.mDeviceOrientation;
    }

    public float getFontSize() {
        return this.mFontSize;
    }

    public int getGoogleBlockIdx() {
        return this.mGoogleBlockIdx;
    }

    public int getHeight() {
        return this.mBlockHeight;
    }

    public ArrayList<String> getLanguages() {
        return this.mLangs;
    }

    public double getMarginY() {
        return this.mMarginY;
    }

    public int getOrient() {
        return this.mOrient.ordinal();
    }

    public Point[] getPaddedBoxPoly() {
        return this.mPaddedBoxPoly;
    }

    public Point[] getPoly() {
        return this.mPoly;
    }

    public ArrayList<String> getSplitTRSLines() {
        return this.mSplitTRSLines;
    }

    public int getTextAlignment() {
        return this.mTextAlignment;
    }

    public Point[] getTrackedPoly() {
        return this.mTrackedPoly;
    }

    public String getTrlUnit() {
        return this.trlUnit;
    }

    public String getTrsLanguage() {
        return this.mTrsLang;
    }

    public Point[] getTrsPoly() {
        return this.mTrsPoly;
    }

    public int getTrsTextHeight() {
        return this.mTrsTextHeight;
    }

    public Bitmap getTrsTextMask() {
        return this.mTrsTextMask;
    }

    public Point[] getTrsTextMaskPoly() {
        return this.mTrsTextMaskPoly;
    }

    public int getTrsTextwidth() {
        return this.mTrsTextWidth;
    }

    public String getTrsValue() {
        String str = this.mTrsValue;
        if (str == null) {
            return "";
        }
        return str;
    }

    public SceneTextType getType() {
        return this.mType;
    }

    public Point[] getUpdatedTrsPoly() {
        return this.mUpdatedTrsPoly;
    }

    public String getValue() {
        return this.mValue;
    }

    public boolean getVerticalType() {
        return this.mVerticalType;
    }

    public int getWidth() {
        return this.mBlockWidth;
    }

    public boolean hasOnlyNumber() {
        return this.hasOnlyNumber;
    }

    public boolean isParagraphRendering() {
        return this.isParagraphRendering;
    }

    public boolean repair() {
        if (getComponents().size() > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            String str = TAG;
            LTTLogger.d(str, "size of SceneText's lines before repairText: " + this.mComponents.size());
            LTTLogger.d(str, "paragraph poly before repairText : " + getPoly()[0] + ArcCommonLog.TAG_COMMA + getPoly()[1] + ArcCommonLog.TAG_COMMA + getPoly()[2] + ArcCommonLog.TAG_COMMA + getPoly()[3]);
            repairText();
            LTTLogger.d(str, "paragraph poly after repairText : " + getPoly()[0] + ArcCommonLog.TAG_COMMA + getPoly()[1] + ArcCommonLog.TAG_COMMA + getPoly()[2] + ArcCommonLog.TAG_COMMA + getPoly()[3]);
            StringBuilder sb2 = new StringBuilder("size of SceneText's lines after repairText: ");
            sb2.append(this.mComponents.size());
            LTTLogger.d(str, sb2.toString());
            StringBuilder sb3 = new StringBuilder("repairText time of the current paragraph = ");
            sb3.append(System.currentTimeMillis() - currentTimeMillis);
            sb3.append("ms");
            LTTLogger.p(str, sb3.toString());
        }
        if (getComponents().size() > 0) {
            long currentTimeMillis2 = System.currentTimeMillis();
            String str2 = TAG;
            LTTLogger.d(str2, "size of SceneText's lines before removeLines: " + this.mComponents.size());
            LTTLogger.d(str2, "paragraph poly before removeLines : " + getPoly()[0] + ArcCommonLog.TAG_COMMA + getPoly()[1] + ArcCommonLog.TAG_COMMA + getPoly()[2] + ArcCommonLog.TAG_COMMA + getPoly()[3]);
            removeLines();
            LTTLogger.d(str2, "paragraph poly after removeLines : " + getPoly()[0] + ArcCommonLog.TAG_COMMA + getPoly()[1] + ArcCommonLog.TAG_COMMA + getPoly()[2] + ArcCommonLog.TAG_COMMA + getPoly()[3]);
            StringBuilder sb4 = new StringBuilder("size of SceneText's lines after removeLines: ");
            sb4.append(this.mComponents.size());
            LTTLogger.d(str2, sb4.toString());
            StringBuilder sb5 = new StringBuilder("removing overlapped lines time of the current paragraph = ");
            sb5.append(System.currentTimeMillis() - currentTimeMillis2);
            sb5.append("ms");
            LTTLogger.p(str2, sb5.toString());
        }
        if (getComponents().size() > 0) {
            return true;
        }
        return false;
    }

    public void setArea(double d) {
        this.mArea = d;
    }

    public void setBlockType(int i2) {
        this.blockType = i2;
    }

    public void setComponent(SceneText sceneText) {
        if (this.mComponents == null) {
            this.mComponents = new CopyOnWriteArrayList<>();
            this.mComponentNum = 0;
        }
        this.mComponents.add(sceneText);
        this.mComponentNum++;
    }

    public void setComponents(List<SceneText> list) {
        if (this.mComponents == null) {
            this.mComponents = new CopyOnWriteArrayList<>();
            this.mComponentNum = 0;
        }
        this.mComponents.addAll(list);
        this.mComponentNum = this.mComponents.size();
    }

    public void setDeviceOrientation(int i2) {
        this.mDeviceOrientation = i2;
    }

    public void setFontSize(float f) {
        this.mFontSize = f;
    }

    public void setGoogleBlockIdx(int i2) {
        if (this.mType == SceneTextType.LINE) {
            this.mGoogleBlockIdx = i2;
        } else {
            this.mGoogleBlockIdx = -1;
        }
    }

    public void setHeight(int i2) {
        this.mBlockHeight = i2;
    }

    public void setLanguages(ArrayList<String> arrayList) {
        if (arrayList != null) {
            this.mLangs = new ArrayList<>(arrayList);
        }
    }

    public void setMarginY(double d) {
        this.mMarginY = d;
    }

    public void setOnlyNumber(boolean z) {
        this.hasOnlyNumber = z;
    }

    public void setOrient(TextOrientation textOrientation) {
        this.mOrient = textOrientation;
    }

    public void setPaddedBoxPoly(Point[] pointArr) {
        this.mPaddedBoxPoly = pointArr;
    }

    public void setParagraphRendering(boolean z) {
        this.isParagraphRendering = z;
    }

    public void setPoly(Point[] pointArr) {
        if (this.mPoly == null) {
            this.mPoly = new Point[4];
        }
        this.mPoly = pointArr;
    }

    public void setSplitTRSLines(ArrayList<String> arrayList) {
        this.mSplitTRSLines = arrayList;
    }

    public void setTextAlignment(int i2) {
        this.mTextAlignment = i2;
    }

    public void setTrackedPoly(Point[] pointArr) {
        if (this.mTrackedPoly == null) {
            this.mTrackedPoly = new Point[4];
        }
        this.mTrackedPoly = pointArr;
    }

    public void setTrlUnit(String str) {
        this.trlUnit = str;
    }

    public void setTrsLanguage(String str) {
        this.mTrsLang = str;
    }

    public void setTrsPoly(Point[] pointArr) {
        if (this.mTrsPoly == null) {
            this.mTrsPoly = new Point[4];
        }
        this.mTrsPoly = pointArr;
    }

    public void setTrsTextHeight(int i2) {
        this.mTrsTextHeight = i2;
    }

    public void setTrsTextMask(Bitmap bitmap) {
        Bitmap bitmap2 = this.mTrsTextMask;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.mTrsTextMask = bitmap;
    }

    public void setTrsTextMaskPoly(Point[] pointArr) {
        if (this.mTrsTextMaskPoly == null) {
            this.mTrsTextMaskPoly = new Point[4];
        }
        this.mTrsTextMaskPoly = pointArr;
    }

    public void setTrsTextwidth(int i2) {
        this.mTrsTextWidth = i2;
    }

    public void setTrsValue(String str) {
        this.mTrsValue = str;
    }

    public void setType(SceneTextType sceneTextType) {
        this.mType = sceneTextType;
    }

    public void setUpdatedTrsPoly(Point[] pointArr) {
        if (this.mUpdatedTrsPoly == null) {
            this.mUpdatedTrsPoly = new Point[4];
        }
        this.mUpdatedTrsPoly = pointArr;
    }

    public void setValue(String str) {
        this.mValue = str;
    }

    public void setVerticalType(boolean z) {
        this.mVerticalType = z;
    }

    public void setWidth(int i2) {
        this.mBlockWidth = i2;
    }

    public SceneText clone() {
        SceneText sceneText = new SceneText();
        sceneText.setType(this.mType);
        sceneText.setDeviceOrientation(this.mDeviceOrientation);
        sceneText.setLanguages(this.mLangs);
        sceneText.setVerticalType(this.mVerticalType);
        sceneText.setGoogleBlockIdx(this.mGoogleBlockIdx);
        sceneText.setOrient(this.mOrient);
        Point[] pointArr = this.mPoly;
        if (pointArr != null) {
            Point[] pointArr2 = new Point[pointArr.length];
            for (int i2 = 0; i2 < this.mPoly.length; i2++) {
                pointArr2[i2] = new Point(this.mPoly[i2]);
            }
            sceneText.setPoly(pointArr2);
        }
        Point[] pointArr3 = this.mTrackedPoly;
        if (pointArr3 != null) {
            Point[] pointArr4 = new Point[pointArr3.length];
            for (int i7 = 0; i7 < this.mTrackedPoly.length; i7++) {
                pointArr4[i7] = new Point(this.mTrackedPoly[i7]);
            }
            sceneText.setTrackedPoly(pointArr4);
        }
        sceneText.setValue(this.mValue);
        sceneText.setTrsLanguage(this.mTrsLang);
        Point[] pointArr5 = this.mTrsPoly;
        if (pointArr5 != null) {
            Point[] pointArr6 = new Point[pointArr5.length];
            for (int i8 = 0; i8 < this.mTrsPoly.length; i8++) {
                pointArr6[i8] = new Point(this.mTrsPoly[i8]);
            }
            sceneText.setTrsPoly(pointArr6);
        }
        sceneText.setTrsValue(this.mTrsValue);
        sceneText.setTrsTextMask(this.mTrsTextMask);
        if (this.mComponents != null) {
            sceneText.mComponents = new CopyOnWriteArrayList<>();
            for (int i10 = 0; i10 < this.mComponents.size(); i10++) {
                sceneText.mComponents.add(this.mComponents.get(i10).clone());
            }
        }
        return sceneText;
    }
}
