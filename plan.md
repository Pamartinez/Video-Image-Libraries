
## Feature: centralize versionName + versionNameSuffix (all 3 apps)
Unify versionName to a single shared value = 1.1 across gallery-transfer, image, and
video libraries. Move versionName and versionNameSuffix into a single shared Gradle
source (root gradle.properties) so they are declared once and referenced by every
module. versionCode stays per-app. The About screen already reads the built
versionName via PackageManager, which includes the -debug suffix on debug builds, so
it will show e.g. '1.1-debug' automatically once centralized.

### Approach
- Add appVersionName=1.1 and appVersionNameSuffix=-debug to root gradle.properties.
- In each module build.gradle.kts: versionName = providers.gradleProperty(\"appVersionName\").get(); debug versionNameSuffix = providers.gradleProperty(\"appVersionNameSuffix\").get().
- Leave versionCode untouched per app.
- Build + install all three to verify About shows 1.1-debug.

