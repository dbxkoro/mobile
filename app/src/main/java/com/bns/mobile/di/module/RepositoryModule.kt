package com.bns.mobile.di.module

import com.bns.mobile.network.mapper.*
import com.bns.mobile.network.services.*
import com.bns.mobile.repository.auth.AuthRepository
import com.bns.mobile.repository.auth.AuthRepositoryBuilder
import com.bns.mobile.repository.balance.BalanceRepository
import com.bns.mobile.repository.balance.BalanceRepositoryBuilder
import com.bns.mobile.repository.params.city.CityRepository
import com.bns.mobile.repository.params.city.CityRepositoryBuilder
import com.bns.mobile.repository.params.degree.DegreeRepository
import com.bns.mobile.repository.params.degree.DegreeRepositoryBuilder
import com.bns.mobile.repository.params.income.IncomeRepository
import com.bns.mobile.repository.params.income.IncomeRepositoryBuilder
import com.bns.mobile.repository.params.industrial.IndustrialRepository
import com.bns.mobile.repository.params.industrial.IndustrialRepositoryBuilder
import com.bns.mobile.repository.params.province.ProvinceRepository
import com.bns.mobile.repository.params.province.ProvinceRepositoryBuilder
import com.bns.mobile.repository.proxy.ProxyRepository
import com.bns.mobile.repository.proxy.ProxyRepositoryBuilder
import com.bns.mobile.repository.params.purpose.PurposeRepository
import com.bns.mobile.repository.params.purpose.PurposeRepositoryBuilder
import com.bns.mobile.repository.server.KeyRepository
import com.bns.mobile.repository.server.KeyRepositoryBuilder
import com.bns.mobile.repository.params.source.SourceIncomeRepository
import com.bns.mobile.repository.params.source.SourceIncomeRepositoryBuilder
import com.bns.mobile.repository.params.typework.TypeWorkRepository
import com.bns.mobile.repository.params.typework.TypeWorkRepositoryBuilder
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
        provinceDtoMapper: ParamsDtoMapper
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
        cityDtoMapper: ParamsDtoMapper
    ): CityRepository {
        return CityRepositoryBuilder(
            service = cityService,
            mapper = cityDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideDegreeRepository(
        degreeService: DegreeService,
        degreeDtoMapper: ParamsDtoMapper
    ): DegreeRepository {
        return DegreeRepositoryBuilder(
            service = degreeService,
            mapper = degreeDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideIncomeRepository(
        incomeService: IncomeService,
        incomeDtoMapper: ParamsDtoMapper
    ): IncomeRepository {
        return IncomeRepositoryBuilder(
            service = incomeService,
            mapper = incomeDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideIndustrialRepository(
        industryService: IndustryService,
        industryDtoMapper: ParamsDtoMapper
    ): IndustrialRepository {
        return IndustrialRepositoryBuilder(
            service = industryService,
            mapper = industryDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun providePurposeRepository(
        purposeService: PurposeService,
        purposeDtoMapper: ParamsDtoMapper
    ): PurposeRepository {
        return PurposeRepositoryBuilder(
            service = purposeService,
            mapper = purposeDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideSourceRepository(
       sourceIncomeService: SourceIncomeService,
       sourceIncomeDtoMapper: ParamsDtoMapper
    ): SourceIncomeRepository {
        return SourceIncomeRepositoryBuilder(
            service = sourceIncomeService,
            mapper = sourceIncomeDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideWorkRepository(
       typeWorkService: TypeWorkService,
       typeWorkDtoMapper: ParamsDtoMapper
    ): TypeWorkRepository {
        return TypeWorkRepositoryBuilder(
            service = typeWorkService,
            mapper = typeWorkDtoMapper,
        )
    }

}