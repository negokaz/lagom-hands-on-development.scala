# 事前準備

ハンズオン当日までにこのページの手順に従って準備をお願いします。

* JDK のインストール
* コマンドラインツールのインストール
* プロジェクトのクローン
* IntelliJ IDEA のインストールとプロジェクトのインポート
* プロジェクトの起動確認

## 困ったときは

事前準備でわからないことがあったり、トラブルが起きた場合は [Issues](https://github.com/negokaz/lagom-hands-on-development.scala/issues) から連絡をお願いします。

## JDK のインストール

Java 8 の JDK がインストールされていない場合は、下記からインストーラをダウンロードしてインストールしてください。

[Java SE - Downloads | Oracle Technology Network | Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## コマンドラインツールのインストール

下記のコマンドラインツールをインストールしてください。

* [Git](https://git-scm.com/) : [Git - Gitのインストール](https://git-scm.com/book/ja/v2/%E4%BD%BF%E3%81%84%E5%A7%8B%E3%82%81%E3%82%8B-Git%E3%81%AE%E3%82%A4%E3%83%B3%E3%82%B9%E3%83%88%E3%83%BC%E3%83%AB)
* [sbt](http://www.scala-sbt.org/) : [sbtをインストールする · Scala研修テキスト](http://dwango.github.io/scala_text/sbt-install.html)

## プロジェクトのクローン

プロジェクトをローカル環境にクローンします。

```bash
$ git clone https://github.com/negokaz/lagom-hands-on-development.scala.git
```

## IntelliJ IDEA のインストールとプロジェクトのインポート

下記を参考に、IntelliJ IDEA のインストールと、クローンしたプロジェクトのインポートを行って下さい。

[IDE(Intellij IDEA) · Scala研修テキスト](http://dwango.github.io/scala_text/IDE.html)

## プロジェクトの起動確認

IntelliJ IDEA の Terminal から下記のコマンドを実行して、プロジェクトが起動できることを確認します。

```bash
$ sbt runAll

~~~ ↓ (以下、同じように表示されれば OK) ↓ ~~~

[info] (Services started, press enter to stop and go back to the console...)
```

Terminal 上で Enter キーを押すと終了します。

## おつかれさまでした！ :tada::tada::tada:

事前準備は以上で完了です。

![](http://i.imgur.com/iZbk1kW.gif)
