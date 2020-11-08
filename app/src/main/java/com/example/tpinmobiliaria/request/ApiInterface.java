package com.example.tpinmobiliaria.request;

import com.example.tpinmobiliaria.model.Contrato;
import com.example.tpinmobiliaria.model.Inmueble;
import com.example.tpinmobiliaria.model.Login;
import com.example.tpinmobiliaria.model.Propietario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    //Iniciar Sesion1 [FromForm] en asp.net
    @FormUrlEncoded
    @POST("Propietarios/API_LOGIN")
    Call<String> loginApi(@Field("Usuario") String mail, @Field("Password") String password);

    //Iniciar Sesion2 [FromBody] en asp.net
    @POST("Propietarios/API_LOGIN")
    Call<String> loginApi2(@Body Login login);

    //Buscar Propietario
    @GET("Propietarios/Logeado")
    Call<Propietario> getUserApi(@Header("Authorization") String token);

    //Modificar Propieterio
    @PUT("Propietarios/MODIFICAR")
    Call<Propietario> modificarPropietarioApi(@Header("Authorization") String token, @Body Propietario propietario);

    //Listar Sus Inmuebles
    @GET("Inmuebles/SUS_INMUEBLES")
    Call<List<Inmueble>> getInmueblesApi(@Header("Authorization") String token);

    //Listar Sus Inmuebles Deshabilitados
    @GET("Inmuebles/SUS_INMUEBLES_DES")
    Call<List<Inmueble>> getInmueblesDesApi(@Header("Authorization") String token);

    //Listar Sus Inmuebles
    @DELETE("Inmuebles/BAJA_LOGICA/{id}")
    Call<Boolean> bajaLogicaInmuebleApi(@Header("Authorization") String token, @Path("id") int idInmueble);

    //Listar Sus Inmuebles
    @GET("Contratos/SUS_CONTRATOS/{id}")
    Call<List<Contrato>> getContratosApi(@Header("Authorization") String token, @Path("id") int idInmueble);

}