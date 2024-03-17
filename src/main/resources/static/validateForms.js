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