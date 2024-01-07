
    document.addEventListener('DOMContentLoaded', function() {

    const profileImage = document.querySelector('.profile-header');
    const dropdownMenu = document.querySelector('.profile-dropdown');

    profileImage.addEventListener('click', function() {
    dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
});

    window.addEventListener('click', function(event) {
    if (event.target !== profileImage) {
    dropdownMenu.style.display = 'none';
}
});
});

const logo=document.querySelector('.logo');
logo.addEventListener('click',()=>{
    location.href='/navigation/home';
})