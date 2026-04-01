package ge;

import E2.r;
import com.samsung.android.sum.core.types.NumericEnum;
import ee.V;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ge.d1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1017d1 implements V {
    public static final Logger d = Logger.getLogger(C1017d1.class.getName());
    public static final Q0 e = new Q0(11);
    public static final Y f = new Y(1);

    /* renamed from: a  reason: collision with root package name */
    public final r f4502a;
    public final Q0 b;

    /* renamed from: c  reason: collision with root package name */
    public final InetSocketAddress f4503c;

    public C1017d1() {
        int i2;
        String str = System.getenv("GRPC_PROXY_EXP");
        Y y = f;
        y.getClass();
        this.f4502a = y;
        Q0 q0 = e;
        q0.getClass();
        this.b = q0;
        if (str != null) {
            String[] split = str.split(NumericEnum.SEP, 2);
            if (split.length > 1) {
                i2 = Integer.parseInt(split[1]);
            } else {
                i2 = 80;
            }
            d.warning("Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
            this.f4503c = new InetSocketAddress(split[0], i2);
            return;
        }
        this.f4503c = null;
    }

    public final ee.r a(InetSocketAddress inetSocketAddress) {
        URL url;
        ee.r rVar;
        String str = null;
        if (inetSocketAddress != null) {
            InetSocketAddress inetSocketAddress2 = this.f4503c;
            if (inetSocketAddress2 != null) {
                return new ee.r(inetSocketAddress2, inetSocketAddress, (String) null, (String) null);
            }
            Logger logger = d;
            try {
                try {
                    URI uri = new URI("https", (String) null, Z.b(inetSocketAddress), inetSocketAddress.getPort(), (String) null, (String) null, (String) null);
                    ProxySelector proxySelector = (ProxySelector) this.f4502a.get();
                    if (proxySelector == null) {
                        logger.log(Level.FINE, "proxy selector is null, so continuing without proxy lookup");
                        return null;
                    }
                    List<Proxy> select = proxySelector.select(uri);
                    if (select.size() > 1) {
                        logger.warning("More than 1 proxy detected, gRPC will select the first one");
                    }
                    Proxy proxy = select.get(0);
                    if (proxy.type() != Proxy.Type.DIRECT) {
                        InetSocketAddress inetSocketAddress3 = (InetSocketAddress) proxy.address();
                        String b5 = Z.b(inetSocketAddress3);
                        InetAddress address = inetSocketAddress3.getAddress();
                        int port = inetSocketAddress3.getPort();
                        this.b.getClass();
                        try {
                            url = new URL("https", b5, port, "");
                        } catch (MalformedURLException unused) {
                            logger.log(Level.WARNING, "failed to create URL for Authenticator: {0} {1}", new Object[]{"https", b5});
                            url = null;
                        }
                        PasswordAuthentication requestPasswordAuthentication = Authenticator.requestPasswordAuthentication(b5, address, port, "https", "", (String) null, url, Authenticator.RequestorType.PROXY);
                        if (inetSocketAddress3.isUnresolved()) {
                            inetSocketAddress3 = new InetSocketAddress(InetAddress.getByName(inetSocketAddress3.getHostName()), inetSocketAddress3.getPort());
                        }
                        int i2 = ee.r.f4308h;
                        if (requestPasswordAuthentication == null) {
                            rVar = new ee.r(inetSocketAddress3, inetSocketAddress, (String) null, (String) null);
                        } else {
                            String userName = requestPasswordAuthentication.getUserName();
                            if (requestPasswordAuthentication.getPassword() != null) {
                                str = new String(requestPasswordAuthentication.getPassword());
                            }
                            rVar = new ee.r(inetSocketAddress3, inetSocketAddress, userName, str);
                        }
                        return rVar;
                    }
                } catch (URISyntaxException e7) {
                    logger.log(Level.WARNING, "Failed to construct URI for proxy lookup, proceeding without proxy", e7);
                    return null;
                }
            } catch (Throwable th) {
                logger.log(Level.WARNING, "Failed to get host for proxy lookup, proceeding without proxy", th);
                return null;
            }
        }
        return null;
    }
}
