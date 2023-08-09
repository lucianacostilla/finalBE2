window.addEventListener('load', function () {


    //VISUALIZAR DATOS

    let listaOdontologos = document.getElementById('listaOdontologos')
    let listaPacientes = document.getElementById('listaPaciente')


    const url = '/odontologos';
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            data.forEach(persona => {
                listaOdontologos.innerHTML += `
                <option id="${persona.id}" value="${persona.id}">${persona.nombre + " "+ persona.apellido}</option>
                `


                console.log(persona)
            })
            console.log(data)

            console.log("app funcionando");
        });



    const url2 = '/pacientes';
    const settings2 = {
        method: 'GET'
    }
    fetch(url2, settings2)
        .then(response => response.json())
        .then(data => {
            data.forEach(persona => {
                listaPacientes.innerHTML += `
                <option id="${persona.id}" value="${persona.id}">${persona.nombre + " "+ persona.apellido}</option>
                `


                console.log(persona)
            })
            console.log(data)

            console.log("app funcionando");
        });




    //ENVIAR DATOS A BASE DE DATOS

    let odontologo = document.getElementById("listaOdontologos").value
    let paciente = document.getElementById("listaPaciente").value
    let fecha = document.getElementById("fecha").value

    const formulario = document.getElementById('add-turno');

    formulario.addEventListener('submit',function(event){
        event.preventDefault();

        const turno = {
            odontologo:{id:document.getElementById("listaOdontologos").value} ,
            paciente: {
            id:document.getElementById("listaPaciente").value
            },
            fecha: document.getElementById("fecha").value

        };

        console.log(turno);

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(turno)
        }

        fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                   //Si no hay ningun error se muestra un mensaje diciendo que el estudiante
                   //se agrego bien
                   alert("Se ha agregado el Turno con éxito!")
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
        odontologo = "";
        paciente = "";
        fecha = "";
    }





});