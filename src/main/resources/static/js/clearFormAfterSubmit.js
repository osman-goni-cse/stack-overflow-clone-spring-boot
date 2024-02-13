// document.querySelector('form').onsubmit = e => {
//     e.preventDefault();
//     e.target.submit();
//     e.target.reset();
//     return false;
// };

// function submitForm() {
//     console.log("Form submission successful");
//     var form = document.querySelector('form');
//     setTimeout(function () {
//         form.submit(); // Submit the form after a short delay
//         form.reset(); // Reset the form after submission
//     }, 100);
//
//     return false;
// };
function clearForm() {
    console.log("Form Clearing.....wait");
    document.getElementById('tagId').value = '';
    document.getElementById('tagName').value = '';
    document.getElementById('tagDetails').value = '';
}