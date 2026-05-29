function PerfilUsuario(props: {name: string, profesion: string}) {

    const handleSolicitud=() => {
        alert(`Solicitud enviada a: ${props.name}`)
    }

    return(
        <>
            <h4>Nombre de Usuario {props.name}</h4>
            <b>Cargo: {props.profesion}</b>
            <button style={{marginLeft: '10px'}} onClick={handleSolicitud}>Solicitud</button>
        </>
    )
}

export default PerfilUsuario