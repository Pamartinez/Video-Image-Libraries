package com.samsung.android.gallery.module.fileio.abstraction;

import com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface;
import com.samsung.android.gallery.module.fileio.database.cmh.CmhCloudDbOperation;
import com.samsung.android.gallery.module.fileio.database.cmh.CmhGroupMediaDbOperation;
import com.samsung.android.gallery.module.fileio.database.cmh.CmhLocalDbOperation;
import com.samsung.android.gallery.module.fileio.database.mp.MpCloudDbOperation;
import com.samsung.android.gallery.module.fileio.database.mp.MpGroupMediaDbOperation;
import com.samsung.android.gallery.module.fileio.database.mp.MpLocalDbOperation;
import com.samsung.android.gallery.module.fileio.database.mpq.MpQCloudDbOperation;
import com.samsung.android.gallery.module.fileio.database.mpq.MpQGroupMediaDbOperation;
import com.samsung.android.gallery.module.fileio.database.mpq.MpQLocalDbOperation;
import com.samsung.android.gallery.module.fileio.database.mpr.MpRCloudDbOperation;
import com.samsung.android.gallery.module.fileio.database.mpr.MpRGroupMediaDbOperation;
import com.samsung.android.gallery.module.fileio.database.mpr.MpRLocalDbOperation;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class DbOperationFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FactoryHolder {
        static final DbOperationInterface[] sInstance = create();

        private static DbOperationInterface[] create() {
            DbOperationInterface[] dbOperationInterfaceArr = new DbOperationInterface[3];
            if (!Features.isEnabled(Features.USE_NEWMP)) {
                dbOperationInterfaceArr[0] = new CmhLocalDbOperation();
                dbOperationInterfaceArr[1] = new CmhCloudDbOperation();
                dbOperationInterfaceArr[2] = new CmhGroupMediaDbOperation();
                return dbOperationInterfaceArr;
            } else if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                dbOperationInterfaceArr[0] = new MpRLocalDbOperation();
                dbOperationInterfaceArr[1] = new MpRCloudDbOperation();
                dbOperationInterfaceArr[2] = new MpRGroupMediaDbOperation();
                return dbOperationInterfaceArr;
            } else if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                dbOperationInterfaceArr[0] = new MpQLocalDbOperation();
                dbOperationInterfaceArr[1] = new MpQCloudDbOperation();
                dbOperationInterfaceArr[2] = new MpQGroupMediaDbOperation();
                return dbOperationInterfaceArr;
            } else {
                dbOperationInterfaceArr[0] = new MpLocalDbOperation();
                dbOperationInterfaceArr[1] = new MpCloudDbOperation();
                dbOperationInterfaceArr[2] = new MpGroupMediaDbOperation();
                return dbOperationInterfaceArr;
            }
        }
    }

    public static DbOperationInterface getFactory(int i2) {
        return FactoryHolder.sInstance[i2];
    }
}
