package org.marshsoft.nextcalc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.lighthousegames.logging.logging

class CalculatorViewModel: ViewModel() {
    private val _expression = MutableStateFlow("")
    val expression: StateFlow<String> = _expression.asStateFlow()
    fun clear() {
        _expression.value = ""
    }

    fun append(char: String) {
        if (char in "0123456789") {
            _expression.value += char
        }else if(char in "+-×÷") {
            if (expression.value.isNotEmpty()) {
                val lastChar = expression.value.last()

                // if last char is an operator, replace it with the new operator
                if (lastChar in "+-×÷") {
                    _expression.value = expression.value.dropLast(1)
                }
            }
            _expression.value += char
        }else if(char == ".") {
            if (expression.value.isNotEmpty()) {
                val lastChar = expression.value.last()
                if (lastChar!='.') {
                    // if last char is an operator, and the current char is a dot, add a zero before the dot
                    if (lastChar in "+-×÷") {
                        _expression.value += "0"
                    }
                    _expression.value += char
                }
            }

        }else if(char =="("){
            if (expression.value.isNotEmpty()) {
                val lastChar = expression.value.last()
                // if last char is not a operator, add a multiplication operator before the parenthesis
                if (lastChar !in "+-×÷") {
                    _expression.value += "×"
                }
            }
            _expression.value += char
        }else if(char ==")"){
            _expression.value += char
        }
    }

    fun delete() {
        if (expression.value.isNotEmpty()) {
            _expression.value = expression.value.dropLast(1)
        }
    }

    fun evaluate() {
        _expression.value = try {
            val result = evaluate(expression.value)
            result.toString()
        } catch (e: Exception) {
            "Error"
        }
    }
    companion object {
        val log = logging()
    }
}