package D9;

import android.content.Context;
import android.provider.Settings;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.work.impl.WorkDatabase;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.scsp.common.FeatureConfigurator;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import com.samsung.scsp.framework.core.util.HashUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements GroupApi.GroupResultCallback, FaultBarrier.ThrowableSupplier, SupportSQLiteOpenHelper.Factory {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;

    public /* synthetic */ b(Context context, int i2) {
        this.d = i2;
        this.e = context;
    }

    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return WorkDatabase.Companion.create$lambda$0(this.e, configuration);
    }

    public Object get() {
        int i2 = this.d;
        Context context = this.e;
        switch (i2) {
            case 1:
                return DeviceUtil.lambda$getSimMcc$5(context);
            case 2:
                return Settings.System.getString(context.getContentResolver(), "device_name");
            case 3:
                return Settings.Global.getString(context.getContentResolver(), "device_name");
            case 4:
                return DeviceUtil.lambda$getNetworkMcc$4(context);
            case 5:
                return DeviceUtil.lambda$getSimMnc$7(context);
            case 6:
                return DeviceUtil.lambda$getNetworkMnc$6(context);
            default:
                return HashUtil.getByteArraySHA256(context.getPackageManager().getPackageInfo(FeatureConfigurator.CONFIGURATOR_PACKAGE, 64).signatures[0].toByteArray());
        }
    }

    public void onResult(Object obj) {
        MdeGroupHelper.lambda$requestGroupInvitationRejection$2(this.e, (BooleanResult) obj);
    }
}
