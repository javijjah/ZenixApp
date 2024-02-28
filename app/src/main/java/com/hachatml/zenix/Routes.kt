package com.hachatml.zenix

sealed class Routes(val route:String){

    object SplashScreen: Routes("splashscreen")
    object MainScreen: Routes("mainscreen")
    object LoginPage: Routes("loginpage")
    object MeditationPage: Routes("meditationpage")
    object QuotePage: Routes("quotepage")
    object SessionPlaying: Routes("sessionplaying")
    object AccountPage: Routes("accountpage")
}