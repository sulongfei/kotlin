class Test {
    @`InnerAnnotation` @InnerAnnotation
    companion object : StaticClass(), <!INAPPLICABLE_CANDIDATE!>InnerClass<!>() {

    }

    annotation class InnerAnnotation
    open class StaticClass

    open inner class InnerClass
}