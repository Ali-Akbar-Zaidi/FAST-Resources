// const url = 'https://calories-burned-by-api-ninjas.p.rapidapi.com/v1/caloriesburned?activity=skiing';
const options = {
	method: 'GET',
	headers: {
		'X-RapidAPI-Key': '21e98cf022msh14404d6e4a4ac9cp1bf828jsna95e489f662a',
		'X-RapidAPI-Host': 'calories-burned-by-api-ninjas.p.rapidapi.com'
	}
};


const getCal = (act,duration)=>
{
    fetch(
        `https://calories-burned-by-api-ninjas.p.rapidapi.com/v1/caloriesburned?activity=${act}`,
        options
    )
    .then((response)=> response.json())
    .then((response)=>
    {
        console.log(response);
        console.log(response[0].total_calories);    
        document.getElementById("ans").value = response[0].total_calories;
    })
};
let submit = document.getElementById("enter");
submit.addEventListener("click",(e)=>
{
    e.preventDefault();
    getCal(cal_input.value);
})



document.addEventListener("DOMContentLoaded", function () {
    const formOpenBtn = document.querySelector("#form-open"),
        home = document.querySelector(".home"),
        formContainer = document.querySelector(".form_container"),
        formCloseBtn = document.querySelector(".form_close"),
        signupBtn = document.querySelector("#signup"),
        loginBtn = document.querySelector("#login"),
        pwShowHide = document.querySelectorAll(".pw_hide"),
        hamburgerIcon = document.getElementById("hamburger-icon"),
        myLinks = document.getElementById("myLinks");

    formOpenBtn.addEventListener("click", () => home.classList.add("show"));
    formCloseBtn.addEventListener("click", () => home.classList.remove("show"));

    pwShowHide.forEach((icon) => {
        icon.addEventListener("click", () => {
            let getPwInput = icon.parentElement.querySelector("input");
            if (getPwInput.type === "password") {
                getPwInput.type = "text";
                icon.classList.replace("uil-eye-slash", "uil-eye");
            } else {
                getPwInput.type = "password";
                icon.classList.replace("uil-eye", "uil-eye-slash");
            }
        });
    });

    signupBtn.addEventListener("click", (e) => {
        e.preventDefault();
        formContainer.classList.add("active");
    });

    loginBtn.addEventListener("click", (e) => {
        e.preventDefault();
        formContainer.classList.remove("active");
    });

    hamburgerIcon.addEventListener("click", () => {
        myLinks.classList.toggle("show-links");
    });
});


getCal("running");