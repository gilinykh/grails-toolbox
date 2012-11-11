<html>

<head>
    <title>Hello world</title>
    <meta name="layout" content="bootstrap"/>
    <r:require module="plugin-page"/>
</head>

<body>

<div class="container">
    <h2>Resources plugin</h2>
    <p>use different templates for HTML structure based on layout (e.g., grid or fluid; Default is grid)</p>
</div>

<div class="container">
    <h3>Resources</h3>

    <table class="table">
        <thead>
        <tr>
            <th>Submited</th>
            <th>Type</th>
            <th>Title</th>
        </tr>
        </thead>

        <tr>
            <td>6 months ago</td>
            <td>Blog post</td>
            <td>Trololo</td>
            <td></td>
            <td><a class="btn btn-warning btn-small" href="">!</a></td>
        </tr>

        <tr>
            <td>6 months ago</td>
            <td>Blog post</td>
            <td>Trololo</td>
            <td></td>
            <td><a class="should-be-checked btn btn-warning btn-small" href="#report" data-toggle="modal">!</a></td>
        </tr>
    </table>

    <a class="btn pull-right" href="">Add new Resource</a>
</div>

%{--modal - report--}%
<div id="report" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>Modal header</h3>
    </div>
    <div class="modal-body">
        <p>Ok. We will check this item. Your comment will help us a lot</p>
        <textarea rows="3" class="support-comment"></textarea>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn">Close</a>
        <a href="#" class="btn btn-primary">Send</a>
    </div>
</div>

</body>

</html>
