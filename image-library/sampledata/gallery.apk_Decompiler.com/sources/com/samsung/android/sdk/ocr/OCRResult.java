package com.samsung.android.sdk.ocr;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OCRResult implements Parcelable {
    public static final Parcelable.Creator<OCRResult> CREATOR = new Parcelable.Creator<OCRResult>() {
        public OCRResult createFromParcel(Parcel parcel) {
            return new OCRResult(parcel);
        }

        public OCRResult[] newArray(int i2) {
            return new OCRResult[i2];
        }
    };
    private static final boolean SUPPORT_UNION_RECT = true;
    private static final String TAG = "OCRResult";
    private ArrayList<BlockData> mBlockDataList = null;
    private ProcessInfo mProcessInfo = null;
    private ArrayList<TableData> mTableDataList = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProcessInfo implements Parcelable {
        public static final Parcelable.Creator<ProcessInfo> CREATOR = new Parcelable.Creator<ProcessInfo>() {
            public ProcessInfo createFromParcel(Parcel parcel) {
                return new ProcessInfo(parcel);
            }

            public ProcessInfo[] newArray(int i2) {
                return new ProcessInfo[i2];
            }
        };
        private boolean mHasText;
        private boolean mIsHandwrittenResult;
        private String mMOCREngineVersion;
        private String mSOCREngineVersion;

        public ProcessInfo() {
            this.mMOCREngineVersion = "";
            this.mSOCREngineVersion = "";
            this.mIsHandwrittenResult = false;
            this.mHasText = false;
        }

        public int describeContents() {
            return 0;
        }

        public String getEngineVersion() {
            return this.mIsHandwrittenResult ? this.mSOCREngineVersion : this.mMOCREngineVersion;
        }

        public boolean hasText() {
            return this.mHasText;
        }

        public boolean isHandwrittenResult() {
            return this.mIsHandwrittenResult;
        }

        public void setEngineVersion(String str, boolean z) {
            if (z) {
                this.mSOCREngineVersion = str;
            } else {
                this.mMOCREngineVersion = str;
            }
        }

        public void setHandwrittenResult(boolean z) {
            this.mIsHandwrittenResult = z;
        }

        public void setHasText(boolean z) {
            this.mHasText = z;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeBoolean(this.mHasText);
            parcel.writeBoolean(this.mIsHandwrittenResult);
            parcel.writeString(this.mSOCREngineVersion);
            parcel.writeString(this.mMOCREngineVersion);
        }

        public String getEngineVersion(boolean z) {
            return z ? this.mSOCREngineVersion : this.mMOCREngineVersion;
        }

        public ProcessInfo(Parcel parcel) {
            this.mMOCREngineVersion = "";
            this.mSOCREngineVersion = "";
            this.mHasText = parcel.readBoolean();
            this.mIsHandwrittenResult = parcel.readBoolean();
            this.mSOCREngineVersion = parcel.readString();
            this.mMOCREngineVersion = parcel.readString();
        }
    }

    public OCRResult() {
        init();
    }

    public static Point[] getRotatedCorner(Point[] pointArr, float f, PointF pointF) {
        if (pointArr.length != 4) {
            Log.e(TAG, "The number of corner points is not 4.");
            return null;
        }
        Point[] pointArr2 = new Point[4];
        int i2 = 0;
        for (Point point : pointArr) {
            Point point2 = new Point(point);
            pointArr2[i2] = point2;
            rotate(point2, f, pointF);
            i2++;
        }
        return pointArr2;
    }

    private void init() {
        this.mBlockDataList = new ArrayList<>();
        this.mTableDataList = new ArrayList<>();
        this.mProcessInfo = new ProcessInfo();
    }

    public static void printRect(Point[] pointArr, String str, String str2) {
        if (str2 != null) {
            Log.i(TAG, String.format("%s [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)] %s", new Object[]{str, Integer.valueOf(pointArr[0].x), Integer.valueOf(pointArr[0].y), Integer.valueOf(pointArr[1].x), Integer.valueOf(pointArr[1].y), Integer.valueOf(pointArr[2].x), Integer.valueOf(pointArr[2].y), Integer.valueOf(pointArr[3].x), Integer.valueOf(pointArr[3].y), str2}));
            return;
        }
        Integer valueOf = Integer.valueOf(pointArr[0].x);
        Integer valueOf2 = Integer.valueOf(pointArr[0].y);
        Integer valueOf3 = Integer.valueOf(pointArr[1].x);
        Integer num = valueOf3;
        Integer valueOf4 = Integer.valueOf(pointArr[1].y);
        Integer num2 = valueOf;
        Integer num3 = num;
        Log.i(TAG, String.format("%s [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{str, num2, valueOf2, num3, valueOf4, Integer.valueOf(pointArr[2].x), Integer.valueOf(pointArr[2].y), Integer.valueOf(pointArr[3].x), Integer.valueOf(pointArr[3].y)}));
    }

    public static void rotate(Point point, float f, PointF pointF) {
        double d = (((double) f) * 3.141592653589793d) / 180.0d;
        float f5 = pointF.x;
        double d2 = (double) (((float) point.x) - f5);
        double d3 = (double) (((float) point.y) - pointF.y);
        point.x = (int) ((Math.sin(d) * d3) + (Math.cos(d) * d2) + ((double) f5));
        point.y = (int) ((Math.cos(d) * d3) + (((double) pointF.y) - (Math.sin(d) * d2)));
    }

    public void add(BlockData blockData) {
        this.mBlockDataList.add(blockData);
    }

    public void clear() {
        init();
    }

    public void clearResult() {
        this.mBlockDataList = new ArrayList<>();
        this.mTableDataList = new ArrayList<>();
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<BlockData> getBlockDataList() {
        return this.mBlockDataList;
    }

    public ProcessInfo getProcessInfo() {
        return this.mProcessInfo;
    }

    public ArrayList<TableData> getTableDataList() {
        return this.mTableDataList;
    }

    public String getText() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<BlockData> it = this.mBlockDataList.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().getText());
            sb2.append("\n\n");
        }
        return new String(sb2).trim();
    }

    public Point[] getTextRect() {
        Log.w(TAG, "OCRResult.getTextRect() was deprecated. Use getUnionRect() instead!");
        return getUnionRect();
    }

    public Point[] getUnionRect() {
        Point[] createInitialRect = OCRResultUtils.createInitialRect();
        Iterator<BlockData> it = this.mBlockDataList.iterator();
        while (it.hasNext()) {
            OCRResultUtils.unionRect(createInitialRect, it.next().getUnionRect());
        }
        return createInitialRect;
    }

    public boolean logInfo() {
        Log.i(TAG, "=====================================================================");
        Log.i(TAG, "Is Handwritten = " + this.mProcessInfo.isHandwrittenResult());
        Log.i(TAG, "Engine Version(SOCR) = " + this.mProcessInfo.getEngineVersion(true));
        Log.i(TAG, "Engine Version(MOCR) = " + this.mProcessInfo.getEngineVersion(false));
        Log.i(TAG, "---------------------------------------------------------------------");
        printRect(getUnionRect(), "OCRResult[mUnionRect]", String.format(" contains %d Blocks", new Object[]{Integer.valueOf(this.mBlockDataList.size())}));
        Log.i(TAG, "OCRResult::Text:");
        Iterator<BlockData> it = this.mBlockDataList.iterator();
        while (it.hasNext()) {
            if (!it.next().logInfo()) {
                return false;
            }
        }
        return true;
    }

    public boolean offset(int i2, int i7) {
        Iterator<BlockData> it = this.mBlockDataList.iterator();
        while (it.hasNext()) {
            if (!it.next().offset(i2, i7)) {
                return false;
            }
        }
        return true;
    }

    public boolean scale(float f) {
        Iterator<BlockData> it = this.mBlockDataList.iterator();
        while (it.hasNext()) {
            if (!it.next().scale(f)) {
                return false;
            }
        }
        return true;
    }

    public void setBlockDataList(ArrayList<BlockData> arrayList) {
        this.mBlockDataList.addAll(arrayList);
    }

    public void setTableDataList(ArrayList<TableData> arrayList) {
        this.mTableDataList.addAll(arrayList);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        ArrayList<BlockData> arrayList = this.mBlockDataList;
        if (arrayList != null) {
            parcel.writeTypedList(arrayList);
        }
        ArrayList<TableData> arrayList2 = this.mTableDataList;
        if (arrayList2 != null) {
            parcel.writeTypedList(arrayList2);
        }
        parcel.writeTypedObject(this.mProcessInfo, i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CharData extends AbsData {
        public static final Parcelable.Creator<CharData> CREATOR = new Parcelable.Creator<CharData>() {
            public CharData createFromParcel(Parcel parcel) {
                return new CharData(parcel);
            }

            public CharData[] newArray(int i2) {
                return new CharData[i2];
            }
        };
        private String mText = "";

        public CharData() {
        }

        public void clear() {
            this.mText = "";
        }

        public int describeContents() {
            return 0;
        }

        public /* bridge */ /* synthetic */ float getAngle() {
            return super.getAngle();
        }

        public /* bridge */ /* synthetic */ PointF getCenter() {
            return super.getCenter();
        }

        public final String getText() {
            return this.mText;
        }

        public /* bridge */ /* synthetic */ boolean offset(int i2, int i7) {
            return super.offset(i2, i7);
        }

        public /* bridge */ /* synthetic */ boolean rotate(float f, PointF pointF) {
            return super.rotate(f, pointF);
        }

        public /* bridge */ /* synthetic */ boolean scale(float f) {
            return super.scale(f);
        }

        public /* bridge */ /* synthetic */ void setAngle(float f) {
            super.setAngle(f);
        }

        public /* bridge */ /* synthetic */ void setRect(Point[] pointArr) {
            super.setRect(pointArr);
        }

        public void setText(String str) {
            this.mText = str;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeToParcel: CharData");
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.mText);
        }

        public CharData(Parcel parcel) {
            super(parcel);
            this.mText = parcel.readString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TableCellData extends AbsData {
        public static final Parcelable.Creator<TableCellData> CREATOR = new Parcelable.Creator<TableCellData>() {
            public TableCellData createFromParcel(Parcel parcel) {
                return new TableCellData(parcel);
            }

            public TableCellData[] newArray(int i2) {
                return new TableCellData[i2];
            }
        };
        private Point[] mCellRect;
        private String mCellText = "";

        public TableCellData() {
        }

        public void clear() {
            this.mCellText = "";
        }

        public int describeContents() {
            return 0;
        }

        public /* bridge */ /* synthetic */ float getAngle() {
            return super.getAngle();
        }

        public final Point[] getCellRect() {
            Point[] pointArr = this.mCellRect;
            if (pointArr != null) {
                return pointArr;
            }
            Log.e(OCRResult.TAG, "[getCellRect] mCellRect is null, so set zero-length array");
            return new Point[0];
        }

        public final String getCellText() {
            return this.mCellText;
        }

        public /* bridge */ /* synthetic */ PointF getCenter() {
            return super.getCenter();
        }

        public boolean logInfo() {
            Point[] rect = getRect();
            Locale locale = Locale.ENGLISH;
            OCRResult.printRect(rect, "OCRResult::Cell[mRect]", C0212a.j(this.mCellText.length(), " contains ", " Characters"));
            return true;
        }

        public /* bridge */ /* synthetic */ boolean offset(int i2, int i7) {
            return super.offset(i2, i7);
        }

        public /* bridge */ /* synthetic */ boolean rotate(float f, PointF pointF) {
            return super.rotate(f, pointF);
        }

        public /* bridge */ /* synthetic */ boolean scale(float f) {
            return super.scale(f);
        }

        public /* bridge */ /* synthetic */ void setAngle(float f) {
            super.setAngle(f);
        }

        public void setCellRect(Point[] pointArr) {
            if (pointArr == null) {
                Log.e(OCRResult.TAG, "[setCellRect] rect is null.");
                this.mCellRect = null;
                return;
            }
            this.mCellRect = pointArr;
        }

        public void setCellText(String str) {
            this.mCellText = str;
        }

        public /* bridge */ /* synthetic */ void setRect(Point[] pointArr) {
            super.setRect(pointArr);
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeToParcel: TableCellData");
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.mCellText);
        }

        public TableCellData(Parcel parcel) {
            super(parcel);
            this.mCellText = parcel.readString();
        }
    }

    public void add(TableData tableData) {
        this.mTableDataList.add(tableData);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AbsData implements Parcelable {
        public static final Parcelable.Creator<AbsData> CREATOR = new Parcelable.Creator<AbsData>() {
            public AbsData createFromParcel(Parcel parcel) {
                return new AbsData(parcel);
            }

            public AbsData[] newArray(int i2) {
                return new AbsData[i2];
            }
        };
        protected float mAngle = 0.0f;
        protected Point[] mRect = new Point[4];

        public AbsData() {
        }

        public int describeContents() {
            return 0;
        }

        public float getAngle() {
            return this.mAngle;
        }

        public PointF getCenter() {
            Point[] pointArr = this.mRect;
            int length = pointArr.length;
            if (length > 0) {
                int i2 = 0;
                int i7 = 0;
                for (Point point : pointArr) {
                    i2 += point.x;
                    i7 += point.y;
                }
                float f = (float) length;
                return new PointF(((float) i2) / f, ((float) i7) / f);
            }
            throw new UnsupportedOperationException("mRect should not be defined to calculate center");
        }

        public final float[] getLines() {
            Point[] pointArr = this.mRect;
            Point point = pointArr[0];
            int i2 = point.x;
            int i7 = point.y;
            Point point2 = pointArr[1];
            int i8 = point2.x;
            int i10 = point2.y;
            Point point3 = pointArr[2];
            int i11 = point3.x;
            int i12 = point3.y;
            Point point4 = pointArr[3];
            int i13 = point4.x;
            int i14 = point4.y;
            return new float[]{(float) i2, (float) i7, (float) i8, (float) i10, (float) i8, (float) i10, (float) i11, (float) i12, (float) i11, (float) i12, (float) i13, (float) i14, (float) i13, (float) i14, (float) i2, (float) i7};
        }

        public Point[] getRect() {
            return this.mRect;
        }

        public boolean offset(int i2, int i7) {
            Point[] pointArr = this.mRect;
            if (pointArr.length <= 0) {
                return false;
            }
            for (Point offset : pointArr) {
                offset.offset(i2, i7);
            }
            return true;
        }

        public boolean rotate(float f, PointF pointF) {
            this.mAngle += f;
            this.mRect = OCRResult.getRotatedCorner(this.mRect, f, pointF);
            return true;
        }

        public boolean scale(float f) {
            for (Point point : this.mRect) {
                point.x = (int) (((float) point.x) * f);
                point.y = (int) (((float) point.y) * f);
            }
            return true;
        }

        public void setAngle(float f) {
            this.mAngle = f;
        }

        public void setRect(Point[] pointArr) {
            this.mRect = pointArr;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeIntArray");
            Point[] pointArr = this.mRect;
            Point point = pointArr[0];
            int i7 = point.x;
            int i8 = point.y;
            Point point2 = pointArr[1];
            int i10 = point2.x;
            int i11 = point2.y;
            Point point3 = pointArr[2];
            int i12 = point3.x;
            int i13 = point3.y;
            Point point4 = pointArr[3];
            parcel.writeIntArray(new int[]{i7, i8, i10, i11, i12, i13, point4.x, point4.y});
            parcel.writeFloat(this.mAngle);
        }

        public AbsData(Parcel parcel) {
            int[] iArr = new int[8];
            Log.i(OCRResult.TAG, "readIntArray");
            parcel.readIntArray(iArr);
            this.mRect[0] = new Point(iArr[0], iArr[1]);
            this.mRect[1] = new Point(iArr[2], iArr[3]);
            this.mRect[2] = new Point(iArr[4], iArr[5]);
            this.mRect[3] = new Point(iArr[6], iArr[7]);
            this.mAngle = parcel.readFloat();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LineData extends AbsData {
        public static final Parcelable.Creator<LineData> CREATOR = new Parcelable.Creator<LineData>() {
            public LineData createFromParcel(Parcel parcel) {
                return new LineData(parcel);
            }

            public LineData[] newArray(int i2) {
                return new LineData[i2];
            }
        };
        private boolean mIsCurved;
        private final ArrayList<WordData> mWordDataList;

        public LineData() {
            this.mIsCurved = false;
            this.mWordDataList = new ArrayList<>();
        }

        public void add(WordData wordData) {
            this.mWordDataList.add(wordData);
        }

        public void clear() {
            this.mWordDataList.clear();
        }

        public int describeContents() {
            return 0;
        }

        public /* bridge */ /* synthetic */ float getAngle() {
            return super.getAngle();
        }

        public /* bridge */ /* synthetic */ PointF getCenter() {
            return super.getCenter();
        }

        public final String getText() {
            StringBuilder sb2 = new StringBuilder();
            Iterator<WordData> it = this.mWordDataList.iterator();
            while (it.hasNext()) {
                sb2.append(it.next().getText());
                sb2.append(" ");
            }
            return sb2.toString().trim();
        }

        public ArrayList<WordData> getWordDataList() {
            return this.mWordDataList;
        }

        public boolean isCurved() {
            return this.mIsCurved;
        }

        public boolean logInfo() {
            Log.i(OCRResult.TAG, "------------------------------------------------------------------");
            OCRResult.printRect(super.getRect(), "OCRResult::Line[mRect]", String.format(" contains %d Words", new Object[]{Integer.valueOf(this.mWordDataList.size())}));
            Iterator<WordData> it = this.mWordDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().logInfo()) {
                    return false;
                }
            }
            return true;
        }

        public boolean offset(int i2, int i7) {
            super.offset(i2, i7);
            Iterator<WordData> it = this.mWordDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().offset(i2, i7)) {
                    return false;
                }
            }
            return true;
        }

        public boolean rotate(float f, PointF pointF) {
            super.rotate(f, pointF);
            Iterator<WordData> it = this.mWordDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().rotate(f, pointF)) {
                    return false;
                }
            }
            return true;
        }

        public boolean scale(float f) {
            super.scale(f);
            Iterator<WordData> it = this.mWordDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().scale(f)) {
                    return false;
                }
            }
            return true;
        }

        public /* bridge */ /* synthetic */ void setAngle(float f) {
            super.setAngle(f);
        }

        public void setCurved(boolean z) {
            this.mIsCurved = z;
        }

        public /* bridge */ /* synthetic */ void setRect(Point[] pointArr) {
            super.setRect(pointArr);
        }

        public void setWordDataList(ArrayList<WordData> arrayList) {
            this.mWordDataList.addAll(arrayList);
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeToParcel: LineData");
            super.writeToParcel(parcel, i2);
            ArrayList<WordData> arrayList = this.mWordDataList;
            if (arrayList != null) {
                parcel.writeTypedList(arrayList);
            }
        }

        public LineData(Parcel parcel) {
            super(parcel);
            this.mIsCurved = false;
            ArrayList<WordData> arrayList = new ArrayList<>();
            this.mWordDataList = arrayList;
            parcel.readTypedList(arrayList, WordData.CREATOR);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TableData extends AbsData {
        public static final Parcelable.Creator<TableData> CREATOR = new Parcelable.Creator<TableData>() {
            public TableData createFromParcel(Parcel parcel) {
                return new TableData(parcel);
            }

            public TableData[] newArray(int i2) {
                return new TableData[i2];
            }
        };
        private final ArrayList<TableRowData> mRowDataList;
        private Point[] mTableRect;

        public TableData() {
            this.mTableRect = null;
            this.mRowDataList = new ArrayList<>();
        }

        public void add(TableRowData tableRowData) {
            this.mRowDataList.add(tableRowData);
        }

        public void clear() {
            this.mTableRect = null;
            this.mRowDataList.clear();
        }

        public int describeContents() {
            return 0;
        }

        public /* bridge */ /* synthetic */ float getAngle() {
            return super.getAngle();
        }

        public /* bridge */ /* synthetic */ PointF getCenter() {
            return super.getCenter();
        }

        public ArrayList<TableRowData> getRowDataList() {
            return this.mRowDataList;
        }

        public Point[] getTableRect() {
            Point[] pointArr = this.mTableRect;
            if (pointArr != null) {
                return pointArr;
            }
            Log.e(OCRResult.TAG, "[getTableRect] mTableRect is null! Return zero-length array.");
            return new Point[0];
        }

        public String getText() {
            StringBuilder sb2 = new StringBuilder();
            Iterator<TableRowData> it = this.mRowDataList.iterator();
            while (it.hasNext()) {
                Iterator<TableCellData> it2 = it.next().getCellDataList().iterator();
                while (it2.hasNext()) {
                    sb2.append(it2.next().getCellText());
                    sb2.append("\n");
                }
            }
            return new String(sb2).trim();
        }

        public boolean logInfo() {
            Log.i(OCRResult.TAG, "------------------------------------------------------------------");
            Point[] rect = getRect();
            Locale locale = Locale.ENGLISH;
            OCRResult.printRect(rect, "OCRResult::Table[mRect]", C0212a.j(this.mRowDataList.size(), " contains ", " Rows"));
            Iterator<TableRowData> it = this.mRowDataList.iterator();
            while (it.hasNext()) {
                Iterator<TableCellData> it2 = it.next().getCellDataList().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!it2.next().logInfo()) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public /* bridge */ /* synthetic */ boolean offset(int i2, int i7) {
            return super.offset(i2, i7);
        }

        public /* bridge */ /* synthetic */ boolean rotate(float f, PointF pointF) {
            return super.rotate(f, pointF);
        }

        public /* bridge */ /* synthetic */ boolean scale(float f) {
            return super.scale(f);
        }

        public /* bridge */ /* synthetic */ void setAngle(float f) {
            super.setAngle(f);
        }

        public /* bridge */ /* synthetic */ void setRect(Point[] pointArr) {
            super.setRect(pointArr);
        }

        public void setRowDataList(ArrayList<TableRowData> arrayList) {
            this.mRowDataList.addAll(arrayList);
        }

        public void setTableRect(Point[] pointArr) {
            this.mTableRect = pointArr;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeToParcel: LineData");
            super.writeToParcel(parcel, i2);
            ArrayList<TableRowData> arrayList = this.mRowDataList;
            if (arrayList != null) {
                parcel.writeTypedList(arrayList);
            }
        }

        public TableData(Parcel parcel) {
            super(parcel);
            this.mTableRect = null;
            ArrayList<TableRowData> arrayList = new ArrayList<>();
            this.mRowDataList = arrayList;
            parcel.readTypedList(arrayList, TableRowData.CREATOR);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TableRowData extends AbsData {
        public static final Parcelable.Creator<TableRowData> CREATOR = new Parcelable.Creator<TableRowData>() {
            public TableRowData createFromParcel(Parcel parcel) {
                return new TableRowData(parcel);
            }

            public TableRowData[] newArray(int i2) {
                return new TableRowData[i2];
            }
        };
        private ArrayList<TableCellData> mCellDataList;

        public TableRowData() {
            this.mCellDataList = null;
            this.mCellDataList = new ArrayList<>();
        }

        public void add(TableCellData tableCellData) {
            this.mCellDataList.add(tableCellData);
        }

        public void clear() {
            this.mCellDataList.clear();
        }

        public int describeContents() {
            return 0;
        }

        public /* bridge */ /* synthetic */ float getAngle() {
            return super.getAngle();
        }

        public ArrayList<TableCellData> getCellDataList() {
            return this.mCellDataList;
        }

        public /* bridge */ /* synthetic */ PointF getCenter() {
            return super.getCenter();
        }

        public String getText() {
            StringBuilder sb2 = new StringBuilder();
            Iterator<TableCellData> it = this.mCellDataList.iterator();
            while (it.hasNext()) {
                sb2.append(it.next().getCellText());
                sb2.append("\n");
            }
            return new String(sb2).trim();
        }

        public boolean logInfo() {
            Log.i(OCRResult.TAG, "------------------------------------------------------------------");
            Point[] rect = getRect();
            Locale locale = Locale.ENGLISH;
            OCRResult.printRect(rect, "OCRResult::TableRow[mRect]", C0212a.j(this.mCellDataList.size(), " contains ", " Cells"));
            Iterator<TableCellData> it = this.mCellDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().logInfo()) {
                    return false;
                }
            }
            return true;
        }

        public /* bridge */ /* synthetic */ boolean offset(int i2, int i7) {
            return super.offset(i2, i7);
        }

        public /* bridge */ /* synthetic */ boolean rotate(float f, PointF pointF) {
            return super.rotate(f, pointF);
        }

        public /* bridge */ /* synthetic */ boolean scale(float f) {
            return super.scale(f);
        }

        public /* bridge */ /* synthetic */ void setAngle(float f) {
            super.setAngle(f);
        }

        public void setCellDataList(ArrayList<TableCellData> arrayList) {
            this.mCellDataList.addAll(arrayList);
        }

        public /* bridge */ /* synthetic */ void setRect(Point[] pointArr) {
            super.setRect(pointArr);
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeToParcel: LineData");
            super.writeToParcel(parcel, i2);
            ArrayList<TableCellData> arrayList = this.mCellDataList;
            if (arrayList != null) {
                parcel.writeTypedList(arrayList);
            }
        }

        public TableRowData(Parcel parcel) {
            super(parcel);
            this.mCellDataList = null;
            ArrayList<TableCellData> arrayList = new ArrayList<>();
            this.mCellDataList = arrayList;
            parcel.readTypedList(arrayList, TableCellData.CREATOR);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WordData extends AbsData {
        public static final Parcelable.Creator<WordData> CREATOR = new Parcelable.Creator<WordData>() {
            public WordData createFromParcel(Parcel parcel) {
                return new WordData(parcel);
            }

            public WordData[] newArray(int i2) {
                return new WordData[i2];
            }
        };
        private final ArrayList<CharData> mCharDataList;
        private Point[] mPoly;

        public WordData() {
            this.mPoly = null;
            this.mCharDataList = new ArrayList<>();
        }

        private void setPolyWithRect() {
            Point[] pointArr = this.mRect;
            if (pointArr != null && pointArr.length > 0) {
                if (pointArr[0] != null) {
                    this.mPoly = new Point[pointArr.length];
                    for (int i2 = 0; i2 < this.mRect.length; i2++) {
                        Point[] pointArr2 = this.mPoly;
                        Point point = this.mRect[i2];
                        pointArr2[i2] = new Point(point.x, point.y);
                    }
                    return;
                }
            }
            Log.w(OCRResult.TAG, "mRect is null or zero-length or its elements are null!");
        }

        public void add(CharData charData) {
            this.mCharDataList.add(charData);
        }

        public void clear() {
            this.mCharDataList.clear();
        }

        public int describeContents() {
            return 0;
        }

        public /* bridge */ /* synthetic */ float getAngle() {
            return super.getAngle();
        }

        public /* bridge */ /* synthetic */ PointF getCenter() {
            return super.getCenter();
        }

        public ArrayList<CharData> getCharDataList() {
            return this.mCharDataList;
        }

        public final Point[] getPoly() {
            if (this.mPoly == null) {
                Log.i(OCRResult.TAG, "mPoly is null, so set poly with rect");
                setPolyWithRect();
            }
            return this.mPoly;
        }

        public final String getText() {
            StringBuilder sb2 = new StringBuilder();
            Iterator<CharData> it = this.mCharDataList.iterator();
            while (it.hasNext()) {
                sb2.append(it.next().getText());
            }
            return sb2.toString().trim();
        }

        public boolean logInfo() {
            OCRResult.printRect(super.getRect(), "OCRResult::Word[mRect]", String.format(" contains %d Characters", new Object[]{Integer.valueOf(this.mCharDataList.size())}));
            return true;
        }

        public /* bridge */ /* synthetic */ boolean offset(int i2, int i7) {
            return super.offset(i2, i7);
        }

        public /* bridge */ /* synthetic */ boolean rotate(float f, PointF pointF) {
            return super.rotate(f, pointF);
        }

        public /* bridge */ /* synthetic */ boolean scale(float f) {
            return super.scale(f);
        }

        public /* bridge */ /* synthetic */ void setAngle(float f) {
            super.setAngle(f);
        }

        public void setCharDataList(ArrayList<CharData> arrayList) {
            this.mCharDataList.addAll(arrayList);
        }

        public void setPoly(Point[] pointArr) {
            if (pointArr == null || pointArr.length <= 0) {
                Log.w(OCRResult.TAG, "setPoly : poly is null or zero-length");
            } else {
                this.mPoly = pointArr;
            }
        }

        public /* bridge */ /* synthetic */ void setRect(Point[] pointArr) {
            super.setRect(pointArr);
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeToParcel: WordData");
            super.writeToParcel(parcel, i2);
            ArrayList<CharData> arrayList = this.mCharDataList;
            if (arrayList != null) {
                parcel.writeTypedList(arrayList);
            }
        }

        public WordData(Parcel parcel) {
            super(parcel);
            this.mPoly = null;
            ArrayList<CharData> arrayList = new ArrayList<>();
            this.mCharDataList = arrayList;
            parcel.readTypedList(arrayList, CharData.CREATOR);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BlockData extends AbsData {
        public static final Parcelable.Creator<BlockData> CREATOR = new Parcelable.Creator<BlockData>() {
            public BlockData createFromParcel(Parcel parcel) {
                return new BlockData(parcel);
            }

            public BlockData[] newArray(int i2) {
                return new BlockData[i2];
            }
        };
        private int mIsTabular;
        private final ArrayList<LineData> mLineDataList;

        public BlockData(Parcel parcel) {
            super(parcel);
            this.mIsTabular = 0;
            ArrayList<LineData> arrayList = new ArrayList<>();
            this.mLineDataList = arrayList;
            parcel.readTypedList(arrayList, LineData.CREATOR);
        }

        public void add(LineData lineData) {
            this.mLineDataList.add(lineData);
        }

        public void clear() {
            this.mLineDataList.clear();
        }

        public int describeContents() {
            return 0;
        }

        public /* bridge */ /* synthetic */ float getAngle() {
            return super.getAngle();
        }

        public /* bridge */ /* synthetic */ PointF getCenter() {
            return super.getCenter();
        }

        public ArrayList<LineData> getLineDataList() {
            return this.mLineDataList;
        }

        public String getText() {
            StringBuilder sb2 = new StringBuilder();
            Iterator<LineData> it = this.mLineDataList.iterator();
            while (it.hasNext()) {
                sb2.append(it.next().getText());
                sb2.append("\n");
            }
            return new String(sb2).trim();
        }

        public Point[] getTextRect() {
            Log.w(OCRResult.TAG, "BlockData.getTextRect() was deprecated. Use getUnionRect() instead!");
            return getUnionRect();
        }

        public Point[] getUnionRect() {
            Point[] createInitialRect = OCRResultUtils.createInitialRect();
            Iterator<LineData> it = this.mLineDataList.iterator();
            while (it.hasNext()) {
                OCRResultUtils.unionRect(createInitialRect, it.next().getRect());
            }
            return createInitialRect;
        }

        public int isTabular() {
            return this.mIsTabular;
        }

        public boolean logInfo() {
            Log.i(OCRResult.TAG, "=====================================================================");
            OCRResult.printRect(getUnionRect(), "OCRResult::Block[mUnionRect]", String.format(" contains %d Lines", new Object[]{Integer.valueOf(this.mLineDataList.size())}));
            OCRResult.printRect(super.getRect(), "OCRResult::Block[mRect]", String.format(" contains %d Lines", new Object[]{Integer.valueOf(this.mLineDataList.size())}));
            Log.i(OCRResult.TAG, "OCRResult::BlockData Text:");
            Iterator<LineData> it = this.mLineDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().logInfo()) {
                    return false;
                }
            }
            return true;
        }

        public boolean offset(int i2, int i7) {
            super.offset(i2, i7);
            Iterator<LineData> it = this.mLineDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().offset(i2, i7)) {
                    return false;
                }
            }
            return true;
        }

        public /* bridge */ /* synthetic */ boolean rotate(float f, PointF pointF) {
            return super.rotate(f, pointF);
        }

        public boolean scale(float f) {
            super.scale(f);
            Iterator<LineData> it = this.mLineDataList.iterator();
            while (it.hasNext()) {
                if (!it.next().scale(f)) {
                    return false;
                }
            }
            return true;
        }

        public /* bridge */ /* synthetic */ void setAngle(float f) {
            super.setAngle(f);
        }

        public void setLineDataList(ArrayList<LineData> arrayList) {
            this.mLineDataList.addAll(arrayList);
        }

        public /* bridge */ /* synthetic */ void setRect(Point[] pointArr) {
            super.setRect(pointArr);
        }

        public void setTabular(int i2) {
            this.mIsTabular = i2;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Log.i(OCRResult.TAG, "writeToParcel: BlockData");
            super.writeToParcel(parcel, i2);
            ArrayList<LineData> arrayList = this.mLineDataList;
            if (arrayList != null) {
                parcel.writeTypedList(arrayList);
            }
        }

        public BlockData() {
            this.mIsTabular = 0;
            this.mLineDataList = new ArrayList<>();
        }
    }

    public OCRResult(Parcel parcel) {
        ArrayList<BlockData> arrayList = new ArrayList<>();
        this.mBlockDataList = arrayList;
        parcel.readTypedList(arrayList, BlockData.CREATOR);
        ArrayList<TableData> arrayList2 = new ArrayList<>();
        this.mTableDataList = arrayList2;
        parcel.readTypedList(arrayList2, TableData.CREATOR);
        this.mProcessInfo = new ProcessInfo();
        this.mProcessInfo = (ProcessInfo) parcel.readTypedObject(ProcessInfo.CREATOR);
    }
}
