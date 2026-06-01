import {useLocation} from "react-router-dom";

function Resumen () {
    // 7. Llama al Hook de locación (la terminal de llegadas)

    const location = useLocation()

    // 8. Extrae el 'state' de la locación y dile a TypeScript qué forma tiene el objeto (adoptante y mascota) o null
    const adopcion = location.state as {name: string, animal: string} | null

    return (
        <>
            <div style={{padding: '20px', border: '2px solid green'}}>
                {/* 9. Usa el renderizado condicional con el operador ternario (? :) */}
                {/* Si existen datos, muestra un mensaje de felicitaciones con los nombres. */}
                {/* Si NO existen, muestra un mensaje de error "No hay adopción en proceso". */}
                {
                    adopcion?
                        (
                            <p>Felicitaciones: {adopcion.name}, haz adoptado a: {adopcion.animal}</p>
                        ) : (
                            <p style={{color:'red'}}>No hay adopción en proceso</p>
                        )
                }
            </div>
        </>
    )
}

export default Resumen