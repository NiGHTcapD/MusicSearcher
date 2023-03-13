function validateBeats() {
    var x = document.getElementById("beatsPer");
    var c = document.getElementById("beatsPerCallout");
    var regex = /([0-9]+\s*,\s*)*[0-9]+/;
    if (regex.test(x.value) == false && x.value != ""){
        //make warning
        c.innerHTML = "Positive integers only, separated by spaces and a comma. Empty is acceptable as well."
        return false;
    }
    else{
        c.innerHTML = ""
        return true;
    }
}

function validateKeys() {
    var x = document.getElementById("musicKeys");
    var c = document.getElementById("musicKeysCallout");
    var regex = /([A-Z]\s*,\s*)*[A-Z]/;
    if (regex.test(x.value) == false && x.value != ""){
        //make warning
        c.innerHTML = "Capital letters only, separated by spaces and a comma. Empty is acceptable as well."
        return false;
    }
    else{
        c.innerHTML = ""
        return true;
    }
}

function validateSigns() {
    var x = document.getElementById("timeSigns");
    var c = document.getElementById("timeSignsCallout");
    var regex = /([0-9]+\/[0-9]+\s*,\s*)*[0-9]+\/[0-9]+/;
    if (regex.test(x.value) == false && x.value != ""){
        //make warning
        c.innerHTML = "Positive integers only, separated by a slash; separated by spaces and a comma. Empty is acceptable as well."
        return false;
    }
    else{
        c.innerHTML = ""
        return true;
    }

}

function validateTitle() {
    var x = document.getElementById("songTitle");
    var c = document.getElementById("titleCallout");
    if (x.value == ""){
        //make warning
        c.innerHTML = "A song without a title is a song without a definition, humming without recording. Please title your song."
        return false;
    }
    else{
        c.innerHTML = ""
        return true;
    }
}
function validateArtist() {
    var x = document.getElementById("artist");
    var c = document.getElementById("artistCallout");
    if (x.value == ""){
        //make warning
        c.innerHTML = "Somebody had to write the song. Maybe it's too old to remember who, but please, give me a name."
        return false;
    }
    else{
        c.innerHTML = ""
        return true;
    }
}

function validator(){
    if (validateBeats() && validateKeys() && validateSigns() && validateTitle() && validateArtist()){
        document.getElementById("sumbissionCallout").style="color:darkgreen"
        document.getElementById("sumbissionCallout").innerHTML = "Submission accepted.";
        document.getElementById("entry").submit();
    }
    else{
        document.getElementById("sumbissionCallout").style="color:red"
        document.getElementById("sumbissionCallout").innerHTML = "Please double-check all fields. Submission failed.";
    }
}