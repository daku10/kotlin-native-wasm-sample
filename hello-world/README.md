Hello, Kotlin/Native
====

## ビルド&実行

```
$ konanc main.kt -target wasm32 -o hello
$ php -S 0.0.0.0:8000
```

ブラウザでlocalhost:8000にアクセスすると、consoleにHello, Kotlin/Nativeと出ているはずです。
