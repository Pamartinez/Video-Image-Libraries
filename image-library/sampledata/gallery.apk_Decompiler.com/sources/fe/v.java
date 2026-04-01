package fe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum v {
    BIND_SERVICE("bindService"),
    BIND_SERVICE_AS_USER("bindServiceAsUser"),
    DEVICE_POLICY_BIND_SEVICE_ADMIN("DevicePolicyManager.bindDeviceAdminServiceAsUser");
    
    private final String methodName;

    /* access modifiers changed from: public */
    v(String str) {
        this.methodName = str;
    }

    public final String a() {
        return this.methodName;
    }
}
