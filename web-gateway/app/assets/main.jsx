var Route = ReactRouter.Route;

function post(url, data) {
    return $.ajax({
        url: url,
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8"
    });
}


class Message extends React.Component {

    constructor(props) {
        this.state = {message: props.message, timestamp: props.timestamp, from: props.sender}
    }

    render() {
        return (
            <div className="message">
                <div className="meta">
                    <span className="from">{this.state.from}</span>
                    <span className="timestamp">{this.state.timestamp}</span>
                </div>
                <div className="message-body">
                    <span className="body">{this.state.message}</span>
                </div>
            </div>
        );
    }
}

class Timeline extends React.Component {
    render() {
        return (
            <div>timeline</div>
        );
    }
}

class MessageForm extends React.Component {

    constructor(props) {
        this.state = {message: ""};
    }

    handleMessageChange(e) {
        this.setState({message: e.target.value});
    }

    handleSubmit(e) {
        e.preventDefault();
        var message = this.state.message.trim();
        if (!message) {
            return;
        }
        post(`/api/hello/}`, {
            message: message
        })
        .done(() => this.setState({message: ""}))
        .fail(() => console.error(`Failed post the message: ${message}`));
    }

    render() {
        return (
            <form className="message-form" onSubmit={this.handleSubmit.bind(this)}>
                <input type="text"
                       value={this.state.message}
                       placeholder="Say something..."
                       onChange={this.handleMessageChange.bind(this)} />
                <input type="submit" value="POST" />
            </form>
        );
    }
}

class App extends React.Component {

    render() {
        return (
            <div>
                <Timeline/>
                <MessageForm/>
            </div>
        );
    }
}

ReactDOM.render(
    <ReactRouter.Router>
        <Route path="/" component={App}/>
    </ReactRouter.Router>
    ,
    document.getElementById("content")
);