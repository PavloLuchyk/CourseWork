let check = () => {
    document.getElementById("submitButton").disabled = passwordCheck();
}


let passwordCheck = () =>{
    const password = document.getElementById("password").value;
    const passwordConfirm = document.getElementById("confirm_password").value;
    const passwordCheckMessage = document.getElementById("passwordCheck");
    const passwordConfirmationMessage = document.getElementById("message");
    const regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    let disabled = true;
    if (password.length >= 8
        && password.length <= 20
        && regex.test(password)){
        passwordCheckMessage.innerHTML = "";
        disabled = false;
    } else {
        passwordCheckMessage.innerHTML = "Password must have from 8-20 characters, " +
                                        "contain at least one uppercase letter, one lowercase,one special character" +
                                        " and one number ";
        passwordCheckMessage.style.color = 'red';
        disabled = true;
    }
    if (password == passwordConfirm) {
        passwordConfirmationMessage.style.color = 'green';
        passwordConfirmationMessage.innerHTML = 'Passwords matching';
    } else {
        passwordConfirmationMessage.style.color = 'red';
        passwordConfirmationMessage.innerHTML = 'Passwords do not matching';
        disabled = true;
    }

    return disabled
}

const emailValidation = () =>{
    const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    if (!re.test(document.getElementById("email").value)){
        document.getElementById("emailMessage").innerHTML = "Email is not valid, email should be like: example@gmail.com";
        document.getElementById("emailMessage").style.color = 'red';
        document.getElementById("submitButton").disabled = true;
    } else {
        document.getElementById("emailMessage").innerHTML ="";
        document.getElementById("submitButton").disabled = false;
    }
}