package androidx.test.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.test.internal.util.Checks;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class InstrumentationActivityInvoker {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BootstrapActivity extends Activity {
        private static final String TAG = "androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity";
        private boolean isTargetActivityStarted;
        private final BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                BootstrapActivity.this.finishActivity(0);
                BootstrapActivity.this.finish();
            }
        };

        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        public void onActivityResult(int i2, int i7, Intent intent) {
            if (i2 == 0) {
                Intent intent2 = new Intent("androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED");
                intent2.putExtra("androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY", i7);
                if (intent != null) {
                    intent2.putExtra("androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY", intent);
                }
                sendBroadcast(intent2);
                finish();
            }
        }

        public void onCreate(Bundle bundle) {
            boolean z;
            super.onCreate(bundle);
            InstrumentationActivityInvoker.registerBroadcastReceiver(this, this.receiver, new IntentFilter("androidx.test.core.app.InstrumentationActivityInvoker.FINISH_BOOTSTRAP_ACTIVITY"));
            if (bundle == null || !bundle.getBoolean("IS_TARGET_ACTIVITY_STARTED_KEY", false)) {
                z = false;
            } else {
                z = true;
            }
            this.isTargetActivityStarted = z;
            overridePendingTransition(0, 0);
        }

        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        public void onResume() {
            super.onResume();
            if (!this.isTargetActivityStarted) {
                this.isTargetActivityStarted = true;
                PendingIntent pendingIntent = (PendingIntent) Checks.checkNotNull((PendingIntent) getIntent().getParcelableExtra("androidx.test.core.app.InstrumentationActivityInvoker.START_TARGET_ACTIVITY_INTENT_KEY"));
                Bundle access$100 = InstrumentationActivityInvoker.optInToGrantBalPrivileges(getIntent().getBundleExtra("androidx.test.core.app.InstrumentationActivityInvoker.TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY"));
                if (access$100 == null) {
                    try {
                        startIntentSenderForResult(pendingIntent.getIntentSender(), 0, (Intent) null, 268435456, 0, 0);
                    } catch (IntentSender.SendIntentException e) {
                        IntentSender.SendIntentException sendIntentException = e;
                        Log.e(TAG, "Failed to start target activity.", sendIntentException);
                        throw new RuntimeException(sendIntentException);
                    }
                } else {
                    startIntentSenderForResult(pendingIntent.getIntentSender(), 0, (Intent) null, 268435456, 0, 0, access$100);
                }
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean("IS_TARGET_ACTIVITY_STARTED_KEY", this.isTargetActivityStarted);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmptyActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                EmptyActivity.this.finish();
            }
        };

        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            InstrumentationActivityInvoker.registerBroadcastReceiver(this, this.receiver, new IntentFilter("androidx.test.core.app.InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES"));
            overridePendingTransition(0, 0);
        }

        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        public void onResume() {
            super.onResume();
            sendBroadcast(new Intent("androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_ACTIVITY_RESUMED"));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmptyFloatingActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                EmptyFloatingActivity.this.finish();
            }
        };

        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            InstrumentationActivityInvoker.registerBroadcastReceiver(this, this.receiver, new IntentFilter("androidx.test.core.app.InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES"));
            overridePendingTransition(0, 0);
        }

        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        public void onResume() {
            super.onResume();
            sendBroadcast(new Intent("androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_FLOATING_ACTIVITY_RESUMED"));
        }
    }

    /* access modifiers changed from: private */
    public static Bundle optInToGrantBalPrivileges(Bundle bundle) {
        if (Build.VERSION.SDK_INT < 34) {
            return bundle;
        }
        Bundle bundle2 = ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        return bundle2;
    }

    /* access modifiers changed from: private */
    public static void registerBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (Build.VERSION.SDK_INT < 33) {
            context.registerReceiver(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter, 2);
        }
    }
}
