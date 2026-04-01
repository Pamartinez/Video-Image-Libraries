package com.samsung.android.sdk.ocr;

import com.samsung.android.app.sdk.deepsky.contract.search.Contract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum RecognizerApi {
    IS_PRINTED(0, "isPrinted"),
    IS_HANDWRITTEN(1, "isHandwritten"),
    HAS_TEXT1(2, "hasText1"),
    HAS_TEXT2(3, "hasText2"),
    DETECT_TEXT(4, "detectText"),
    DETECT(5, "detect"),
    DETECT_BLOCK2(6, "detectBlock2"),
    DETECT_BLOCK3(7, "detectBlock3"),
    RECOGNIZE(8, "recognize"),
    RECOGNIZE_BLOCK_AT(9, "recognizeBlockAt"),
    CANCEL(10, Contract.COMMAND_ID_CANCEL),
    CLOSE(11, "close");
    
    private final int apiIndex;
    private final String apiName;

    private RecognizerApi(int i2, String str) {
        this.apiIndex = i2;
        this.apiName = str;
    }

    public static RecognizerApi from(String str) {
        for (RecognizerApi recognizerApi : values()) {
            if (str.equals(recognizerApi.toName())) {
                return recognizerApi;
            }
        }
        return IS_HANDWRITTEN;
    }

    public int toIndex() {
        return this.apiIndex;
    }

    public String toName() {
        return this.apiName;
    }
}
