function validateGameForms() {
    var name = document.forms[0]["name"].value;
    var releaseYear = document.forms[0]["releaseYear"].value;
    var duration = document.forms[0]["duration"].value;
    if(name === ""){
        alert("The game must have a name");
        return false
    }
    if(releaseYear === ""){
        alert("The game must have a release year");
        return false;
    }
    if(isNaN(releaseYear)){
        alert("The release year must be a number");
        return false;
    }
    if(releaseYear > 2024 || releaseYear < 2000){
        alert("Invalid release year");
        return false;
    }
    if(duration === ""){
        alert("The game must have duration");
        return false;
    }
    if(isNaN(duration)){
        alert("The duration must be a number");
        return false;
    }
    if(duration <= 0){
        alert("Invalid duration");
        return false;
    }
    return true;
}
function validateTrialForms(){
    var chapter = document.forms[0]["chapter"].value;
    var decor = document.forms[0]["decor"].value;
    var summary = document.forms[0]["summary"].value;
    if(chapter === ""){
        alert("The trial must belong to a chapter");
        return false;
    }
    if(isNaN(chapter)){
        alert("The chapter must be a number");
        return false;
    }
    if(chapter < 0 ){
        alert("Invalid chapter number");
        return false;
    }
    if(decor === ""){
        alert("The trial must have decoration");
        return false;
    }
    /*if(summary === ""){
        alert("The trial must have a summary");
        return false;
    }*/
    return true;
}

function validateCharacterForms(){
    var name = document.forms[0]["name"].value;
    var surname = document.forms[0]["surname"].value;
    var like = document.forms[0]["like"].value;
    var dislike = document.forms[0]["dislike"].value;
    var talent = document.forms[0]["talent"].value;
    var height = document.forms[0]["height"].value;

    if(name === ""){
        alert("The character must have a name");
        return false;
    }
    /*if(surname === ""){
        alert("The character must have a surname");
        return false;
    }*/
    if(like === ""){
        alert("The character must have a like");
        return false;
    }
    if(dislike === ""){
        alert("The character must have a dislike");
        return false;
    }
    if(talent === ""){
        alert("The character must have a talent");
        return false;
    }
    if(height === ""){
        alert("The character must have a height");
        return false;
    }
    if(isNaN(height)){
        alert("The height must be a number");
        return false;
    }
    if(height < 0 ){
        alert("Invalid height number");
        return false;
    }
    return true;
}