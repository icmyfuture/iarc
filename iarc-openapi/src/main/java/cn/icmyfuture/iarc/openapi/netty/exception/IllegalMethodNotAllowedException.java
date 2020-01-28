package cn.icmyfuture.iarc.openapi.netty.exception;

public class IllegalMethodNotAllowedException extends Exception {
    public IllegalMethodNotAllowedException() {
        super("METHOD NOT ALLOWED");
    }
}
