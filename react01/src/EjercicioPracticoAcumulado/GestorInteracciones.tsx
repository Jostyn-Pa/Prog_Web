import * as React from "react";

function GestorInteracciones (props: {creador:string}) {

    const [contador, setContador] = React.useState(100)

    const handleLike = () => {
        setContador(contador + 1)
    }

    const handleNotificar = () => {
        alert(`Te has suscrito a: ${props.creador}`)
    }
    return (
        <>
            <div style={{border: '1px solid red', padding: '15px', marginTop: '10px'}}>
                <p>Total de Likes: {contador}</p>
                <button onClick={handleLike}>Dar Like</button>
                <button style={{marginLeft: '10px'}} onClick={handleNotificar}>Suscribirse</button>
            </div>
        </>
    )
}

export default GestorInteracciones