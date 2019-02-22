$(document).ready(function() {
   
  $('#loginForm').submit(function(event) {
       
      /*var producer = $('#producer').val();
      var model = $('#model').val();
      var price = $('#price').val();*/
      var form=$('#ajax-login');
  	var formUser=$('#user');
  	var formPassword=$('#password');
      var json = { "ajax-login" : form, "user" : formUser, "password": formPassword};
       
    $.ajax({
        url: '/login',
        data: JSON.stringify(json),
        type: "POST",
         
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(smartphone) {
            var respContent = "";
             
            respContent += "<span class='success'>Smartphone was created: [";
            respContent += loginForm.form + " : ";
            respContent += loginForm.formUser + " : " ;
            respContent += loginForm.formPassword + "]</span>";
             
            $("#sloginFromResponse").html(respContent);         
        }
    });
      
    event.preventDefault();
  });
    
});