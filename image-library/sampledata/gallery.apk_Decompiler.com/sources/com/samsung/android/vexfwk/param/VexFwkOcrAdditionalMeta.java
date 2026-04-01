package com.samsung.android.vexfwk.param;

import A.a;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.b;
import ne.C1202t;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0004\u0019\u001a\u001b\u001cB/\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0007\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta$TableInfo;", "tableInfoList", "", "lineCurveInfo", "isHandWritten", "<init>", "(Ljava/util/List;Ljava/util/List;Z)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getTableInfoList", "()Ljava/util/List;", "getLineCurveInfo", "Z", "()Z", "Companion", "TableCellInfo", "TableRowInfo", "TableInfo", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrAdditionalMeta implements Parcelable {
    public static final Parcelable.Creator<VexFwkOcrAdditionalMeta> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final boolean isHandWritten;
    private final List<Boolean> lineCurveInfo;
    private final List<TableInfo> tableInfoList;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkOcrAdditionalMeta> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkOcrAdditionalMeta.CREATOR;
        }

        private Companion() {
            super(new b(2));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkOcrAdditionalMeta> {
        public final VexFwkOcrAdditionalMeta createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            boolean z = false;
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.a(TableInfo.CREATOR, parcel, arrayList, i2, 1);
            }
            int readInt2 = parcel.readInt();
            ArrayList arrayList2 = new ArrayList(readInt2);
            for (int i7 = 0; i7 != readInt2; i7++) {
                arrayList2.add(Boolean.valueOf(parcel.readInt() != 0));
            }
            if (parcel.readInt() != 0) {
                z = true;
            }
            return new VexFwkOcrAdditionalMeta(arrayList, arrayList2, z);
        }

        public final VexFwkOcrAdditionalMeta[] newArray(int i2) {
            return new VexFwkOcrAdditionalMeta[i2];
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta$TableInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta$TableRowInfo;", "tableRowInfo", "Landroid/graphics/Rect;", "tableBoundary", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getTableRowInfo", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getTableBoundary", "()Landroid/graphics/Rect;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableInfo implements Parcelable {
        public static final Parcelable.Creator<TableInfo> CREATOR = new Creator();
        private final Rect tableBoundary;
        private final List<TableRowInfo> tableRowInfo;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<TableInfo> {
            public final TableInfo createFromParcel(Parcel parcel) {
                j.e(parcel, "parcel");
                int readInt = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt);
                int i2 = 0;
                while (i2 != readInt) {
                    i2 = a.a(TableRowInfo.CREATOR, parcel, arrayList, i2, 1);
                }
                return new TableInfo(arrayList, (Rect) parcel.readParcelable(TableInfo.class.getClassLoader()));
            }

            public final TableInfo[] newArray(int i2) {
                return new TableInfo[i2];
            }
        }

        public TableInfo(List<TableRowInfo> list, Rect rect) {
            j.e(list, "tableRowInfo");
            j.e(rect, "tableBoundary");
            this.tableRowInfo = list;
            this.tableBoundary = rect;
        }

        public final int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof TableInfo) && this.tableRowInfo.size() == ((TableInfo) obj).tableRowInfo.size()) {
                return true;
            }
            return false;
        }

        public final Rect getTableBoundary() {
            return this.tableBoundary;
        }

        public final List<TableRowInfo> getTableRowInfo() {
            return this.tableRowInfo;
        }

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.tableRowInfo);
            while (j2.hasNext()) {
                ((TableRowInfo) j2.next()).writeToParcel(parcel, i2);
            }
            parcel.writeParcelable(this.tableBoundary, i2);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta$TableRowInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta$TableCellInfo;", "tableCellInfo", "<init>", "(Ljava/util/List;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getTableCellInfo", "()Ljava/util/List;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableRowInfo implements Parcelable {
        public static final Parcelable.Creator<TableRowInfo> CREATOR = new Creator();
        private final List<TableCellInfo> tableCellInfo;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<TableRowInfo> {
            public final TableRowInfo createFromParcel(Parcel parcel) {
                j.e(parcel, "parcel");
                int readInt = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt);
                int i2 = 0;
                while (i2 != readInt) {
                    i2 = a.a(TableCellInfo.CREATOR, parcel, arrayList, i2, 1);
                }
                return new TableRowInfo(arrayList);
            }

            public final TableRowInfo[] newArray(int i2) {
                return new TableRowInfo[i2];
            }
        }

        public TableRowInfo(List<TableCellInfo> list) {
            j.e(list, "tableCellInfo");
            this.tableCellInfo = list;
        }

        public final int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof TableRowInfo) && this.tableCellInfo.size() == ((TableRowInfo) obj).tableCellInfo.size()) {
                return true;
            }
            return false;
        }

        public final List<TableCellInfo> getTableCellInfo() {
            return this.tableCellInfo;
        }

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.tableCellInfo);
            while (j2.hasNext()) {
                ((TableCellInfo) j2.next()).writeToParcel(parcel, i2);
            }
        }
    }

    public VexFwkOcrAdditionalMeta(List<TableInfo> list, List<Boolean> list2, boolean z) {
        j.e(list, "tableInfoList");
        j.e(list2, "lineCurveInfo");
        this.tableInfoList = list;
        this.lineCurveInfo = list2;
        this.isHandWritten = z;
    }

    public final int describeContents() {
        return 0;
    }

    public final List<Boolean> getLineCurveInfo() {
        return this.lineCurveInfo;
    }

    public final List<TableInfo> getTableInfoList() {
        return this.tableInfoList;
    }

    public final boolean isHandWritten() {
        return this.isHandWritten;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Iterator j2 = a.j(parcel, this.tableInfoList);
        while (j2.hasNext()) {
            ((TableInfo) j2.next()).writeToParcel(parcel, i2);
        }
        Iterator j3 = a.j(parcel, this.lineCurveInfo);
        while (j3.hasNext()) {
            parcel.writeInt(((Boolean) j3.next()).booleanValue() ? 1 : 0);
        }
        parcel.writeInt(this.isHandWritten ? 1 : 0);
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta$TableCellInfo;", "Landroid/os/Parcelable;", "Landroid/graphics/Rect;", "cellBoundary", "", "cellText", "<init>", "(Landroid/graphics/Rect;Ljava/lang/String;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/graphics/Rect;", "getCellBoundary", "()Landroid/graphics/Rect;", "Ljava/lang/String;", "getCellText", "()Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableCellInfo implements Parcelable {
        public static final Parcelable.Creator<TableCellInfo> CREATOR = new Creator();
        private final Rect cellBoundary;
        private final String cellText;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<TableCellInfo> {
            public final TableCellInfo createFromParcel(Parcel parcel) {
                j.e(parcel, "parcel");
                return new TableCellInfo((Rect) parcel.readParcelable(TableCellInfo.class.getClassLoader()), parcel.readString());
            }

            public final TableCellInfo[] newArray(int i2) {
                return new TableCellInfo[i2];
            }
        }

        public TableCellInfo(Rect rect, String str) {
            j.e(rect, "cellBoundary");
            j.e(str, "cellText");
            this.cellBoundary = rect;
            this.cellText = str;
        }

        public final int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TableCellInfo)) {
                return false;
            }
            TableCellInfo tableCellInfo = (TableCellInfo) obj;
            if (j.a(this.cellText, tableCellInfo.cellText) && j.a(this.cellBoundary, tableCellInfo.cellBoundary)) {
                return true;
            }
            return false;
        }

        public final Rect getCellBoundary() {
            return this.cellBoundary;
        }

        public final String getCellText() {
            return this.cellText;
        }

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            parcel.writeParcelable(this.cellBoundary, i2);
            parcel.writeString(this.cellText);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TableCellInfo(Rect rect, String str, int i2, e eVar) {
            this(rect, (i2 & 2) != 0 ? "" : str);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VexFwkOcrAdditionalMeta(List list, List list2, boolean z, int i2, e eVar) {
        this(list, (i2 & 2) != 0 ? C1202t.d : list2, (i2 & 4) != 0 ? false : z);
    }
}
