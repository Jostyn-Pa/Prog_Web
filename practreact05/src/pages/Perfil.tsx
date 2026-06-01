import {Link, useParams} from "react-router-dom";
import * as React from "react";
import HolaMundo from "./HolaMundo.tsx";

function Perfil () {

    const {nombreUsuario} = useParams<{nombreUsuario:string}>()

    const [likes, setLikes] = React.useState(0)

    const handleAumentarLike = () => {
        setLikes(likes + 1)
    }

    return (
        <>
            <div style={{border: '2px solid blue', padding: '20px'}}>
                <HolaMundo name={nombreUsuario || 'Invitado'} />

                <p>Tienes: {likes} Me Gusta</p>
                <br/>
                <button onClick={handleAumentarLike}>Like</button>
                <br/>
                <Link to="/">Volver al Inicio</Link>
            </div>
        </>
    )
}

export default Perfil