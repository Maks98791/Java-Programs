package x;
class B extends A {}
public class A {
 public static void n(A... a){} // 1
public static void main(String[] args) {
B[] ab = new B[2];
 A a = new A();
n(ab); // 2
n(a,a); // 3
}
}
