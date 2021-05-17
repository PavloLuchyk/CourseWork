const loginFailedMessage = () =>{
    const div = document.createElement("div");
    let textNode = document.createTextNode("Wrong username or password");
    div.style.color = 'red';
    div.append(textNode);
    document.getElementById("loginForm").prepend(div);
}

window.onload = loginFailedMessage();