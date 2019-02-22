$(function(){
	
	var form=$('#ajax-login');
	var formUser=$('#user');
	var formPassword=$('#password');
	// Set up an event listener for the contact form.
	$(form).submit(function(event) {
	    // Stop the browser from submitting the form.
	    event.preventDefault();

	    // TODO
	});
	// Serialize the form data.
	var formData = $(form).serialize();
	// Submit the form using AJAX.
	$.ajax({
	    type: 'POST',
	    url: $(form).attr('action'),
	    data: formData
	}).done(function(response) {
    // Make sure that the formMessages div has the 'success' class.
    $(formMessages).removeClass('error');
    $(formMessages).addClass('success');

    // Set the message text.
    $(formMessages).text(response);

    // Clear the form.
    $('#user').val('');
    $('#password').val('');
    
}).fail(function(data) {
    // Make sure that the formMessages div has the 'error' class.
    $(formMessages).removeClass('success');
    $(formMessages).addClass('error');

    // Set the message text.
    if (data.responseText !== '') {
        $(formMessages).text(data.responseText);
    } else {
        $(formMessages).text('Oops! An error occured and your message could not be sent.');
    }
});
	
}










/*<div class="body"></div>
<div class="grad"></div>
<div class="header">
	<div>Site<span>Random</span></div>
</div>
<br>
<div class="login">
		<input id="user" type="text" placeholder="user" name='j_username'><br>
		<input type="password" placeholder="password" name='j_password'><br>
	    <button type="submit" class="registerbtn"<a href="loginvalidation/{username}/{password}">Register</button>
		    
		
</div>
</form>
  
</head>
<script src="jquery-2.1.0.min.js"></script>
<script src="app.js"></script>

*/