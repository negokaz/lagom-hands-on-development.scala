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
            this.is = 'user-list';
            this.properties = {
                users: {
                    type: Array,
                    value: []
                },
                usersURL: {
                    type: String,
                    value: playRoutes.controllers.ChatController.otherUsers().absoluteURL()
                },
                userStreamURL: {
                    type: String,
                    value: playRoutes.controllers.ChatController.userEvents().webSocketURL()
                }
            };
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
        key: 'addUser',
        value: function addUser(user) {
            this.push('users', user);
        }
    }, {
        key: '_receiveUserEvent',
        value: function _receiveUserEvent(e) {
            var event = JSON.parse(e.detail.data);
            switch (event.type) {
                case 'UserCreated':
                    this.addUser(event.user);
                    break;
                default:
                    console.warn('Unhandled event ' + event.type);
                    break;
            }
        }
    }]);

    return Element;
}());
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImVsZW1lbnRzL2NoYXQvdXNlci1saXN0LmpzIl0sIm5hbWVzIjpbIlBvbHltZXIiLCJpcyIsInByb3BlcnRpZXMiLCJ1c2VycyIsInR5cGUiLCJBcnJheSIsInZhbHVlIiwidXNlcnNVUkwiLCJTdHJpbmciLCJwbGF5Um91dGVzIiwiY29udHJvbGxlcnMiLCJDaGF0Q29udHJvbGxlciIsIm90aGVyVXNlcnMiLCJhYnNvbHV0ZVVSTCIsInVzZXJTdHJlYW1VUkwiLCJ1c2VyRXZlbnRzIiwid2ViU29ja2V0VVJMIiwidXNlciIsInB1c2giLCJlIiwiZXZlbnQiLCJKU09OIiwicGFyc2UiLCJkZXRhaWwiLCJkYXRhIiwiYWRkVXNlciIsImNvbnNvbGUiLCJ3YXJuIl0sIm1hcHBpbmdzIjoiOzs7Ozs7QUFBQUE7QUFBQTtBQUFBO0FBQUE7O0FBQUE7QUFBQTtBQUFBLHlDQUU2QjtBQUNiLGlCQUFLQyxFQUFMLEdBQVUsV0FBVjtBQUNBLGlCQUFLQyxVQUFMLEdBQWtCO0FBQ2RDLHVCQUFPO0FBQ0hDLDBCQUFNQyxLQURIO0FBRUhDLDJCQUFPO0FBRkosaUJBRE87QUFLZEMsMEJBQVU7QUFDTkgsMEJBQU1JLE1BREE7QUFFTkYsMkJBQU9HLFdBQVdDLFdBQVgsQ0FBdUJDLGNBQXZCLENBQXNDQyxVQUF0QyxHQUFtREMsV0FBbkQ7QUFGRCxpQkFMSTtBQVNkQywrQkFBZTtBQUNYViwwQkFBTUksTUFESztBQUVYRiwyQkFBT0csV0FBV0MsV0FBWCxDQUF1QkMsY0FBdkIsQ0FBc0NJLFVBQXRDLEdBQW1EQyxZQUFuRDtBQUZJO0FBVEQsYUFBbEI7QUFjSDtBQWxCYjtBQUFBO0FBQUEsa0NBbUJzQixDQUFFO0FBbkJ4QjtBQUFBO0FBQUEsZ0NBb0JvQixDQUFFO0FBcEJ0QjtBQUFBO0FBQUEsbUNBcUJ1QixDQUFFO0FBckJ6QjtBQUFBO0FBQUEsbUNBc0J1QixDQUFFO0FBdEJ6QjtBQUFBO0FBQUEsMkNBdUIrQixDQUFFO0FBdkJqQztBQUFBO0FBQUEsZ0NBeUJvQkMsSUF6QnBCLEVBeUIwQjtBQUNWLGlCQUFLQyxJQUFMLENBQVUsT0FBVixFQUFtQkQsSUFBbkI7QUFDSDtBQTNCYjtBQUFBO0FBQUEsMENBNkI4QkUsQ0E3QjlCLEVBNkJpQztBQUNqQixnQkFBSUMsUUFBUUMsS0FBS0MsS0FBTCxDQUFXSCxFQUFFSSxNQUFGLENBQVNDLElBQXBCLENBQVo7QUFDQSxvQkFBT0osTUFBTWhCLElBQWI7QUFDSSxxQkFBSyxhQUFMO0FBQ0kseUJBQUtxQixPQUFMLENBQWFMLE1BQU1ILElBQW5CO0FBQ0E7QUFDSjtBQUNJUyw0QkFBUUMsSUFBUixzQkFBZ0NQLE1BQU1oQixJQUF0QztBQUNBO0FBTlI7QUFRSDtBQXZDYjs7QUFBQTtBQUFBIiwiZmlsZSI6ImVsZW1lbnRzL2NoYXQvdXNlci1saXN0LmpzIiwic291cmNlc0NvbnRlbnQiOlsiUG9seW1lcihjbGFzcyBFbGVtZW50IHtcblxuICAgICAgICAgICAgYmVmb3JlUmVnaXN0ZXIoKSB7XG4gICAgICAgICAgICAgICAgdGhpcy5pcyA9ICd1c2VyLWxpc3QnO1xuICAgICAgICAgICAgICAgIHRoaXMucHJvcGVydGllcyA9IHtcbiAgICAgICAgICAgICAgICAgICAgdXNlcnM6IHtcbiAgICAgICAgICAgICAgICAgICAgICAgIHR5cGU6IEFycmF5LFxuICAgICAgICAgICAgICAgICAgICAgICAgdmFsdWU6IFtdXG4gICAgICAgICAgICAgICAgICAgIH0sXG4gICAgICAgICAgICAgICAgICAgIHVzZXJzVVJMOiB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0eXBlOiBTdHJpbmcsXG4gICAgICAgICAgICAgICAgICAgICAgICB2YWx1ZTogcGxheVJvdXRlcy5jb250cm9sbGVycy5DaGF0Q29udHJvbGxlci5vdGhlclVzZXJzKCkuYWJzb2x1dGVVUkwoKVxuICAgICAgICAgICAgICAgICAgICB9LFxuICAgICAgICAgICAgICAgICAgICB1c2VyU3RyZWFtVVJMOiB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0eXBlOiBTdHJpbmcsXG4gICAgICAgICAgICAgICAgICAgICAgICB2YWx1ZTogcGxheVJvdXRlcy5jb250cm9sbGVycy5DaGF0Q29udHJvbGxlci51c2VyRXZlbnRzKCkud2ViU29ja2V0VVJMKClcbiAgICAgICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgICAgIH07XG4gICAgICAgICAgICB9XG4gICAgICAgICAgICBjcmVhdGVkKCkge31cbiAgICAgICAgICAgIHJlYWR5KCkge31cbiAgICAgICAgICAgIGF0dGFjaGVkKCkge31cbiAgICAgICAgICAgIGRldGFjaGVkKCkge31cbiAgICAgICAgICAgIGF0dHJpYnV0ZUNoYW5nZWQoKSB7fVxuXG4gICAgICAgICAgICBhZGRVc2VyKHVzZXIpIHtcbiAgICAgICAgICAgICAgICB0aGlzLnB1c2goJ3VzZXJzJywgdXNlcik7XG4gICAgICAgICAgICB9XG5cbiAgICAgICAgICAgIF9yZWNlaXZlVXNlckV2ZW50KGUpIHtcbiAgICAgICAgICAgICAgICB2YXIgZXZlbnQgPSBKU09OLnBhcnNlKGUuZGV0YWlsLmRhdGEpO1xuICAgICAgICAgICAgICAgIHN3aXRjaChldmVudC50eXBlKSB7XG4gICAgICAgICAgICAgICAgICAgIGNhc2UgJ1VzZXJDcmVhdGVkJzpcbiAgICAgICAgICAgICAgICAgICAgICAgIHRoaXMuYWRkVXNlcihldmVudC51c2VyKTtcbiAgICAgICAgICAgICAgICAgICAgICAgIGJyZWFrO1xuICAgICAgICAgICAgICAgICAgICBkZWZhdWx0OlxuICAgICAgICAgICAgICAgICAgICAgICAgY29uc29sZS53YXJuKGBVbmhhbmRsZWQgZXZlbnQgJHtldmVudC50eXBlfWApO1xuICAgICAgICAgICAgICAgICAgICAgICAgYnJlYWs7XG4gICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgfVxuICAgICAgICB9KTsiXX0=
