import * as React from "react";

function SelectorCantidad() {

    const [cantidad, setCantidad] = React.useState(1)

    const handleAumentar = () => {
        setCantidad(cantidad + 1)
    }

    const handleDisminuir = () => {
        setCantidad(cantidad - 1)
    }

    return (
        <>
            <div>Unidades Seleccionadas {cantidad}</div>
            <br/>
            <button onClick={handleDisminuir}>-</button>
            <button onClick={handleAumentar}>+</button>
        </>
    )
}

export default SelectorCantidad