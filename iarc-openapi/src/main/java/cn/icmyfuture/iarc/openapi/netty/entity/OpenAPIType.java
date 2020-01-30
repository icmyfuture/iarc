package cn.icmyfuture.iarc.openapi.netty.entity;

public enum OpenAPIType {
    OPEN_API(0),
    DEVICE_API(1);

    private int value;

    OpenAPIType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
