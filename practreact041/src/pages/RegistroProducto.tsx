import * as React from "react";
import TarjetaVisual, {type ProductoProps} from "./TarjetaVisual.tsx";

function RegistroProducto () {

    const [productoGuardado, setProductoGuardado] = React.useState<ProductoProps|null>(null)

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event?.preventDefault()

        const formData = new FormData(event.currentTarget)
        const nombreExtradio = formData.get("nombre")
        const ofertaEspecialExtraida = formData.get("ofertaEspecial")

        setProductoGuardado({
            nombre: nombreExtradio,
            ofertaEspecial: ofertaEspecialExtraida
        })
    }

    return (
        <>
            <div style={{padding: "20px"}}>
                <h2>Registrar Nuevo Producto</h2>

                <form onSubmit={handleSubmit}>
                    <label>Nombre del Producto: </label>
                    <input type="text" name="nombre"/>
                    <br/>
                    <br/>
                    <label>Oferta Especial: </label>
                    <input type="text" name="ofertaEspecial"/>
                    <br/>
                    <input type="submit" value="Registrar"/>

                </form>
                {productoGuardado && (
                    <TarjetaVisual nombre={productoGuardado.nombre} ofertaEspecial={productoGuardado.ofertaEspecial}/>
                )}
            </div>
        </>
    )
}

export default RegistroProducto