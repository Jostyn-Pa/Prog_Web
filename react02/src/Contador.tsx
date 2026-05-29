import * as React from "react";
import {useEffect} from "react";

function Contador() {

    const [contador, setContador] = React.useState(0)

    useEffect(() => {
        console.log("Componente Montado")

        /*return () => {
            console.log("Componente Desmontado")
        }*/
    })

    useEffect(() => {
        console.log(`Componente Cambiado ${contador}`)
    }, [contador]);

    const handleUpdate = () => {
        setContador(contador + 1)
    }

    const handleDown = () => {
        setContador(contador - 1)
    }

    return (
        <>
            <h4>Contador {contador}</h4>
            <button onClick={handleUpdate}>+</button>
            <button onClick={handleDown}>-</button>
        </>
    )
}

export default Contador