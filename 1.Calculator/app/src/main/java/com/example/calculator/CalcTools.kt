package com.example.calculator


import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.lang.NumberFormatException

class CalcTools {

    private val maxLen = 18
    var text = "0"

    fun addNum(num: Char) {
        if (text.length == maxLen)
            return
        if (checkZero())
            text = text.dropLast(1)
        text += num
    }

    fun addSign(sign: Char) {
        if (text.length == maxLen)
            return
        if ((sign == '.') and checkPoint())
            return
        if (!text[text.lastIndex].isDigit())
            text = text.dropLast(1)
        text += sign
    }

    fun clearAll() {
        text = "0"
    }

    fun cancelOne() {
        if (text.length > 1)
            text = text.dropLast(1)
        else
            clearAll()
    }

    fun calculate(): String {
        text = try {
            ExpressionBuilder(text).build().evaluate().toString()
        } catch (e: ArithmeticException) {
            "division_by_zero"
        } catch (e: NumberFormatException) {
            "incorrect_number_format"
        } catch (e: IllegalArgumentException) {
            "invalid_expression"
        } catch (e: Exception) {
            "Error!"
        }
        return text
    }


    private fun checkZero(): Boolean {
        return ((text[text.lastIndex] == '0') and !checkPoint())
    }

    private fun checkPoint(): Boolean {
        var point = false
        for (char in text) {
            if (!char.isDigit()) point = char == '.'
        }
        return point
    }


}