class: middle, center

# Scala ではじめる<br>マイクロサービス
## Lagom でチャットを実装してみよう！

???

Total: 1:25

* 0:10: 前説・STEP0
* 0:15: STEP1: API定義
* 0:15: STEP2: メッセージ配信
* 0:30: STEP3: 永続化
* 0:10: STEP4: CB
* 0:05: まとめ・質問

---

## 準備

下記 URL の手順に従って準備してください

1. ダッシュボードを開く: http://bit.ly/lagom-scala (Chrome 推奨)
2. IntelliJ IDEA を開く
3. `lagom-hands-on-delelopment.scala` のプロジェクトを開く
4. ターミナルで下記を実行
```bash
git fetch origin
git checkout step1
```


---
class: middle, center

# Scala ではじめる<br>マイクロサービス
## Lagom でチャットを実装してみよう！

---

## 私は何者？

* 根来 和輝 .whisper[Negoro Kazuki]
* TIS株式会社 生産技術R＆D室
* Reactive System に関する技術検証・コンサル
    * Lightbend Reactive Platform
        * Scala / Akka / Play framework

.with-twitter-icon[[@negokaz](https://twitter.com/negokaz)]
.with-github-icon[[negokaz](https://github.com/negokaz)]

---

## マイクロサービスアーキテクチャ？

* 2014年3月に ThoughtWorks社 の Martin Fowler によって提唱された
* 小さいサービスを組み合わせて 一つのアプリケーションを構築するスタイルのこと
* システムを復数に分割することによって デリバリスピードや可用性の面においてメリットがある

.whisper[※ 以降は MSA と略記]

---

## MSA のトッピックス

* アプリ層の設計
* インフラ層の設計
* ドメイン駆動設計
* (人の)組織
* システム運用
* セキュリティ
* ...etc

---

## 今日やること・やらないこと

* **アプリ層の設計**
* ~~インフラ層の設計~~
* ~~ドメイン駆動設計~~
* ~~(人の)組織~~
* ~~システム運用~~
* ~~セキュリティ~~
* ~~...etc~~

---

## Lagom とは？

.height-3.center[![](r/img/lagom_logo.svg)]

* Lightbend社が3月にリリースしたMSA向けの<br>サーバーサイドフレームワーク
* 提供されている内部APIは Scala/Java
* コンセプトは **Reactive** *Microservices Architecture*
    * 大規模なシステムがターゲット

.footnote[
[https://www.lightbend.com/lagom](https://www.lightbend.com/lagom)

[Reactive Microservices Architecture (O’Reilly)](http://www.oreilly.com/programming/free/reactive-microservices-architecture.html)
]

---

## Lagom が目指すもの

.center[
<iframe src="https://www.slideshare.net/slideshow/embed_code/key/bpqBuXOj1fENSp?startSlide=29" width="577" height="470" frameborder="1" marginwidth="0" marginheight="0" scrolling="no"></iframe>
]

.footnote[
[リアクティブ・アーキテクチャ ～大規模サービスにおける必要性と課題〜](http://www.slideshare.net/okapies/reactive-architecture-20160218-58403521)
]

---

## Lagom の特徴的な設計ポリシー

* サービス内部で使うAPIは全て**非同期API**
  .with-arrow[スレッドを使いまわしてリソースを節約]
* 外部APIの呼び出しは全て **Circuit Breaker** が有効
  .with-arrow[依存するサービスが応答できなくなっても 即応性を維持]

.footnote[
[Lagom #Lagom design philosophy](http://www.lagomframework.com/documentation/1.3.x/scala/LagomDesignPhilosophy.html)

[Lagom #Polyglot systems with Lagom](http://www.lagomframework.com/documentation/1.3.x/scala/PolyglotSystems.html)
]

---

## 詳しくはこちら

.center[
<iframe src="https://www.slideshare.net/slideshow/embed_code/key/xcxq7ErCS7Rg5" width="577" height="470" frameborder="1" marginwidth="0" marginheight="0" scrolling="no"></iframe>
]

.footnote[
[Lagom で学ぶ Reactive Microservices Architecture](https://www.slideshare.net/negokaz/lagom-reactive-microservices-architecture)
]

???

設計ポリシーについてより詳しく理解したい人はこちらを参照

---

## Lagom が提供する機能

* スケーラブルな永続化
  * **Event Sourcing**
  * **CQRS**
* 疎結合なコンポーネント間通信
  * **Pub-Sub**
* クラスタリングで分散処理
  * **Cluster Sharding**

.with-arrow[要件に応じて取捨選択が可能]

???

今日は Lagom の基本的なところに加えて、上２つを取り上げます

---
class: middle, center

### Hands-On STEP 0
## 今日 Lagom で作るもの

???

Lagom の概要を知れたところで

---

## Lagom Chat

Slack のようなチャットアプリケーション

.center[
.with-border-frame.height-14[![](r/img/lagom-chat.png)]
]

---

## Lagom Chat のサービス

* Web Gateway
.with-arrow[ユーザー認証・UIを提供]
* User Service
.with-arrow[ユーザー登録・参照]
* Message Service
.with-arrow[メッセージの投稿・配信]

---

## Lagom Chat のサービス構成


.as-underlay[
.height-14[![](r/img/lagom-chat-services.svg)]
]

--

.as-underlay[
.height-14[![](r/img/lagom-chat-services.write.svg)]
]

--

.as-underlay[
.height-14[![](r/img/lagom-chat-services.read.svg)]
]

---

## プロジェクトの構成

* Message Service
  * `message-api` .whisper[(*-api: インターフェイス)]
  * `message-impl` .whisper[(*-impl: 具体的な実装)]
* User Service
  * `user-api`
  * `user-impl`
* Web Gateway
  * `web-gateway`

---

## 進捗状況

* .with-checkbox-on[Web Gateway]
* .with-checkbox-on[User Service]
* .with-checkbox-off[*Message Service*]
  * 重要な部分以外は既に実装済み
  
.with-arrow[メッセージの投稿と閲覧ができない状態]

---

## Message Service の API

下記のような API を想定して開発します

* チャットルームにメッセージを投稿できる
.with-arrow[`POST` `/api/messages/:userId`]
* 投稿されたメッセージをリアルタイムに確認できる
.with-arrow[`GET` `/api/messagestream` .whisper[(WebSocket)]]
* チャットルームに入る前のメッセージを確認できる
.with-arrow[`GET` `/api/messages`]

---
class: middle, center

### Hands-On STEP 1
## サービスの API を実装してみよう

???

【0:10: 前説・STEP0】

---

## アプリを起動

ターミナルからプロジェクト直下で`sbt runAll`を実行

.with-code-annotation[
`Terminal`
```bash
$ sbt runAll

~~~ ↓ (下記のログが表示されれば 起動完了) ↓ ~~~

[info] (Services started, press enter to stop and go back to the console...)
```
]

起動後、ブラウザで下記にアクセス

http://localhost:9000

※ ソースの変更は自動反映されるため変更後の再起動は不要

---

## API の実装

1. Serivce の API を定義したトレイトを実装
  .with-arrow[`message-api > MessageService.scala`]
2. 定義したトレイトをミックスインして具象クラスを実装
  .with-arrow[`message-impl > MessageServiceImpl.scala`]

---

## Message Service の API

下記の API を `MessageService` トレイトに定義します

* .with-checkbox-off[チャットルームにメッセージを投稿できる]
  * `POST` `/api/messages/:userId`
* .with-checkbox-off[投稿されたメッセージをリアルタイムに確認できる]
  * `GET` `/api/messagestream` .whisper[(WebSocket)]
* .with-checkbox-off[チャットルームに入る前のメッセージを確認できる]
  * `GET` `/api/messages`

---

## メッセージの投稿

`POST` `/api/messages/:userId`

Request:
```javascript
{
   body: "メッセージ"
}
```

Response:
```javascript
HTTP/1.1 200 OK
Content-Length: 0
```

---
name: first_descriptor

## Message Service の Descriptor を定義

.with-code-annotation[
`com.example.lagomchat.message.api.MessageService`
```scala
// ServiceCall を返す抽象メソッド (定義済み)
def sendMessage(userId: String): ServiceCall[RequestMessage, Done]
```
]

.with-code-annotation[
`com.example.lagomchat.message.api.MessageService`
```scala
override def descriptor = {
  import Service._
  named("message").withCalls(
    // TODO: パスとメソッドのマッピングを定義
    pathCall("/api/messages/:userId", sendMessage _)
  ).withAutoAcl(true)
}
```
]

.footnote[
IntelliJ の `Navigate > Class... (Ctrl + N)` でクラスの名前を指定してジャンプできます
]

???

★Hands-On★

---

## ServiceCall

サービスの API コールを抽象化したクラス

シグネチャ:
```scala
trait ServiceCall[Request, Response]
```

* 第一型引数が Request Body
* 第二型引数が Response Body

を表す。

---
name: json_mapping

## Request Body

JSON の Request Body が case class にマッピングされる

`ServiceCall[RequestMessage, _]` と宣言すると…

.with-array-to-bottom[
.with-code-annotation[
`JSON`
```javascript
{
   body: "メッセージ"
}
```
]
]
<div style="height:1rem"></div>
.with-code-annotation[
`com.example.lagomchat.message.api.MessageService`
```scala
case class RequestMessage(body: String) // ⇒ RequestMessage("メッセージ")
```
]

---

## pathCall

API のパスをメソッドにマッピングする

シグネチャ:
```scala
def pathCall[Request, Response](pathPattern: String, method: ScalaMethodServiceCall[Request, Response])
```

* 第一引数に API のパス
* 第二引数に `ServiceCall` を返すメソッド

を指定する。

.footnote[
[ここ](#first_descriptor)での第二引数は `sendMessage`
]

---

## pathCall

`ServiceCall` の型引数によってHTTPメソッドが決まる

| 型宣言 | HTTPメソッド |
|-------|-------------|
|`ServiceCall[NotUsed, _]`|`GET`|
|`ServiceCall[_, _]`|`POST`|

.footnote[
任意のHTTPメソッドを指定できる`restCall`という APIもある

[Lagom - Service descriptors #REST identifiers](https://www.lagomframework.com/documentation/1.3.x/scala/ServiceDescriptors.html#REST-identifiers)
]

---

## 他の API も定義してみよう

* .with-checkbox-on[チャットルームにメッセージを投稿できる]
  * `POST` `/api/messages/:userId`
* .with-checkbox-off[*投稿されたメッセージをリアルタイムに確認できる*]
  * `GET` `/api/messagestream` .whisper[(WebSocket)]
* .with-checkbox-off[*チャットルームに入る前のメッセージを確認できる*]
  * `GET` `/api/messages`

.footnote[
`withCalls` にカンマ区切りで復数定義できる
]

???

★Hands-On★

---

## メッセージのストリーム .small[(WebSocket)]

`GET` `/api/messagestream`

Frames:
.with-code-annotation[
`JSON`
```javascript
{ body: "わーい！", user: "user1", timestamp: 1488866889258 }
{ body: "すごーい！", user: "user2", timestamp: 1488866889259 }
```
]

---
name: def_message_stream

## メッセージのストリーム .small[(WebSocket)]

.with-code-annotation[
`com.example.lagomchat.message.api.MessageService`
```scala
def messageStream(): ServiceCall[NotUsed, Source[Message, NotUsed]]
```
]

Response を `Source[_, _]` にすることによって
WebSocket の API を定義できる

* ストリームデータの **入力** を表す
* 第一型引数が入力データの型
* 第二型引数はストリーム終了時に得られる型
  * 使わないので `NotUsed`

---

### メッセージの一覧を取得

`GET` `/api/messages`

Response:
```javascript
HTTP/1.1 200 OK

[
  { body: "わーい！", user: "user1", timestamp: 1488866889258 },
  { body: "すごーい！", user: "user2", timestamp: 1488866889259 }
]
```
-------

.with-code-annotation[
`com.example.lagomchat.message.api.MessageService`
```scala
def messages(): ServiceCall[NotUsed, Seq[Message]]
```
]

---

## Service Descriptor の完成形

下記のようになっていれば、完成です

.with-code-annotation[
`com.example.lagomchat.message.api.MessageService`
```scala
override def descriptor = {
  import Service._
  named("message").withCalls(
    // パスとメソッドのマッピングを定義
    pathCall("/api/messages/:userId", sendMessage _),
    pathCall("/api/messagestream", messageStream),
    pathCall("/api/messages", messages)
  ).withAutoAcl(true)
}
```
]

---

## Message Service の具象クラスを実装

.with-code-annotation[
`com.example.lagomchat.message.impl.MessageServiceImpl`
```scala
override def sendMessage(id: String) = ServiceCall { requestMessage =>
  // TODO: メッセージを PubSub に publish する
  // TODO: メッセージを Entity に送る
* println(s"$requestMessage from $id")
* Future.successful(Done)
}

override def messageStream() = ServiceCall { _ =>
  ???
}
```
]

* requestMessage: <br>JSON の値がマッピングされた case class .small[([参照](#json_mapping))]
* メッセージが POST されたらターミナルに表示

???

★Hands-On★

---

## 間に合わなかった場合

下記のコマンドでブランチを`step2`に切り替えてください

.with-code-annotation[
`Terminal`
```bash
git checkout step2
```
]

---

## 実装の確認

Web Gateway には既に Message Service を呼び出す
機能が実装されている

http://localhost:9000/chat

* .with-checkbox-on[*ターミナルに*投稿したメッセージが表示される]

.with-code-annotation[
`Terminal`
```bash
RequestMessage(わーい！) from user1
RequestMessage(すごーい！) from user1
```
]

---

## Web Gateway 上の実装 .small[(参考)]

.with-code-annotation[
`controllers.ChatController`
```scala
def receiveMessage = Authenticated.async { implicit request =>
  request.body.asJson.flatMap(_.validate[RequestMessage].asOpt).map { msg =>
*  messageService
*     .sendMessage(request.user)
*     .invoke(msg)
      .map(_ => NoContent)
  }.getOrElse(Future.successful(BadRequest))
}
```
]

`ServiceCall#invoke()` をコールしてサービスを実行する

.with-arrow[Message Service に対してHTTPリクエストが発行される]

---
class: middle, center

### Hands-On STEP 2
## メッセージを配信してみよう

???

【0:25: STEP1: API定義】

---

## メッセージを配信してみよう

他のユーザーが投稿したメッセージを リアルタイムに確認したい

1. メッセージがクライアントから POST される
2. メッセージを他のクライアントに配信する

.with-arrow[**PubSub** を使うと簡単に実装できる]

---

## メッセージの配信を実装

投稿されたメッセージを配信するための Topic を作成

.with-code-annotation[
`com.example.lagomchat.message.impl.MessageServiceImpl`
```scala
// TODO: メッセージを配信するための Topic を作成
val topic = pubSub.refFor(TopicId[Message])
```
]

???

★Hands-On★

---

## メッセージの配信を実装

作成した Topic に対してメッセージを `publish`

.with-code-annotation[
`com.example.lagomchat.message.impl.MessageServiceImpl`
```scala
override def sendMessage(id: String) = ServiceCall { requestMessage =>
* val message = Message(requestMessage.body, id, DateTime.now())
  // TODO: メッセージを PubSub に publish する
* topic.publish(message)
  // TODO: メッセージを Entity に送る
  println(s"$requestMessage from $userId")
  Future.successful(Done)
}
```
]

???

★Hands-On★

---

## メッセージの配信を実装

Topic の `subscriber` を `Future` で包んで返すだけ

.with-code-annotation[
`com.example.lagomchat.message.impl.MessageServiceImpl`
```scala
override def messageStream() = ServiceCall { _ =>
  // TODO: PubSub で subscribe したメッセージを流す
* Future.successful(topic.subscriber)
}
```
]

`subscriber` のシグネチャ:
```scala
def subscriber: Source[T, NotUsed] // T: ここでは Message
```

.footnote[
参照: [メッセージストリームの API 定義](#def_message_stream)
]

???

★Hands-On★

---

## PubSub の制約

* 到達保証はされない .whisper.sup[※1]
* 同一サービス内でのみ使える

上記が必要な場合は [Message Broker API](https://www.lagomframework.com/documentation/1.3.x/scala/MessageBroker.html) を使う

.footnote[
※1 将来のリリースで解消される予定:
[Lagom - Publish-Subscribe #Limitations](https://www.lagomframework.com/documentation/1.3.x/scala/PubSub.html#Limitations)
]

---

## 間に合わなかった場合

下記のコマンドでブランチを`step3`に切り替えてください

.with-code-annotation[
`Terminal`
```bash
git checkout step3
```
]

---

## 実装の確認

http://localhost:9000/chat

* .with-checkbox-on[投稿したメッセージは他のユーザーからも確認できる]
  * シークレットウィンドウから他のユーザーでログイン
* リロードするとメッセージが消える

---
class: middle, center

### Hands-On STEP 3
## メッセージを永続化する

???

0:40: STEP2: メッセージ配信

---

## Lagom における永続化

* 高い可用性とスケーラビリティを実現するため **Event Sourcing** と **CQRS** による永続化のしくみを備えている
* データストアは Cassandra と RDB が選択できる

.footnote[
[Lagom - Relational Database Persistent Entities](https://www.lagomframework.com/documentation/1.3.x/scala/PersistentEntityRDBMS.html)

[Lagom - Cassandra Persistent Entities](https://www.lagomframework.com/documentation/1.3.x/scala/PersistentEntityCassandra.html)
]

---

## Event Sourcing

* システムの中で起きた**イベント**を永続化する
* イベントは不変(immutable)
    * キャッシュ、コピー、共有が容易にできる
        * スケールしやすい
        * 耐障害性を高められる
* ロック不要
    * 高いスループットを実現できる

---

## Event Sourcing

* システムの中で起きた**イベント**を永続化する

.center[
.height-13[![](r/img/event-sourcing.4.svg)]
]

---

## Event Sourcing

* デメリット
  * データの集計にコストがかかる
  .with-arrow[**CQRS** で解決できる]

---

## CQRS

* *C*ommand and *Q*uery *R*esponsibility *S*egregation
    コマンドクエリ責務分離
* コマンド(書き込み)とクエリ(読み込み)を分離する

.center[
.height-10[![](r/img/command-and-query.svg)]
]

---

## CQRS

.center.without-margin[
.height-8[![](r/img/command-and-query.svg)]
]
.without-margin[
書き込み側と読み込み側で
* 異なるDB・データ構造が使える
* 別々にスケールできるようになる
.with-arrow[Command-Side にイベントソーシングを使い<br>Query-Side に集計しやすい形で永続化する]
]

---

## CQRS

.center[
![](r/img/es-and-cqrs.svg)
]

---

## Entity を実装

`behavior` に Entity の状態ごとの振る舞いを定義する

* `onCommand`: コマンドからイベントを作成して永続化
* `onEvent`: イベントに基いて Entity の状態を更新
.with-arrow[ここでは簡単にメッセージ数をカウント]

.with-code-annotation[
`com.example.lagomchat.message.impl.RoomEntity`
```scala
override def behavior = {
    // ...(略)... //
  case RoomState(_, _) =>
    Actions()
      .onCommand[PostMessage, Done] {
        case (msg, ctx, state) =>
*         ??? // TODO: MessagePosted を永続化する
      }
      .onEvent {
        case (_: MessagePosted, state) =>
*         ??? // TODO: state の countOfMessage をインクリメントする
      }
}
```
]

---

## Entity を実装

.with-code-annotation[
`com.example.lagomchat.message.impl.RoomEntity`
```scala
override def behavior = {
  // ...(略)... //
  case RoomState(_, _) =>
    Actions()
      .onCommand[PostMessage, Done] {
        case (msg, ctx, state) =>
          // MessagePosted を永続化する
*         val msgId = UUID.randomUUID()
*         val event = MessagePosted(msgId, state.roomId, msg.message, msg.user, msg.timestamp)
*         ctx.thenPersist(event)(_ => ctx.reply(Done))
      }
      .onEvent {
        case (_: MessagePosted, state) =>
          // state の countOfMessage をインクリメントする
*         state.incrementsMessages
      }
}
```
]

???

★Hands-On★

---

## EventProcessor を実装

イベントに基いてデータを更新するクエリを作る

.with-arrow[`processMessagePosted` として定義済 ↓]

.with-code-annotation[
`com.example.lagomchat.message.impl.RoomEventProcessor`
```scala
// 定義済み
private def processMessagePosted(e: EventStreamElement[MessagePosted]): Future[List[BoundStatement]] = {
  writeMessage.future.map { prepareStatement =>
    // INSERT INTO message (roomId, id, message, user, timestamp) VALUES (?, ?, ?, ?, ?)
    val bind = prepareStatement.bind()
    bind.setString("roomId", e.event.roomId)
    bind.setUUID("id", e.event.id)
    bind.setString("message", e.event.message)
    bind.setString("user", e.event.user)
    bind.setTimestamp("timestamp", e.event.timestamp.toDate)
    List(bind)
  }
}
```
]

---

## EventProcessor を実装

`ReadSideHandler` に `EventHandler` として登録

.with-code-annotation[
`com.example.lagomchat.message.impl.RoomEventProcessor`
```scala
override def buildHandler(): ReadSideHandler[RoomEvent] = {
  val builder = readSide.builder[RoomEvent]("roomoffset")
  builder.setGlobalPrepare(createTable)
  builder.setPrepare(_ => prepareWriteUser())
  // Entity で起きたイベントを Read モデルに反映する
* builder.setEventHandler[MessagePosted](processMessagePosted)
  builder.build()
}
```
]

???

★Hands-On★

---

## メッセージ一覧を読み取る

EventProcessor で書き込んだデータを SELECT するだけ

.with-code-annotation[
`com.example.lagomchat.message.impl.MessageServiceImpl`
```scala
override def messages(): ServiceCall[NotUsed, Seq[Message]] = ServiceCall { _ =>
  // TODO: メッセージの一覧を返す
* cassandra
*   .select(
*     """
*       | SELECT message, user, timestamp
*       | FROM message
*       | WHERE roomId = ?
*       | ORDER BY timestamp ASC
*     """.stripMargin, RoomEntity.RoomId)
*   .map { row =>
*     Message(
*       body = row.getString("message"),
*       user = row.getString("user"),
*       timestamp = new DateTime(row.getTimestamp("timestamp"))
*     )
*   }
*   .runFold(Seq.empty[Message])((acc, e) => acc :+ e)
}
```
]

???

★Hands-On★

---

## Entity にコマンドを送る

.with-code-annotation[
`com.example.lagomchat.message.impl.MessageServiceImpl`
```scala
// TODO: Entity の参照を取得
*val entity = registry.refFor[RoomEntity](RoomEntity.RoomId)

override def sendMessage(userId: String) = ServiceCall { requestMessage =>
  val message = Message(requestMessage.body, userId, DateTime.now())
  // TODO: メッセージを Entity に送る
* entity.ask(PostMessage(message.body, message.user, message.timestamp)).map { _ =>
*   // メッセージを PubSub に publish する
*   topic.publish(message)
*   Done
  }
}
```
]

* `refFor` で Entity への参照を取得 .small[(Entity の ID は固定値)]
* `ask` を使って `PostMessage` を送信
* Entity から応答があったら、メッセージを `publish`

???

★Hands-On★

---

## 間に合わなかった場合

下記のコマンドでブランチを`step4`に切り替えてください

.with-code-annotation[
`Terminal`
```bash
git checkout step4
```
]

---

## 実装の確認

http://localhost:9000/chat

* .with-checkbox-on[リロードしても過去のメッセージが確認できる]
* .with-checkbox-on[新しいユーザーも過去のメッセージが確認できる]

---
class: middle, center

### Hands-On STEP 4
## Circuit Breaker を作動させる

???

【1:10: STEP3: 永続化】

---

## Circuit Breaker?

* 一定回数失敗するとAPIコールを遮断 (Open)
* 一定時間でAPIコールできる状態に (Half-Open)
* APIコールが成功すれば元に戻る (Close)

.center[
![](r/img/cb-open-close.svg)
]

Lagom では全ての API コールで Circuit Breaker が有効

---

## User Service

下記のAPIからチャットルームに居る
ユーザーの一覧が確認できる

* `GET` http://localhost:9000/users

.with-code-annotation[
`JSON`
```javascript
[{"name":"user1"},{"name":"user2"}]
```
]

---

## User Service を止めてみましょう

`user-service.kill` を `true` に設定

.with-code-annotation[
`user-impl/src/main/resources/application.conf`
```javascript
user-service.kill = true
```
]

何度か API を呼び出す

.with-arrow[http://localhost:9000/users]

---

## User Serivce が停止

エラーの応答がすぐに返ってくるようになる

.center[
.width-26[![](r/img/circuit-break-error.png)]
]

---

## 何が起きたのか？

User Serivce の API が何度もタイムアウトになったため<br>
Web Gateway で Circuit Breaker が作動した

.center[
.height-14[![](r/img/circuit-break.svg)]
]

---

## 障害から復旧させてみる

1. `user-service.kill` を `false` に設定 .small[(障害から復旧)]
2. 約 15 秒経過 .small[(Circuit Breaker が Half-Open になる)]
3. 再びユーザー一覧が取得できるようになる
.with-arrow[http://localhost:9000/users]

---

## まとめ

Lagom には大規模なマイクロサービスアーキテクチャに
必要な機能を備えている

* 非同期/ノンブロッキング API
* PubSub
* ES + CQRS
* Circuit Breaker

???

【1:20: STEP4: CB】

---

## ハンズオンで体験しなかった機能

* クラスタリングで負荷分散
* サービスを跨いだ PubSub

---
class: center

## Question?

<iframe src="https://wall2.sli.do/event/psvkzxqk"  width="800" height="500" frameborder=0></iframe>