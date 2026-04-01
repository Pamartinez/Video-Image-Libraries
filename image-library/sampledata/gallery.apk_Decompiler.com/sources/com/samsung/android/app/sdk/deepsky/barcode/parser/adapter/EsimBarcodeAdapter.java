package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.r;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import com.samsung.android.app.sdk.deepsky.barcode.action.uri.LaunchGalaxyWearableAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.uri.LaunchSimCardManagerAction;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\f\u001a\u00020\u000b*\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\f\u0010\rJ#\u0010\u000e\u001a\u00020\u000b*\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0013\u0010\u0011J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/EsimBarcodeAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "<init>", "(Landroid/content/Context;LX2/r;)V", "Ljava/util/ArrayList;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Lkotlin/collections/ArrayList;", "Lme/x;", "addLaunchSimCardManagerActionIfPossible", "(Ljava/util/ArrayList;)V", "addLaunchGalaxyWearableAction", "", "getTitle", "()Ljava/lang/String;", "getBody", "getBodyTts", "", "getActions", "()Ljava/util/List;", "LX2/r;", "appContext", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EsimBarcodeAdapter implements BarcodeParsedResult {
    private final Context appContext;
    private final r parsedResult;

    public EsimBarcodeAdapter(Context context, r rVar) {
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        this.parsedResult = rVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    private final void addLaunchGalaxyWearableAction(ArrayList<Action> arrayList) {
        LaunchGalaxyWearableAction launchGalaxyWearableAction = new LaunchGalaxyWearableAction(this.appContext, this.parsedResult, (Injector) null, 4, (e) null);
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, launchGalaxyWearableAction.getIntent())) {
            launchGalaxyWearableAction = null;
        }
        if (launchGalaxyWearableAction != null) {
            arrayList.add(launchGalaxyWearableAction);
        }
    }

    private final void addLaunchSimCardManagerActionIfPossible(ArrayList<Action> arrayList) {
        LaunchSimCardManagerAction launchSimCardManagerAction = new LaunchSimCardManagerAction(this.appContext, this.parsedResult, (Injector) null, 4, (e) null);
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, launchSimCardManagerAction.getIntent())) {
            launchSimCardManagerAction = null;
        }
        if (launchSimCardManagerAction != null) {
            arrayList.add(launchSimCardManagerAction);
        }
    }

    public List<Action> getActions() {
        ArrayList arrayList = new ArrayList();
        addLaunchSimCardManagerActionIfPossible(arrayList);
        addLaunchGalaxyWearableAction(arrayList);
        return arrayList;
    }

    public String getBody() {
        String string = this.appContext.getString(R$string.barcode_body_esim);
        j.d(string, "getString(...)");
        return string;
    }

    public String getBodyTts() {
        return getBody();
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_title_esim);
        j.d(string, "getString(...)");
        return string;
    }
}
