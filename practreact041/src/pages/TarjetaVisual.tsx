
export interface ProductoProps {
    nombre: string
    ofertaEspecial? : string
}

function TarjetaVisual(props: ProductoProps) {

    return (
        <>
            <div style={{border:"1px solid black", marginTop: "20px"}}>
                <h4>Producto: <b>{props.nombre}</b></h4>
                {props.ofertaEspecial && <p>Oferta Especial: {props.ofertaEspecial}</p>}
            </div>
        </>
    )
}

export default TarjetaVisual