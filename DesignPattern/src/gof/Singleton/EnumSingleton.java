package gof.Singleton;

public enum EnumSingleton {
    INSTANCE;
    EnumSingleton() {
        System.out.println("Create an object.");
    }
    public void doStuff() {
        System.out.println("This is a singleton object.");
    }
    public static void main(String[] args) {
        EnumSingleton.INSTANCE.doStuff();
        EnumSingleton.INSTANCE.doStuff();
        EnumSingleton.INSTANCE.doStuff();
    }
}
