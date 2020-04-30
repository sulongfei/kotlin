open class SomeClass<T>
class TestSome<P> {
    object Some : SomeClass<P>() {
    }
}

class Test {
    object Some : <!INAPPLICABLE_CANDIDATE!>InnerClass<!>() {
        val a = object: <!INAPPLICABLE_CANDIDATE!>InnerClass<!>() {
        }

        fun more(): InnerClass {
            val b = <!UNRESOLVED_REFERENCE!>InnerClass<!>()

            val testVal = <!UNRESOLVED_REFERENCE!>inClass<!>
            <!UNRESOLVED_REFERENCE!>foo<!>()

            return b
        }
    }

    val inClass = 12
    fun foo() {
    }

    open inner class InnerClass
}
