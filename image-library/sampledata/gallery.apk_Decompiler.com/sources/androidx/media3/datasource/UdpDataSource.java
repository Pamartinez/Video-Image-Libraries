package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UdpDataSource extends BaseDataSource {
    private InetAddress address;
    private MulticastSocket multicastSocket;
    private boolean opened;
    private final DatagramPacket packet;
    private final byte[] packetBuffer;
    private int packetRemaining;
    private DatagramSocket socket;
    private final int socketTimeoutMillis;
    private Uri uri;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UdpDataSourceException extends DataSourceException {
        public UdpDataSourceException(Throwable th, int i2) {
            super(th, i2);
        }
    }

    public UdpDataSource() {
        this(2000);
    }

    public void close() {
        this.uri = null;
        MulticastSocket multicastSocket2 = this.multicastSocket;
        if (multicastSocket2 != null) {
            try {
                multicastSocket2.leaveGroup((InetAddress) Assertions.checkNotNull(this.address));
            } catch (IOException unused) {
            }
            this.multicastSocket = null;
        }
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.socket = null;
        }
        this.address = null;
        this.packetRemaining = 0;
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec dataSpec) {
        Uri uri2 = dataSpec.uri;
        this.uri = uri2;
        String str = (String) Assertions.checkNotNull(uri2.getHost());
        int port = this.uri.getPort();
        transferInitializing(dataSpec);
        try {
            this.address = InetAddress.getByName(str);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.address, port);
            if (this.address.isMulticastAddress()) {
                MulticastSocket multicastSocket2 = new MulticastSocket(inetSocketAddress);
                this.multicastSocket = multicastSocket2;
                multicastSocket2.joinGroup(this.address);
                this.socket = this.multicastSocket;
            } else {
                this.socket = new DatagramSocket(inetSocketAddress);
            }
            this.socket.setSoTimeout(this.socketTimeoutMillis);
            this.opened = true;
            transferStarted(dataSpec);
            return -1;
        } catch (SecurityException e) {
            throw new UdpDataSourceException(e, 2006);
        } catch (IOException e7) {
            throw new UdpDataSourceException(e7, 2001);
        }
    }

    public int read(byte[] bArr, int i2, int i7) {
        if (i7 == 0) {
            return 0;
        }
        if (this.packetRemaining == 0) {
            try {
                ((DatagramSocket) Assertions.checkNotNull(this.socket)).receive(this.packet);
                int length = this.packet.getLength();
                this.packetRemaining = length;
                bytesTransferred(length);
            } catch (SocketTimeoutException e) {
                throw new UdpDataSourceException(e, 2002);
            } catch (IOException e7) {
                throw new UdpDataSourceException(e7, 2001);
            }
        }
        int length2 = this.packet.getLength();
        int i8 = this.packetRemaining;
        int min = Math.min(i8, i7);
        System.arraycopy(this.packetBuffer, length2 - i8, bArr, i2, min);
        this.packetRemaining -= min;
        return min;
    }

    public UdpDataSource(int i2) {
        this(i2, Encode.BitRate.VIDEO_HD_BITRATE);
    }

    public UdpDataSource(int i2, int i7) {
        super(true);
        this.socketTimeoutMillis = i7;
        byte[] bArr = new byte[i2];
        this.packetBuffer = bArr;
        this.packet = new DatagramPacket(bArr, 0, i2);
    }
}
