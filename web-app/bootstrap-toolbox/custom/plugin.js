$(document).ready(function () {
    $('.should-be-checked').tipsy({
        html:true,
        fallback:"This item was added automatically. <br>You can tell us that something is wrong" });

    $('.add-resource').tipsy({
        fallback: "Coming soon =)"
    });

    var sendData = function($elem, data, modal, okMessage) {
        var url = $elem.data('url');

        $.ajax({
            url:url,
            type:'POST',
            data: (data),

            success:function (data) {
                modal.modal('hide');
                notify(okMessage)
            },

            error: function(e) {
                notify('Oups =(. Something goes wrong')
            }
        });
    };

    var notify = function(message) {
        $('.notifications.top-right').notify({
            message: { html: message },
            fadeOut: { enabled: true },
        }).show();
    };

    var plugin = $('#data').data('plugin');

    $('#report .send').click(function(e) {
        e.preventDefault();

        var $this = $(this);
        var modal = $this.closest('.modal');
        var comment = modal.find('.support-comment').val();

        sendData($this, {comment: comment}, modal, 'Thank you!<br> We will check this resource soon');
    });

    $('#add-resource .send').click(function(e) {
        e.preventDefault();

        var $this = $(this);
        var modal = $this.closest('.modal');
        var comment = modal.find('.support-comment').val();
        var url = modal.find('.resource-url').val();


        sendData($this, {comment: comment, url: url}, modal, 'Thank you!<br> We will check this resource soon');
    });
});