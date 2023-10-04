// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.Car
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.CarApi


@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

@Composable
fun CarList() {

    val BASE_URL = "http://localhost:8080"
    val retrofit: Retrofit? = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val carApi = retrofit?.create(CarApi::class.java)

    val carsState = remember { mutableStateOf<List<Car>>(emptyList()) }

    LaunchedEffect(Unit) {
        val cars = carApi?.getCars()
        if (cars != null) {
            carsState.value = cars
        }
    }

    LazyColumn {
        items(carsState.value) { car ->
            Text(car.name)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {

        App()
        CarList()
    }
}
