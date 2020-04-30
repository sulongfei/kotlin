// FILE: JavaBase.java

public class JavaBase {
    public class Inner {}
}

// FILE: test.kt

open class Base {
    inner class Inner
}

class Derived : Base() {
    val test = Inner()
}

class JavaDerived : JavaBase() {
    val test = Inner()
}