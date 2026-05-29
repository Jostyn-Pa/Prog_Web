import * as React from "react";
import {useEffect} from "react";

export interface SensorProps {
    ubicacion: string
    tempMaxima?: number
}

function Sensor(props: SensorProps) {

    const [temperatura, setTemperatura] = React.useState(20)

    useEffect(() => {
        console.log(`El sensor se ha renderizado ${temperatura}`)
    })

    useEffect(() => {
        console.log(`La temperatura ha cambiado a: ${temperatura}`)
    }, [temperatura]);

    const handleSubirTemp = () => {
        setTemperatura(temperatura + 1)
    }

    const handleBajarTemp = () => {
        setTemperatura(temperatura - 1)
    }

    return (
        <>
            <div style={{border: '2px solid black', padding: '20px', marginTop: '15px'}}>
                <h4>Ubicación del Sensor: {props.ubicacion}</h4>

                <p>Temperatura Actual: {temperatura}</p>
                {props.tempMaxima && <p style={{color: 'red'}}>Límite de Seguridad: {props.tempMaxima}</p>}

                <hr/>
                <button onClick={handleSubirTemp}>Subir Temperatura</button>
                <button onClick={handleBajarTemp}>Bajar Temperatura</button>
            </div>
        </>
    )
}

export default Sensor