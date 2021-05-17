const registrationMessage = () => {
    const message = document.getElementById("registrationMessage").value;
    const div = document.createElement("div");
    div.append(message);
    document.getElementById("registration").prepend(div);
    div.style.color = 'red';
}

window.onload = registrationMessage();