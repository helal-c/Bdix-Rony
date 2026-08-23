pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Bdix-Rony"

include(
    ":providers:Banglatube",
    ":providers:BasPlay",
    ":providers:Bdcinema",
    ":providers:Bokasoka",
    ":providers:CineplexBd",
    ":providers:CircleNew",
    ":providers:CircleOld",
    ":providers:CloudFtp",
    ":providers:CtgFtp",
    ":providers:Ctgfun",
    ":providers:DflixLive",
    ":providers:Dhakamovie",
    ":providers:DiscoveryCdn",
    ":providers:DiscoveryMovies",
    ":providers:FlixhubLive",
    ":providers:Fmftp",
    ":providers:FtpMedia",
    ":providers:FtpZone",
    ":providers:FunTime",
    ":providers:Ghuri",
    ":providers:Halum",
    ":providers:Ibccl",
    ":providers:IccFtp",
    ":providers:Ihub",
    ":providers:Jaltrapala",
    ":providers:Moviehaat",
    ":providers:MoviesWorld",
    ":providers:MurgiLive",
    ":providers:NowhdTime",
    ":providers:Polyflix",
    ":providers:RelaxTime",
    ":providers:Royalflix",
    ":providers:SamFtp",
    ":providers:Sarail",
    ":providers:Sebait",
    ":providers:Timepass",
    ":providers:Zflixbd"
)
