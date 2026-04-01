package com.samsung.android.imagetranslation.data;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LttOcrResult implements Parcelable {
    public static final Parcelable.Creator<LttOcrResult> CREATOR = new Parcelable.Creator<LttOcrResult>() {
        public LttOcrResult createFromParcel(Parcel parcel) {
            return new LttOcrResult(parcel);
        }

        public LttOcrResult[] newArray(int i2) {
            return new LttOcrResult[i2];
        }
    };
    private final List<BlockInfo> blockInfoList;
    private List<String> languageTagList;

    public LttOcrResult(List<BlockInfo> list) {
        this.blockInfoList = list;
        this.languageTagList = new ArrayList();
    }

    public int describeContents() {
        return 0;
    }

    public List<BlockInfo> getBlockInfoList() {
        return this.blockInfoList;
    }

    public List<String> getLanguageTagList() {
        return this.languageTagList;
    }

    public List<LineInfo> getLineInfoList() {
        ArrayList arrayList = new ArrayList();
        for (BlockInfo lineInfo : this.blockInfoList) {
            arrayList.addAll(lineInfo.getLineInfo());
        }
        return arrayList;
    }

    public List<WordInfo> getWordInfoList() {
        ArrayList arrayList = new ArrayList();
        for (BlockInfo lineInfo : this.blockInfoList) {
            for (LineInfo wordInfo : lineInfo.getLineInfo()) {
                arrayList.addAll(wordInfo.getWordInfo());
            }
        }
        return arrayList;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.blockInfoList);
    }

    public LttOcrResult(List<BlockInfo> list, List<String> list2) {
        this.blockInfoList = list;
        this.languageTagList = list2;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CharInfo implements Parcelable {
        public static final Parcelable.Creator<CharInfo> CREATOR = new Parcelable.Creator<CharInfo>() {
            public CharInfo createFromParcel(Parcel parcel) {
                return new CharInfo(parcel);
            }

            public CharInfo[] newArray(int i2) {
                return new CharInfo[i2];
            }
        };
        private final String charString;
        private boolean isLinked;
        private final Point[] poly;
        private final Rect rect;

        public CharInfo(String str, Rect rect2, Point[] pointArr) {
            this.charString = str;
            this.rect = rect2;
            this.poly = pointArr;
            this.isLinked = false;
        }

        public int describeContents() {
            return 0;
        }

        public String getChar() {
            return this.charString;
        }

        public Point[] getPoly() {
            return this.poly;
        }

        public Rect getRect() {
            return this.rect;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.charString);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray(this.poly, i2);
            parcel.writeByte(this.isLinked ? (byte) 1 : 0);
        }

        public CharInfo(Parcel parcel) {
            this.charString = parcel.readString();
            this.rect = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.poly = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.isLinked = parcel.readByte() != 0;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LineInfo implements Parcelable {
        public static final Parcelable.Creator<LineInfo> CREATOR = new Parcelable.Creator<LineInfo>() {
            public LineInfo createFromParcel(Parcel parcel) {
                return new LineInfo(parcel);
            }

            public LineInfo[] newArray(int i2) {
                return new LineInfo[i2];
            }
        };
        private String langCode;
        private Point[] poly;
        private Rect rect;
        private final List<WordInfo> wordInfoList;

        public LineInfo(ArrayList<WordInfo> arrayList, Rect rect2, Point[] pointArr, String str) {
            this.wordInfoList = arrayList;
            this.rect = rect2;
            this.poly = pointArr;
            this.langCode = str;
        }

        public int describeContents() {
            return 0;
        }

        public String getLangCode() {
            return this.langCode;
        }

        public Point[] getPoly() {
            return this.poly;
        }

        public Rect getRect() {
            return this.rect;
        }

        public String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (WordInfo string : this.wordInfoList) {
                sb2.append(string.getString());
            }
            return sb2.toString();
        }

        public List<WordInfo> getWordInfo() {
            return this.wordInfoList;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeTypedList(this.wordInfoList);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray(this.poly, i2);
            parcel.writeString(this.langCode);
        }

        public LineInfo(Parcel parcel) {
            ArrayList arrayList = new ArrayList();
            this.wordInfoList = arrayList;
            parcel.readTypedList(arrayList, WordInfo.CREATOR);
            this.rect = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.poly = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.langCode = parcel.readString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WordInfo implements Parcelable {
        public static final Parcelable.Creator<WordInfo> CREATOR = new Parcelable.Creator<WordInfo>() {
            public WordInfo createFromParcel(Parcel parcel) {
                return new WordInfo(parcel);
            }

            public WordInfo[] newArray(int i2) {
                return new WordInfo[i2];
            }
        };
        private final List<CharInfo> charInfoList;
        private final String langCode;
        private final Point[] poly;
        private final Rect rect;

        public WordInfo(ArrayList<CharInfo> arrayList, Rect rect2, Point[] pointArr, String str) {
            this.charInfoList = arrayList;
            this.rect = rect2;
            this.poly = pointArr;
            this.langCode = str;
        }

        public int describeContents() {
            return 0;
        }

        public List<CharInfo> getCharInfo() {
            return this.charInfoList;
        }

        public String getLangCode() {
            return this.langCode;
        }

        public Point[] getPoly() {
            return this.poly;
        }

        public Rect getRect() {
            return this.rect;
        }

        public String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (CharInfo charInfo : this.charInfoList) {
                sb2.append(charInfo.getChar());
            }
            return sb2.toString();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeTypedList(this.charInfoList);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray(this.poly, i2);
            parcel.writeString(this.langCode);
        }

        public WordInfo(Parcel parcel) {
            ArrayList arrayList = new ArrayList();
            this.charInfoList = arrayList;
            parcel.readTypedList(arrayList, CharInfo.CREATOR);
            this.rect = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.poly = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.langCode = parcel.readString();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BlockInfo implements Parcelable {
        public static final Parcelable.Creator<BlockInfo> CREATOR = new Parcelable.Creator<BlockInfo>() {
            public BlockInfo createFromParcel(Parcel parcel) {
                return new BlockInfo(parcel);
            }

            public BlockInfo[] newArray(int i2) {
                return new BlockInfo[i2];
            }
        };
        private final int blockType;
        private final String langCode;
        private final List<LineInfo> lineInfoList;
        private final Point[] poly;
        private final Rect rect;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum BLOCK_TYPE {
            DEFAULT,
            TABULAR
        }

        public BlockInfo(List<LineInfo> list, Rect rect2, Point[] pointArr, String str, int i2) {
            this.lineInfoList = list;
            this.rect = rect2;
            this.poly = pointArr;
            this.langCode = str;
            this.blockType = i2;
        }

        public int describeContents() {
            return 0;
        }

        public int getBlockType() {
            return this.blockType;
        }

        public String getLangCode() {
            return this.langCode;
        }

        public List<LineInfo> getLineInfo() {
            return this.lineInfoList;
        }

        public Point[] getPoly() {
            return this.poly;
        }

        public Rect getRect() {
            return this.rect;
        }

        public String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (LineInfo string : this.lineInfoList) {
                sb2.append(string.getString());
            }
            return sb2.toString();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeTypedList(this.lineInfoList);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray(this.poly, i2);
            parcel.writeString(this.langCode);
            parcel.writeInt(this.blockType);
        }

        public BlockInfo(Parcel parcel) {
            ArrayList arrayList = new ArrayList();
            this.lineInfoList = arrayList;
            parcel.readTypedList(arrayList, LineInfo.CREATOR);
            this.rect = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.poly = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.langCode = parcel.readString();
            this.blockType = parcel.readInt();
        }
    }

    public LttOcrResult(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.blockInfoList = arrayList;
        parcel.readTypedList(arrayList, BlockInfo.CREATOR);
    }
}
