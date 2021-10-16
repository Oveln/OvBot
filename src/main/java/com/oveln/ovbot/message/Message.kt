package com.oveln.ovbot.message

class Message(MessageStrings: List<String>) {
    private var Regexs: MutableList<Regex> = mutableListOf()
    private var Messages: List<String> = MessageStrings

    constructor(RegexStrings: List<String> , MessageStrings: List<String>): this(MessageStrings) {
        RegexStrings.forEachIndexed() {index, s ->
            Regexs.add(s.toRegex())
        }
    }
    fun Check(s: String): Boolean {
        Regexs.forEach() {
            if (it.containsMatchIn(s)) return true
        }
        return false
    }
    fun GetMessage(): String {
        return Messages.random()
    }
}