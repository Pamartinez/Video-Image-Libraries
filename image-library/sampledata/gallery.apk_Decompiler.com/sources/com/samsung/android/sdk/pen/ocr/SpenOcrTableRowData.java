package com.samsung.android.sdk.pen.ocr;

import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrTableRowData {
    private static final String TAG = "SpenOcrTableRowData";
    private ArrayList<SpenOcrTableCellData> mCellDataList;

    public SpenOcrTableRowData() {
        this.mCellDataList = null;
        this.mCellDataList = new ArrayList<>();
        clear();
    }

    public void add(SpenOcrTableCellData spenOcrTableCellData) {
        this.mCellDataList.add(spenOcrTableCellData);
    }

    public void clear() {
        this.mCellDataList.clear();
    }

    public ArrayList<SpenOcrTableCellData> getCellDataList() {
        return this.mCellDataList;
    }

    public final String getText() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<SpenOcrTableCellData> it = this.mCellDataList.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().getText());
            sb2.append(" ");
        }
        return sb2.toString();
    }
}
