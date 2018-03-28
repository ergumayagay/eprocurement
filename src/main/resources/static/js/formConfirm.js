document.addEventListener("DOMContentLoaded",function(){

    const validatedForm = document.getElementsByClassName("form-confirm");

    for (let index = 0; index < validatedForm.length; index++) {
        validatedForm[index].addEventListener("submit",function(e){
          if(!confirm("Save changes?")){
            e.preventDefault();
          }
        });
        
    }

});