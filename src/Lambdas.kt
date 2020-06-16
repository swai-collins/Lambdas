typealias DoubleConversion = (Double) -> Double

fun convert (x: Double,
             converter:DoubleConversion): Double {
    val result = converter(x)
    println("$x is converted to $result")
    return result
}

fun getConversionLambda(str: String): DoubleConversion{
    if (str == "CentigradeToFahrenheit"){
        return {it * 1.8 + 32}
    } else if (str == "KgsToPounds"){
        return {it * 2.204623}
    } else if (str == "PoundsToUsTons"){
        return {it / 2000.0}
    } else {
        return {it}
    }
}

fun combine(lambda1: DoubleConversion,
            lambda2: DoubleConversion) : DoubleConversion{
    return {x: Double -> lambda2(lambda1(x))}
}
fun main(args: Array<String>){
    //Convert 2.5kg to pounds
    println("Convert 2.5kg to pounds: ${getConversionLambda("KgsToPounds")(2.5)}")

    //Define two lambdas
    val kgsToPoundsLambdas = getConversionLambda("KgsToPounds")
    val poundsToUSTonsLambdas = getConversionLambda("poundsToUsTons")

    //Combine the two lambdas to create a new one
    val kgsToUSTonsLambda = combine(kgsToPoundsLambdas, poundsToUSTonsLambdas)

    //Use the new lambda to convert 17.4 to US tons
    val value = 17.4
    println("$value kgs is ${convert(value, kgsToUSTonsLambda)} US tons")

}