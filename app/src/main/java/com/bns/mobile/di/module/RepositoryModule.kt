package com.bns.mobile.di.module

import com.bns.mobile.network.mapper.AuthDtoMapper
import com.bns.mobile.network.mapper.BalanceDtoMapper
import com.bns.mobile.network.mapper.KeyDtoMapper
import com.bns.mobile.network.mapper.ProxyDtoMapper
import com.bns.mobile.network.services.AuthService
import com.bns.mobile.network.services.BalanceService
import com.bns.mobile.network.services.KeyServerService
import com.bns.mobile.network.services.ProxyService
import com.bns.mobile.repository.auth.AuthRepository
import com.bns.mobile.repository.auth.AuthRepositoryBuilder
import com.bns.mobile.repository.balance.BalanceRepository
import com.bns.mobile.repository.balance.BalanceRepositoryBuilder
import com.bns.mobile.repository.proxy.ProxyRepository
import com.bns.mobile.repository.proxy.ProxyRepositoryBuilder
import com.bns.mobile.repository.server.KeyRepository
import com.bns.mobile.repository.server.KeyRepositoryBuilder
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


}