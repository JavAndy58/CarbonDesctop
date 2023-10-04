package service

import model.Car
import retrofit2.http.GET

interface CarApi {
    @GET("/cars")
    suspend fun getCars(): List<Car>
}