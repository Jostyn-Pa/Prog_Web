function FormularioPostulacion() {

    function buscarEmpleo(formData: FormData) {
        const candidato = formData.get('candidato')
        const puesto = formData.get('puesto')

        alert(`Se ha postulado: ${candidato} al puesto: ${puesto}`)
    }

    return (
        <>
            <form action={buscarEmpleo}>
                <div>
                    Candidato: <input type='text' name='candidato'/>
                    <br/>
                    Puesto: <input type='text' name='puesto'/>
                    <br/>
                    <input type='submit' value='Buscar'/>
                </div>
            </form>
        </>
    )
}

export default FormularioPostulacion