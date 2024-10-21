package org.marshsoft.nextcalc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val calculatorViewModel = viewModel { CalculatorViewModel() }
        Calc(calculatorViewModel)
    }
}
@Composable
fun Calc(viewModel: CalculatorViewModel){
   // val expression = viewModel.expression
    val expression by viewModel.expression.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
        val buttonModifier = Modifier.weight(1f).fillMaxHeight().padding(horizontal = 2.dp, vertical = 8.dp)
        Row(modifier = Modifier.fillMaxWidth().weight(2f)) {
            Text(
                text = expression,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 8.dp),
                fontWeight = FontWeight.Light,
                fontSize = 80.sp,
                color = Color.Black,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Clip
            )
        }
        Row( modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            CalcButton("AC",Color.Red, buttonModifier.clickable { viewModel.clear() })
            CalcButton("(",Color.Gray, buttonModifier.clickable { viewModel.append("(") })
            CalcButton(")",Color.Gray, buttonModifier.clickable { viewModel.append(")") })
            CalcButton("÷",Color.Gray, buttonModifier.clickable { viewModel.append("÷") })
        }
        Row(modifier = Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.SpaceBetween)
        {
            CalcButton("7",Color.Black, buttonModifier.clickable { viewModel.append("7") })
            CalcButton("8",Color.Black, buttonModifier.clickable { viewModel.append("8") })
            CalcButton("9", Color.Black, buttonModifier.clickable { viewModel.append("9") })
            CalcButton("x",Color.Gray, buttonModifier.clickable { viewModel.append("×") })

        }
        Row(modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            CalcButton("4",Color.Black, buttonModifier.clickable { viewModel.append("4") })
            CalcButton("5",Color.Black, buttonModifier.clickable { viewModel.append("5") })
            CalcButton("6",Color.Black, buttonModifier.clickable { viewModel.append("6") })
            CalcButton("-",Color.Gray, buttonModifier.clickable { viewModel.append("-") })

        }
        Row(modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            CalcButton("1",Color.Black, buttonModifier.clickable {
                viewModel.append("1") })
            CalcButton("2",Color.Black, buttonModifier.clickable { viewModel.append("2") })
            CalcButton("3",Color.Black, buttonModifier.clickable { viewModel.append("3") })
            CalcButton("+",Color.Gray,buttonModifier.clickable { viewModel.append("+") })


        }
        Row( modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween){
            CalcButton(".",Color.DarkGray, buttonModifier.clickable { viewModel.append(".") })
            CalcButton("0",Color.DarkGray, buttonModifier.clickable { viewModel.append("0") })
            CalcButton("C",Color.DarkGray, buttonModifier.clickable { viewModel.delete() })
            CalcButton("=",Color.Red, buttonModifier.clickable { viewModel.evaluate() })
        }


       
    }
}

@Composable
fun CalcButton(buttonText:String, backgroundColor:Color, modifier: Modifier)
{
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(backgroundColor)
            .then(modifier)
    ) {
        Text(
            text = buttonText,
            fontSize = 36.sp,
            color = Color.White,
        )
    }
}