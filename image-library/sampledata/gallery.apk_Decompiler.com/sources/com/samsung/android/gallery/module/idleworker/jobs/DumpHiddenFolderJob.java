package com.samsung.android.gallery.module.idleworker.jobs;

import A.a;
import android.content.Context;
import com.samsung.android.gallery.database.dal.DbDump;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DumpHiddenFolderJob extends IdleWorkerJob {
    public DumpHiddenFolderJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    public void execute(Context context) {
        try {
            DbDump.dumpQuery("select bucket_display_name,bucket_id from files where is_hide=1 group by bucket_id", FileUtils.getHiddenLogFile(context), false);
        } catch (Exception e) {
            a.s(e, new StringBuilder("dump hidden log failed. e="), this.TAG);
        }
    }
}
