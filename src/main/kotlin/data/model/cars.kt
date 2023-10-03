package data.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Car(val id: Int, val name: String, val vinCode: String)

interface CarApi {
    @GET("/cars")
    suspend fun getCars(): List<Car>
}

val retrofit: Retrofit? = Retrofit.Builder()
    .baseUrl("http://localhost:8080")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val carApi = retrofit?.create(CarApi::class.java)