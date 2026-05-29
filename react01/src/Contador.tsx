import * as React from "react";
import HolaMundo2 from "./HolaMundo2.tsx";

function Contador() {

    const [contador, setContador] = React.useState(0);

    const handleUpdate = () => {
        setContador(contador + 1)
    }

    const handleDown = () => {
        setContador(contador - 1)
    }

    return (
        <>
            <div>Valor {contador}</div>
            <br/>
            <button onClick={handleUpdate}>+</button>
            <button onClick={handleDown}>-</button>
            <br/>
            <HolaMundo2 name={"Pablo"} age={contador}/>
        </>
    )
}

export default Contador