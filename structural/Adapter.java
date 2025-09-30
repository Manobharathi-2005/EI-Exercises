package structural;

interface HdmiInput {
    void connectViaHdmi();
}


class TypeCOutputDevice {
    public void connectViaTypeC() {
        System.out.println("Device is outputting signal via Type-C port.");
    }
}

class TypeCToHdmiAdapter implements HdmiInput {
    private final TypeCOutputDevice device;

    public TypeCToHdmiAdapter(TypeCOutputDevice device) {
        this.device = device;
    }

    @Override
    public void connectViaHdmi() {
        System.out.println("Adapter is converting Type-C signal to HDMI...");
        device.connectViaTypeC();
        System.out.println("...Conversion complete. Displaying on HDMI monitor.");
    }
}

public class Adapter {
    public static void main(String[] args) {
        TypeCOutputDevice laptop = new TypeCOutputDevice();
        HdmiInput monitorConnection = new TypeCToHdmiAdapter(laptop);

        monitorConnection.connectViaHdmi();
    }
}