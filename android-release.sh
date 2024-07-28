
# First argument is the Gitlab API token, second argument is the version (tag name)
# Careful, you need to change the version number manually in the script before running it for now, TODO fix that!

idArm64=$(curl --header "PRIVATE-TOKEN: $1" --upload-file app/release/app-arm64-v8a-release.apk "https://gitlab.shinice.net/api/v4/projects/109/packages/generic/eu.artectrex.eu/$2/app-release.apk?select=package_file" | jq -r .id)

curl --request POST --header "PRIVATE-TOKEN: $1" --data name="APK (arm64-v8a)" \
  --data url="https://gitlab.shinice.net/pixeldroid/bunny/-/package_files/$idArm64/download" \
  --data direct_asset_path="/package/bunny-$2-202.apk" "https://gitlab.shinice.net/api/v4/projects/109/releases/$2/assets/links"

idArm=$(curl --header "PRIVATE-TOKEN: $1" --upload-file app/release/app-armeabi-v7a-release.apk "https://gitlab.shinice.net/api/v4/projects/109/packages/generic/eu.artectrex.eu/$2/app-release.apk?select=package_file" | jq -r .id)

curl --request POST --header "PRIVATE-TOKEN: $1" --data name="APK (arm)" \
  --data url="https://gitlab.shinice.net/pixeldroid/bunny/-/package_files/$idArm/download" \
  --data direct_asset_path="/package/bunny-$2-201.apk" "https://gitlab.shinice.net/api/v4/projects/109/releases/$2/assets/links"

idx86=$(curl --header "PRIVATE-TOKEN: $1" --upload-file app/release/app-x86-release.apk "https://gitlab.shinice.net/api/v4/projects/109/packages/generic/eu.artectrex.eu/$2/app-release.apk?select=package_file" | jq -r .id)

curl --request POST --header "PRIVATE-TOKEN: $1" --data name="APK (x86)" \
  --data url="https://gitlab.shinice.net/pixeldroid/bunny/-/package_files/$idx86/download" \
  --data direct_asset_path="/package/bunny-$2-203.apk" "https://gitlab.shinice.net/api/v4/projects/109/releases/$2/assets/links"

idx8664=$(curl --header "PRIVATE-TOKEN: $1" --upload-file app/release/app-x86_64-release.apk "https://gitlab.shinice.net/api/v4/projects/109/packages/generic/eu.artectrex.eu/$2/app-release.apk?select=package_file" | jq -r .id)

curl --request POST --header "PRIVATE-TOKEN: $1" --data name="APK (x86_64)" \
  --data url="https://gitlab.shinice.net/pixeldroid/bunny/-/package_files/$idx8664/download" \
  --data direct_asset_path="/package/bunny-$2-204.apk" "https://gitlab.shinice.net/api/v4/projects/109/releases/$2/assets/links"


