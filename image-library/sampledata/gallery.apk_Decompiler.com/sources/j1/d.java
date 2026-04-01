package j1;

import Q0.e;
import V0.j;
import com.fasterxml.jackson.dataformat.xml.deser.a;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends e {
    static {
        a.a();
        com.fasterxml.jackson.dataformat.xml.ser.a.a();
    }

    public d() {
        this(0);
    }

    public static void b(XMLInputFactory xMLInputFactory, XMLOutputFactory xMLOutputFactory) {
        Boolean bool = Boolean.TRUE;
        xMLOutputFactory.setProperty("javax.xml.stream.isRepairingNamespaces", bool);
        xMLInputFactory.setProperty("javax.xml.stream.isCoalescing", bool);
    }

    public d(int i2) {
        super((j) null);
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        Boolean bool = Boolean.FALSE;
        newInstance.setProperty("javax.xml.stream.isSupportingExternalEntities", bool);
        newInstance.setProperty("javax.xml.stream.supportDTD", bool);
        b(newInstance, XMLOutputFactory.newInstance());
    }
}
