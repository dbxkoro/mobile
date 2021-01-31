package com.bns.mobile.di.module

import com.bns.mobile.network.mapper.*
import com.bns.mobile.network.services.*
import com.bns.mobile.repository.auth.AuthRepository
import com.bns.mobile.repository.auth.AuthRepositoryBuilder
import com.bns.mobile.repository.balance.BalanceRepository
import com.bns.mobile.repository.balance.BalanceRepositoryBuilder
import com.bns.mobile.repository.params.ParamRepository
import com.bns.mobile.repository.params.ParamRepositoryBuilder

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
            resultServerDtoResponseMapper: ResultDtoMapper,
    ): KeyRepository {
        return KeyRepositoryBuilder(
                service = keyServerService,
                mapper = resultServerDtoResponseMapper,
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
            authService: AuthService,
            authDtoMapper: ResultDtoMapper,
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
    fun provideParamsRepository(
           purposeService: PurposeService,
           provinceService: ProvinceService,
           cityService: CityService,
           incomeService: IncomeService,
           sourceIncomeService: SourceIncomeService,
           degreeService: DegreeService,
           industryService: IndustryService,
           typeWorkService: TypeWorkService,
           resulMapper: ResultDtoMapper
    ): ParamRepository {
        return ParamRepositoryBuilder(
                purpose = purposeService,
                province = provinceService,
                city = cityService,
                income = incomeService,
                source = sourceIncomeService,
                degree = degreeService,
                industrial = industryService,
                typework = typeWorkService,
                mapper = resulMapper,
        )
    }

}