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
            this.is = 'chat-message-form';
            this.properties = {
                message: {
                    type: String,
                    value: ""
                },
                target: {
                    type: Object,
                    value: function value() {
                        this.$.input;
                    }
                },
                postURL: {
                    type: String,
                    value: playRoutes.controllers.ChatController.receiveMessage().absoluteURL()
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
        key: "submit",
        value: function submit() {
            if (this.message.trim().length > 0) {
                this.querySelector("iron-ajax").generateRequest();
            }
        }
    }, {
        key: "_toObjMessage",
        value: function _toObjMessage(message) {
            return { body: message };
        }
    }, {
        key: "_onPostSuccess",
        value: function _onPostSuccess() {
            this.fire('submit', { message: this.message });
            this.message = "";
        }
    }]);

    return Element;
}());
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImVsZW1lbnRzL2NoYXQvY2hhdC1tZXNzYWdlLWZvcm0uanMiXSwibmFtZXMiOlsiUG9seW1lciIsImlzIiwicHJvcGVydGllcyIsIm1lc3NhZ2UiLCJ0eXBlIiwiU3RyaW5nIiwidmFsdWUiLCJ0YXJnZXQiLCJPYmplY3QiLCIkIiwiaW5wdXQiLCJwb3N0VVJMIiwicGxheVJvdXRlcyIsImNvbnRyb2xsZXJzIiwiQ2hhdENvbnRyb2xsZXIiLCJyZWNlaXZlTWVzc2FnZSIsImFic29sdXRlVVJMIiwidHJpbSIsImxlbmd0aCIsInF1ZXJ5U2VsZWN0b3IiLCJnZW5lcmF0ZVJlcXVlc3QiLCJib2R5IiwiZmlyZSJdLCJtYXBwaW5ncyI6Ijs7Ozs7O0FBQUFBO0FBQUE7QUFBQTtBQUFBOztBQUFBO0FBQUE7QUFBQSx5Q0FFNkI7QUFDYixpQkFBS0MsRUFBTCxHQUFVLG1CQUFWO0FBQ0EsaUJBQUtDLFVBQUwsR0FBa0I7QUFDZEMseUJBQVM7QUFDTEMsMEJBQU1DLE1BREQ7QUFFTEMsMkJBQU87QUFGRixpQkFESztBQUtkQyx3QkFBUTtBQUNKSCwwQkFBTUksTUFERjtBQUVKRiwyQkFBTyxpQkFBVztBQUNkLDZCQUFLRyxDQUFMLENBQU9DLEtBQVA7QUFDSDtBQUpHLGlCQUxNO0FBV2RDLHlCQUFTO0FBQ0xQLDBCQUFNQyxNQUREO0FBRUxDLDJCQUFPTSxXQUFXQyxXQUFYLENBQXVCQyxjQUF2QixDQUFzQ0MsY0FBdEMsR0FBdURDLFdBQXZEO0FBRkY7QUFYSyxhQUFsQjtBQWdCSDtBQXBCYjtBQUFBO0FBQUEsa0NBcUJzQixDQUFFO0FBckJ4QjtBQUFBO0FBQUEsZ0NBc0JvQixDQUFFO0FBdEJ0QjtBQUFBO0FBQUEsbUNBdUJ1QixDQUFFO0FBdkJ6QjtBQUFBO0FBQUEsbUNBd0J1QixDQUFFO0FBeEJ6QjtBQUFBO0FBQUEsMkNBeUIrQixDQUFFO0FBekJqQztBQUFBO0FBQUEsaUNBMkJxQjtBQUNMLGdCQUFJLEtBQUtiLE9BQUwsQ0FBYWMsSUFBYixHQUFvQkMsTUFBcEIsR0FBNkIsQ0FBakMsRUFBb0M7QUFDaEMscUJBQUtDLGFBQUwsQ0FBbUIsV0FBbkIsRUFBZ0NDLGVBQWhDO0FBQ0g7QUFDSjtBQS9CYjtBQUFBO0FBQUEsc0NBaUMwQmpCLE9BakMxQixFQWlDbUM7QUFDbkIsbUJBQU8sRUFBQ2tCLE1BQU1sQixPQUFQLEVBQVA7QUFDSDtBQW5DYjtBQUFBO0FBQUEseUNBcUM2QjtBQUNiLGlCQUFLbUIsSUFBTCxDQUFVLFFBQVYsRUFBb0IsRUFBQ25CLFNBQVMsS0FBS0EsT0FBZixFQUFwQjtBQUNBLGlCQUFLQSxPQUFMLEdBQWUsRUFBZjtBQUNIO0FBeENiOztBQUFBO0FBQUEiLCJmaWxlIjoiZWxlbWVudHMvY2hhdC9jaGF0LW1lc3NhZ2UtZm9ybS5qcyIsInNvdXJjZXNDb250ZW50IjpbIlBvbHltZXIoY2xhc3MgRWxlbWVudCB7XG5cbiAgICAgICAgICAgIGJlZm9yZVJlZ2lzdGVyKCkge1xuICAgICAgICAgICAgICAgIHRoaXMuaXMgPSAnY2hhdC1tZXNzYWdlLWZvcm0nO1xuICAgICAgICAgICAgICAgIHRoaXMucHJvcGVydGllcyA9IHtcbiAgICAgICAgICAgICAgICAgICAgbWVzc2FnZToge1xuICAgICAgICAgICAgICAgICAgICAgICAgdHlwZTogU3RyaW5nLFxuICAgICAgICAgICAgICAgICAgICAgICAgdmFsdWU6IFwiXCJcbiAgICAgICAgICAgICAgICAgICAgfSxcbiAgICAgICAgICAgICAgICAgICAgdGFyZ2V0OiB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0eXBlOiBPYmplY3QsXG4gICAgICAgICAgICAgICAgICAgICAgICB2YWx1ZTogZnVuY3Rpb24oKSB7XG4gICAgICAgICAgICAgICAgICAgICAgICAgICAgdGhpcy4kLmlucHV0O1xuICAgICAgICAgICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgICAgICAgICB9LFxuICAgICAgICAgICAgICAgICAgICBwb3N0VVJMOiB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0eXBlOiBTdHJpbmcsXG4gICAgICAgICAgICAgICAgICAgICAgICB2YWx1ZTogcGxheVJvdXRlcy5jb250cm9sbGVycy5DaGF0Q29udHJvbGxlci5yZWNlaXZlTWVzc2FnZSgpLmFic29sdXRlVVJMKClcbiAgICAgICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgICAgIH07XG4gICAgICAgICAgICB9XG4gICAgICAgICAgICBjcmVhdGVkKCkge31cbiAgICAgICAgICAgIHJlYWR5KCkge31cbiAgICAgICAgICAgIGF0dGFjaGVkKCkge31cbiAgICAgICAgICAgIGRldGFjaGVkKCkge31cbiAgICAgICAgICAgIGF0dHJpYnV0ZUNoYW5nZWQoKSB7fVxuXG4gICAgICAgICAgICBzdWJtaXQoKSB7XG4gICAgICAgICAgICAgICAgaWYgKHRoaXMubWVzc2FnZS50cmltKCkubGVuZ3RoID4gMCkge1xuICAgICAgICAgICAgICAgICAgICB0aGlzLnF1ZXJ5U2VsZWN0b3IoXCJpcm9uLWFqYXhcIikuZ2VuZXJhdGVSZXF1ZXN0KCk7XG4gICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICBfdG9PYmpNZXNzYWdlKG1lc3NhZ2UpIHtcbiAgICAgICAgICAgICAgICByZXR1cm4ge2JvZHk6IG1lc3NhZ2V9O1xuICAgICAgICAgICAgfVxuXG4gICAgICAgICAgICBfb25Qb3N0U3VjY2VzcygpIHtcbiAgICAgICAgICAgICAgICB0aGlzLmZpcmUoJ3N1Ym1pdCcsIHttZXNzYWdlOiB0aGlzLm1lc3NhZ2V9KTtcbiAgICAgICAgICAgICAgICB0aGlzLm1lc3NhZ2UgPSBcIlwiO1xuICAgICAgICAgICAgfVxuICAgICAgICB9KTsiXX0=
