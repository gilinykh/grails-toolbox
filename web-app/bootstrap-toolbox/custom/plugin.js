$(document).ready(function () {
    $('.should-be-checked').tipsy({
        html:true,
        fallback:"This item was added automatically. <br>You can tell us that something is wrong" });

    $('.add-resource').tipsy({
        fallback: "Coming soon =)"
    })
});