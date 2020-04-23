class Outer { inner class Inner }
fun test() {
    val x = object : <!INAPPLICABLE_CANDIDATE!>Outer.Inner<!>() { }
}