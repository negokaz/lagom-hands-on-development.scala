(function(remark) {

  function RemoteApi(slideshow, url, options) {

    var self = this
      , defaultOptions = {
          debug: false
        };

    options = options || defaultOptions;

    for (var key in defaultOptions) {
      if (typeof options[key] === 'undefined') {
          options[key] = defaultOptions[key];
      }
    }

    self.follow = function() {
      var follower = new Follower(slideshow, url, options);
      return follower;
    };

    self.control = function(passcode) {
      var controller =  new Controller(slideshow, url, passcode, options);
      return controller;
    };
  }

  function Follower(slideshow, url, options) {
    var self = this;

    self.socketClient = new WebSocketClient(url + "follow", options);

    self.socketClient.onconnect = function() {
      if(self.followWhen()) {
        self.sync();
      }
    }

    // ondefiance を無理やり起こすための変数
    // 最後に follow したindexと現在表示しようとしているindexを比較して
    // 異なる場合に ondefiance を起こす
    var latestFollowIndex = null;

    self.socketClient.onreceive = function(data) {
      if (data.follow_slide && self.followWhen()) {
        // 必ず gotoSlide の前に値を入れておく
        latestFollowIndex = data.follow_slide.index;
        slideshow.gotoSlide(data.follow_slide.index + 1);
      }
    };

    slideshow.on('showSlide', function(slide) {
      if (latestFollowIndex != slide.getSlideIndex()) {
        self.ondefiance();
      }
    });

    self.sync = function() {
      self.socketClient.send({
        "request": "latest_command"
      });
    };
  }
  Follower.prototype.ondefiance = function() {};
  Follower.prototype.followWhen = function() { return true };

  function Controller(slideshow, url, passcode, options) {
    var self = this;

    self.socketClient = new WebSocketClient(url + "control?passcode=" + passcode, options);

    slideshow.on('showSlide', function(slide) {
      self.socketClient.send({
        "goto_slide": {
          "index": slide.getSlideIndex()
        }
      });
    });
  }
  Controller.prototype.onstartsurvey = function(category) {};
  Controller.prototype.onsurveyresult = function(category, answer) {};

  function WebSocketClient(url, options) {
    var self = this;

    var connection = new ReconnectingWebSocket(url, null, {
      debug: options.debug
    });

    self.send = function(obj) {
      connection.send(JSON.stringify(obj));
    };

    connection.onopen = function() {
      self.onconnect();
    };

    connection.onclose = function() {
      self.ondisconnect();
    };

    connection.onmessage = function(e) {
      self.onreceive(JSON.parse(e.data));
    };
  }
  WebSocketClient.prototype.onconnect    = function() {};
  WebSocketClient.prototype.onreceive    = function(data) {};
  WebSocketClient.prototype.ondisconnect = function() {};

  remark.remote = function(slideshow, url, options) {
    return new RemoteApi(slideshow, url, options);
  };

})(remark);
