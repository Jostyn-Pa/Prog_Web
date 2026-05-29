
export interface ProductoProps {
    nombre: string
    precio: number
    descuento?: number
}
function TarjetaProducto(props: ProductoProps) {

    return(
        <>
            <h4>Nombre Producto: {props.nombre}</h4>
            <h4>Precio: {props.precio}</h4>
            {props.descuento && <b style={{color: "red"}}>Oferta del {props.descuento}</b>}
        </>
    )
}

export default TarjetaProducto