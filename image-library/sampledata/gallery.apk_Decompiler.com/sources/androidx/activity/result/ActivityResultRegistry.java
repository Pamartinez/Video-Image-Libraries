package androidx.activity.result;

import Ee.a;
import Ee.d;
import N2.j;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActivityResultRegistry {
    final transient Map<String, CallbackAndContract<?>> mKeyToCallback = new HashMap();
    private final Map<String, LifecycleContainer> mKeyToLifecycleContainers = new HashMap();
    final Map<String, Integer> mKeyToRc = new HashMap();
    ArrayList<String> mLaunchedKeys = new ArrayList<>();
    final Map<String, Object> mParsedPendingResults = new HashMap();
    final Bundle mPendingResults = new Bundle();
    private final Map<Integer, String> mRcToKey = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CallbackAndContract<O> {
        final ActivityResultCallback<O> mCallback;
        final ActivityResultContract<?, O> mContract;

        public CallbackAndContract(ActivityResultCallback<O> activityResultCallback, ActivityResultContract<?, O> activityResultContract) {
            this.mCallback = activityResultCallback;
            this.mContract = activityResultContract;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LifecycleContainer {
        final Lifecycle mLifecycle;
        private final ArrayList<LifecycleEventObserver> mObservers = new ArrayList<>();

        public LifecycleContainer(Lifecycle lifecycle) {
            this.mLifecycle = lifecycle;
        }

        public void addObserver(LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle.addObserver(lifecycleEventObserver);
            this.mObservers.add(lifecycleEventObserver);
        }

        public void clearObservers() {
            Iterator<LifecycleEventObserver> it = this.mObservers.iterator();
            while (it.hasNext()) {
                this.mLifecycle.removeObserver(it.next());
            }
            this.mObservers.clear();
        }
    }

    private void bindRcKey(int i2, String str) {
        this.mRcToKey.put(Integer.valueOf(i2), str);
        this.mKeyToRc.put(str, Integer.valueOf(i2));
    }

    private <O> void doDispatch(String str, int i2, Intent intent, CallbackAndContract<O> callbackAndContract) {
        if (callbackAndContract == null || callbackAndContract.mCallback == null || !this.mLaunchedKeys.contains(str)) {
            this.mParsedPendingResults.remove(str);
            this.mPendingResults.putParcelable(str, new ActivityResult(i2, intent));
            return;
        }
        callbackAndContract.mCallback.onActivityResult(callbackAndContract.mContract.parseResult(i2, intent));
        this.mLaunchedKeys.remove(str);
    }

    private int generateRandomNumber() {
        a aVar = d.d;
        int nextInt = d.d.d().nextInt(2147418112);
        while (true) {
            int i2 = nextInt + 65536;
            if (!this.mRcToKey.containsKey(Integer.valueOf(i2))) {
                return i2;
            }
            a aVar2 = d.d;
            nextInt = d.d.d().nextInt(2147418112);
        }
    }

    private void registerKey(String str) {
        if (this.mKeyToRc.get(str) == null) {
            bindRcKey(generateRandomNumber(), str);
        }
    }

    public final boolean dispatchResult(int i2, int i7, Intent intent) {
        String str = this.mRcToKey.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        doDispatch(str, i7, intent, this.mKeyToCallback.get(str));
        return true;
    }

    public abstract <I, O> void onLaunch(int i2, ActivityResultContract<I, O> activityResultContract, I i7, ActivityOptionsCompat activityOptionsCompat);

    public final void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList != null && integerArrayList != null) {
                this.mLaunchedKeys = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                this.mPendingResults.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
                for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                    String str = stringArrayList.get(i2);
                    if (this.mKeyToRc.containsKey(str)) {
                        Integer remove = this.mKeyToRc.remove(str);
                        if (!this.mPendingResults.containsKey(str)) {
                            this.mRcToKey.remove(remove);
                        }
                    }
                    bindRcKey(integerArrayList.get(i2).intValue(), stringArrayList.get(i2));
                }
            }
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList(this.mKeyToRc.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList(this.mKeyToRc.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList(this.mLaunchedKeys));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.mPendingResults.clone());
    }

    public final <I, O> ActivityResultLauncher<I> register(final String str, LifecycleOwner lifecycleOwner, final ActivityResultContract<I, O> activityResultContract, final ActivityResultCallback<O> activityResultCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            registerKey(str);
            LifecycleContainer lifecycleContainer = this.mKeyToLifecycleContainers.get(str);
            if (lifecycleContainer == null) {
                lifecycleContainer = new LifecycleContainer(lifecycle);
            }
            lifecycleContainer.addObserver(new LifecycleEventObserver() {
                public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    if (Lifecycle.Event.ON_START.equals(event)) {
                        ActivityResultRegistry.this.mKeyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
                        if (ActivityResultRegistry.this.mParsedPendingResults.containsKey(str)) {
                            Object obj = ActivityResultRegistry.this.mParsedPendingResults.get(str);
                            ActivityResultRegistry.this.mParsedPendingResults.remove(str);
                            activityResultCallback.onActivityResult(obj);
                        }
                        ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.mPendingResults.getParcelable(str);
                        if (activityResult != null) {
                            ActivityResultRegistry.this.mPendingResults.remove(str);
                            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
                        }
                    } else if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.mKeyToCallback.remove(str);
                    } else if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                        ActivityResultRegistry.this.unregister(str);
                    }
                }
            });
            this.mKeyToLifecycleContainers.put(str, lifecycleContainer);
            return new ActivityResultLauncher<I>() {
                public void launch(I i2, ActivityOptionsCompat activityOptionsCompat) {
                    Integer num = ActivityResultRegistry.this.mKeyToRc.get(str);
                    if (num != null) {
                        ActivityResultRegistry.this.mLaunchedKeys.add(str);
                        try {
                            ActivityResultRegistry.this.onLaunch(num.intValue(), activityResultContract, i2, activityOptionsCompat);
                        } catch (Exception e) {
                            ActivityResultRegistry.this.mLaunchedKeys.remove(str);
                            throw e;
                        }
                    } else {
                        throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + i2 + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
                    }
                }

                public void unregister() {
                    ActivityResultRegistry.this.unregister(str);
                }
            };
        }
        throw new IllegalStateException("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
    }

    public final void unregister(String str) {
        Integer remove;
        if (!this.mLaunchedKeys.contains(str) && (remove = this.mKeyToRc.remove(str)) != null) {
            this.mRcToKey.remove(remove);
        }
        this.mKeyToCallback.remove(str);
        if (this.mParsedPendingResults.containsKey(str)) {
            StringBuilder k = j.k("Dropping pending result for request ", str, ": ");
            k.append(this.mParsedPendingResults.get(str));
            Log.w("ActivityResultRegistry", k.toString());
            this.mParsedPendingResults.remove(str);
        }
        if (this.mPendingResults.containsKey(str)) {
            StringBuilder k10 = j.k("Dropping pending result for request ", str, ": ");
            k10.append(this.mPendingResults.getParcelable(str));
            Log.w("ActivityResultRegistry", k10.toString());
            this.mPendingResults.remove(str);
        }
        LifecycleContainer lifecycleContainer = this.mKeyToLifecycleContainers.get(str);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
            this.mKeyToLifecycleContainers.remove(str);
        }
    }

    public final <O> boolean dispatchResult(int i2, O o2) {
        ActivityResultCallback<O> activityResultCallback;
        String str = this.mRcToKey.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        CallbackAndContract callbackAndContract = this.mKeyToCallback.get(str);
        if (callbackAndContract == null || (activityResultCallback = callbackAndContract.mCallback) == null) {
            this.mPendingResults.remove(str);
            this.mParsedPendingResults.put(str, o2);
            return true;
        } else if (!this.mLaunchedKeys.remove(str)) {
            return true;
        } else {
            activityResultCallback.onActivityResult(o2);
            return true;
        }
    }

    public final <I, O> ActivityResultLauncher<I> register(final String str, final ActivityResultContract<I, O> activityResultContract, ActivityResultCallback<O> activityResultCallback) {
        registerKey(str);
        this.mKeyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
        if (this.mParsedPendingResults.containsKey(str)) {
            Object obj = this.mParsedPendingResults.get(str);
            this.mParsedPendingResults.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.mPendingResults.getParcelable(str);
        if (activityResult != null) {
            this.mPendingResults.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
        }
        return new ActivityResultLauncher<I>() {
            public void launch(I i2, ActivityOptionsCompat activityOptionsCompat) {
                Integer num = ActivityResultRegistry.this.mKeyToRc.get(str);
                if (num != null) {
                    ActivityResultRegistry.this.mLaunchedKeys.add(str);
                    try {
                        ActivityResultRegistry.this.onLaunch(num.intValue(), activityResultContract, i2, activityOptionsCompat);
                    } catch (Exception e) {
                        ActivityResultRegistry.this.mLaunchedKeys.remove(str);
                        throw e;
                    }
                } else {
                    throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + i2 + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
                }
            }

            public void unregister() {
                ActivityResultRegistry.this.unregister(str);
            }
        };
    }
}
