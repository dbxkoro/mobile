package com.bns.mobile.di.module

import com.bns.mobile.network.mapper.*
import com.bns.mobile.network.mapper.ProvinceDtoMapper
import com.bns.mobile.network.services.*
import com.bns.mobile.repository.auth.AuthRepository
import com.bns.mobile.repository.auth.AuthRepositoryBuilder
import com.bns.mobile.repository.balance.BalanceRepository
import com.bns.mobile.repository.balance.BalanceRepositoryBuilder
import com.bns.mobile.repository.city.CityRepository
import com.bns.mobile.repository.city.CityRepositoryBuilder
import com.bns.mobile.repository.province.ProvinceRepository
import com.bns.mobile.repository.province.ProvinceRepositoryBuilder
import com.bns.mobile.repository.proxy.ProxyRepository
import com.bns.mobile.repository.proxy.ProxyRepositoryBuilder
import com.bns.mobile.repository.server.KeyRepository
import com.bns.mobile.repository.server.KeyRepositoryBuilder
import com.bns.mobile.repository.user.UserRepository
import com.bns.mobile.repository.user.UserRepositoryBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideProxyRepository(
            proxyService: ProxyService,
            proxyDtoResponseMapper: ProxyDtoMapper,
    ): ProxyRepository {
        return ProxyRepositoryBuilder(
                service = proxyService,
                mapper = proxyDtoResponseMapper,
        )
    }

    @Singleton
    @Provides
    fun provideKeyServerRepository(
            keyServerService: KeyServerService,
            keyServerDtoResponseMapper: KeyDtoMapper,
    ): KeyRepository {
        return KeyRepositoryBuilder(
                service = keyServerService,
                mapper = keyServerDtoResponseMapper,
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
            authService: AuthService,
            authDtoMapper: AuthDtoMapper,
    ): AuthRepository {
        return AuthRepositoryBuilder(
                service = authService,
                mapper = authDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideBalanceRepository(
        balanceService: BalanceService,
        balanceDtoMapper: BalanceDtoMapper,
    ): BalanceRepository {
        return BalanceRepositoryBuilder(
            service = balanceService,
            mapper = balanceDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        userService : UserService,
        userDtoMapper: UserDtoMapper,
    ): UserRepository {
        return UserRepositoryBuilder(
            service = userService,
            mapper = userDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideProvinceRepository(
        provinceService: ProvinceService,
        provinceDtoMapper: ProvinceDtoMapper
    ): ProvinceRepository {
        return ProvinceRepositoryBuilder(
            service = provinceService,
            mapper = provinceDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideCityRepository(
        cityService: CityService,
        cityDtoMapper: CityDtoMapper
    ): CityRepository {
        return CityRepositoryBuilder(
            service = cityService,
            mapper = cityDtoMapper,
        )
    }

}