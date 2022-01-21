//Some sort of collection to hold page names here???

const pageNames = ["Home", "About", "Order", "Login", "Contact"];

function getCurrentPage() {
    let currentPage = "";

    if (document.title.includes(pageNames[0]))
        currentPage="home";
    else if (document.title.includes(pageNames[1]))
        currentPage="about";
    else if (document.title.includes(pageNames[2]))
        currentPage="order";
    else if (document.title.includes(pageNames[3]))
        currentPage="login";
    else if (document.title.includes(pageNames[4]))
        currentPage="contact";

    return currentPage;
}

function highlightNavButton() {
    let page = getCurrentPage(); //calls getCurrentPage method
    document.getElementById(page).style.boxShadow = "0px 5px inset";
    // document.getElementById(page).style.borderBottom = "5px solid black";
}

function setBackground() {
    document.body.style.backgroundImage = "url('images/background.jpg')";
    document.body.style.backgroundRepeat = "no-repeat";
    document.body.style.backgroundSize = "120vw 200vh";
    document.body.style.backgroundPosition = "center center";
    document.body.style.backgroundAttachment = "fixed";
}

function setTextArea() {
    try {
        document.getElementById("message").style.fontFamily = "Arial";
        document.getElementById("message").style.fontSize = "11pt";
        document.getElementById("address").style.fontFamily = "Arial";
        document.getElementById("address").style.fontSize = "11pt";
    }
    catch (error) {}
}

//name validation
function validateName() {
    if (document.getElementById("contact_name").value == "") {
        alert("You must provide your name.");
        return false;
    }
    return true;
}
//email regex validation
function validateEmail() {
    let emailReg = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
    
    if (document.getElementById("email").value == "") {
        alert("You must provide your email.")
        return false;
    }
    else if (emailReg.test(document.getElementById("email").value)) {
        return true;
    }
    alert("You have entered an invalid email address!")
    return false;
}
//message validation
function validateMessage() {
    if (document.getElementById("message").value == "") {
        alert("You must fill out a message.");
        return false;
    }
    return true;
}

function validateForm() {
    validateName();
    validateEmail();
    validateMessage();
}


//highlight current page's nav button
highlightNavButton();

//set background image
setBackground();

//set aesthetics for text area in forms
setTextArea();

//validate contact form
document.getElementById("submit_contact").addEventListener("click", validateForm); //calls 3 other validate methods


try {
    document.getElementById("submit_contact").style.backgroundColor = "grey";
    document.getElementById("submit_contact").addEventListener("mouseenter", function() {
        this.style.backgroundColor = "pink";
    })
    document.getElementById("submit_contact").addEventListener("mouseleave", function() {
        this.style.backgroundColor = "grey";
    })
}
catch (error) {}