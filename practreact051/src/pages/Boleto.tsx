import {useLocation} from "react-router-dom";

function Boleto () {

    const location = useLocation()

    const datosBoleto = location.state as {pasajero: string, destino: string} | null

    return (
        <>
            <div style={{padding: "20px", background: "#eee"}}>
                <h2>Ticket de Abordaje</h2>
                {
                    datosBoleto ? (
                        <p>
                            Pasajero: <b>{datosBoleto.pasajero}</b>
                            <br/>
                            Destino: <b>{datosBoleto.destino}</b>
                        </p>
                    ) : (
                        <p style={{color:'red'}}>No se encontró ninguna reserva</p>
                    )
                }
            </div>
        </>
    )
}

export default Boleto