package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH'¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000b\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH'¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0010\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u0010\u0010\u0012J\u0017\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0013J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H'¢\u0006\u0004\b\u0015\u0010\u0016ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0003"}, d2 = {"Landroidx/work/impl/model/SystemIdInfoDao;", "", "Landroidx/work/impl/model/SystemIdInfo;", "systemIdInfo", "Lme/x;", "insertSystemIdInfo", "(Landroidx/work/impl/model/SystemIdInfo;)V", "", "workSpecId", "", "generation", "getSystemIdInfo", "(Ljava/lang/String;I)Landroidx/work/impl/model/SystemIdInfo;", "Landroidx/work/impl/model/WorkGenerationalId;", "id", "(Landroidx/work/impl/model/WorkGenerationalId;)Landroidx/work/impl/model/SystemIdInfo;", "removeSystemIdInfo", "(Ljava/lang/String;I)V", "(Ljava/lang/String;)V", "(Landroidx/work/impl/model/WorkGenerationalId;)V", "", "getWorkSpecIds", "()Ljava/util/List;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SystemIdInfoDao {
    SystemIdInfo getSystemIdInfo(WorkGenerationalId workGenerationalId) {
        j.e(workGenerationalId, "id");
        return getSystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration());
    }

    SystemIdInfo getSystemIdInfo(String str, int i2);

    List<String> getWorkSpecIds();

    void insertSystemIdInfo(SystemIdInfo systemIdInfo);

    void removeSystemIdInfo(WorkGenerationalId workGenerationalId) {
        j.e(workGenerationalId, "id");
        removeSystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration());
    }

    void removeSystemIdInfo(String str);

    void removeSystemIdInfo(String str, int i2);
}
