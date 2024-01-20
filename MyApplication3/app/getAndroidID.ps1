Set-Location $env:LOCALAPPDATA\Android\Sdk\platform-tools
While($true){
if (-Not (./adb devices | Select-Object -Skip 1)){
    continue
}
$androidId = ./adb devices | Select-Object -Skip 1 | Select-Object -SkipLast 1 | ForEach-Object { $_.Substring(0, $_.Length-7) }
$androidIdArray = $androidId.Split("\n")
For ($i=0; $i -lt $androidIdArray.Length; $i++){
    ./adb -s $androidIdArray[$i] shell mkdir /sdcard/ScoutingData
}
}

