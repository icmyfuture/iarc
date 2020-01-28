package cn.icmyfuture.iarc.openapi.netty.exception;

public class IllegalPathNotFoundException extends Exception {
    public IllegalPathNotFoundException() {
        super("PATH NOT FOUND");
    }
}
