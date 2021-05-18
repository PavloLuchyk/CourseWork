const registrationMessage = () => {
    const message = document.getElementById("updateMessage").value;
    const div = document.createElement("div");
    div.append(message);
    document.getElementById("registration").prepend(div);
    div.style.color = 'red';
}

window.onload = registrationMessage();