# Bdix-Rony CloudStream Repository

A CloudStream extension repository for BDIX (Bangladesh Internet Exchange) movie and FTP sources.

## ✅ Status

All **37 providers are now fully implemented** with:
- ✅ Complete `MainAPI` class structure
- ✅ `getMainPage()` - Fetches and displays homepage content
- ✅ `search()` - Searches across multiple URL patterns
- ✅ `load()` - Loads movie/series details with poster and description
- ✅ `loadLinks()` - Extracts video URLs from various source types
- ✅ Generic HTML parsing that works with most BDIX site structures

**Note:** Many sources use BDIX-internal IP addresses (10.x.x.x, 172.x.x.x) and are only accessible from within Bangladesh ISP networks that are part of the BDIX network.

## Structure

```
Bdix-Rony/
├── providers/           # One provider folder per source (37 total)
│   └── <ProviderName>/
│       ├── build.gradle.kts
│       ├── README.md
│       └── src/main/kotlin/<ProviderName>.kt
├── docs/                # Repository documentation
├── builds/repo.json     # Repository manifest
├── settings.gradle.kts  # Gradle settings (includes all providers)
├── build.gradle.kts     # Root build configuration
└── .github/workflows/   # CI/CD pipeline
```

## Building

```bash
# Make sure you have JDK 17+ installed
./gradlew build

# Build a specific provider
./gradlew :providers:DiscoveryCdn:build

# Build all providers
./gradlew build
```

Built JAR files will be in `providers/<ProviderName>/build/libs/`

## Installing in CloudStream

### Option 1: Manual Installation
1. Build the provider: `./gradlew :providers:<ProviderName>:build`
2. Copy the JAR file from `providers/<ProviderName>/build/libs/`
3. Rename it to `<ProviderName>.cs3`
4. Transfer to your Android device
5. In CloudStream: Settings → Extensions → Add from file

### Option 2: Repository (Recommended)
1. Build all providers
2. Upload JAR files to a public URL (GitHub Releases, etc.)
3. Update `builds/repo.json` with download URLs
4. Host the repo.json file publicly
5. In CloudStream: Add repository URL

## Provider Implementation Details

Each provider includes:

### Generic HTML Parsing
The providers use flexible CSS selectors that work with common BDIX site patterns:
- **Movie links**: `a[href*=/movie/]`, `a[href*=/video/]`, `.movie-item`, `.video-item`
- **Titles**: `h1`, `h2`, `.title`, `.movie-title`
- **Posters**: `img[src*=/poster/]`, `img[src*=/thumb/]`, `.poster img`
- **Video sources**: `video source`, `a[href*=.mp4]`, `a[href*=.mkv]`, `iframe[src]`

### Search Patterns
Tries multiple common search URL formats:
- `/search?q=query`
- `/search/query`
- `/?s=query`
- `/index.php?search=query`

### Video Extraction
Handles multiple video source types:
- Direct video files (.mp4, .mkv, .avi, .m4v, .mov)
- HTML5 `<video>` elements
- Embedded iframes and players
- Direct download links

## Customization

If a specific site doesn't work with the generic parser, you can customize the CSS selectors in the provider's Kotlin file:

```kotlin
// In search() or getMainPage():
document.select("YOUR_CUSTOM_SELECTOR")

// In load():
document.select("h1.custom-title-class")
document.select("img.custom-poster-class")
```

## Available Providers

See [docs/PROVIDERS.md](docs/PROVIDERS.md) or `builds/repo.json` for the complete list of 37 providers.

### Provider Categories

**Public Domain Sites:**
- DiscoveryCdn, DiscoveryMovies, Moviehaat, Ihub, Bokasoka
- Zflixbd, Dhakamovie, Ctgfun, CtgFtp, CineplexBd, Polyflix
- Banglatube, Jaltrapala, FlixhubLive, DflixLive, Fmftp
- Halum, Sarail, MurgiLive, NowhdTime, CircleOld, CircleNew

**BDIX Internal (Private IPs):**
- IccFtp, SamFtp, BasPlay, FtpMedia, CloudFtp, Bdcinema
- FunTime, MoviesWorld, RelaxTime, Royalflix, Timepass
- Ibccl, Ghuri, Sebait, FtpZone

## Development

### Adding a New Provider
1. Create folder: `providers/NewProvider/`
2. Create `build.gradle.kts`:
   ```kotlin
   plugins {
       id("org.jetbrains.kotlin.jvm")
   }
   
   dependencies {
       compileOnly("com.lagradost:cloudstream3:pre-release")
   }
   ```
3. Create `src/main/kotlin/NewProvider.kt` extending `MainAPI`
4. Add to `settings.gradle.kts`: `include(":providers:NewProvider")`
5. Add entry to `builds/repo.json`

### Testing
```bash
# Build specific provider
./gradlew :providers:DiscoveryCdn:build

# Check for compilation errors
./gradlew compileKotlin
```

## CI/CD

The GitHub Actions workflow:
1. Validates `builds/repo.json` format
2. Sets up JDK 17
3. Builds all providers with Gradle
4. Uploads JAR artifacts (7-day retention)

## License

This project is for educational purposes. Make sure you have permission to access and redistribute content from the target sites before publishing any builds.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Implement/test your changes
4. Submit a pull request

## Support

For issues or questions:
- Open an issue on GitHub
- Check CloudStream documentation
- Review the provider implementation for site-specific issues
