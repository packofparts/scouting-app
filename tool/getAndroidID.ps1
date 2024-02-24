Set-Location $env:LOCALAPPDATA\Android\Sdk\platform-tools
While($true){
if (-Not (./adb devices | Select-Object -Skip 1)){
    continue
}
$androidId = ./adb devices | Select-Object -Skip 1 | Select-Object -SkipLast 1 | ForEach-Object { $_.Substring(0, $_.Length-7) }
$androidIdArray = $androidId.Split("\n")
For ($i=0; $i -lt $androidIdArray.Length; $i++){
    if (-Not (./adb shell "(ls /sdcard/Documents/ScoutingData/lock.txt >> /dev/null 2>&1 && echo y)")) {
        ./adb -s $androidIdArray[$i] pull /sdcard/Documents/ScoutingData $env:USERPROFILE\documents
        ./adb -s $androidIdArray[$i] shell touch /sdcard/Documents/ScoutingData/lock.txt
    }
    
}
}

