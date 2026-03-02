package com.nishchay.concurrentpkg.threadlocal;

public class ThreadLocalSetGet {


    public static void main(String[] args) {

        setValue();
        defaultAndRemove();
        overrideInitialValue();
}

    private static void setValue() {

        ThreadLocal<String> threadLocal =  new ThreadLocal<>();

        // ThreadLocal without generics
        threadLocal.set("A thread local value");
        String val = (String) threadLocal.get();
        System.out.println("val = " + val);

        // ThreadLocal with generics
        ThreadLocal<String> genericThrdLocal =  new ThreadLocal<>();
        genericThrdLocal.set("A thread local generic value");
        val =  genericThrdLocal.get();
        System.out.println("val = " + val);

    }

    private static void defaultAndRemove() {

        ThreadLocal<String> threadLocal =  new ThreadLocal<>();
        System.out.println(threadLocal.get());

        threadLocal.set("java");
        System.out.println(threadLocal.get());

        threadLocal.remove();
        System.out.println(threadLocal.get());

    }

    private static void overrideInitialValue() {


/*        ThreadLocal<String> genericThreadLocal =  new ThreadLocal(){
            public String initialValue(){
                return "java";
            }
        };
*/

        ThreadLocal<String> genericThreadLocal = ThreadLocal.withInitial(() -> "java");
        System.out.println(genericThreadLocal.get());

        genericThreadLocal.set("perl");
        System.out.println(genericThreadLocal.get());

        genericThreadLocal.set("ruby");
        System.out.println(genericThreadLocal.get());

        genericThreadLocal.remove();
        System.out.println(genericThreadLocal.get());
    }
}
