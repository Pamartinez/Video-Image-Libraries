package com.samsung.android.gallery.module.idleworker.jobs;

import A.a;
import android.content.Context;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.mdebase.utils.MdeMetadataParser;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import h4.C0464a;
import java.util.List;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanUpMdeJob extends IdleWorkerJob {
    public CleanUpMdeJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    private void clearRemainedEditedItemFile() {
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            try {
                List<String> remainedItemInstantMetaDataList = new MdeDatabase().getRemainedItemInstantMetaDataList();
                deleteRemainedEditedFile(remainedItemInstantMetaDataList);
                deleteRemainedEditedThumbnail(remainedItemInstantMetaDataList);
            } catch (Exception e) {
                a.s(e, new StringBuilder("clearRemainedEditedItemFile failed. e="), this.TAG);
            }
        }
    }

    private void deleteRemainedEditedFile(List<String> list) {
        list.stream().filter(new C0464a(13)).forEach(new B(11));
    }

    private void deleteRemainedEditedThumbnail(List<String> list) {
        list.stream().filter(new C0464a(13)).forEach(new B(10));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteRemainedEditedFile$0(String str) {
        String editedFilePathFromInstantMetadata = MdeMetadataParser.getEditedFilePathFromInstantMetadata(str);
        if (editedFilePathFromInstantMetadata != null && editedFilePathFromInstantMetadata.startsWith("/data/sec/sems/")) {
            FileUtils.delete(editedFilePathFromInstantMetadata);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteRemainedEditedThumbnail$1(String str) {
        String instantThumbnailPathFromInstantMetadata = MdeMetadataParser.getInstantThumbnailPathFromInstantMetadata(str);
        if (instantThumbnailPathFromInstantMetadata != null && instantThumbnailPathFromInstantMetadata.startsWith("/data/sec/sems/")) {
            FileUtils.delete(instantThumbnailPathFromInstantMetadata);
        }
    }

    public void execute(Context context) {
        MdeSharingService.getInstance().clearShareServiceStatus();
        clearRemainedEditedItemFile();
    }
}
