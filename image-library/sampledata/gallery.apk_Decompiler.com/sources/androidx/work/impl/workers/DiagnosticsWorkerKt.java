package androidx.work.impl.workers;

import Ae.b;
import androidx.work.Logger;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.model.WorkTagDao;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001a/\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\t\u001a.\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"TAG", "", "workSpecRow", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "name", "systemId", "", "tags", "(Landroidx/work/impl/model/WorkSpec;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Ljava/lang/String;", "workSpecRows", "workNameDao", "Landroidx/work/impl/model/WorkNameDao;", "workTagDao", "Landroidx/work/impl/model/WorkTagDao;", "systemIdInfoDao", "Landroidx/work/impl/model/SystemIdInfoDao;", "workSpecs", "", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DiagnosticsWorkerKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("DiagnosticsWrkr");
        j.d(tagWithPrefix, "tagWithPrefix(\"DiagnosticsWrkr\")");
        TAG = tagWithPrefix;
    }

    private static final String workSpecRow(WorkSpec workSpec, String str, Integer num, String str2) {
        StringBuilder sb2 = new StringBuilder("\n");
        sb2.append(workSpec.id);
        sb2.append("\t ");
        sb2.append(workSpec.workerClassName);
        sb2.append("\t ");
        sb2.append(num);
        sb2.append("\t ");
        sb2.append(workSpec.state.name());
        sb2.append("\t ");
        sb2.append(str);
        sb2.append("\t ");
        return C0086a.m(sb2, str2, 9);
    }

    /* access modifiers changed from: private */
    public static final String workSpecRows(WorkNameDao workNameDao, WorkTagDao workTagDao, SystemIdInfoDao systemIdInfoDao, List<WorkSpec> list) {
        Integer num;
        StringBuilder sb2 = new StringBuilder("\n Id \t Class Name\t Job Id\t State\t Unique Name\t Tags\t");
        for (WorkSpec workSpec : list) {
            SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(WorkSpecKt.generationalId(workSpec));
            if (systemIdInfo != null) {
                num = Integer.valueOf(systemIdInfo.systemId);
            } else {
                num = null;
            }
            sb2.append(workSpecRow(workSpec, C1194l.R0(workNameDao.getNamesForWorkSpecId(workSpec.id), GlobalPostProcInternalPPInterface.SPLIT_REGEX, (String) null, (String) null, (b) null, 62), num, C1194l.R0(workTagDao.getTagsForWorkSpecId(workSpec.id), GlobalPostProcInternalPPInterface.SPLIT_REGEX, (String) null, (String) null, (b) null, 62)));
        }
        String sb3 = sb2.toString();
        j.d(sb3, "StringBuilder().apply(builderAction).toString()");
        return sb3;
    }
}
