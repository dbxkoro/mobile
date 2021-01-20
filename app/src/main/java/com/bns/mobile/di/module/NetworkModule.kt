package com.bns.mobile.di.module


import com.bns.mobile.network.mapper.*
import com.bns.mobile.network.mapper.ProvinceDtoMapper
import com.bns.mobile.network.services.*
import com.bns.mobile.presenter.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


// TODO: 07/01/21 SET VARIABLE GLOBAL FOR API URL
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .callTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(providesLoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideProxyService() : ProxyService {
//        TODO("Certificate Pinner SSL Pinning")
        return Retrofit.Builder()
                .baseUrl(BaseApplication.proxy_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build()
                .create(ProxyService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl : String) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build()
    }

    @Singleton
    @Provides
    fun providePublicKeyServer() : KeyServerService {
        return provideRetrofit(BaseApplication.api_url).create(KeyServerService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuth() : AuthService {
        return provideRetrofit(BaseApplication.api_url).create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideBalance() : BalanceService {
        return provideRetrofit(BaseApplication.api_url).create(BalanceService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserInfo() : UserService {
        return provideRetrofit(BaseApplication.api_url).create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideListProvince() : ProvinceService {
        return provideRetrofit(BaseApplication.api_url).create(ProvinceService::class.java)
    }

    @Singleton
    @Provides
    fun provideListCity() : CityService {
        return provideRetrofit(BaseApplication.api_url).create(CityService::class.java)
    }

    @Singleton
    @Provides
    fun provideListDegree() : DegreeService {
        return provideRetrofit(BaseApplication.api_url).create(DegreeService::class.java)
    }

    @Singleton
    @Provides
    fun provideListIncome() : IncomeService {
        return provideRetrofit(BaseApplication.api_url).create(IncomeService::class.java)
    }


    //MAPPER
    @Singleton
    @Provides
    fun provideProxyMapper(): ProxyDtoMapper {
        return ProxyDtoMapper()
    }

    @Singleton
    @Provides
    fun provideKeyMapper(): KeyDtoMapper {
        return KeyDtoMapper()
    }

    @Singleton
    @Provides
    fun provideAuthMapper(): AuthDtoMapper {
        return AuthDtoMapper()
    }

    @Singleton
    @Provides
    fun provideBalanceMapper(): BalanceDtoMapper {
        return BalanceDtoMapper()
    }

    @Singleton
    @Provides
    fun provideUserMapper(): UserDtoMapper {
        return UserDtoMapper()
    }

    @Singleton
    @Provides
    fun provideListProvinceMapper(): ProvinceDtoMapper {
        return ProvinceDtoMapper()
    }

    @Singleton
    @Provides
    fun provideListCityMapper(): CityDtoMapper {
        return CityDtoMapper()
    }

    @Singleton
    @Provides
    fun provideDegreeListMapper(): DegreeDtoMapper {
        return DegreeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideIncomeListMapper(): IncomeDtoMapper {
        return IncomeDtoMapper()
    }

}