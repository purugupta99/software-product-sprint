function getLoginDetail(){
    fetch('/login').then(response => response.json()).then((loginDetails) => {
        console.log(loginDetails);  
        
        var welcome_tab = document.getElementById("welcome-tab");
        var login_button = document.getElementById("login-button");
        var submit_button = document.getElementById("submit-button");

        login_button.href = loginDetails.logUrl
        
        if(loginDetails.state == "logged out"){
            login_button.innerText = "Login";
            submit_button.disabled = true;

            // data-toggle="tooltip" data-placement="bottom" title="Hooray!"
            $("#submit-button").attr("data-toggle", "tooltip");
            $("#submit-button").attr("data-placement", "bottom");
            $("#submit-button").attr("title", "Login to leave a comment!");

        } else {
            login_button.innerText = "Logout";
            submit_button.disabled = false;

            // $("#username-input").attr("value", loginDetails.userEmail)
            welcome_tab.innerText = "Welcome, " + loginDetails.userEmail
            document.getElementById("username-input").value = loginDetails.userEmail
            $("#username-input").attr("readonly", true)
        }
    }).catch(e => {
        console.log(e);
    });
}

