package com.samsung.android.gallery.module.fileio.compat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileOpApiLocalCloud extends FileOpApiLocal {
    public boolean copyOverwrite(FileOpJob fileOpJob) {
        if (!super.copyOverwrite(fileOpJob)) {
            return false;
        }
        SecMediaStoreApi.deleteCloudByServerPath(fileOpJob.target);
        return true;
    }

    public boolean renameOverwrite(FileOpJob fileOpJob) {
        if (!super.renameOverwrite(fileOpJob)) {
            return false;
        }
        SecMediaStoreApi.deleteCloudByServerPath(fileOpJob.target);
        SecMediaStoreApi.deleteCloudByServerId(fileOpJob.source.getCloudServerId());
        return true;
    }
}
