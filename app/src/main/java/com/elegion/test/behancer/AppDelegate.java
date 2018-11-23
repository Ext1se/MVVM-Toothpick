package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.di.DI;
import com.elegion.test.behancer.di.models.AppModule;
import com.elegion.test.behancer.di.models.NetworkModule;
import com.elegion.test.behancer.helper_picasso.PicassoHelper;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;
import toothpick.smoothie.module.SmoothieApplicationModule;

public class AppDelegate extends Application {

    private static Scope sAppScope;

    @Override
    public void onCreate() {
        super.onCreate();
        PicassoHelper.createPicasso(this);

        Configuration configuration;
        if (BuildConfig.DEBUG) {
            configuration = Configuration.forDevelopment().preventMultipleRootScopes();
        } else {
            configuration = Configuration.forProduction().disableReflection();
        }
        Toothpick.setConfiguration(configuration);
        //MemberInjectorRegistryLocator.setRootRegistry(new MemberInjectorRegistry());
        //FactoryRegistryLocator.setRootRegistry(new FactoryRegistry());

        sAppScope = Toothpick.openScope(DI.APP_SCOPE);
        sAppScope.installModules(new SmoothieApplicationModule(this), new AppModule(this), new NetworkModule());
    }

    public static Scope getAppScope() {
        return sAppScope;
    }
}
