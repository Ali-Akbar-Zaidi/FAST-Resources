const courses = [
    { name: "SE", fee: 250000 },
    { name: "DS", fee: 200000 },
    { name: "CS", fee: 150000 },
    { name: "AF", fee: 100000 },
    { name: "FinTech", fee: 250000 },
    { name: "AI", fee: 200000 },
    { name: "CY", fee: 150000 },
    { name: "Robotics", fee: 100000 }
];

function calculateFee() {
    const courseSelected = document.getElementById("courseSelect").value;
    const selectedCourse = courses[courseSelected];  
    let duration = document.getElementById("noOfSem").value;
    let totalFee = selectedCourse.fee * duration;  
    let scholarship = document.getElementById("scholarship").value;
    let discountedFee = totalFee - (totalFee * scholarship / 100);
    let finalFee = totalFee - discountedFee;
    document.getElementById("result").innerHTML = `<br>Total Fee: PKR ${totalFee.toLocaleString()}<br>Discounted Fee: PKR ${discountedFee.toLocaleString()}<br>Final Fee: PKR ${finalFee.toLocaleString()}`;
}

function validateContactForm() {
    const name = document.getElementById("name").value;
    const number = document.getElementById("number").value;
    const email = document.getElementById("email").value;
    const message = document.getElementById("message").value;
    const captchaInput = document.getElementById("captchaInput").value;

    if (!name || !number || !email || !message || !captchaInput) {
        alert("Please fill in all fields.");
        return false;
    }

    if (!/^[a-zA-Z\s]+$/.test(name)) {
        alert("Name should only contain alphabets and spaces.");
        return false;
    }

    if (!/^\+92\d{10}$/.test(number)) {
        alert("Contact number must be in the format +92XXXXXXXXXX.");
        return false;
    }

    if (!/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[com]{2,}$/.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

    if (parseInt(captchaInput) !== window.captchaAnswer) {
        alert("Captcha does not match. Please try again.");
        return false;
    }

    alert("Form submitted successfully!");
    return true;
}

function generateCaptcha() {
    const num1 = Math.floor(Math.random() * 10) + 1; // 1 to 10
    const num2 = Math.floor(Math.random() * 10) + 1; // 1 to 10
    window.captchaAnswer = num1 + num2; // store answer globally
    document.getElementById("captchaText").textContent = `${num1} + ${num2}`; // show question
    document.getElementById("captchaInput").value = ''; // clear previous input
}

// Generate captcha when the page loads
window.addEventListener("load", function() {
    generateCaptcha();
});

function checkEligibility(){
    const name=document.getElementById("fullName").value;
    const sub1=document.getElementById("Sub1").value;
    const sub2=document.getElementById("Sub2").value;
    const sub3=document.getElementById("Sub3").value;
    const TotalMarks=parseInt(sub1) + parseInt(sub2) + parseInt(sub3);
    const percentage=(TotalMarks/300)*100;
    const Eligible=percentage >= 60;
    document.getElementById("eligibilityResult").innerHTML = `Dear "${name}"<br>Your Total marks are ${TotalMarks}<br>Your Percentage is ${percentage.toFixed(2)}%<br>You are ${Eligible ? "eligible" : "not eligible"}`;
}