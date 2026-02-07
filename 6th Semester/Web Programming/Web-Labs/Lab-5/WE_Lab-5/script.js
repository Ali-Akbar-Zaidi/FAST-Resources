//---------------------------show an alert if any field is empty-------------------------
document.getElementById("myForm").addEventListener("submit", function (event) {
    event.preventDefault();
    var inputs = this.querySelectorAll("input");
    var isEmpty = false;
    for (var i = 0; i < inputs.length - 1; i++) {
        if (inputs[i].value.trim() === "") {
            isEmpty = true;
            break;
        }
    }
    if (isEmpty) {
        alert("Please Fill in all Fields!!!");
    } else {
        var condition1 = validatePhoneNumber();
        var condition2 = validateEmail();
        var condition3 = validatePasswords();
        //---------------------------------onclick event---------------------------------
        if (!condition1) {
            alert("Incorrect Phone Number Format!")
        }
        else if (!condition2) {
            alert("Incorrect Email Format!")
        }
        else if (!condition3) {
            alert("Passwords do NOT Match!")
        }
        else {
            alert("Thank You! Your Request has been Submitted.");
            this.submit();
        }
    }
});

//--------------------------------show a Greeting Message--------------------------------
window.onload = function () {
    alert("Welcome to The Registration Form!!!");
};

//-------------------------------------event onfocus-------------------------------------
document.getElementById('button').onfocus = function () {
    this.style.backgroundColor = 'rgb(164, 162, 162)';
};
document.getElementById('button').onblur = function () {
    this.style.backgroundColor = '';
};
var inputs = document.getElementsByClassName('form-input');
for (var i = 0; i < inputs.length; i++) {
    inputs[i].addEventListener('focus', function () {
        this.style.backgroundColor = 'rgb(221, 193, 193)';
    });

    inputs[i].addEventListener('blur', function () {
        this.style.backgroundColor = '';
    });
}

//-------------------------------------onclick event-------------------------------------
// document.getElementById('button').onclick = function () {
//     alert("Thank You! Your Request has been Submitted.");
// };

//-----------------------------------additional checks-----------------------------------
function validatePhoneNumber() {
    var phoneNumber = document.getElementById('phoneNumber').value;
    var regex = /^03\d{2}-\d{7}$/;
    if (regex.test(phoneNumber)) {
        return true;
    }
    return false;
}

function validateEmail() {
    var email = document.getElementById('email').value;
    if (email.includes('@') && email.includes(".com")) {
        return true;
    }
    return false;
}

function validatePasswords() {
    var pass1 = document.getElementById('password1').value;
    var pass2 = document.getElementById('password2').value;
    if (pass1 == pass2) {
        return true;
    }
    return false;
}