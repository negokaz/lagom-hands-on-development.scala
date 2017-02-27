# 事前準備

ハンズオン当日までにこのページの手順に従って準備をお願いします。

* JDK のインストール
* コマンドラインツールのインストール
* IntelliJ IDEA のインストール
* プロジェクトの動作確認

## 困ったときは

事前準備でわからないことがあったり、トラブルが起きた場合は [Issues](https://github.com/negokaz/lagom-hands-on-development.scala/issues) から連絡をお願いします。

## JDK のインストール

*-TODO-*

## コマンドラインツールのインストール

このハンズオンでは、下記のコマンドラインツールを使います。

* [Git](https://git-scm.com/)
* [HTTPie](https://httpie.org/)
* [sbt](http://www.scala-sbt.org/)

## IntelliJ IDEA のインストール

*-TODO-*

## プロジェクトの動作確認

プロジェクトをローカル環境にクローンして起動します。

```bash
$ git clone https://github.com/negokaz/lagom-hands-on-development.scala.git
$ cd lagom-hands-on-development.scala
$ sbt runAll

~~~ ↓ (以下、同じように表示されれば OK) ↓ ~~~

[info] (Services started, press enter to stop and go back to the console...)
```

別のターミナルから、リクエストを投げてみます。

```bash
$ http :9000/api/hello/Lagom%20World

~~~ ↓ (以下、同じように表示されれば OK) ↓ ~~~

HTTP/1.1 200 OK
Content-Length: 13
Content-Type: text/plain
Date: Mon, 27 Feb 2017 00:00:00 GMT

Hello, Lagom World!

```

## おつかれさまでした！ :tada::tada::tada:

事前準備は以上で完了です。

![](http://i.imgur.com/iZbk1kW.gif)
