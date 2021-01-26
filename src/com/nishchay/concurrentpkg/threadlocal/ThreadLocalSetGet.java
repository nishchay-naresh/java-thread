package com.nishchay.concurrentpkg.threadlocal;

public class ThreadLocalSetGet {


    public static void main(String[] args) {

//        setValue();
//        defaultAndRemove();
        overrideInitialValue();
}

    private static void setValue() {

        ThreadLocal thrdLocal =  new ThreadLocal();

        // ThreadLocal without generics
        thrdLocal.set("A thread local value");
        String val = (String) thrdLocal.get();
        System.out.println("val = " + val);

        // ThreadLocal with generics
        ThreadLocal<String> genericThrdLocal =  new ThreadLocal<>();
        genericThrdLocal.set("A thread local generic value");
        val =  genericThrdLocal.get();
        System.out.println("val = " + val);

    }

    private static void defaultAndRemove() {

        ThreadLocal thrdLocal =  new ThreadLocal();
        System.out.println(thrdLocal.get());

        thrdLocal.set("java");
        System.out.println(thrdLocal.get());

        thrdLocal.remove();
        System.out.println(thrdLocal.get());

    }

    private static void overrideInitialValue() {


/*        ThreadLocal<String> genericThrdLocal =  new ThreadLocal(){
            public String initialValue(){
                return "java";
            }
        };
*/

        ThreadLocal<String> genericThrdLocal = ThreadLocal.withInitial(() -> "java");
        System.out.println(genericThrdLocal.get());

        genericThrdLocal.set("perl");
        System.out.println(genericThrdLocal.get());

        genericThrdLocal.set("ruby");
        System.out.println(genericThrdLocal.get());

        genericThrdLocal.remove();
        System.out.println(genericThrdLocal.get());
    }
}
