package OOD.DesignPattern.Singleton;

/*
 * recommended way to implement Singleton
 * in this way, the INSTANCE is static final and will be initialized once when the first time
 * the class is loaded into memory. So the initialization of INSTANCE is thread safe
 *
 * a little potential problem of this way is that if the constructor of Singleton depends on some
 * parameter or configuration file which means it need invoke some method to get the parameter before
 * invoking getInstance(), this way cannot work
 *
 * to solve this, we can use static nested class
 */
public class Singleton {
    private static final Singleton INSTANCE = new Singleton(); // make sure only one instance in the class Singleton
    // explicitly declare constructor which is private to prohibit other constructors
    private Singleton() {}
    // always return the only existed instance INSTANCE which is shared by all the instances of this class
    public static Singleton getInstance() {
        return INSTANCE;
    }
}

// static nested class
/*
 * best recommended way to implement Singleton
 * 1. because the SingletonHolder is private
 *    so only getInstance() can access to it
 * 2. It is thread safe because SingletonHolder is a static class and INSTANCE is static final
 *    so that getInstance() will not cause any data race
 */
class Singleton0 {
    private static class SingleHolder {
        private static final Singleton0 INSTANCE = new Singleton0();
    }

    private Singleton0() {}

    public static Singleton0 getInstance() {
        return SingleHolder.INSTANCE;
    }
}

/*
 * second way:
 * This way is lazy-load mode, but this implementation is NOT thread safe
 * because if multiple threads are invoking getInstance(), then there will
 * be more than one instances that will be created.
 * make critical section to guarantee thread safe
 * However this implementation is not efficient
 * because the synchronization is needed when first time
 * to create singleton instance, after that synchronization
 * is useless
 */

class Singleton2 {
    private static Singleton2 INSTANCE;
    private Singleton2() {}

    public static synchronized Singleton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
}

/*
 * Double-checked locking pattern
 * In this way, everything is good except:
 *              INSTANCE = new Singleton3();
 * This line may have problem because this assignment code is not atomic
 * Actually in JVM, three things happened here:
 *     1. allocate memory space for INSTANCE
 *     2. invoke constructor Singleton3() to initialize INSTANCE
 *     3. make INSTANCE point to the memory space allocated for it (after this INSTANCE is NOT null any more)
 *
 * However there could be optimization because of JIT JVM (to re-sort instructions)
 * So JVM could not guarantee the execution order of step2 and step3.
 * The final execution order could be 1-2-3 or 1-3-2, no one knows.
 * And if the execution order is 1-3-2, then after step3 is executed and before step2 is executed,
 * there is a thread2 to use the critical section, and at this moment, INSTANCE is not null but
 * it has not been initialized by constructor() and then be returned by thread2 which will throw exception without any doubt
 * use volatile keyword to disable instruction resorting
 */

class Singleton3 {
    private static volatile Singleton3 INSTANCE;
    private Singleton3() {}
    public static Singleton3 getInstance() {
        if (INSTANCE == null) {
            synchronized(Singleton3.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}

/*
 * Best implementation
 * easiest way: Enum
 * Enum is thread safe by default
 * and it can also avoid recreate INSTANCE object
 * by deserialization
 */

enum Singleton4 {
    INSTANCE;
}