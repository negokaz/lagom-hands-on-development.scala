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
            this.is = 'about-me';
            this.properties = {
                url: {
                    type: String,
                    value: playRoutes.controllers.ChatController.aboutMe().absoluteURL()
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
    }]);

    return Element;
}());
//# sourceMappingURL=data:application/json;charset=utf8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImVsZW1lbnRzL2NoYXQvYWJvdXQtbWUuanMiXSwibmFtZXMiOlsiUG9seW1lciIsImlzIiwicHJvcGVydGllcyIsInVybCIsInR5cGUiLCJTdHJpbmciLCJ2YWx1ZSIsInBsYXlSb3V0ZXMiLCJjb250cm9sbGVycyIsIkNoYXRDb250cm9sbGVyIiwiYWJvdXRNZSIsImFic29sdXRlVVJMIl0sIm1hcHBpbmdzIjoiOzs7Ozs7QUFBQUE7QUFBQTtBQUFBO0FBQUE7O0FBQUE7QUFBQTtBQUFBLHlDQUU2QjtBQUNiLGlCQUFLQyxFQUFMLEdBQVUsVUFBVjtBQUNBLGlCQUFLQyxVQUFMLEdBQWtCO0FBQ2RDLHFCQUFLO0FBQ0RDLDBCQUFNQyxNQURMO0FBRURDLDJCQUFPQyxXQUFXQyxXQUFYLENBQXVCQyxjQUF2QixDQUFzQ0MsT0FBdEMsR0FBZ0RDLFdBQWhEO0FBRk47QUFEUyxhQUFsQjtBQU1IO0FBVmI7QUFBQTtBQUFBLGtDQVdzQixDQUFFO0FBWHhCO0FBQUE7QUFBQSxnQ0FZb0IsQ0FBRTtBQVp0QjtBQUFBO0FBQUEsbUNBYXVCLENBQUU7QUFiekI7QUFBQTtBQUFBLG1DQWN1QixDQUFFO0FBZHpCO0FBQUE7QUFBQSwyQ0FlK0IsQ0FBRTtBQWZqQzs7QUFBQTtBQUFBIiwiZmlsZSI6ImVsZW1lbnRzL2NoYXQvYWJvdXQtbWUuanMiLCJzb3VyY2VzQ29udGVudCI6WyJQb2x5bWVyKGNsYXNzIEVsZW1lbnQge1xuXG4gICAgICAgICAgICBiZWZvcmVSZWdpc3RlcigpIHtcbiAgICAgICAgICAgICAgICB0aGlzLmlzID0gJ2Fib3V0LW1lJztcbiAgICAgICAgICAgICAgICB0aGlzLnByb3BlcnRpZXMgPSB7XG4gICAgICAgICAgICAgICAgICAgIHVybDoge1xuICAgICAgICAgICAgICAgICAgICAgICAgdHlwZTogU3RyaW5nLFxuICAgICAgICAgICAgICAgICAgICAgICAgdmFsdWU6IHBsYXlSb3V0ZXMuY29udHJvbGxlcnMuQ2hhdENvbnRyb2xsZXIuYWJvdXRNZSgpLmFic29sdXRlVVJMKClcbiAgICAgICAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgICAgIH07XG4gICAgICAgICAgICB9XG4gICAgICAgICAgICBjcmVhdGVkKCkge31cbiAgICAgICAgICAgIHJlYWR5KCkge31cbiAgICAgICAgICAgIGF0dGFjaGVkKCkge31cbiAgICAgICAgICAgIGRldGFjaGVkKCkge31cbiAgICAgICAgICAgIGF0dHJpYnV0ZUNoYW5nZWQoKSB7fVxuICAgICAgICB9KTsiXX0=
