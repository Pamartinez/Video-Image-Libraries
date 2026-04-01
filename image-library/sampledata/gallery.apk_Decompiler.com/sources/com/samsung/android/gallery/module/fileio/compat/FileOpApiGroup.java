package com.samsung.android.gallery.module.fileio.compat;

import N2.j;
import android.content.ContentProviderOperation;
import android.media.MediaScannerConnection;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.cloud.SCloudHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SefFileUtils;
import f4.a;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileOpApiGroup extends FileOpApi {
    Consumer<Float> mGroupProgressListener;

    public FileOpApiGroup(Consumer<Float> consumer, BooleanSupplier booleanSupplier) {
        this.mGroupProgressListener = consumer;
        this.mCancelSignal = booleanSupplier;
    }

    private ArrayList<ContentProviderOperation> buildRenameBulk(List<FileOpJob> list) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        list.stream().map(new b(1)).forEach(new a(arrayList, 13));
        return arrayList;
    }

    private List<FileOpJob> buildSubJobs(FileOpJob fileOpJob) {
        List<FileOpJob> list = (List) fileOpJob.loadGroupItems().stream().map(new d(fileOpJob, FileUtils.getStorageName(fileOpJob.targetDir))).collect(Collectors.toList());
        int orElse = IntStream.range(0, list.size()).filter(new e(list, fileOpJob.source.getFileId())).findFirst().orElse(-1);
        if (orElse > 0) {
            String str = this.TAG;
            Log.i(str, "execute group. replace first with " + orElse);
            Optional.ofNullable(list.remove(orElse)).ifPresent(new f(0, list));
        }
        return list;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$buildSubJobs$4(List list, long j2, int i2) {
        if (((FileOpJob) list.get(i2)).source.getFileId() == j2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$execute$0(FileOpJob fileOpJob) {
        if (fileOpJob.isRename() || !fileOpJob.source.isLocal()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$execute$2(int i2) {
        return new String[i2];
    }

    private String makeCloudOnlyTarget(FileOpJob fileOpJob) {
        String nameFromPath = FileUtils.getNameFromPath(fileOpJob.source.getCloudServerPath());
        String newFileSetNumberForBurstShot = SCloudHelper.getNewFileSetNumberForBurstShot(AppResources.getAppContext(), fileOpJob.source, fileOpJob.target);
        if (!TextUtils.isEmpty(newFileSetNumberForBurstShot)) {
            String[] splitNameAndExtension = FileUtils.splitNameAndExtension(nameFromPath);
            Locale.getDefault();
            nameFromPath = splitNameAndExtension[0] + "_" + newFileSetNumberForBurstShot + "." + splitNameAndExtension[1];
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(fileOpJob.targetDir);
        return C0212a.p(sb2, File.separator, nameFromPath);
    }

    private int makeNewGroupId() {
        long currentTimeMillis = System.currentTimeMillis();
        long hashCode = (long) AppResources.getAppContext().hashCode();
        if (currentTimeMillis > hashCode) {
            currentTimeMillis -= hashCode;
        }
        return new Random(currentTimeMillis).nextInt(Integer.MAX_VALUE) + 1;
    }

    private Map<String, byte[]> makeNewGroupSefData(int i2, int i7) {
        if (GroupType.BURST.value == i2) {
            return SefFileUtils.getBurstShotSefDataMap(i7);
        }
        if (GroupType.SINGLE_TAKEN.value == i2) {
            return SefFileUtils.getSingleTakenSefDataMap(i7);
        }
        return new HashMap();
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [java.util.function.Predicate, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v5, types: [java.lang.Object, java.util.function.IntFunction] */
    public boolean execute(FileOpJob fileOpJob) {
        long currentTimeMillis = System.currentTimeMillis();
        fileOpJob.passList = new ArrayList<>();
        fileOpJob.failList = new ArrayList<>();
        List<FileOpJob> buildSubJobs = buildSubJobs(fileOpJob);
        ArrayList arrayList = new ArrayList();
        int makeNewGroupId = makeNewGroupId();
        fileOpJob.newGroupId = makeNewGroupId;
        Map<String, byte[]> makeNewGroupSefData = makeNewGroupSefData(fileOpJob.source.getGroupType(), makeNewGroupId);
        Iterator<FileOpJob> it = buildSubJobs.iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            FileOpJob next = it.next();
            if (this.mCancelSignal.getAsBoolean()) {
                Log.e(this.TAG, "CancelSignal detected while group operation");
                break;
            }
            if (next != null && next.prepareOrWait(2)) {
                if (next.source.isCloudOnly()) {
                    next.target = makeCloudOnlyTarget(next);
                } else if (fileOpJob.isRename()) {
                    arrayList.add(next);
                }
                if (super.execute(next)) {
                    if (next.isCopy()) {
                        SefFileUtils.updateFileWithSef(makeNewGroupSefData, next.target);
                    }
                    fileOpJob.passList.add(next);
                } else {
                    fileOpJob.failList.add(next);
                }
            }
            Consumer<Float> consumer = this.mGroupProgressListener;
            if (consumer != null) {
                i2++;
                consumer.accept(Float.valueOf(((float) i2) / ((float) buildSubJobs.size())));
            }
        }
        if (arrayList.size() > 0) {
            if (!MediaStoreApi.applyBatch(buildRenameBulk(arrayList))) {
                fileOpJob.failList.addAll(arrayList);
            } else {
                fileOpJob.passList.addAll(arrayList);
            }
            Consumer<Float> consumer2 = this.mGroupProgressListener;
            if (consumer2 != null) {
                consumer2.accept(Float.valueOf(1.0f));
            }
        }
        String[] strArr = (String[]) fileOpJob.passList.stream().filter(new Object()).map(new b(0)).toArray(new Object());
        if (strArr.length > 0) {
            MediaScanner.scanFiles(AppResources.getAppContext(), strArr, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
        }
        String str = this.TAG;
        A.a.A(new Object[]{fileOpJob.toGroupSummary(), j.g(new StringBuilder("rename="), arrayList), "scan=" + strArr.length, Long.valueOf(currentTimeMillis)}, new StringBuilder("execute group"), str);
        if (fileOpJob.passList.size() == 0) {
            fileOpJob.e(this.TAG, FileOpError.GroupOpFailed, "execute group all failed");
            return false;
        } else if (buildSubJobs.size() <= fileOpJob.passList.size()) {
            return true;
        } else {
            fileOpJob.e(this.TAG, FileOpError.GroupOpPartialFailed, "execute group partial failed" + Logger.v(Integer.valueOf(buildSubJobs.size()), Integer.valueOf(fileOpJob.passList.size())));
            return true;
        }
    }
}
