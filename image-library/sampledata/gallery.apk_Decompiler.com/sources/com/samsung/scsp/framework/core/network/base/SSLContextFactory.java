package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.error.FaultBarrier;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SSLContextFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final SSLContext sslContext = FaultBarrier.get(new Object(), null).obj;

        private LazyHolder() {
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ SSLContext lambda$static$0() {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load((InputStream) null, (char[]) null);
            KeyStore instance2 = KeyStore.getInstance("AndroidCAStore");
            instance2.load((InputStream) null, (char[]) null);
            Enumeration<String> aliases = instance2.aliases();
            while (aliases.hasMoreElements()) {
                String nextElement = aliases.nextElement();
                X509Certificate x509Certificate = (X509Certificate) instance2.getCertificate(nextElement);
                if (nextElement.startsWith("system:")) {
                    instance.setCertificateEntry(nextElement, x509Certificate);
                }
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance);
            SSLContext instance4 = SSLContext.getInstance("TLS");
            instance4.init((KeyManager[]) null, instance3.getTrustManagers(), (SecureRandom) null);
            return instance4;
        }
    }

    public static SSLContext get() {
        return LazyHolder.sslContext;
    }
}
