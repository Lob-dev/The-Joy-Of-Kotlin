
fun main(args: Array<String>) {

    // 코틀린에서 Null 을 허용하여 NPE 가 발생할 수 있는 코드는 프로그래머가 처리하여야 한다.
    val s: String? = returnNull()

    // 해당 코드는 컴파일 하지 못한다.
    //val v = s.length

    // Null 을 처리하는 코드 -> 자바와 비슷하다.
    val v = if(s != null) s.length.toString() else null
    println(v)

    // ?. 는 안전한 호출 연산자라고 부른다.
    // 안전한 호출 연산자로 위의 코드를 짧게 처리할 수 있다.
    // 해당 연산자는 null 값이 아닐 때만 호출할 수 있게 동작한다.
    val l = s?.length.toString()
    println(l)

    // !! 연산자를 통해 컴파일러에게 null 이 아님을 보장할 수 있다. -> null 이 넘어오면 NPE 가 발생한다.
    val n = s!!.length.toString()
    println(n)


    // 해당 구문을 통해 null 처리를 하는 if else 구문을 내포하면서 널 안정성을 확보할 수 있다.
    // val city: City? = map[companyName]?.manager?.address?.city

    /* 자바 코드와 비교하면 확실히 간결한 모습을 보인다.
    City city = Optional.ofNullable(map.get(companyName))
                    .flatMap(Company::getManager)
                    .flatMap(Employee::getAddress)
                    .flatMap(Address::getCity)
                    .getOrElse(null);
     */

    // Elvis 연산자는 자바의 Optional.getOrElse() 와 같다.
    // 해당 코드는 중간에 null 이 존재한다면 기본 값을 지정하여 제공할 수 있다.
    // val city: City? = map[companyName]?.manager?.address?.city ?: City.UNKOWN

}

fun returnNull() = null

