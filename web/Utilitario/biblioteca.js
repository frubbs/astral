function mascaraData(campoData){
    //var kC = (document.all) ? event.keyCode : e.keyCode;
    //var tecla=window.event.keyCode; 
        
    var data = campoData.value;
    
    //if ( kC == 8 && kC == 46 ) {
        if (data.length == 2) {
            data = data + '/';
            document.forms[0].sDtNascForm.value = data;
            return true;              
        } else {
            if (data.length == 5){
                data = data + '/';
                document.forms[0].sDtNascForm.value = data;
                return true;
            }
        }
 //   }
 }

function validaCampos() {
    if (document.forms[0].sNomeForm.value == "") {
        alert("Informe o nome !");
        document.forms[0].sNomeForm.focus();
        return false;
    } 

    if (document.forms[0].sDtNascForm.value == "") {
        alert("Informe a data de nascimento !");
        document.forms[0].sDtNascForm.focus();
        return false;
    }                
}