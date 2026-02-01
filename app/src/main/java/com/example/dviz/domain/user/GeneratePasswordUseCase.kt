package com.example.dviz.domain.user

import javax.inject.Inject

class GeneratePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): String{
        val latin = password.lowercase()
            .replace("а", "a")
            .replace("б", "b")
            .replace("в", "v")
            .replace("г", "g")
            .replace("д", "d")
            .replace("е", "e")
            .replace("ё", "e")
            .replace("ж", "zh")
            .replace("з", "z")
            .replace("и", "i")
            .replace("й", "j")
            .replace("к", "k")
            .replace("л", "l")
            .replace("м", "m")
            .replace("н", "n")
            .replace("о", "o")
            .replace("п", "p")
            .replace("р", "r")
            .replace("с", "s")
            .replace("т", "t")
            .replace("у", "u")
            .replace("ф", "f")
            .replace("х", "h")
            .replace("ц", "c")
            .replace("ч", "ch")
            .replace("ш", "sh")
            .replace("щ", "sch")
            .replace("ъ", "")
            .replace("ы", "y")
            .replace("ь", "")
            .replace("э", "e")
            .replace("ю", "yu")
            .replace("я", "ya")

        val modifiedPhrase = latin
            .replace("i", "1")
            .replace("o", "0")
            .replace("s", "5")
            .replace("e", "3")
            .replace("a", "4")
            .replace("t", "7")
            .replace("g", "9")
            .replace("z", "2")
            .replace(" ", "_")


        val specialChars = listOf(
            '!',
            '@',
            '#',
            '$',
            '%',
            '^',
            '&',
            '*',
            '(',
            ')',
            '-',
            '_',
            '+',
            '=',
            '[',
            ']',
            '{',
            '}',
            '|',
            ';',
            ':',
            '<',
            '>',
            '.',
            ',',
            '?',
            '/',
            '`',
            '~'
        )

        val stringBuffer = StringBuffer(modifiedPhrase)

        for (n in stringBuffer.indices step 4){
            if(n < stringBuffer.length ){
                stringBuffer.insert(n, specialChars.random())
            }
        }

        for (n in stringBuffer.indices){
            if( stringBuffer.all { it.isLowerCase() } ){
                stringBuffer.replace(n, n, stringBuffer[n].uppercase())
            }
        }
        if(stringBuffer.length < 8) stringBuffer.append(specialChars.random())

        return stringBuffer.toString()
    }
}