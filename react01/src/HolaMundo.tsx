function HolaMundo(props: {name: string}) {

    const handleClick = () => {
        alert(`clicked ${props.name}`)
    }

    return (
        <>
            <h4>Hola Mundo {props.name}</h4>
            <b>Ejemplo de Componente React</b>
            <button style={{marginLeft: '10px'}} onClick={handleClick}>Click </button>
        </>
    )
}

export default HolaMundo