package com.example.semesterthreeproject

//fun main(){
//    var number: ANy = readln()!!.toInt()
//
//    if(number.toString().toInt() % 2 == 0){
//        println("$number is even")
//    }else{
//        println("$number is odd")
//    }
//}

//fun main(){
//    print("Please enter your age :: ")
//    var yourAge: Int = readln()!!.toInt()
//    if(yourAge < 13){
//        print("You are a child")
//    }else if (yourAge < 19) {
//        print("You are a teenager")
//    }else{
//        if (yourAge < 50){
//            print("You are an adult")
//        }else{
//            print("You are a senior")
//        }
//    }
//}

//fun main(){
//    println("Please enter 3 numbers : ")
//    var number1: Int = readln()!!.toInt()
//    var number2: Int = readln()!!.toInt()
//    var number3: Int = readln()!!.toInt()
//    var largestNumber: Int
//    if(number1 >= number2){
//        if(number1 >= number3){
//            largestNumber = number1
//        }else{
//            largestNumber = number3
//        }
//    }else{
//        if(number2 >= number3){
//            largestNumber = number2
//        }else{
//            largestNumber = number3
//        }
//    }
//    println("the largest number is $largestNumber")
//}

//fun main(){
//    print("Please enter a day number of week : - ") var dayNumber: Int = readln()!!.toInt()
//    var day: String
//    when (dayNumber)
//    {
//        1 -> day = "Sunday"
//
//        2 -> day = "Monday"
//
//        3 -> day = "Tuesday"
//
//        4 -> day = "Wednesday"
//
//        5 -> day = "Thursday"
//
//        6 -> day = "Friday"
//
//        7 -> day = "Saturday"
//
//        else -> day = "Invalid day choice"
//    }
//}

//fun main(){
//    // Fixed first loop
//    for (i in 1..5) {
//        println(i)
//    }
//
//    var sum: Int = 0
//
//// Fixed second loop
//    for (x in 0..5) {
//        println(x)
//        sum += x // sum = sum + x
//    }
//
//    var sum2: Int = 0
//
//// Fixed third loop
//    for (x in 0..10) {
//        if (x % 2 == 0) {
//            println(x)
//            sum2 += x // sum = sum + x
//        }
//    }
//
//    println("The sum of even number is $sum2")
//}

//fun main(){
//    var vehicle = arrayList0f<String>("Tata", "Kia", "Hyundai", "MG")
//    for(y in vehicle.indices){
//        println( "The value in $y index is: - "+ vehicle[y])
//    }
//}

//fun main(){
//    var vehicle = arrayList0f<String>("Tata", "Kia", "Hyundai", "MG")
//
//    vehicle.forEach {
//        println(it)
//    }
//
//}

//fun main() {
//    var i: Int = 0;
//    while (i < 5) {
//        println(i)
//        i++
//    }
//}

//fun main(){
//    var k = 1
//    var fact = 1
//    while (k<6) {
//        fact*=k;
//        println("$k! = $fact");
//        k++
//    }
//}

//import kotlin.random.Random
//
//fun main() {
//    var number = Random.nextInt(from = 0, until = 10000)
//
//    println("Please enter any number from 0 to 10000: - ")
//
//    while (true) {
//        var userGuess: Int = readln().toInt()
//
//        if (userGuess == number) {
//            println("congratulations!!!!, you won")
//            break
//        } else if (userGuess < number) {
//            println("Increase your guess")
//        } else {
//            println("Decrease your guess")
//        }
//    }
//}

//fun main(){
//    var number = 1
//    do{
//        println(number)
//        number++
//    }while (number <= 15)
//}
