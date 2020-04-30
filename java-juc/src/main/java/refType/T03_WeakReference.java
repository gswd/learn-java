package refType;

import java.lang.ref.WeakReference;

/**
 * 遇到gc就回收 弱引用
 * 一般用在容器里， WeakHashMap
 */
public class T03_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m);
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();//ThreadLocal 用完后必须手动remove掉，否则可能会发生内存泄漏.

        ThreadLocal<M> tl2 = new ThreadLocal<>();
        tl2.set(new M());
        tl2.remove();
    }
}
