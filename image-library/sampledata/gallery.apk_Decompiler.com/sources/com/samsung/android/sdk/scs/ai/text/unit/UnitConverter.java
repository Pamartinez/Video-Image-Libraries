package com.samsung.android.sdk.scs.ai.text.unit;

import G.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnitConverter {
    private static final String TAG = "ScsApi@UnitConverter";
    private final boolean isUnitConverterSupported;
    private final TextServiceExecutor mServiceExecutor;

    public UnitConverter(Context context) {
        boolean z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_CONVERT_UNIT) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isUnitConverterSupported = z;
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    private Bundle getBundleFromInput(String str) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a(2, this, str));
        try {
            Bundle bundle = (Bundle) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return bundle;
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in getSupportedSymbolList:getBundleFromInput : " + e.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in getSupportedSymbolList:getBundleFromInput : " + e7.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
    }

    private Quantity getQuantityFromBundle(Bundle bundle) {
        if (bundle == null) {
            try {
                Log.e(TAG, "UnitConverter.convert(). ContentResolver result is null!");
                return new Quantity();
            } catch (Exception e) {
                Log.e(TAG, "Exception: getQuantityFromBundle", e);
                return new Quantity();
            }
        } else {
            int i2 = bundle.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i2 != 1) {
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return new Quantity();
            }
            String string = bundle.getString("unitType");
            String string2 = bundle.getString("unitName");
            String string3 = bundle.getString("unitSymbol");
            BigDecimal bigDecimal = (BigDecimal) bundle.getSerializable("unitValue");
            if (!(string == null || string2 == null || string3 == null)) {
                if (bigDecimal != null) {
                    return new Quantity(string, string2, string3, bigDecimal);
                }
            }
            Log.e(TAG, "null!! type: " + string + ", name: " + string2 + ", symbol: " + string3 + ", value: " + bigDecimal);
            return new Quantity();
        }
    }

    private List<String> getSymbolListFromBundle(Bundle bundle) {
        if (bundle == null) {
            try {
                Log.e(TAG, "UnitConverter.getSupportedSymbolList(). ContentResolver result is null!");
                return Collections.EMPTY_LIST;
            } catch (Exception e) {
                Log.e(TAG, "Exception: getSymbolListFromBundle", e);
                return Collections.EMPTY_LIST;
            }
        } else {
            int i2 = bundle.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i2 != 1) {
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return Collections.EMPTY_LIST;
            }
            ArrayList<String> stringArrayList = bundle.getStringArrayList("symbolList");
            if (stringArrayList != null) {
                return stringArrayList;
            }
            Log.e(TAG, "null!! symbolList: " + stringArrayList);
            return Collections.EMPTY_LIST;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Bundle lambda$getBundleFromInput$0(String str) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("source", str);
            return textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getSupportedSymbolList", (String) null, bundle);
        } catch (Exception e) {
            Log.e(TAG, "Exception: getSupportedSymbolList getBundleFromInput", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Bundle lambda$getBundleFromInput$1(BigDecimal bigDecimal, String str, String str2, String str3) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putSerializable("number", bigDecimal);
            bundle.putString("source", str);
            bundle.putString("target", str2);
            bundle.putString(BuddyContract.Address.COUNTRY, str3);
            return textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "convertUnit", (String) null, bundle);
        } catch (Exception e) {
            Log.e(TAG, "Exception: convert getBundleFromInput", e);
            return null;
        }
    }

    public Quantity convert(BigDecimal bigDecimal, String str, String str2, String str3) {
        Log.d(TAG, "UnitConverter convert - country : " + str3);
        if (this.isUnitConverterSupported) {
            return getQuantityFromBundle(getBundleFromInput(bigDecimal, str, str2, str3));
        }
        Log.e(TAG, "Feature.FEATURE_TEXT_CONVERT_UNIT not supported!");
        return new Quantity();
    }

    public List<String> getSupportedSymbolList(String str) {
        Log.d(TAG, "UnitConverter getSupportedSymbolList");
        if (this.isUnitConverterSupported) {
            return getSymbolListFromBundle(getBundleFromInput(str));
        }
        Log.e(TAG, "Feature.FEATURE_TEXT_CONVERT_UNIT not supported!");
        return Collections.EMPTY_LIST;
    }

    private Bundle getBundleFromInput(BigDecimal bigDecimal, String str, String str2, String str3) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new Vc.a(this, bigDecimal, str, str2, str3));
        try {
            Bundle bundle = (Bundle) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return bundle;
        } catch (TimeoutException e) {
            TimeoutException timeoutException = e;
            submit.cancel(true);
            Log.e(TAG, "Timeout in convert:getBundleFromInput : " + timeoutException.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Exception e7) {
            Exception exc = e7;
            Log.e(TAG, "Exception occurred in convert:getBundleFromInput : " + exc.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            newSingleThreadExecutor.shutdownNow();
            throw th2;
        }
    }
}
