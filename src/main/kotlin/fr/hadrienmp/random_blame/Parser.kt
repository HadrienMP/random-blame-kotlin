package fr.hadrienmp.random_blame

fun parse(request: String) = "${designationStrategy(request)} ${deliverWay(request)}"

private fun designationStrategy(request: String): String {
    val designatedPerson = designatedPerson(request)
    return if (designatedPerson == null) {
        "random"
    } else {
        "designate " + designatedPerson
    }
}

private fun designatedPerson(request: String) = Regex("(@[^\\s]+)").find(request)?.groupValues?.get(0)

private fun deliverWay(request: String): String {
    if (request.contains("--with-violence")) {
        return "violent"
    }
    return "polite"
}