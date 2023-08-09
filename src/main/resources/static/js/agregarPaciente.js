

let nombre = document.getElementById("nombre").value
let apellido = document.getElementById("apellido").value
let dni = document.getElementById("dni").value
let fecha = document.getElementById("fecha").value

const formulario = document.getElementById('add-paciente');

formulario.addEventListener('submit',function(event){
    event.preventDefault();

    const paciente = {
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        dni: document.getElementById("dni").value,
        fechaDeAlta: document.getElementById("fecha").value



    };

    console.log(paciente);

   const url = '/pacientes';
    const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(paciente)
    }

    fetch(url, settings)
            .then(response => response.json())
            .then(data => {
               //Si no hay ningun error se muestra un mensaje diciendo que el estudiante
               //se agrego bien
               alert("Se ha agregado el Paciente con éxito!")
                 resetUploadForm();

            })
            .catch(error => {
                 //Si hay algun error se muestra un mensaje diciendo que el estudiante
                 //no se pudo guardar y se intente nuevamente
                 alert("Error, intente nuevamente!")
                   //se dejan todos los campos vacíos por si se quiere ingresar otro estudiante
                   resetUploadForm();})

})

function resetUploadForm(){
    document.querySelector('#nombre').value = "";
    document.querySelector('#apellido').value = "";
    document.getElementById("dni").value = "";
    document.getElementById("fecha").value = "";
}
