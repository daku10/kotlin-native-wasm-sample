Kotlin/Native WASMからDOMに触るサンプル -Canvasに線を引く-
===

Kotlin/Native WASMでDOMに触るサンプルです。こちらは[公式のサンプル](https://github.com/JetBrains/kotlin-native/tree/master/samples/html5Canvas)を参考にさせていただきました。(というか殆ど一緒)
ライセンスがApache2.0だったので、本サンプルとして変更させていただいたものを公開いたします。

## ビルド&実行

```
$ sh build.sh
$ php -S 0.0.0.0:8000
```

ブラウザでlocalhost:8000にアクセスすると、Canvasが表示されます。
Canvas内部でマウスでクリックしながらポインタを動かすと、線が引けます。
