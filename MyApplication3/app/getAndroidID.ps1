cd $env:LOCALAPPDATA\Android\Sdk\platform-tools
$androidId = ./adb devices | Select-Object -Skip 1 | Select-Object -SkipLast 1 | ForEach-Object { $_.Substring(0, $_.Length-7) }
$androidIdArray = $androidId.Split("\n")
For ($i=0; $i -lt $androidIdArray.Length; $i++){
    $checkPathExists = (./adb -s $androidIdArray[$i] shell ls sdcard/download/readyToTransfer.txt) | Out-String
    if($checkPathExists -match 'sdcard/download/readyToTransfer.txt'){
        echo "yay"
    }
    else{
        echo "bruh"
    }
}

