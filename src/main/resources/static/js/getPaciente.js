window.addEventListener('load', function () {


    let insert_table = document.getElementById('insert.tr')
    let btnDelete = document.getElementById('btn-delete')


    const url = '/pacientes';
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            data.forEach(persona => {
                insert_table.innerHTML += `
                <tr class="list-item" >
                <td class="item-id-pac" >${persona.id}</td>
                <td class="item-pac">${persona.nombre}</td>
                <td class="item-pac">${persona.apellido}</td>
                <td class="item-dni-pac">${persona.dni}</td>
                <td class="item-fecha-pac">${persona.fechaDeAlta}</td>
                <th class="item-btn-delete-pac"><img id="btn-delete" width="15px" height="15px" src="https://cdn.icon-icons.com/icons2/624/PNG/512/Delete-80_icon-icons.com_57340.png" alt=""></th>
                </tr >
                `


                console.log(persona)
            })
            console.log(data)

            console.log("app funcionando");
        });







    function deleteBy(id) {
        //con fetch invocamos a la API de odontologos con el método DELETE
        //pasandole el id en la URL
        const url = '/pacientes/' + id;
        const settings = {
            method: 'DELETE'
        }
        fetch(url, settings)
            .then(response => response.json())

        //borrar la fila del odontolodo eliminado
        let row_id = "#tr_" + id;
        document.querySelector(row_id).remove();

    }
});
