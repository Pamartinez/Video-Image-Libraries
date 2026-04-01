package com.samsung.android.gallery.module.fileio.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum FileOpResult {
    OP_NONE(-2),
    OP_NOT_FOUND_OPERATION(-1),
    OP_NOT_ENOUGH_STORAGE(0),
    OP_LOCAL_OK(1),
    OP_LOCAL_FAIL(2),
    OP_CANCEL(3),
    OP_FAIL_SYNC_OFF(4),
    OP_LOCAL_OK_SYNC_OFF(5),
    OP_ITEMS_ALL_NULL(6),
    OP_ADDING_FAIL_WRONG_ITEMS(7),
    OP_LOAD_FAIL_OF_INTENT_VALUE(8),
    OP_PATH_NOT_MIGRATED(9),
    OP_STORAGE_STATE_FAIL(10),
    OP_PPP_FAIL(11),
    OP_CLOUD_OK(100),
    OP_CLOUD_PARTIAL_OK(101),
    OP_CLOUD_FAIL(200),
    OP_CLOUD_QUOTA_FAIL(201);
    
    private final int mValue;

    private FileOpResult(int i2) {
        this.mValue = i2;
    }

    public static boolean isCanceled(FileOpResult fileOpResult) {
        if (fileOpResult == OP_CANCEL) {
            return true;
        }
        return false;
    }

    public static boolean isNotEnoughStorage(FileOpResult fileOpResult) {
        if (fileOpResult == OP_NOT_ENOUGH_STORAGE) {
            return true;
        }
        return false;
    }

    public static boolean isSuccess(FileOpResult fileOpResult) {
        if (fileOpResult == OP_LOCAL_OK || fileOpResult == OP_LOCAL_OK_SYNC_OFF || fileOpResult == OP_CLOUD_OK || fileOpResult == OP_CLOUD_PARTIAL_OK) {
            return true;
        }
        return false;
    }

    public static FileOpResult toResult(int i2) {
        if (i2 == 100) {
            return OP_CLOUD_OK;
        }
        if (i2 == 101) {
            return OP_CLOUD_PARTIAL_OK;
        }
        if (i2 == 200) {
            return OP_CLOUD_FAIL;
        }
        if (i2 == 201) {
            return OP_CLOUD_QUOTA_FAIL;
        }
        switch (i2) {
            case -1:
                return OP_NOT_FOUND_OPERATION;
            case 0:
                return OP_NOT_ENOUGH_STORAGE;
            case 1:
                return OP_LOCAL_OK;
            case 2:
                return OP_LOCAL_FAIL;
            case 3:
                return OP_CANCEL;
            case 4:
                return OP_FAIL_SYNC_OFF;
            case 5:
                return OP_LOCAL_OK_SYNC_OFF;
            case 6:
                return OP_ITEMS_ALL_NULL;
            case 7:
                return OP_ADDING_FAIL_WRONG_ITEMS;
            case 8:
                return OP_LOAD_FAIL_OF_INTENT_VALUE;
            case 9:
                return OP_PATH_NOT_MIGRATED;
            case 10:
                return OP_STORAGE_STATE_FAIL;
            case 11:
                return OP_PPP_FAIL;
            default:
                return OP_NONE;
        }
    }

    public boolean equal(FileOpResult fileOpResult) {
        if (fileOpResult.toInt() == this.mValue) {
            return true;
        }
        return false;
    }

    public int toInt() {
        return this.mValue;
    }
}
