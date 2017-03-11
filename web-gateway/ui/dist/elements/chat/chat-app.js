"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Polymer(function () {
    function Element() {
        _classCallCheck(this, Element);
    }

    _createClass(Element, [{
        key: "beforeRegister",
        value: function beforeRegister() {
            this.is = 'chat-app';

            this.properties = {
                messagesURL: {
                    type: String,
                    value: playRoutes.controllers.ChatController.messageStream().webSocketURL()
                }
            };
        }
    }, {
        key: "created",
        value: function created() {}
    }, {
        key: "ready",
        value: function ready() {}
    }, {
        key: "attached",
        value: function attached() {}
    }, {
        key: "detached",
        value: function detached() {}
    }, {
        key: "attributeChanged",
        value: function attributeChanged() {}
    }, {
        key: "_handleSubmit",
        value: function _handleSubmit(e) {}
    }, {
        key: "_receiveMessage",
        value: function _receiveMessage(e) {
            var message = JSON.parse(e.detail.data);
            this.querySelector("chat-message-list").addMessage({ username: message.user, message: message.body, timestamp: new Date(message.timestamp).toISOString() });
        }
    }]);

    return Element;
}());
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImVsZW1lbnRzL2NoYXQvY2hhdC1hcHAuanMiXSwibmFtZXMiOlsiUG9seW1lciIsImlzIiwicHJvcGVydGllcyIsIm1lc3NhZ2VzVVJMIiwidHlwZSIsIlN0cmluZyIsInZhbHVlIiwicGxheVJvdXRlcyIsImNvbnRyb2xsZXJzIiwiQ2hhdENvbnRyb2xsZXIiLCJtZXNzYWdlU3RyZWFtIiwid2ViU29ja2V0VVJMIiwiZSIsIm1lc3NhZ2UiLCJKU09OIiwicGFyc2UiLCJkZXRhaWwiLCJkYXRhIiwicXVlcnlTZWxlY3RvciIsImFkZE1lc3NhZ2UiLCJ1c2VybmFtZSIsInVzZXIiLCJib2R5IiwidGltZXN0YW1wIiwiRGF0ZSIsInRvSVNPU3RyaW5nIl0sIm1hcHBpbmdzIjoiOzs7Ozs7QUFBQUE7QUFBQTtBQUFBO0FBQUE7O0FBQUE7QUFBQTtBQUFBLHlDQUU2QjtBQUNiLGlCQUFLQyxFQUFMLEdBQVUsVUFBVjs7QUFFQSxpQkFBS0MsVUFBTCxHQUFrQjtBQUNkQyw2QkFBYTtBQUNUQywwQkFBTUMsTUFERztBQUVUQywyQkFBT0MsV0FBV0MsV0FBWCxDQUF1QkMsY0FBdkIsQ0FBc0NDLGFBQXRDLEdBQXNEQyxZQUF0RDtBQUZFO0FBREMsYUFBbEI7QUFNSDtBQVhiO0FBQUE7QUFBQSxrQ0FZc0IsQ0FBRTtBQVp4QjtBQUFBO0FBQUEsZ0NBYW9CLENBQUU7QUFidEI7QUFBQTtBQUFBLG1DQWN1QixDQUFFO0FBZHpCO0FBQUE7QUFBQSxtQ0FldUIsQ0FBRTtBQWZ6QjtBQUFBO0FBQUEsMkNBZ0IrQixDQUFFO0FBaEJqQztBQUFBO0FBQUEsc0NBa0IwQkMsQ0FsQjFCLEVBa0I2QixDQUVoQjtBQXBCYjtBQUFBO0FBQUEsd0NBc0I0QkEsQ0F0QjVCLEVBc0IrQjtBQUNmLGdCQUFJQyxVQUFVQyxLQUFLQyxLQUFMLENBQVdILEVBQUVJLE1BQUYsQ0FBU0MsSUFBcEIsQ0FBZDtBQUNBLGlCQUFLQyxhQUFMLENBQW1CLG1CQUFuQixFQUNLQyxVQURMLENBQ2dCLEVBQUNDLFVBQVVQLFFBQVFRLElBQW5CLEVBQXlCUixTQUFTQSxRQUFRUyxJQUExQyxFQUFnREMsV0FBVyxJQUFJQyxJQUFKLENBQVNYLFFBQVFVLFNBQWpCLEVBQTRCRSxXQUE1QixFQUEzRCxFQURoQjtBQUVIO0FBMUJiOztBQUFBO0FBQUEiLCJmaWxlIjoiZWxlbWVudHMvY2hhdC9jaGF0LWFwcC5qcyIsInNvdXJjZXNDb250ZW50IjpbIlBvbHltZXIoY2xhc3MgRWxlbWVudCB7XG5cbiAgICAgICAgICAgIGJlZm9yZVJlZ2lzdGVyKCkge1xuICAgICAgICAgICAgICAgIHRoaXMuaXMgPSAnY2hhdC1hcHAnO1xuXG4gICAgICAgICAgICAgICAgdGhpcy5wcm9wZXJ0aWVzID0ge1xuICAgICAgICAgICAgICAgICAgICBtZXNzYWdlc1VSTDoge1xuICAgICAgICAgICAgICAgICAgICAgICAgdHlwZTogU3RyaW5nLFxuICAgICAgICAgICAgICAgICAgICAgICAgdmFsdWU6IHBsYXlSb3V0ZXMuY29udHJvbGxlcnMuQ2hhdENvbnRyb2xsZXIubWVzc2FnZVN0cmVhbSgpLndlYlNvY2tldFVSTCgpXG4gICAgICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgICAgICB9O1xuICAgICAgICAgICAgfVxuICAgICAgICAgICAgY3JlYXRlZCgpIHt9XG4gICAgICAgICAgICByZWFkeSgpIHt9XG4gICAgICAgICAgICBhdHRhY2hlZCgpIHt9XG4gICAgICAgICAgICBkZXRhY2hlZCgpIHt9XG4gICAgICAgICAgICBhdHRyaWJ1dGVDaGFuZ2VkKCkge31cblxuICAgICAgICAgICAgX2hhbmRsZVN1Ym1pdChlKSB7XG5cbiAgICAgICAgICAgIH1cblxuICAgICAgICAgICAgX3JlY2VpdmVNZXNzYWdlKGUpIHtcbiAgICAgICAgICAgICAgICB2YXIgbWVzc2FnZSA9IEpTT04ucGFyc2UoZS5kZXRhaWwuZGF0YSk7XG4gICAgICAgICAgICAgICAgdGhpcy5xdWVyeVNlbGVjdG9yKFwiY2hhdC1tZXNzYWdlLWxpc3RcIilcbiAgICAgICAgICAgICAgICAgICAgLmFkZE1lc3NhZ2Uoe3VzZXJuYW1lOiBtZXNzYWdlLnVzZXIsIG1lc3NhZ2U6IG1lc3NhZ2UuYm9keSwgdGltZXN0YW1wOiBuZXcgRGF0ZShtZXNzYWdlLnRpbWVzdGFtcCkudG9JU09TdHJpbmcoKX0pXG4gICAgICAgICAgICB9XG4gICAgICAgIH0pOyJdfQ==
