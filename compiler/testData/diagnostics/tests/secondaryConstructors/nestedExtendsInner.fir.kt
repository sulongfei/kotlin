class A {
    open inner class Inner

    class Nested : Inner {
        <!INAPPLICABLE_CANDIDATE!>constructor()<!>
    }
}
