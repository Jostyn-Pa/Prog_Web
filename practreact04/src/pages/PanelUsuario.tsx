import {Link, useParams} from "react-router-dom";
import {useEffect} from "react";

function PanelUsuario() {

    const {name} = useParams<{name : string}>()

    useEffect(() => {
        console.log(`Logueado como ${name}`)
    }, []);

    return (
        <>
            <div style={{padding: "20px", border: "2px solid blue"}}>
                <h1>Bienvenido a tu Panel Privado <b>{name}</b></h1>
                <Link to="/">Cerrar Sesión</Link>
            </div>
        </>
    )
}

export default PanelUsuario