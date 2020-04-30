package refType;

import java.util.concurrent.locks.LockSupport;

public class T01_NormalReference {
    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.gc();
        LockSupport.park();
    }
}
