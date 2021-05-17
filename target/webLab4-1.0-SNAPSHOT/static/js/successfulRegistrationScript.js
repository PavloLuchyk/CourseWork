const registrationMessage = () =>{
    const div = document.createElement("div");
    let textNode = document.createTextNode("You have successfully registered. Now you can log in");
    div.style.color = 'green';
    div.append(textNode);
    document.getElementById("loginForm").prepend(div);
}

window.onload = registrationMessage();