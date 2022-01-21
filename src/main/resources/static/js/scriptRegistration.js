const pageNames = ["Home", "About", "Order", "Login", "Contact", "Registration"];

function getCurrentPage() {
    let currentPage = "";

    if (document.title.includes(pageNames[0]))
        currentPage="home";
    else if (document.title.includes(pageNames[1]))
        currentPage="about";
    else if (document.title.includes(pageNames[2]))
        currentPage="order";
    else if (document.title.includes(pageNames[3]) || document.title.includes(pageNames[5]))
        currentPage="login";
    else if (document.title.includes(pageNames[4]))
        currentPage="contact";

    return currentPage;
}

//calls getCurrentPage method
function highlightNavButton() {
    let page = getCurrentPage();
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

//set background image
setBackground();

//highlight current page's nav button
highlightNavButton();

try {
    document.getElementById("submit").style.backgroundColor = "grey";
    document.getElementById("submit").addEventListener("mouseenter", function() {
        this.style.backgroundColor = "pink";
    })
    document.getElementById("submit").addEventListener("mouseleave", function() {
        this.style.backgroundColor = "grey";
    })
}
catch (error) {}