function getCommentTab(parentDiv, username, comment){
    var commentDiv = document.createElement('div');
    commentDiv.className = 'comment-div';
    commentDiv.innerText = comment;
    
    var userDiv = document.createElement('div');
    userDiv.className = 'user-div';
    userDiv.innerText = '- ' + username;

    parentDiv.appendChild(commentDiv);
    parentDiv.appendChild(userDiv);

    return parentDiv;
}

function fillDisplay(){
    fetch('/comment').then(response => response.json()).then((comments) => {
        // console.log(comments)
        var comment_display = document.getElementById("comment-display");

        var i=0;
        for (i = 0; i<comments.length; i++){
            console.log(comments[i].id)
            var comment_div = document.createElement('div');
            comment_div.className = 'comment-tab';
            // comment_div.innerText = comments[i].comment;

            comment_div = getCommentTab(comment_div, comments[i].username, comments[i].comment)

            comment_display.appendChild(comment_div);
            comment_display.appendChild(document.createElement("br"));
        }
    });
}