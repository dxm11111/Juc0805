package com.atguigu.juc;
@FunctionalInterface
interface Foo{
    //public void sayHello();

    //public void say886();

    public int add(int x,int y);

    default int div(int x,int y){
        return x+y;
    }
    default int div2(int x,int y){
        return x+y;
    }

    static int mul(int x,int y){
        return x-y;
    }
    static int mul2(int x,int y){
        return x-y;
    }

}


public class SaleTicket02 {
    public static void main(String[] args) {
        /*Foo foo = new Foo(){
            @Override
            public void sayHello(){
                System.out.println(".....Hello 0805");
            }

            @Override
            public void say886() {

            }
        };*/
        //foo.sayHello();

        /*Foo foo2 = () -> {System.out.println(".....Hello 0805  lambdeExpress");};
        foo2.sayHello();*/

        Foo foo = ( x,y)->{
            System.out.println(".....come in add method");
            return x + y;
        };
        System.out.println(foo.add(3,5));
        System.out.println(foo.div(3,5));
        System.out.println(foo.div2(3,5));
        System.out.println(Foo.mul(3,5));
        System.out.println(Foo.mul2(3,5));
    }

}
