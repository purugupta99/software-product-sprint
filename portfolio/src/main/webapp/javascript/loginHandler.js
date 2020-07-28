function getLoginDetail(){
    fetch('/login').then(response => response.json()).then((loginDetails) => {
        console.log(loginDetails);  
        
        var login_button = document.getElementById("login-button");
        login_button.href = loginDetails.logUrl
        
        if(loginDetails.state == "logged out"){
            login_button.innerText = "Login";
        } else {
            login_button.innerText = "Logout";
        }
    }).catch(e => {
        console.log(e);
    });
}