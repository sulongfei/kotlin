interface A
interface B {
    fun next(): B?
}

class Some {
    fun foo(s: A) {
        if (s !is B) return
        var ss: B? = s
        while (ss != null) {
            ss = ss.next()
        }
    }
}