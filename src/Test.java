public class Test {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal<>()  ;
        System.out.println(threadLocal.get());;
        threadLocal.set("srini");
        System.out.println(threadLocal.get());;

        threadLocal.remove();
        System.out.println(threadLocal.get());
    }
}
