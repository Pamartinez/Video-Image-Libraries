package com.samsung.android.sdk.ocr;

import android.util.Log;
import com.samsung.android.ocr.MOCRResult;
import com.samsung.android.sdk.ocr.OCRResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCRConverter {
    private static final String TAG = "MOCRConverter";

    public static OCRResult.BlockData convertBlock(MOCRResult.Block block, OCRResult.BlockData blockData) {
        if (blockData == null) {
            blockData = new OCRResult.BlockData();
        } else {
            blockData.clear();
        }
        MOCRResult.Line[] lineArr = block.lines;
        if (lineArr == null) {
            Log.e(TAG, "[convertBlock] MOCRResult.Block.lines is null!");
        } else {
            for (MOCRResult.Line convertLine : lineArr) {
                blockData.add(convertLine(convertLine, (OCRResult.LineData) null));
            }
        }
        blockData.setRect(block.bRect);
        blockData.setTabular(block.isTabular);
        return blockData;
    }

    public static OCRResult.TableCellData convertCell(MOCRResult.TableCell tableCell, OCRResult.TableCellData tableCellData) {
        if (tableCellData == null) {
            tableCellData = new OCRResult.TableCellData();
        } else {
            tableCellData.clear();
        }
        tableCellData.setCellRect(tableCell.cellRect);
        tableCellData.setCellText(tableCell.cellText);
        return tableCellData;
    }

    public static OCRResult.CharData convertChar(MOCRResult.Char charR, OCRResult.CharData charData) {
        if (charData == null) {
            charData = new OCRResult.CharData();
        } else {
            charData.clear();
        }
        charData.setText(charR.getText());
        charData.setRect(charR.cRect);
        return charData;
    }

    public static OCRResult.LineData convertLine(MOCRResult.Line line, OCRResult.LineData lineData) {
        if (lineData == null) {
            lineData = new OCRResult.LineData();
        } else {
            lineData.clear();
        }
        MOCRResult.Word[] wordArr = line.words;
        if (wordArr == null) {
            Log.e(TAG, "[convertLine] MOCRResult.Line.words is null!");
        } else {
            for (MOCRResult.Word convertWord : wordArr) {
                lineData.add(convertWord(convertWord, (OCRResult.WordData) null));
            }
        }
        lineData.setRect(line.lRect);
        lineData.setCurved(line.isCurved);
        return lineData;
    }

    public static void convertPage(MOCRResult.Page page, OCRResult oCRResult) {
        if (oCRResult == null) {
            oCRResult = new OCRResult();
        } else {
            oCRResult.clearResult();
        }
        MOCRResult.Block[] blockArr = page.blocks;
        if (blockArr == null) {
            Log.e(TAG, "[convertPage] MOCRResult.Page.blocks is null!");
        } else {
            for (MOCRResult.Block convertBlock : blockArr) {
                oCRResult.add(convertBlock(convertBlock, (OCRResult.BlockData) null));
            }
        }
        MOCRResult.Table[] tableArr = page.tables;
        if (tableArr == null) {
            Log.e(TAG, "[convertPage] MOCRResult.Page.tables is null!");
        } else {
            for (MOCRResult.Table convertTable : tableArr) {
                oCRResult.add(convertTable(convertTable, (OCRResult.TableData) null));
            }
        }
        oCRResult.logInfo();
    }

    public static OCRResult.TableRowData convertRow(MOCRResult.TableRow tableRow, OCRResult.TableRowData tableRowData) {
        if (tableRowData == null) {
            tableRowData = new OCRResult.TableRowData();
        } else {
            tableRowData.clear();
        }
        MOCRResult.TableCell[] tableCellArr = tableRow.cells;
        if (tableCellArr == null) {
            Log.e(TAG, "[convertRow] MOCRResult.TableRow.cells is null!");
            return tableRowData;
        }
        for (MOCRResult.TableCell convertCell : tableCellArr) {
            tableRowData.add(convertCell(convertCell, (OCRResult.TableCellData) null));
        }
        return tableRowData;
    }

    public static OCRResult.TableData convertTable(MOCRResult.Table table, OCRResult.TableData tableData) {
        if (tableData == null) {
            tableData = new OCRResult.TableData();
        } else {
            tableData.clear();
        }
        MOCRResult.TableRow[] tableRowArr = table.rows;
        if (tableRowArr == null) {
            Log.e(TAG, "[convertTable] MOCRResult.Table.rows is null!");
        } else {
            for (MOCRResult.TableRow convertRow : tableRowArr) {
                tableData.add(convertRow(convertRow, (OCRResult.TableRowData) null));
            }
        }
        tableData.setTableRect(table.tableRect);
        return tableData;
    }

    public static OCRResult.WordData convertWord(MOCRResult.Word word, OCRResult.WordData wordData) {
        if (wordData == null) {
            wordData = new OCRResult.WordData();
        } else {
            wordData.clear();
        }
        MOCRResult.Char[] charArr = word.chars;
        if (charArr == null) {
            Log.e(TAG, "[convertWord] MOCRResult.Word.chars is null!");
        } else {
            for (MOCRResult.Char convertChar : charArr) {
                wordData.add(convertChar(convertChar, (OCRResult.CharData) null));
            }
        }
        wordData.setRect(word.wRect);
        wordData.setPoly(word.wPoly);
        return wordData;
    }
}
