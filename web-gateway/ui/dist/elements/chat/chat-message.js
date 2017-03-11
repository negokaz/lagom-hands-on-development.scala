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
            this.is = 'chat-message';

            this.properties = {
                username: {
                    type: String,
                    value: "Anonymous"
                },
                timestamp: {
                    type: String,
                    value: ""
                },
                message: {
                    type: String,
                    value: ""
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
    }]);

    return Element;
}());
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImVsZW1lbnRzL2NoYXQvY2hhdC1tZXNzYWdlLmpzIl0sIm5hbWVzIjpbIlBvbHltZXIiLCJpcyIsInByb3BlcnRpZXMiLCJ1c2VybmFtZSIsInR5cGUiLCJTdHJpbmciLCJ2YWx1ZSIsInRpbWVzdGFtcCIsIm1lc3NhZ2UiXSwibWFwcGluZ3MiOiI7Ozs7OztBQUFBQTtBQUFBO0FBQUE7QUFBQTs7QUFBQTtBQUFBO0FBQUEseUNBRTZCO0FBQ2IsaUJBQUtDLEVBQUwsR0FBVSxjQUFWOztBQUVBLGlCQUFLQyxVQUFMLEdBQWtCO0FBQ2RDLDBCQUFVO0FBQ05DLDBCQUFNQyxNQURBO0FBRU5DLDJCQUFPO0FBRkQsaUJBREk7QUFLZEMsMkJBQVc7QUFDUEgsMEJBQU1DLE1BREM7QUFFUEMsMkJBQU87QUFGQSxpQkFMRztBQVNkRSx5QkFBUztBQUNMSiwwQkFBTUMsTUFERDtBQUVMQywyQkFBTztBQUZGO0FBVEssYUFBbEI7QUFjSDtBQW5CYjtBQUFBO0FBQUEsa0NBb0JzQixDQUFFO0FBcEJ4QjtBQUFBO0FBQUEsZ0NBcUJvQixDQUFFO0FBckJ0QjtBQUFBO0FBQUEsbUNBc0J1QixDQUFFO0FBdEJ6QjtBQUFBO0FBQUEsbUNBdUJ1QixDQUFFO0FBdkJ6QjtBQUFBO0FBQUEsMkNBd0IrQixDQUFFO0FBeEJqQzs7QUFBQTtBQUFBIiwiZmlsZSI6ImVsZW1lbnRzL2NoYXQvY2hhdC1tZXNzYWdlLmpzIiwic291cmNlc0NvbnRlbnQiOlsiUG9seW1lcihjbGFzcyBFbGVtZW50IHtcblxuICAgICAgICAgICAgYmVmb3JlUmVnaXN0ZXIoKSB7XG4gICAgICAgICAgICAgICAgdGhpcy5pcyA9ICdjaGF0LW1lc3NhZ2UnO1xuXG4gICAgICAgICAgICAgICAgdGhpcy5wcm9wZXJ0aWVzID0ge1xuICAgICAgICAgICAgICAgICAgICB1c2VybmFtZToge1xuICAgICAgICAgICAgICAgICAgICAgICAgdHlwZTogU3RyaW5nLFxuICAgICAgICAgICAgICAgICAgICAgICAgdmFsdWU6IFwiQW5vbnltb3VzXCJcbiAgICAgICAgICAgICAgICAgICAgfSxcbiAgICAgICAgICAgICAgICAgICAgdGltZXN0YW1wOiB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0eXBlOiBTdHJpbmcsXG4gICAgICAgICAgICAgICAgICAgICAgICB2YWx1ZTogXCJcIlxuICAgICAgICAgICAgICAgICAgICB9LFxuICAgICAgICAgICAgICAgICAgICBtZXNzYWdlOiB7XG4gICAgICAgICAgICAgICAgICAgICAgICB0eXBlOiBTdHJpbmcsXG4gICAgICAgICAgICAgICAgICAgICAgICB2YWx1ZTogXCJcIlxuICAgICAgICAgICAgICAgICAgICB9XG4gICAgICAgICAgICAgICAgfTtcbiAgICAgICAgICAgIH1cbiAgICAgICAgICAgIGNyZWF0ZWQoKSB7fVxuICAgICAgICAgICAgcmVhZHkoKSB7fVxuICAgICAgICAgICAgYXR0YWNoZWQoKSB7fVxuICAgICAgICAgICAgZGV0YWNoZWQoKSB7fVxuICAgICAgICAgICAgYXR0cmlidXRlQ2hhbmdlZCgpIHt9XG4gICAgICAgIH0pOyJdfQ==
