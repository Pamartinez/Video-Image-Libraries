package androidx.appsearch.app;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppSearchEnvironmentFactory {
    private static volatile AppSearchEnvironment sAppSearchEnvironment;

    public static AppSearchEnvironment getEnvironmentInstance() {
        AppSearchEnvironment appSearchEnvironment;
        AppSearchEnvironment appSearchEnvironment2 = sAppSearchEnvironment;
        if (appSearchEnvironment2 != null) {
            return appSearchEnvironment2;
        }
        synchronized (AppSearchEnvironmentFactory.class) {
            try {
                appSearchEnvironment = sAppSearchEnvironment;
                if (appSearchEnvironment == null) {
                    appSearchEnvironment = new JetpackAppSearchEnvironment();
                    sAppSearchEnvironment = appSearchEnvironment;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return appSearchEnvironment;
    }
}
