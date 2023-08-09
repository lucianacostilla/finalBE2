window.addEventListener('load', function () {


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


});
