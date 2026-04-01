package com.samsung.android.ocr;

import android.graphics.Point;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCRResult {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Block {
        public Point[] bRect;
        public int isTabular;
        private int lang;
        public int lineCount;
        public Line[] lines;

        public String getText() {
            StringBuilder sb2 = new StringBuilder();
            int i2 = 0;
            while (true) {
                Line[] lineArr = this.lines;
                if (i2 >= lineArr.length) {
                    return sb2.toString();
                }
                sb2.append(lineArr[i2].getText());
                if (i2 < this.lines.length) {
                    sb2.append("\n");
                }
                i2++;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Char {
        public Point[] cRect;
        public int unicode;

        public String getText() {
            return Character.toString((char) this.unicode);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DocColumn {
        public int[] blockIds;
        public Point[] colRect;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Document {
        public int columnCount;
        public DocColumn[] columns;
        public Point[] docRect;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Layout {
        public Point[] lRect;
        public int layoutType;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Line {
        public boolean isCurved;
        public Point[] lRect;
        public int lang;
        public int wordCount;
        public Word[] words;

        public String getText() {
            StringBuilder sb2 = new StringBuilder();
            int i2 = 0;
            while (true) {
                Word[] wordArr = this.words;
                if (i2 >= wordArr.length) {
                    return sb2.toString().trim();
                }
                Word word = wordArr[i2];
                if (word.getText() != null) {
                    sb2.append(word.getText());
                    if (i2 < this.words.length) {
                        sb2.append(" ");
                    }
                }
                i2++;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Page {
        private static final String TAG = "Page";
        public int blockCount;
        public Block[] blocks;
        public boolean debugViTExec;
        public Document document;
        public Layout[] layouts;
        public int ocrLang;
        public Table[] tables;

        public String getText() {
            StringBuilder sb2 = new StringBuilder();
            MOCRLog.d(TAG, this.blockCount + " blocks");
            if (this.blockCount > 0) {
                for (Block text : this.blocks) {
                    sb2.append(text.getText());
                }
            }
            return sb2.toString().trim();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Table {
        public TableCell[] cells;
        int columnCount;
        public TableColumn[] columns;
        int rowCount;
        public TableRow[] rows;
        public Point[] tableRect;

        public String getText() {
            StringBuilder sb2 = new StringBuilder("<table border=1 style='text-align:center;'>\n");
            TableRow[] tableRowArr = this.rows;
            if (tableRowArr != null) {
                for (TableRow tableRow : tableRowArr) {
                    sb2.append("<tr>\n");
                    TableCell[] tableCellArr = tableRow.cells;
                    if (tableCellArr != null) {
                        for (TableCell tableCell : tableCellArr) {
                            if (tableCell.colSpan > 1 || tableCell.rowSpan > 1) {
                                sb2.append("<td colspan=\"" + tableCell.colSpan + "\" rowspan=\"" + tableCell.rowSpan + "\" >\n");
                            } else {
                                sb2.append("<td>\n");
                            }
                            sb2.append(tableCell.cellText + "\n");
                            sb2.append("</td>\n");
                        }
                    }
                    sb2.append("</tr>\n");
                }
            }
            sb2.append("</table>\n");
            return sb2.toString().trim();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TableCell {
        public Point[] cellRect;
        public String cellText;
        public int colSpan;
        public int rowSpan;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TableColumn {
        public TableCell[] cells;
        public Point[] colRect;
        int rowCount;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TableRow {
        public TableCell[] cells;
        int columnCount;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Word {
        public int charCount;
        public Char[] chars;
        public float conf;
        public int lang;
        public Point[] tlPoly;
        public Point[] wPoly;
        public Point[] wRect;
        public String wordText;

        public String getText() {
            return this.wordText;
        }
    }
}
