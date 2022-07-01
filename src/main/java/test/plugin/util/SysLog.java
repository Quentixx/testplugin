package test.plugin.util;

public class SysLog {

    public static void info(Class source, String info) {
        System.out.println("[" + source.getSimpleName() + "]: " +  info);
    }

    public static void err(Class source, String err) {
        System.out.println("[" + source.getSimpleName() + "]: " +  err);
    }
}
