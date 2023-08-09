

let nombre = document.getElementById("nombre").value
let apellido = document.getElementById("apellido").value
let matricula = document.getElementById("matricula").value

const formulario = document.getElementById('add-odontologo');

formulario.addEventListener('submit',function(event){
    event.preventDefault();

    const odontologo = {
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        numeroDeMatricula: document.getElementById("matricula").value

    };

    console.log(odontologo);

    const url = '/odontologos';
    const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(odontologo)
    }

    fetch(url, settings)
            .then(response => response.json())
            .then(data => {
               //Si no hay ningun error se muestra un mensaje diciendo que el estudiante
               //se agrego bien
               alert("Se ha agregado el Odontólogo con éxito!")
                 resetUploadForm();
                 renderOdontologo()

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
    document.getElementById("matricula").value = "";
}


function renderOdontologo(){

let insert_table = document.getElementById('insert.tr')

const url = '/odontologos';
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            data.forEach(persona => {
            insert_table.innerHTML += `
                                     <tr class="list-item">
                                                         <td class="item-id">${persona.id}</td>
                                                         <td class="item">${persona.nombre}</td>
                                                         <td class="item">${persona.apellido}</td>
                                                         <td class="item-mat">${persona.numeroDeMatricula}</td>
                                                         <a href=""><th class="item-btn-delete"><img width="15px" height="15px" src="https://cdn.icon-icons.com/icons2/624/PNG/512/Delete-80_icon-icons.com_57340.png" alt=""></th></a>
                                                     </tr>
                                     `
                                     console.log(persona)
            })
            console.log(data)

            console.log("app funcionando");
        });
}