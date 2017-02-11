'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Polymer(function () {
    function Element() {
        _classCallCheck(this, Element);
    }

    _createClass(Element, [{
        key: 'beforeRegister',
        value: function beforeRegister() {
            this.is = 'chat-message-list';
            this.properties = {
                messages: {
                    type: Array,
                    value: []
                },
                messagesURL: {
                    type: String,
                    value: playRoutes.controllers.ChatController.chatMessages().absoluteURL()
                }
            };
            this.observers = ['_messagesChanged(messages.*)'];
        }
    }, {
        key: 'created',
        value: function created() {}
    }, {
        key: 'ready',
        value: function ready() {}
    }, {
        key: 'attached',
        value: function attached() {}
    }, {
        key: 'detached',
        value: function detached() {}
    }, {
        key: 'attributeChanged',
        value: function attributeChanged() {}
    }, {
        key: 'addMessage',
        value: function addMessage(message) {
            this.push('messages', message);
        }
    }, {
        key: '_messagesChanged',
        value: function _messagesChanged(messages) {
            // show newest
            this.$.list.scrollToIndex(this.messages.length - 1);
        }
    }, {
        key: '_receiveMessages',
        value: function _receiveMessages(e) {
            var messages = e.detail.xhr.response;
            var _iteratorNormalCompletion = true;
            var _didIteratorError = false;
            var _iteratorError = undefined;

            try {
                for (var _iterator = messages[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
                    var message = _step.value;

                    this.addMessage({ username: message.user, message: message.body, timestamp: new Date(message.timestamp).toISOString() });
                }
            } catch (err) {
                _didIteratorError = true;
                _iteratorError = err;
            } finally {
                try {
                    if (!_iteratorNormalCompletion && _iterator.return) {
                        _iterator.return();
                    }
                } finally {
                    if (_didIteratorError) {
                        throw _iteratorError;
                    }
                }
            }
        }
    }]);

    return Element;
}());
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImVsZW1lbnRzL2NoYXQvY2hhdC1tZXNzYWdlLWxpc3QuanMiXSwibmFtZXMiOlsiUG9seW1lciIsImlzIiwicHJvcGVydGllcyIsIm1lc3NhZ2VzIiwidHlwZSIsIkFycmF5IiwidmFsdWUiLCJtZXNzYWdlc1VSTCIsIlN0cmluZyIsInBsYXlSb3V0ZXMiLCJjb250cm9sbGVycyIsIkNoYXRDb250cm9sbGVyIiwiY2hhdE1lc3NhZ2VzIiwiYWJzb2x1dGVVUkwiLCJvYnNlcnZlcnMiLCJtZXNzYWdlIiwicHVzaCIsIiQiLCJsaXN0Iiwic2Nyb2xsVG9JbmRleCIsImxlbmd0aCIsImUiLCJkZXRhaWwiLCJ4aHIiLCJyZXNwb25zZSIsImFkZE1lc3NhZ2UiLCJ1c2VybmFtZSIsInVzZXIiLCJib2R5IiwidGltZXN0YW1wIiwiRGF0ZSIsInRvSVNPU3RyaW5nIl0sIm1hcHBpbmdzIjoiOzs7Ozs7QUFBQUE7QUFBQTtBQUFBO0FBQUE7O0FBQUE7QUFBQTtBQUFBLHlDQUU2QjtBQUNiLGlCQUFLQyxFQUFMLEdBQVUsbUJBQVY7QUFDQSxpQkFBS0MsVUFBTCxHQUFrQjtBQUNkQywwQkFBVTtBQUNOQywwQkFBTUMsS0FEQTtBQUVOQywyQkFBTztBQUZELGlCQURJO0FBS2RDLDZCQUFhO0FBQ1RILDBCQUFNSSxNQURHO0FBRVRGLDJCQUFPRyxXQUFXQyxXQUFYLENBQXVCQyxjQUF2QixDQUFzQ0MsWUFBdEMsR0FBcURDLFdBQXJEO0FBRkU7QUFMQyxhQUFsQjtBQVVBLGlCQUFLQyxTQUFMLEdBQWlCLENBQ2YsOEJBRGUsQ0FBakI7QUFHSDtBQWpCYjtBQUFBO0FBQUEsa0NBa0JzQixDQUFFO0FBbEJ4QjtBQUFBO0FBQUEsZ0NBbUJvQixDQUFFO0FBbkJ0QjtBQUFBO0FBQUEsbUNBb0J1QixDQUFFO0FBcEJ6QjtBQUFBO0FBQUEsbUNBcUJ1QixDQUFFO0FBckJ6QjtBQUFBO0FBQUEsMkNBc0IrQixDQUFFO0FBdEJqQztBQUFBO0FBQUEsbUNBd0J1QkMsT0F4QnZCLEVBd0JnQztBQUNoQixpQkFBS0MsSUFBTCxDQUFVLFVBQVYsRUFBc0JELE9BQXRCO0FBQ0g7QUExQmI7QUFBQTtBQUFBLHlDQTRCNkJaLFFBNUI3QixFQTRCdUM7QUFDdkI7QUFDQSxpQkFBS2MsQ0FBTCxDQUFPQyxJQUFQLENBQVlDLGFBQVosQ0FBMEIsS0FBS2hCLFFBQUwsQ0FBY2lCLE1BQWQsR0FBdUIsQ0FBakQ7QUFDSDtBQS9CYjtBQUFBO0FBQUEseUNBaUM2QkMsQ0FqQzdCLEVBaUNnQztBQUNoQixnQkFBSWxCLFdBQVdrQixFQUFFQyxNQUFGLENBQVNDLEdBQVQsQ0FBYUMsUUFBNUI7QUFEZ0I7QUFBQTtBQUFBOztBQUFBO0FBRWhCLHFDQUFvQnJCLFFBQXBCLDhIQUE4QjtBQUFBLHdCQUFyQlksT0FBcUI7O0FBQzFCLHlCQUFLVSxVQUFMLENBQWdCLEVBQUNDLFVBQVVYLFFBQVFZLElBQW5CLEVBQXlCWixTQUFTQSxRQUFRYSxJQUExQyxFQUFnREMsV0FBVyxJQUFJQyxJQUFKLENBQVNmLFFBQVFjLFNBQWpCLEVBQTRCRSxXQUE1QixFQUEzRCxFQUFoQjtBQUNIO0FBSmU7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUtuQjtBQXRDYjs7QUFBQTtBQUFBIiwiZmlsZSI6ImVsZW1lbnRzL2NoYXQvY2hhdC1tZXNzYWdlLWxpc3QuanMiLCJzb3VyY2VzQ29udGVudCI6WyJQb2x5bWVyKGNsYXNzIEVsZW1lbnQge1xuXG4gICAgICAgICAgICBiZWZvcmVSZWdpc3RlcigpIHtcbiAgICAgICAgICAgICAgICB0aGlzLmlzID0gJ2NoYXQtbWVzc2FnZS1saXN0JztcbiAgICAgICAgICAgICAgICB0aGlzLnByb3BlcnRpZXMgPSB7XG4gICAgICAgICAgICAgICAgICAgIG1lc3NhZ2VzOiB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0eXBlOiBBcnJheSxcbiAgICAgICAgICAgICAgICAgICAgICAgIHZhbHVlOiBbXVxuICAgICAgICAgICAgICAgICAgICB9LFxuICAgICAgICAgICAgICAgICAgICBtZXNzYWdlc1VSTDoge1xuICAgICAgICAgICAgICAgICAgICAgICAgdHlwZTogU3RyaW5nLFxuICAgICAgICAgICAgICAgICAgICAgICAgdmFsdWU6IHBsYXlSb3V0ZXMuY29udHJvbGxlcnMuQ2hhdENvbnRyb2xsZXIuY2hhdE1lc3NhZ2VzKCkuYWJzb2x1dGVVUkwoKVxuICAgICAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgfTtcbiAgICAgICAgICAgICAgICB0aGlzLm9ic2VydmVycyA9IFtcbiAgICAgICAgICAgICAgICAgICdfbWVzc2FnZXNDaGFuZ2VkKG1lc3NhZ2VzLiopJ1xuICAgICAgICAgICAgICAgIF07XG4gICAgICAgICAgICB9XG4gICAgICAgICAgICBjcmVhdGVkKCkge31cbiAgICAgICAgICAgIHJlYWR5KCkge31cbiAgICAgICAgICAgIGF0dGFjaGVkKCkge31cbiAgICAgICAgICAgIGRldGFjaGVkKCkge31cbiAgICAgICAgICAgIGF0dHJpYnV0ZUNoYW5nZWQoKSB7fVxuXG4gICAgICAgICAgICBhZGRNZXNzYWdlKG1lc3NhZ2UpIHtcbiAgICAgICAgICAgICAgICB0aGlzLnB1c2goJ21lc3NhZ2VzJywgbWVzc2FnZSk7XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIF9tZXNzYWdlc0NoYW5nZWQobWVzc2FnZXMpIHtcbiAgICAgICAgICAgICAgICAvLyBzaG93IG5ld2VzdFxuICAgICAgICAgICAgICAgIHRoaXMuJC5saXN0LnNjcm9sbFRvSW5kZXgodGhpcy5tZXNzYWdlcy5sZW5ndGggLSAxKTtcbiAgICAgICAgICAgIH1cblxuICAgICAgICAgICAgX3JlY2VpdmVNZXNzYWdlcyhlKSB7XG4gICAgICAgICAgICAgICAgdmFyIG1lc3NhZ2VzID0gZS5kZXRhaWwueGhyLnJlc3BvbnNlO1xuICAgICAgICAgICAgICAgIGZvciAodmFyIG1lc3NhZ2Ugb2YgbWVzc2FnZXMpIHtcbiAgICAgICAgICAgICAgICAgICAgdGhpcy5hZGRNZXNzYWdlKHt1c2VybmFtZTogbWVzc2FnZS51c2VyLCBtZXNzYWdlOiBtZXNzYWdlLmJvZHksIHRpbWVzdGFtcDogbmV3IERhdGUobWVzc2FnZS50aW1lc3RhbXApLnRvSVNPU3RyaW5nKCl9KVxuICAgICAgICAgICAgICAgIH1cbiAgICAgICAgICAgIH1cbiAgICAgICAgfSk7Il19
