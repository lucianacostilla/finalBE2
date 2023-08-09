window.addEventListener('load', function () {

let insert_table = document.getElementById('insert.tr')


  const url = '/turnos';
  const settings = {
    method: 'GET'
  };

  fetch(url, settings)
    .then(response => response.json())
    .then(data => {
      data.forEach(turno => {
                  insert_table.innerHTML += `
                                           <tr class="list-item">
                                                                <td class="item">${turno.fecha}</td>
                                                               <td class="item">${turno.paciente.nombre}</td>
                                                               <td class="item">${turno.odontologo.nombre}</td>


                                                               <a href=""><th class="item-btn-delete"><img width="15px" height="15px" src="https://cdn.icon-icons.com/icons2/624/PNG/512/Delete-80_icon-icons.com_57340.png" alt=""></th></a>
                                                           </tr>
                                           `

                  })
                  console.log(data)
    });


  // aquí puede continuar con el resto del código que interactúa con los elementos
});
