import {Link, useLocation} from "react-router-dom";

function TicketEnviado () {

    const location = useLocation()

    const ticket = location.state as {usuario: string, problema: string}

    // 3. Creamos la función para el botón
    /*const handleVolver = () => {
        navigate("/"); // Viajamos a la raíz
    }*/

    return (
        <>
            <div style={{padding: '20px', border: '2px solid orange'}}>
                {
                    ticket.problema? (
                        <p>Gracias usuario {ticket.usuario}, revisaremos tu Problema {ticket.problema}</p>
                    ) : (
                        <p style={{color: 'red'}}>ERROR: No se envío ningun ticket con el problema</p>
                    )
                }
            </div>
            <Link to={"/"}>Volver al Inicio</Link>
            {/*<button onClick={handleVolver}>Volver al Inicio</button>*/}
        </>
    )
}

export default TicketEnviado